package com.fun.smallschool.entity;

import com.fun.smallschool.entity.audit.UserAudit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "todo_list")
public class MyTodo extends UserAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
	@Column(name = "title")
	@Size(max = 100)
    private String title;
    
    @NotBlank
	@Column(name = "desc")
	@Size(max = 200)
	@Size(min = 5)
    private String description;

    public MyTodo(String title) {
        super();
        this.title = title;
    }
}


