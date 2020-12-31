package com.sbs.example.textBoard.main;

import com.sbs.example.textBoard.AppConfig;
import com.sbs.example.textBoard.util.Util;

public class Main {
	
	public static void main(String[] args) {
		
		new App().run();
		
//		testApi();
		
	}

	private static void testApi() {
		
		String keyFilePath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
		System.out.println(keyFilePath);
		
	}

}
