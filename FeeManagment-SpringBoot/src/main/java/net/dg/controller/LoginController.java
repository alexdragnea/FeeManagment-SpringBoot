package net.dg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class LoginController {

	@RequestMapping("/admin_login")
	public String AdminLogin() {
		return "adminlogin";
	}

	@RequestMapping("/user_login")
	public String UserLogin() {
		return "userlogin";
	}

	@GetMapping("/403")
	public String Error403() {
		return "error403";
	}

}
