package input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class InputParser {

	public static ArrayList<ArrayList<String>> readFile(String inputFile,
			String delimiter) {
		BufferedReader bufferReader = null;
		try {
			bufferReader = new BufferedReader(new FileReader(inputFile));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		StringTokenizer st;
		String line = null;
		ArrayList<ArrayList<String>> instances = new ArrayList<ArrayList<String>>();

		while (true) {
			try {
				line = bufferReader.readLine();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

			if (line == null)
				break;
			
			if (line.length() == 0 || line.charAt(0) == '#')
				continue;
			
			st = new StringTokenizer(line, delimiter);
			ArrayList<String> instance = new ArrayList<String>();
			while (st.hasMoreTokens()) {
				instance.add(st.nextToken());
			}
			instances.add(instance);
		}

		try {
			bufferReader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return instances;
	}

	public static RealMatrix createMatrix(int numFeatures,
			ArrayList<ArrayList<String>> instances) {
		return createMatrix(numFeatures, instances, 0, 0, 0);
	}

	public static RealMatrix createMatrix(int numFeatures,
			ArrayList<ArrayList<String>> instances, int startingIndex) {
		return createMatrix(numFeatures, instances, startingIndex, 0, 0);
	}

	public static RealMatrix createMatrix(int numFeatures,
			ArrayList<ArrayList<String>> instances, int startingIndex,
			double defaultValue, int numDefaultColumn) {

		double[][] matrixData = new double[instances.size()][numFeatures
				+ numDefaultColumn];

		for (int instanceCounter = 0; instanceCounter < instances.size(); instanceCounter++) {

			for (int index = 0; index < numDefaultColumn; index++) {
				matrixData[instanceCounter][index] = defaultValue;
			}

			for (int featureCounter = 0; featureCounter < numFeatures; featureCounter++) {
				matrixData[instanceCounter][featureCounter + numDefaultColumn] = Double
						.parseDouble(instances.get(instanceCounter).get(
								featureCounter + startingIndex));
			}
		}

		return MatrixUtils.createRealMatrix(matrixData);
	}

}
