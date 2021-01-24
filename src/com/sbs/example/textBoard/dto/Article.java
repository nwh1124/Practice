package com.sbs.example.textBoard.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Article {
	
	private int id;
	private String regDate;
	private String updateDate;
	private String title;
	private String body;
	private int hitsCount;
	private int recommend;
	private int memberId;
	private int boardId;
	private int likesCount;
	private int commentsCount;

	public String extra__tags;
	
	public Article() {
		
	}

	public Article(Map<String, Object> map) {
		
		this.id = (int)map.get("id");
		this.regDate = (String)map.get("regDate");
		this.updateDate = (String)map.get("updateDate");
		this.title = (String)map.get("title");
		this.body = (String)map.get("body");
		this.hitsCount = (int)map.get("hitsCount");
		this.recommend = (int)map.get("recommend");
		this.memberId = (int)map.get("memberId");
		this.boardId = (int)map.get("boardId");
		this.likesCount = (int)map.get("likesCount");
		this.commentsCount = (int)map.get("commentsCount");

		this.extra__tags = (String)map.get("tags");
		
	}

}
