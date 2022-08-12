package sweng.myproj.frontend;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sweng.myproj.backend.model.*;
import javafx.scene.control.ListView;
import sweng.myproj.backend.view.PrinterGUI;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ClientFx extends Application {

    private final ArrayList<Student> students = new ArrayList<>();
    private final ArrayList<Course> courses = new ArrayList<>();
    private final PrinterGUI myPrinter = new PrinterGUI();

    public static final ObservableList<Course> observableCourses = FXCollections.observableArrayList();
    public static final ObservableList<Student> observableStudents = FXCollections.observableArrayList();
    private final ListView coursesView = new ListView();
    private final ListView studentView = new ListView();

    private ArrayList<Student> getStudents(Course course) {
        return (ArrayList<Student>) students.stream().filter(student -> student.getCourse().getName().equals(course.getName())).collect(Collectors.toList());
    }


    private void loadTestData() {
        Course course1 = new Course();
        course1.setName("software engineering 1");
        Student student1 = new Student();
        student1.setName("matteo cadoni");
        student1.setCourse(course1);
        courses.add(course1);
        students.add(student1);

        Course course2 = new Course();
        course2.setName("coding 2");
        Student student2 = new Student();
        student2.setName("matteo rossi");
        student2.setCourse(course2);
        courses.add(course2);
        students.add(student2);

        Course course3 = new Course();
        course3.setName("software engineering 3");
        Student student3 = new Student();
        student3.setName("matteo verdi");
        student3.setCourse(course3);
        Student student4 = new Student();
        student4.setName("mario Rossi");
        student4.setCourse(course3);
        courses.add(course3);
        students.add(student3);
        students.add(student4);

        observableCourses.addAll(courses);
        for (Course p : observableCourses
        ) {
            p.setName(myPrinter.print(p));
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Students-Course");
        loadTestData();
        coursesView.setItems(observableCourses);
        coursesView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Course>) (observable, oldValue, newValue) -> {
            studentView.getItems().clear();
            observableStudents.addAll(getStudents(newValue));
            for (Student p : observableStudents
            ) {
                p.setName(myPrinter.print(p));
            }
            studentView.setItems(observableStudents);


        });

        HBox root = new HBox();
        root.getChildren().add(coursesView);
        root.getChildren().add(studentView);
        root.setAlignment(Pos.TOP_CENTER);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 500, 250));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
