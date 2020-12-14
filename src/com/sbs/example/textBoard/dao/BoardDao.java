package com.sbs.example.textBoard.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;
import com.sbs.example.textBoard.dto.Board;

public class BoardDao {

	public Board getBoardByName(String name) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM board");
		sql.append("WHERE name = ?", name);
		
		Map<String, Object> map = MysqlUtil.selectRow(sql);
		
		if(map.size() == 0) {
			return null;
		}
		
		Board board = new Board(map);
		
		return board;
		
	}

	public int make(String name, String code) {
		
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO board");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", name = ?", name);
		sql.append(", code = ?", code);
		
		return MysqlUtil.insert(sql);
	}

	public List<Board> getBoards() {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM board");
		
		List<Map<String, Object>> listMap = MysqlUtil.selectRows(sql);
		
		List<Board> boards = new ArrayList<>();
		
		for(Map<String, Object> map : listMap) {
			boards.add(new Board(map));
		}
		
		return boards;
	}

	public String getBoardNameById(int boardId) {
		SecSql sql = new SecSql();
		
		sql.append("SELECT code");
		sql.append("FROM board");
		sql.append("WHERE id = ?", boardId);
		
		return MysqlUtil.selectRowStringValue(sql);
	}

}
