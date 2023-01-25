package com.salesken.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salesken.model.Semester;
import com.salesken.model.Student;

@Repository
public interface SemesterRepo extends JpaRepository<Semester, Integer>{

	
	public List<Semester> findByStudent(Integer id);
	
	@Query("from Semester where semester = :id")
	public List<Semester> findAllStudent(@Param("id") Integer id);
	
	@Query("from Semester")
	public List<Semester> avgMarksOfStudentsInSubject(String subject);
}
