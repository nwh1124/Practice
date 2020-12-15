package com.sbs.example.textBoard.service;

import java.util.List;

import com.sbs.example.textBoard.container.Container;
import com.sbs.example.textBoard.dao.BoardDao;
import com.sbs.example.textBoard.dto.Board;

public class BoardService {

	private static BoardDao boardDao;
	
	public BoardService() {
		
		boardDao = Container.boardDao;
		
	}

	public static Board getBoardByName(String name) {
		return boardDao.getBoardByName(name);
	}

	public int make(String name, String code) {
		return boardDao.make(name, code);
	}

	public List<Board> getBoards() {
		return boardDao.getBoards();
	}

	public String getBoardNameById(int boardId) {
		return boardDao.getBoardNameById(boardId);
	}

}
