package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class AnalyticsCounter {
	private static int symptomCount = 0;

	public static <lineSymptom> void main(String args[]) throws Exception {
        final String path = ".\\Project02Eclipse\\";
		String fileToRead = "symptoms.txt";
        System.out.println(Paths.get(path + fileToRead).toAbsolutePath());

		ReadSymptomDataFromFile symptomsFile = new ReadSymptomDataFromFile(path + fileToRead);
		System.out.println(symptomsFile.GetSymptoms());
		//System.out.println(Paths.get("").toAbsolutePath());
		Map<String,Integer> symptomsMap = new HashMap<>();
		BufferedReader reader = new BufferedReader (new FileReader(path + fileToRead));
		String line = reader.readLine();

		int i = 0;
		String lineDelimiterForSplit = ":";
		int headCount = 0;
		while (line != null) {
			i++;
			line = reader.readLine();
			//lineSymptom = line.split(lineDelimiterForSplit)[0];
			//System.out.println("symptom from file: " + lineSymptom);
			if (!(symptomsMap.containsKey(line)) && line != null) {
				//headCount++;
				System.out.println("symptom " + line + " does not exist, let's add it to initializing to 1");
				symptomsMap.put(line, 1);
			}
			else if (line != null){
				int currentSymptomCount = symptomsMap.get(line);
				System.out.println("symptom " + line + " is already in Map with value" + currentSymptomCount);
				symptomsMap.put(line, currentSymptomCount + 1);
			}

		}
		reader.close();
//		System.out.println(symptomsMap);
//		symptomsMap.entrySet().stream()
//				.sorted(Map.Entry.comparingByKey())
//				.forEach(System.out::println);
//		for(Map.Entry m:symptomsMap.entrySet()){
//			System.out.println(m.getKey()+ " : " +m.getValue());}
		Map<String,Integer> symptomsSortedMap = new TreeMap<String,Integer>(symptomsMap);
		System.out.println(symptomsSortedMap);

		// next generate output
		FileWriter writer = new FileWriter ("result.out");
		symptomsMap.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.forEach(System.out::println);
		//writer.write("headache: " + headacheCount + "\n");
		//writer.write("rash: " + rashCount + "\n");
		//writer.write("dialated pupils: " + pupilCount + "\n");
		for(Map.Entry m:symptomsSortedMap.entrySet()){
			writer.write(m.getKey()+ " : " +m.getValue() + String.format("%n"));
		}

		writer.close();
	}
}
