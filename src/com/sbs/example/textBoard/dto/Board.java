package com.sbs.example.textBoard.dto;

import java.util.Map;

public class Board {
		
	public int id;
	public String regDate;
	public String updateDate;
	public String name;
	public String code;
	
	public Board(Map<String, Object> map) {

		this.id = (int)map.get("id");
		this.regDate = (String)map.get("regDate");
		this.updateDate = (String)map.get("updateDate");
		this.name = (String)map.get("name");
		this.code = (String)map.get("code");
		
	}

}
