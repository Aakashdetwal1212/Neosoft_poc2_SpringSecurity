package com.poc2.service;

import java.util.List;
import java.util.Optional;

import com.poc2.entity.Project;
import com.poc2.entity.Student;

public interface StudentService {
	
	public Student insertStudent(Student student);
	public Student getStudentById(int id);
	public List<Student> getAllStudent();
	public Student updateStudent(int id, Student student);
	public void deleteStudentById(int id);
	
	public Project insertProject(Project project);
	public void deleteProjectByNo(int projectNo);
	public Project getProject(int projectNo);
}
