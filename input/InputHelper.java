package input;

import java.util.Random;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class InputHelper {

	static Random random = new Random();
	
	public static RealMatrix createRandomMatrix(int numColumns, int numRows, double lowerBound,
			double upperBound) {

		double[][] matrixData = new double[numRows][numColumns];
		
		double range = upperBound - lowerBound;
		for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
			for (int columnIndex = 0; columnIndex < numColumns; columnIndex++) {
				matrixData[rowIndex][columnIndex] = random.nextDouble() * range - (range / 2);
			}
		}
		
		return MatrixUtils.createRealMatrix(matrixData);
	}

}
