package tracker;

import tracker.enums.Courses;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static tracker.enums.Courses.*;

public class PointsAdding implements Executor {
    @Override
    public void execute(List<Student> listOfStudents) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an id and points or 'back' to return:");
        String pointsInput;
        String pointsRegex = "^[0-9]\\d*$";
        do {
            pointsInput = scanner.nextLine();
            if (pointsInput.equals("back")) {
                break;
            }
            String[] pointsInputArray = pointsInput.split(" ");

            if (pointsInputArray.length != 5) {
                System.out.println("Incorrect points format.");
                continue;
            }
            try {
                Integer.parseInt(pointsInputArray[0]);
            } catch (Exception e) {
                System.out.println("No student is found for id=" + pointsInputArray[0]);
                continue;
            }
            int studentId = Integer.parseInt(pointsInputArray[0]);
            String javaPoints = pointsInputArray[1];
            String dsaPoints = pointsInputArray[2];
            String dbPoints = pointsInputArray[3];
            String springPoints = pointsInputArray[4];

            if (!Pattern.matches(pointsRegex, javaPoints) || !Pattern.matches(pointsRegex, dsaPoints) || !Pattern.matches(pointsRegex, dbPoints) || !Pattern.matches(pointsRegex, springPoints)) {
                System.out.println("Incorrect points format.");
            } else {
                for (Student student : listOfStudents) {
                    if (student.getId() == studentId) {
                        List<Integer> points = student.getStudentsCredits();
                        points.set(0, points.get(0) + Integer.parseInt(javaPoints));
                        points.set(1, points.get(1) + Integer.parseInt(dsaPoints));
                        points.set(2, points.get(2) + Integer.parseInt(dbPoints));
                        points.set(3, points.get(3) + Integer.parseInt(springPoints));
                        if (!javaPoints.trim().equals("0")) {
                            JAVA.addTask();
                            JAVA.addPoints(Integer.parseInt(javaPoints));
                        }
                        if (!dsaPoints.trim().equals("0")) {
                            DSA.addTask();
                            DSA.addPoints(Integer.parseInt(dsaPoints));
                        }
                        if (!dbPoints.trim().equals("0")) {
                            DB.addTask();
                            DB.addPoints(Integer.parseInt(dbPoints));
                        }
                        if (!springPoints.trim().equals("0")) {
                            SPRING.addTask();
                            SPRING.addPoints(Integer.parseInt(springPoints));
                        }
                    }
                }
                System.out.println("Points updated.");
            }
        }
        while (true);
    }
}
