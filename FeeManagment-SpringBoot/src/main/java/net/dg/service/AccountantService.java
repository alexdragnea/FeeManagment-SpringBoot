package net.dg.service;
import java.util.List;

import net.dg.model.Accountant;

public interface AccountantService {
	List<Accountant> getAllAccountants();
	void saveAccountant(Accountant accountant);
	void deleteAccountantById(int id);
	Accountant getAccountantById(int id);
	
}
