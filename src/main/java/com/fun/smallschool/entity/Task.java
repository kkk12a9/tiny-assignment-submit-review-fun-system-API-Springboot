package com.fun.smallschool.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tasks")
public class Task extends UserAudit {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
	@Column(name = "title")
	@Size(max = 100)
	@Size(min = 10)
    private String title;

    @NotBlank
	@Column(name = "desc")
	@Size(max = 200)
	@Size(min = 10)
    private String description;

    @JsonIgnore
	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Submission> submissions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public Task(String title) {
        super();
        this.title = title;
    }

    public List<Submission> getSubmissions() {
		return this.submissions == null ? null : new ArrayList<>(submissions);
	}

	public void setSubmissions(List<Submission> posts) {
		if (submissions == null) {
			this.submissions = null;
		} else {
			this.submissions = Collections.unmodifiableList(submissions);
		}
	}
}
