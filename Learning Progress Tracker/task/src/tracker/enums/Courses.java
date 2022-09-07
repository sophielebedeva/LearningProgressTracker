package tracker.enums;

import tracker.BestStudent;
import tracker.Student;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public enum Courses {
    JAVA(0, "Java", 600),
    DSA(1, "DSA", 400),
    DB(2, "Databases", 480),
    SPRING(3, "Spring", 550);
    private String name;

    public String getName() {
        return name;
    }

    private final Integer index;
    private Set<Student> enrolledStudents = new HashSet<>();
    private final Integer pointsToComplete;

    public Integer getPointsToComplete() {
        return pointsToComplete;
    }

    private int coursePoints = 0;

    public Integer getTasksAmount() {
        return tasksAmount;
    }

    private Integer tasksAmount = 0;
    private Integer points = 0;

    Courses(int index, String name, int pointsToComplete) {
        this.index = index;
        this.name = name;
        this.pointsToComplete = pointsToComplete;
    }

    public Integer enrolledStudents(List<Student> listOfStudents) {
        int studentsAmount = 0;
        for (Student student : listOfStudents) {
            if (student.getStudentsCredits().get(index) > 0) {
                studentsAmount++;
            }
        }
        return studentsAmount;
    }

    public Integer coursePoints(List<Student> listOfStudents) {
        int pointsAmount = 0;
        for (Student student : listOfStudents) {
            pointsAmount += student.getStudentsCredits().get(index);
        }
        return pointsAmount;
    }

    public double getNumberToCompare(Category category) {
        switch (category) {
            case ACTIVITY:
                return this.getTasksAmount();
            case POPULARITY:
                return this.enrolledStudents.size();
            default:
                return this.coursePoints / this.tasksAmount;
        }
    }

    public void addTask() {
        this.tasksAmount += 1;
    }

    public void addPoints(int newPoints) {
        this.coursePoints += newPoints;
    }

    public static List<String> getListByComparator(Comparator<Courses> comparator, Category category) {
        List<Courses> sortedCourses = Arrays.stream(Courses.values())
                .sorted(comparator)
                .collect(Collectors.toList());
        List<Courses> most = new ArrayList<>();
        List<Courses> least = new ArrayList<>();
        List<String> popularityList = new ArrayList<>();
        int sameCourses = 0;

            most.add(sortedCourses.get(0));
            for (int i = 1; i < Courses.values().length; i++) {
                if (sortedCourses.get(i).getNumberToCompare(category) == sortedCourses.get(0).getNumberToCompare(category)) {
                    most.add(sortedCourses.get(i));
                }
            }
            sameCourses = most.size();

        popularityList.add(most.stream().map(course -> course.name).collect(Collectors.joining(",")));
        Collections.reverse(sortedCourses);
        if (sameCourses == 4) {
            popularityList.add("n/a");
        } else {
            least.add(sortedCourses.get(0));
            for (int i = 1; i < Courses.values().length; i++) {
                if (sortedCourses.get(i).getNumberToCompare(category) == sortedCourses.get(0).getNumberToCompare(category)) {
                    least.add(sortedCourses.get(i));
                }
            }
            popularityList.add(least.stream().map(course -> course.name).collect(Collectors.joining(",")));
        }
        if (category.equals(Category.DIFFICULTY)) {
            Collections.reverse(popularityList);
        }
        return popularityList;
    }


    public static boolean checkNA(List<Student> studentList) {
        for (Student student : studentList) {
            if (!student.getStudentsCredits().stream().allMatch(o -> o == 0)) {
                return false;
            }
        }
        return true;
    }

    public void bestStudents(List<Student> listOfStudents) {
        List<BestStudent> bestStudents = new ArrayList<>();
        PercentageCalculator pc = new PercentageCalculator();
        for (Student student : listOfStudents) {
            bestStudents.add(new BestStudent(
                    student.getId(),
                    student.getStudentsCredits().get(index),
                    pc.calculatePercentage(student.getStudentsCredits().get(index), pointsToComplete) + "%"));

        }
        bestStudents = bestStudents.stream().filter(bs -> bs.getPoints() > 0)
                .sorted(Comparator.comparing(BestStudent::getPoints)
                        .reversed()
                        .thenComparing(BestStudent::getId))
                .collect(Collectors.toList());
        System.out.println("id        points    completed");
        bestStudents.forEach(bs -> System.out.format("%d" + "%8d" + "%12s" +"\n", bs.getId(), bs.getPoints(), bs.getCourseCompletion()));
    }
}
class PercentageCalculator {
    private static final DecimalFormat df = new DecimalFormat("0.0");
    String calculatePercentage(double obtained, double total) {
        return df.format(Math.round(obtained * 10000.0 / total /10.0)/10.0);
    }
}

