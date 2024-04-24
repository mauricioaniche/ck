package com.github.mauricioaniche.ck.util;

import com.github.mauricioaniche.ck.metric.ClassLevelMetric;
import com.github.mauricioaniche.ck.metric.MethodLevelMetric;
import com.github.mauricioaniche.ck.metric.VariableOrFieldMetric;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MetricsFinder {

	private static List<Class<? extends MethodLevelMetric>> methodLevelClasses = null;
	private static List<Class<? extends ClassLevelMetric>> classLevelClasses = null;
	private DependencySorter sorter;

	public MetricsFinder(DependencySorter sorter) {
		this.sorter = sorter;
	}

	public MetricsFinder() {
		this(new DependencySorter());
	}

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

	public List<ClassLevelMetric> allClassLevelMetrics(boolean verbose) {

		if(classLevelClasses == null)
			loadClassLevelClasses();

		try {
			ArrayList<ClassLevelMetric> metrics = new ArrayList<>();
			for (Class<? extends ClassLevelMetric> aClass : classLevelClasses) {
				boolean isVerbose = Arrays.stream(aClass.getFields())
						.anyMatch(f -> f.getName().equals("IS_VERBOSE")) ? aClass.getField("IS_VERBOSE").getBoolean(null) : false;

				if (verbose) {
					metrics.add(aClass.getDeclaredConstructor().newInstance());
				}
				else {
					if (!isVerbose) {
						metrics.add(aClass.getDeclaredConstructor().newInstance());
					}
				}
			}

			return metrics;
		} catch(Exception e) {
			throw new RuntimeException("Could not instantiate a method level metric. Something is really wrong", e);
		}
	}

	private void loadMethodLevelClasses(boolean variablesAndFields) {
		try {
			Reflections reflections = new Reflections("com.github.mauricioaniche.ck.metric");

			methodLevelClasses = sorter.sort(reflections.getSubTypesOf(MethodLevelMetric.class).stream()
					.filter(x -> variablesAndFields || !Arrays.asList(x.getInterfaces()).contains(VariableOrFieldMetric.class))
					.collect(Collectors.toList()));

		} catch(Exception e) {
			throw new RuntimeException("Could not find method level metrics. Something is really wrong", e);
		}
	}

	private void loadClassLevelClasses() {
		try {
			Reflections reflections = new Reflections("com.github.mauricioaniche.ck.metric");
			classLevelClasses = sorter.sort(new ArrayList<>(reflections.getSubTypesOf(ClassLevelMetric.class)));
		} catch(Exception e) {
			throw new RuntimeException("Could not find class level metrics. Something is really wrong", e);
		}
	}


}
