package sweng.myproj.backend.model;

import sweng.myproj.backend.view.Printable;

import java.util.ArrayList;
import java.util.List;

public class Student implements Printable {
    private String name;
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public ArrayList<String> getData() {
        return new ArrayList<String>(List.of(new String[]{name}));
    }
}
