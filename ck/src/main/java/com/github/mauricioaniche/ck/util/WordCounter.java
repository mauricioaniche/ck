package com.github.mauricioaniche.ck.util;

import java.util.*;
import java.util.stream.Collectors;

public class WordCounter {

	private static Set<String> javaKeywords;

	static {
		javaKeywords = new HashSet<String>(){{
			add("abstract");
			add("continue");
			add("for");
			add("new");
			add("switch");
			add("assert***");
			add("default");
			add("goto*");
			add("package");
			add("synchronized");
			add("boolean");
			add("do");
			add("if");
			add("private");
			add("this");
			add("break");
			add("double");
			add("implements");
			add("protected");
			add("throw");
			add("byte");
			add("else");
			add("import");
			add("public");
			add("throws");
			add("case");
			add("enum****");
			add("instanceof");
			add("return");
			add("transient");
			add("catch");
			add("extends");
			add("int");
			add("short");
			add("try");
			add("char");
			add("final");
			add("interface");
			add("static");
			add("void");
			add("class");
			add("finally");
			add("long");
			add("strictfp**");
			add("volatile");
			add("const*");
			add("float");
			add("native");
			add("super");
			add("while");

			add("String");
		}};
	}


	public static Set<String> wordsIn(String fullString) {
		String[] cleanString = fullString
				.replace("\t", " ")
				.replace("\n", " ")
				.replace("\r", " ")
				.replace("(", " ")
				.replace(")", " ")
				.replace("{", " ")
				.replace("}", " ")
				.replace("=", " ")
				.replace(">", " ")
				.replace(">", " ")
				.replace("&", " ")
				.replace("|", " ")
				.replace("!", " ")
				.replace("+", " ")
				.replace("*", " ")
				.replace("/", " ")
				.replace("-", " ")
				.replace(";", " ")
				.split(" ");

		List<String> strings = Arrays.stream(cleanString).filter(word -> !javaKeywords.contains(word))
				.filter(word -> !word.isEmpty())
				.filter(word -> word.matches("\\w*"))
				.filter(word -> !word.matches("[0-9]*"))
				.collect(Collectors.toList());

		HashSet<String> words = new HashSet<>();
		for(String string : strings) {
			words.addAll(breakString(string));
		}

		return words;

	}

	private static Collection<? extends String> breakString(String string) {

		if(string.length() == 1)
			return Arrays.asList(string);

		int current = 0;
		List<String> words = new ArrayList<>();

		for(int i = 1; i < string.length(); i++) {
			if(string.charAt(i) == '_' || Character.isUpperCase(string.charAt(i))) {
				String wordToAdd = string.substring(current, i);
				words.add(wordToAdd);
				current = i + (string.charAt(i) == '_' ? 1 : 0);
			}
		}
		String remainingWord = string.substring(current);
		words.add(remainingWord);
		return words;
	}


	public static String removeSpacesAndIdentation(String toString) {
		return toString
				.trim()
				.replace("\r", " ")
				.replace("\t", " ")
				.replace("\n", " ")
				.replaceAll(" +", " ");
	}

}
