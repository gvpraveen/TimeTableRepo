package com.classTimetTable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AssignCoTeacher {

	public void assigTeacher(Map<String,Map<String,List<Map<String,String>>>> finalMap) {
		List timesList = Arrays.asList("8:00 AM","9:00 AM","10:00 AM","11:00 AM","12:00 PM","1:00 PM","2:00 PM","3:00 PM","4:00 PM");
		finalMap.forEach((k, v1) -> {
			if(k != "Leasure") {
				List<String> leasureList = new ArrayList(timesList);
				v1.forEach((day, dayList) -> {
					Map<String, String> leasureMap = finalMap.get("Leasure").get(day).stream()
						    .flatMap(map -> map.entrySet().stream())
						    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
					List al = new ArrayList<>(dayList.stream().flatMap(m -> m.keySet().stream().map(obj -> obj.toString())).collect(Collectors.toList()));
					leasureList.removeAll(al);
					leasureList.forEach(obj -> {
						Map dayMap = new HashMap<>();
						dayMap.put(obj, leasureMap.get(obj));
						dayList.add(dayMap);
					});
					
				});
			}
		});
		System.out.println(finalMap);
	}

}
