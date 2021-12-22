package com.poc2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.poc2.entity.Project;
import com.poc2.entity.Student;
import com.poc2.exception.ResourceNotFoundException;
import com.poc2.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class StudentServiceImplTest {

	@Mock
	StudentRepository studentRepository;

	@InjectMocks
	StudentServiceImpl studentServiceImpl;

	Student student;

	@Test
	public void insertStudentTest() {

		Set<Project> projects = new HashSet<>();
		Project project1 = new Project(1, "irctc", 17);
		Project project2 = new Project(2, "MakeMyTrip", 11);
		projects.add(project1);
		projects.add(project2);
		student = new Student(1, "bob", "builder", "9134667890", "bob@gmail.com", projects);

		Mockito.when(studentRepository.save(student)).thenReturn(student);
		
		Student studentExpected = studentServiceImpl.insertStudent(student);
		assertThat(studentExpected).isEqualTo(student);
	}
	/*
	@Override
	@Transactional(readOnly = true)
	public Student getStudentById(int id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("student id:" + id + " does not found"));
	}
	
	public void getStudentByIdTest() {
		
		
	}
	*/
}
