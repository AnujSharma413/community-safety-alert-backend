package com.community.safetyalert.dto.incident;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class IncidentResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String locationName;
    private String status;
    private LocalDateTime reportedAt;
    private String mediaUrl;
    private String reportedBy;
}
