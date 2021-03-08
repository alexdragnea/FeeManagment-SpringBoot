package net.dg.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.dg.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import net.dg.model.Accountant;
import net.dg.repository.AccountatRepository;

@Service
public class AccountantServiceImpl implements AccountantService {

	@Autowired
	private AccountatRepository accountantRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public AccountantServiceImpl(AccountatRepository accountantRepository) {
		super();
		this.accountantRepository = accountantRepository;
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Accountant accountant = accountantRepository.findByEmail(username);
		if(accountant == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(accountant.getEmail(), accountant.getPassword(), mapRolesToAuthorities(accountant.getRoles()));	
	}
	
	public Accountant saveAccountant(Accountant accountantDTO) {
		Accountant accountant = new Accountant(accountantDTO.getFirstname(), 
				accountantDTO.getLastname(), accountantDTO.getEmail(),
				passwordEncoder.encode(accountantDTO.getPassword()), accountantDTO.getAddress(), accountantDTO.getContact(), Arrays.asList(new Role("ROLE_USER")));
		
		return accountantRepository.save(accountant);
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public List<Accountant> getAllAccountants() {
		
		return accountantRepository.findAll();
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
	
	

}
