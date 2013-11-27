package ML;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

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
		int numInstances = y.getRowDimension();
		int numFeatures = x.getColumnDimension();
		int iteration = 0;
		RealMatrix h, hMinusY;
		
		while (iteration < numIteration) {
			System.out.println("iteration: " + iteration);
			
			// calculate h(x)
			h = theta.multiply(x.transpose()).transpose();
			
			// calculate cost
			double cost = getCost(h);
			System.out.println("cost: " + cost);
			
			// update theta
			hMinusY = h.subtract(y);
			
			double[][] newTheta = new double[1][numFeatures];
			for (int featureIndex = 0; featureIndex < numFeatures; featureIndex++) {
				newTheta[0][featureIndex] = 0;
				
				for (int instanceIndex = 0; instanceIndex < numInstances; instanceIndex++) {
					newTheta[0][featureIndex] += x.getEntry(instanceIndex, featureIndex) * hMinusY.getEntry(instanceIndex, 0);
				}
				
				newTheta[0][featureIndex] *= alpha / numInstances;
			}
			
			theta = theta.subtract(MatrixUtils.createRealMatrix(newTheta));
			iteration++;
		}
		
	}

	private double getCost(RealMatrix h) {
		double cost = 0;
		
		for (int instanceIndex = 0; instanceIndex < x.getRowDimension(); instanceIndex++) {
			cost += Math.pow(h.getEntry(instanceIndex, 0) - y.getEntry(instanceIndex, 0), 2);
		}
		
		return cost / (2 * x.getRowDimension());
	}

}
