
public class CommissionEmployee extends HourlyEmployee {

	protected double salesTotal;
	protected double commisionPercentage;
	
	public CommissionEmployee(String fn, String ln, String bd, int id, String jt, String c, double hr, double hpw, double st, double cp) {
		super(fn, ln, bd, id, jt, c, hr, hpw);
		salesTotal = st;
		commisionPercentage = cp;
	}
	
	public double getAnnualSalary() {
		return super.getAnnualSalary() + (salesTotal * commisionPercentage);
	}
}
