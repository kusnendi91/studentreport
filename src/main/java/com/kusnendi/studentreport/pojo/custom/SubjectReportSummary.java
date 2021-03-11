package com.kusnendi.studentreport.pojo.custom;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SqlResultSetMapping(name = "SubjectReportSummary", entities = {
		@EntityResult(entityClass = SubjectReportSummary.class, fields = {
				@FieldResult(name = "id",column = "id"),
				@FieldResult(name = "name",column = "nama"),
				@FieldResult(name = "kelas",column = "kelas"),
				@FieldResult(name = "subjectName",column = "subject_name"),
				@FieldResult(name = "score",column = "nilai")
				
		}) })

@Entity
public class SubjectReportSummary {

	@Id
	@JsonIgnore
	private Long id;
	private String name;
	private String kelas;
	private String subjectName;
	private Integer score;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKelas() {
		return kelas;
	}
	public void setKelas(String kelas) {
		this.kelas = kelas;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	
	
}
