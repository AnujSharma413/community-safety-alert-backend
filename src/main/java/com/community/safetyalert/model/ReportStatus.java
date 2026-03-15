package com.community.safetyalert.model;

public enum ReportStatus {

    PENDING,      // user submitted report
    VERIFIED,     // admin approved
    REJECTED,     // admin rejected
    IN_PROGRESS,  // authorities working
    RESOLVED      // issue solved

}