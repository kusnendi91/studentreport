package com.kusnendi.studentreport.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="subject")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="subject_id")
	private Long id;
	
	private String subjectName;
	private Boolean enabled;
	
	@OneToMany(mappedBy = "subject")
	@JsonIgnore
	private List<Scores> scores;
	
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
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public List<Scores> getScores() {
		return scores;
	}
	public void setScores(List<Scores> scores) {
		this.scores = scores;
	}
	
	
	

}
