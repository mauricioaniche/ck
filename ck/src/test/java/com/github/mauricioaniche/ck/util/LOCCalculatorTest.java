package com.github.mauricioaniche.ck.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LOCCalculatorTest {


	@Test
	public void noComments() {
		StringBuilder sb = new StringBuilder();
		sb.append("class A {\r\n");
		sb.append("\tprivate int 1;\r\n");
		sb.append("\tvoid m1() {\r\n");
		sb.append("\t\tSystem.out.println(\"aa\");\r\n");
		sb.append("}\r\n");

		int loc = LOCCalculator.calculate(sb.toString());
		Assertions.assertEquals(5, loc);
	}

	@Test
	public void noSlashR() {
		StringBuilder sb = new StringBuilder();
		sb.append("class A {\n");
		sb.append("private int 1;\n");
		sb.append("void m1() {\n");
		sb.append("System.out.println(\"aa\");\n");
		sb.append("}\n");


		int loc = LOCCalculator.calculate(sb.toString());
		Assertions.assertEquals(5, loc);
	}

	@Test
	public void singleLineComment() {
		StringBuilder sb = new StringBuilder();
		sb.append("class A {\n");
		sb.append("\t// comment\n");
		sb.append("\t//comment\n");
		sb.append("private int 1;\n");
		sb.append("void m1() {\n");
		sb.append("System.out.println(\"aa\");\n");
		sb.append("}\n");


		int loc = LOCCalculator.calculate(sb.toString());
		Assertions.assertEquals(5, loc);
	}

	@Test
	public void singleLineCommentWithStar() {
		StringBuilder sb = new StringBuilder();
		sb.append("class A {\n");
		sb.append("\t/* comment */\n");
		sb.append("\t/*comment*/\n");
		sb.append("private int 1;\n");
		sb.append("void m1() {\n");
		sb.append("System.out.println(\"aa\");\n");
		sb.append("}\n");


		int loc = LOCCalculator.calculate(sb.toString());
		Assertions.assertEquals(5, loc);
	}

	@Test
	public void multiLineComment() {
		StringBuilder sb = new StringBuilder();
		sb.append("class A {\n");
		sb.append("\t/* comment\n");
		sb.append("\t * comment\n");
		sb.append("\t */\n");
		sb.append("\t/** comment\n");
		sb.append("\t * comment\n");
		sb.append("\t */\n");
		sb.append("private int 1;\n");
		sb.append("void m1() {\n");
		sb.append("System.out.println(\"aa\");\n");
		sb.append("}\n");


		int loc = LOCCalculator.calculate(sb.toString());
		Assertions.assertEquals(5, loc);
	}

	@Test
	public void codeAndComment() {
		StringBuilder sb = new StringBuilder();
		sb.append("class A {\n");
		sb.append("private int 1;\n");
		sb.append("void m1() { // comment \n");
		sb.append("System.out.println(\"aa\");//comment\n");
		sb.append("}\n");


		int loc = LOCCalculator.calculate(sb.toString());
		Assertions.assertEquals(5, loc);
	}

	@Test
	public void codeAndCommentAndCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("class A {\n");
		sb.append("private int 1;\n");
		sb.append("void m1() { /* comment */ int a; \n");
		sb.append("System.out.println(\"aa\");//comment\n");
		sb.append("}\n");


		int loc = LOCCalculator.calculate(sb.toString());
		Assertions.assertEquals(5, loc);
	}

	@Test
	public void commentEndOfFile() {
		StringBuilder sb = new StringBuilder();
		sb.append("class A {\n");
		sb.append("private int 1;\n");
		sb.append("void m1() { // comment \n");
		sb.append("System.out.println(\"aa\");//comment\n");
		sb.append("}// comment\n");
		sb.append("// comment");


		int loc = LOCCalculator.calculate(sb.toString());
		Assertions.assertEquals(5, loc);
	}

	@Test
	public void lambda() {
		StringBuilder sb = new StringBuilder();
		sb.append("class A {\n");
		sb.append("private int 1;\n");
		sb.append("void m1() { // comment \n");
		sb.append("() -> { }; //comment\n");
		sb.append("() -> { /* aa */ };\n");
		sb.append("}// comment\n");
		sb.append("// comment");


		int loc = LOCCalculator.calculate(sb.toString());
		Assertions.assertEquals(6, loc);
	}

	@Test
	public void noEndOfLine() {
		StringBuilder sb = new StringBuilder();
		sb.append("class A {\r\n");
		sb.append("\tprivate int 1;\r\n");
		sb.append("\tvoid m1() {\r\n");
		sb.append("\t\tSystem.out.println(\"aa\");\r\n");
		sb.append("}");

		int loc = LOCCalculator.calculate(sb.toString());
		Assertions.assertEquals(5, loc);
	}


	@Test
	public void emptyLines() {
		StringBuilder sb = new StringBuilder();
		sb.append("class A {\r\n");
		sb.append("\r\n");
		sb.append("\tprivate int 1;\r\n");
		sb.append("\r\n");
		sb.append("\tvoid m1() {\r\n");
		sb.append("\t\tSystem.out.println(\"aa\");\r\n");
		sb.append("\r\n");
		sb.append("\r\n");
		sb.append("\r\n");
		sb.append("\r\n");
		sb.append("}");
		sb.append("\r\n");

		int loc = LOCCalculator.calculate(sb.toString());
		Assertions.assertEquals(5, loc);
	}



}
