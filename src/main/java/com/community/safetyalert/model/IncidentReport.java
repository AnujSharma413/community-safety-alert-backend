package com.community.safetyalert.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncidentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private String category;

    private Double latitude;
    private Double longitude;

    private String locationName;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime reportedAt;

    @PrePersist
    public void setDefaults() {
        this.reportedAt = LocalDateTime.now();
        this.status = ReportStatus.PENDING;
    }

    private String mediaUrl;

    @ManyToOne
    @JoinColumn(name = "reported_by")
    private User reportedBy;
}