package com.poc2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
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
import com.poc2.repository.ProjectRepository;
import com.poc2.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class StudentServiceImplTest {

	@Mock
	StudentRepository studentRepository;
	@Mock
	ProjectRepository projectRepository;

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

	@Test
	public void getStudentByIdTest() {

		Set<Project> projects = new HashSet<>();
		Project project1 = new Project(1, "irctc", 17);
		Project project2 = new Project(2, "MakeMyTrip", 11);
		projects.add(project1);
		projects.add(project2);
		student = new Student(1, "bob", "builder", "9134667890", "bob@gmail.com", projects);

		Mockito.when(studentRepository.findById(1)).thenReturn(Optional.of(student));

		Student studentExpacted = studentServiceImpl.getStudentById(1);
		assertThat(studentExpacted).isEqualTo(student);
	}

	@Test
	public void getStudentByIdExceptionTest() {

		int id = 1;
		Mockito.when(studentRepository.findById(id))
				.thenThrow(new ResourceNotFoundException("student id:" + id + " does not found"));

		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
			studentServiceImpl.getStudentById(id);
		});
		assertTrue(exception.getMessage().contains("student id:" + id + " does not found"));
	}

	@Test
	public void getAllStudentTest() {

		List<Student> studentList = new ArrayList<>();
		Set<Project> projects = new HashSet<>();
		Project project1 = new Project(1, "irctc", 17);
		Project project2 = new Project(2, "MakeMyTrip", 11);

		projects.add(project1);
		projects.add(project2);

		student = new Student(1, "bob", "builder", "9134667890", "bob@gmail.com", projects);

		studentList.add(student);

		Mockito.when(studentRepository.findAll()).thenReturn(studentList);
		List<Student> studentListExpacted = studentServiceImpl.getAllStudent();

		assertEquals(1, studentListExpacted.size());
	}

	@Test
	public void updateStudentTest() {

		int id = 1;
		List<Student> studentList = new ArrayList<>();
		Set<Project> projects = new HashSet<>();
		Project project1 = new Project(1, "irctc", 17);
		Project project2 = new Project(2, "MakeMyTrip", 11);

		projects.add(project1);
		projects.add(project2);

		student = new Student(1, "bob", "builder", "9134667890", "bob@gmail.com", projects);
		Mockito.when(studentRepository.findById(id)).thenReturn(Optional.of(student));
		Mockito.when(studentRepository.save(student)).thenReturn(student);

		Student studentExpacted = studentServiceImpl.updateStudent(id, student);
		assertThat(studentExpacted.getFirstName()).isEqualTo(student.getFirstName());
	}

	@Test
	public void updateStudentExceptionTest() {

		int id = 1;
		Set<Project> projects = new HashSet<>();
		Project project1 = new Project(1, "irctc", 17);
		Project project2 = new Project(2, "MakeMyTrip", 11);

		projects.add(project1);
		projects.add(project2);

		student = new Student(1, "bob", "builder", "9134667890", "bob@gmail.com", projects);

		Mockito.when(studentRepository.findById(id))
				.thenThrow(new ResourceNotFoundException("student id:" + id + " does not found"));

		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
			studentServiceImpl.updateStudent(id, student);
		});
		assertTrue(exception.getMessage().contains("student id:" + id + " does not found"));
	}

	@Test
	public void deleteStudentByIdTest() {

		int id = 1;
		List<Student> studentList = new ArrayList<>();
		Set<Project> projects = new HashSet<>();
		Project project1 = new Project(1, "irctc", 17);
		Project project2 = new Project(2, "MakeMyTrip", 11);

		projects.add(project1);
		projects.add(project2);

		student = new Student(1, "bob", "builder", "9134667890", "bob@gmail.com", projects);
		Mockito.when(studentRepository.findById(id)).thenReturn(Optional.of(student));

		studentServiceImpl.deleteStudentById(id);
		verify(studentRepository).deleteById(id);
	}

	@Test
	public void deleteStudentByIdExceptionTest() {

		int id = 1;

		Mockito.when(studentRepository.findById(id))
				.thenThrow(new ResourceNotFoundException("student id:" + id + " does not found"));

		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
			studentServiceImpl.deleteStudentById(id);
		});

		assertTrue(exception.getMessage().contains("student id:" + id + " does not found"));
	}

	@Test
	public void deleteProjectByNoTest() {

		int id = 1;
		Set<Project> projects = new HashSet<>();
		Project project1 = new Project(1, "irctc", 17);
		Project project2 = new Project(2, "MakeMyTrip", 11);

		projects.add(project1);
		projects.add(project2);

		student = new Student(1, "bob", "builder", "9134667890", "bob@gmail.com", projects);
		Mockito.when(studentRepository.findById(id)).thenReturn(Optional.of(student));

		studentServiceImpl.deleteProjectByNo(1, 2);
		verify(projectRepository).deleteById(2);

		projects.remove(project2);
	}

	@Test
	public void deleteProjectByNoExceptionTest() {

		int id = 1;
		Mockito.when(studentRepository.findById(id))
				.thenThrow(new ResourceNotFoundException("student id:" + id + " does not found"));

		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
			studentServiceImpl.deleteProjectByNo(1, 2);
		});

		assertTrue(exception.getMessage().contains("student id:" + id + " does not found"));
	}
}
