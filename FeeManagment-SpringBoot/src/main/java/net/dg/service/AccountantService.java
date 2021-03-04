package net.dg.service;
import java.util.List;

import net.dg.model.Accountant;

public interface AccountantService {
	
	List<Accountant> getAllAccountants();
	List<Accountant> findByKeyboard(String keyboard);
	void saveAccountant(Accountant accountant);
	Accountant getAccountantById(int id);
	void deleteAccountantById(int id);
	
	String hashPassword(String password_plaintext);
	boolean checkPassword(String password_plaintext, String stored_hash);
}
