package com.github.mauricioaniche.ck.util;

import com.github.mauricioaniche.ck.metric.ClassLevelMetric;
import com.github.mauricioaniche.ck.metric.MethodLevelMetric;
import com.github.mauricioaniche.ck.metric.VariableOrFieldMetric;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MetricsFinder {

	private static Set<Class<? extends MethodLevelMetric>> methodLevelClasses = null;
	private static Set<Class<? extends ClassLevelMetric>> classLevelClasses = null;

	public List<MethodLevelMetric> allMethodLevelMetrics(boolean variablesAndFields) {

		if(methodLevelClasses == null)
			loadMethodLevelClasses(variablesAndFields);

		try {
			ArrayList<MethodLevelMetric> metrics = new ArrayList<>();
			for (Class<? extends MethodLevelMetric> aClass : methodLevelClasses) {
				metrics.add(aClass.getDeclaredConstructor().newInstance());
			}

			return metrics;
		} catch(Exception e) {
			throw new RuntimeException("Could not instantiate a method level metric. Something is really wrong", e);
		}
	}

	private void loadMethodLevelClasses(boolean variablesAndFields) {
		try {
			Reflections reflections = new Reflections("com.github.mauricioaniche.ck.metric");
			methodLevelClasses = reflections.getSubTypesOf(MethodLevelMetric.class);
			if(!variablesAndFields)
				methodLevelClasses = methodLevelClasses.stream().filter(x -> !Arrays.asList(x.getInterfaces()).contains(VariableOrFieldMetric.class)).collect(Collectors.toSet());
		} catch(Exception e) {
			throw new RuntimeException("Could not find method level metrics. Something is really wrong", e);
		}
	}


	public List<ClassLevelMetric> allClassLevelMetrics() {

		if(classLevelClasses == null)
			loadClassLevelClasses();

		try {
			ArrayList<ClassLevelMetric> metrics = new ArrayList<>();
			for (Class<? extends ClassLevelMetric> aClass : classLevelClasses) {
				metrics.add(aClass.getDeclaredConstructor().newInstance());
			}

			return metrics;
		} catch(Exception e) {
			throw new RuntimeException("Could not instantiate a method level metric. Something is really wrong", e);
		}
	}

	private void loadClassLevelClasses() {
		try {
			Reflections reflections = new Reflections("com.github.mauricioaniche.ck.metric");
			classLevelClasses = reflections.getSubTypesOf(ClassLevelMetric.class);
		} catch(Exception e) {
			throw new RuntimeException("Could not find method level metrics. Something is really wrong", e);
		}
	}


}
