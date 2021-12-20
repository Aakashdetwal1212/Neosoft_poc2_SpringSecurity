package com.poc2.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {

	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;
	
	@Column(name = "first_name")
	@NotBlank(message = "FirstName can not be blank")
	@NotNull
	private String firstName;
	
	@NotBlank(message = "LastName can not be blank")
	@Column(name = "last_name")
	@NotNull
	private String lastName;
	
	@Pattern(regexp="(^$|[0-9]{10})", message = "MobileNumber Must should start with 91 or 0, length 10")
	private String mobile;
	
	@Email(message = "Invalid EmailID")
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id")
	private Set<Project> projects;
}
