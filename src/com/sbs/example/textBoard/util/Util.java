package com.sbs.example.textBoard.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Util {
	
	public static void writeFileContents(String filePath, String content) {
		
		BufferedOutputStream bs = null;
		
		try {
			
			bs = new BufferedOutputStream(new FileOutputStream(filePath));
			bs.write(content.getBytes());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
		
	public static boolean copy(String sourcePath, String targetPath) {
		
		Path source = Paths.get(sourcePath);
		
		Path target = Paths.get(targetPath);
		
		if(!Files.exists(target.getParent())) {
			
			try {
				Files.createDirectories(target.getParent());
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		try {
			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
		
		return true;
		
	}

	public static String getFileContents(String filePath) {
		
		String rs = null;
		
		FileInputStream fileStream = null;
		
		try {
			
			fileStream = new FileInputStream(filePath);
			byte[] readBuffer = new byte[fileStream.available()];
			
			while(fileStream.read(readBuffer) != -1) {			
			}
			
			rs = new String(readBuffer);
			
			fileStream.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
						
		return rs;
	}

	public static void mkdirs(String path) {
		
		File dir = new File(path);
		
		if(dir.exists() == false) {
			dir.mkdirs();
		}
	}

	public static boolean rmdir(String path) {
		return rmdir(new File(path));
	}

	public static boolean rmdir(File dirToBeDeleted) {
		File[] allContents = dirToBeDeleted.listFiles();
		if (allContents != null) {
			for (File file : allContents) {
				rmdir(file);
			}
		}

		return dirToBeDeleted.delete();
	}

}
