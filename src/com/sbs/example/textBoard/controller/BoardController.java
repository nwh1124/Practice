package com.sbs.example.textBoard.controller;

import java.util.List;
import java.util.Scanner;

import com.sbs.example.textBoard.container.Container;
import com.sbs.example.textBoard.dto.Board;
import com.sbs.example.textBoard.service.BoardService;

public class BoardController extends Controller{
	
	Scanner sc;
	private BoardService boardService;
	
	public BoardController() {
		
		sc = Container.sc;
		boardService = Container.boardService;
		
	}
	
	public void doCommand(String cmd) {
		
		if(cmd.equals("board make")) {			
			make();			
		}else if(cmd.startsWith("board select")) {			
			select(cmd);			
		}
		
	}

	private void select(String cmd) {
		
		System.out.println("== 게시판 선택 ==");
		
		List<Board> boards = boardService.getBoards();
		
		System.out.println("번호 / 생성일 / 이름 / 코드");
		for(Board board : boards) {
			System.out.printf("%d / %s / %s / %s\n", board.getId(), board.getRegDate(), board.getName(), board.getCode());
		}
				
		System.out.printf("게시판 코드 : ");
		String inStr = sc.nextLine();
		
		for(Board board : boards) {
			if(board.getCode().equals(inStr)) {
				Container.session.selectBoard(board.getId());
				System.out.printf("= %s(%d번) 게시판이 선택되었습니다 =\n", board.getName(), board.getId());
				return;
			}
		}
		
		System.out.printf("= %s 게시판은 존재하지 않습니다 =\n", inStr);
		
	}

	private void make() {

		if(Container.session.isAdmin(Container.session.getLoginedId()) == false) {
			System.out.println("= 권한이 없습니다 =");
			return;
		}
		
		System.out.println("== 게시판 생성 ==");
		
		int missCount = 0;
		int missCountMax = 3;
		
		String name;
		String code;
		
		Board board;
		
		while(true) {
			
			if(missCount >= missCountMax) {
				System.out.println("= 게시판 생성 취소 =");
				return;
			}
			
			System.out.printf("게시판 이름 : ");
			name = sc.nextLine();
					
			board = boardService.getBoardByName(name);
					
			if(name.trim().length() == 0) {
				System.out.println("= 이름을 입력해주세요 =");
				missCount++;
				continue;
			}else if(board != null) {
				System.out.println("= 이미 존재하는 이름입니다 =");
				missCount++;
				continue;
			}else {
				break;
			}
		}
		
		missCount = 0;
		
		while(true) {
			
			if(missCount >= missCountMax) {
				System.out.println("= 게시판 생성 취소 =");
				return;
			}
			
			System.out.printf("게시판 코드 : ");
			code = sc.nextLine();
					
			board = boardService.getBoardByName(name);
					
			if(code.trim().length() == 0) {
				System.out.println("= 코드를 입력해주세요 =");
				missCount++;
				continue;
			}else if(board != null) {
				System.out.println("= 이미 존재하는 코드입니다 =");
				missCount++;
				continue;
			}else {
				break;
			}
		}
		
		int id = boardService.make(name, code);
		System.out.printf("= %d번 게시판이 생성되었습니다 =\n", id);
				
	}

}
