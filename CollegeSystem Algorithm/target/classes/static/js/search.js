function renderDepartmentResult(department) {
    document.getElementById("departmentResult").innerHTML = `
        <h3 class="result-title">${department.name}</h3>
        <p>Department ID: ${department.id}</p>
    `;
}

function renderStudentResult(student) {
    document.getElementById("studentResult").innerHTML = `
        <h3 class="result-title">${student.name}</h3>
        <p>Student ID: ${student.id}</p>
        <p>GPA: ${student.gpa.toFixed(2)}</p>
        <p>Year: ${student.year}</p>
        <p>Phone: ${student.phone}</p>
    `;
}

function setupSearchForms() {
    document.getElementById("departmentSearchForm").addEventListener("submit", async (event) => {
        event.preventDefault();
        const name = event.currentTarget.name.value.trim();

        try {
            const department = await api.get(`/api/departments/search?name=${encodeURIComponent(name)}`);
            renderDepartmentResult(department);
        } catch (error) {
            document.getElementById("departmentResult").innerHTML = `<p>${error.message}</p>`;
        }
    });

    document.getElementById("studentSearchForm").addEventListener("submit", async (event) => {
        event.preventDefault();
        const id = Number(event.currentTarget.id.value);

        try {
            const student = await api.get(`/api/students/search?id=${id}`);
            renderStudentResult(student);
        } catch (error) {
            document.getElementById("studentResult").innerHTML = `<p>${error.message}</p>`;
        }
    });
}

document.addEventListener("DOMContentLoaded", setupSearchForms);
