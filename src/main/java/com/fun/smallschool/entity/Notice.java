package com.fun.smallschool.entity;

import com.fun.smallschool.entity.audit.UserAudit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "notices")
public class Notice extends UserAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
	@Column(name = "title")
	@Size(max = 100)
    private String title;
    
    @NotBlank
	@Column(name = "desc")
	@Size(max = 300)
	@Size(min = 10)
    private String description;

	@Column(name = "files")
    private List<FileUpload> files;

    // NOTE: why need to perform BIDIRECTIONAL for one-to-many?
    // refer: https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public Notice(String title, String description) {
        super();
        this.title = title;
        this.description = description;
    }

    public List<FileUpload> getFilesUpload() {
		return this.files == null ? null : new ArrayList<>(this.files);
	}

	public void setFilesUpload(List<FileUpload> posts) {
		if (files == null) {
			this.files = null;
		} else {
			this.files = Collections.unmodifiableList(files);
		}
	}
}


