package net.dg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
		model.addAttribute("students", studentService.getAllStudents());
		return "student";
	}
}
