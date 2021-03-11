package com.kusnendi.studentreport.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kusnendi.studentreport.exception.DataNotFoundException;
import com.kusnendi.studentreport.exception.UnauthorizedException;
import com.kusnendi.studentreport.model.Scores;
import com.kusnendi.studentreport.model.Student;
import com.kusnendi.studentreport.model.Subject;
import com.kusnendi.studentreport.model.User;
import com.kusnendi.studentreport.pojo.BasePojo;
import com.kusnendi.studentreport.pojo.RequestIdPojo;
import com.kusnendi.studentreport.pojo.ResponsePojo;
import com.kusnendi.studentreport.pojo.ScorePojo;
import com.kusnendi.studentreport.pojo.ScoreReportPojo;
import com.kusnendi.studentreport.pojo.SummaryPojo;
import com.kusnendi.studentreport.pojo.custom.SubjectReport;
import com.kusnendi.studentreport.pojo.custom.SubjectReportSummary;
import com.kusnendi.studentreport.repository.CustomRepository;
import com.kusnendi.studentreport.repository.ScoresRepository;
import com.kusnendi.studentreport.repository.StudentRepository;
import com.kusnendi.studentreport.repository.SubjectRepository;
import com.kusnendi.studentreport.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class ScoreController {
	
	@Autowired
	ScoresRepository scoreRepo;
	
	@Autowired
	SubjectRepository subjectRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CustomRepository customRepo;
	
	@PostMapping("/score/all")
	public List<Scores> getAllScores(@RequestBody BasePojo req){
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			return scoreRepo.findAll();
		}
	}
	
	@PostMapping("/score/getbyid")
	public Scores getScoresById(@RequestBody RequestIdPojo req) {
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			return scoreRepo.findById(req.getId()).orElseThrow(() -> new DataNotFoundException(String.valueOf(req.getId()), "Scores"));
		}
	}
	
	@PostMapping("/score/failedsubject")
	public List<SubjectReport> getFailedSubject(@RequestBody ScoreReportPojo req){
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			return customRepo.getFailedSubject(req.getName(), req.getKelas());
		}
	}
	
	@PostMapping("/score/summary")
	public List<SubjectReportSummary> getSummary(@RequestBody SummaryPojo req){
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			List<SubjectReportSummary> sumList = customRepo.getReportSummary(req.getName());
			return sumList;
		}
	}
	
	@PostMapping("/score/new")
	public ResponsePojo newScores(@RequestBody ScorePojo req) {
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			Scores sec = new Scores();
			Subject sub = subjectRepo.findById(req.getSubjectId()).orElseThrow(() -> new DataNotFoundException(String.valueOf(req.getSubjectId()), "Subject"));
			Student stud = studentRepo.findById(req.getStudentId()).orElseThrow(()-> new DataNotFoundException(String.valueOf(req.getStudentId()), "Student"));
			sec.setSubject(sub);
			sec.setStudent(stud);
			sec.setKelas(req.getKelas());
			sec.setSemester(req.getSemester());
			sec.setScore(req.getScore());
			scoreRepo.save(sec);
			return new ResponsePojo("200","OK");
		}
		

	}
	
	@PostMapping("/score/update")
	public ResponsePojo updateScores(@RequestBody ScorePojo req) {
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			Scores sec = scoreRepo.findById(req.getId()).orElseThrow(() -> new DataNotFoundException(String.valueOf(req.getId()), "Scores"));
			Subject sub = subjectRepo.findById(req.getSubjectId()).orElseThrow(() -> new DataNotFoundException(String.valueOf(req.getSubjectId()), "Subject"));
			Student stud = studentRepo.findById(req.getStudentId()).orElseThrow(()-> new DataNotFoundException(String.valueOf(req.getStudentId()), "Student"));
			sec.setSubject(sub);
			sec.setStudent(stud);
			sec.setKelas(req.getKelas());
			sec.setSemester(req.getSemester());
			sec.setScore(req.getScore());
			scoreRepo.save(sec);
			return new ResponsePojo("200","OK");
		}
		
	}
	
	@PostMapping("/score/delete")
	public ResponsePojo deleteScore(@RequestBody RequestIdPojo req){
		User user = userRepo.findByToken(req.getToken());
		if(user == null || req.getToken().isEmpty()) {
			throw new UnauthorizedException();
		}else {
			Scores sec = scoreRepo.findById(req.getId()).orElseThrow(() -> new DataNotFoundException(String.valueOf(req.getId()), "Scores"));
			scoreRepo.delete(sec);
			return new ResponsePojo("200","OK");

		}
		
		
	}
	

}
