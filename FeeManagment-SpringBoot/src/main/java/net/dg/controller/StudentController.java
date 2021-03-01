package net.dg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import net.dg.model.Student;
import net.dg.repository.StudentRepository;
import net.dg.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/students")
	public String viewHomePage(Model model) {
		model.addAttribute("Studentlist", studentService.getAllStudents());
		return "student";
	}
	
	@GetMapping("/showNewStudentForm")
	public String showNewStudentForm(Model model) {
		
		Student student = new Student();
		model.addAttribute("student", student);
		
		return "new_student";
	}
	
	@PostMapping("/saveStudent")
	public String saveEmployee(@ModelAttribute("student") Student student) {
		int due;
		due = student.getFee() - student.getPaid();
		student.setDue(due);
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("deleteStudent/{rollno}")
	public String deleteEmployee(@PathVariable (value = "rollno") int rollno) {
		  this.studentService.deleteStudentByRollNo(rollno);
		  return "redirect:/students";
	}
}
