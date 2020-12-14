package com.sbs.example.textBoard.service;

import java.io.File;
import java.util.Collections;
import java.util.List;

import com.sbs.example.textBoard.container.Container;
import com.sbs.example.textBoard.dto.Article;
import com.sbs.example.textBoard.dto.Board;
import com.sbs.example.textBoard.util.Util;

public class BuildService {

	private ArticleService articleService;
	private MemberService memberService;
	private BoardService boardService;

	public BuildService() {

		articleService = Container.articleService;
		memberService = Container.memberService;
		boardService = Container.boardService;
	}

	public void buildSite() {

		Util.rmdir("site");
		Util.mkdirs("site/article");

		Util.copy("site_template/app.css", "site/app.css");

		buildIndexPage();
		buildArticleListPages();
		
	}

	private void buildArticleListPages() {

		List<Board> boards = boardService.getBoards();

		for (Board board : boards) {
			List<Article> articles = articleService.getArticles(board.code);

			StringBuilder sb = new StringBuilder();
			int listNum = 0;
			int articleSize = (articles.size() - 1) / 10;

			String head = getHeadHtml("article_list_" + board.code);
			String foot = Util.getFileContents("site_template/foot.html");

			sb.append(head);

			Collections.reverse(articles);
			
			buildArticleDetailPages(board, articles);
			
			sb = new StringBuilder();
			head = getHeadHtml("article_list_" + board.code);
			sb.append(head);

			for (Article article : articles) {

				if (listNum == 0) {
					
				}
				
				listNum++;
				
				if(listNum % 10 == 1) {
					sb.append("<ul class=\"section-1__article-list\">");
					sb.append("<li>");
						sb.append("<span>번호</span>");
						sb.append("<span>작성일</span>");
						sb.append("<span>작성자</span>");
						sb.append("<span>제목</span>");
						sb.append("<span>조회수</span>");
						sb.append("<span>추천수</span>");
					sb.append("</li>");
					sb.append("<br>");
				}

				sb.append("<li>");
				sb.append("<span>" + article.id + "</span>");
				sb.append("<span>" + article.regDate.substring(0, 10) + "</span>");
				sb.append("<span>" + article.body + "</span>");
				sb.append("<span><a href=\"" + board.code + "-list-" + (((listNum -1) / 10)+ 1)  + "-" + listNum + ".html" + "\">" + article.title + "</a></span>");
				sb.append("<span>" + article.hit + "</span>");
				sb.append("<span>" + article.recommand + "</span>");
				sb.append("</li>");
				sb.append("<br>");

				if (listNum != 0 && listNum % 10 == 0) {
					sb.append("</ul>");
					
					sb.append("<br>");
					
					sb.append("<ul class=\"flex flex-jc-sa\">");
					
					sb.append("<li>");
					
					if(listNum > 10) {
						sb.append("<a href=\"\">&lt; 이전 페이지</a>");	
					}
					
					for(int i = 1; i <= articleSize + 1; i++) {
						if((listNum / 10 ) == i) {
							sb.append("<a href=\"" + board.code + "-list-" + i + ".html\" class=\"color-red\">[" + i +"]</a>");
							continue;
						}
						sb.append("<a href=\"" + board.code + "-list-" + i + ".html\">[" + i +"]</a>");
					}
					
					if((listNum / 10) < articleSize + 1) {
						sb.append("<a href=\"\">다음 페이지 &gt;</a>");	
					}
					
					sb.append("</li>");				
					sb.append("</ul>");
					
					sb.append(foot);
					
					Util.writeFileContents("site/article/" + board.code + "-list-" + (listNum / 10) + ".html", sb.toString());
					System.out.println(board.code + "-list-" + (listNum/10) +".html 생성되었습니다");					
					
					sb = new StringBuilder();
					sb.append(head);
				}

			}

		}

	}

	private void buildArticleDetailPages(Board board, List<Article> articles) {
		
		String head = getHeadHtml("article_detail");
		String foot = Util.getFileContents("site_template/foot.html");
		
		int listNum = 0;
		
		for(Article article : articles) {
			
			listNum++;
						
			StringBuilder sb = new StringBuilder();
			
			sb.append(head);
			
			sb.append("<div class=\"section-1__article-detail\">");
			
			sb.append("<ul>");
			
			sb.append("<li class=\"title\">");

			sb.append("<span>" + article.title + "</span>");
			
			sb.append("</li>");
			
			sb.append("<li>");
			
			sb.append("<span>" + boardService.getBoardNameById(article.boardId) + "</span>");
			sb.append("<span>" + article.regDate.substring(0, 10) + "</span>");
			sb.append("<span>" + memberService.getMemberNameById(1) + "</span>");

			sb.append("</li>");
			sb.append("<li class=\"body\"> 내용");
			sb.append("</li>");
			
			if(listNum == 1) {				
				sb.append("<li class=\"list\">");

				sb.append("<a href=\"" + board.code + "-list-" + (((listNum - 1) / 10)+ 1) + ".html\">목록</a>");	
				sb.append("<a href=\"" + board.code + "-list-" + (((listNum) / 10)+ 1)  + "-" + (listNum + 1) + ".html\">");
				sb.append("다음 글</a>");
				sb.append("</ul>");	
				sb.append("</li>");				
			}else if(listNum == articles.size()) {
				sb.append("<li class=\"list\">");
				sb.append("<a href=\"" + board.code + "-list-" + (((listNum - 2) / 10)+ 1)  + "-" + (listNum - 1) + ".html\">");
				sb.append("이전 글</a>");
				sb.append("<a href=\"" + board.code + "-list-" + (((listNum - 1) / 10)+ 1) + ".html\">목록</a>");
				sb.append("</ul>");	
				sb.append("</li>");
			}else {
				sb.append("<li class=\"list\">");
				sb.append("<a href=\"" + board.code + "-list-" + (((listNum - 2) / 10)+ 1)  + "-" + (listNum - 1) + ".html\">");
				sb.append("이전 글</a>");
				sb.append("<a href=\"" + board.code + "-list-" + (((listNum - 1) / 10)+ 1) + ".html\">목록</a>");	
				sb.append("<a href=\"" + board.code + "-list-" + (((listNum) / 10)+ 1)  + "-" + (listNum + 1) + ".html\">");
				sb.append("다음 글</a>");
				sb.append("</ul>");	
				sb.append("</li>");
			}

			sb.append("</div>");
			
			sb.append(foot);
			
			Util.writeFileContents("site/article/" + board.code + "-list-" + (((listNum -1) / 10)+ 1)  + "-" + listNum + ".html", sb.toString());
			System.out.println(board.code+"-list-" + (((listNum -1) / 10)+ 1)  + "-" + listNum + ".html 생성되었습니다");			
		}
	}

	private void buildIndexPage() {

		StringBuilder sb = new StringBuilder();

		String head = getHeadHtml("index");
		String foot = Util.getFileContents("site_template/foot.html");

		String mainHtml = Util.getFileContents("site_template/index.html");

		sb.append(head);
		sb.append(mainHtml);
		sb.append(foot);

		String filePath = "site/index.html";
		Util.writeFileContents(filePath, sb.toString());
		System.out.println(filePath + " 생성");

	}

	private String getHeadHtml(String pageName) {

		String head = Util.getFileContents("site_template/head.html");

		StringBuilder boardMenuContents = new StringBuilder();

		List<Board> boards = boardService.getBoards();

		for (Board board : boards) {

			String link = board.code + "-list-1.html";

			boardMenuContents.append("<li>");

			boardMenuContents.append("<a href=\"" + link + "\" class=\"block\">");

			boardMenuContents.append(getTitleBarContentByPageName("article_list_" + board.code));

			boardMenuContents.append("</a>");

			boardMenuContents.append("</li>");

		}

		head = head.replace("[[menu-bar__menu1__menulist]]", boardMenuContents);

		String titleBarContentHtml = getTitleBarContentByPageName(pageName);

		head = head.replace("[[title-bar__logo]]", titleBarContentHtml);

		String titleBarType = getTitleBarContentByPageName(pageName);

		head = head.replace("[[title-bar]]", titleBarType);

		if (pageName.equals("index")) {
			head = head.replace("[[href#HOME]]", "./index.html");
		} else {
			head = head.replace("[[href#HOME]]", "../index.html");
		}

		return head;
	}

	private String getTitleBarContentByPageName(String pageName) {

		if (pageName.equals("index")) {
			return "<i class=\"fas fa-home\"></i> <span>HOME</span>";
		} else if (pageName.equals("boardList")) {
			return "<i class=\"fas fa-clipboard-list\"></i> <span>BOARD LIST</span>";
		} else if (pageName.startsWith("article_list_free")) {
			return "<i class=\"fab fa-free-code-camp\"></i> <span>FREE LIST</span>";
		} else if (pageName.startsWith("article_list_notice")) {
			return "<i class=\"fas fa-flag\"></i> <span>NOTICE LIST</span>";
		} else if (pageName.startsWith("aritcle_list")) {
			return "<i class=\"fas fa-clipboard-list\"></i> <span>LIST</span>";
		} else if (pageName.equals("article_detail")) {
			return "<i class=\"fas fa-file-alt\"></i> <span>ARTICLE DETAIL</span>";
		}

		return "";
	}

	public void doTest() {
		
		Util.rmdir("site");
		Util.mkdirs("site/article");

		Util.copy("site_template/app.css", "site/app.css");

		buildIndexPage();

		buildArticleListPages();

	}

}