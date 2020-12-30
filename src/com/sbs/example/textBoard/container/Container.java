
package com.sbs.example.textBoard.container;
import java.util.Scanner;

import com.sbs.example.textBoard.AppConfig;
import com.sbs.example.textBoard.controller.ArticleController;
import com.sbs.example.textBoard.controller.BoardController;
import com.sbs.example.textBoard.controller.BuildController;
import com.sbs.example.textBoard.controller.MemberController;
import com.sbs.example.textBoard.dao.ArticleDao;
import com.sbs.example.textBoard.dao.BoardDao;
import com.sbs.example.textBoard.dao.MemberDao;
import com.sbs.example.textBoard.service.ArticleService;
import com.sbs.example.textBoard.service.BoardService;
import com.sbs.example.textBoard.service.BuildService;
import com.sbs.example.textBoard.service.MemberService;
import com.sbs.example.textBoard.session.Session;

public class Container {
	
	public static Scanner sc;
	public static Session session;
	
	public static MemberDao memberDao;
	public static BoardDao boardDao;
	public static ArticleDao articleDao;
	
	public static MemberService memberService;
	public static BoardService boardService;
	public static ArticleService articleService;
	public static BuildService buildService;
	
	public static MemberController memberController;
	public static ArticleController articleController;
	public static BoardController boardController;
	public static BuildController exportController;
	
	public static AppConfig config;
	
	static {
		
		config = new AppConfig();
		
		sc = new Scanner(System.in);
		session = new Session();
		
		memberDao = new MemberDao();
		boardDao = new BoardDao();
		articleDao = new ArticleDao();
		
		memberService = new MemberService();
		boardService = new BoardService();
		articleService = new ArticleService();
		buildService = new BuildService();
		
		memberController = new MemberController();
		articleController = new ArticleController();
		boardController = new BoardController();
		exportController = new BuildController();
		
	}

}
