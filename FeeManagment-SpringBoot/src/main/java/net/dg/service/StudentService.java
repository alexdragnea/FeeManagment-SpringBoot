package net.dg.service;

import java.util.List;

import net.dg.model.Student;

public interface StudentService {
	List<Student> getAllStudents();
	void saveStudent(Student student);
	Student getStudentById(int id);
	void deleteStudentById(int id);
	
} 
