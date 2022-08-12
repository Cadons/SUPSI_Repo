package matteo_cadoni.serie07.es1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import matteo_cadoni.serie07.es1.person.Person;
import matteo_cadoni.serie07.es1.person.PersonFactory;
import matteo_cadoni.serie07.es1.person.Worker;
import matteo_cadoni.serie07.es1.person.Student;

public class S7E1 {
    @FunctionalInterface
    private static interface CategorizeOperation<K> {

        K getCategory(Person p);
    }

    @FunctionalInterface
    private static interface EvaluateOperation {

        boolean evaluate(Person p);
    }

    private static List<Person> search(List<Person> population, EvaluateOperation op) {
        List<Person> tmp = new ArrayList<>();
        for (Person e : population) {
            if (op.evaluate(e))
                tmp.add(e);
        }
        return tmp;
    }

    private static Map<String, List<Person>> categorizeString(List<Person> population, CategorizeOperation<String> op) {
        Map<String, List<Person>> tmp = new HashMap<>();

        for (Person e : population) {
            String t = op.getCategory(e);
            if (!tmp.containsKey(t)) {
                tmp.put(t, new ArrayList<>());
            }
            tmp.get(t).add(e);

        }
        return tmp;
    }

    private static Map<Integer, List<Person>> categorizeInteger(List<Person> population, CategorizeOperation<Integer> op) {
        Map<Integer, List<Person>> tmp = new HashMap<>();

        for (Person e : population) {
            int t = op.getCategory(e);
            if (!tmp.containsKey(t)) {
                tmp.put(t, new ArrayList<>());
            }
            tmp.get(t).add(e);

        }
        return tmp;
    }

    public static void main(String[] args) {
        List<Person> population = init(100);

        // Categorize population by surname
		/*final CategorizeOperation<String> categorizeBySurname = new CategorizeOperation<String>() {

			@Override
			public String getCategory(Person p) {
				return p.getSurname();
			}
		};*/
        Map<String, List<Person>> categorizedBySurname = new HashMap<>(categorizeString(population, (p) -> p.getSurname()));

	/*	final CategorizeOperation<String> categorizeByOccupation = new CategorizeOperation<String>() {

			@Override
			public String getCategory(Person p) {
				if (p instanceof Student)
					return ((Student) p).getEducationalStage().toString();
				if (p instanceof Worker)
					return ((Worker) p).getWorkSector().toString();
				return "RETIRED";
			}
		};*/
        Map<String, List<Person>> categorizedBySurname2 = new HashMap<>(categorizeString(population, (p) -> {
            if (p instanceof Student)
                return ((Student) p).getEducationalStage().toString();
            if (p instanceof Worker)
                return ((Worker) p).getWorkSector().toString();
            return "RETIRED";
        }));

        Map<String, List<Person>> categorizedByOccupation = categorizedBySurname2;

        // Categorize population by Age (in decades)
		/*final CategorizeOperation<Integer> categorizeByAge = new CategorizeOperation<Integer>() {
			@Override
			public Integer getCategory(Person p) {
				return p.getAge() / 10;
			}
		};*/
        Map<Integer, List<Person>> categorizedByAgeDecades1 = new HashMap<>(categorizeInteger(population, (p) -> p.getAge() / 10));

        Map<Integer, List<Person>> categorizedByAgeDecades = categorizedByAgeDecades1;

        // Search for people working in secondary sector having salary between 50k - 80k
		/*final EvaluateOperation evaluateSecondarySalary50k_80k = new EvaluateOperation() {

			@Override
			public boolean evaluate(Person p) {
				if (!(p instanceof Worker))
					return false;
				final Worker worker = (Worker) p;
				return worker.getWorkSector() == Worker.WorkSectorType.SECONDARY && 
						worker.getSalary() >= 50_000 && 
						worker.getSalary() <= 80_000;
			}
		};*/
        List<Person> secondaryWorkers1 = new ArrayList<>(search(population, (p) -> {
            if (!(p instanceof Worker))
                return false;
            final Worker worker = (Worker) p;
            return worker.getWorkSector() == Worker.WorkSectorType.SECONDARY &&
                    worker.getSalary() >= 50_000 &&
                    worker.getSalary() <= 80_000;
        }));

		/*for (Person p3 : population) {
			if (evaluateSecondarySalary50k_80k.evaluate(p3))
				secondaryWorkers1.add(p3);
		}*/
        List<Person> secondaryWorkers = secondaryWorkers1;

        // Search for high school students
        final EvaluateOperation evaluateHighSchoolStudent = new EvaluateOperation() {
            @Override
            public boolean evaluate(Person p) {
                return p instanceof Student &&
                        ((Student) p).getEducationalStage() == Student.EducationalStage.HIGH_SCHOOL;
            }
        };
        List<Person> secondaryWorkers2 = new ArrayList<>(search(population, (p) -> p instanceof Student &&
                ((Student) p).getEducationalStage() == Student.EducationalStage.HIGH_SCHOOL));

		/*for (Person p4 : population) {
			if (evaluateHighSchoolStudent.evaluate(p4))
				secondaryWorkers2.add(p4);
		}*/
        List<Person> highSchoolStudents = secondaryWorkers2;


        // Print results
        printCategorizedString("Population by surname", categorizedBySurname);
        printCategorizedString("Population by occupation", categorizedByOccupation);
        printCategorizedInteger("Population by decades", categorizedByAgeDecades);
        printPersonList("HighSchool students", highSchoolStudents);
        printPersonList("Secondary sector workers with salary (50k - 80k)", secondaryWorkers);
    }

    // Utility methods

    // Generate random dataset
    private static List<Person> init(int populationSize) {
        // Generate random dataset
        List<Person> population = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            population.add(PersonFactory.createRandomPerson());

        // Print all persons
        for (Person p : population)
            System.out.println(p);

        return population;
    }

    private static void printCategorizedString(String title, Map<String, List<Person>> categorized) {
        System.out.println("----------------------------------");
        System.out.println(title);
        System.out.println("----------------------------------");
        for (String surname : categorized.keySet())
            System.out.println(String.format("%s : %d", surname, categorized.get(surname).size()));
    }

    private static void printCategorizedInteger(String title, Map<Integer, List<Person>> categorized) {
        System.out.println("----------------------------------");
        System.out.println(title);
        System.out.println("----------------------------------");
        for (Integer category : categorized.keySet())
            System.out.println(String.format("%d : %d", category, categorized.get(category).size()));
    }

    private static void printPersonList(String title, List<Person> people) {
        System.out.println("----------------------------------");
        System.out.println(String.format("%s [%d]", title, people.size()));
        System.out.println("----------------------------------");
        for (Person p : people)
            System.out.print(p + ", ");
        System.out.println();
    }
}
