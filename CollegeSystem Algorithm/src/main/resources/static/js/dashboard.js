async function loadDashboard() {
    const stats = await api.get("/api/dashboard");
    document.getElementById("departmentsCount").textContent = formatNumber(stats.departments);
    document.getElementById("coursesCount").textContent = formatNumber(stats.courses);
    document.getElementById("studentsCount").textContent = formatNumber(stats.students);
    document.getElementById("enrollmentsCount").textContent = formatNumber(stats.totalEnrollments);
    document.getElementById("averageGpa").textContent = stats.averageGpa.toFixed(2);

    const students = await api.get("/api/students?sorted=true");
    const topStudent = students[0];
    document.getElementById("topStudent").textContent = topStudent ? topStudent.name : "No students yet";
    document.getElementById("topStudentMeta").textContent = topStudent
        ? `GPA ${topStudent.gpa.toFixed(2)} | Year ${topStudent.year}`
        : "Add students to see performance insights.";
}

document.addEventListener("DOMContentLoaded", () => {
    loadDashboard().catch(() => {
        document.getElementById("topStudent").textContent = "Unable to load";
        document.getElementById("topStudentMeta").textContent = "Start the Spring Boot server and refresh the page.";
    });
});
