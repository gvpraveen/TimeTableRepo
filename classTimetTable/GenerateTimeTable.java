package com.classTimetTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public class GenerateTimeTable {

	public static void main(String[] args) {
		List<String> allFiles = new ArrayList<String>(
				Arrays.asList("E:/restCall/backend_assignment/assignment/Teacher_wise_class_timetable_English.csv",
						"E:/restCall/backend_assignment/assignment/Teacher_wise_class_timetable_science.csv",
						"E:/restCall/backend_assignment/assignment/Teacher_wise_class_timetable_maths.csv",
						"E:/restCall/backend_assignment/assignment/Teacher_wise_class_timetable_hindi.csv",
						"E:/restCall/backend_assignment/assignment/Teacher_wise_class_timetable_kannada.csv"));
		String line = "";
		String splitBy = ",";
		try {
			Map<String, List> classMap = new TreeMap<String, List>();

			for (String subject : allFiles) {
				String subName = subject.substring(subject.lastIndexOf("_") + 1, subject.indexOf(".csv"));
				BufferedReader br = new BufferedReader(new FileReader(subject));
				int j = 0;
				while ((line = br.readLine()) != null) {
					if (j > 0) {
						String[] lineinFile = line.split(splitBy);
						List<String> wordList = Arrays.asList(lineinFile);
						List<String> copywordList = new ArrayList<>(wordList);
						for (int i = wordList.size() + 1; i <= 7; i++) {
							copywordList.add("");
						}
						classMap.put(subName + "&&" + copywordList.get(0), copywordList);
					}
					j++;
				}
				br.close();

			}

			Map<String, String> classMap1 = new HashMap();
			classMap.forEach((k, v) -> {
				String obj[] = k.split("&&");

				for (int i = 1; i < v.size(); i++) {

					String sub = v.get(i) + "@" + i + "@" + obj[1];

					classMap1.put(sub, obj[0]);

				}

			});

			Map<String, Object> m = new HashMap();

			classMap1.forEach((k, v) -> {

				String s[] = k.split("\\@");

				if (m.containsKey(s[0])) {
					String str = (String) m.get(s[0]);
					str = str + "#" + k + "@" + v;
					m.put(s[0], str);
				} else {
					m.put(s[0], k + "@" + v);
				}

			});

			m.forEach((k, v) -> {
				String s[] = ((String) v).split("\\#");
				List<String> clas = Arrays.asList(s);
				m.put(k, clas);
			});
String classArray[] = {"10th","9th","8th","6th"};
for(String classEach : classArray) {
	List<String> TenthList = (List) m.get(classEach);
	Map tenthFinalMap = new HashMap<>();
	for (String e : TenthList) {

		String data[] = e.split("\\@");
		
		if(data[1].equals("1")) {
			data[1] = "Monday";
		}else if (data[1].equals("2")) {
			data[1] = "Tuesday";
		}
		else if (data[1].equals("3")) {
			data[1] = "Wednesday";
		}
		else if (data[1].equals("4")) {
			data[1] = "Thursday";
		}
		else if (data[1].equals("5")) {
			data[1] = "Friday";
		}
		else if (data[1].equals("6")) {
			data[1] = "Saturday";
		}
		
		if (tenthFinalMap.containsKey(data[1])) {
			List tenthList = (List) tenthFinalMap.get(data[1]);
			Map timeSub = new HashMap<>();
			timeSub.put(data[2], data[3]);
			tenthList.add(timeSub);
			tenthFinalMap.put(data[1], tenthList);
		} else {
			List tenthList = new ArrayList<>();
			Map timeSub = new HashMap<>();
			timeSub.put(data[2], data[3]);
			tenthList.add(timeSub);
			tenthFinalMap.put(data[1], tenthList);
		}

	}
	System.out.println("=========="+classEach+"=================");
	System.out.println(tenthFinalMap);
}
} catch (IOException e) {
			e.printStackTrace();
}

	}

}