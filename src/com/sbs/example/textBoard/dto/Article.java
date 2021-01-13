package com.sbs.example.textBoard.dto;

import java.util.Map;

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
	
	public String extra__writer;
	
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
		
		this.extra__writer = (String)map.get("name");
		
	}

	public int getId() {
		return id;
	}
	
	public String getRegDate() {
		return regDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getHitsCount() {
		return hitsCount;
	}

	public void setHitsCount(int hitsCount) {
		this.hitsCount = hitsCount;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getLikesCount() {
		return likesCount;
	}

	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
