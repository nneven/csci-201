
public class Person {

	protected String firstName;
	protected String lastName;
	protected String birthdate;
	
	public Person(String fn, String ln, String bd) {
		firstName = fn;
		lastName = ln;
		birthdate = bd;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getBirthdate() {
		return birthdate;
	}
}
