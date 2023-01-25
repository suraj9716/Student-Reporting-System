package com.salesken.service;

import java.util.List;

import com.salesken.exception.StudentException;
import com.salesken.model.Semester;
import com.salesken.model.Student;

public interface StudentService {

	public Student addStudentWithMarksAndSem(Student student) throws StudentException;
	public Student getReport(String email) throws StudentException;
	public double AvgPerOfClass(Integer id) throws StudentException;
	public double AvgPerSubOfStudents(String subject) throws StudentException;
	public List<Student> top2ConstitentStudent();
	
	
}
