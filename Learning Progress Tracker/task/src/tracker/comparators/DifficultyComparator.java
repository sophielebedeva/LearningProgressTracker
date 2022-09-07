package tracker.comparators;

import tracker.Student;
import tracker.enums.Courses;

import java.util.Comparator;
import java.util.List;

public class DifficultyComparator implements Comparator<Courses> {
    List<Student> listOfStudents;

    public DifficultyComparator(List<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    @Override
    public int compare(Courses o1, Courses o2) {
        if (o1.getTasksAmount() == 0) return 1;
        if (o2.getTasksAmount() == 0) return -1;
        return Integer.compare(o1.coursePoints(listOfStudents) / o1.getTasksAmount(), o2.coursePoints(listOfStudents) / o2.getTasksAmount());
    }
}
