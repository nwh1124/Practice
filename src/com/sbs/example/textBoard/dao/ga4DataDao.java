package com.sbs.example.textBoard.dao;

import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

public class ga4DataDao {

	public static void deletePagePath(String pagePath) {		
		SecSql sql = new SecSql();
		
		sql.append("DELETE FROM ga4DataPagePath");
		sql.append("WHERE pagePath = ?", pagePath);
		
		MysqlUtil.delete(sql);		
	}

	public static void savaPagePath(String pagePath, int hit) {
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO ga4DataPagePath");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", pagePath = ?", pagePath);
		sql.append(", hitsCount = ?", hit);
		
		MysqlUtil.insert(sql);		
	}

	
	
}
