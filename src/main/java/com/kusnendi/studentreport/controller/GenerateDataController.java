package com.kusnendi.studentreport.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusnendi.studentreport.model.Scores;
import com.kusnendi.studentreport.model.Student;
import com.kusnendi.studentreport.model.Subject;
import com.kusnendi.studentreport.model.User;
import com.kusnendi.studentreport.pojo.ResponsePojo;
import com.kusnendi.studentreport.repository.ScoresRepository;
import com.kusnendi.studentreport.repository.StudentRepository;
import com.kusnendi.studentreport.repository.SubjectRepository;
import com.kusnendi.studentreport.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class GenerateDataController {

	@Autowired
	ScoresRepository scoreRepo;
	
	@Autowired
	SubjectRepository subjectRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	UserRepository userRepo;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	@GetMapping("/generate")
	public ResponsePojo generateData() throws ParseException {
		
		User userx = userRepo.findByUsername("admin");
		if(userx != null) {
			return new ResponsePojo("500","data already generated");
		}
		
		//generate user with username admin and password admin
		User user = new User();
		user.setUsername("admin");
		user.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt()));
		userRepo.save(user);
		
		//generate subject list
		String[] subjectArr = {"IPA","IPS","Matematika","Bahsa Indonesia","Bahasa Inggris","Agama","Komputer"};
		for(String subjectStr : subjectArr) {
			Subject subject = new Subject();
			subject.setSubjectName(subjectStr);
			subject.setEnabled(true);
			subjectRepo.save(subject);
		}
		
		//generate student list
		int counter = 1;
		Date startDate  = sdf.parse("01-02-1993");
		Date endDate = sdf.parse("04-02-1993");
		LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		for(LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
			Student student = new Student();
			student.setNama("Murid "+counter);
			student.setNamaAyah("Udin "+counter);
			student.setNamaIbu("Umi "+counter);
			student.setAlamatEmail("murid"+counter+"@test.com");
			student.setAlamatRumah("Jl Sudirman "+counter);
			Date tglLahir = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			student.setTglLahir(tglLahir);
			studentRepo.save(student);
			counter++;
		}
		
		// generate random score
		List<Student> studentList = studentRepo.findAll();
		List<Subject> subjectList = subjectRepo.findAll();

		for (Student stud : studentList) {
			for (Subject sub : subjectList) {
				for (int kelas = 1; kelas < 7; kelas++) {
					for (int semester = 1; semester < 3; semester++) {
						Scores score = new Scores();
						score.setStudent(stud);
						score.setSubject(sub);
						score.setKelas(String.valueOf(kelas));
						score.setSemester("Semester " + semester);
						score.setScore(random(10, 100));
						scoreRepo.save(score);
					}
				}
			}
		}
		return new ResponsePojo("200","OK");
	}
	
	public int random(int start, int end) {
		return new Random().ints(start,end).findFirst().getAsInt();
	}
}
