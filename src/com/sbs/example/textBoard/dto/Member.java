package com.sbs.example.textBoard.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Member {
	
	private int id;
	private String regDate;
	private String updateDate;
	private String loginId;
	private String loginPw;
	private String name;
	private int authLever;

	public Member(Map<String, Object> map) {

		this.id = (int)map.get("id");
		this.regDate = (String)map.get("regDate");
		this.updateDate = (String)map.get("updateDate");
		this.loginId = (String)map.get("loginId");
		this.loginPw = (String)map.get("loginPw");
		this.name = (String)map.get("name");
		this.id = (int)map.get("authLever");
		
	}
}
