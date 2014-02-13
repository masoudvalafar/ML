package neuralnetworks;

import java.util.ArrayList;

import org.apache.commons.math3.linear.RealMatrix;

public class NeuralNetworkAlgorithm {

	private static final double ETA = 0.01;
	private RealMatrix x;
	private RealMatrix y;
	private ArrayList<Layer> layers = new ArrayList<Layer>();
	private int numLayers = 0;
	private Delta deltas;

	public NeuralNetworkAlgorithm(RealMatrix x, RealMatrix y,
			int[] numNodesLayers) {
		this.x = x;
		this.y = y;
		this.numLayers  = numNodesLayers.length;
		
		deltas = new Delta(numNodesLayers);
		for (int counter = 0; counter < this.numLayers; counter++) {
			this.layers.add(new Layer(numNodesLayers[counter]));
		}
	}

	public void run() {
		boolean continueRunning = true;
		
		// loop until converge
		while (continueRunning) {
			double error = 0.0;
			deltas.clean();
			
			// loop on each instance
			for (int instanceCounter = 0; instanceCounter < x.getRowDimension(); instanceCounter++) {
				RealMatrix instance = x.getSubMatrix(instanceCounter, instanceCounter, 0, x.getColumnDimension() - 1);
				RealMatrix expectedOutput = y.getSubMatrix(instanceCounter, instanceCounter, 0, y.getColumnDimension() - 1);
				
				// forward propagation
				for (int layerIndex = 0; layerIndex < layers.size(); layerIndex++) {
					if (layerIndex == 0) {
						layers.get(layerIndex).setOuput(instance);
					}
					else {
						layers.get(layerIndex).calculateOutput(layers.get(layerIndex - 1).getOutput());
					}
					//System.out.println("output for layer " + layerIndex + " : " + layers.get(layerIndex).getOutput());
				}
				
				// calculating error
				RealMatrix output = layers.get(layers.size() - 1).getOutput();
				for (int nodeIndex = 0; nodeIndex < output.getColumnDimension(); nodeIndex++) {
					error += Math.pow(output.getEntry(0, nodeIndex) - expectedOutput.getEntry(0, nodeIndex), 2) ;
				}
				
				// calculate deltas - backward for each instance
				for (int layerIndex = layers.size() - 1; layerIndex >= 0; layerIndex--) {
					if (layerIndex == layers.size() - 1) {
						layers.get(layerIndex).calcLastLayerError(expectedOutput);
					} else {
						layers.get(layerIndex).calcError(layers.get(layerIndex + 1).getError(), layers.get(layerIndex + 1).getWeight());
					}
					//System.out.println("delta for layer " + layerIndex + " : " + layers.get(layerIndex).getError());
					
					// add instance error to total error
					deltas.update(layerIndex, layers.get(layerIndex).getError());
					//System.out.println(deltas.getDelta(layerIndex));
				}
			}
			
			System.out.println("Error: " + error);
			
			// adjust deltas
			//deltas.adjustDeltas(x.getRowDimension());
			for (int layerIndex = layers.size() - 1; layerIndex >= 0; layerIndex--) {
				System.out.println(layerIndex + " : " + deltas.getDelta(layerIndex));
			}
			
			// back-propagate error
			for (int layerIndex = layers.size() - 2; layerIndex >= 0; layerIndex--) {
				propagateError(layers.get(layerIndex + 1).getWeight(), deltas.getDelta(layerIndex).getRowMatrix(0));
			}
			
		}
		
	}

	private void propagateError(RealMatrix weight, RealMatrix delta) {
		System.out.println("w - rows: " + weight.getRowDimension());
		System.out.println("w - columns: " + weight.getColumnDimension());
		System.out.println("delta - columns: " + delta.getColumnDimension());
		for (int index = 0; index  < delta.getColumnDimension(); index++) {
			
		}
	}

}
