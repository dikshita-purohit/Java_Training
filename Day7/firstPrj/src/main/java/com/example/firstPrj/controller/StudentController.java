package com.example.firstPrj.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.example.firstPrj.repositorys.StudentRepositry;
import com.example.firstPrj.student.Students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@RequestMapping("/students")
public class StudentController {
@Autowired
	StudentRepositry st;
	List<Students> studentList = new ArrayList<>(); 
	
	@GetMapping()
	public List<Students> getStudents(@RequestParam(required = false) String name, @RequestHeader(name="accName", required=false) String accName)
	{
//		System.out.println(name);
//		System.out.println(accName);
//		System.out.println(id);
		//return studentList;
		return st.findAll();
		}
	
	
	@GetMapping("/{Id}")
	public Optional<Students> getStudentsByID(@RequestParam(required = false) String name, @RequestHeader(name="accName",required = false) String accName, @PathVariable("Id") int rollNo)
	{
//		System.out.println(name);
//		System.out.println(accName);
//		System.out.println(rollNo);
		
//		for(Students s: studentList) {
//			if(s.getRollNo()==rollNo) {
//				return s;
//			}
//		}
//		return null;
		
		return st.findById((long) rollNo);
	}
	
	
	@PostMapping
	public List<Students> addStudents(@RequestBody Students s)
	{
		
//		System.out.println(s.getName());
//		System.out.println(s.getRollNo());
//		studentList.add(s);
//		return studentList;
		
		st.save(s);
		return st.findAll();
		
	}
	
	
	@PutMapping("/{Id}")
	public List<Students> updateStudents(@PathVariable("Id") Long rollNo, @RequestBody Students student){
		
//		for(Students s:studentList) {
//			if(s.getRollNo() == rollNo) {
//				s.setName(student.getName());
//				break;
//			}
//		}
//		return studentList;
		
		Optional<Students> existingStudentOpt = st.findById(rollNo);
        if (existingStudentOpt.isPresent()) {
            Students existing = existingStudentOpt.get();
            existing.setName(student.getName());
            existing.setRollNo(student.getRollNo());
            st.save(existing);
        }
        return st.findAll();
		
	}
	
	
	@DeleteMapping("/{id}")
	public List<Students> deleteStudent(@PathVariable("id") Long rollNo) {
//	    studentList.removeIf(s -> s.getRollNo() == rollNo);
//	    
//	    return studentList;
		
		st.deleteById(rollNo);
		return st.findAll();
	}

	
	
	
	
}
