package com.community.safetyalert.repository;

import com.community.safetyalert.model.IncidentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentReportRepo extends JpaRepository<IncidentReport,Long> {
}

