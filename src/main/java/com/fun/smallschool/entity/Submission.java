package com.fun.smallschool.entity;

import com.fun.smallschool.entity.audit.UserAudit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "task_submission")
public class Submission extends UserAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
	@Column(name = "title")
	@Size(max = 100)
    private String title;

	@Column(name = "desc")
	@Size(max = 200)
    private String description;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "file_id")
	private FileUpload file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    public Submission(String title) {
        super();
        this.title = title;
    }
}


