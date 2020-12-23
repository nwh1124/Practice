package com.sbs.example.textBoard.dto;

import java.util.List;
import java.util.Map;

public class Member {
	
	public int id;
	public String regDate;
	public String updateDate;
	public String loginId;
	public String loginPw;
	public String name;

	public Member(Map<String, Object> map) {

		this.id = (int)map.get("id");
		this.regDate = (String)map.get("regDate");
		this.updateDate = (String)map.get("updateDate");
		this.loginId = (String)map.get("loginId");
		this.loginPw = (String)map.get("loginPw");
		this.name = (String)map.get("name");
		
	}

}
