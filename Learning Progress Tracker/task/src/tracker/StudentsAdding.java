package tracker;

import java.util.List;
import java.util.Scanner;

public class StudentsAdding implements Executor {
    @Override
    public void execute(List<Student> listOfStudents) {
        String lastNameRegex = "^[A-Za-z]+((\\s)?(['-]?([A-Za-z])+))+?$";
        String firstNameRegex = "^[A-Za-z]+(['-]?([A-Za-z])+)+?$";
        String emailRegex = "[a-zA-Z0-9\\.]+@[a-zA-Z0-9]+\\..+";
        int counter = 0;
        int idCounter = 1000;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student credentials or 'back' to return:");
        String studentToAdd;
        int arrayLength;
        do {
            studentToAdd = scanner.nextLine();
            if (studentToAdd.equals("back")) {
                break;
            }
            try {
                arrayLength = studentToAdd.split(" ").length;
            } catch (StringIndexOutOfBoundsException e) {
                arrayLength = 1;
            }


            if (arrayLength < 3) {
                System.out.println("Incorrect credentials.");
                continue;
            }
            String firstName = studentToAdd.substring(0, studentToAdd.indexOf(" "));
            String lastName = studentToAdd.substring(studentToAdd.indexOf(" ") + 1, studentToAdd.lastIndexOf(" "));
            String email = studentToAdd.substring(studentToAdd.lastIndexOf(" ") + 1);

            if (!firstName.matches(firstNameRegex)) {
                System.out.println("Incorrect first name.");
                continue;
            }
            if (!lastName.matches(lastNameRegex)) {
                System.out.println("Incorrect last name.");
                continue;
            }
            if (!email.matches(emailRegex)) {
                System.out.println("Incorrect email.");
                continue;
            }
            try {
                for (Student student : listOfStudents) {
                    if (email.equals(student.getEmail())) {
                        System.out.println("This email is already taken.");
                        throw new Exception();
                    }
                }
            } catch (Exception e) {
                continue;
            }
            Student student = new Student(firstName, lastName, email, idCounter);
            listOfStudents.add(student);
            idCounter++;
            counter++;
            System.out.println("The student has been added.");
        } while (true);
        System.out.printf("Total %d students have been added.\n", counter);
    }
}
