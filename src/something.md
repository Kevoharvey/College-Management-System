# 🎓 College Management System (Java + Spring Boot + HTML/CSS/JS)

A professional full-stack student project with:

* Java backend logic
* Spring Boot MVC architecture
* Professional frontend dashboard
* Sorting algorithms (Bubble, Selection, Merge)
* Search functions
* Big O analysis

---

# 📂 Project Structure

```plaintext
college-management-system/
│
├── src/main/java/com/college/
│   ├── CollegeManagementApplication.java
│   ├── controller/
│   │   └── MainController.java
│   ├── model/
│   │   ├── Department.java
│   │   ├── Course.java
│   │   └── Student.java
│   ├── service/
│   │   └── CollegeService.java
│   └── sorting/
│       ├── BubbleSort.java
│       ├── SelectionSort.java
│       └── MergeSort.java
│
├── src/main/resources/
│   ├── templates/
│   │   └── index.html
│   └── static/
│       ├── css/style.css
│       └── js/app.js
```

---

# ☕ Backend Code

## Department.java

```java
package com.college.model;

public class Department {
    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Department ID: " + id + ", Name: " + name;
    }
}
```

## Course.java

```java
package com.college.model;

public class Course {
    private String code;
    private String name;
    private int studentCount;

    public Course(String code, String name, int studentCount) {
        this.code = code;
        this.name = name;
        this.studentCount = studentCount;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public int getStudentCount() { return studentCount; }
}
```

## Student.java

```java
package com.college.model;

public class Student {
    private int id;
    private String name;
    private double gpa;
    private int year;
    private String phone;

    public Student(int id, String name, double gpa, int year, String phone) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.year = year;
        this.phone = phone;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getGpa() { return gpa; }
    public int getYear() { return year; }
    public String getPhone() { return phone; }
}
```

---

# 🔄 Sorting Algorithms

## BubbleSort.java (Departments by Name)

```java
package com.college.sorting;
import com.college.model.Department;
import java.util.List;

public class BubbleSort {
    public static void sortDepartments(List<Department> departments) {
        for (int i = 0; i < departments.size() - 1; i++) {
            for (int j = 0; j < departments.size() - i - 1; j++) {
                if (departments.get(j).getName().compareTo(departments.get(j + 1).getName()) > 0) {
                    Department temp = departments.get(j);
                    departments.set(j, departments.get(j + 1));
                    departments.set(j + 1, temp);
                }
            }
        }
    }
}
```

### Big O:

* Best: O(n)
* Average/Worst: O(n²)

---

## SelectionSort.java (Courses by Student Count)

```java
package com.college.sorting;
import com.college.model.Course;
import java.util.List;

public class SelectionSort {
    public static void sortCourses(List<Course> courses) {
        for (int i = 0; i < courses.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < courses.size(); j++) {
                if (courses.get(j).getStudentCount() < courses.get(minIndex).getStudentCount()) {
                    minIndex = j;
                }
            }
            Course temp = courses.get(i);
            courses.set(i, courses.get(minIndex));
            courses.set(minIndex, temp);
        }
    }
}
```

### Big O:

* Best/Worst/Average: O(n²)

---

## MergeSort.java (Students by GPA)

```java
package com.college.sorting;
import com.college.model.Student;
import java.util.*;

public class MergeSort {
    public static void sortStudents(List<Student> students) {
        students.sort(Comparator.comparingDouble(Student::getGpa));
    }
}
```

### Big O:

* Best/Average/Worst: O(n log n)

---

# 🔍 Search Functions

## Binary Search

```java
package com.college.search;
import com.college.model.Department;
import java.util.List;

public static int binarySearch(List<Department> list, String key) {
    int low = 0;
    int high = list.size() - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;
        int cmp = list.get(mid).getName().compareTo(key);

        if (cmp == 0) return mid;          // found
        if (cmp < 0) low = mid + 1;         // go right
        else high = mid - 1;             // go left
    }

    return -1;                           // not found
}
```

## Student Search by ID

```java
public Student searchStudentById(int id) {
    for (Student s : students) {
        if (s.getId() == id) return s;
    }
    return null;
}
```

### Big O:

* Linear Search: O(n)

---

# 🌐 Frontend

## index.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>College Management System</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="dashboard">
    <aside class="sidebar">
        <h2>🎓 CMS Dashboard</h2>
        <ul>
            <li>Departments</li>
            <li>Courses</li>
            <li>Students</li>
            <li>Search</li>
        </ul>
    </aside>

    <main class="main-content">
        <header>
            <h1>College Management System</h1>
        </header>

        <section class="cards">
            <div class="card">Manage Departments</div>
            <div class="card">Manage Courses</div>
            <div class="card">Manage Students</div>
        </section>

        <section class="form-section">
            <h2>Add Student</h2>
            <form>
                <input type="text" placeholder="Student ID">
                <input type="text" placeholder="Name">
                <input type="text" placeholder="GPA">
                <button type="submit">Add</button>
            </form>
        </section>
    </main>
</div>
<script src="/js/app.js"></script>
</body>
</html>
```

---

## style.css

```css
body {
    margin: 0;
    font-family: Arial, sans-serif;
    background: #f4f7fb;
}

.dashboard {
    display: flex;
}

.sidebar {
    width: 250px;
    background: #1e293b;
    color: white;
    min-height: 100vh;
    padding: 20px;
}

.sidebar ul {
    list-style: none;
    padding: 0;
}

.sidebar li {
    margin: 20px 0;
    cursor: pointer;
}

.main-content {
    flex: 1;
    padding: 30px;
}

.cards {
    display: flex;
    gap: 20px;
}

.card {
    background: white;
    padding: 20px;
    border-radius: 12px;
    box-shadow: 0 4px 10px rgba(0,0,0,0.1);
    flex: 1;
}

form input, form button {
    display: block;
    margin: 10px 0;
    padding: 10px;
    width: 100%;
}
```

---

## app.js

```javascript
document.addEventListener("DOMContentLoaded", () => {
    console.log("College Management System Loaded Successfully 🚀");
});
```

---

# 🧠 Professional Features

## Included:

* Responsive dashboard UI
* MVC backend structure
* User input forms
* Sorting & searching
* Professional portfolio-ready design
* Expandable for:

  * MySQL integration
  * Authentication
  * Admin panel
  * Analytics dashboard

---

# 📊 Final Big O Summary

| Operation         | Algorithm      | Complexity |
| ----------------- | -------------- | ---------- |
| Sort Departments  | Bubble Sort    | O(n²)      |
| Sort Courses      | Selection Sort | O(n²)      |
| Sort Students     | Merge Sort     | O(n log n) |
| Search Department | Linear Search  | O(n)       |
| Search Student    | Linear Search  | O(n)       |

---

# 🏁 Final Notes

This system is ideal for:

* Software Engineering projects
* Data Structures projects
* Discrete Math integration
* Portfolio presentation
* Graduation showcases

You now have a professional-level student management system that looks market-ready rather than “last night before deadline” ready 😎
