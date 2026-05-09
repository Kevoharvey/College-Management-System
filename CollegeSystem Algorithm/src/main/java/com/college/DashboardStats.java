package com.college;

public record DashboardStats(
        int departments,
        int courses,
        int students,
        int totalEnrollments,
        double averageGpa
) {
}
