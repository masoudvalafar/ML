import input.InputHelper;
import input.InputParser;

import java.util.ArrayList;

import org.apache.commons.math3.linear.RealMatrix;

import ML.LinearRegression;


public class Ex1 {


	public static void main(String[] args) {
		
		// single variable
//		int numFeatures = 1;
//		String inputFile = "data/ex1/ex1data1.txt";
//		String delimiter = ",";
//		
//		ArrayList<ArrayList<String>> instances = InputParser.readFile(inputFile , delimiter);
//		System.out.println(instances.size() + " " + instances.get(0).size());
//		
//		RealMatrix x = InputParser.createMatrix(numFeatures, instances, 0, 1.0, 1);
//		RealMatrix y = InputParser.createMatrix(1, instances, 1, 0, 0);
//		RealMatrix theta = InputHelper.createRandomMatrix(numFeatures + 1, 1, -1, 1);
//		
//		LinearRegression linearRegression = new LinearRegression(x, y);
//		linearRegression.setInitialTheta(theta);
//		
//		System.out.println(x.getRowDimension() + " " + x.getColumnDimension() + " " + x.getEntry(1, 1));
//		System.out.println(y.getRowDimension() + " " + y.getColumnDimension());
//		System.out.println(theta.getRowDimension() + " " + theta.getColumnDimension());
//		linearRegression.execute();
		
		
		// multivariable
		String inputFile2 = "data/ex1/ex1data2.txt";
		String delimiter = ",";
		int numFeatures = 2;
		
		ArrayList<ArrayList<String>> instances2 = InputParser.readFile(inputFile2 , delimiter);
		System.out.println(instances2.size() + " " + instances2.get(0).size());
		
		RealMatrix x2 = InputParser.createMatrix(numFeatures, instances2, 0, 1.0, 1);
		RealMatrix y2 = InputParser.createMatrix(1, instances2, numFeatures, 0, 0);
		RealMatrix theta2 = InputHelper.createRandomMatrix(1, numFeatures + 1, -1, 1);
		
		LinearRegression linearRegression2 = new LinearRegression(x2, y2);
		linearRegression2.setInitialTheta(theta2);
		
		System.out.println("X2: " + x2.getRowDimension() + " " + x2.getColumnDimension() + " " + x2.getEntry(0, 2));
		System.out.println("Y2: " + y2.getRowDimension() + " " + y2.getColumnDimension());
		System.out.println("Theta: " + theta2.getRowDimension() + " " + theta2.getColumnDimension());
		linearRegression2.execute();
	}

}
