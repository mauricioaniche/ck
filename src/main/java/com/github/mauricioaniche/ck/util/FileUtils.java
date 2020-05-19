package com.github.mauricioaniche.ck.util;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
	//Get all directories from the directory at the given path.
	public static String[] getAllDirs(String path) {
		try {
			return Files.walk(Paths.get(path))
					.filter(Files::isDirectory)
					.filter(x -> !x.toAbsolutePath().toString().contains(".git/"))
					.map(x -> x.toAbsolutePath().toString())
					.toArray(String[]::new);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	//Get all java class files from the directory at the given path.
	public static String[] getAllJavaFiles(String path) {
		return getAllFiles(path, "java");
	}

	//Get all jars from the directory at the given path.
	public static String[] getAllJars(String path) {
		return getAllFiles(path, "jar");
	}

	//Get all files from of the given file ending from the directory at the given path.
	private static String[] getAllFiles(String path, String ending){
		try {
			return Files.walk(Paths.get(path))
					.filter(Files::isRegularFile)
					.filter(x -> !x.toAbsolutePath().toString().contains(".git/"))
					.filter(x -> x.toAbsolutePath().toString().toLowerCase().endsWith(ending))
					.map(x -> x.toAbsolutePath().toString())
					.toArray(String[]::new);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}