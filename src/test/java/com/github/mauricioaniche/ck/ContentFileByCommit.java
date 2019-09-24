package com.github.mauricioaniche.ck;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.ObjectId;
import org.junit.Test;

import com.github.mauricioaniche.ck.util.GitFactory;

/**
 * This test use the files of a commit in a git repository
 * 
 * The difference when compared to the original implementation
 * is that we can analyse multiple versions (commits in different branches) of source code,
 * not only the current version in local folder.
 * 
 * It eliminates the hard-work to manipulate the versions of
 * repository externally, which updates the state of files in 
 * each version.
 * 
 * This implementation uses JGit dependency
 * 
 * @since  Sep 24, 2019
 * @author leandroungari
 *
 */
public class ContentFileByCommit {

	
	private static final String REPO_URL = "https://github.com/danilofes/refactoring-toy-example";
	private static final String REPO_LOCAL_FOLDER = "/home/leandroungari/Documents/repositories/refactoring-toy-example";
	//this commit is the latest in master branch, just for example
	private static final String COMMIT_ID = "d4bce13a443cf12da40a77c16c1e591f4f985b47";
	
	@Test
	public void original() {
		
		
		System.out.println("=== Original ===");
		new CK().calculate(
			REPO_LOCAL_FOLDER, 
			result -> {
				
				System.out.println(result.getFile());
				System.out.println("CBO: " + 	result.getCbo());
		});
	}
	
	@Test
	public void myImplementation() throws IOException {
		
		System.out.println("=== My Implementation ===");
		
		var git = Git.open(new File(
			REPO_LOCAL_FOLDER
		));
		
		var factory = new GitFactory();
		factory.setRepository(git);
		
		new CK().calculate(
			ObjectId
			.fromString(COMMIT_ID), 
			result -> {
				
				System.out.println(result.getFile());
				System.out.println("CBO: " + 	result.getCbo());
		});
	}
	
}
