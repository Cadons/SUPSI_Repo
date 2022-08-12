package matteo_cadoni.serie08.es1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import matteo_cadoni.serie08.es1.person.PersonFactory;
import matteo_cadoni.serie08.es1.person.Person;
import matteo_cadoni.serie08.es1.person.Student;
import matteo_cadoni.serie08.es1.person.Worker;

public class S8E1 {

	/*private static interface CategorizeOperation<K> {

		K getCategory(Person p);
	}*/

	/*private static interface EvaluateOperation {

		boolean evaluate(Person p);
	}*/

	private static class HelperClass {

		static String categorizeByOccupation(final Person p) {
			if (p instanceof Student)
				return ((Student) p).getEducationalStage().toString();
			if (p instanceof Worker)
				return ((Worker) p).getWorkSector().toString();
			return "RETIRED";
		}
	}

	public static void main(String[] args) {
		List<Person> population = init(100);

		// Categorize population by surname
	//Map<String, List<Person>> categorizedBySurname = categorizeString(population, person -> person.getSurname());
        Map<String, List<Person>> categorizedBySurname=population.stream().collect(Collectors.groupingBy(person -> person.getSurname()));
		// Categorize population by Occupation
		//Map<String, List<Person>> categorizedByOccupation = categorizeString(population, HelperClass::categorizeByOccupation);
        Map<String, List<Person>> categorizedByOccupation =population.stream().collect(Collectors.groupingBy(HelperClass::categorizeByOccupation));
		// Categorize population by Age (in decades)
		//Map<Integer, List<Person>> categorizedByAgeDecades = categorizeInteger(population, person -> person.getAge() / 10);
        Map<Integer, List<Person>> categorizedByAgeDecades= population.stream().collect(Collectors.groupingBy((person -> person.getAge() / 10)));
		// Search for people working in secondary sector having salary between 50k - 80k
        List<Person> secondaryWorkers=population.stream().filter(person ->  {
            if (!(person instanceof Worker))
                return false;
            final Worker worker = (Worker) person;
            return worker.getWorkSector() == Worker.WorkSectorType.SECONDARY &&
                    worker.getSalary() >= 50_000 &&
                    worker.getSalary() <= 80_000;
        }).collect(Collectors.toList());
/*
        List<Person> secondaryWorkers = search(population, person ->  {
				if (!(person instanceof Worker))
					return false;
				final Worker worker = (Worker) person;
				return worker.getWorkSector() == Worker.WorkSectorType.SECONDARY &&
						worker.getSalary() >= 50_000 &&
						worker.getSalary() <= 80_000;
		});
*/
		// Search for high school students
		/*List<Person> highSchoolStudents = search(population, person ->  {
				return person instanceof Student &&
						((Student) person).getEducationalStage() == Student.EducationalStage.HIGH_SCHOOL;
		});*/
        List<Person> highSchoolStudents=population.stream().filter(person ->  {
            return person instanceof Student &&
                    ((Student) person).getEducationalStage() == Student.EducationalStage.HIGH_SCHOOL;
        }).collect(Collectors.toList());

		// Print results
		printCategorizedString("Population by surname", categorizedBySurname);
		printCategorizedString("Population by occupation", categorizedByOccupation);
		printCategorizedInteger("Population by decades", categorizedByAgeDecades);
		printPersonList("HighSchool students", highSchoolStudents);
		printPersonList("Secondary sector workers with salary (50k - 80k)", secondaryWorkers);
	}

	// Utility methods


    // FIXME refactor by replacing method with filter stream operation 
/*	private static List<Person> search(List<Person> population, EvaluateOperation eval) {
		List<Person> secondaryWorkers = new ArrayList<>();
		/*for (Person p : population) {
			if (eval.evaluate(p))
				secondaryWorkers.add(p);
		}
		secondaryWorkers=population.stream().filter(eval::evaluate).collect(Collectors.toList());
		return secondaryWorkers;
	}

	// FIXME refactor by replacing with collect / groupingBy stream operation
	private static Map<Integer, List<Person>> categorizeInteger(List<Person> population,
			CategorizeOperation<Integer> integerCategorizationOperation) {
	/*	Map<Integer, List<Person>> categorizedByAgeDecades = new HashMap<>();
		for (Person p : population) {
			Integer category = integerCategorizationOperation.getCategory(p);
			if (categorizedByAgeDecades.containsKey(category) == false)
				categorizedByAgeDecades.put(category, new ArrayList<>());
			categorizedByAgeDecades.get(category).add(p);
		}
        return population.stream().collect(Collectors.groupingBy((e)->integerCategorizationOperation.getCategory(e)));

	}

    // FIXME refactor by replacing with collect / groupingBy stream operation
	private static Map<String, List<Person>> categorizeString(List<Person> population,
			CategorizeOperation<String> stringCategorizationOperation) {
	/*	Map<String, List<Person>> categorizedBySurname = new HashMap<>();
		for (Person p : population) {
			String category = stringCategorizationOperation.getCategory(p);
			if (categorizedBySurname.containsKey(category) == false)
				categorizedBySurname.put(category, new ArrayList<>());
			categorizedBySurname.get(category).add(p);
		}
		return population.stream().collect(Collectors.groupingBy((e)->stringCategorizationOperation.getCategory(e)));

	}
*/
	// Generate random dataset
	private static List<Person> init(int populationSize) {
		//List<Person> population = new ArrayList<>();
		List<Person> population=Stream.generate(PersonFactory::createRandomPerson).limit(populationSize).peek(System.out::println).collect(Collectors.toList());

		/*for (int i = 0; i < populationSize; i++)
			population.add(PersonFactory.createRandomPerson());
		
		// Print all persons
		for (Person p : population)
			System.out.println(p);
*/
		return population;
	}

	private static void printCategorizedString(String title, Map<String, List<Person>> categorized) {
		System.out.println("----------------------------------");
		System.out.println(title);
		System.out.println("----------------------------------");
	/*	for (Entry<String, List<Person>> entry : categorized.entrySet())
			System.out.println(String.format("%s : %d", entry.getKey(), entry.getValue().size()));*/
        categorized.entrySet().stream().forEach(entry->System.out.println(String.format("%s : %d", entry.getKey(), entry.getValue().size())));
	}

	private static void printCategorizedInteger(String title, Map<Integer, List<Person>> categorized) {
		System.out.println("----------------------------------");
		System.out.println(title);
		System.out.println("----------------------------------");
		/*for (Entry<Integer, List<Person>> entry : categorized.entrySet())
			System.out.println(String.format("%d : %d", entry.getKey(), entry.getValue().size()));*/
        categorized.entrySet().stream().forEach(entry->System.out.println(String.format("%s : %d", entry.getKey(), entry.getValue().size())));

    }

	private static void printPersonList(String title, List<Person> people) {
		System.out.println("----------------------------------");
		System.out.println(String.format("%s [%d]", title, people.size()));
		System.out.println("----------------------------------");
		/*for (Person p : people)
			System.out.print(p + ", ");
		System.out.println();*/
        System.out.println(people.stream().map(element->element.toString()).collect(Collectors.joining(", ")));
	}
}
