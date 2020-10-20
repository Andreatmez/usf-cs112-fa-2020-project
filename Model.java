package Project1;

import java.util.ArrayList;

public abstract class Model {
	
	// COPY PASTE OF FUNCTIONS GIVEN BY PROFESSOR
	
	abstract void train(ArrayList<DataPoint> data);
	abstract String test(ArrayList<DataPoint> data);
	abstract Double getAccuracy(ArrayList<DataPoint> data);
	abstract Double getPrecision(ArrayList<DataPoint> data);

}
