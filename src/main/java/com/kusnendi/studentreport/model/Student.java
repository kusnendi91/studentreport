package com.kusnendi.studentreport.model;

import javax.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "student")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "modifiedAt"}, 
allowGetters = true)
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="student_id")
	private Long id;
	private String nama;
	private Date tglLahir;
	private String namaAyah;
	private String namaIbu;
	private String alamatRumah;
	private String alamatEmail;
	@OneToMany(mappedBy = "student")
	private List<Scores> scores;
	
	@Column(nullable=false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date modifiedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getNamaAyah() {
		return namaAyah;
	}

	public void setNamaAyah(String namaAyah) {
		this.namaAyah = namaAyah;
	}

	public String getNamaIbu() {
		return namaIbu;
	}

	public void setNamaIbu(String namaIbu) {
		this.namaIbu = namaIbu;
	}

	public String getAlamatRumah() {
		return alamatRumah;
	}

	public void setAlamatRumah(String alamatRumah) {
		this.alamatRumah = alamatRumah;
	}

	public String getAlamatEmail() {
		return alamatEmail;
	}

	public void setAlamatEmail(String alamatEmail) {
		this.alamatEmail = alamatEmail;
	}

	public List<Scores> getScores() {
		return scores;
	}

	public void setScores(List<Scores> scores) {
		this.scores = scores;
	}

	public Date getTglLahir() {
		return tglLahir;
	}

	public void setTglLahir(Date tglLahir) {
		this.tglLahir = tglLahir;
	}
	
	
	
	
	
	
	
	
	
	
}
