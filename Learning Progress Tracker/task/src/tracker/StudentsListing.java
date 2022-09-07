package tracker;

import java.util.List;

public class StudentsListing implements Executor{
    @Override
    public void execute(List<Student> listOfStudents) {
        if (listOfStudents.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Students:");
            for (Student listOfStudent : listOfStudents) {
                System.out.println(listOfStudent.getId());
            }
        }
    }
}
