package com.ninj.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class FileUtil {

	public static void removeContentsOfDir(String path) {
		File working_dir = new File(path);
		File[] files = working_dir.listFiles();
		for (File f : files) {
			f.delete();
		}
	}
	
}
