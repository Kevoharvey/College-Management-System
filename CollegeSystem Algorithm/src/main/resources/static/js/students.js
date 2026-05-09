async function loadStudents(sorted = false) {
    const students = await api.get(`/api/students?sorted=${sorted}`);
    renderRows("studentsTable", students, (student) => `
        <tr>
            <td><span class="pill">${student.id}</span></td>
            <td>${student.name}</td>
            <td>${student.gpa.toFixed(2)}</td>
            <td>Year ${student.year}</td>
            <td>${student.phone}</td>
        </tr>
    `);
}

function setupStudentForm() {
    document.getElementById("studentForm").addEventListener("submit", async (event) => {
        event.preventDefault();
        const form = event.currentTarget;
        const student = {
            id: Number(form.id.value),
            name: form.name.value,
            gpa: Number(form.gpa.value),
            year: Number(form.year.value),
            phone: form.phone.value
        };

        try {
            await api.post("/api/students", student);
            form.reset();
            setMessage("studentMessage", "Student added successfully.", "success");
            await loadStudents();
        } catch (error) {
            setMessage("studentMessage", error.message, "error");
        }
    });

    document.getElementById("sortStudents").addEventListener("click", () => {
        loadStudents(true);
    });
}

document.addEventListener("DOMContentLoaded", () => {
    setupStudentForm();
    loadStudents();
});
