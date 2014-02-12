package neuralnetworks;

import input.InputHelper;

import java.util.ArrayList;

import org.apache.commons.math3.linear.RealMatrix;

public class Delta {

	ArrayList<RealMatrix> deltas = new ArrayList<RealMatrix>();
	
	public Delta(int[] numNodesLayers) {
		for (int counter = 0; counter < numNodesLayers.length; counter++) {
			deltas.add(InputHelper.createZeroMatrix(1, numNodesLayers[counter]));
		}
	}

	public void update(int layerIndex, RealMatrix delta) {
		RealMatrix d = deltas.get(layerIndex);
		for (int counter = 0; counter < d.getColumnDimension(); counter ++) {
			d.setEntry(0, counter, d.getEntry(0, counter) + delta.getEntry(0, counter));
		}
	}

	public RealMatrix getDelta(int index) {
		return deltas.get(index);
	}

}
