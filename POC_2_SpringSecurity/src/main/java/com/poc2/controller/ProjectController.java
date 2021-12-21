package com.poc2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc2.service.StudentService;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	StudentService studentService;
	
	@DeleteMapping(value = "/delete/{projectNo}/admin/{studentId}")
	public ResponseEntity<HttpStatus> deleteProject(@PathVariable("studentId") int studentId, @PathVariable("projectNo") int projectNo) {
		studentService.deleteProjectByNo(studentId, projectNo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
