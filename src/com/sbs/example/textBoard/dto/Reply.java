package com.sbs.example.textBoard.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Reply{
	
	public int id;
	public String regDate;
	public String updateDate;
	public String body;
	private String relTypeCode;
	public int relId;
	public int memberId;
	private String delDate;
	private int delStatus;

	public Reply(Map<String, Object> map) {
		
		this.id = (int)map.get("id");
		this.regDate = (String)map.get("regDate");
		this.updateDate = (String)map.get("updateDate");
		this.body = (String)map.get("body");
		this.relTypeCode = (String)map.get("relTypeCode");
		this.relId = (int)map.get("relId");
		this.memberId = (int)map.get("memberId");
		this.delDate = (String)map.get("delDate");
		this.delStatus = (int)map.get("delStatus");
		
	}
}
