import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private int studentId;
    private String name;
    private List<Course> courses;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void enrollCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            course.addStudent(this);
            System.out.println(name + " has enrolled in " + course.getCourseName());
        } else {
            System.out.println(name + " is already enrolled in " + course.getCourseName());
        }
    }

    public void dropCourse(Course course) {
        if (courses.contains(course)) {
            courses.remove(course);
            course.removeStudent(this);
            System.out.println(name + " has dropped " + course.getCourseName());
        } else {
            System.out.println(name + " is not enrolled in " + course.getCourseName());
        }
    }

    public void displayEnrolledCourses() {
        if (!courses.isEmpty()) {
            System.out.println(name + "'s enrolled courses:");
            for (Course course : courses) {
                System.out.println(course.getCourseName());
            }
        } else {
            System.out.println(name + " is not enrolled in any courses.");
        }
    }
}

class Course {
    private String courseCode;
    private String courseName;
    private int maxStudents;
    private List<Student> students;

    public Course(String courseCode, String courseName, int maxStudents) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxStudents = maxStudents;
        this.students = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        if (students.size() < maxStudents) {
            students.add(student);
        }
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void displayEnrolledStudents() {
        System.out.println("Students enrolled in " + courseName + ":");
        for (Student student : students) {
            System.out.println(student.getName());
        }
    }
}

public class RegistrationSystem {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        List<Course> courses = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println(" ************** Welcom Student Course Registration System *****************");
            System.out.println("\n Student Course Registration System");
            System.out.println("1. Enroll in a course");
            System.out.println("2. Drop a course");
            System.out.println("3. Display enrolled courses");
            System.out.println("4. Display students in a course");
            System.out.println("5. Quit");
            scanner.nextLine();
            System.out.println(" > ENTER YOU CHOICE :");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    int studentId = scanner.nextInt();
                    System.out.print("Enter course name: ");
                    scanner.nextLine(); // Consume newline
                    String courseName = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();
                    System.out.print("Enter maximum students for the course: ");
                    int maxStudents = scanner.nextInt();

                    Student student = new Student(studentId, studentName);
                    Course course = new Course(courseCode, courseName, maxStudents);

                    students.add(student);
                    courses.add(course);

                    student.enrollCourse(course);
                    break;

                case 2:
                    System.out.print("Enter student ID: ");
                    int studentIDToDrop = scanner.nextInt();
                    System.out.print("Enter course code: ");
                    scanner.nextLine(); // Consume newline
                    String courseCodeToDrop = scanner.nextLine();

                    Student studentToDrop = findStudentById(students, studentIDToDrop);
                    Course courseToDrop = findCourseByCode(courses, courseCodeToDrop);

                    if (studentToDrop != null && courseToDrop != null) {
                        studentToDrop.dropCourse(courseToDrop);
                    } else {
                        System.out.println("Invalid student ID or course code.");
                    }
                    break;

                case 3:
                    System.out.print("Enter student ID: ");
                    int studentIDToDisplay = scanner.nextInt();
                    Student studentToDisplay = findStudentById(students, studentIDToDisplay);
                    if (studentToDisplay != null) {
                        studentToDisplay.displayEnrolledCourses();
                    } else {
                        System.out.println("Invalid student ID.");
                    }
                    break;

                case 4:
                    System.out.print("Enter course code: ");
                    scanner.nextLine(); // Consume newline
                    String courseCodeToDisplay = scanner.nextLine();
                    Course courseToDisplay = findCourseByCode(courses, courseCodeToDisplay);
                    if (courseToDisplay != null) {
                        courseToDisplay.displayEnrolledStudents();
                    } else {
                        System.out.println("Invalid course code.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static Student findStudentById(List<Student> students, int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(List<Course> courses, String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
