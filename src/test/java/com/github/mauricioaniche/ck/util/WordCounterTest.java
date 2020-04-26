package com.github.mauricioaniche.ck.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class WordCounterTest {

	@Test
	public void countWords() {
		String sourceCode = "a b c d r$ a@ h^";

		Set<String> words = WordCounter.wordsIn(sourceCode);
		Assertions.assertEquals(4, words.size());
		Assertions.assertTrue(words.contains("a"));
		Assertions.assertTrue(words.contains("b"));
		Assertions.assertTrue(words.contains("c"));
		Assertions.assertTrue(words.contains("d"));
	}

	@Test
	public void ignoreJavaKeywords() {
		String sourceCode = "package a.b.c;\n" +
				"class Test {\n" +
				"@Tests public void metodo() {\n" +
				"  int taxes = 10;\n" +
				"  double interests = 0;"+
				"\n} }";

		Set<String> words = WordCounter.wordsIn(sourceCode);
		Assertions.assertEquals(4, words.size());
		Assertions.assertTrue(words.contains("Test"));
		Assertions.assertTrue(words.contains("metodo"));
		Assertions.assertTrue(words.contains("taxes"));
		Assertions.assertTrue(words.contains("interests"));

	}

	@Test
	public void breakWords() {
		String sourceCode = "thisIsABigWord another_word_like_this now_weMix";
		Set<String> words = WordCounter.wordsIn(sourceCode);

		Assertions.assertEquals(11, words.size());

		String[] expected = new String[] { "A", "Big", "Word", "like", "another", "now", "this", "Is", "word", "Mix", "we"};
		for (String expectedString : expected) {
			Assertions.assertTrue(words.contains(expectedString));
		}
	}

	@Test
	public void mixOfCamelCase_and_underscore() {
		Set<String> words = WordCounter.wordsIn("longName_likeThis");

		Assertions.assertEquals(4, words.size());
	}

}
