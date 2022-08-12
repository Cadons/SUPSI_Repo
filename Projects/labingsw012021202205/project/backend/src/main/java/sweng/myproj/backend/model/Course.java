package sweng.myproj.backend.model;

import sweng.myproj.backend.view.Printable;

import java.util.ArrayList;
import java.util.List;

public class Course implements Printable {
    private String name;

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
    public ArrayList<String> getData() {
        return new ArrayList<String>(List.of(new String[]{name}));
    }
}
