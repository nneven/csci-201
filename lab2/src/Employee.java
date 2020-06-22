
public abstract class Employee extends Person {
	
	protected int employeeID;
	protected String jobTitle;
	protected String company;
	
	public Employee(String fn, String ln, String bd, int id, String jt, String c) {
		super(fn, ln, bd);
		employeeID = id;
		jobTitle = jt;
		company = c;
	}
	
	public int getEmployeeID() {
		return employeeID;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public String getCompany() {
		return company;
	}
	
	public abstract double getAnnualSalary();
}
