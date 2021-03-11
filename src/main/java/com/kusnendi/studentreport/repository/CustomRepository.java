package com.kusnendi.studentreport.repository;

import java.util.List;

import com.kusnendi.studentreport.pojo.custom.SubjectReport;
import com.kusnendi.studentreport.pojo.custom.SubjectReportSummary;

public interface CustomRepository {

	List<SubjectReport> getFailedSubject(String name, String kelas);
	List<SubjectReportSummary> getReportSummary(String name);
}
