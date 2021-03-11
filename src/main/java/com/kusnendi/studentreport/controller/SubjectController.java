package com.kusnendi.studentreport.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusnendi.studentreport.exception.DataNotFoundException;
import com.kusnendi.studentreport.exception.UnauthorizedException;
import com.kusnendi.studentreport.model.Subject;
import com.kusnendi.studentreport.model.User;
import com.kusnendi.studentreport.pojo.BasePojo;
import com.kusnendi.studentreport.pojo.RequestIdPojo;
import com.kusnendi.studentreport.pojo.ResponsePojo;
import com.kusnendi.studentreport.pojo.SubjectPojo;
import com.kusnendi.studentreport.repository.SubjectRepository;
import com.kusnendi.studentreport.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class SubjectController {
	
	@Autowired
	SubjectRepository subjectRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@PostMapping("/subject/all")
	public List<Subject> getAllSubject(@RequestBody BasePojo req){
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			return subjectRepo.findAll();
		}
	}
	
	@PostMapping("/subject/enabled")
	public List<Subject> getAllEnabledSubject(@RequestBody BasePojo req){
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			return subjectRepo.findEnabledSubject();
		}
	}
	
	@PostMapping("/subject/getbyid")
	public Subject getSubjectById(@RequestBody RequestIdPojo req){
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			return subjectRepo.findById(req.getId()).orElseThrow(() -> new DataNotFoundException(String.valueOf(req.getId()), "Subject"));
		}
	}
	
	@PostMapping("/subject/new")
	public ResponsePojo newSubject(@RequestBody SubjectPojo req) {
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			Subject sub = new Subject();
			sub.setSubjectName(req.getSubjectName());
			sub.setEnabled(req.getEnabled());
			subjectRepo.save(sub);
			return new ResponsePojo("200","OK");

		}
	}
	
	@PostMapping("/subject/update")
	public ResponsePojo updateSubject(@RequestBody SubjectPojo req) {
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			Subject sub = subjectRepo.findById(req.getId()).orElseThrow(() -> new DataNotFoundException(String.valueOf(req.getId()), "Subject"));
			sub.setEnabled(req.getEnabled());
			sub.setSubjectName(req.getSubjectName());
			subjectRepo.save(sub);
			return new ResponsePojo("200","OK");
		}

	}
	
	@PostMapping("/subject/delete")
	public ResponsePojo deleteSubject(@RequestBody RequestIdPojo req){
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			Subject sub = subjectRepo.findById(req.getId()).orElseThrow(() -> new DataNotFoundException(String.valueOf(req.getId()), "Subject"));
			subjectRepo.delete(sub);
			return new ResponsePojo("200","OK");
		}

	}

}
