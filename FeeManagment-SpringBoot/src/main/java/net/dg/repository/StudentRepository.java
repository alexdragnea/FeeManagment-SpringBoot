package net.dg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import net.dg.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>,
	CrudRepository<Student, Integer>{

}
