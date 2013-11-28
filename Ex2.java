import input.InputHelper;
import input.InputParser;

import java.util.ArrayList;

import org.apache.commons.math3.linear.RealMatrix;

import ML.LogisticRegression;


public class Ex2 {

	public static void main(String[] args) {
		
		String inputFile = "data/ex2/ex2data1.txt";
		String delimiter = ",";
		int numFeatures = 2;
		
		ArrayList<ArrayList<String>> instances = InputParser.readFile(inputFile , delimiter);
		System.out.println(instances.size() + " " + instances.get(0).size());
		
		RealMatrix x = InputParser.createMatrix(numFeatures, instances, 0, 1.0, 1);
		RealMatrix y = InputParser.createMatrix(1, instances, numFeatures, 0, 0);
		RealMatrix theta = InputHelper.createRandomMatrix(numFeatures + 1, 1, -1, 1);
		
		LogisticRegression logisticRegression = new LogisticRegression(x, y);
		
		logisticRegression.execute();
	}

}
