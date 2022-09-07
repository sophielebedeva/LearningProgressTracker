package tracker.comparators;

import tracker.Student;
import tracker.enums.Courses;

import java.util.Comparator;
import java.util.List;

public class ActivityComparator implements Comparator<Courses> {
    List<Student> listOfStudents;

    public ActivityComparator(List<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    @Override
    public int compare(Courses o1, Courses o2) {
        return o1.getTasksAmount().compareTo(o2.getTasksAmount());
    }
}
