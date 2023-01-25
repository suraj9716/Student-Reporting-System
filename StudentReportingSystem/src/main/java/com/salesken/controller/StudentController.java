package com.salesken.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salesken.exception.StudentException;
import com.salesken.model.Semester;
import com.salesken.model.Student;
import com.salesken.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService sservice;
	
	
	@PostMapping("/student")
	public ResponseEntity<Student> addStudentHandler(@RequestBody Student student) throws StudentException{
		
		return new ResponseEntity<Student>(sservice.addStudentWithMarksAndSem(student), HttpStatus.OK);
	}
	
	@GetMapping("/studentReport/{email}")
    public ResponseEntity<Student> seeStudentReportHandler(@RequestParam String email) throws StudentException{
		
		return new ResponseEntity<Student>(sservice.getReport(email), HttpStatus.OK);
	}
	
	 @GetMapping("/student/{enterSemester}")
	 public ResponseEntity<Double> seeAvgPercentWholeClass(@RequestParam Integer id) throws StudentException{
			
			return new ResponseEntity<Double>(sservice.AvgPerOfClass(id), HttpStatus.OK);
	}
	 
	 @GetMapping("/students/{enterSubject}")
	 public ResponseEntity<Double> seeAvgPerOfSubject(@RequestParam String subject) throws StudentException{
		 
		 return new ResponseEntity<Double>(sservice.AvgPerSubOfStudents(subject), HttpStatus.OK);
			
	}
	
	 @GetMapping("/top2ConstitentStudent")
	 public ResponseEntity<List<Student>> top2ConstitentStudent(){
		 return new ResponseEntity<List<Student>>(sservice.top2ConstitentStudent(), HttpStatus.OK);
			
	}
	 
	
	
}
