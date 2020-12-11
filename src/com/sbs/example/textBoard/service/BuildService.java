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

			for (Article article : articles) {

				if (listNum == 0) {
					sb.append("<ul>");
					sb = new StringBuilder();
					head = getHeadHtml("article_list_" + board.code);
					sb.append(head);
				}
				
				listNum++;

				sb.append("<li>");
				sb.append("<span>" + article.id + "</span>");
				sb.append("<span>" + article.regDate + "</span>");
				sb.append("<span>" + article.body + "</span>");
				sb.append("<a href=\"" + board.code + "-list-" + (((listNum -1) / 10)+ 1)  + "-" + listNum + ".html" + "\">" + article.title + "</a>");
				sb.append("<span>" + article.hit + "</span>");
				sb.append("<span>" + article.recommand + "</span>");
				sb.append("</li>");

				if (listNum != 0 && listNum % 10 == 0) {
					sb.append("</ul>");
					
					sb.append("<ul class=\"flex flex-jc-sa\">");
					sb.append("<li>");
					
					for(int i = 1; i <= articleSize + 1; i++) {
						sb.append("<a href=\"" + board.code + "-list-" + i + ".html\">[" + i +"]</a>");
					}
					
					sb.append("</li></ul>");
					
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
		
		int listId = 0;
		
		for(Article article : articles) {
			
			listId++;
						
			StringBuilder sb = new StringBuilder();
			
			sb.append(head);
			
			sb.append("<div>");
			
			sb.append("<span>" + board.name + "</span><br>");
			sb.append("<span>" + article.id + "</span><br>");
			sb.append("<span>" + article.regDate + "</span><br>");
			sb.append("<span>" + article.updateDate + "</span><br>");
			sb.append("<span>" + article.title + "</span><br>");
			sb.append("<span>" + article.body + "</span><br>");
			sb.append("<span>" + article.hit + "</span><br>");
			sb.append("<span>" + article.recommand + "</span><br>");
	
			sb.append("</div>");
			
			if(listId == 1) {				
				sb.append("<div>");
				sb.append("<a href=\"" + board.code + "-list-" + (((listId -1) / 10)+ 1)  + "-" + (listId + 1) + ".html\">");
				sb.append("다음 글</a>");
				sb.append("</div>");				
			}else if(listId == articles.size()) {
				sb.append("<div>");
				sb.append("<a href=\"" + board.code + "-list-" + (((listId -1) / 10)+ 1)  + "-" + (listId - 1) + ".html\">");
				sb.append("이전 글</a>");
				sb.append("</div>");
			}else {
				sb.append("<div>");
				sb.append("<a href=\"" + board.code + "-list-" + (((listId -1) / 10)+ 1)  + "-" + (listId - 1) + ".html\">");
				sb.append("이전 글</a>");
				sb.append("<a href=\"" + board.code + "-list-" + (((listId -1) / 10)+ 1)  + "-" + (listId + 1) + ".html\">");
				sb.append("다음 글</a>");
				sb.append("</div>");
			}
			
			sb.append(foot);
			
			Util.writeFileContents("site/article/" + board.code + "-list-" + (((listId -1) / 10)+ 1)  + "-" + listId + ".html", sb.toString());
			System.out.println(board.code+"-list-" + (((listId -1) / 10)+ 1)  + "-" + listId + ".html 생성되었습니다");			
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

			boardMenuContents.append("<a href=\"" + link + "\" class=\"\"");

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
			head = head.replace("[[href#FREE]]", "article/free-list-1.html");
			head = head.replace("[[href#NOTICE]]", "article/notice-list-1.html");
		} else {
			head = head.replace("[[href#HOME]]", "../index.html");
			head = head.replace("[[href#FREE]]", "./free-list-1.html");
			head = head.replace("[[href#NOTICE]]", "./notice-list-1.html");
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