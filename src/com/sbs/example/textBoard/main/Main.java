package com.sbs.example.textBoard.main;

import com.sbs.example.textBoard.AppConfig;
import com.sbs.example.textBoard.util.Util;

public class Main {
	
	public static void main(String[] args) {
		
		new App().run();
		
//		testApi();
		
	}

	private static void testApi() {
		
		AppConfig ac = new AppConfig();
		
		String url = "https://disqus.com/api/3.0/forums/listThreads.json"; 
		
		String rs = Util.callApi(url, "api_key=" + ac.getDisqusApiKey(), "forum=" + ac.getDisqusForumName(), "thread:ident=article_detail_15.html");
		System.out.println(rs);
		
	}

}
