package com.github.mauricioaniche.ck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Test;

class IndividualFilesWithContextTest {

  private final CK ck = new CK();
  private final CKNotifier ckNotifier = new CKNotifier() {

    @Override
    public void notify(CKClassResult classResult) {
      assertNotNull(classResult);
      results.add(classResult);
    }

    @Override
    public void notifyError(String sourceFilePath, Exception e) {
      fail(String.format("Ck threw an error during processor for filepath %s", sourceFilePath), e);
    }

  };

  private final Path fixturesDir = Paths.get("fixtures");
  private final Path realWorldDir = fixturesDir.resolve("real-world");
  private final Set<CKClassResult> results = new HashSet<>();

  @Test
  void testRealWorldDir1File() {
    ck.calculate(realWorldDir, ckNotifier, Paths.get("Unauthorized401Interceptor.java"));
    assertEquals(1, results.size());
    CKClassResult result = results.iterator().next();
    assertEquals("org.asynchttpclient.netty.handler.intercept.Unauthorized401Interceptor", result.getClassName());
  }

  @Test
  void testRealWorldDir2Files() {
    ck.calculate(realWorldDir, ckNotifier, Paths.get("Unauthorized401Interceptor.java"),
        Paths.get("XMLInputFactory.java"));
    assertEquals(2, results.size());
    Iterator<CKClassResult> it = results.iterator();
    assertEquals("javax.xml.stream.XMLInputFactory", it.next().getClassName());
    assertEquals("org.asynchttpclient.netty.handler.intercept.Unauthorized401Interceptor", it.next().getClassName());

  }

  @Test
  void testRealWorldDirNFiles() throws IOException {
    Path[] files = Files.walk(realWorldDir).map(Path::getFileName).toArray(Path[]::new);
    ck.calculate(realWorldDir, ckNotifier, files);
    assertEquals(55, results.size());
  }

  @Test
  void testConvenienceMethod() {
    ck.calculate(realWorldDir, ckNotifier);
    assertEquals(55, results.size());
  }

}
