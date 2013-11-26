package input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class InputParser {

	public static ArrayList<ArrayList<String>> readFile(String inputFile, String delimiter) {
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

}
