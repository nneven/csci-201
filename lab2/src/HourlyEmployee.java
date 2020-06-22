
public class HourlyEmployee extends Employee {

	protected double hourlyRate;
	protected double numberHoursPerWeek;
	
	public HourlyEmployee(String fn, String ln, String bd, int id, String jt, String c, double hr, double hpw) {
		super(fn, ln, bd, id, jt, c);
		hourlyRate = hr;
		numberHoursPerWeek = hpw;
	}
	
	public double getAnnualSalary() {
		return (hourlyRate * numberHoursPerWeek) * 52;
	}
}
