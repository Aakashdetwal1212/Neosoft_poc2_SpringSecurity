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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
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
	@NotBlank(message = "mobileNumber can not be blank")
	private String mobile;
	
	@Email(message = "Invalid EmailID")
	@NotBlank(message = "email can not be blank")
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id")
	private Set<Project> projects;
}
