package neuralnetworks;

import java.util.ArrayList;

import org.apache.commons.math3.linear.RealMatrix;

public class Delta {

	ArrayList<RealMatrix> deltas;
	
	public Delta(int[] numNodesLayers) {
		for (int counter = 0; counter < numNodesLayers.length; counter++) {
			
			deltas.add(null);
		}
	}

	public void update(int layerIndex, RealMatrix error) {
		// TODO Auto-generated method stub
		
	}

}
