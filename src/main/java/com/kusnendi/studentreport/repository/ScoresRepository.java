package com.kusnendi.studentreport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kusnendi.studentreport.model.Scores;

@Repository
public interface ScoresRepository extends JpaRepository<Scores, Long>{

}
