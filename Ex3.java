import input.InputParser;

import java.util.ArrayList;

public class Ex3 {

	public static void main(String[] args) {
		String inputFileX = "data/ex3/X.txt";
		String inputFileY = "data/ex3/y.txt";
		String delimiter = " ";
		
		ArrayList<ArrayList<String>> inputX = InputParser.readFile(inputFileX , delimiter);
		ArrayList<ArrayList<String>> inputY = InputParser.readFile(inputFileY , delimiter);
		System.out.println(inputX.size() + " " + inputX.get(0).size());
		System.out.println(inputY.size() + " " + inputY.get(0).size());

	}

}
