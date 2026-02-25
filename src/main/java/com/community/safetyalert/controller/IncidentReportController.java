package com.community.safetyalert.controller;

import com.community.safetyalert.dto.incident.IncidentRequestDTO;
import com.community.safetyalert.dto.incident.IncidentResponseDTO;
import com.community.safetyalert.service.IncidentReportService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class IncidentReportController {

    private final IncidentReportService incidentReportService;

    public IncidentReportController(IncidentReportService incidentReportService){
        this.incidentReportService = incidentReportService;
    }

    @PostMapping("/user/{userId}")
    public IncidentResponseDTO createReport(
            @Valid @RequestBody IncidentRequestDTO dto,
            @PathVariable Long userId){

        return incidentReportService.createReport(dto, userId);
    }

    @GetMapping
    public List<IncidentResponseDTO> getAllReports(){
        return incidentReportService.getAllReports();
    }

    @GetMapping("/user/{userId}")
    public List<IncidentResponseDTO> getReportsByUser(@PathVariable Long userId){
        return incidentReportService.getReportsByUser(userId);
    }

    @PutMapping("/{reportId}/status")
    public IncidentResponseDTO updateStatus(
            @PathVariable Long reportId,
            @RequestParam String status){

        return incidentReportService.updateStatus(reportId, status);
    }
}