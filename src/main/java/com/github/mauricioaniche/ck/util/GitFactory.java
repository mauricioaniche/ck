package com.github.mauricioaniche.ck.util;

import java.io.IOException;
import java.util.function.Consumer;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.errors.CorruptObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;



/**
 * A simple toolkit for JGit
 * @author leandroungari
 *
 */
public class GitFactory {

	private static Git git;
	
	public GitFactory() {
	
	}
	
	public String absoluteFolder() {
		
		String absolutePath = git
			.getRepository()
			.getDirectory()
			.getAbsolutePath();
		
		return absolutePath.substring(0, absolutePath.length() - 4);
	}
	
	public String folder() {
		
		var list = git
		.getRepository()
		.getDirectory()
		.getAbsolutePath()
		.split(java.util.regex.Pattern.quote("/"));
		
		return list[list.length - 2];
	}
	
	public void setRepository(Git g) {
		git = g;
	}
	
	
	private RevWalk walk() {
		
		return new RevWalk(git.getRepository());
	}
	
	private TreeWalk treeWalk() {
			
		return new TreeWalk(git.getRepository());
	}
	
	private RevCommit commit(ObjectId id) 
		throws MissingObjectException, 
						IncorrectObjectTypeException, 
						IOException {
		
		var walk = walk();
		var commit = walk.parseCommit(id);
		walk.close();
		
		return commit;
	}
	
	public void treeStart(
			ObjectId id,
			Consumer<? super TreeWalk> action
		) throws MissingObjectException, 
							IncorrectObjectTypeException, 
							CorruptObjectException, 
							IOException {
			
		var treeWalker = treeWalk();
		treeWalker.addTree(commit(id).getTree());
		treeWalker.setRecursive(true);
		treeWalker.setPostOrderTraversal(false);
		
		while(treeWalker.next()) {
			action.accept(treeWalker);
		}
		
		treeWalker.close();
	}

	public String fileContent(ObjectId id) 
		throws MissingObjectException, 
						IOException {
		
		var loader = git
			.getRepository()
			.open(id);
		return new String(loader.getBytes());
	}

}

