package br.com.aniche.ck.metric;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

abstract class BaseTest {

	protected String p1dir() {
		try {
			String cfgFile = BaseTest.class.getResource("/project-dir.txt").getPath();
			return FileUtils.readFileToString(new File(cfgFile));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
