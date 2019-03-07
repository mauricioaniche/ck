package com.github.mauricioaniche.ck.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class WordUtilsTest {

	@Test
	public void countWords() {
		String sourceCode = "a b c d r$ a@ h^";

		List<String> words = WordUtils.stringsIn(sourceCode);
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

		List<String> words = WordUtils.stringsIn(sourceCode);
		Assert.assertEquals(4, words.size());
		Assert.assertTrue(words.contains("Test"));
		Assert.assertTrue(words.contains("metodo"));
		Assert.assertTrue(words.contains("taxes"));
		Assert.assertTrue(words.contains("interests"));

	}

}
