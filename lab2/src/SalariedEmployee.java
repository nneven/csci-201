
public class SalariedEmployee extends Employee {

	protected double annualSalary;
	
	public SalariedEmployee(String fn, String ln, String bd, int id, String jt, String c, double as) {
		super(fn, ln, bd, id, jt, c);
		annualSalary = as;
	}
	
	public double getAnnualSalary() {
		return annualSalary;
	}
}
