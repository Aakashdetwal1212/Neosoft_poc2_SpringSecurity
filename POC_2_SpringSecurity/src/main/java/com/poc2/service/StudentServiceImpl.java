package com.poc2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poc2.entity.Project;
import com.poc2.entity.Student;
import com.poc2.exception.ResourceNotFoundException;
import com.poc2.repository.ProjectRepository;
import com.poc2.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ProjectRepository projectRepository;

	@Override
	@Transactional(readOnly = false)
	public Student insertStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(int id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("student id:" + id + " does not found"));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Student updateStudent(int id, Student student) {
		Student studentDb = studentRepository.findById(id).get();
		studentDb.setFirstName(student.getFirstName());
		studentDb.setLastName(student.getLastName());
		studentDb.setMobile(student.getMobile());
		studentDb.setEmail(student.getEmail());
		studentDb.setProjects(student.getProjects());
		return studentRepository.save(studentDb);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteStudentById(int studentId) {
		studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("student id:" + studentId + " does not found"));
		studentRepository.deleteById(studentId);
	}

	@Override
	@Transactional(readOnly = false)
	public Project insertProject(int studentId, Project project) {
		return studentRepository.findById(studentId).map(student -> {
			project.setStudent(student);
			return projectRepository.save(project);
		}).orElseThrow(() -> new ResourceNotFoundException("student id:" + studentId + " does not found"));
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteProjectByNo(int projectNo) {
		projectRepository.deleteById(projectNo);
	}

	@Override
	public Project getProject(int projectNo) {
		return projectRepository.findById(projectNo)
				.orElseThrow(() -> new ResourceNotFoundException("project No:" + projectNo + " does not found"));
	}
}
