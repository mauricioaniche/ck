package com.github.mauricioaniche.ck.util;

import java.io.IOException;

import org.eclipse.jgit.lib.ObjectId;

/**
 * It defines a file in a git repository
 * @author leandroungari
 *
 */
public class File {

	private String name;
	private ObjectId id;
	
	public File(String n, ObjectId i) {
		name = n;
		id = i;
	}
	
	public ObjectId id() {
		return id;
	}
	
	public String name() {
		return name;
	}
	
	public String get() {
		var factory = new GitFactory();
		
		try {
			return factory.fileContent(id);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		return null;
	}
}
