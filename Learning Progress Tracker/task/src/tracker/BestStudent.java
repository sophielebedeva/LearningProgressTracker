package tracker;

public class BestStudent {
    Integer id;
    Integer points;
    String courseCompletion;

    public BestStudent(Integer id, Integer points, String percent){
        this.id = id;
        this.points = points;
        this.courseCompletion = percent;

    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getCourseCompletion() {
        return courseCompletion;
    }

    public void setCourseCompletion(String courseCompletion) {
        this.courseCompletion = courseCompletion;
    }
}
