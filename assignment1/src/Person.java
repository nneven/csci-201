import java.util.*;

public class Person implements Comparable<Person>{
	
	public String firstName;
	public String lastName;
	public String emailAddress;
	public int age;
	public boolean nearCampus;
	public Vector<String> notes;

	public Person(String fn, String ln, String ea, int age, boolean nearCampus, String notes) {
		
	}
	
	public Person(String[] info) {
		
		notes = new Vector<String>();
				
		firstName = info[0];
		lastName = info[1];
		emailAddress = info[2];
		age = Integer.parseInt(info[3]);
		if (info[4].equals("true")) nearCampus = true;
		else nearCampus = false;
		for (int i = 5; i < info.length; i++) {
			notes.add(info[i]);
		}
	}
	
	public void printAll() {
		
		System.out.println();
		System.out.println("Name: " + firstName + " " + lastName);
		System.out.println("Email: " + emailAddress);
		System.out.println("Age: " + age);
		System.out.print("Near Campus: " );
		if (nearCampus) System.out.println("Yes");
		else System.out.println("No");
		System.out.print("Notes: ");
		for (int i = 0; i < notes.size(); i++) {
			System.out.println(notes.get(i));
		}
	}

	public int compareTo(Person p) {
		int fn = firstName.compareTo(p.firstName);
		if (fn != 0) return fn;
		else return lastName.compareTo(p.lastName);
	}
}
