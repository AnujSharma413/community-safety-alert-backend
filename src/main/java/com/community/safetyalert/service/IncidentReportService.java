package com.community.safetyalert.service;

import com.community.safetyalert.dto.incident.IncidentRequestDTO;
import com.community.safetyalert.dto.incident.IncidentResponseDTO;
import com.community.safetyalert.model.IncidentReport;

import java.util.List;

public interface IncidentReportService {

    IncidentResponseDTO createReport(IncidentRequestDTO dto, Long userId);

    List<IncidentResponseDTO> getAllReports();

    List<IncidentResponseDTO> getReportsByUser(Long userId);

    IncidentResponseDTO updateStatus(Long reportId, String status);
}
