package com.poc2.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc2.StudentApplication;
import com.poc2.entity.Project;
import com.poc2.entity.Student;
import com.poc2.repository.StudentRepository;
import com.poc2.service.StudentServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	StudentServiceImpl studentServiceImpl;
	@MockBean
	StudentRepository studentRepository;

	@Mock
	StudentController studentController;

	MvcResult mvcResult;

	Student student;

	private static ObjectMapper mapper = new ObjectMapper();

	//@Test
	public void getStudentTest() throws Exception {

		Set<Project> projects = new HashSet<>();
		Project project1 = new Project(1, "irctc", 17);
		Project project2 = new Project(2, "MakeMyTrip", 11);
		projects.add(project1);
		projects.add(project2);
		student = new Student(1, "bob", "builder", "9134667890", "bob@gmail.com", projects);

		Mockito.when(studentServiceImpl.getStudentById(1)).thenReturn(student);
		String json = mapper.writeValueAsString(student);

		Mockito.when(studentServiceImpl.getStudentById(1)).thenReturn(student);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/student/get/1/admin")
				.contentType(MediaType.APPLICATION_JSON_VALUE);

		mvcResult = mockMvc.perform(builder).andReturn();

		mockMvc.perform(builder).andExpect(status().isOk()).andExpect(jsonPath("$.firstName", is("bob")))
				.andExpect(jsonPath("$.lastName", is("builder"))).andExpect(jsonPath("$.mobile", is("9134667890")))
				.andExpect(jsonPath("$.gmail", is("bob@gmail.com")));
	}

	@Test
	public void insertStudentTest() throws Exception {

		Set<Project> projects = new HashSet<>();
		Project project1 = new Project(1, "irctc", 17);
		Project project2 = new Project(2, "MakeMyTrip", 11);
		projects.add(project1);
		projects.add(project2);
		student = new Student(1, "bob", "builder", "9134667890", "bob@gmail.com", projects);

		Mockito.when(studentServiceImpl.insertStudent(student)).thenReturn(student);
		String json = mapper.writeValueAsString(student);

		mockMvc.perform(post("/student/insert").contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", is("bob"))).andExpect(jsonPath("$.lastName", is("builder")));
	}

}
