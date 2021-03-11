package com.kusnendi.studentreport.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kusnendi.studentreport.pojo.custom.SubjectReport;
import com.kusnendi.studentreport.pojo.custom.SubjectReportSummary;
import com.kusnendi.studentreport.repository.CustomRepository;

@Service("customRepository")
public class CustomRepositoryImpl implements CustomRepository{

	@Autowired
	private EntityManager em;
	
	String sql = "select s.id,sb.subject_name,avg(s.score) as 'score' from scores s "
			+ "join student st on st.student_id = s.student_id "
			+ "join subject sb on sb.subject_id = s.subject_id "
			+ "where st.nama = :name and s.kelas = :kelas "
			+ "group by s.id, sb.subject_name "
			+ "having avg(s.score) < 50 "
			+ "order by s.id, sb.subject_name ";
	
	String sql1 = "select s.id, st.nama,s.kelas,sb.subject_name,avg(s.score) as 'nilai' from scores s "
			+ "join student st on st.student_id = s.student_id "
			+ "join subject sb on sb.subject_id = s.subject_id "
			+ "where st.nama = :name "
			+ "group by st.nama,s.kelas,sb.subject_name "
			+ "order by st.nama, s.kelas, sb.subject_name";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SubjectReport> getFailedSubject(String name, String kelas) {
		Query q = this.em.createNativeQuery(sql,"SubjectReport");
		q.setParameter("name", name);
		q.setParameter("kelas", kelas);
		List<SubjectReport> sub = (List<SubjectReport>) q.getResultList();
		em.close();
		return sub;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubjectReportSummary> getReportSummary(String name) {
		Query q = this.em.createNativeQuery(sql1,"SubjectReportSummary");
		q.setParameter("name", name);
		List<SubjectReportSummary> reportSummary = (List<SubjectReportSummary>) q.getResultList();
		em.close();
		return reportSummary;
	}

}
