package com.salesken.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salesken.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

	public Student findByEmail(String email);
	
	@Query("from Student")
	public List<Student> findAllStudent();
}
