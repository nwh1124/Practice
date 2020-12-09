package com.sbs.example.textBoard.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.sbs.example.textBoard.container.Container;
import com.sbs.example.textBoard.dto.Article;
import com.sbs.example.textBoard.util.Util;

public class BuildService {
	
	ArticleService articleService;
	MemberService memberService;
	
	public BuildService() {
		
		articleService = Container.articleService;
		memberService = Container.memberService;
		
	}

	public void buildSite_article() {
		
		File makeFolder = new File("site/article");	
		
		if(makeFolder.exists() == false) {
			makeFolder.mkdirs();
		}
		
		int listId = 1;
		String fileName = "";
		StringBuilder sb = new StringBuilder();
		
	
		//목록 생성
		
		String boardName = "free notice";
		String[] boardNameSplit = boardName.split(" "); 
		int[] lastBoardList = new int [boardNameSplit.length];
		
		for(int i = 0; i < boardNameSplit.length; i++) {
			List<Article> articles = articleService.getArticles(boardNameSplit[i]);
			
			int listSize = articles.size() / 10;
			lastBoardList[i] = listSize;
			listId = 1;
			
			for(int j = 0; j < listSize; j++) {
				
				sb = new StringBuilder();
				sb.append("<!DOCTYPE html>");
				sb.append("<html lang=\"ko\">");
				
				sb.append("<head>");
				
				sb.append("<meta charset=\"UTF-8\">");
				sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
				sb.append("<title>" + boardNameSplit[i] + "게시판 </title>");
				
				sb.append("</head>");			
				sb.append("<body>");
				
				sb.append("<h1>");			
				sb.append(boardNameSplit[i] +" 게시판");			
				sb.append("</h1>");
				
				sb.append("<div>게시판 페이지 <br>");
				
				for(int k = 1; k <= listSize; k++) {				
					sb.append("<a href=\"" + boardNameSplit[i] + "-list-" + k + ".html\">"+ k + "</a>&nbsp");				
				}				
				
				sb.append("</div><br>");
				
				sb.append("<div>");
				
				for(int k = 1; k <= 10; k++) {
					int l = 10 * j;
					if(k % 10 == 0) {
						sb.append("<a href=\"" + boardNameSplit[i] + "-list-"+ (j + 2) + "-" + (k + l) + ".html\">"+ (k + l) +"번 게시물</a><br>");
					}else{
						sb.append("<a href=\"" + boardNameSplit[i] + "-list-"+ (j + 1) + "-" + (k + l) + ".html\">"+ (k + l) +"번 게시물</a><br>");
					}
				}
				
				sb.append("</div>");
				sb.append("<br>");
				sb.append("<div>");
				
				if(j > 0) {
					sb.append("<a href=\"" + boardNameSplit[i] + "-list-" + j + ".html\">이전 페이지</a><br>");
				}
				if(j < listSize - 1) {
					sb.append("<a href=\"" + boardNameSplit[i] + "-list-" + (j + 2) + ".html\">다음 페이지</a><br>");
				}
				
				sb.append("<a href=\"../index.html\">HOME</a><br>");
				
				sb.append("</div>");
				
				sb.append("</body>");
				
				sb.append("</html>");
				
				fileName = "site/article/" + boardNameSplit[i] + "-list-" + (j + 1) + ".html";
				
				Util.writeFileContents(fileName, sb.toString());
				
				System.out.printf(fileName +" 생성\n");		
				
				
			}
			
			String header;
			
			Util.getFileContents("site/template/header.html");
			
			//게시판 게시물
			for(Article article : articles) {
				
				sb = new StringBuilder();
				
				sb.append(header);
				
				sb.append("<!DOCTYPE html>");
				sb.append("<html lang=\"ko\">");
				
				sb.append("<head>");
				
				sb.append("<meta charset=\"UTF-8\">");
				sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
				sb.append("<title>게시물 상세페이지 - " + article.title + "</title>");
				
				sb.append("</head>");			
				sb.append("<body>");
				
				sb.append("<h1>게시물 상세 페이지</h1>");			
				sb.append("<div>");
				
				sb.append("번호 : " + article.id + "<br>");
				sb.append("생성날짜 : " + article.regDate + "<br>");
				sb.append("갱신날짜 : " + article.updateDate + "<br>");
				sb.append("작성자 : " + article.memberId + "<br>");
				sb.append("제목 : " + article.title + "<br>");
				sb.append("내용 : " + article.body + "<br>");

				if(listId > 1) {
					if(listId % 10 == 0) {
						sb.append("<a href=\"" + boardNameSplit[i] + "-list-" + ((listId-1)/10 + 1) + "-" + (listId - 1) + ".html\">이전글</a><br>");
					}else{
						sb.append("<a href=\"" + boardNameSplit[i] + "-list-" + (listId/10 + 1) + "-" + (listId - 1) + ".html\">이전글</a><br>");
					}
				}
				if(listId < articles.size()-1) {
					if(listId % 10 == 9) {
						sb.append("<a href=\"" + boardNameSplit[i] + "-list-" + ((listId+1)/10 + 1) + "-" + (listId + 1) + ".html\">다음글</a><br>");
					}else {				
						sb.append("<a href=\"" + boardNameSplit[i] + "-list-" + (listId/10 + 1) + "-" + (listId + 1) + ".html\">다음글</a><br>");
					}
				}
				
				sb.append("<a href=\"" + boardNameSplit[i] + "-list-" + (((listId - 1)/10) + 1) + ".html\">게시물 목록</a><br>");
				
				sb.append("</div>");			
				sb.append("</body>");			
				sb.append("</html>");
				
				fileName = boardNameSplit[i] + "-list-" + (listId/10 + 1) + "-" + listId + ".html";
				
				listId++;
				
				String filePath = "site/article/" + fileName ;
				
				Util.writeFileContents(filePath, sb.toString());
				
				System.out.println(filePath + " 생성");			
				
			}
			
		}
		
		//인덱스 생성
		
		sb = new StringBuilder();
		
		sb.append("<!DOCTYPE html>");
		sb.append("<html lang=\"ko\">");
		
		sb.append("<head>");
		
		sb.append("<meta charset=\"UTF-8\">");
		sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		sb.append("<title> HOME </title>");
		
		sb.append("</head>");		
		sb.append("<body>");		
		sb.append("<h1>");		
		sb.append("게시판");		
		sb.append("</h1>");		
		sb.append("<div>");
		
		sb.append("<a href=\"article/notice-list-" + lastBoardList[1] + ".html\">" + boardNameSplit[1] + " board</a><br>");
		sb.append("<a href=\"article/free-list-" + lastBoardList[0] + ".html\">" + boardNameSplit[0] + " board</a><br>");
		sb.append("</div>");
		
		sb.append("<h2>");		
		sb.append("통계");		
		sb.append("</h2>");				
		sb.append("<div>");		
		sb.append("<a href=\"#\">통계</a>");		
		sb.append("</div>");
		
		sb.append("<h2>");		
		sb.append("블로그");	
		sb.append("</h2>");		
		sb.append("<div>");		
		sb.append("<a href=\"https://nwh1124.tistory.com\" target=\"_blank\">블로그</a>");		
		sb.append("</div>");
		
		sb.append("<h2>");		
		sb.append("GIT Repository");		
		sb.append("</h2>");		
		sb.append("<div>");		
		sb.append("<a href=\"https://github.com/nwh1124?tab=repositories\" target=\"_blank\">GIT Repository</a>");		
		sb.append("</div>");
		
		sb.append("</body>");		
		sb.append("</html>");
		
		fileName = "site/index.html";
		
		Util.writeFileContents(fileName, sb.toString());
		
		System.out.printf(fileName +" 생성\n");
		
	}

}