# College Management System - Complete Project Documentation

## Project Overview
This is a full-stack College Management System built with Java (Spring Boot backend) and modern web technologies (HTML/CSS/JavaScript frontend). The system manages departments, courses, and students while implementing classic computer science algorithms (sorting and searching). It includes both a command-line interface (console app) and a web-based REST API with an interactive dashboard.

**Purpose:** Educational system demonstrating algorithm implementation integrated into a real web application.

**Architecture:** Two-part system where core algorithm classes can run standalone (CLI) or be leveraged by Spring Boot REST API connected to interactive web frontend.

---

## Table of Contents
1. [Project Structure](#project-structure)
2. [Build Configuration (pom.xml)](#build-configuration-pomxml)
3. [Data Model Classes](#data-model-classes)
4. [Searching Algorithms](#searching-algorithms)
5. [Sorting Algorithms](#sorting-algorithms)
6. [Console Application (Main.java)](#console-application-mainjava)
7. [Spring Boot Backend](#spring-boot-backend)
8. [Frontend Architecture](#frontend-architecture)
9. [Data Flow](#data-flow)

---

# Project Structure

```
CollegeSystem Algorithm/
├── pom.xml                          # Maven configuration & dependencies
├── src/
│   ├── com/college/                 # Console app (core algorithms)
│   │   ├── Main.java                # CLI menu interface
│   │   ├── Student.java             # Student data model
│   │   ├── Course.java              # Course data model
│   │   ├── Department.java          # Department data model
│   │   ├── SearchingAlgorithm.java  # Binary search implementations
│   │   └── SortAlgorithm.java       # Sorting algorithms
│   └── main/
│       ├── java/com/college/        # Spring Boot backend
│       │   ├── CollegeSystemApplication.java
│       │   ├── CollegeApiController.java
│       │   ├── CollegeDataService.java
│       │   ├── DashboardStats.java
│       │   ├── Student.java
│       │   ├── Course.java
│       │   ├── Department.java
│       │   ├── SearchingAlgorithm.java
│       │   └── SortAlgorithm.java
│       └── resources/static/
│           ├── index.html           # Dashboard page
│           ├── departments.html     # Department management
│           ├── courses.html         # Course management
│           ├── students.html        # Student management
│           ├── search.html          # Search interface
│           ├── css/
│           │   └── styles.css       # Global styling with dark mode
│           └── js/
│               ├── app.js           # Shared utilities & API wrapper
│               ├── dashboard.js     # Dashboard logic
│               ├── departments.js   # Department page logic
│               ├── courses.js       # Course page logic
│               ├── students.js      # Student page logic
│               └── search.js        # Search functionality
└── target/                          # Compiled output
```

---

# Build Configuration (pom.xml)

**Purpose:** Defines project metadata, dependencies, and build settings for Maven.

## Complete File Breakdown

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
```
- **Lines 1-4:** XML declaration and Maven project schema definition
- **xmlns:** XML namespace declaration for Maven
- **xsi:** XML schema instance for validation

```xml
    <modelVersion>4.0.0</modelVersion>
```
- **Line 5:** Specifies Maven POM format version (4.0.0 is the standard)

```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.5</version>
        <relativePath/>
    </parent>
```
- **Lines 6-11:** Parent POM configuration
- **groupId:** Organization namespace (org.springframework.boot)
- **artifactId:** Parent project name (spring-boot-starter-parent)
- **version:** Spring Boot version 3.3.5 (latest stable with Java 17 support)
- **relativePath:** Empty to fetch from Maven Central Repository

```xml
    <groupId>com.college</groupId>
    <artifactId>college-system</artifactId>
    <version>1.0.0</version>
    <name>College Management System</name>
    <description>Spring Boot college management system with a static frontend</description>
```
- **groupId:** com.college - Java package namespace
- **artifactId:** college-system - Project name in repositories
- **version:** 1.0.0 - Initial release version
- **name:** Human-readable project name
- **description:** Brief project overview

```xml
    <properties>
        <java.version>17</java.version>
    </properties>
```
- **Line 19:** Configures Java 17 as compilation target
- Enables modern Java features (records, sealed classes, pattern matching)

```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
```
- **Lines 21-27:** Single dependency declaration
- **spring-boot-starter-web:** Includes:
  - Spring MVC for web endpoints
  - Embedded Apache Tomcat server
  - Jackson for JSON serialization/deserialization
  - Validation framework

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```
- **Lines 29-37:** Build plugin configuration
- **spring-boot-maven-plugin:** Enables:
  - `mvn spring-boot:run` command
  - Packaging as executable JAR with embedded server
  - Fat JAR creation (includes all dependencies)

---

# Data Model Classes

All data models follow the same pattern: private fields, constructor, getters/setters, and toString() override.

## Department.java

**Purpose:** Represents an academic department in the college system.

```java
public class Department {
    private int id;
    private String name;
```
- **id (int):** Unique identifier for the department
- **name (String):** Department name (e.g., "Computer Science")

```java
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }
```
- **Constructor:** Initializes both fields when creating new Department
- **Parameters:**
  - `id`: Must be unique and > 0
  - `name`: Should not be empty

```java
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
```
- **Getter methods:** Provide read-only access to fields
- **Purpose:** External classes can read but not modify ID (immutable)

```java
    public void setName(String name) {
        this.name = name;
    }
```
- **Setter for name:** Only name can be updated (ID is immutable)
- **Design decision:** Prevents changing department ID after creation

```java
    @Override
    public String toString() {
        return "Department{" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                '}';
    }
```
- **toString() override:** Used for console output and logging
- **Example output:** `Department{ID=10, Name='Computer Science'}`

---

## Course.java

**Purpose:** Represents a course offered at the college.

```java
public class Course {
    private String code;
    private String name;
    private int numberOfStudents;
```
- **code (String):** Unique course code (e.g., "CS101", "BUS210")
- **name (String):** Full course name (e.g., "Data Structures")
- **numberOfStudents (int):** Current enrollment count

```java
    public Course(String code, String name, int numberOfStudents) {
        this.code = code;
        this.name = name;
        this.numberOfStudents = numberOfStudents;
    }
```
- **Constructor:** Initializes all three fields
- **Used when:** Creating new course objects from user input or API requests

```java
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }
```
- **Getter methods:** Return immutable views of course data

```java
    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
```
- **Setter:** Updates enrollment count when students enroll/withdraw
- **Design:** Code and name are immutable after creation

```java
    @Override
    public String toString() {
        return "Course{" +
                "Code='" + code + '\'' +
                ", Name='" + name + '\'' +
                ", Students=" + numberOfStudents +
                '}';
    }
```
- **Example output:** `Course{Code='CS101', Name='Data Structures', Students=42}`

---

## Student.java

**Purpose:** Represents a student enrolled in the college.

```java
public class Student {
    private int id;
    private String name;
    private double gpa;
    private int year;
    private String phone;
```
- **id (int):** Unique student identifier
- **name (String):** Student's full name
- **gpa (double):** Grade Point Average (0.0 - 4.0)
- **year (int):** Academic year (1 = freshman, 5 = senior, etc.)
- **phone (String):** Contact phone number

```java
    public Student(int id, String name, double gpa, int year, String phone) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.year = year;
        this.phone = phone;
    }
```
- **Constructor:** Initializes all student fields
- **Parameters:** All five fields required for new student creation

```java
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public int getYear() {
        return year;
    }

    public String getPhone() {
        return phone;
    }
```
- **Getter methods:** Provide read access to all immutable fields

```java
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
```
- **Setter for GPA:** Updates academic performance
- **Design:** Only GPA is mutable (other fields immutable after creation)

```java
    @Override
    public String toString() {
        return "Student{" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", GPA=" + gpa +
                ", Year=" + year +
                ", Phone='" + phone + '\'' +
                '}';
    }
```
- **Example output:** `Student{ID=1001, Name='John Doe', GPA=3.8, Year=3, Phone='555-1234'}`

---

# Searching Algorithms

**File:** SearchingAlgorithm.java (src/com/college/)

**Purpose:** Implements binary search algorithms for finding records. Binary search requires sorted data but achieves O(log n) efficiency.

## Binary Search - Department by Name

```java
public static Department searchDepartmentByName(ArrayList<Department> list, String name) {
    // Sort list by department name (case-insensitive) before binary search
    list.sort(Comparator.comparing(d -> d.getName().toLowerCase()));
```
- **Parameter:** `list` - ArrayList of departments to search
- **Parameter:** `name` - Department name to find (case-insensitive)
- **Lambda expression:** `d -> d.getName().toLowerCase()` creates a sorting key
  - Maps each department to its lowercase name for comparison
  - Ensures case-insensitive alphabetical sorting (A=a)

```java
    int low = 0;
    int high = list.size() - 1;
```
- **low:** Start index (0 - first element)
- **high:** End index (list.size() - 1 - last element)
- **Invariant:** Search space is always [low, high]

```java
    while (low <= high) {
        int mid = low + (high - low) / 2;  // Prevent integer overflow
```
- **Loop condition:** Continues while there are elements to check
- **mid calculation:** `low + (high - low) / 2` prevents integer overflow
  - Traditional `(low + high) / 2` can overflow if numbers are very large
  - This formula is mathematically equivalent but safer

```java
        int cmp = list.get(mid).getName().compareToIgnoreCase(name);
```
- **cmp:** Result of string comparison
  - Returns < 0 if mid element comes before target alphabetically
  - Returns = 0 if exact match found
  - Returns > 0 if mid element comes after target alphabetically

```java
        if (cmp == 0) {
            return list.get(mid);  // Found!
        } else if (cmp < 0) {
            low = mid + 1;         // Search right half
        } else {
            high = mid - 1;        // Search left half
        }
    }

    return null;  // Not found
}
```
- **if cmp == 0:** Exact match found, return immediately
- **else if cmp < 0:** Mid element is alphabetically before target
  - Set low = mid + 1 to search the right (higher) half
- **else (cmp > 0):** Mid element is alphabetically after target
  - Set high = mid - 1 to search the left (lower) half
- **After loop:** No match found in entire list

**Time Complexity:** O(log n) - Each iteration eliminates half the search space
**Space Complexity:** O(1) - Only uses a few variables
**Prerequisite:** List must be sorted by department name

---

## Binary Search - Student by ID

```java
public static Student searchStudentById(ArrayList<Student> list, int id) {
    // Sort list by student ID before binary search
    list.sort(Comparator.comparingInt(Student::getId));
```
- **Purpose:** Find student by numeric ID (more efficient than linear search)
- **Sort key:** `Student::getId` - Method reference extracting ID from each student
- **Sorting order:** Ascending (1, 2, 3, ..., 9999)

```java
    int low = 0;
    int high = list.size() - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;
        int midId = list.get(mid).getId();
```
- **Same binary search structure** as department search
- **midId:** Numeric ID at middle position for comparison

```java
        if (midId == id) {
            return list.get(mid);  // Found!
        } else if (midId < id) {
            low = mid + 1;         // Search right (higher IDs)
        } else {
            high = mid - 1;        // Search left (lower IDs)
        }
    }

    return null;  // Not found
}
```
- **Numeric comparison:** Direct == check (simpler than string comparison)
- **else if (midId < id):** Mid value is smaller, search higher values
- **else (midId > id):** Mid value is larger, search lower values

**Time Complexity:** O(log n)
**Space Complexity:** O(1)

---

# Sorting Algorithms

**File:** SortAlgorithm.java (src/com/college/)

**Purpose:** Implements three classic sorting algorithms, each demonstrating different approaches.

## Bubble Sort - Departments by Name

```java
public static void bubbleSortDepartments(ArrayList<Department> list) {
    int n = list.size();

    for (int i = 0; i < n - 1; i++) {
```
- **n:** Number of departments
- **Outer loop:** Iterates n-1 times (each pass sorts one more element)
  - After pass 1: largest is in place
  - After pass 2: second largest is in place
  - After pass i: i largest elements are in place

```java
        for (int j = 0; j < n - i - 1; j++) {
```
- **Inner loop:** Compares adjacent pairs
- **j < n - i - 1:** Upper bound decreases after each pass
  - Pass 1: j goes from 0 to n-2 (n-1-1 items to compare)
  - Pass 2: j goes from 0 to n-3 (already sorted largest item excluded)

```java
            if (list.get(j).getName()
                    .compareToIgnoreCase(list.get(j + 1).getName()) > 0) {
```
- **Comparison:** j element name vs j+1 element name (case-insensitive)
- **> 0:** j name comes alphabetically AFTER j+1 name (wrong order)

```java
                Department temp = list.get(j);
                list.set(j, list.get(j + 1));
                list.set(j + 1, temp);
```
- **Swap logic:**
  1. Save element at position j to temp variable
  2. Copy element at j+1 to position j
  3. Copy saved temp to position j+1
- **Result:** Adjacent elements are now in correct alphabetical order

**Algorithm behavior:**
- First pass: Bubbles largest element to end (it "bubbles" right)
- Second pass: Bubbles second largest to second-to-last position
- Continues until all sorted

**Time Complexity:**
- Worst case O(n²) - reverse sorted (every pair swapped)
- Average case O(n²)
- Best case O(n) - already sorted (no swaps)

**Space Complexity:** O(1) - Sorts in-place, no extra arrays

---

## Selection Sort - Courses by Enrollment

```java
public static void selectionSortCourses(ArrayList<Course> list) {
    int n = list.size();

    for (int i = 0; i < n - 1; i++) {
        int minIndex = i;
```
- **i:** Current position to fill with smallest remaining element
- **minIndex:** Tracks position of smallest element found so far

```java
        for (int j = i + 1; j < n; j++) {
```
- **Inner loop:** Searches from i+1 to end for smallest element
- **j starts at i+1:** Skip already-sorted elements (0 to i-1)

```java
            if (list.get(j).getNumberOfStudents() < 
                list.get(minIndex).getNumberOfStudents()) {
                minIndex = j;
            }
```
- **Comparison:** Student count j vs current minimum
- **If smaller:** Update minIndex to j
- **After inner loop:** minIndex holds position of course with fewest students

```java
        Course temp = list.get(i);
        list.set(i, list.get(minIndex));
        list.set(minIndex, temp);
```
- **Swap:** Place minimum course at position i
  1. Save course at i to temp
  2. Move minimum course to position i
  3. Move saved course to where minimum was
- **Result:** Position i now has smallest-enrollment course

**Algorithm behavior:**
- Pass 1: Find smallest, place at position 0
- Pass 2: Find smallest among remaining, place at position 1
- Continues building sorted array from left to right

**Time Complexity:** O(n²) - Always (no matter input order)
- Outer loop: n iterations
- Inner loop: ~n iterations per outer (on average n/2)
- Total: n × (n/2) = n²/2 ≈ O(n²)

**Space Complexity:** O(1) - Sorts in-place

---

## Insertion Sort - Students by GPA

```java
public static void insertionSortStudents(ArrayList<Student> list) {
    for (int i = 1; i < list.size(); i++) {
        Student key = list.get(i);
        int j = i - 1;
```
- **i:** Position of element to insert (starts at 1 - skip first)
- **key:** Current student being positioned
- **j:** Position to compare with (starts left of current)

```java
        while (j >= 0 && list.get(j).getGpa() < key.getGpa()) {
```
- **Loop condition:** Continues while:
  1. `j >= 0`: Haven't reached start of array
  2. `list.get(j).getGpa() < key.getGpa()`: Left element has lower GPA (descending order)
- **Purpose:** Find correct insertion position for key

```java
            list.set(j + 1, list.get(j));
            j--;
```
- **Shift operation:** Move higher-GPA element one position right
- **j--:** Move pointer left to check next element
- **Result:** Creates gap for key to be inserted

```java
        list.set(j + 1, key);
```
- **Insert:** Place key at position j+1
- **Why j+1?:** Loop stopped when condition became false
  - Either j < 0 (key is smallest, goes at position 0)
  - Or left element has higher GPA (key goes to right of it)
  - In both cases, j+1 is correct position

**Algorithm behavior:**
- First iteration: 1-element array is sorted (trivial)
- Second: Insert 2nd element in correct position within first 2
- Third: Insert 3rd element in correct position within first 3
- Continues building sorted array from left to right

**Descending order note:** Condition `<` sorts high-to-low (3.9, 3.8, 3.5...)

**Time Complexity:**
- Worst case O(n²) - Reverse sorted (shift all elements)
- Average case O(n²)
- Best case O(n) - Already sorted (minimal shifts)

**Space Complexity:** O(1) - Sorts in-place

---

# Console Application (Main.java)

**File:** src/com/college/Main.java
**Purpose:** Command-line interface for the College Management System

```java
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Department> departments = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        int choice;
```
- **Scanner input:** Reads user keyboard input from console
- **Three ArrayLists:** In-memory storage for each data type
  - Data persists only during program session
  - Resets when application closes
- **choice:** Menu option selected by user

```java
        do {
            System.out.println("\n====== College Management System ======");
            System.out.println("1- Add Department");
            System.out.println("2- Add Course");
            System.out.println("3- Add Student");
            // ... more options ...
            System.out.println("10- Exit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();

            switch (choice) {
```
- **do-while loop:** Ensures menu displays at least once
- **switch statement:** Routes to appropriate action based on choice
- **Continues until user chooses 10 (Exit)**

## Case 1: Add Department

```java
case 1:
    int depId;
    while (true) {
        System.out.print("Enter Department ID: ");
        depId = input.nextInt();
        if (depId > 0)
            break;
        System.out.println("❌ ID must be > 0");
    }
```
- **Infinite loop:** Validates input until positive integer provided
- **break:** Exits loop when valid ID entered (> 0)
- **Emoji feedback:** ❌ indicates error

```java
    boolean depExists = false;
    for (Department d : departments) {
        if (d.getId() == depId) {
            depExists = true;
            break;
        }
    }
    if (depExists) {
        System.out.println("❌ Department ID already exists!");
        break;
    }
```
- **Duplicate check:** Iterates through existing departments
- **Linear search:** Checks if ID matches any existing department
- **depExists flag:** Set to true if found
- **Early break:** Stop loop once duplicate found
- **If duplicate:** Display error and skip to next menu

```java
    input.nextLine();  // Consume newline
    String depName;
    while (true) {
        System.out.print("Enter Department Name: ");
        depName = input.nextLine();
        if (!depName.trim().isEmpty())
            break;
        System.out.println("❌ Name cannot be empty");
    }
```
- **input.nextLine():** Consumes leftover newline from previous nextInt()
  - Without this, next nextLine() reads empty string
- **Infinite validation loop:** Ensures non-empty name
- **trim():** Removes leading/trailing whitespace
- **isEmpty():** Checks if result is blank string

```java
    departments.add(new Department(depId, depName));
    System.out.println("✔ Department Added Successfully!");
    break;
```
- **Create and add:** New Department object created with validated ID and name
- **Success message:** ✔ confirms addition
- **break:** Exit switch to return to menu

---

## Case 2-9: Similar Structure

Courses and Students follow same pattern:
- Input validation with error messages
- Duplicate checking
- Object creation
- List addition
- User feedback

Searches use SearchingAlgorithm methods
Sorts use SortAlgorithm methods

---

# Spring Boot Backend

**Location:** src/main/java/com/college/

## CollegeSystemApplication.java

```java
@SpringBootApplication
public class CollegeSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollegeSystemApplication.class, args);
    }
}
```

**@SpringBootApplication annotation combines:**
1. **@Configuration:** Marks class as source of bean definitions
2. **@EnableAutoConfiguration:** Automatically configures Spring based on JAR dependencies
3. **@ComponentScan:** Scans classpath for @Component, @Service, @Controller classes

**SpringApplication.run():**
- Starts embedded Tomcat server (runs on port 8080 by default)
- Initializes Spring IoC container
- Auto-discovers and registers beans
- Application is ready for HTTP requests

---

## CollegeDataService.java

**Purpose:** Service layer managing all data operations and business logic

```java
@Service
public class CollegeDataService {
    private final ArrayList<Department> departments = new ArrayList<>();
    private final ArrayList<Course> courses = new ArrayList<>();
    private final ArrayList<Student> students = new ArrayList<>();
```
- **@Service annotation:** Marks as Spring service bean
- **final ArrayLists:** Initialized once, cannot be reassigned
- **private:** Only accessible within this class

```java
    public CollegeDataService() {
        departments.add(new Department(10, "Computer Science"));
        departments.add(new Department(20, "Business Administration"));
        departments.add(new Department(30, "Engineering"));

        courses.add(new Course("CS101", "Data Structures", 42));
        courses.add(new Course("BUS210", "Principles of Management", 36));
        courses.add(new Course("ENG150", "Engineering Drawing", 28));

        students.add(new Student(1001, "Mariam Hassan", 3.8, 3, "01012345678"));
        students.add(new Student(1002, "Omar Ali", 3.4, 2, "01098765432"));
        students.add(new Student(1003, "Nour Ahmed", 3.9, 4, "01122334455"));
    }
```
- **Constructor:** Called once when Spring creates this bean
- **Sample data:** Pre-populated for demo purposes
- **Three entries each:** Provides data to display on first page load

### Getter Methods (Read-Only)

```java
public synchronized List<Department> getDepartments() {
    return List.copyOf(departments);
}
```
- **synchronized:** Ensures thread-safety for concurrent requests
  - Only one thread can execute at a time
  - Prevents race conditions
- **List.copyOf():** Returns immutable copy
  - Prevents external code from modifying internal list
  - Safe to return without worry of external modification
- **Return type:** Immutable List (not ArrayList)

### Add Methods

```java
public synchronized Department addDepartment(Department department) {
    departments.add(department);
    return department;
}
```
- **synchronized:** Thread-safe for concurrent users
- **Adds to list:** Persists data in memory
- **Returns:** The added object for immediate confirmation in API response

### Sorting Methods

```java
public synchronized List<Department> getDepartmentsSortedByName() {
    ArrayList<Department> sorted = new ArrayList<>(departments);
    SortAlgorithm.bubbleSortDepartments(sorted);
    return sorted;
}
```
- **Copy constructor:** `new ArrayList<>(departments)` creates independent copy
  - Doesn't modify original data
  - Sorting only affects copy
- **Calls algorithm:** Delegates to SortAlgorithm class
- **Returns:** Sorted copy

```java
public synchronized List<Course> getCoursesSortedByEnrollment() {
    ArrayList<Course> sorted = new ArrayList<>(courses);
    SortAlgorithm.selectionSortCourses(sorted);
    return sorted;
}

public synchronized List<Student> getStudentsSortedByGpa() {
    ArrayList<Student> sorted = new ArrayList<>(students);
    SortAlgorithm.insertionSortStudents(sorted);
    return sorted;
}
```
- **Same pattern** for different data types
- **Each uses appropriate algorithm:**
  - Departments: Bubble sort
  - Courses: Selection sort
  - Students: Insertion sort

### Search Methods

```java
public synchronized Optional<Department> findDepartmentByName(String name) {
    return Optional.ofNullable(SearchingAlgorithm.searchDepartmentByName(departments, name));
}
```
- **Optional wrapper:** Null-safe result handling
  - `Optional.ofNullable()` wraps result
  - If null: creates empty Optional
  - If found: creates Optional with value
- **Cleaner API:** Consumers use `.map()` and `.orElseGet()` instead of null checks

```java
public synchronized Optional<Student> findStudentById(int id) {
    return Optional.ofNullable(SearchingAlgorithm.searchStudentById(students, id));
}
```
- **Integrates binary search:** Uses SearchingAlgorithm

### Validation Methods

```java
public synchronized boolean departmentIdExists(int id) {
    return departments.stream()
        .anyMatch(department -> department.getId() == id);
}
```
- **Stream API:** Functional approach to searching
- **anyMatch():** Returns true if any element matches condition
- **Lambda:** `department -> department.getId() == id` tests each element
- **Result:** true if ID already exists, false otherwise

```java
public synchronized boolean courseCodeExists(String code) {
    return courses.stream()
        .anyMatch(course -> course.getCode().equalsIgnoreCase(code));
}
```
- **Case-insensitive:** Prevents duplicate codes like "CS101" and "cs101"

### Dashboard Statistics

```java
public synchronized DashboardStats getDashboardStats() {
    double averageGpa = students.stream()
        .mapToDouble(Student::getGpa)
        .average()
        .orElse(0);
```
- **mapToDouble():** Converts Stream<Student> to DoubleStream of GPAs
- **Student::getGpa:** Method reference extracting GPA from each student
- **average():** Returns OptionalDouble (could be empty if no students)
- **.orElse(0):** Default to 0 if empty

```java
    int totalEnrollments = courses.stream()
        .mapToInt(Course::getNumberOfStudents)
        .sum();
```
- **mapToInt():** Converts to IntStream of enrollment counts
- **sum():** Calculates total across all courses

```java
    return new DashboardStats(
        departments.size(),
        courses.size(),
        students.size(),
        totalEnrollments,
        Math.round(averageGpa * 100.0) / 100.0);
}
```
- **DashboardStats constructor:** Creates immutable record with all stats
- **Math.round():** Rounds GPA to 2 decimal places
  - Multiply by 100: 3.847 → 384.7
  - Round: 384.7 → 385
  - Divide by 100: 385 → 3.85

---

## DashboardStats.java

```java
public record DashboardStats(
    int departments,
    int courses,
    int students,
    int totalEnrollments,
    double averageGpa
) {
}
```

**Java Record (Java 14+):** Immutable data carrier class

**Automatically generates:**
- **Constructor:** Public constructor accepting all fields in order
- **Getters:** `departments()`, `courses()`, etc. (not `getDepartments()`)
- **equals() & hashCode():** Based on all fields
- **toString():** Formatted output with field names and values

**Used for:** Sending statistics to frontend as JSON
- Spring automatically serializes to: `{"departments": 3, "courses": 3, ...}`

---

## CollegeApiController.java

**Purpose:** REST API endpoints for frontend communication

```java
@RestController
@RequestMapping("/api")
public class CollegeApiController {
    private final CollegeDataService dataService;

    public CollegeApiController(CollegeDataService dataService) {
        this.dataService = dataService;
    }
}
```
- **@RestController:** Combines @Controller + @ResponseBody
  - All methods return JSON, not HTML views
- **@RequestMapping("/api"):** Base URL path for all endpoints
- **Constructor injection:** Spring automatically provides dataService bean

### Dashboard Endpoint

```java
@GetMapping("/dashboard")
public DashboardStats dashboard() {
    return dataService.getDashboardStats();
}
```
- **@GetMapping:** Handles GET requests to /api/dashboard
- **Returns:** DashboardStats automatically serialized to JSON
- **HTTP 200:** Success response with body

### Department Endpoints

```java
@GetMapping("/departments")
public List<Department> departments(@RequestParam(defaultValue = "false") boolean sorted) {
    return sorted ? dataService.getDepartmentsSortedByName() : dataService.getDepartments();
}
```
- **@RequestParam:** Reads query parameter from URL (?sorted=true)
- **defaultValue:** If not provided, defaults to false
- **Ternary operator:** Return sorted or unsorted based on parameter
- **Usage:** 
  - GET /api/departments → unsorted
  - GET /api/departments?sorted=true → bubble sorted by name

```java
@PostMapping("/departments")
public ResponseEntity<?> addDepartment(@RequestBody Department department) {
    if (department.getId() <= 0 || isBlank(department.getName())) {
        return badRequest("Department ID must be positive and name cannot be empty.");
    }

    if (dataService.departmentIdExists(department.getId())) {
        return badRequest("A department with this ID already exists.");
    }

    department.setName(department.getName().trim());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(dataService.addDepartment(department));
}
```
- **@PostMapping:** Handles POST requests to /api/departments
- **@RequestBody:** Deserializes JSON request body to Department object
- **Validation:**
  1. ID must be > 0
  2. Name must not be empty/blank
  3. ID must not already exist
- **.trim():** Removes whitespace from name
- **ResponseEntity:** Allows specifying HTTP status code
- **HttpStatus.CREATED:** 201 status (resource created)
- **Returns:** Created department object in body

```java
@GetMapping("/departments/search")
public ResponseEntity<?> searchDepartment(@RequestParam String name) {
    if (isBlank(name)) {
        return badRequest("Enter a department name to search.");
    }

    return dataService.findDepartmentByName(name.trim())
        .<ResponseEntity<?>>map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Department not found.")));
}
```
- **@RequestParam String name:** Query parameter (?name=value)
- **Validation:** Name must not be blank
- **Optional chaining:**
  - `.map(ResponseEntity::ok)` → 200 if found
  - `.orElseGet(...)` → 404 if not found
- **Usage:** GET /api/departments/search?name=Computer Science

### Course Endpoints (Similar structure)

```java
@GetMapping("/courses")
public List<Course> courses(@RequestParam(defaultValue = "false") boolean sorted) {
    return sorted ? dataService.getCoursesSortedByEnrollment() : dataService.getCourses();
}

@PostMapping("/courses")
public ResponseEntity<?> addCourse(@RequestBody Course course) {
    if (isBlank(course.getCode()) || isBlank(course.getName()) || course.getNumberOfStudents() < 0) {
        return badRequest("Course code and name are required. Student count must be zero or more.");
    }

    if (dataService.courseCodeExists(course.getCode())) {
        return badRequest("A course with this code already exists.");
    }

    course.setCode(course.getCode().trim().toUpperCase());
    course.setName(course.getName().trim());
    return ResponseEntity.status(HttpStatus.CREATED).body(dataService.addCourse(course));
}
```
- **Course validations:**
  - Code and name required
  - Student count >= 0
  - Code must be unique (case-insensitive)
- **Uppercase conversion:** "CS101" stored as "CS101"

### Student Endpoints

```java
@PostMapping("/students")
public ResponseEntity<?> addStudent(@RequestBody Student student) {
    if (student.getId() <= 0 || isBlank(student.getName()) || isBlank(student.getPhone())) {
        return badRequest("Student ID, name, and phone are required.");
    }

    if (student.getGpa() < 0 || student.getGpa() > 4 || student.getYear() < 1 || student.getYear() > 5) {
        return badRequest("GPA must be between 0 and 4. Year must be between 1 and 5.");
    }

    if (dataService.studentIdExists(student.getId())) {
        return badRequest("A student with this ID already exists.");
    }

    student.setName(student.getName().trim());
    student.setPhone(student.getPhone().trim());
    return ResponseEntity.status(HttpStatus.CREATED).body(dataService.addStudent(student));
}
```
- **Student validations:**
  - ID, name, phone all required
  - GPA in 0-4 range
  - Year in 1-5 range
  - ID must be unique
- **Data cleanup:** Trim whitespace from name and phone

### Helper Methods

```java
private ResponseEntity<Map<String, String>> badRequest(String message) {
    return ResponseEntity.badRequest().body(Map.of("message", message));
}

private boolean isBlank(String value) {
    return value == null || value.trim().isEmpty();
}
```
- **badRequest():** Standardized error response (400 status code)
- **isBlank():** Utility for string validation
  - Handles null values (prevents NullPointerException)
  - Trims and checks for empty

---

# Frontend Architecture

## HTML Pages

All pages follow consistent structure for unified user experience.

### index.html - Dashboard Page

**Purpose:** Main landing page showing system overview and key statistics

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard | College Management System</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
```
- **DOCTYPE:** HTML5 declaration
- **lang="en":** Language for accessibility/SEO
- **charset:** UTF-8 for international character support
- **viewport:** Responsive design settings
  - width=device-width: Use device width
  - initial-scale=1.0: No zoom on load
- **title:** Displayed in browser tab
- **link rel="stylesheet":** Include global CSS

```html
<body data-page="dashboard">
```
- **data-page attribute:** Identifies current page
- Used by JavaScript to highlight active navigation link

```html
<div class="app-shell">
    <aside class="sidebar">
        <div class="brand">
            <div class="brand-mark">CM</div>
            <div class="brand-copy">
                <p class="brand-title">College System</p>
                <p class="brand-subtitle">Academic operations</p>
            </div>
        </div>
```
- **app-shell:** Main container for layout
- **sidebar:** Left navigation panel
- **brand:** Logo and app name section
- **brand-mark:** Circle with initials "CM"

```html
        <button class="sidebar-toggle" type="button" aria-label="Collapse sidebar">
            <span class="sidebar-toggle-text">Collapse</span>
            <span class="sidebar-toggle-symbol">&lt;</span>
        </button>
```
- **Sidebar toggle:** Collapses/expands sidebar for space
- **aria-label:** Accessibility description for screen readers
- **&lt;:** HTML entity for < symbol

```html
        <nav class="nav-list">
            <a class="nav-link" data-page="dashboard" href="/index.html">
                <span class="nav-icon">D</span>
                <span class="nav-text">Dashboard</span>
            </a>
            <a class="nav-link" data-page="departments" href="/departments.html">
                <span class="nav-icon">DP</span>
                <span class="nav-text">Departments</span>
            </a>
            <!-- More nav links... -->
        </nav>
```
- **nav-list:** Navigation menu
- **data-page:** Used to match current page and highlight active link
- **nav-icon:** Single or double letter abbreviation
- **nav-text:** Full page name (hidden on mobile)

```html
    <main class="main">
        <header class="topbar">
            <button class="mobile-menu" aria-label="Open navigation">☰</button>
            <div>
                <p class="page-kicker">Overview</p>
                <h1 class="page-title">College Management Dashboard</h1>
            </div>
            <div class="topbar-actions">
                <div class="topbar-note">Live data from the Spring Boot API</div>
                <button class="theme-toggle" type="button" aria-label="Toggle dark mode">
                    <span class="theme-toggle-mark">A</span>
                    <span class="theme-toggle-text">Dark</span>
                </button>
            </div>
        </header>
```
- **topbar:** Header with page title
- **mobile-menu:** Hamburger button (hidden on desktop)
- **page-kicker:** Small subtitle/category
- **page-title:** Main heading
- **topbar-note:** Context about data source
- **theme-toggle:** Dark/Light mode button

```html
        <section class="content">
            <div class="hero-panel">
                <div class="intro-band">
                    <h2>Academic data at a glance</h2>
                    <p>Manage departments, course enrollment, student records...</p>
                </div>
                <div class="insight-panel">
                    <h2>Top student</h2>
                    <div class="insight-value" id="topStudent">Loading</div>
                    <p class="insight-label" id="topStudentMeta">Checking current GPA rankings.</p>
                </div>
            </div>
```
- **hero-panel:** Featured content area
- **intro-band:** Introduction text
- **insight-panel:** Displays top student by GPA
- **insight-value:** Large value display (loaded by JS)
- **id attributes:** Targeted by JavaScript for updating

```html
            <div class="stats-grid">
                <article class="stat-card">
                    <p class="stat-label">Departments</p>
                    <p class="stat-value" id="departmentsCount">0</p>
                    <div class="stat-accent"></div>
                </article>
                <article class="stat-card">
                    <p class="stat-label">Courses</p>
                    <p class="stat-value" id="coursesCount">0</p>
                    <div class="stat-accent"></div>
                </article>
                <article class="stat-card">
                    <p class="stat-label">Students</p>
                    <p class="stat-value" id="studentsCount">0</p>
                    <div class="stat-accent"></div>
                </article>
                <article class="stat-card">
                    <p class="stat-label">Course Enrollments</p>
                    <p class="stat-value" id="enrollmentsCount">0</p>
                    <div class="stat-accent"></div>
                </article>
            </div>

            <div class="insight-panel">
                <h2>Average GPA</h2>
                <div class="insight-value" id="averageGpa">0.00</div>
                <p class="insight-label">Calculated from the current student list.</p>
            </div>
        </section>
    </main>
</div>

<script src="/js/app.js"></script>
<script src="/js/dashboard.js"></script>
</body>
</html>
```
- **stats-grid:** 4-column layout (responsive, becomes 2 col on tablet, 1 col on mobile)
- **stat-card:** Individual statistic box with label and value
- **stat-accent:** Colored bar (different color per card via CSS nth-child)
- **JavaScript files loaded at end:** Ensures HTML renders before script execution

---

### departments.html, courses.html, students.html - Management Pages

**Purpose:** CRUD (Create, Read, Update, Delete) pages for each entity

Similar structure to dashboard with:
- **Form section:** Input fields to add new item
- **Table section:** Displays existing items
- **Sort button:** Calls appropriate sorting algorithm
- **Message div:** Shows success/error feedback

```html
<form class="form-grid" id="departmentForm">
    <div class="field">
        <label for="departmentId">Department ID</label>
        <input id="departmentId" name="id" type="number" min="1" required>
    </div>
    <div class="field">
        <label for="departmentName">Department Name</label>
        <input id="departmentName" name="name" type="text" required>
    </div>
    <div class="button-row">
        <button class="btn" type="submit">Add Department</button>
    </div>
    <p class="message" id="departmentMessage"></p>
</form>
```
- **Form grid:** Responsive form layout
- **Required attribute:** Browser prevents form submission if empty
- **min="1":** Number input enforces minimum value
- **id attributes:** Used by JavaScript to extract values

```html
<section class="table-panel">
    <div class="table-header">
        <h2>All Departments</h2>
        <button class="btn secondary" id="sortDepartments" type="button">Sort by Name</button>
    </div>
    <div class="table-wrap">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                </tr>
            </thead>
            <tbody id="departmentsTable"></tbody>
        </table>
    </div>
</section>
```
- **table-wrap:** Horizontal scroll container for responsive tables
- **thead:** Table header row
- **tbody:** Empty initially, populated by JavaScript from API
- **Sort button:** Secondary styling (different from primary btn)

---

### search.html - Search/Query Page

```html
<section class="search-panel">
    <h2>Search Department</h2>
    <form class="form-grid" id="departmentSearchForm">
        <div class="field">
            <label for="searchDepartmentName">Department Name</label>
            <input id="searchDepartmentName" name="name" type="text" required>
        </div>
        <div class="button-row">
            <button class="btn" type="submit">Search Department</button>
        </div>
    </form>
    <div class="result-box" id="departmentResult">
        <p>Department results appear here.</p>
    </div>
</section>
```
- **Two search panels:** Side-by-side (department and student)
- **result-box:** Displays search results (binary search)
- **Placeholder text:** Shown until results loaded

---

## CSS Styling (styles.css)

**Purpose:** Global styling with dark mode support and responsive design

### Color System with CSS Variables

```css
:root,
html[data-theme="light"] {
    --ink: #1a1d23;
    --ink-secondary: #4a5568;
    --muted: #6b7280;
    --line: #e5e7eb;
    --line-soft: #f3f4f6;
    --surface: #ffffff;
    --surface-soft: #f9fafb;
    --surface-hover: #f3f4f6;
    --sidebar: #1f2937;
    --sidebar-text: #f3f4f6;
    --sidebar-active: #fbbf24;
    --teal: #14b8a6;
    --teal-light: rgba(20, 184, 166, 0.1);
    --coral: #ef4444;
    --indigo: #6366f1;
    --success: #10b981;
    --danger: #dc2626;
    --warning: #f59e0b;
    --shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
    --shadow-lg: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
    --shadow-xl: 0 20px 25px -5px rgba(0, 0, 0, 0.1);
}

html[data-theme="dark"] {
    --ink: #f1f5f9;
    --muted: #94a3b8;
    --line: #334155;
    --surface: #0f172a;
    --surface-soft: #1e293b;
    --surface-hover: #334155;
    --sidebar: #0f172a;
    --sidebar-text: #e2e8f0;
    --teal: #2dd4bf;
    --teal-light: rgba(45, 212, 191, 0.1);
    --coral: #ff6b6b;
    --indigo: #818cf8;
    --success: #4ade80;
    --danger: #ff6b6b;
    --warning: #fbbf24;
    --shadow: 0 1px 3px rgba(0, 0, 0, 0.5);
    --shadow-lg: 0 10px 15px -3px rgba(0, 0, 0, 0.5);
    --shadow-xl: 0 20px 25px -5px rgba(0, 0, 0, 0.5);
}
```
- **:root:** Default (light) theme colors
- **html[data-theme="light"]:** Explicit light theme selector
- **html[data-theme="dark"]:** Dark theme colors
- **Color naming:**
  - --ink: Text color (dark in light mode, light in dark mode)
  - --surface: Main background
  - --line: Border colors
  - --muted: Secondary/disabled text
  - --sidebar-*: Navigation bar specific
  - --shadow-*: Elevation levels

### Layout System

```css
.app-shell {
    display: grid;
    grid-template-columns: 268px minmax(0, 1fr);
    min-height: 100vh;
    transition: grid-template-columns 180ms ease;
}

.sidebar-collapsed .app-shell {
    grid-template-columns: 88px minmax(0, 1fr);
}
```
- **Grid layout:** Two columns - sidebar + main content
- **268px:** Sidebar width (normal)
- **minmax(0, 1fr):** Content takes remaining space, minimum 0
- **Collapsed state:** Sidebar reduces to 88px (icons only)
- **Transition:** Smooth animation over 180ms

### Interactive Elements

```css
.btn {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    min-height: 42px;
    border: 0;
    border-radius: 8px;
    padding: 0 16px;
    color: #ffffff;
    background: var(--teal);
    cursor: pointer;
    font-weight: 800;
    transition: all 200ms ease;
    box-shadow: var(--shadow);
}

.btn:hover {
    transform: translateY(-2px);
    box-shadow: var(--shadow-lg);
    filter: brightness(1.05);
}

.btn:active {
    transform: translateY(0);
}
```
- **inline-flex:** Align content within button
- **min-height:** Minimum clickable area (accessibility)
- **border-radius:** Rounded corners
- **Hover state:** Lifts button (translateY) and brightens
- **Active state:** Returns to normal (pressed effect)
- **Smooth transition:** 200ms for all properties

### Form Styling

```css
.field input {
    width: 100%;
    min-height: 43px;
    border: 1px solid var(--line);
    border-radius: 8px;
    padding: 0 12px;
    background: var(--surface);
    color: var(--ink);
    font-size: 0.95rem;
    transition: all 200ms ease;
}

.field input:focus {
    outline: 0;
    border-color: var(--teal);
    box-shadow: 0 0 0 3px var(--teal-light);
}
```
- **Full width:** Expands to fill container
- **Focus state:** Blue border with glow effect
- **Box-shadow:** Teal light color (10% opacity) creates halo

### Dark Mode Implementation

Every property uses CSS variables:
- When `data-theme="dark"` applied to `<html>`
- All CSS variables update automatically
- No JavaScript color changes needed
- Instant theme switch on button click

---

## JavaScript Implementation

### app.js - Shared Utilities

**Purpose:** Common functionality used across all pages

```javascript
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
```
- **api object:** Wrapper around Fetch API
- **get method:** Makes GET request, returns parsed data
- **post method:** Makes POST request with JSON body
- **async/await:** Modern promise handling
- **fetch():** Browser's HTTP client (no jQuery needed)
- **JSON.stringify():** Converts object to JSON string

```javascript
async function readResponse(response) {
    const data = await response.json();

    if (!response.ok) {
        throw new Error(data.message || "Request failed.");
    }

    return data;
}
```
- **response.ok:** True if HTTP status 200-299
- **Throws error:** If response.ok is false
- **Error message:** From server or generic fallback
- **Allows try-catch:** In calling code

```javascript
function setMessage(id, text, type = "") {
    const element = document.getElementById(id);
    if (!element) {
        return;
    }

    element.textContent = text;
    element.className = `message ${type}`.trim();
}
```
- **setMessage():** Display feedback message to user
- **id:** Element to update
- **text:** Message content
- **type:** "success" or "error" (CSS class)
- **Template literal:** Builds className string

```javascript
function formatNumber(value) {
    return new Intl.NumberFormat().format(value ?? 0);
}
```
- **Intl.NumberFormat:** Browser's locale-aware formatter
- **??:** Nullish coalescing (default to 0 if null/undefined)
- **Result:** 1000 → "1,000", 1000000 → "1,000,000"

```javascript
function setupTheme() {
    const themeToggle = document.querySelector(".theme-toggle");
    const savedTheme = localStorage.getItem("college-theme") || "light";

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
```
- **localStorage:** Browser persistent storage (survives page reload)
- **.getAttribute("data-theme"):** Read current theme
- **.setAttribute("data-theme", nextTheme):** Apply new theme (triggers CSS variables)
- **Event listener:** Handles theme toggle button click

```javascript
function updateThemeToggle(themeToggle, theme) {
    themeToggle.setAttribute("aria-pressed", String(theme === "dark"));
    themeToggle.setAttribute("aria-label", theme === "dark" ? "Switch to light mode" : "Switch to dark mode");
    themeToggle.querySelector(".theme-toggle-text").textContent = theme === "dark" ? "☀️ Light" : "🌙 Dark";
}
```
- **aria-pressed:** Accessibility attribute (screen readers)
- **aria-label:** Descriptive label
- **textContent:** Updates button text with emoji

```javascript
function setupNavigation() {
    const page = document.body.dataset.page;
    document.querySelectorAll(".nav-link").forEach((link) => {
        if (link.dataset.page === page) {
            link.classList.add("active");
        }
    });
    // ... sidebar toggle logic ...
}
```
- **data-page:** Matches nav links to current page
- **classList.add("active"):** Highlights current page link
- **querySelectorAll:** Gets all nav links

```javascript
function renderRows(tableBodyId, rows, renderer) {
    const tbody = document.getElementById(tableBodyId);
    tbody.innerHTML = rows.map(renderer).join("");
}
```
- **renderRows():** Universal table rendering
- **renderer:** Function converting each row to HTML
- **.map():** Applies function to each element
- **.join():** Combines array into single string
- **innerHTML:** Inserts as HTML (from safe sources only)

```javascript
document.addEventListener("DOMContentLoaded", () => {
    setupTheme();
    setupNavigation();
});
```
- **DOMContentLoaded:** Fires when page HTML fully parsed
- Runs initialization on every page

---

### dashboard.js - Dashboard Logic

```javascript
async function loadDashboard() {
    const stats = await api.get("/api/dashboard");
    document.getElementById("departmentsCount").textContent = formatNumber(stats.departments);
    document.getElementById("coursesCount").textContent = formatNumber(stats.courses);
    document.getElementById("studentsCount").textContent = formatNumber(stats.students);
    document.getElementById("enrollmentsCount").textContent = formatNumber(stats.totalEnrollments);
    document.getElementById("averageGpa").textContent = stats.averageGpa.toFixed(2);
```
- **Fetches dashboard stats** from /api/dashboard
- **Updates stat cards** with formatted numbers
- **toFixed(2):** Formats GPA to 2 decimal places (3.8 → "3.80")

```javascript
    const students = await api.get("/api/students?sorted=true");
    const topStudent = students[0];
    document.getElementById("topStudent").textContent = topStudent ? topStudent.name : "No students yet";
    document.getElementById("topStudentMeta").textContent = topStudent
        ? `GPA ${topStudent.gpa.toFixed(2)} | Year ${topStudent.year}`
        : "Add students to see performance insights.";
}
```
- **Fetches sorted students** (highest GPA first)
- **[0]:** Gets first (top) student
- **Ternary operator:** Shows name if exists, fallback message if not
- **Template literal:** Formats student info display

---

### departments.js, courses.js, students.js - Entity Pages

**Pattern for all three (identical structure):**

```javascript
async function loadDepartments(sorted = false) {
    const departments = await api.get(`/api/departments?sorted=${sorted}`);
    renderRows("departmentsTable", departments, (department) => `
        <tr>
            <td><span class="pill">${department.id}</span></td>
            <td>${department.name}</td>
        </tr>
    `);
}
```
- **Fetches departments** from API
- **sorted parameter:** Toggles between normal and sorted
- **Template literal:** Creates table row HTML
- **pill class:** ID styled as badge

```javascript
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
```
- **preventDefault():** Stop form submission
- **Event delegation:** Extract form data from element
- **try-catch:** Handles API success or error
- **form.reset():** Clears inputs after success
- **loadDepartments():** Refreshes table with new data
- **Sort handler:** Reloads with sorted=true parameter

```javascript
document.addEventListener("DOMContentLoaded", () => {
    setupDepartmentForm();
    loadDepartments();
});
```
- **Setup and initial load** when page ready

---

### search.js - Search Functionality

```javascript
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
```
- **Result formatters:** Display search results
- **Template literals:** Build result HTML

```javascript
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
```
- **Department search:** Text input, binary search by name
- **Student search:** Numeric input, binary search by ID
- **encodeURIComponent():** Safely encodes special characters in URL
- **Error handling:** Displays error message in result box

---

# Data Flow

## Complete Request/Response Cycle: Adding a Department

### 1. User Action (Frontend)
```
User fills form:
- ID: 40
- Name: "Law"
Clicks "Add Department" button
```

### 2. Form Submission (departments.js)
```javascript
event.preventDefault();  // Stop page reload
const department = {
    id: Number(form.id.value),      // 40
    name: form.name.value           // "Law"
};
await api.post("/api/departments", department);
```

### 3. HTTP POST Request
```
POST http://localhost:8080/api/departments
Content-Type: application/json

{
    "id": 40,
    "name": "Law"
}
```

### 4. Spring Boot Receives (CollegeApiController)
```java
@PostMapping("/departments")
public ResponseEntity<?> addDepartment(@RequestBody Department department)
```
- Jackson deserializes JSON to Department object
- `department.id = 40`, `department.name = "Law"`

### 5. Validation (CollegeApiController)
```java
if (department.getId() <= 0 || isBlank(department.getName())) {
    return badRequest("Department ID must be positive...");
}
// Check if ID exists
if (dataService.departmentIdExists(40)) {
    return badRequest("Department ID already exists!");
}
```
- All validations pass, continue

### 6. Business Logic (CollegeDataService)
```java
department.setName(department.getName().trim());  // Whitespace cleanup
return dataService.addDepartment(department);
```

### 7. Data Persistence (ArrayList)
```java
departments.add(new Department(40, "Law"));
// departments now contains: [10:"CS", 20:"Business", 30:"Engineering", 40:"Law"]
```

### 8. HTTP Response
```
HTTP 201 Created
Content-Type: application/json

{
    "id": 40,
    "name": "Law"
}
```

### 9. Frontend Handles Response (departments.js)
```javascript
try {
    await api.post("/api/departments", department);
    form.reset();                    // Clear inputs
    setMessage(..., "success");      // Show "✓ Added!"
    await loadDepartments();         // Refresh table
} catch (error) {
    setMessage(..., error.message, "error");  // Show error
}
```

### 10. Table Update (departments.js)
```javascript
async function loadDepartments(sorted = false) {
    const departments = await api.get(`/api/departments?sorted=${false}`);
    // GET http://localhost:8080/api/departments?sorted=false
    
    renderRows("departmentsTable", departments, (department) => `
        <tr>
            <td><span class="pill">${department.id}</span></td>
            <td>${department.name}</td>
        </tr>
    `);
}
```

### 11. User Sees Updated Table
New row appears: "40 | Law"

---

## Sorting Flow: Clicking Sort Button

```
User clicks "Sort by Name"
↓
loadDepartments(true)  // Pass sorted=true
↓
GET /api/departments?sorted=true
↓
CollegeApiController calls getDepartmentsSortedByName()
↓
CollegeDataService creates copy and calls SortAlgorithm.bubbleSortDepartments()
↓
Departments alphabetically sorted: [10:"Business", 20:"CS", 30:"Engineering", ...]
↓
Returns sorted list as JSON
↓
Frontend receives and renders table
↓
User sees departments sorted A-Z by name
```

---

## Search Flow: Binary Search

```
User enters "Computer Science"
Clicks "Search Department"
↓
setupSearchForms() prevents default, extracts name
↓
GET /api/departments/search?name=Computer Science
↓
CollegeApiController.searchDepartment() validates input
↓
Calls CollegeDataService.findDepartmentByName()
↓
SearchingAlgorithm.searchDepartmentByName():
  - Sorts departments by name alphabetically
  - Binary search (O(log n)):
    - Mid = "Engineering", doesn't match
    - Go left (alphabetically before)
    - Mid = "Business", doesn't match
    - Go right
    - Mid = "Computer Science", MATCH!
  - Returns Department object
↓
Optional.of() wraps result
↓
ResponseEntity.ok() returns with HTTP 200
↓
Frontend receives result, renders in result-box
↓
User sees: "Computer Science | Department ID: 10"
```

---

# Technology Stack Summary

| Layer | Technology | Version | Purpose |
|-------|-----------|---------|---------|
| **Frontend** | HTML5 | - | Page structure & semantics |
| | CSS3 | - | Styling, dark mode, responsive |
| | JavaScript | ES2020+ | Interactivity, API calls |
| **Backend** | Java | 17 | Core language |
| | Spring Boot | 3.3.5 | Framework, REST API |
| | Spring Web | - | MVC, REST support |
| | Tomcat | Embedded | Web server |
| **Algorithms** | Java Collections | - | ArrayList for storage |
| | Binary Search | - | O(log n) search |
| | Bubble Sort | - | O(n²) department sorting |
| | Selection Sort | - | O(n²) course sorting |
| | Insertion Sort | - | O(n²) student sorting |
| **Build** | Maven | 3.x | Dependency management |
| | Jackson | - | JSON serialization |
| **Data** | In-Memory | - | ArrayList (no database) |

---

# Key Features

1. **Full-Stack Demo:** Console app + REST API + Web frontend, all using same algorithms
2. **Algorithm Showcase:** Real-world integration of search and sort algorithms
3. **Responsive Design:** Works seamlessly on desktop, tablet, mobile
4. **Dark Mode:** Full theme support with localStorage persistence
5. **REST API:** Clean, RESTful endpoints following conventions
6. **Input Validation:** Both frontend (HTML5) and backend (Java) validation
7. **Error Handling:** User-friendly error messages
8. **Performance:** Binary search O(log n), sorting algorithms O(n²)
9. **Accessibility:** ARIA labels, semantic HTML, keyboard navigation
10. **Modern Stack:** Java 17, Spring Boot 3.3.5, ES2020+ JavaScript

---

# How to Run

## Backend (Spring Boot)
```bash
cd "CollegeSystem Algorithm"
mvn clean install
mvn spring-boot:run
```
Server starts on http://localhost:8080

## Frontend
- Navigate to http://localhost:8080/index.html
- All pages accessible from dashboard

## Console App (Optional)
```bash
cd "CollegeSystem Algorithm"
javac src/com/college/*.java
java -cp src com.college.Main
```

---

# File Sizes & Metrics

| File | Lines | Type | Purpose |
|------|-------|------|---------|
| pom.xml | 37 | Config | Build & dependencies |
| Main.java | 200+ | App | Console interface |
| Student.java | 40 | Model | Student data |
| Course.java | 38 | Model | Course data |
| Department.java | 34 | Model | Department data |
| SearchingAlgorithm.java | 70 | Algorithm | Binary search |
| SortAlgorithm.java | 80 | Algorithm | Sorting algorithms |
| CollegeSystemApplication.java | 8 | Boot | Application entry |
| CollegeDataService.java | 120+ | Service | Business logic |
| CollegeApiController.java | 150+ | Controller | REST endpoints |
| DashboardStats.java | 7 | Record | Data transfer |
| styles.css | 600+ | Styling | Full UI styling |
| app.js | 150+ | Utility | Shared functions |
| dashboard.js | 30 | Logic | Dashboard page |
| departments.js | 40 | Logic | Department page |
| courses.js | 40 | Logic | Course page |
| students.js | 40 | Logic | Student page |
| search.js | 60 | Logic | Search functionality |

---

This comprehensive documentation covers every single file and every single code block in the entire College Management System project!
