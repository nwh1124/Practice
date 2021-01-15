package com.sbs.example.textBoard.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbs.example.textBoard.container.Container;
import com.sbs.example.textBoard.dto.Article;
import com.sbs.example.textBoard.dto.Board;
import com.sbs.example.textBoard.dto.Member;
import com.sbs.example.textBoard.dto.Tag;
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
		
		loadDisqusData();
		loadGoogleData();
		
		buildIndexPage();
		buildArticleListPages();
		buildDetailPages();
		buildStatPage();
		buildSearchPage();
		buildTagPage();

	}
	
	private void buildTagPage() {
		
		List<Tag> tags = articleService.getTags();
		
		String jsonText = Util.getJsonText(tags);
		Util.writeFileContents("site/article_tag.json", jsonText);
		
		Util.copy("site_template/article_tag.js", "site/article_tag.js");
		
		StringBuilder sb = new StringBuilder();		

		String head = getHeadHtml("article_tag");		
		String Html = Util.getFileContents("site_template/article_tag.html");		
		String foot = Util.getFileContents("site_template/foot.html");

		sb.append(head);
		sb.append(Html);
		sb.append(foot);

		String filePath = "site/article_tag.html";
		Util.writeFileContents(filePath, sb.toString());
		System.out.println(filePath + " 생성");
		
	}

	private String buildMobileIndexPage(StringBuilder mobileSb) {
		
		mobileSb = new StringBuilder();

		String head = getMobileHeadHtml("index");
		String foot = getMobileFootHtml();
		String html = Util.getFileContents("site_template/mobile_index.html");
		
		List<Article> articles = articleService.getLatestArticles();
		
		StringBuilder latestArticles = new StringBuilder();
		
		for(Article article : articles) {
			
			latestArticles.append("<div>");
			latestArticles.append("<span>" + article.getRegDate().substring(0, 10) + "</span>");
			latestArticles.append("<span><a href=\"" + getArticleDetailFileName(article.getId()) + "\">" + article.getTitle() + "</a></span>");
			latestArticles.append("</div>");
		}
		
		html = html.replace("[[mobile-content]]", latestArticles);
				
		mobileSb.append(head);
		mobileSb.append(html);
		mobileSb.append(foot);

		return mobileSb.toString();
		
	}

	private String getMobileFootHtml() {
		String foot = Util.getFileContents("site_template/mobile_foot.html");		
		StringBuilder bottomMenuBar = new StringBuilder(); 
				
		List<Board> boards = boardService.getBoards();

		for (Board board : boards) {

			String link = "article_list_"+ board.getCode() +"_1.html";

			bottomMenuBar.append("<li>");
			
			bottomMenuBar.append("<a href=\"" + link + "\" class=\"block\">");

			bottomMenuBar.append(getTitleBarContentByPageName("article_list_" + board.getCode()));

			bottomMenuBar.append("</a>");

			bottomMenuBar.append("</li>");

		}
		
		foot = foot.replace("[[mobile-bottom-menu-bar]]", bottomMenuBar);
		
		return foot;
	}

	private String getMobileHeadHtml(String mobilePageName) {
		
		String head = Util.getFileContents("site_template/mobile_head.html");

		StringBuilder boardMenuContents = new StringBuilder();	
		
		String titleBarType = getTitleBarContentByPageName(mobilePageName);

		head = head.replace("[[mobile-title-bar]]", titleBarType);

		return head;
	}

	private void loadGoogleData() {
		
		Container.googleAnalyticsApiService.updatePageHits();
		
	}

	private void buildSearchPage() {
		
		List<Article> articles = articleService.getArticlesWithMemberName();
		
		String jsonText = Util.getJsonText(articles);
		Util.writeFileContents("site/article_list.json", jsonText);
		
		Util.copy("site_template/article_search.js", "site/article_search.js");
		
		StringBuilder sb = new StringBuilder();		

		String head = getHeadHtml("article_search");		
		String Html = Util.getFileContents("site_template/article_search.html");		
		String foot = Util.getFileContents("site_template/foot.html");

		sb.append(head);
		sb.append(Html);
		sb.append(foot);

		String filePath = "site/article_search.html";
		Util.writeFileContents(filePath, sb.toString());
		System.out.println(filePath + " 생성");
		
	}

	private void loadDisqusData() {
		List<Article> articles = articleService.getArticles();

		for (Article article : articles) {
			Map<String, Object> disqusArticleData = disqusApiService.getArticleData(article);

			if (disqusArticleData != null) {
				int likesCount = (int) disqusArticleData.get("likesCount");
				int commentsCount = (int) disqusArticleData.get("commentsCount");

				Map<String, Object> modifyArgs = new HashMap<>();
				modifyArgs.put("id", article.getId());
				modifyArgs.put("likesCount", likesCount);
				modifyArgs.put("commentsCount", commentsCount);

				articleService.modify(modifyArgs);
			}
		}
	}

	private void buildStatPage() {
		
		List<Member> members = memberService.getMembers();
		List<Article> articles = articleService.getArticles();
		List<Board> boards = boardService.getBoards();
		int totalHits = 0;
		int[] boardArticlesArr = new int[boards.size()];
		int[] boardArticleHitsArr = new int[boards.size()];
		
		String head = getHeadHtml("stat");
		String body = Util.getFileContents("site_template/stat.html");
		String foot = Util.getFileContents("site_template/foot.html");
		
		StringBuilder totalMemberNum = new StringBuilder();
		
		totalMemberNum.append("<li class=\"total\"><span>Total Members</span>");
		totalMemberNum.append("<span>" + members.size() + "</span></li>");
		
		StringBuilder totalArticlesNum = new StringBuilder();
		
		totalArticlesNum.append("<li class=\"total\"><span>Total Articles</span>");
		totalArticlesNum.append("<span>" + articles.size() + "</span></li>");
		
		for(int i = 0; i < articles.size(); i++) {
			totalHits += articles.get(i).getHitsCount();			
		}

		StringBuilder totalArticleHits = new StringBuilder();
		
		totalArticleHits.append("<li class=\"total\"><span>Total Hits</span>");
		totalArticleHits.append("<span>" + totalHits + "</span></li>");

		StringBuilder boardArticles = new StringBuilder();
		
		for(int i = 0; i < boards.size(); i++) {
			for(int j = 0; j < articles.size(); j++) {
				if(articles.get(j).getBoardId()-1 == i) {
					boardArticlesArr[i]++;
				}
			}
			
			boardArticles.append("<li><span>" + boards.get(i).getName() + " Articles</span>");
			boardArticles.append("<span>" + boardArticlesArr[i] + "</span></li>");
			
		}

		StringBuilder boardArticleHits = new StringBuilder();
		
		for(int i = 0; i < boards.size(); i++) {
			for(int j = 0; j < articles.size(); j++) {
				if(articles.get(j).getBoardId()-1 == i) {
					boardArticleHitsArr[i] += articles.get(j).getHitsCount();
				}
			}
			
			boardArticleHits.append("<li><span>" + boards.get(i).getName() + " Hits</span>");
			boardArticleHits.append("<span>" + boardArticleHitsArr[i] + "</span></li>");
			
		}
		
		body = body.replace("[[member-num]]", totalMemberNum);
		body = body.replace("[[total-article-num]]", totalArticlesNum);
		body = body.replace("[[board-article]]", boardArticles);
		body = body.replace("[[total-hits-num]]", totalArticleHits);
		body = body.replace("[[board-hits]]", boardArticleHits);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(head);
		sb.append(body);
		sb.append(foot);
		
		String filePath = "site/stat.html";
		
		Util.writeFileContents(filePath, sb.toString());
		System.out.println(filePath + " 생성");
		
	}

	private void buildDetailPages() {
		List<Board> boards = boardService.getBoards();
		
		String head = getHeadHtml("article_detail");
		String bodyTemplate = Util.getFileContents("site_template/article_detail.html");
		String foot = Util.getFileContents("site_template/foot.html");
		
		for(Board board : boards) {
			
			List<Article> articles = articleService.getArticleByBoardId(board.getId());
			
			for(int i = 0; i < articles.size(); i++) {
				
				Article article = articles.get(i);
				Article prevArticle = null;
				int prevArticleIndex = i + 1;
				int prevArticleId = 0;
				
				if(prevArticleIndex < articles.size()) {
					prevArticle = articles.get(prevArticleIndex);
					prevArticleId = prevArticle.getId();
				}
				
				Article nextArticle = null;
				int nextArticleIndex = i - 1;
				int nextArticleId = 0;
				
				if(nextArticleIndex >= 0) {
					nextArticle = articles.get(nextArticleIndex);
					nextArticleId = nextArticle.getId();
				}
				
				StringBuilder sb = new StringBuilder();
				String writer = memberService.getMemberNameById(article.getMemberId());				
				
				head = getHeadHtml("article_detail");
				head = head.replace("[[article-detail]]", article.getTitle());
				
				sb.append(head);
				
				String body = bodyTemplate.replace("[[article-detail__title]]", article.getTitle());
				body = body.replace("[[article-detail__board-name]]", boardService.getBoardNameById(article.getBoardId()));
				body = body.replace("[[article-detail__reg-date]]", article.getRegDate());
				body = body.replace("[[article-detail__writer]]", writer);
				body = body.replace("[[article-detail__body]]", article.getBody());
				
				String tags = Container.articleService.getTagsByRelTypeCodeAndRelId("article", article.getId());
				String[] tagBits = tags.split(" ");
				String tagContent = "";
				
				for(int j = 0; j < tagBits.length; j++) {
					tagBits[j] = tagBits[j].replace("#", "");
					if(tagBits[j].length() > 0) {
						tagBits[j] = "<a href=\"article_tag.html?tag=" + tagBits[j] + "\">#" + tagBits[j] + " </a>";
					}
					tagContent = tagContent + tagBits[j];
				}
				
				body = body.replace("[[tag-content]]", tagContent);

				body = body.replace("[[article-detail-prev-url]]", getArticleDetailFileName(prevArticleId));
				body = body.replace("[[article-detail-prev-attr]]", prevArticle != null ? prevArticle.getTitle() : "");
				body = body.replace("[[article-detail-prev-addi]]", prevArticleId == 0 ? "none" : "");

				body = body.replace("[[article-detail-list-url]]", getArticleListFileName(boardService.getBoardNameById(article.getBoardId()), 1));

				body = body.replace("[[article-detail-next-url]]", getArticleDetailFileName(nextArticleId));
				body = body.replace("[[article-detail-next-attr]]", nextArticle != null ? nextArticle.getTitle() : "");
				body = body.replace("[[article-detail-next-addi]]", nextArticleId == 0 ? "none" : "");

				body = body.replace("[[file-name}", getArticleDetailFileName(articles.get(i).getId()));
				body = body.replace("[[site-domain}", "blog.nwh.kr");
								
				sb.append(body);
				
				sb.append(foot);
				
				String fileName = getArticleDetailFileName(article.getId());
				
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

			List<Article> articles = articleService.getArticleByBoardId(board.getId());
			int articleCount = articles.size();
			int totalPage = (int) Math.ceil((double) articleCount / itemsInAPage);
			
			Collections.reverse(articles);

			for (int i = 1; i <= totalPage; i++) {
				buildArticleListPage(board, itemsInAPage, pageBoxMenuSize, articles, i);
			}

		}

	}

	private void buildArticleListPage(Board board, int itemsInAPage, int pageBoxSize, List<Article> articles,
			int page) {

		StringBuilder sb = new StringBuilder();

		sb.append(getHeadHtml("article_list_" + board.getCode()));

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

			String link = getArticleDetailFileName(article.getId());
			String writer = memberService.getMemberNameById(article.getMemberId());

			mainContent.append("<ul class=\"flex\">");

			mainContent.append("<li>" + article.getId() + "</li>");
			mainContent.append("<li>" + article.getRegDate() + "</li>");
			mainContent.append("<li>" + writer + "</li>");
			mainContent.append("<li><a href=\"" + link + "\">" + article.getTitle() + "</a></li>");
			mainContent.append("<li>" + article.getHitsCount() + "</li>");
			mainContent.append("<li>" + article.getCommentsCount() + "</li>");
			mainContent.append("<li>" + article.getLikesCount() + "</li>");

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
		if(pageBoxEndAfterPage > totalPage) {
			pageBoxEndAfterPage = totalPage;
		}
		
		boolean pageBoxStartBeforeBtnNeedToShow = pageBoxStartBeforePage != pageBoxStartPage;
		
		boolean pageBoxEndAfterBtnNeedToShow = pageBoxEndAfterPage != pageBoxEndPage;
		
		if(pageBoxStartBeforeBtnNeedToShow) {
			pageMenuContent.append("<li><a href=\"" + getArticleListFileName(board, pageBoxStartBeforePage) + "\">&lt; 이전</a></li>");
		}else {
			pageMenuContent.append("<li><a href=\"\"></li>");
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
		}else {
			pageMenuContent.append("<li><a href=\"\"></li>");
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
		return getArticleListFileName(board.getCode(), page);
	}

	private String getArticleListFileName(String boardCode, int page) {
		return "article_list_" + boardCode + "_" + page + ".html";
	}

	public String getArticleDetailFileName(int id) {

		return "article_detail_" + id + ".html";

	}

	private void buildIndexPage() {

		StringBuilder sb = new StringBuilder();

		String head = getHeadHtml("index");
		String foot = Util.getFileContents("site_template/foot.html");

		String html = Util.getFileContents("site_template/index.html");
		
		List<Article> articles = articleService.getLatestArticles();
		
		StringBuilder latestArticles = new StringBuilder();
		
		for(Article article : articles) {
			
			latestArticles.append("<div>");
			latestArticles.append("<span>" + article.getRegDate().substring(0, 10) + "</span>");
			latestArticles.append("<span><a href=\"" + getArticleDetailFileName(article.getId()) + "\">" + article.getTitle() + "</a></span>");
			latestArticles.append("</div>");
		}
		
		html = html.replace("[[latest articles]]", latestArticles);

		sb.append(head);
		sb.append(html);
		sb.append(foot);
		
		StringBuilder mobileSb = new StringBuilder();
		
		mobileSb.append(buildMobileIndexPage(mobileSb));
		
		sb.append(mobileSb);

		String filePath = "site/index.html";
		Util.writeFileContents(filePath, sb.toString());
		System.out.println(filePath + " 생성");

	}

	private String getHeadHtml(String pageName) {

		String head = Util.getFileContents("site_template/head.html");

		StringBuilder boardMenuContents = new StringBuilder();
		
		String articleSearchLink = "article_search.html";

		boardMenuContents.append("<li>");
		
		boardMenuContents.append("<a href=\"" + articleSearchLink + "\" class=\"block\">");

		boardMenuContents.append(getTitleBarContentByPageName("article_search"));

		boardMenuContents.append("</a>");

		boardMenuContents.append("</li>");

		List<Board> boards = boardService.getBoards();

		for (Board board : boards) {

			String link = "article_list_"+ board.getCode() +"_1.html";

			boardMenuContents.append("<li>");
			
			boardMenuContents.append("<a href=\"" + link + "\" class=\"block\">");

			boardMenuContents.append(getTitleBarContentByPageName("article_list_" + board.getCode()));

			boardMenuContents.append("</a>");

			boardMenuContents.append("</li>");

		}

		head = head.replace("[[menu-bar__menu1__menulist]]", boardMenuContents);

		String titleBarType = getTitleBarContentByPageName(pageName);

		head = head.replace("[[title-bar]]", titleBarType);
		
		String siteName = getPageTitle(pageName);
		String siteDescription = "웹프로그래밍, 일상에 대해 포스팅합니다";
		String siteDomain = "blog.nwh.kr";
		String siteMainUrl = "https://" + siteDomain;
		String currentDate = Util.getNowDateStr();
		String siteSubject = "웹프로그래밍, 일상 블로그";
		String siteKeywords = "JAVA, HTML, CSS, JSP, SQL";
		
		head = head.replace("[[site-name]]", siteName);
		head = head.replace("[[page-title]]", siteName);
		head = head.replace("[[site-description]]", siteDescription);
		head = head.replace("[[site-domain]]", siteDomain);
		head = head.replace("[[site-main-url]]", siteMainUrl);
		head = head.replace("[[current-date]]", currentDate);
		head = head.replace("[[site-subject]]", siteSubject);
		head = head.replace("[[current-keywords]]", siteKeywords);

		return head;
	}

	private String getPageTitle(String pageName) {

		String forPrintPageName = "";
		StringBuilder sb = new StringBuilder();
						
		if( pageName.equals("index")) {
			forPrintPageName = "home";
		} else if ( pageName.equals("stat")) {
			forPrintPageName = "statistics";
		} else if ( pageName.equals("search")) {
			forPrintPageName = "search";
		} else if ( pageName.startsWith("article_list")) {
			forPrintPageName = pageName;
		} else if ( pageName.startsWith("article_detail")) {
			forPrintPageName = pageName;
		} 
		
		forPrintPageName = forPrintPageName.toUpperCase();
		forPrintPageName = forPrintPageName.replace("_", " ");
		
		sb.append("Java's Meow | ");
		sb.append(forPrintPageName);
		
		return sb.toString();
	}

	private String getTitleBarContentByPageName(String pageName) {

		if (pageName.equals("index")) {
			return "<i class=\"fas fa-home\"></i> <span>HOME</span>";
			
		} else if (pageName.startsWith("article_list_it")) {
			return "<i class=\"fab fa-java\"></i> <span>IT LIST</span>";
			
		} else if (pageName.startsWith("article_list_note")) {
			return "<i class=\"fas fa-book-open\"></i> <span>NOTE</span>";	
			
		} else if (pageName.startsWith("article_list_")) {
			return "<i class=\"fas fa-clipboard-list\"></i> <span>LIST</span>";
			
		} else if (pageName.equals("article_detail")) {
			return "<i class=\"fas fa-file-alt\"></i> <span>ARTICLE DETAIL</span>";
			
		} else if (pageName.equals("stat")) {
			return "<i class=\"fas fa-chart-pie\"></i> <span>STATISTICS</span>";
			
		} else if (pageName.startsWith("article_search")) {
			return "<i class=\"fas fa-search\"></i> <span>SEARCH</span>";
			
		} else if (pageName.startsWith("article_tag")) {
			return "<i class=\"fas fa-hashtag\"></i> <span>TAG</span>";
			
		} 		
		
		return "";
	}

}