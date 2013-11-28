package ML;

import org.apache.commons.math3.analysis.function.Sigmoid;
import org.apache.commons.math3.linear.RealMatrix;

public class LogisticRegression {

	private RealMatrix x;
	private RealMatrix y;
	private Sigmoid s = new Sigmoid();

	public LogisticRegression(RealMatrix x, RealMatrix y) {
		this.x = x;
		this.y = y;
	}
	
	public void execute() {
		
		
		
	}
	
	private double getCost(RealMatrix h) {
		
		double cost = 0;
		int numInstances = x.getRowDimension();
		
		for (int instanceIndex = 0; instanceIndex < numInstances; instanceIndex++) {
			double yEntry = y.getEntry(instanceIndex, 0);
			double hEntry = h.getEntry(instanceIndex, 0);
			cost += -1 * yEntry * Math.log(hEntry) - (1 - yEntry) * Math.log(1 - hEntry) ;
		}
		
		return cost /  numInstances;
	}

}
