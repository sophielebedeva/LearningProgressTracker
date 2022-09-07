package tracker;

import java.util.*;


public class LearningTracker {

    Scanner scanner = new Scanner(System.in);
    List<Student> listOfStudents = new ArrayList<>();

    void printGreetings() {
        System.out.println("Learning Progress Tracker");
    }

    void processInput() {
        String command;
        HashMap<String, Executor> commandsAndExecutors = new HashMap<>();
        commandsAndExecutors.put("add students", new StudentsAdding());
        commandsAndExecutors.put("list", new StudentsListing() );
        commandsAndExecutors.put("add points", new PointsAdding());
        commandsAndExecutors.put("find", new StudentsFinder());
        commandsAndExecutors.put("statistics", new Statistics());
        commandsAndExecutors.put("notify",new Notification());

        do {
            command = scanner.nextLine();
            if (!command.equals("exit")) {
                if (command.equals("back")) {
                    System.out.println("Enter 'exit' to exit the program.");
                    continue;
                }
                if (command.isBlank()) {
                    System.out.println("No input.");
                    continue;
                }
                if (!commandsAndExecutors.containsKey(command)) {
                    System.out.println("Unknown command.");
                    continue;
                }
                commandsAndExecutors.get(command).execute(listOfStudents);
            }
        }
        while (!command.equals("exit"));
        System.out.println("Bye!");
    }

    public List<Student> getListOfStudents() {
        return listOfStudents;
    }

    public void setListOfStudents(List<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }
}
