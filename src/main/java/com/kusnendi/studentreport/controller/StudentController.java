package com.kusnendi.studentreport.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusnendi.studentreport.exception.DataNotFoundException;
import com.kusnendi.studentreport.exception.UnauthorizedException;
import com.kusnendi.studentreport.model.Student;
import com.kusnendi.studentreport.model.User;
import com.kusnendi.studentreport.pojo.BasePojo;
import com.kusnendi.studentreport.pojo.RequestIdPojo;
import com.kusnendi.studentreport.pojo.RequestNamePojo;
import com.kusnendi.studentreport.pojo.ResponsePojo;
import com.kusnendi.studentreport.pojo.StudentPojo;
import com.kusnendi.studentreport.repository.StudentRepository;
import com.kusnendi.studentreport.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	UserRepository userRepo;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	
	@PostMapping("/student/all")
	public List<Student> getAllStudent(@RequestBody BasePojo req){
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			return studentRepo.findAll();
		}

	}
	
	@PostMapping("/student/getbyid")
	public Student getStudentById(@RequestBody RequestIdPojo req) {
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			return studentRepo.findById(req.getId()).orElseThrow(()-> new DataNotFoundException(String.valueOf(req.getId()), "Student"));
		}
		
	}
	
	@PostMapping("/student/getbyname")
	public List<Student> getStudentByNamaLike(@RequestBody RequestNamePojo req){
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			return studentRepo.findByNamaContaining(req.getName());
		}

	}
	
	@PostMapping("/student/new")
	public ResponsePojo newStudent(@RequestBody StudentPojo req) throws ParseException {
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			sdf.setLenient(false);
			Student stud = new Student();
			stud.setNama(req.getNama());
			stud.setNamaAyah(req.getNamaAyah());
			stud.setNamaIbu(req.getNamaIbu());
			stud.setAlamatRumah(req.getAlamatRumah());
			stud.setAlamatEmail(req.getAlamatEmail());
			stud.setTglLahir(sdf.parse(req.getTglLahir()));
			studentRepo.save(stud);
			return new ResponsePojo("200","OK");

		}
		
	}
	
	@PostMapping("/student/update")
	public ResponsePojo updateStudent(@RequestBody StudentPojo req) throws ParseException {
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			Student stud = studentRepo.findById(req.getId()).orElseThrow(()-> new DataNotFoundException(String.valueOf(req.getId()), "Student"));
			stud.setAlamatEmail(req.getAlamatEmail());
			stud.setAlamatRumah(req.getAlamatRumah());
			stud.setNama(req.getNama());
			stud.setNamaAyah(req.getNamaAyah());
			stud.setNamaIbu(req.getNamaIbu());
			stud.setTglLahir(sdf.parse(req.getTglLahir()));
			studentRepo.save(stud);
			return new ResponsePojo("200","OK");
		}

	}
	
	@PostMapping("/student/delete")
	public ResponsePojo deleteStudent(@RequestBody RequestIdPojo req){
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			Student stud = studentRepo.findById(req.getId()).orElseThrow(()-> new DataNotFoundException(String.valueOf(req.getId()), "Student"));
			studentRepo.delete(stud);
			return new ResponsePojo("200","OK");
		}

	}
	

}
