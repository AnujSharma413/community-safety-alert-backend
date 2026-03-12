package com.community.safetyalert.controller;

import com.community.safetyalert.dto.incident.IncidentRequestDTO;
import com.community.safetyalert.dto.incident.IncidentResponseDTO;
import com.community.safetyalert.service.IncidentReportService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class IncidentReportController {

    private final IncidentReportService incidentReportService;

    public IncidentReportController(IncidentReportService incidentReportService){
        this.incidentReportService = incidentReportService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/reports")
    public IncidentResponseDTO createReport(
            @Valid @RequestBody IncidentRequestDTO dto,
            @PathVariable Long userId){

        return incidentReportService.createReport(dto, userId);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/reports")
    public List<IncidentResponseDTO> getAllReports(){
        return incidentReportService.getAllReports();
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/user/{userId}")
    public List<IncidentResponseDTO> getReportsByUser(@PathVariable Long userId){
        return incidentReportService.getReportsByUser(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/reports/{id}/status")
    public IncidentResponseDTO updateStatus(
            @PathVariable Long reportId,
            @RequestParam String status){

        return incidentReportService.updateStatus(reportId, status);
    }
}