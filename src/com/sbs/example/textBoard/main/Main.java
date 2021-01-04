package com.sbs.example.textBoard.main;

import java.io.IOException;

import com.google.analytics.data.v1alpha.AlphaAnalyticsDataClient;
import com.google.analytics.data.v1alpha.DateRange;
import com.google.analytics.data.v1alpha.Dimension;
import com.google.analytics.data.v1alpha.Entity;
import com.google.analytics.data.v1alpha.Metric;
import com.google.analytics.data.v1alpha.Row;
import com.google.analytics.data.v1alpha.RunReportRequest;
import com.google.analytics.data.v1alpha.RunReportResponse;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.textBoard.container.Container;

public class Main {
	
	public static void main(String[] args) {
		
		new App().run();
		
//		testApi();
		
	}

	private static void testApi() {
		
//		String keyFilePath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
//		System.out.println(keyFilePath);
		
		MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "ssgDb");
		
		Container.googleAnalyticsApiService.updatePageHits();
	
		MysqlUtil.closeConnection();
		
	}
	
}
