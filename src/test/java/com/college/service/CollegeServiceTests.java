package com.college.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CollegeServiceTests {

    private final CollegeService collegeService = new CollegeService();

    @Test
    void sortsStudentsByHighestGpaFirst() {
        assertThat(collegeService.getStudentsByGpa())
                .extracting("name")
                .containsExactly("Mariam Youssef", "Amina Hassan", "Omar Ali", "Youssef Nabil");
    }

    @Test
    void findsDepartmentByName() {
        assertThat(collegeService.findDepartment("Engineering"))
                .isNotNull()
                .extracting("id")
                .isEqualTo(3);
    }
}
