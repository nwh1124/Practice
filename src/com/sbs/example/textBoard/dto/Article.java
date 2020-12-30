package com.sbs.example.textBoard.dto;

import java.util.Map;

public class Article {
	
	public int id;
	public String regDate;
	public String updateDate;
	public String title;
	public String body;
	public int hit;
	public int recommand;
	public int memberId;
	public int boardId;
	public int likesCount;
	public int commentsCount;
	
	public Article() {
		
	}

	public Article(Map<String, Object> map) {
		
		this.id = (int)map.get("id");
		this.regDate = (String)map.get("regDate");
		this.updateDate = (String)map.get("updateDate");
		this.title = (String)map.get("title");
		this.body = (String)map.get("body");
		this.hit = (int)map.get("hit");
		this.recommand = (int)map.get("recommend");
		this.memberId = (int)map.get("memberId");
		this.boardId = (int)map.get("boardId");
		this.likesCount = (int)map.get("likesCount");
		this.commentsCount = (int)map.get("commentsCount");
		
	}

}
