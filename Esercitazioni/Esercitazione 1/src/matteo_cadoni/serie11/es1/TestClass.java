package matteo_cadoni.serie11.es1;

public class TestClass {

    public static void main(String[] args) {
        System.out.println(CodeReviewer.analyze(testAnnotation.class));
    }

}
@Improvement(severity = "LOW", description = "to string method")
class testAnnotation
{
    @Error(severity = "HIGH", description = "for cycle broken")
    private void m1()
    {

    }
    @Bug(severity = "NORMAL", description = " infinite loop")
    private void m2()
    {

    }

}
