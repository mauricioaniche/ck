package com.github.mauricioaniche.ck.integration;

import com.github.mauricioaniche.ck.CK;
import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKNotifier;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.github.mauricioaniche.ck.AssertResult.assertResultNotNull;

/**
 * These tests mostly look for crashes ("notifyError") or for objects that come
 * back with nulls or negative numbers in large open source systems.
 *
 * They require internet connection and might take a while to run.
 */
public class IntegrationTest {

    private final String tempFolder = Files.createTempDir().getAbsolutePath();

    @AfterEach
    void deleteTempDir() throws IOException {
        FileUtils.deleteDirectory(new File(this.tempFolder));
    }

    @ParameterizedTest
    @CsvSource({ "https://github.com/apache/commons-lang.git", "https://github.com/apache/commons-math.git" })
    void checkForCrashes(String uri) throws Exception {
        clone(uri);

        new CK().calculate(tempFolder, new CKNotifier() {
            @Override
            public void notify(CKClassResult result) {
                assertResultNotNull(result);
            }

            @Override
            public void notifyError(String sourceFilePath, Exception e) {
                Assertions.fail(sourceFilePath, e);
            }
        });
    }

    @Test
    void testIndividualFiles() throws GitAPIException {
        clone("https://github.com/apache/commons-lang.git");
        Path tempFolderPath = Paths.get(tempFolder);
        String classFolder = "src/main/java/org/apache/commons/lang3/";

        String[] inputFiles = new String[] { "CharEncoding.java", "RegExUtils.java", "SystemUtils.java",
                "ThreadUtils.java" };

        String[] absInputPaths = Stream.of(inputFiles)
                .map(inputFile -> tempFolderPath
                    .resolve(classFolder)
                    .resolve(inputFile)
                    .toString()
                )
                .toArray(String[]::new);

        new CK().calculate(tempFolder, new CKNotifier() {
            @Override
            public void notify(CKClassResult result) {
                assertResultNotNull(result);
            }

            @Override
            public void notifyError(String sourceFilePath, Exception e) {
                Assertions.fail(sourceFilePath, e);
            }
        }, absInputPaths);
    }

    private void clone(String uri) throws GitAPIException {
        try (Git git = Git.cloneRepository().setDirectory(new File(tempFolder)).setURI(uri).setCloneAllBranches(true)
                .call()) {
        }
        ;
    }
}
