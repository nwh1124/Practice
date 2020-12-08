package com.sbs.example.textBoard.main;

import java.util.Scanner;

import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.textBoard.container.Container;
import com.sbs.example.textBoard.controller.ArticleController;
import com.sbs.example.textBoard.controller.BoardController;
import com.sbs.example.textBoard.controller.Controller;
import com.sbs.example.textBoard.controller.BuildController;
import com.sbs.example.textBoard.controller.MemberController;

public class App {
	
	private Scanner sc;
	private boolean isCmdExit;
	private MemberController memberController;
	private ArticleController articleController;
	private BoardController boardController;
	private BuildController buildController;
	
	public App() {
		
		sc = Container.sc;
		isCmdExit = false;
		
		memberController = Container.memberController;
		articleController = Container.articleController;
		boardController = Container.boardController;
		buildController = Container.exportController;
		
	}
	
	public void run() {
		
		while(true) {
		
			System.out.printf("명령어 ) ");
			String cmd = sc.nextLine();
			
			if(cmd.equals("system exit")) {
				System.out.println("== 시스템 종료 ==");
				isCmdExit = true;				
			}
			
			Controller controller = getControllerByCmd(cmd);
			if(controller != null) {
				MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "exam");
				controller.doCommand(cmd);
			}
			
			if(isCmdExit) {
				MysqlUtil.closeConnection();
				break;
			}
			
		}
		
	}

	private Controller getControllerByCmd(String cmd) {
		if(cmd.startsWith("member")) {
			return memberController;
		}else if(cmd.startsWith("article")) {
			return articleController;
		}else if(cmd.startsWith("board")) {
			return boardController;
		}else if(cmd.startsWith("build")) {
			return buildController;
		}
		return null;
	}

}
