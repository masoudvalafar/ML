package ML;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class LinearRegression {

	private RealMatrix x;
	private RealMatrix y;
	private RealMatrix theta;
	
	private int numIteration = 100;
	private double alpha = 0.01;

	public LinearRegression(RealMatrix x, RealMatrix y) {
		this.x = x;
		this.y = y;
	}

	public void setInitialTheta(RealMatrix theta) {
		this.theta = theta;
	}
	
	public void execute() {
		
		// normalizing inputs
		normalizeX();

		// main loop
		int iteration = 0;
		RealMatrix h;
		while (iteration < numIteration) {
			System.out.println("iteration: " + iteration);
			
			// calculate h(x)
			h = theta.multiply(x.transpose()).transpose();
			
			// calculate cost
			double cost = calculateCost(h);
			System.out.println("cost: " + cost);
			
			// update theta
			updateTheta(h);
			
			iteration++;
		}
		
	}

	private void updateTheta(RealMatrix h) {
		int numFeatures = x.getColumnDimension();
		int numInstances = y.getRowDimension();
		
		RealMatrix hMinusY = h.subtract(y);
		
		double[][] newTheta = new double[1][numFeatures];
		for (int featureIndex = 0; featureIndex < numFeatures; featureIndex++) {
			newTheta[0][featureIndex] = 0;
			
			for (int instanceIndex = 0; instanceIndex < numInstances; instanceIndex++) {
				newTheta[0][featureIndex] += x.getEntry(instanceIndex, featureIndex) * hMinusY.getEntry(instanceIndex, 0);
			}
			
			newTheta[0][featureIndex] *= alpha / numInstances;
		}
		
		theta = theta.subtract(MatrixUtils.createRealMatrix(newTheta));
	}

	private void normalizeX() {
		
		for (int columnIndex = 1; columnIndex < x.getColumnDimension(); columnIndex++) {
			
			double[] values = x.getColumn(columnIndex);
			
			DescriptiveStatistics stats = new DescriptiveStatistics(values);
			double standardDeviation = stats.getStandardDeviation();
			double mean = stats.getMean();
			
			for (int instanceIndex = 0; instanceIndex < values.length; instanceIndex++) {
				values[instanceIndex] = (values[instanceIndex] - mean) / standardDeviation;
			}
			
			x.setColumn(columnIndex, values);
		}
		
	}

	private double calculateCost(RealMatrix h) {
		
		double cost = 0;
		int numInstances = x.getRowDimension();
		
		for (int instanceIndex = 0; instanceIndex < numInstances; instanceIndex++) {
			cost += Math.pow(h.getEntry(instanceIndex, 0) - y.getEntry(instanceIndex, 0), 2);
		}
		
		return cost / (2 * numInstances);
	}

}
