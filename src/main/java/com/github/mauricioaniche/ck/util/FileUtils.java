package com.github.mauricioaniche.ck.util;

import java.io.File;
import java.util.ArrayList;


public class FileUtils {

	public static String[] getAllDirs(String path) {
		ArrayList<String> dirs = new ArrayList<String>();
		getAllDirs(path, dirs);
		
		String[] ar = new String[dirs.size()];
		ar = dirs.toArray(ar);
		return ar;
	}
	
	private static void getAllDirs(String path, ArrayList<String> dirs) {
		
		File f = new File(path);
		if(f.getName().equals(".git")) return;
		
		for(File inside : f.listFiles()) {
			if(inside.isDirectory()) {
				String newDir = inside.getAbsolutePath();
				dirs.add(newDir);
				getAllDirs(newDir, dirs);
			}
		}
	}

	public static String[] getAllJavaFiles(String path) {
		ArrayList<String> files = new ArrayList<String>();
		getAllJavaFiles(path, files);
		
		String[] ar = new String[files.size()];
		ar = files.toArray(ar);
		return ar;
	}
	
	private static void getAllJavaFiles(String path, ArrayList<String> files) {
		
		File f = new File(path);
		if(f.getName().equals(".git")) return;
		
		for(File inside : f.listFiles()) {
			if(inside.isDirectory()) {
				String newDir = inside.getAbsolutePath();
				getAllJavaFiles(newDir, files);
			} else if(inside.getAbsolutePath().toLowerCase().endsWith(".java")) {
				files.add(inside.getAbsolutePath());
			}
		}
	}
	
	
}
