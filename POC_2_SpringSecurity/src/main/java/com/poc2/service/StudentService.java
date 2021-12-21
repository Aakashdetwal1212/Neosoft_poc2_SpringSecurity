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
	
	public void deleteProjectByNo(int studentId, int projectNo);
}
