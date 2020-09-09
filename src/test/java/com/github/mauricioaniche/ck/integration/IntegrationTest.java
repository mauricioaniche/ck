package com.github.mauricioaniche.ck.integration;

import static com.github.mauricioaniche.ck.AssertResult.assertResultNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.github.mauricioaniche.ck.CK;
import com.github.mauricioaniche.ck.CKClassResult;
import com.github.mauricioaniche.ck.CKNotifier;
import com.google.common.io.Files;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * These tests mostly look for crashes ("notifyError") or for objects that come
 * back with nulls or negative numbers in large open source systems.
 *
 * They require internet connection and might take a while to run.
 */
@TestInstance(Lifecycle.PER_CLASS)
class IntegrationTest {

    private final static String APACHE_COMMONS_LANG_GIT_URL = "https://github.com/apache/commons-lang.git";

    private static final String[] REPO_URLS = new String[] { APACHE_COMMONS_LANG_GIT_URL,
            "https://github.com/apache/commons-math.git" };

    private final Map<String, String> urlTmpDirMap = new HashMap<>();

    private final CKNotifier ckNotifier = new CKNotifier() {
        @Override
        public void notify(CKClassResult result) {
            assertResultNotNull(result);
        }

        @Override
        public void notifyError(String sourceFilePath, Exception e) {
            Assertions.fail(sourceFilePath, e);
        }
    };

    private final CK ck = new CK();

    @BeforeAll
    void cloneRepos() throws GitAPIException {
        for (String url : REPO_URLS) {
            String dstDir = Files.createTempDir().getAbsolutePath();
            clone(url, dstDir);
            urlTmpDirMap.put(url, dstDir);
        }
    }

    @AfterAll
    void deleteTempDir() throws IOException {
        for (String tmpDir : urlTmpDirMap.values()) {
            FileUtils.deleteDirectory(new File(tmpDir));
        }
    }

    @Test
    void checkForCrashes() throws Exception {
        for (String repoFolder : urlTmpDirMap.values()) {
            ck.calculate(repoFolder, ckNotifier);
        }
    }

    @Test
    void testIndividualFiles() throws GitAPIException {
        Path tempFolderPath = Paths.get(urlTmpDirMap.get(APACHE_COMMONS_LANG_GIT_URL));
        String classFolder = "src/main/java/org/apache/commons/lang3/";

        String[] inputFiles = new String[] { "CharEncoding.java", "RegExUtils.java", "SystemUtils.java",
                "ThreadUtils.java" };

        String[] absInputPaths = Stream.of(inputFiles)
                .map(inputFile -> tempFolderPath.resolve(classFolder).resolve(inputFile).toString())
                .toArray(String[]::new);

        ck.calculate(tempFolderPath.toString(), ckNotifier, absInputPaths);
    }

    private void clone(String uri, String dstDir) throws GitAPIException {
        try (Git git = Git.cloneRepository().setDirectory(new File(dstDir)).setURI(uri).setCloneAllBranches(true)
                .call()) {
        }
    }
}
