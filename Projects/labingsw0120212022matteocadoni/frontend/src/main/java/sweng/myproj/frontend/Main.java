package sweng.myproj.frontend;

import sweng.myproj.backend.model.Student;
import sweng.myproj.backend.model.Course;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Course course1=new Course();
        course1.setName("Software Engineering");
        Student student1=new Student();
        student1.setName("Matteo Cadoni");
        student1.setCourse(course1);
        System.out.println(student1.getName().toLowerCase()+"...."+student1.getCourse().getName().toLowerCase());

        Course course2=new Course();
        course2.setName("Prog2");
        Student student2=new Student();
        student2.setName("Matteo Rossi");
        student2.setCourse(course2);
        System.out.println(student2.getName().toLowerCase()+"...."+student2.getCourse().getName().toLowerCase());

        Course course3=new Course();
        course3.setName("Software Engineering 3");
        Student student3=new Student();
        student3.setName("Matteo Verdi");
        student3.setCourse(course3);
        System.out.println(student3.getName().toLowerCase()+"...."+student3.getCourse().getName().toLowerCase());
    }
}
