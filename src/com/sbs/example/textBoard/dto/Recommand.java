package com.sbs.example.textBoard.dto;

import java.util.Map;

public class Recommand {

	public int id;
	public String regDate;
	public String updateDate;
	public boolean recommand;
	public int articleId;
	public int memberId;
		
	public Recommand(Map<String, Object> map) {
		
		this.id = (int)map.get("id");
		this.regDate = (String)map.get("regDate");
		this.updateDate = (String)map.get("updateDate");
		this.recommand = (boolean)map.get("recommand");
		this.articleId = (int)map.get("articleId");
		this.memberId = (int)map.get("memberId");
		
	}
	
}
