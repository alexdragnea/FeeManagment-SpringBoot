package net.dg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.dg.model.Student;
import net.dg.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public void saveStudent(Student student) {
		this.studentRepository.save(student);
		
	}

	@Override
	public Student getStudentByRollNo(int id) {
		Optional<Student> optional = studentRepository.findById(id);
		Student student = null;
		if(optional.isPresent()) {
			student = optional.get();
		}
		else {
			throw new RuntimeException(" Student with id: " + id + " not found");
		}
		return student;
	}

	@Override
	public void deleteStudentByRollNo(int id) {
		this.studentRepository.deleteById(id);
		
	}

	@Override
	public List<Student> getDueFeeStudents() {
		
		return studentRepository.dueFee();
	}
	
	public List<Student> listSearch(){
			
		return studentRepository.search();
	
	}

}
