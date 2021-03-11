package com.kusnendi.studentreport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kusnendi.studentreport.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	public List<Student> findByNamaContaining(String nama);
	
	@Query(value="select student_id from student limit 1",nativeQuery=true)
	public Long getFirstEntryId();
	

}
