package com.kusnendi.studentreport.pojo.custom;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SqlResultSetMapping(name = "SubjectReport", entities = {
		@EntityResult(entityClass = SubjectReport.class, fields = {
				@FieldResult(name = "id",column = "id"),
				@FieldResult(name = "subjectName",column = "subject_name"),
				@FieldResult(name = "score",column = "score")
				
		}) })

@Entity
public class SubjectReport {
	
	@Id
	@JsonIgnore
	private Long id;
	private String subjectName;
	private Integer score;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
