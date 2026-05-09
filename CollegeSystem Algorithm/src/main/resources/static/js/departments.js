async function loadDepartments(sorted = false) {
    const departments = await api.get(`/api/departments?sorted=${sorted}`);
    renderRows("departmentsTable", departments, (department) => `
        <tr>
            <td><span class="pill">${department.id}</span></td>
            <td>${department.name}</td>
        </tr>
    `);
}

function setupDepartmentForm() {
    document.getElementById("departmentForm").addEventListener("submit", async (event) => {
        event.preventDefault();
        const form = event.currentTarget;
        const department = {
            id: Number(form.id.value),
            name: form.name.value
        };

        try {
            await api.post("/api/departments", department);
            form.reset();
            setMessage("departmentMessage", "Department added successfully.", "success");
            await loadDepartments();
        } catch (error) {
            setMessage("departmentMessage", error.message, "error");
        }
    });

    document.getElementById("sortDepartments").addEventListener("click", () => {
        loadDepartments(true);
    });
}

document.addEventListener("DOMContentLoaded", () => {
    setupDepartmentForm();
    loadDepartments();
});
