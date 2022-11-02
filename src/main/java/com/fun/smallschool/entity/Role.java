package com.fun.smallschool.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import com.fun.smallschool.common.constants.RoleName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "roles")
public class Role {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(name = "name")
	private RoleName name;

    public Role(Integer id) {
        this.id = id;
    }

	public Role(RoleName name) {
		this.name = name;
	}
}