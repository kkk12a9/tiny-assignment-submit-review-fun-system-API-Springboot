package com.fun.smallschool.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fun.smallschool.entity.audit.UserAudit;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "notices")
public class FileUpload extends UserAudit {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
	@Column(name = "file_name")
    private String fileName;

    @NotBlank
	@Column(name = "file_url")
    private String fileUrl;

    public FileUpload(String fileName, String fileUrl, Integer fileSizeMax) {
        super();
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }
}
