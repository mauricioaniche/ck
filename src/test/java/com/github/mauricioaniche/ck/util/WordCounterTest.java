package com.github.mauricioaniche.ck.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class WordCounterTest {

	@Test
	public void countWords() {
		String sourceCode = "a b c d r$ a@ h^";

		Set<String> words = WordCounter.wordsIn(sourceCode);
		Assert.assertEquals(4, words.size());
		Assert.assertTrue(words.contains("a"));
		Assert.assertTrue(words.contains("b"));
		Assert.assertTrue(words.contains("c"));
		Assert.assertTrue(words.contains("d"));
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
		Assert.assertEquals(4, words.size());
		Assert.assertTrue(words.contains("Test"));
		Assert.assertTrue(words.contains("metodo"));
		Assert.assertTrue(words.contains("taxes"));
		Assert.assertTrue(words.contains("interests"));

	}

	@Test
	public void breakWords() {
		String sourceCode = "thisIsABigWord another_word_like_this now_weMix";
		Set<String> words = WordCounter.wordsIn(sourceCode);

		Assert.assertEquals(11, words.size());

		String[] expected = new String[] { "A", "Big", "Word", "like", "another", "now", "this", "Is", "word", "Mix", "we"};
		for (String expectedString : expected) {
			Assert.assertTrue(words.contains(expectedString));
		}
	}

}
