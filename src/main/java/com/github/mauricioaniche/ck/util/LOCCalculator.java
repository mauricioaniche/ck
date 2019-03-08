package com.github.mauricioaniche.ck.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class LOCCalculator {

	private static Logger log = Logger.getLogger(LOCCalculator.class);
	
	public static int calculate(InputStream sourceCode) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(sourceCode));
			return SourceCodeLineCounter.getNumberOfLines(reader);
		} catch (IOException e) {
			log.error("Error when counting lines", e);
			return 0;
		}
	}

}
