package SpringBean;

import Entities.Course;
import Entities.Grade;
import Entities.GradeLetter;
import Entities.Student;

import java.util.ArrayList;

public class CourseManagementBean {

    private Course course;
    private ArrayList<Student> enrolledStudents;
    private ArrayList<Grade> studentsGrades;

    public CourseManagementBean(String courseName, String courseCode) {
        course = new Course(courseName, courseCode);
        enrolledStudents = new ArrayList<>();
        studentsGrades = new ArrayList<>();
    }

    public boolean setCourseMarks(int midterm, int project, int exam) {
        if (midterm + project + exam != 100) return false;

        course.setMidTermFUll(midterm);
        course.setProjectFUll(project);
        course.setExamFUll(exam);

        return true;
    }

    public boolean areCourseMarksSet() {
        return course.getMidTermFUll() + course.getProjectFUll() + course.getExamFUll() == 100;
    }

    public boolean addStudent(String studentName, String studentMetric) {
        if (studentName == null || studentMetric == null || studentName.isBlank() || studentMetric.isBlank())
            return false;

        return enrolledStudents.add(new Student(studentName, studentMetric))
                && studentsGrades.add(new Grade(0, 0, 0));
    }

    public boolean calculateStudentGrade(int index) {
        if (!hasStudentIndex(index) || !areCourseMarksSet()) return false;

        float midtermMark = studentsGrades.get(index).getMidtermMark();
        float projectMark = studentsGrades.get(index).getProjectMark();
        float examMark = studentsGrades.get(index).getExamMark();

        float grade = midtermMark + projectMark + examMark;

        studentsGrades.get(index).setFinalGrade(grade);

        if (grade >= 90)
            studentsGrades.get(index).setGradeLetter(GradeLetter.A_Plus);

        else if (grade >= 85)
            studentsGrades.get(index).setGradeLetter(GradeLetter.A);

        else if (grade >= 80)
            studentsGrades.get(index).setGradeLetter(GradeLetter.A_Minus);

        else if (grade >= 75)
            studentsGrades.get(index).setGradeLetter(GradeLetter.B_Plus);

        else if (grade >= 70)
            studentsGrades.get(index).setGradeLetter(GradeLetter.B);

        else if (grade >= 65)
            studentsGrades.get(index).setGradeLetter(GradeLetter.B_Minus);

        else if (grade >= 60)
            studentsGrades.get(index).setGradeLetter(GradeLetter.C_Plus);

        else if (grade >= 55)
            studentsGrades.get(index).setGradeLetter(GradeLetter.C);

        else if (grade >= 50)
            studentsGrades.get(index).setGradeLetter(GradeLetter.C_Minus);

        else if (grade >= 45)
            studentsGrades.get(index).setGradeLetter(GradeLetter.D);

        else
            studentsGrades.get(index).setGradeLetter(GradeLetter.F);

        return true;
    }

    public Course getCourse() {
        return course;
    }

    public ArrayList<Student> getRegisteredStudents() {
        return enrolledStudents;
    }

    public boolean editStudentMarks(int index, float midterm, float project, float exam) {
        if (index >= enrolledStudents.size() || midterm < 0 || project < 0 || exam < 0) return false;
        if (midterm > course.getMidTermFUll() || project > course.getProjectFUll() || exam > course.getExamFUll())
            return false;

        studentsGrades.get(index).setMidtermMark(midterm);
        studentsGrades.get(index).setProjectMark(project);
        studentsGrades.get(index).setExamMark(exam);

        return true;
    }

    public Grade getStudentGrade(int index) {
        return studentsGrades.get(index);
    }

    public boolean hasStudents() {
        return !enrolledStudents.isEmpty();
    }

    public boolean hasStudentIndex(int index) {
        return (index < enrolledStudents.size()) && (index >= 0);
    }
}
