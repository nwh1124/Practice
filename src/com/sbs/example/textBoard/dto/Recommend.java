package com.sbs.example.textBoard.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Recommend {

	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String relTypeCode;
	private int relId;
	private int point;
		
	public Recommend(Map<String, Object> map) {
		
		this.id = (int)map.get("id");
		this.regDate = (String)map.get("regDate");
		this.updateDate = (String)map.get("updateDate");
		this.memberId = (int)map.get("memberId");
		this.relTypeCode = (String)map.get("relTypeCode");
		this.relId = (int)map.get("relId");
		this.point = (int)map.get("point");
	}
	
}
