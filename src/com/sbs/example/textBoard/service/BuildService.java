package com.sbs.example.textBoard.service;

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

		Util.mkdirs("site");

		Util.copy("site_template/app.css", "site/app.css");
		Util.copy("site_template/app.js", "site/app.js");

		buildIndexPage();
		buildArticleListPages();
		buildDetailPages();

	}

	private void buildDetailPages() {
		List<Board> boards = boardService.getBoards();
		
		String head = getHeadHtml("article_detail");
		String bodyTemplate = Util.getFileContents("site_template/article_detail.html");
		String foot = Util.getFileContents("site_template/foot.html");
		
		for(Board board : boards) {
			
			List<Article> articles = articleService.getArticleByBoardId(board.id);
			
			for(int i = 0; i < articles.size(); i++) {
				
				Article article = articles.get(i);
				Article prevArticle = null;
				int prevArticleIndex = i + 1;
				int prevArticleId = 0;
				
				if(prevArticleIndex < articles.size()) {
					prevArticle = articles.get(prevArticleIndex);
					prevArticleId = prevArticle.id;
				}
				
				Article nextArticle = null;
				int nextArticleIndex = i + 1;
				int nextArticleId = 0;
				
				if(nextArticleIndex < articles.size()) {
					nextArticle = articles.get(nextArticleIndex);
					nextArticleId = nextArticle.id;
				}
				
				StringBuilder sb = new StringBuilder();
				String writer = memberService.getMemberNameById(article.memberId);
				
				sb.append(head);
				
				String body = bodyTemplate.replace("[[article-detail__title]]", article.title);
				body = body.replace("[[article-detail__board-name]]", boardService.getBoardNameById(article.boardId));
				body = body.replace("[[article-detail__reg-date]]", article.regDate);
				body = body.replace("[[article-detail__writer]]", writer);
				body = body.replace("[[article-detail__body]]", article.body);
				if(prevArticle != null) {
					body = body.replace("[[article-detail-prev-url]]", getArticleDetailFileName(prevArticle.id));
					body = body.replace("[[article-detail-prev-title]]", prevArticle != null ? prevArticle.title : "");
				}
				body = body.replace("[[article-detail-list-url]]", getArticleListFileName(boardService.getBoardNameById(article.boardId), 1));
				if(nextArticle != null) {
					body = body.replace("[[article-detail-next-url]]", getArticleDetailFileName(nextArticle.id));
					body = body.replace("[[article-detail-next-title]]", nextArticle != null ? nextArticle.title : "");
				}
				
				sb.append(body);
				
				sb.append(foot);
				
				String fileName = getArticleDetailFileName(article.id);
				
				String filePath = "site/" + fileName;
				
				Util.writeFileContents(filePath, sb.toString());
				System.out.println(filePath + " 생성");
				
			}
			
		}
		
	}

	private void buildArticleListPages() {

		List<Board> boards = boardService.getBoards();

		int itemsInAPage = 10;
		int pageBoxMenuSize = 10;

		for (Board board : boards) {

			List<Article> articles = articleService.getArticleByBoardId(board.id);
			int articleCount = articles.size();
			int totalPage = (int) Math.ceil((double) articleCount / itemsInAPage);

			for (int i = 1; i <= totalPage; i++) {
				buildArticleListPage(board, itemsInAPage, pageBoxMenuSize, articles, i);
			}

		}

	}

	private void buildArticleListPage(Board board, int itemsInAPage, int pageBoxSize, List<Article> articles,
			int page) {

		StringBuilder sb = new StringBuilder();

		sb.append(getHeadHtml("article_list_" + board.code));

		String bodyTemplate = Util.getFileContents("site_template/article_list.html");

		StringBuilder mainContent = new StringBuilder();

		int articlesCount = articles.size();
		int start = (page - 1) * itemsInAPage;
		int end = start + itemsInAPage - 1;

		if (end >= articlesCount) {
			end = articlesCount - 1;
		}

		for (int i = start; i <= end; i++) {

			Article article = articles.get(i);

			String link = getArticleDetailFileName(article.id);
			String writer = memberService.getMemberNameById(article.memberId);

			mainContent.append("<ul class=\"flex\">");

			mainContent.append("<li>" + article.id + "</li>");
			mainContent.append("<li>" + article.regDate + "</li>");
			mainContent.append("<li>" + writer + "</li>");
			mainContent.append("<li><a href=\"" + link + "\">" + article.title + "</a></li>");
			mainContent.append("<li>" + article.hit + "</li>");
			mainContent.append("<li>" + article.recommand + "</li>");

			mainContent.append("</ul>");

		}

		StringBuilder pageMenuContent = new StringBuilder();

		int totalPage = (int) Math.ceil((double) articlesCount / itemsInAPage);

		if (page < 1) {
			page = 1;
		}

		if (page > totalPage) {
			page = totalPage;
		}
		
		
		//박스의 시작과 끝 계산
		int previousPageBoxesCount = (page - 1) / pageBoxSize;
		int pageBoxStartPage = pageBoxSize * previousPageBoxesCount + 1;
		int pageBoxEndPage = pageBoxStartPage + pageBoxSize - 1;
		
		if(pageBoxEndPage > totalPage) {
			pageBoxEndPage = totalPage;
		}
		
		//이전 버튼 계산
		int pageBoxStartBeforePage = pageBoxStartPage - 1;
		if(pageBoxStartBeforePage < 1) {
			pageBoxStartBeforePage = 1;
		}
		
		// 다음 버튼 계산
		int pageBoxEndAfterPage = pageBoxEndPage + 1;
		if(pageBoxEndAfterPage < totalPage) {
			pageBoxEndAfterPage = totalPage;
		}
		
		boolean pageBoxStartBeforeBtnNeedToShow = pageBoxStartBeforePage != pageBoxStartPage;
		
		boolean pageBoxEndAfterBtnNeedToShow = pageBoxEndAfterPage != pageBoxEndPage;
		
		if(pageBoxStartBeforeBtnNeedToShow) {
			pageMenuContent.append("<li><a href=\"" + getArticleListFileName(board, pageBoxStartBeforePage) + "\">&lt; 이전</a></li>");
		}
		
		for(int i = pageBoxStartPage; i <= pageBoxEndPage; i++) {
			
			String selectedClass = "";
			
			if(i == page) {
				selectedClass = "color-red";
			}
			
			pageMenuContent.append("<li><a href=\"" + getArticleListFileName(board, i) + "\" class=\"" + selectedClass + "\">"
					 + i + "</a></li>");
			
		}
		
		if(pageBoxEndAfterBtnNeedToShow) {
			pageMenuContent.append("<li><a href=\"" + getArticleListFileName(board, pageBoxEndAfterPage) + "\">다음 &gt;</a></li>");
		}
		
		String body = bodyTemplate.replace("[[article_list]]", mainContent);
		body = body.replace("[[article_list_page]]", pageMenuContent);
		
		sb.append(body);
		
		sb.append(Util.getFileContents("site_template/foot.html"));
		
		String fileName = getArticleListFileName(board, page);
		String filePath = "site/" + fileName;
		
		Util.writeFileContents(filePath, sb.toString());
		System.out.println(filePath + " 생성");

	}

	private String getArticleListFileName(Board board, int page) {
		return getArticleListFileName(board.code, page);
	}

	private String getArticleListFileName(String boardCode, int page) {
		return "article_list_" + boardCode + "_" + page + ".html";
	}

	private String getArticleDetailFileName(int id) {

		return "article_detail_" + id + ".html";

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

			String link = "article_list_"+ board.code +"_1.html";

			boardMenuContents.append("<li>");
			
			boardMenuContents.append("<a href=\"" + link + "\" class=\"block\">");

			boardMenuContents.append(getTitleBarContentByPageName("article_list_" + board.code));

			boardMenuContents.append("</a>");

			boardMenuContents.append("</li>");

		}

		head = head.replace("[[menu-bar__menu1__menulist]]", boardMenuContents);

		String titleBarType = getTitleBarContentByPageName(pageName);

		head = head.replace("[[title-bar]]", titleBarType);

		return head;
	}

	private String getTitleBarContentByPageName(String pageName) {

		if (pageName.equals("index")) {
			return "<i class=\"fas fa-home\"></i> <span>HOME</span>";
		} else if (pageName.startsWith("article_list_free")) {
			return "<i class=\"fab fa-free-code-camp\"></i> <span>FREE LIST</span>";
		} else if (pageName.startsWith("article_list_notice")) {
			return "<i class=\"fas fa-flag\"></i> <span>NOTICE LIST</span>";
		} else if (pageName.startsWith("article_list_java")) {
			return "<i class=\"fab fa-java\"></i> <span>JAVA LIST</span>";
		} else if (pageName.startsWith("article_list_")) {
			return "<i class=\"fas fa-clipboard-list\"></i> <span>LIST</span>";
		} else if (pageName.equals("article_detail")) {
			return "<i class=\"fas fa-file-alt\"></i> <span>ARTICLE DETAIL</span>";
		}

		return "";
	}

}