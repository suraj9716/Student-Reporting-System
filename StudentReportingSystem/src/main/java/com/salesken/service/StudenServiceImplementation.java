package com.salesken.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesken.exception.StudentException;
import com.salesken.model.Semester;
import com.salesken.model.Student;
import com.salesken.repository.SemesterRepo;
import com.salesken.repository.StudentRepo;

@Service
public class StudenServiceImplementation implements StudentService{
	
	@Autowired
	private StudentRepo srepo;
	
	@Autowired
	private SemesterRepo semrepo;

	@Override
	public Student addStudentWithMarksAndSem(Student student) throws StudentException {

        Student stud =   srepo.findByEmail(student.getEmail());
        
        if(stud!=null) {
        	 throw new StudentException("student already present");
        }
        else {
          student.setSemester(student.getSemester());
//         Semester sem =  student.getSemester().get(0);
         int i = 0;
         Semester sem = null;
         for(Semester k : student.getSemester()) {
        	
        	 sem =  student.getSemester().get(i);
        	 i++;
        	 sem.setStudent(student);
         }
        
//         semrepo.save(sem);
        	return srepo.save(student);
        }
	}

	@Override
	public Student getReport(String email) throws StudentException {

	   Student stud =  srepo.findByEmail(email);
	
	   if(stud==null) {
		   throw new StudentException("No student found with this email Id " +  email);
	   }
		 return stud;
	}

	@Override
	public double AvgPerOfClass(Integer id) throws StudentException {
		
		
		 List<Semester> list =  semrepo.findAllStudent(id);
		 
		 if(list.isEmpty()) {
			 throw new StudentException("No student present in this semester");
		 }
		 
		 int marks = 0;
		
		 for(Semester k : list) {
			 
			marks+= k.getEnglish() + k.getMaths() + k.getScience();
			
		 }
		 double avgMarks = (marks / (list.size()*3));
		 
		return avgMarks;
	}

	@Override
	public double AvgPerSubOfStudents(String subject) throws StudentException {
		 
		  List<Student> list = srepo.findAll();
		  int avgEng=0;
		  int avgMath=0;
		  int avgSci=0;
		  int count = 0;
		  
		  if(list.isEmpty()) {
			  throw new StudentException("No student present in this subject");
		  }
		  
		  for(Student s : list) {
			 List<Semester> sl = s.getSemester();
			 count = sl.size();
			 for(Semester k : sl) {
				avgEng+=k.getEnglish();
				avgMath+=k.getMaths();
				avgSci+=k.getScience();
			 }  
			  
		  }
		  if(subject.equalsIgnoreCase("english")){
			  return avgEng/ (list.size()+ count);
		  }
		  else if(subject.equalsIgnoreCase("maths")) {
			  
			 return avgMath/(list.size()+ count);
		  }
		  else if(subject.equalsIgnoreCase("science")) {

			  
			  return avgMath/(list.size()+ count);
		  }
		  else {
			  throw new StudentException("please enter valid subject");
		  }
		  
	}

	@Override
	public List<Student> top2ConstitentStudent() {
		List<Student> slist = srepo.findAll();
		
		int max = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;
		String s1 = null;
		String s2 = null;
		List<Student> maxList = new ArrayList<>();
		
		for(Student k : slist) {
			int marks = 0;
			List<Semester> semlist = k.getSemester();
			for(Semester sem : semlist) {
				marks += sem.getEnglish()+ sem.getMaths()+ sem.getScience();	
			}
			if(marks>max) {
				
				max = marks;
				s1 = k.getEmail();
			}
		}
		for(Student k : slist) {
			int marks2 = 0;
			
			List<Semester> semlist = k.getSemester();
			for(Semester sem : semlist) {
					marks2 += sem.getEnglish()+ sem.getMaths()+ sem.getScience();
			}
	
			if(marks2>max2 && marks2<max) {
				
				max2 = marks2;
				s2 = k.getEmail();
			}
		}
		
	    maxList.add(srepo.findByEmail(s1));
	    maxList.add(srepo.findByEmail(s2));
		return maxList;

	}

	
	


}
