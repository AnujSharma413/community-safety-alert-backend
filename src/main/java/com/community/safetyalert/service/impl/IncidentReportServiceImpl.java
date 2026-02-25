package com.community.safetyalert.service.impl;

import com.community.safetyalert.dto.incident.IncidentRequestDTO;
import com.community.safetyalert.dto.incident.IncidentResponseDTO;
import com.community.safetyalert.model.IncidentReport;
import com.community.safetyalert.model.ReportStatus;
import com.community.safetyalert.model.User;
import com.community.safetyalert.repository.IncidentReportRepo;
import com.community.safetyalert.repository.UserRepo;
import com.community.safetyalert.service.IncidentReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncidentReportServiceImpl implements IncidentReportService {

    private final IncidentReportRepo reportRepo;
    private final UserRepo userRepo;

    public IncidentReportServiceImpl(IncidentReportRepo reportRepo, UserRepo userRepo){
        this.reportRepo = reportRepo;
        this.userRepo = userRepo;
    }

    @Override
    public IncidentResponseDTO createReport(IncidentRequestDTO dto, Long userId){

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        IncidentReport report = new IncidentReport();
        report.setTitle(dto.getTitle());
        report.setDescription(dto.getDescription());
        report.setCategory(dto.getCategory());
        report.setLatitude(dto.getLatitude());
        report.setLongitude(dto.getLongitude());
        report.setLocationName(dto.getLocationName());
        report.setMediaUrl(dto.getMediaUrl());

        report.setReportedBy(user);
        report.setStatus(ReportStatus.PENDING);
        report.setReportedAt(LocalDateTime.now());

        IncidentReport saved = reportRepo.save(report);

        return new IncidentResponseDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getCategory(),
                saved.getLocationName(),
                saved.getStatus().name(),
                saved.getReportedAt(),
                saved.getMediaUrl(),
                saved.getReportedBy().getName()
        );
    }

    @Override
    public List<IncidentResponseDTO> getAllReports(){
        return reportRepo.findAll()
                .stream()
                .map(report -> new IncidentResponseDTO(
                        report.getId(),
                        report.getTitle(),
                        report.getDescription(),
                        report.getCategory(),
                        report.getLocationName(),
                        report.getStatus().name(),
                        report.getReportedAt(),
                        report.getMediaUrl(),
                        report.getReportedBy().getName()
                ))
                .toList();
    }

    @Override
    public  List<IncidentResponseDTO> getReportsByUser(Long userId){
        return reportRepo.findAll()
                .stream()
                .filter(r -> r.getReportedBy().getId().equals(userId))
                .map(report -> new IncidentResponseDTO(
                        report.getId(),
                        report.getTitle(),
                        report.getDescription(),
                        report.getCategory(),
                        report.getLocationName(),
                        report.getStatus().name(),
                        report.getReportedAt(),
                        report.getMediaUrl(),
                        report.getReportedBy().getName()
                ))
                .toList();
    }

    @Override
    public IncidentResponseDTO updateStatus(Long reportId, String status){

        IncidentReport report = reportRepo.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        ReportStatus newStatus;

        try {
            newStatus = ReportStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status value");
        }

        report.setStatus(newStatus);

        IncidentReport updated = reportRepo.save(report);

        return new IncidentResponseDTO(
                updated.getId(),
                updated.getTitle(),
                updated.getDescription(),
                updated.getCategory(),
                updated.getLocationName(),
                updated.getStatus().name(),
                updated.getReportedAt(),
                updated.getMediaUrl(),
                updated.getReportedBy().getName()
        );
    }
}
