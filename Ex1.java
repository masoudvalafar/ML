import input.InputHelper;
import input.InputParser;

import java.util.ArrayList;

import org.apache.commons.math3.linear.RealMatrix;

import ML.LinearRegression;


public class Ex1 {

	private static final int NUM_FEATURES = 1;

	public static void main(String[] args) {
		
		String inputFile = "data/ex1/ex1data1.txt";
		String delimiter = ",";
		
		ArrayList<ArrayList<String>> instances = InputParser.readFile(inputFile , delimiter);
		System.out.println(instances.size() + " " + instances.get(0).size());
		
		RealMatrix x = InputParser.createMatrix(NUM_FEATURES, instances, 0, 1.0, 1);
		RealMatrix y = InputParser.createMatrix(1, instances, 1, 0, 0);
		RealMatrix theta = InputHelper.createRandomMatrix(NUM_FEATURES + 1, x.getRowDimension(), -1, 1);
		
		LinearRegression linearRegression = new LinearRegression(x, y);
		linearRegression.setInitialTheta(theta);
		
		linearRegression.execute();
	}

}
