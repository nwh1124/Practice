package com.sbs.example.textBoard.service;

import java.io.File;
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
		buildArticleDetailPages();
		
	}

	private void buildArticleListPages() {
		
		
	}

	private void buildArticleDetailPages() {
		
		String fileName = "";
		String boardName = "free notice";
		String[] boardNameSplit = boardName.split(" ");
		
		int[] lastBoardList = new int[boardNameSplit.length];
		int listId;
		
		String head = getHeadHtml("boardList");
		
		String foot = Util.getFileContents("site_template/foot.html");

		for (int i = 0; i < boardNameSplit.length; i++) {
			List<Article> articles = articleService.getArticles(boardNameSplit[i]);

			int listSize = articles.size() / 10;
			lastBoardList[i] = listSize;
			listId = 1;

			for (int j = 0; j < listSize; j++) {

				StringBuilder sb = new StringBuilder();
				
				head = getHeadHtml("article_list_free");

				sb.append(head);

				sb.append("<title>" + boardNameSplit[i] + "게시판 </title>");

				sb.append("<body>");

				sb.append("<h1>");
				sb.append(boardNameSplit[i] + " 게시판");
				sb.append("</h1>");

				

				for (int k = 1; k <= 10; k++) {
					int l = 10 * j;
					if (k % 10 == 0) {
						sb.append("<a href=\"article/" + boardNameSplit[i] + "-list-" + (j + 2) + "-" + (k + l) + ".html\">"
								+ (k + l) + "번 게시물</a><br>");
					} else {
						sb.append("<a href=\"article/" + boardNameSplit[i] + "-list-" + (j + 1) + "-" + (k + l) + ".html\">"
								+ (k + l) + "번 게시물</a><br>");
					}
				}

				sb.append("</div>");
				sb.append("<br>");
				sb.append("<div>");
				
				sb.append("<div>게시판 페이지 <br>");

				for (int k = 1; k <= listSize; k++) {
					sb.append("<a href=\"" + boardNameSplit[i] + "-list-" + k + ".html\">" + k + "</a>&nbsp");
				}

				sb.append("</div><br>");

				sb.append("<div>");

				if (j > 0) {
					sb.append("<a href=\"" + boardNameSplit[i] + "-list-" + j + ".html\">이전 페이지</a><br>");
				}
				if (j < listSize - 1) {
					sb.append("<a href=\"" + boardNameSplit[i] + "-list-" + (j + 2) + ".html\">다음 페이지</a><br>");
				}

				sb.append("<a href=\"index.html\">HOME</a><br>");

				sb.append("</div>");

				sb.append("</body>");
				
				sb.append("</section>");

				sb.append(foot);

				fileName = "site/" + boardNameSplit[i] + "-list-" + (j + 1) + ".html";

				Util.writeFileContents(fileName, sb.toString());

				System.out.printf(fileName + " 생성\n");

			}

			// 게시판 게시물
			for (Article article : articles) {

				StringBuilder sb = new StringBuilder();
				
				head = getHeadHtml("article_detail");

				sb.append(head);

				sb.append("<h1>게시물 상세 페이지</h1>");
				sb.append("<div>");

				sb.append("번호 : " + article.id + "<br>");
				sb.append("생성날짜 : " + article.regDate + "<br>");
				sb.append("갱신날짜 : " + article.updateDate + "<br>");
				sb.append("작성자 : " + article.memberId + "<br>");
				sb.append("제목 : " + article.title + "<br>");
				sb.append("내용 : " + article.body + "<br>");

				if (listId > 1) {
					if (listId % 10 == 0) {
						sb.append("<a href=\"" + boardNameSplit[i] + "-list-" + ((listId - 1) / 10 + 1) + "-"
								+ (listId - 1) + ".html\">이전글</a><br>");
					} else {
						sb.append("<a href=\"" + boardNameSplit[i] + "-list-" + (listId / 10 + 1) + "-" + (listId - 1)
								+ ".html\">이전글</a><br>");
					}
				}
				if (listId < articles.size()) {
					if (listId % 10 == 9) {
						sb.append("<a href=\"" + boardNameSplit[i] + "-list-" + ((listId + 1) / 10 + 1) + "-"
								+ (listId + 1) + ".html\">다음글</a><br>");
					} else {
						sb.append("<a href=\"" + boardNameSplit[i] + "-list-" + (listId / 10 + 1) + "-" + (listId + 1)
								+ ".html\">다음글</a><br>");
					}
				}

				sb.append("<a href=\"../" + boardNameSplit[i] + "-list-" + (((listId - 1) / 10) + 1)
						+ ".html\">게시물 목록</a><br>");

				sb.append("</div>");
				sb.append("</body>");
				sb.append("</html>");

				sb.append(foot);

				fileName = boardNameSplit[i] + "-list-" + (listId / 10 + 1) + "-" + listId + ".html";

				listId++;

				String filePath = "site/article/" + fileName;

				Util.writeFileContents(filePath, sb.toString());

				System.out.println(filePath + " 생성");
				
			}
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
		System.out.println(filePath+" 생성");

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
		
		if(pageName.equals("article_detail")) {
			head = head.replace("[[href#HOME]]", "../index.html");
			head = head.replace("[[href#ARTICLES]]", "../free-list-1.html");
			head = head.replace("[[href#FREE]]", "../free-list-1.html");
			head = head.replace("[[href#NOTICE]]", "../notice-list-1.html");
		}else {
			head = head.replace("[[href#HOME]]", "index.html");
			head = head.replace("[[href#ARTICLES]]", "free-list-1.html");
			head = head.replace("[[href#FREE]]", "free-list-1.html");
			head = head.replace("[[href#NOTICE]]", "notice-list-1.html");
		}
		
		return head;
	}

	private String getTitleBarContentByPageName(String pageName) {
		
		if(pageName.equals("index")) {
			return "<i class=\"fas fa-home\"></i> <span>HOME</span>";
		}else if(pageName.equals("boardList")) {
			return "<i class=\"fas fa-clipboard-list\"></i> <span>BOARD LIST</span>";
		}else if(pageName.startsWith("article_list_free")) {
			return "<i class=\"fab fa-free-code-camp\"></i> <span>FREE LIST</span>";
		}else if(pageName.startsWith("article_list_notice")) {
			return "<i class=\"fas fa-flag\"></i> <span>NOTICE LIST</span>";
		}else if(pageName.startsWith("aritcle_list")) {
			return "<i class=\"fas fa-clipboard-list\"></i> <span>LIST</span>";
		}else if(pageName.equals("article_detail")) {
			return "<i class=\"fas fa-file-alt\"></i> <span>ARTICLE DETAIL</span>";
		}
		
		return "";
	}	
	
	public void doTest() {

	}

}