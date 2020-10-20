package Project1;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Main {
	
	

	public static void main(String[] args) {
		
		ArrayList<DataPoint> train = new ArrayList<DataPoint>(); // CREAYES TRAIN DATA POINT
		ArrayList<DataPoint> test = new ArrayList<DataPoint>(); // CREATES TEST DATA POINT
		
		String label[] = {"Apple", "Banana", "Orange"}; // CREATES LABEL ARRAY 
        Random r = new Random(); // CREATES RANDOM GENERATOR VARIABLE 

        for(int i = 0; i < 100; i++){ 
            double f1 = 100 * r.nextDouble(); // GENERATES RANDOM # FOR F1
            double f2 = 100 * r.nextDouble(); // GENERATES RANDOM # FOR F2
            int random = r.nextInt(3);
            train.add(new DataPoint(f1, f2, label[random], "DummyModel"));
        }
        for(int i = 0; i < 100; i++){
            double f1 = 100 * r.nextDouble(); // GENERATES RANDOM # FOR F1
            double f2 = 100 * r.nextDouble(); // GENERATES RANDOM # FOR F2
            int random = r.nextInt(3);
            test.add(new DataPoint(f1, f2, label[random], "DummyModel"));
        }
        
        
        DummyModel dummyModel = new DummyModel(); // CREATES NEW DUMMYMODEL
        
        dummyModel.train(train); 
        System.out.println(dummyModel.test(test)); // PRINTS OUT 
        
        
        double AccuracyDummy = dummyModel.getAccuracy(test); 
        double PrecisionDummy = dummyModel.getPrecision(test);
        
        // SETS FORMAT : 
        // (LENGTH OF "WORD", LENGTH OF # DISPLAYED, DISPLAYED # *100 TO MAKE IT WHOLE AND NOT DECIMAL)
        String Accuracy = String.format("%8s : %2.0f", "Accuracy % ", AccuracyDummy * 100); 
        String Precision = String.format("%9s : %2.0f", "Precision % ", PrecisionDummy * 100);
        
        
        JFrame MyFrame = new JFrame("Accuracy & Precision");  // TITLE FOR DATA DISPLAY
        MyFrame.setVisible(true); // MAKES IT VISIBLE TO SCREEN
        
        JPanel panel = new JPanel();  
        MyFrame.add(panel); // ADDS PANEL 
        MyFrame.setSize(250, 100); // SETS SIZE
        
        JLabel label1 = new JLabel(Accuracy); 
        panel.add(label1);   
        JLabel label2 = new JLabel(Precision);
        panel.add(label2); 
                 
    }
		
	}



