import java.util.ArrayList;

import input.InputParser;


public class Ex1 {

	public static void main(String[] args) {
		
		String inputFile = "data/ex1/ex1data1.txt";
		String delimiter = ",";
		
		ArrayList<ArrayList<String>> instances = InputParser.readFile(inputFile , delimiter);
		System.out.println(instances.size() + " " + instances.get(0).size());
	}

}
