package net.dg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import net.dg.model.Accountant;

public interface AccountatRepository extends JpaRepository<Accountant, Integer>,
	CrudRepository<Accountant, Integer>{

}
