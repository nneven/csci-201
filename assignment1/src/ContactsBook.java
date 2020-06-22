import java.io.*;
import java.util.*;

public class ContactsBook {

	public static void main(String[] args) throws FileNotFoundException {
		
		Set<Person> contacts = parseFile();
		// System.out.println("Number of contacts: " + contacts.size());
		
		boolean exit = false;
		do {
			
			System.out.println();
			System.out.println("	1) Contact Lookup");
			System.out.println("	2) Add contact");
			System.out.println("	3) Delete contact");
			System.out.println("	4) Print to a file");
			System.out.println("	5) Exit");
			System.out.println();
			
			System.out.print("What option would you like to select? ");
			Scanner input = new Scanner(System.in);
			String choice = input.next();
			System.out.println();
			
			if (choice.equals("1")) {
				boolean complete = false;
				do {
					System.out.print("Enter the contact's last name. ");
					Scanner word = new Scanner(System.in);
					String name = input.next();
					if(search(contacts, name)) complete = true;
					System.out.println();
				} while (!complete);
				
			} else if (choice.equals("2")) {
				Person p = newPerson(contacts);
				contacts.add(p);
				System.out.println(p.firstName + " " + p.lastName + " has been added to your contact list.");
				
			} else if (choice.equals("3")) {
				Person del = null;
				
				boolean exist = false;
				do {
					System.out.print("Enter the email of the contact you would like to delete. ");
					Scanner word = new Scanner(System.in);
					String email = word.nextLine();
					Iterator<Person> itr = contacts.iterator();
					while (itr.hasNext()) {
						Person p = itr.next();
						if (p.emailAddress.equals(email)) {
							exist = true;
							del = p;
						}
					}
					if (!exist) {
						System.out.println(email + " does not exist in your contacts list.");
						System.out.println();
					}
				} while (!exist);
				
				contacts.remove(del);
				System.out.println(del.firstName + " " + del.lastName + " was succesfully deleted from your contact list.");
				
			} else if (choice.equals("4")) {
				System.out.print("Enter the name of the file that you would like to print your contact list to. ");
				Scanner word = new Scanner(System.in);
				String fileName = word.nextLine();
				File outputFile = new File(fileName);
				PrintStream output = new PrintStream(outputFile);
				Iterator<Person> itr = contacts.iterator();
				while (itr.hasNext()) {
					Person p = itr.next();
					output.print(p.firstName + "," + p.lastName + "," + p.emailAddress + "," + p.age + ",");
					if (p.nearCampus) output.print("true,");
					else output.print("false,");
					for (String line : p.notes) {
						output.print("," + line);
					}
					output.println();
				}
				System.out.println("Successfully printed all your contacts to " + fileName);
				
			} else if (choice.equals("5") || choice.equals("EXIT")) {
				exit = true;
			} else {
				System.out.println("That is not a valid option.");
			}
			
		} while (!exit);
		
		System.out.println("Thank you for using my contacts program. Goodbye!");
	}
	
	public static boolean search(Set<Person> contacts, String name) {
		
		Iterator<Person> itr = contacts.iterator();
		
		boolean print = false;
		while (itr.hasNext()) {
			Person p = itr.next();
			if (p.lastName.equals(name)) {
				p.printAll();
				print = true;
			}
		}
		
		if (!print)  {
			System.out.println("There is no one with the last name " + name + " in your contact book.");
			return false;
		} else return true;
	}
	
	public static Person newPerson(Set<Person> contacts) {
		Vector<String> data = new Vector<String>();
		Scanner word = new Scanner(System.in);
		System.out.print("What is the first name of your new contact? ");
		data.add(word.next());
		if (!data.get(0).matches("^[a-zA-Z]*$")) {
			displayError(3, "file", data.get(0), "a first name");
			newPerson(contacts);
		}
		System.out.print("What is the last name of your new contact? ");
		data.add(word.next());
		if (!data.get(1).matches("^[a-zA-Z]*$")) {
			displayError(3, "file", data.get(1), "a last name");
			newPerson(contacts);
		}
		System.out.print("What is the email of your new contact? ");
		data.add(word.next());
		String[] info = data.get(2).split("@", 0);
		if(!info[0].matches("[a-zA-Z]*$")) {
			displayError(3, "file", data.get(2), "an email");
			newPerson(contacts);
		}
		if (info.length < 2) {
			displayError(3, "file", data.get(2), "an email");
			newPerson(contacts);
		}
		String[] tag = (info[1]).split("\\.", 0);
		if (tag.length < 2) {
			displayError(3, "file", data.get(2), "an email");
			newPerson(contacts);
		}
		if (!tag[0].matches("^[a-zA-Z]*$")) {
			displayError(3, "file", data.get(2), "an email");
			newPerson(contacts);
		}
		if (!(tag[1].equals("com") || tag[1].equals("net") || tag[1].equals("edu"))) {
			displayError(3, "file", data.get(2), "an email");
			newPerson(contacts);
		}
		System.out.print("What is the age of your new contact? ");
		try {
			int age = word.nextInt();
			data.add("" + age);
		} catch (NumberFormatException e) {
			displayError(3, "file", word.next(), "an age");
			newPerson(contacts);
		} catch (InputMismatchException e) {
			displayError(3, "file", word.next(), "an age");
			newPerson(contacts);
		}
		
		System.out.print("Does your contact live near campus? ");
		String nc = word.next().toLowerCase();
		if (nc.equals("yes")) nc = "true";
		else if(nc.equals("no")) nc = "false";
		else {
			 displayError(3, "file", nc, "a boolean");
			 newPerson(contacts);
		}
		data.add(nc);
		System.out.print("Add a note about your new contact. ");
		word.nextLine();
		String note = word.nextLine();
		data.add(note);
		System.out.print("Do you want to add another note? ");
		String decision = word.next().toLowerCase();
		do {
			System.out.print("Add a note about your new contact. ");
			word.nextLine();
			note = word.nextLine();
			data.add(note);
			System.out.print("Do you want to add another note? ");
			decision = word.next().toLowerCase();
		} while (decision.equals("yes"));
 
		Person p = new Person(data.toArray(new String[data.size()]));
		contacts.add(p);
		return p;
	}
	
	public static Set<Person> parseFile() {
		
		File f = null;
		Set<Person> contacts = new TreeSet<Person>();
		
		try {
			System.out.print("What is the name of the contacts file? ");
			Scanner input = new Scanner(System.in);
			f = new File(input.nextLine());
			input = new Scanner(f);
			while (input.hasNextLine()) {
				
				String line = input.nextLine();
				String[] info = line.split(",", 0);
				if (checkFormat(f.getName(), line, info)) {
					Person p = new Person(info);
					contacts.add(p);
				}
			}
			
		} catch(FileNotFoundException e) {
			System.out.println("The file " + f.getName() + " could not be found.");
			System.out.println();
			parseFile();
		}
		
		return contacts;
	}
	
	public static boolean checkFormat(String file, String line, String[] data) {
		
		if (data.length < 6) displayError(1, file, line, "");
		
		// FIRST NAME LAST NAME
		String fn = data[0];
		String ln = data[1];
		if (!fn.matches("^[a-zA-Z]*$")) displayError(2, file, fn, "a first name");
		else if (!ln.matches("^[a-zA-Z]*$")) displayError(2, file, ln, "a last name");
		
		// EMAIL
		String email = data[2];
		if (!email.contains("@")) displayError(2, file, email, "an email");
		String[] info = email.split("@");
		if (info.length < 2) displayError(2, file, email, "an email");
		if(!info[0].matches("[a-zA-Z]*$")) displayError(2, file, email, "an email");
		String[] tag = (info[1]).split("\\.", 0);
		if (tag.length < 2) displayError(2, file, email, "an email");
		if (!tag[0].matches("^[a-zA-Z]*$")) displayError(2, file, email, "an email");
		if (!(tag[1].equals("com") || tag[1].equals("net") || tag[1].equals("edu"))) displayError(2, file, email, "an email");
		
		// AGE
		try {
			int age = Integer.parseInt(data[3]);
		} catch (NumberFormatException e){
			displayError(2, file, data[3], "an age");
		}
		
		// NEAR CAMPUS
		String nc = data[4].toLowerCase();
		if (!nc.equals("true") && !nc.equals("false")) displayError(2, file, nc, "a boolean");
		
		return true;
		
	}
	
	public static void displayError(int n, String file, String word, String type) {
		
		if (n == 1 || n == 2) System.out.println("The file " + file + " is not formatted properly.");
		
		if (n == 1) {
			System.out.println("There are not enough paramaters on line " + word);
			parseFile();
		} else if (n == 2) {
			System.out.println("The paramater " + word + " cannot be parsed as " + type);
			System.out.println();
			parseFile();
		} else if (n == 3) {
			System.out.println("That is not a valid " + type + ".");
			System.out.println();
		}
	}
}
