import input.InputParser;

import java.util.ArrayList;

import neuralnetworks.NeuralNetworkAlgorithm;

import org.apache.commons.math3.linear.RealMatrix;

public class Ex3 {

	public static void main(String[] args) {
		String inputFileX = "data/ex3/X.txt";
		String inputFileY = "data/ex3/y.txt";
		String delimiter = " ";
		
		ArrayList<ArrayList<String>> inputX = InputParser.readFile(inputFileX , delimiter);
		ArrayList<ArrayList<String>> inputY = InputParser.readFile(inputFileY , delimiter);
		ArrayList<ArrayList<String>> processedInputY = new ArrayList<ArrayList<String>>();
		for (ArrayList<String> a: inputY) {
			ArrayList<String> processedY = new ArrayList<String>();
			for (int counter = 1; counter < 11; counter++) {
				processedY.add((Integer.valueOf(a.get(0)) == counter) ? "1" : "0");
			}
			processedInputY.add(processedY);
		}
		
		System.out.println(inputX.size() + " " + inputX.get(0).size());
		System.out.println(processedInputY.size() + " " + processedInputY.get(0).size());
		
		int numFeatures = inputX.get(0).size();
		RealMatrix x = InputParser.createMatrix(numFeatures, inputX);
		RealMatrix y = InputParser.createMatrix(10, processedInputY);
		
		int[] numNodesLayers = {25, 10};
		
		NeuralNetworkAlgorithm neuralNetworkAlgorithm = new NeuralNetworkAlgorithm(x, y, numNodesLayers);
		neuralNetworkAlgorithm.run();
	}

}
