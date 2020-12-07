package com.sbs.example.textBoard.session;

public class Session {
	
	boolean logined;
	int loginedId;
	int selectedBoardId;
	
	public Session() {		
		logined = false;
		loginedId = 0;	
		selectedBoardId = 1;
	}

	public void login(int id) {
		logined = true;
		loginedId = id;
	}

	public boolean getLogined() {
		return logined;
	}

	public void logout() {
		logined = false;
		loginedId = 0;		
	}

	public int getLoginedId() {
		return loginedId;
	}

	public boolean isAdmin(int loginedId) {
		return loginedId == 1;
	}

	public void selectBoard(int id) {
		selectedBoardId = id;
	}

	public int getSelectedBoard() {
		return selectedBoardId;
	}

}
