package matteo_cadoni.serie09.es1;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Payroll {
    private final int amount;

    public Payroll(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Payroll increaseAmount(final float factor) {
        //this.amount= ((int) (this.amount * factor));
      return new Payroll((int) (this.amount * factor));
    }

    @Override
    public String toString() {
        return String.format("Payroll: %6d", amount);
    }
}

class Worker {
    private String name;
    private String surname;
    private Payroll payroll;

    public Worker(String name, String surname, int salary) {
        this.name = name;
        this.surname = surname;
        this.payroll = new Payroll(salary);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Payroll getPayroll() {
        return payroll;
    }

    @Override
    public String toString() {
        return String.format("%s %s, %s", name, surname, payroll);
    }
}

public class WorkerSalaries {


    static double avgIncreasedSalaries(List<Worker> workers, final int threshold, final float factor) {
        ArrayList<Worker> tmp=new ArrayList<>();
        workers.forEach((e)->tmp.add(new Worker(e.getName(),e.getSurname(),e.getPayroll().getAmount())));
       return tmp.stream()
                .filter(e -> e.getPayroll()
                        .getAmount() <= threshold)
                .mapToDouble(e -> e.getPayroll().increaseAmount(factor).getAmount())
                .average().orElse(0);
     //   final List<Payroll> payrollsToPromote = new ArrayList<>();

        // Find payrolls to increase
      /*  for (Worker w : workers) {
            final Payroll payroll = w.getPayroll();
            if (payroll.getAmount() <= threshold) {
                payrollsToPromote.add(payroll);
            }
        }

        // Increase  payrolls by factor
        for (Payroll payroll : payrollsToPromote) {
            payroll.increaseAmount(factor);
        }

        // Compute stats
        float total = 0;
        for (Payroll payroll : payrollsToPromote) {
            total += payroll.getAmount();
        }*/
      //  return total / payrollsToPromote.size();
    }

    public static void main(String[] args) {
        List<Worker> workers = init(10);
        printAll(workers);
        final int threshold = 12000;
        System.out.println(String.format("Increase by %f -> %.2f", 1.03f, avgIncreasedSalaries(workers, threshold, 1.03f)));

        System.out.println(String.format("Increase by %f -> %.2f", 1.05f, avgIncreasedSalaries(workers, threshold, 1.05f)));

        System.out.println(String.format("Increase by %f -> %.2f", 1.01f, avgIncreasedSalaries(workers, threshold, 1.01f)));
        printAll(workers);
    }

    // Utilities

    final static String F_NAMES[] = {"Maria", "Nancy", "Donna", "Laura", "Linda", "Susan", "Karen", "Carol", "Sarah", "Betty", "Helen"};

    final static String M_NAMES[] = {"James", "David", "Kevin", "Jason", "Brian"};

    final static String SURNAMES[] = {"Burke", "Lopez", "Perez", "White", "Jones", "Smith", "Brown",};

    private static String getRandom(final Random r, String data[]) {
        return data[r.nextInt(data.length)];
    }

    private static List<Worker> init(final int populationSize) {
        final Random random = new Random();
        return Stream
                .generate(() -> {
                    final boolean isFemale = random.nextBoolean();
                    final String name = (isFemale) ? getRandom(random, F_NAMES) : getRandom(random, M_NAMES);
                    return new Worker(name, getRandom(random, SURNAMES), 5000 + (random.nextInt(10) * 1000));
                })
                .limit(populationSize)
                .collect(Collectors.toList());
    }

    private static void printAll(List<Worker> workers) {
        System.out.println("-------------------------------");
        workers.forEach(System.out::println);
        System.out.println("-------------------------------");
    }
}
