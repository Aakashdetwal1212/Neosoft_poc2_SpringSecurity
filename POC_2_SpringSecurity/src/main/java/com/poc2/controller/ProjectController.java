package com.poc2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc2.entity.Project;
import com.poc2.service.StudentService;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	StudentService studentService;
	
	@GetMapping(value = "/get/{projectNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Project> getProject(@PathVariable("projectNo") int projectNo) {
		Project project = studentService.getProject(projectNo);
		return new ResponseEntity<>(project,HttpStatus.OK);
	}
	
	@PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Project> insertProject(@Valid @RequestBody Project project) {
		Project project2 = studentService.insertProject(project);
		return new ResponseEntity<>(project2,HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/delete/{projectNo}")
	public ResponseEntity<HttpStatus> deleteProject(@PathVariable("projectNo") int projectNo) {
		studentService.deleteProjectByNo(projectNo);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
