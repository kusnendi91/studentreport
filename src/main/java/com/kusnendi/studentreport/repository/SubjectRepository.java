package com.kusnendi.studentreport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kusnendi.studentreport.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{
	
	@Query(value = "select subject_id,subject_name,enabled from subject where enabled = 1", nativeQuery= true)
	public List<Subject> findEnabledSubject();
	
	@Query(value="select subject_id from subject limit 1",nativeQuery=true)
	public Long getFirstEntryId();

}
