package com.kusnendi.studentreport.pojo;


public class StudentPojo extends BasePojo{

	private Long id;
	private String nama;
	private String tglLahir;
	private String namaAyah;
	private String namaIbu;
	private String alamatRumah;
	private String alamatEmail;
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getTglLahir() {
		return tglLahir;
	}
	public void setTglLahir(String tglLahir) {
		this.tglLahir = tglLahir;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
