package com.github.mauricioaniche.ck.util;

import java.util.*;
import java.util.stream.Collectors;

public class MethodCounter {
	public static Map<String, Integer> count(String methodList) {
		Map<String, Integer> methodInvocation = new HashMap<>();

		String[] methodNames = methodList.split(";");
		for (String name : methodNames) {
			methodInvocation.put(name, methodInvocation.getOrDefault(name, 0) + 1);
		}

		return methodInvocation;
	}

	public static Map<String, Integer> sort(Map<String, Integer> methodInvocation) {
		return methodInvocation.entrySet().stream()
				.sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new));
	}

	public static String formatResult(Map<String, Integer> methodInvocation) {
		return methodInvocation.entrySet().stream()
				.map(entry -> entry.getKey() + ": " + entry.getValue())
				.collect(Collectors.joining(", "));
	}

}
