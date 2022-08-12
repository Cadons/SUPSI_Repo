package matteo_cadoni.serie03.esercizio02;

public class Target {
    @Extract
    public int theAnswer1 = 42;

    @Extract("prova1")
    private String hello = "world";
    @Extract("theAnswer")
    private int anotherAnswer;
}
class Target2 {
    @Extract
    public int theAnswer2 = 42;

    @Extract("prova1")
    private String hello = "world";
    @Extract("theAnswer1")
    private int anotherAnswer;
}
