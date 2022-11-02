package com.fun.smallschool.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(name = "bookmarks")
public class MyBookmark  extends UserAudit {

	@Column(name = "title")
	@Size(max = 50)
    private String title;

    @Column(name = "desc")
	@Size(max = 100)
    private String description;

    @JsonIgnore
	@OneToMany(mappedBy = "bookmark", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    @JsonIgnore
	@OneToMany(mappedBy = "bookmark", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notice> notices;

    @JsonIgnore
    @OneToOne(mappedBy = "bookmark", cascade = CascadeType.ALL, orphanRemoval = true) 
    private ApplicationUser applicationUser;

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

    public List<Notice> getNotices() {
		return this.notices == null ? null : new ArrayList<>(notices);
	}

	public void setNotices(List<Notice> notices) {
		if (this.notices == null) {
			this.notices = null;
		} else {
			this.notices = Collections.unmodifiableList(notices);
		}
	}
}
