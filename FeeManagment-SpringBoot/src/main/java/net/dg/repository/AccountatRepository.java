package net.dg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import net.dg.model.Accountant;


public interface AccountatRepository extends JpaRepository<Accountant, Integer>,
	CrudRepository<Accountant, Integer>{

	@Query(value = "SELECT * FROM accountant a WHERE a.first_name like %:keyword% "
			+ "or a.last_name like %:keyword% "
			+ "or a.email like %:keyword% ",
			nativeQuery = true)
	List<Accountant> findByKeyword(@Param("keyword") String keyword);
	
	Accountant findByEmail(String email);
}
