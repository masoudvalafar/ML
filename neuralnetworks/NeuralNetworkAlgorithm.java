package neuralnetworks;

import java.util.ArrayList;

import org.apache.commons.math3.linear.RealMatrix;

public class NeuralNetworkAlgorithm {

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
		while (continueRunning ) {
			
			// loop on each instance
			for (int instanceCounter = 0; instanceCounter < x.getRowDimension(); instanceCounter++) {
				RealMatrix instance = x.getSubMatrix(instanceCounter, instanceCounter, 0, x.getColumnDimension() - 1);
				RealMatrix output = y.getSubMatrix(instanceCounter, instanceCounter, 0, y.getColumnDimension() - 1);
				
				// forward propagation
				for (int layerIndex = 0; layerIndex < layers.size(); layerIndex++) {
					if (layerIndex == 0) {
						layers.get(layerIndex).calculateOutput(instance);
					}
					else {
						layers.get(layerIndex).calculateOutput(layers.get(layerIndex - 1).getOutput());
					}
				}
				
				// calculate error - backward for each instance
				for (int layerIndex = layers.size() - 1; layerIndex >= 0; layerIndex--) {
					if (layerIndex == layers.size() - 1) {
						layers.get(layerIndex).calcLastLayerError(output);
					} else {
						layers.get(layerIndex).calcError(layers.get(layerIndex + 1).getError());
					}
				}
				
				// add instance error to total error
				for (int layerIndex = 0; layerIndex < layers.size(); layerIndex++) {
					deltas.update(layerIndex, layers.get(layerIndex).getError());
				}
			}
			
			break;
		}
		
	}

}
