package net.dg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.dg.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>,
	CrudRepository<Student, Integer>{

	@Query(value = "SELECT * FROM student s WHERE s.due>=1",
			nativeQuery = true)
	public List<Student> dueFee();
	
	@Query(value = "SELECT * FROM student s WHERE s.name like %:keyword% "
			+ "or s.email like %:keyword% "
			+ "or s.course like %:keyword% "
			+ "or s.address like %:keyword% "
			+ "or s.contact like %:keyword% ",
			nativeQuery = true)
	List<Student> findByKeyword(@Param("keyword") String keyword);
	
}
