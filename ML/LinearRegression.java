package ML;

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
		int iteration = 0;
		double[] h = new double[numInstances];
		while (iteration < numIteration) {
			System.out.println(iteration);
			
			// calculate h(x)
			for (int index = 0; index < numInstances; numInstances++) {
				h[index] = theta. 
			}
			
			// update theta
			
		}
		
	}

}
