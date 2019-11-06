package com.github.mauricioaniche.ck.util;

import java.nio.file.Files;
import java.nio.file.Paths;


public class FileUtils {

	public static String[] getAllDirs(String path) {
		try {
			return Files.walk(Paths.get(path))
					.filter(Files::isDirectory)
					.filter(x -> !x.toAbsolutePath().toString().contains(".git"))
					.map(x -> x.toAbsolutePath().toString())
					.toArray(String[]::new);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String[] getAllJavaFiles(String path) {

		try {
			return Files.walk(Paths.get(path))
					.filter(Files::isRegularFile)
					.filter(x -> !x.toAbsolutePath().toString().contains(".git"))
					.filter(x -> x.toAbsolutePath().toString().toLowerCase().endsWith("java"))
					.map(x -> x.toAbsolutePath().toString())
					.toArray(String[]::new);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static String[] getAllJars(String path) {

		try {
			return Files.walk(Paths.get(path))
					.filter(Files::isRegularFile)
					.filter(x -> !x.toAbsolutePath().toString().contains(".git"))
					.filter(x -> x.toAbsolutePath().toString().toLowerCase().endsWith("jar"))
					.map(x -> x.toAbsolutePath().toString())
					.toArray(String[]::new);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
