package org.patrickyu.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

public class FileUtils {

	public static void write(File file, CharSequence data) {
		try {
			org.apache.commons.io.FileUtils.write(file, data);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static String readFileToString(File file) {
		return readFileToString(file, "UTF-8");
	}

	public static String readFileToString(File file, String encoding) {
		try {
			return org.apache.commons.io.FileUtils.readFileToString(file, encoding);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getExtension(String filePath) {
		return FilenameUtils.getExtension(filePath);
	}

	public static String getBaseName(String filePath) {
		return FilenameUtils.getBaseName(filePath);
	}

	public static String getName(String filePath) {
		return FilenameUtils.getName(filePath);
	}

	public static void delete(File dir) {
		org.apache.commons.io.FileUtils.deleteQuietly(dir);
	}

	public static void copyDirectory(File srcDir, File destDir) {
		try {
			org.apache.commons.io.FileUtils.copyDirectory(srcDir, destDir);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
