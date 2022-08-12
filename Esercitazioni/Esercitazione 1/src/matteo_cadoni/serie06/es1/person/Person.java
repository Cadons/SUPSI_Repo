package matteo_cadoni.serie06.es1.person;

public class Person {
	public enum Gender {F, M}
	
	private String name;
	private String surname;
	private Gender gender;
	private int age;

	public Person(String name, String surname, Gender gender, int age) {
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public Gender getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return String.format("Person [name=%s, surname=%s, gender=%s, age=%s]", name, surname, gender, age);
	}
}
