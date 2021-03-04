package net.dg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.dg.model.Accountant;
import net.dg.model.Student;
import net.dg.repository.AccountatRepository;

@Service
public class AccountantServiceImpl implements AccountantService {

	@Autowired
	private AccountatRepository accountantRepository;
	
	
	@Override
	public List<Accountant> getAllAccountants() {
		
		return accountantRepository.findAll();
	}

	@Override
	public void saveAccountant(Accountant accountant) {
		
		this.accountantRepository.save(accountant);
		
	}

	@Override
	public Accountant getAccountantById(int id) {
		
		Optional<Accountant> optional = accountantRepository.findById(id);
		Accountant accountant = null;
		if(optional.isPresent()) {
			accountant = optional.get();
		} 
		else {
			 throw new RuntimeException(" Accountant with id: " + id + " not found");
		}
		return accountant;
	}

	@Override
	public void deleteAccountantById(int id) {
		this.accountantRepository.deleteById(id);

	}
	
	@Override
	public List<Accountant> findByKeyboard(String keyboard) {
		// TODO Auto-generated method stub
		return accountantRepository.findByKeyword(keyboard);
	}

	@Override
	public String hashPassword(String password_plaintext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkPassword(String password_plaintext, String stored_hash) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
