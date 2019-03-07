package com.github.mauricioaniche.ck.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WordUtils {

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


	public static List<String> stringsIn(String fullString) {
		String[] words = fullString
				.replace("\t", " ")
				.replace("\n", " ")
				.replace("\r", " ")
				.replace("(", " ")
				.replace(")", " ")
				.replace("{", " ")
				.replace("}", " ")
				.split(" ");

		return Arrays.stream(words).filter(word -> !javaKeywords.contains(word))
				.filter(word -> !word.isEmpty())
				.filter(word -> word.matches("\\w*"))
				.collect(Collectors.toList());

	}
}
