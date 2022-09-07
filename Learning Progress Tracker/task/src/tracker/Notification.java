package tracker;

import tracker.enums.Courses;

import java.util.ArrayList;
import java.util.List;

public class Notification implements Executor {
    @Override
    public void execute(List<Student> listOfStudents) {
        int notifiedStudents = 0;
        List<Courses> listOfCourses = new ArrayList<>();
        listOfCourses.add(Courses.JAVA);
        listOfCourses.add(Courses.DSA);
        listOfCourses.add(Courses.DB);
        listOfCourses.add(Courses.SPRING);
        for (Student student : listOfStudents) {
            boolean wasNotified = false;
            List<Integer> studentsPoints = student.getStudentsCredits();
            for (int i = 0; i < studentsPoints.size(); i++) {
                Courses currentCourse = listOfCourses.get(i);
                if (studentsPoints.get(i) >= currentCourse.getPointsToComplete()) {
                    if (!student.getNotificatedCourses().contains(currentCourse)) {
                        sendNotification(student, currentCourse);
                        wasNotified = true;
                        student.getNotificatedCourses().add(currentCourse);
                    }
                }
            }
            if (wasNotified) {
                notifiedStudents++;
            }
        }
        String pluralOrSingular = "student";
        if (notifiedStudents > 1) {
            pluralOrSingular = "students";
        }
        System.out.printf("Total %d %s have been notified.", notifiedStudents, pluralOrSingular);
    }

    public void sendNotification(Student student, Courses courses) {
        System.out.printf("To: %s\n" +
                        "Re: Your Learning Progress\n" +
                        "Hello, %s %s! You have accomplished our %s course!\n",
                student.getEmail(),
                student.getName(),
                student.getLastName(),
                courses.getName());
    }
}
