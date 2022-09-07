package tracker;

import tracker.comparators.ActivityComparator;
import tracker.comparators.DifficultyComparator;
import tracker.comparators.PopularityComparator;
import tracker.enums.Category;

import java.util.*;

import static tracker.enums.Courses.*;

public class Statistics implements Executor {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void execute(List<Student> listOfStudents) {
        if (checkNA(listOfStudents)) {
            System.out.println("Type the name of a course to see details or 'back' to quit:");

            System.out.println("Most popular: n/a\n" +
                    "Least popular:n/a\n" +
                    "Highest activity: n/a\n" +
                    "Lowest activity: n/a\n" +
                    "Easiest course: n/a\n" +
                    "Hardest course: n/a\n");
        } else {
            List<String> popularity = getListByComparator(new PopularityComparator(listOfStudents), Category.POPULARITY);
            List<String> difficulty = getListByComparator(new DifficultyComparator(listOfStudents), Category.DIFFICULTY);
            List<String> activity = getListByComparator(new ActivityComparator(listOfStudents), Category.ACTIVITY);
            System.out.println("Type the name of a course to see details or 'back' to quit:");
            System.out.printf(
                    "Most popular: %s\n" +
                            "Least popular: %s\n" +
                            "Highest activity: %s\n" +
                            "Lowest activity: %s\n" +
                            "Easiest course: %s\n" +
                            "Hardest course: %s\n",
                    popularity.get(0),
                    popularity.get(1),
                    activity.get(0),
                    activity.get(1),
                    difficulty.get(0),
                    difficulty.get(1)
            );

        }
        //do loop
        do {
            String course = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (course.equals("back")) {
                break;
            }
            switch (course) {
                case "java":
                    System.out.println("Java");
                    JAVA.bestStudents(listOfStudents);
                    break;
                case "dsa":
                    System.out.println("DSA");
                    DSA.bestStudents(listOfStudents);
                    break;
                case "databases":
                    System.out.println("Databases");
                    DB.bestStudents(listOfStudents);
                    break;
                case "spring":
                    System.out.println("Spring");
                    SPRING.bestStudents(listOfStudents);
                    break;
                default:
                    System.out.println("Unknown course.");
            }
        } while (true);
    }
}