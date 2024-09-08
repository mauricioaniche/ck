package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JavadocLinesTest extends BaseTest {

	@BeforeAll
	void setUp() {
		report = run(fixturesDir() + "/javadoc");
	}

	@ParameterizedTest
	@MethodSource("examples")
	void getJavaDoc(String className, String methodName, boolean expectedResult) {
		assertEquals(expectedResult, report.get(className).getMethod(methodName).get().getHasJavadoc());
	}

	static Stream<Arguments> examples() {
		return Stream.of(
			// method not containing javadoc
			Arguments.of("javadoc.JDoc1", "m0/0", false),
			// simple javadoc, no params
			Arguments.of("javadoc.JDoc1", "m1/0", true),
			// with @return
			Arguments.of("javadoc.JDoc1", "m2/0", true),
			// with @param and @return
			Arguments.of("javadoc.JDoc1", "m3/1[int]", true),
			// with @throw
			Arguments.of("javadoc.JDoc1", "m3/1[int]", true),

			// some anonymous methods with javadoc should not trick the analysis
			Arguments.of("javadoc.JDoc2", "m0/0", false),
			Arguments.of("javadoc.JDoc2", "m1/0", true),

			// javadoc in the class should not affect anything
			Arguments.of("javadoc.JDoc3", "m0/0", false),
			Arguments.of("javadoc.JDoc3", "m1/0", true)
		);
	}

}
