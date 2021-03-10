package net.dg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.dg.model.Accountant;
import net.dg.repository.AccountatRepository;
import net.dg.service.AccountantService;


@Controller
@RequestMapping
public class AccountantController {

	@Autowired
	private AccountantService accountantService;

	@Autowired
	private AccountatRepository accountantRepository;

	@GetMapping("/accountants")
	private String viewHomePage(Model model, String keyword) {

		if(keyword != null) {
			model.addAttribute("Accountantlist", accountantService.findByKeyboard(keyword));
		}
		else {
			model.addAttribute("Accountantlist", accountantService.getAllAccountants());
		}

		return "accountant";
	}

	@GetMapping("/showNewAccountantForm")
	private String showNewAccountantForm(Model model) {

		Accountant accountant = new Accountant();
		model.addAttribute("accountant", accountant);

		return "new_accountant";
	}

	@PostMapping("/saveAccountant")
	public String saveAccountant(@ModelAttribute("accountant") Accountant accountant) {
		accountantService.saveAccountant(accountant);
		return "redirect:/accountants";
	}

	@GetMapping("deleteAccountant/{id}")
	public String deleteStudent(@PathVariable (value = "id") int id) {
		this.accountantService.deleteAccountantById(id);
		return "redirect:/accountants";
	}

	@GetMapping("/showFormForUpdateAccountant/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
		Accountant accountant = accountantService.getAccountantById(id);
		model.addAttribute("accountant", accountant);
		return "update_accountant";
	}
	
	@GetMapping("/")
	public String viewLoginPages() {
		return "user_login";
	}

}
