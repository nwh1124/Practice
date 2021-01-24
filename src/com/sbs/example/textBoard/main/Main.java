package com.sbs.example.textBoard.main;

import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.textBoard.container.Container;
import com.sbs.example.textBoard.dto.Article;

public class Main {
	
	public static void main(String[] args) {
		
		new App().run();
		
//		testApi();
		
	}

	private static void testApi() {
		
		MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "ssgDb");
		
		String tags = Container.articleService.getTagsByRelTypeCodeAndRelId("article", 19);
		
		MysqlUtil.closeConnection();
		
		System.out.println(tags);
	}
	
}
