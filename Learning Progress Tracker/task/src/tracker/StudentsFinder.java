package tracker;

import java.util.List;
import java.util.Scanner;

public class StudentsFinder implements Executor{
    @Override
    public void execute(List<Student> listOfStudents) {
        int JAVA = 0;
        int DSA = 1;
        int DATABASE = 2;
        int SPRING = 3;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an id or 'back' to return:");
        String idToFind;
        boolean isFound;
        do {
            idToFind = scanner.nextLine();
            isFound = false;
            if (idToFind.equals("back")) {
                break;
            }
            int idFound = Integer.parseInt(idToFind);
            for (Student foundStudent : listOfStudents) {
                if (foundStudent.getId() == idFound) {
                    isFound = true;
                    System.out.printf("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n", idFound,
                            foundStudent.getStudentsCredits().get(JAVA),
                            foundStudent.getStudentsCredits().get(DSA),
                            foundStudent.getStudentsCredits().get(DATABASE),
                            foundStudent.getStudentsCredits().get(SPRING));
                }
            }
            if (!isFound) {
                System.out.printf("No student is found for id=%d", idFound);
            }
        } while (true);
    }
}
