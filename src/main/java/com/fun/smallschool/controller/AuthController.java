package com.fun.smallschool.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fun.smallschool.common.constants.APIText;
import com.fun.smallschool.common.constants.RoleName;
import com.fun.smallschool.entity.ApplicationUser;
import com.fun.smallschool.entity.Role;
import com.fun.smallschool.exception.ApplicationException;
import com.fun.smallschool.exception.IssueException;
import com.fun.smallschool.payload.request.LoginRequest;
import com.fun.smallschool.payload.request.RegisterRequest;
import com.fun.smallschool.payload.response.APIResponse;
import com.fun.smallschool.payload.response.JwtTokenResponse;
import com.fun.smallschool.repository.RoleRepository;
import com.fun.smallschool.repository.UserRepository;
import com.fun.smallschool.security.JwtAuthenticationEntryPoint;
import com.fun.smallschool.security.JwtTokenProvider;


@RestController
@RequestMapping(APIText.AUTH_URL)
public class AuthController {
    
    private static final String CURRENT_USER_ROLE = "UNDEFINED";

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider; 

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrMail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtTokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new JwtTokenResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        if (Boolean.TRUE.equals(userRepo.checkExistsByUsername(registerRequest.getUsername()))) {
			throw new IssueException(HttpStatus.BAD_REQUEST, "Username is already taken");
		}

		if (Boolean.TRUE.equals(userRepo.checkExistsByEmail(registerRequest.getEmail()))) {
			throw new IssueException(HttpStatus.BAD_REQUEST, "Email is already taken");
		}

        String firstName = registerRequest.getFirstName().toLowerCase();
		String lastName = registerRequest.getLastName().toLowerCase();
		String username = registerRequest.getUsername().toLowerCase();
		String email = registerRequest.getEmail().toLowerCase();
		String password = passwordEncoder.encode(registerRequest.getPassword());

		ApplicationUser user = new ApplicationUser(firstName, lastName, username, email, password);
		List<Role> roles = new ArrayList<>();

		if (userRepo.count() == 0) {
			roles.add(roleRepo.findByName(RoleName.ADMIN)
					.orElseThrow(() -> new ApplicationException(CURRENT_USER_ROLE)));

			roles.add(roleRepo.findByName(RoleName.TEACHER)
					.orElseThrow(() -> new ApplicationException(CURRENT_USER_ROLE)));
		} else {
			roles.add(roleRepo.findByName(RoleName.STUDENT)
					.orElseThrow(() -> new ApplicationException(CURRENT_USER_ROLE)));
		}
        // set roles
		user.setRoles(roles);

        // embedded user to db
		ApplicationUser result = userRepo.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path(APIText.APPLICATION_USER_URL + "/{userId}")
				.buildAndExpand(result.getId()).toUri();

		return ResponseEntity.created(location).body(new APIResponse(Boolean.TRUE, "User registered successfully"));
    }
}

