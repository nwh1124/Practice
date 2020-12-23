package com.sbs.example.textBoard.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;
import com.sbs.example.textBoard.dto.Member;

public class MemberDao {

	public Member getMemberById(String loginId) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM member");
		sql.append("WHERE loginId = ?", loginId);
		
		Map<String, Object> map = MysqlUtil.selectRow(sql);
		
		if(map.size() == 0) {
			return null;
		}
		
		Member member = new Member(map);
		
		return member;
	}

	public int join(String loginId, String loginPw, String name) {
		
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO member");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", name = ?", name);
		
		return MysqlUtil.insert(sql);
	}

	public Member getMemberById(int loginedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM member");
		sql.append("WHERE id = ?", loginedId);
		
		Map<String, Object> map = MysqlUtil.selectRow(sql);
		
		Member member = new Member(map);
		
		return member;
	}

	public String getMemberNameById(int memberId) {
		
		SecSql sql = new SecSql();

		sql.append("SELECT name");
		sql.append("FROM member");
		sql.append("WHERE id = ?", memberId);
		
		return MysqlUtil.selectRowStringValue(sql);
	}

	public List<Member> getMembers() {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM member");
		
		List<Map<String, Object>> listmap = MysqlUtil.selectRows(sql);
		
		List<Member> members = new ArrayList<>();
		
		for(Map<String, Object> map : listmap) {
			members.add(new Member(map));
		}
		
		return members;
	}

}
