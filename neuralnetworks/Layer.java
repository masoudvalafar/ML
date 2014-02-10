package neuralnetworks;

import input.InputHelper;

import java.util.Random;

import org.apache.commons.math3.analysis.function.Sigmoid;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class Layer {

	private int numNodes;
	RealMatrix w = null;
	RealMatrix output = null;
	RealMatrix delta = null;
	
	Random random = new Random();
	private Sigmoid sigmoid = new Sigmoid();

	public Layer(int numNodes) {
		this.numNodes = numNodes;
		delta = InputHelper.createZeroMatrix(1, numNodes);
		output = InputHelper.createZeroMatrix(1, numNodes);
	}

	public void calculateOutput(RealMatrix input) {
		
		clearResults();
		
		if (w == null) {
			w = InputHelper.createRandomMatrix(input.getColumnDimension() + 1, numNodes, -1, 1);
		}
		
		RealMatrix preG = getInputWithBias(input).multiply(w);
		double g = 0;
		for (int nodeIndex = 0; nodeIndex < this.numNodes; nodeIndex++) {
			g = 1 / (1 + Math.pow(Math.E, -1 * preG.getEntry(0, nodeIndex)));
			this.output.setEntry(0, nodeIndex, sigmoid.value(g));
		}
		
	}
	
	private void clearResults() {
		for (int counter = 0; counter < numNodes; counter++) {
			this.output.setEntry(0, counter, 0);
			this.delta.setEntry(0, counter, 0);
		}
		
	}

	private RealMatrix getInputWithBias(RealMatrix x) {
		double[][] xWithBias = new double[1][x.getColumnDimension() + 1];
		xWithBias[0][0] = 1;
		for (int counter = 0; counter < x.getColumnDimension(); counter++) {
			xWithBias[0][counter + 1] = x.getEntry(0, counter);
		}
		
		return MatrixUtils.createRealMatrix(xWithBias);
	}

	public RealMatrix getOutput() {
		return output;
	}
	
	public void calcLastLayerError(RealMatrix correctOutput) {
		RealMatrix tempError = output.subtract(correctOutput);
		
		for (int counter = 0; counter < correctOutput.getColumnDimension(); counter++) {
			delta.setEntry(0, counter, output.getEntry(0, counter) * (1 - output.getEntry(0, counter)) * tempError.getEntry(0, counter) );
		}
//		System.out.println("correctOutput:" + correctOutput);
//		System.out.println("output:" + output);
//		System.out.println("delta: " + delta);
	}

	public RealMatrix getError() {
		return delta;
	}

	public void calcError(RealMatrix nextLayerError) {
		for (int nodeCounter = 0; nodeCounter < numNodes; nodeCounter++) {
			double nodeError = 0;
			for (int index = 0; index < nextLayerError.getColumnDimension(); index++) {
				nodeError += nextLayerError.getEntry(0, index) * w.getEntry(nodeCounter, index);
			}
			delta.setEntry(0, nodeCounter, output.getEntry(0, nodeCounter) * (1 - output.getEntry(0, nodeCounter)) * nodeError);
			System.out.println("nextLayerError:" + nextLayerError);
			System.out.println("output:" + output);
			System.out.println("delta: " + delta);
		}
	}
	
	

}
