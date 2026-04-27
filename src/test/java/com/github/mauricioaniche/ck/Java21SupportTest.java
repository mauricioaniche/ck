package com.github.mauricioaniche.ck;

import org.eclipse.jdt.core.dom.AST;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Java21SupportTest extends BaseTest {

	@Test
	public void recordPatternsRequireJls21Ast() {
		UnsupportedOperationException exception = assertThrows(
			UnsupportedOperationException.class,
			() -> AST.newAST(AST.JLS11).newRecordPattern()
		);

		assertEquals("Operation only supported in JLS21 AST", exception.getMessage());
	}

	@Test
	public void parsesRecordPatterns() {
		Map<String, CKClassResult> report = run(fixturesDir() + "/java21");

		assertEquals(2, report.size());
		assertEquals("class", report.get("java21.Java21RecordPattern").getType());
		assertEquals("record", report.get("java21.Java21RecordPattern$Payment").getType());
	}
}
