package com.sbs.example.textBoard.service;

import java.util.HashMap;
import java.util.Map;

import com.sbs.example.textBoard.apito.DisqusApiDataListThread;
import com.sbs.example.textBoard.container.Container;
import com.sbs.example.textBoard.dto.Article;
import com.sbs.example.textBoard.util.Util;

public class disqusApiService {
	public static Map<String, Object> getArticleData(Article article) {
		String fileName = Container.buildService.getArticleDetailFileName(article.id);
		String url = "https://disqus.com/api/3.0/forums/listThreads.json";
		DisqusApiDataListThread disqusApiDataListThread = (DisqusApiDataListThread) Util.callApiResponseTo(
				DisqusApiDataListThread.class, url, "api_key=" + Container.config.getDisqusApiKey(),
				"forum=" + Container.config.getDisqusForumName(), "thread:ident=" + fileName);

		if (disqusApiDataListThread == null) {
			return null;
		}

		Map<String, Object> rs = new HashMap<>();
		rs.put("likesCount", disqusApiDataListThread.response.get(0).likes);
		rs.put("commentsCount", disqusApiDataListThread.response.get(0).posts);

		return rs;
	}
}
