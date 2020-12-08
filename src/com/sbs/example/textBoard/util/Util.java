package com.sbs.example.textBoard.util;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Util {
	
	public static void writeFileContents(String filePath, String content) {
		
		BufferedOutputStream bs = null;
		
		try {
			
			bs = new BufferedOutputStream(new FileOutputStream(filePath));
			bs.write(content.getBytes());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
