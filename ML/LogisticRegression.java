package ML;

import org.apache.commons.math3.analysis.function.Sigmoid;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class LogisticRegression {

	private RealMatrix x;
	private RealMatrix y;
	private RealMatrix theta;
	private int numIteration = 100;

	private static final double alpha = 0.03;

	private Sigmoid sigmoid = new Sigmoid();
	
	public LogisticRegression(RealMatrix x, RealMatrix y) {
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
			h = calculateH();

			// calculate cost
			double cost = calculateCost(h);
			System.out.println("cost: " + cost);

			// update theta
			updateTheta(h);

			iteration++;
		}
	}

	private RealMatrix calculateH() {
		double g = 0.0;
		
		RealMatrix h = theta.multiply(x.transpose()).transpose();
		for (int instanceIndex = 0; instanceIndex < x.getRowDimension(); instanceIndex++) {
			g = 1 / (1 + Math.pow(Math.E, -1 * h.getEntry(instanceIndex, 0)));
			//System.out.println(g + "  " + sigmoid.value(g));
			h.setEntry(instanceIndex, 0, sigmoid.value(g));
		}
		
		//System.out.println(h);
		return h;
	}

	private double calculateCost(RealMatrix h) {

		double cost = 0;
		int numInstances = x.getRowDimension();

		for (int instanceIndex = 0; instanceIndex < numInstances; instanceIndex++) {
			double yEntry = y.getEntry(instanceIndex, 0);
			double hEntry = h.getEntry(instanceIndex, 0);
			cost += (-1 * yEntry * Math.log(hEntry) - (1 - yEntry)
					* Math.log(1 - hEntry));
		}

		return cost / numInstances;
	}

	private void updateTheta(RealMatrix h) {
		int numFeatures = x.getColumnDimension();
		int numInstances = y.getRowDimension();

		RealMatrix hMinusY = h.subtract(y);
		
		double[][] newTheta = new double[1][numFeatures];
		for (int featureIndex = 0; featureIndex < numFeatures; featureIndex++) {
			newTheta[0][featureIndex] = 0;

			for (int instanceIndex = 0; instanceIndex < numInstances; instanceIndex++) {
				newTheta[0][featureIndex] += x.getEntry(instanceIndex,
						featureIndex) * hMinusY.getEntry(instanceIndex, 0);
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

}
