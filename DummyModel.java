package Project1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DummyModel extends Model {
	
    Map<String, double[]> train = new HashMap<String, double[]>(); // CREATES VARIABLE WITH TWO PARAMETERS (STRING(LABEL) AND DOUBLE(COORDINATES))
    Map<String, Double> trainNumbers = new HashMap<String, Double>(); // CREATES VARIABLE WITH TWO PARAMENTERS (STRING(LABEL) & DOUBLE(COORDINATES))
    String apple = "Apple"; // DUMMY LABEL 
    String banana = "Banana"; // DUMMY LABEL
    
	
    public void train(ArrayList<DataPoint> data){ // TRAIN FUNCTION
    	
        for(DataPoint dataPoint : data){ // CREATES DATAPOINT VARIABLE 
            if(train.containsKey(dataPoint.getLabel())){
            	
                double coordinate[] = train.get(dataPoint.getLabel()); // CREATES VARIABLE THAT GETS LABLE OF SPECIFIC DATA POINT
                coordinate[0] += dataPoint.getf1(); // SETS INDEX 0 TO BE GOTTEN DATA OF F1
                coordinate[1] += dataPoint.getf2(); // SETS INDEX 1 TO BE GOTTEN DATA OF F2
                train.put(dataPoint.getLabel(), coordinate); // ADDS "COORDINATE" TO TRAIN VARIABLE  
                trainNumbers.put(dataPoint.getLabel(), trainNumbers.get(dataPoint.getLabel()) + 1);         
            }
            
            
            else{ // IF NOTHING IS GOTTEN FROM "A"
                double coordinate[] = {0, 0}; // THE COORDINATES ARE SET TO 0,0
                train.put(dataPoint.getLabel(), coordinate); // ADDS "COORDINATE" TO TRAIN VARIABLE
                trainNumbers.put(dataPoint.getLabel(), (double) 0); // ADDS COORDINATE TO TRAIN NUMBRS VARIABLE
            }       
        }
        
        
        for (Map.Entry<String,double[]> dataPoint : train.entrySet()){ 
            double coordinate[] = dataPoint.getValue(); // CREATES COORDINATE VARIBALE AND GETS VALUE OF DATA POINT
            coordinate[0] /= trainNumbers.get(dataPoint.getKey()); // INDEX 0 OF COORDINATES "X" GETS KEY OF DATA POINT
            coordinate[1] /= trainNumbers.get(dataPoint.getKey()); // INDEX 1 OF COORDINATES "Y" GETS KEY OF DATA POINT
            train.put(dataPoint.getKey(), coordinate); // ADDS KEY AND COORDINATE VARIABLE TO TRAIN VARIABLE
        }
    }

    public String test(ArrayList<DataPoint> data){ // "TEST" FUNCTION
        double coordinates[] = {0, 0}; // CREATES COORDINATES VARIABLE
        
        for(DataPoint dataPoint : data){ // FOR LOOP FOR "DATAPOINT" 
            coordinates[0] += dataPoint.getf1(); // SETS INDEX 1 OF COORDINATES "X" TO F1 OF DATA POINT 
            coordinates[1] += dataPoint.getf2(); // SETS INDEX 2 OF COORDINATES "Y" TO F2 OF DATA POINT
        }
        
        coordinates[0] /= data.size(); // DIVIDES INDEX 1 OF COORDINATES "X" BY DATA SIZE (AVERAGE)
        coordinates[1] /= data.size(); // DIVIDES INDEX 2 OF COORDINATES "Y" BY DATA SIZE (AVERAGE) 
        
        double difference = Integer.MAX_VALUE;
        
        String result = null; // INITIALIZES RESULT VARIABLE
        
        for (Map.Entry<String,double[]> diff : train.entrySet()){ // CREATES "DIFF" VARIABLE THAT ACCESES TRAIN ARRAY
        	
            double value[] = diff.getValue(); // CREATES VARIABLE THAT ACCESSES DIFF ARRAY
            
            if(Math.abs(value[0] - coordinates[0]) + Math.abs(value[1] - coordinates[1]) < difference) { 
            	
                difference = Math.abs(value[0] - coordinates[0]) + Math.abs(value[1] - coordinates[1]); // MATH TO GET DIFFERENCE BETWEEN "VALUE" AND "COORDINATES"
                
                result = diff.getKey();  // GETSKEY OF DIFF AND ADDS IT TO RESULT VARIABLE
            } 
        }
        return result; 
    }

    Double getAccuracy(ArrayList<DataPoint> data){ // GET ACCURACY FUNCTION  
    	
        double dataInfo[] = {0, 0, 0, 0}; // CREATES ARRAY VARIABLE  (NECESSARY TO BE ABLE TO CALCULATE ACCURACY) 
        for(DataPoint dataPoint : data){ // FOR LOOPS FOR DATAPOINT
        	
            double difference = Integer.MAX_VALUE;
            
            String result = null; // INITIALIZES RESULT VARIABLE 
            
            for (Map.Entry<String,double[]> diff : train.entrySet()){ // CREATES "DIFF" VARIABLE THAT ACCESSES TRAIN ARRAY
            	
                double value[] = diff.getValue(); // CREATES VARIABLE THAT ACCESSES DIFF ARRAY
                
                if(Math.abs(value[0] - dataPoint.getf1()) + Math.abs(value[1] - dataPoint.getf2()) < difference){
                	
                    difference = Math.abs(value[0] - dataPoint.getf1()) + Math.abs(value[1] - dataPoint.getf2()); // MATH TO GET DIFFERENCE BETWEEN VALUE AND F1 & F2
                    
                    result = diff.getKey(); // GETSKEY OF DIFF AND ADDS IT TO RESULT VARIABLE
                }
            }
            
            if(result.equals(apple) && dataPoint.getLabel().equals(apple)){ // IF RESULT OF A (APPLE) = DATAPOINT OF A 
                dataInfo[0]++; // INDEX 0 INCREASES BY 1 (TRUE POSITIVE) 
            }
            
            else if(result.equals(banana) && dataPoint.getLabel().equals(banana)){ // IF RESULT OF B (BANANA) = DATAPOINT OF B
                dataInfo[1]++; // INDEX 1 INCREASES BY 1 (FALSE NEGATIVE) 
            }
            
            else if(result.equals(apple) && dataPoint.getLabel().equals(banana)){ // IF RESULT OF A (APPLE) = DATAPOINT OF B (BANANA)
                dataInfo[2]++; // INDEX 2 INCREASES BY 1 (TRUE NEGATIVE) 
            }
            
            else{ // ANYTHING ELSE
                dataInfo[3]++; // INDEX 3 INCREASES BY 1 (FALSE POSITIVE) 
            }
            
        }
        return (dataInfo[0] + dataInfo[2])/(dataInfo[0] + dataInfo[1] + dataInfo[2] + dataInfo[3]); // TRUE POSITIVE + TRUE NEGATIVE / 
        																							//	TRUE POSITIVE + FALSE POSITIVE + TRUE NEGATIVE + FALSE NEGATIVE 
    }

    public Double getPrecision(ArrayList<DataPoint> data){ // GET PRECISION FUNCTION 
    	
        double dataInfo[] = {0, 0, 0, 0}; // CREATES ARRAY (NECESSARY TO BE ABLE TO CALCULATE PRECISION)
        
        for(DataPoint dataPoint : data){
            double difference = Integer.MAX_VALUE;
            String result = "";
            for (Map.Entry<String,double[]> entry : train.entrySet()){
                double val[] = entry.getValue();
                
                if(Math.abs(val[0] - dataPoint.getf1()) + Math.abs(val[1] - dataPoint.getf2()) < difference){
                    difference = Math.abs(val[0] - dataPoint.getf1()) + Math.abs(val[1] - dataPoint.getf2());
                    result = entry.getKey();
                }
            }
            if(result.equals(apple) && dataPoint.getLabel().equals(apple)){ // IF RESULT OF A (APPLE) = DATAPOINT OF A 
                dataInfo[0]++; // ADDS 1 TO INDEX 0 (TRUE POSITIVE) 
            }
            else if(result.equals(banana) && dataPoint.getLabel().equals(banana)){ // IF RESULT OF B (BANANA) = DATAPOINT OF B
                dataInfo[1]++; // ADDS 1 TO INDEX 1 (FALSE NETAGIVE) 
            }
            else if(result.equals(apple) && dataPoint.getLabel().equals(banana)){ // IF RESULT OF A (APPLE) = DATAPOINT OF B (BANANA) 
                dataInfo[2]++; // ADDS 1 TO INDEX 2 (TRUE NEGATIVE) 
            }
            else{ // ANYTHING ELSE
                dataInfo[3]++; // ADDS 1 TO INDEX 3 (FALSE POSITIVE) 
            }
        }
        return (dataInfo[0])/(dataInfo[0] + dataInfo[3]); // TRUE POSITIVE / TRUE POSITIVE - FALSE POSITIVE 
    }
	

}
