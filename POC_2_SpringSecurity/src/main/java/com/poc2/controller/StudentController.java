package com.poc2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc2.entity.JwtRequest;
import com.poc2.entity.JwtResponse;
import com.poc2.entity.Student;
import com.poc2.service.StudentService;
import com.poc2.service.UserService;
import com.poc2.utility.JWTUtility;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
	
	@Autowired
	StudentService studentService;

	Student student;

	@GetMapping(value = "/get/{id}/admin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
		student = studentService.getStudentById(id);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping(value = "/getall/admin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> listStudent = studentService.getAllStudent();
		return new ResponseEntity<>(listStudent, HttpStatus.OK);
	}

	@PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> insertStudent(@Valid @RequestBody Student student) {
		student = studentService.insertStudent(student);
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update/{id}/admin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int id) {
		student = studentService.updateStudent(id, student);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}/admin")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") int id) {
		studentService.deleteStudentById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JwtResponse(token);
    }
}
	
