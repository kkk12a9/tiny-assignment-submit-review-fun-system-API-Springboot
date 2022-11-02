package com.fun.smallschool.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fun.smallschool.entity.audit.UserAudit;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "courses")
public class Course extends UserAudit {

    // NOTE: why serialVersionUID is needed?
    // refer: https://www.baeldung.com/java-serial-version-uid 
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;

    @NotBlank
	@Column(name = "title")
	@Size(max = 100)
    private String title;
    
    @NotBlank
	@Column(name = "desc")
	@Size(max = 500)
	@Size(min = 50)
    private String description;

    @NotBlank
	@Column(name = "max_enrollment")
    private Integer maxAllowedEnrollment;

    @NotBlank
	@Column(name = "min_enrollment")
    private Integer minAllowedEnrollment;

	@JsonIgnore
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notice> notices;

    @JsonIgnore
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public Course(String title) {
        super();
        this.title = title;
    }

    public List<Notice> getNotices() {
		return this.notices == null ? null : new ArrayList<>(notices);
	}

	public void setNotices(List<Notice> posts) {
		if (this.notices == null) {
			this.notices = null;
		} else {
			this.notices = Collections.unmodifiableList(notices);
		}
	}

    public List<Task> getTasks() {
		return this.tasks == null ? null : new ArrayList<>(tasks);
	}

	public void setTasks(List<Task> tasks) {
		if (this.tasks == null) {
			this.tasks = null;
		} else {
			this.tasks = Collections.unmodifiableList(tasks);
		}
	}
}
