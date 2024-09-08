package com.github.mauricioaniche.ck.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

// This file uses OS-specific tests to ensure that the file separator specified in FileUtils works!
public class FileUtilsTest {

  private static final List<String> IGNORED_CUSTOM = new ArrayList<>();

  static {
    IGNORED_CUSTOM.addAll(FileUtils.IGNORED_DIRECTORIES);
    IGNORED_CUSTOM.add(String.format("%cfoo%c", File.separatorChar, File.separatorChar));
  }

  //Only enabled on Linux/Mac, marking a folder as hidden on Windows programmatically is far from trivial.
  @EnabledOnOs({OS.LINUX, OS.MAC})
  @Test
  public void hiddenHidden(@TempDir Path dir) {
    File file = new File(dir.toFile(), ".hidden/");
    file.mkdir();
    Assertions.assertTrue(FileUtils.isHiddenDir(file.toPath()));
  }

  //Only enabled on Linux/Mac, marking a folder as hidden on Windows programmatically is far from trivial.
  @EnabledOnOs({OS.LINUX, OS.MAC})
  @Test
  public void hiddenVisible(@TempDir Path dir) {
    File file = new File(dir.toFile(), "visible/");
    file.mkdir();
    Assertions.assertFalse(FileUtils.isHiddenDir(file.toPath()));
  }

  @EnabledOnOs({OS.LINUX, OS.MAC})
  @Test
  public void basicGitTestAllowedLinux() {
    Assertions.assertFalse(FileUtils.isIgnoredDir("/home/bob/myprojects/", FileUtils.IGNORED_DIRECTORIES));
  }

  @EnabledOnOs({OS.LINUX, OS.MAC})
  @Test
  public void basicGitTestDeniedLinux() {
    Assertions.assertTrue(FileUtils.isIgnoredDir("/home/bob/myprojects/.git/", FileUtils.IGNORED_DIRECTORIES));
  }

  @EnabledOnOs(OS.WINDOWS)
  @Test
  public void basicGitTestAllowedWindows() {
    Assertions.assertFalse(FileUtils.isIgnoredDir("C:\\Users\\Bob\\myprojects\\", IGNORED_CUSTOM));
  }

  @EnabledOnOs(OS.WINDOWS)
  @Test
  public void basicGitTestDeniedWindows() {
    Assertions.assertTrue(FileUtils.isIgnoredDir("C:\\Users\\Bob\\myprojects\\.git\\", IGNORED_CUSTOM));
  }

  @EnabledOnOs({OS.LINUX, OS.MAC})
  @Test
  public void customTestAllowedLinux() {
    Assertions.assertFalse(FileUtils.isIgnoredDir("/home/bob/myprojects/bar/", IGNORED_CUSTOM));
  }

  @EnabledOnOs({OS.LINUX, OS.MAC})
  @Test
  public void customTestDeniedLinux() {
    Assertions.assertTrue(FileUtils.isIgnoredDir("/home/bob/myprojects/foo/", IGNORED_CUSTOM));
  }

  @EnabledOnOs(OS.WINDOWS)
  @Test
  public void customTestAllowedWindows() {
    Assertions.assertFalse(FileUtils.isIgnoredDir("C:\\Users\\Bob\\myprojects\\bar\\", IGNORED_CUSTOM));
  }

  @EnabledOnOs(OS.WINDOWS)
  @Test
  public void customTestDeniedWindows() {
    Assertions.assertTrue(FileUtils.isIgnoredDir("C:\\Users\\Bob\\myprojects\\foo\\", IGNORED_CUSTOM));
  }

}
