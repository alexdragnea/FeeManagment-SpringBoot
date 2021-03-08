package net.dg.service;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.dg.model.Accountant;

public interface AccountantService extends UserDetailsService {
	
	List<Accountant> getAllAccountants();
	List<Accountant> findByKeyboard(String keyboard);
	Accountant saveAccountant(Accountant accountant);
	Accountant getAccountantById(int id);
	void deleteAccountantById(int id);
	
}
