package Project1;

public class DataPoint { 
	
	private double f1; // CREATES INSTANCE VARIABLE
	private double f2; // CREATES INSTANCE VARIABLE
	private String label; // CREATES INSTANCE VARIABLE 
	private String type; // CREATES INSTANCE VARIABLE

	
	public DataPoint(double f1, double f2, String label, String type) { // CONSTRUCTOR 
		
		this.f1 = f1;
		this.f2 = f2;
		this.label = label;
		this.type = type;
		
	}
	
	public DataPoint() { // NO-ARGUMENT CONSTRUCTOR 
		
		this(0, 0, "", "");
	}
	
	public double getf1() { // ACCESSOR
		return this.f1;
	}
	
	public double getf2() { // ACCESSOR 
		return this.f2;
	}
	
	public String getLabel() { // ACCESSOR
		return this.label;
	}
	
	public String getType() { // ACCESSOR 
		return this.type;
	}
	
	public void setf1(double f1) { // MUTATOR 
		this.f1 = f1;
	}
	
	public void setf2(double f2) { // MUTATOR
		this.f2 = f2;
	}

	
	public void setLabel(String label) { // MUTATOR 
		this.label = label;
	}
	
	public void setType(String type) { // MUTATOR 
		this.type = type;
	}
}
