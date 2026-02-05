package com.community.safetyalert.dto.incident;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidentRequestDTO {
    private String title;
    private String description;
    private String category;
    private Double latitude;
    private Double longitude;
    private String locationName;
    private String mediaUrl;
}
