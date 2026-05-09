const api = {
    get: async (path) => {
        const response = await fetch(path);
        return readResponse(response);
    },
    post: async (path, body) => {
        const response = await fetch(path, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body)
        });
        return readResponse(response);
    }
};

async function readResponse(response) {
    const data = await response.json();

    if (!response.ok) {
        throw new Error(data.message || "Request failed.");
    }

    return data;
}

function setMessage(id, text, type = "") {
    const element = document.getElementById(id);
    if (!element) {
        return;
    }

    element.textContent = text;
    element.className = `message ${type}`.trim();
}

function formatNumber(value) {
    return new Intl.NumberFormat().format(value ?? 0);
}

function setupTheme() {
    const themeToggle = document.querySelector(".theme-toggle");
    const savedTheme = localStorage.getItem("college-theme") || "light";

    // Apply saved theme immediately on page load
    document.documentElement.setAttribute("data-theme", savedTheme);

    if (themeToggle) {
        updateThemeToggle(themeToggle, savedTheme);
        themeToggle.addEventListener("click", () => {
            const currentTheme = document.documentElement.getAttribute("data-theme");
            const nextTheme = currentTheme === "dark" ? "light" : "dark";
            
            document.documentElement.setAttribute("data-theme", nextTheme);
            localStorage.setItem("college-theme", nextTheme);
            updateThemeToggle(themeToggle, nextTheme);
        });
    }
}

function updateThemeToggle(themeToggle, theme) {
    themeToggle.setAttribute("aria-pressed", String(theme === "dark"));
    themeToggle.setAttribute("aria-label", theme === "dark" ? "Switch to light mode" : "Switch to dark mode");
    themeToggle.querySelector(".theme-toggle-text").textContent = theme === "dark" ? "☀️ Light" : "🌙 Dark";
}

function setupNavigation() {
    const page = document.body.dataset.page;
    document.querySelectorAll(".nav-link").forEach((link) => {
        if (link.dataset.page === page) {
            link.classList.add("active");
        }
    });

    const menuButton = document.querySelector(".mobile-menu");
    const sidebar = document.querySelector(".sidebar");
    const sidebarToggle = document.querySelector(".sidebar-toggle");
    const savedSidebarState = localStorage.getItem("college-sidebar");

    if (savedSidebarState === "collapsed") {
        document.body.classList.add("sidebar-collapsed");
    }

    if (menuButton && sidebar) {
        menuButton.addEventListener("click", () => {
            sidebar.classList.toggle("open");
        });
    }

    if (sidebarToggle) {
        updateSidebarToggle(sidebarToggle);
        sidebarToggle.addEventListener("click", () => {
            if (window.matchMedia("(max-width: 760px)").matches) {
                sidebar.classList.remove("open");
                return;
            }

            document.body.classList.toggle("sidebar-collapsed");
            localStorage.setItem(
                "college-sidebar",
                document.body.classList.contains("sidebar-collapsed") ? "collapsed" : "expanded"
            );
            updateSidebarToggle(sidebarToggle);
        });
    }

    document.querySelectorAll(".nav-link").forEach((link) => {
        link.addEventListener("click", () => {
            sidebar?.classList.remove("open");
        });
    });
}

function updateSidebarToggle(sidebarToggle) {
    if (window.matchMedia("(max-width: 760px)").matches) {
        sidebarToggle.setAttribute("aria-expanded", "true");
        sidebarToggle.setAttribute("aria-label", "Close sidebar");
        sidebarToggle.querySelector(".sidebar-toggle-symbol").textContent = "x";
        sidebarToggle.querySelector(".sidebar-toggle-text").textContent = "Close";
        return;
    }

    const isCollapsed = document.body.classList.contains("sidebar-collapsed");
    sidebarToggle.setAttribute("aria-expanded", String(!isCollapsed));
    sidebarToggle.setAttribute("aria-label", isCollapsed ? "Expand sidebar" : "Collapse sidebar");
    sidebarToggle.querySelector(".sidebar-toggle-symbol").textContent = isCollapsed ? ">" : "<";
    sidebarToggle.querySelector(".sidebar-toggle-text").textContent = isCollapsed ? "Expand" : "Collapse";
}

function renderRows(tableBodyId, rows, renderer) {
    const tbody = document.getElementById(tableBodyId);
    tbody.innerHTML = rows.map(renderer).join("");
}

document.addEventListener("DOMContentLoaded", () => {
    setupTheme();
    setupNavigation();
});
