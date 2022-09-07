package tracker;

import tracker.enums.Courses;

import java.util.*;

public class Student {
    String name;
    String lastName;
    String email;
    int id;
    List <Integer> studentsCredits;

    public Set<Courses> getNotificatedCourses() {
        return notificatedCourses;
    }

    Set<Courses> notificatedCourses;

    public Student(String name, String lastName, String email, int id) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        this.studentsCredits = Arrays.asList(0,0,0,0);
        notificatedCourses = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getStudentsCredits() {
        return studentsCredits;
    }

    public void setStudentsCredits(List<Integer> studentsCredits) {
        this.studentsCredits = studentsCredits;
    }
}

