async function loadCourses(sorted = false) {
    const courses = await api.get(`/api/courses?sorted=${sorted}`);
    renderRows("coursesTable", courses, (course) => `
        <tr>
            <td><span class="pill">${course.code}</span></td>
            <td>${course.name}</td>
            <td>${formatNumber(course.numberOfStudents)}</td>
        </tr>
    `);
}

function setupCourseForm() {
    document.getElementById("courseForm").addEventListener("submit", async (event) => {
        event.preventDefault();
        const form = event.currentTarget;
        const course = {
            code: form.code.value,
            name: form.name.value,
            numberOfStudents: Number(form.numberOfStudents.value)
        };

        try {
            await api.post("/api/courses", course);
            form.reset();
            setMessage("courseMessage", "Course added successfully.", "success");
            await loadCourses();
        } catch (error) {
            setMessage("courseMessage", error.message, "error");
        }
    });

    document.getElementById("sortCourses").addEventListener("click", () => {
        loadCourses(true);
    });
}

document.addEventListener("DOMContentLoaded", () => {
    setupCourseForm();
    loadCourses();
});
