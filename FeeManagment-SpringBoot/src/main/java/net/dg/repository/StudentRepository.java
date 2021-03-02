package net.dg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.dg.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>,
	CrudRepository<Student, Integer>{

	@Query(value = "SELECT * FROM student u WHERE u.due>=1",
			nativeQuery = true)
	public List<Student> dueFee();
}
