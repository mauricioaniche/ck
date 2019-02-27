package com.github.mauricioaniche.ck.metric;

import com.github.mauricioaniche.ck.CK;
import com.github.mauricioaniche.ck.CKNotifier;
import com.github.mauricioaniche.ck.CKNumber;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

abstract class BaseTest {

	protected static String fixturesDir() {
		try {
			String cfgFile = new File(BaseTest.class.getResource("/").getPath() + "../../fixtures/").getCanonicalPath();
			return cfgFile;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected static Map<String, CKNumber> run(String dir) {
		Map<String, CKNumber> map = new HashMap<>();
		new CK().calculate(dir, new CKNotifier() {
			@Override
			public void notify(CKNumber result) {
				map.put(result.getClassName(), result);
			}
		});

		return map;
	}
}
