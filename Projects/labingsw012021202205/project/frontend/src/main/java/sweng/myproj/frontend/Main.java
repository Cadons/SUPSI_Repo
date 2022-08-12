package sweng.myproj.frontend;




public class Main {
    public static void main(String[] args) {
        Printer myprinter=new Printer();
        Course course1=new Course();

        course1.setName("Software Engineering");
        Student student1=new Student();
        student1.setName("Matteo Cadoni");
        student1.setCourse(course1);
        System.out.println(myprinter.print(student1)+"...."+myprinter.print(student1.getCourse()));

        //defining courses

        Course course2=new Course();
        course2.setName("Prog2");
        Student student2=new Student();
        student2.setName("Matteo Rossi");
        student2.setCourse(course2);
        System.out.println(myprinter.print(student2)+"...."+myprinter.print(student2.getCourse()));

        Course course3=new Course();
        course3.setName("Software Engineering 3");
        Student student3=new Student();
        student3.setName("Matteo Verdi");
        student3.setCourse(course3);
        System.out.println(myprinter.print(student2)+"...."+myprinter.print(student2.getCourse()));
    }
}
