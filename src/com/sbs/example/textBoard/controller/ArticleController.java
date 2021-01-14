package com.sbs.example.textBoard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.textBoard.container.Container;
import com.sbs.example.textBoard.dto.Article;
import com.sbs.example.textBoard.dto.Recommend;
import com.sbs.example.textBoard.dto.Reply;
import com.sbs.example.textBoard.service.ArticleService;
import com.sbs.example.textBoard.service.MemberService;

public class ArticleController extends Controller{
	
	
	private Scanner sc;
	private ArticleService articleService;
	private MemberService memberService;
	
	public ArticleController() {
		
		sc = Container.sc;
		articleService = Container.articleService;
		memberService = Container.memberService;
		
	}

	public void doCommand(String cmd) {
		
		if(cmd.equals("article add")) {
			add();
		}else if(cmd.equals("article list")) {
			list();
		}else if(cmd.startsWith("article delete ")) {
			delete(cmd);
		}else if(cmd.startsWith("article modify ")) {
			modify(cmd);
		}else if(cmd.startsWith("article detail ")) {
			detail(cmd);
		}else if(cmd.startsWith("article recommend")) {
			recommend(cmd);
		}else if(cmd.startsWith("article cancelrecommend")) {
			cancelrecommend(cmd);
		}else if(cmd.startsWith("article writeReply")) {
			writeReply(cmd);
		}else if(cmd.startsWith("article deleteReply")) {
			deleteReply(cmd);
		}else if(cmd.startsWith("article modifyReply")) {
			modifyReply(cmd);
		}else if(cmd.equals("article listReply")) {
			listReply(cmd);
		}
		
	}

	private void listReply(String cmd) {
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}
		
		List<Reply> replys = articleService.getReplysByMemberId(Container.session.getLoginedId());
				
		System.out.println("= 사용자가 작성한 댓글 목록 =");
		for(Reply reply : replys) {
			String writer = memberService.getMemberNameById(reply.getMemberId());
			System.out.printf("%d / %s / %s / %s\n", reply.getId(), reply.getRegDate(), writer, reply.getBody());
		}
		
	}

	private void modifyReply(String cmd) {
		
		System.out.println("= 댓글 수정 =");
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}
		
		List<Reply> replys = articleService.getReplysByMemberId(Container.session.getLoginedId());
				
		System.out.println("= 사용자가 작성한 댓글 목록 =");
		for(Reply reply : replys) {
			String writer = memberService.getMemberNameById(reply.getMemberId());
			System.out.printf("%d / %s / %s / %s\n", reply.getId(), reply.getRegDate(), writer, reply.getBody());
		}
		
		System.out.printf("수정할 댓글 번호 : ");
		String modReplyId = sc.nextLine();
		
		System.out.printf("수정할 댓글 내용 : ");
		String modReplyBody = sc.nextLine();
		
		articleService.modifyReply(modReplyId, modReplyBody);
		System.out.println("= 댓글이 수정되었습니다 =");
		
	}

	private void deleteReply(String cmd) {
		
		System.out.println("= 댓글 삭제 =");
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}
		
		List<Reply> replys = articleService.getReplysByMemberId(Container.session.getLoginedId());
				
		System.out.println("= 사용자가 작성한 댓글 목록 =");
		for(Reply reply : replys) {
			String writer = memberService.getMemberNameById(reply.getMemberId());
			System.out.printf("%d / %s / %s / %s\n", reply.getId(), reply.getRegDate(), writer, reply.getBody());
		}
		
		System.out.printf("삭제할 댓글 번호 : ");
		String deleteReplyId = sc.nextLine();
		
		int id = articleService.deleteReply(deleteReplyId);

		if(id == 0) {
			System.out.printf("= %s번 댓글은 존재하지 않습니다 =\n", deleteReplyId);
		}
		
		System.out.println("= 삭제되었습니다 =\n");
	}

	private void writeReply(String cmd) {
		
		System.out.println("= 댓글 쓰기 =");
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}

		int inputedId = 0;
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length <3) {
			System.out.println("= 댓글을 작성할 게시물 번호를 입력해주세요 =");
			return;
		}
		
		if(cmdBits.length > 2) {
			inputedId = Integer.parseInt(cmdBits[2]);
		}
		
		Article article = articleService.getArticle(inputedId);
		
		if(article == null) {
			System.out.println("= 게시물이 존재하지 않습니다 =");
			return;
		}
		
		System.out.printf("작성할 댓글 : ");
		String reply = sc.nextLine();
		
		articleService.writeReply(reply, inputedId, Container.session.getLoginedId());
		System.out.println("= 작성되었습니다 =");
				
	}

	private void cancelrecommend(String cmd) {
		
		System.out.println("== 게시물 추천 취소 ==");
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}

		int inputedId = 0;
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length <3) {
			System.out.println("= 추천 취소할 게시물 번호를 입력해주세요 =");
			return;
		}
		
		if(cmdBits.length > 2) {
			inputedId = Integer.parseInt(cmdBits[2]);
		}
		
		Article article = articleService.getArticle(inputedId);
		
		if(article == null) {
			System.out.println("= 게시물이 존재하지 않습니다 =");
			return;
		}
		
		List<Recommend> recos = articleService.getReco(inputedId);
		
		
		for(Recommend reco : recos) {
			if(reco.getMemberId() == Container.session.getLoginedId() && reco.getPoint() > 0){
				articleService.cancelRoce(inputedId, Container.session.getLoginedId());				
				System.out.println("= 추천 취소되었습니다 =");
				return;
			}
		}
		
		System.out.println("= 추천하지 않은 게시물입니다 = ");
		
	}

	private void recommend(String cmd) {
		
		System.out.println("== 게시물 추천 ==");
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}

		int inputedId = 0;
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length <3) {
			System.out.println("= 추천할 게시물 번호를 입력해주세요 =");
			return;
		}
		
		if(cmdBits.length > 2) {
			inputedId = Integer.parseInt(cmdBits[2]);
		}
		
		Article article = articleService.getArticle(inputedId);
		
		if(article == null) {
			System.out.println("= 게시물이 존재하지 않습니다 =");
			return;
		}
		
		List<Recommend> replys = articleService.getReco(inputedId);
		
		
		for(Recommend reply : replys) {
			if(reply.getMemberId() == Container.session.getLoginedId() && reply.getPoint() > 0 ){
				System.out.println("= 이미 추천한 게시물입니다 =");
				return;
			}
		}
		
		articleService.recommend(inputedId, Container.session.getLoginedId());
		System.out.println("= 추천되었습니다 =");
		
	}

	private void detail(String cmd) {
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}

		System.out.println("= 게시물 상세 =");
		
		int inputedId = 0;
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length <3) {
			System.out.println("= 수정할 게시물 번호를 입력해주세요 =");
			return;
		}
		
		if(cmdBits.length > 2) {
			inputedId = Integer.parseInt(cmdBits[2]);
		}
		
		Article article = articleService.getArticle(inputedId);
		
		if(article == null) {
			System.out.println("= 게시물이 존재하지 않습니다 =");
			return;
		}
		
		String writer = memberService.getMemberNameById(article.getMemberId());

		System.out.printf("게시판 : %s\n", article.getBoardId());
		System.out.printf("번호 : %d\n", article.getId());
		System.out.printf("작성일 : %s\n", article.getRegDate());
		System.out.printf("조회수 : %d\n", article.getHitsCount());
		System.out.printf("추천: %d\n", article.getRecommend());
		System.out.printf("작성자 : %s\n", writer);
		System.out.printf("제목 : %s\n", article.getTitle());
		System.out.printf("내용 : %s\n", article.getBody());

		articleService.doHitPlus(inputedId);
		
		List<Reply> replys = articleService.getReplys();
		
		List<Reply> listReply = new ArrayList<>();
		
		for(Reply reply : replys) {
			if(reply.getRelId() == inputedId) {
				listReply.add(reply);
			}
		}
		
		System.out.println("== 게시물의 댓글 ==");
		System.out.println("번호 / 작성일 / 작성자 / 내용");
		
		for(Reply reply : listReply) {
			writer = memberService.getMemberNameById(reply.getMemberId());

			System.out.printf("%d /", reply.getId());
			System.out.printf("%s /", reply.getRegDate());
			System.out.printf("%s /", writer);
			System.out.printf("%s \n", reply.getBody());
			
		}
		
	}

	private void modify(String cmd) {
		
		System.out.println("== 게시물 수정 ==");
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}

		int inputedId = 0;
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length <3) {
			System.out.println("= 수정할 게시물 번호를 입력해주세요 =");
			return;
		}
		
		if(cmdBits.length > 2) {
			inputedId = Integer.parseInt(cmdBits[2]);
		}
		
		Article article = articleService.getArticle(inputedId);
		
		if(article == null) {
			System.out.println("= 게시물이 존재하지 않습니다 =");
			return;
		}
		
		if(Container.session.getLoginedId() != article.getMemberId()) {
			System.out.println("= 권한이 없습니다 =");
			return;
		}
		
		int missCount = 0;
		int missCountMax = 3;
		
		String modTitle;
		String modBody;
		
		while(true) {			
			if(missCount >= missCountMax) {
				System.out.println("= 게시물 등록 취소 =");
				return;
			}
			
			System.out.printf("제목 : ");
			modTitle = sc.nextLine();
					
			if(modTitle.trim().length() == 0) {
				System.out.println("= 제목을 입력해주세요 =");
				missCount++;
				continue;
			}else {
				break;
			}
		}
		
		System.out.printf("내용 : ");
		modBody = sc.nextLine();
		
		articleService.modify(modTitle, modBody, inputedId);
		System.out.println("= 수정되었습니다 = ");
		
	}

	private void delete(String cmd) {
		System.out.println("== 게시물 삭제 ==");
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}

		int inputedId = 0;
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length <3) {
			System.out.println("= 삭제할 게시물 번호를 입력해주세요 =");
			return;
		}
		
		if(cmdBits.length > 2) {
			inputedId = Integer.parseInt(cmdBits[2]);
		}
		
		Article article = articleService.getArticle(inputedId);
		
		if(article == null) {
			System.out.println("= 게시물이 존재하지 않습니다 =");
			return;
		}
		
		if(Container.session.getLoginedId() != article.getMemberId()) {
			System.out.println("= 권한이 없습니다 =");
			return;
		}
		
		articleService.delete(inputedId);
		System.out.printf("= %d번 게시물이 삭제되었습니다 =\n", inputedId);
		
	}

	private void list() {
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}
		
		System.out.println("== 게시물 목록 ==");
		
		List<Article> articles = articleService.getArticles();
		
		System.out.println("번호 / 작성일 / 작성자 / 제목 / 조회수 / 추천");
		
		for(Article article : articles) {
			String writer = memberService.getMemberNameById(article.getMemberId());
			System.out.printf("%d /", article.getId());
			System.out.printf("%s /", article.getRegDate());
			System.out.printf("%s /", writer);
			System.out.printf("%s /", article.getTitle());
			System.out.printf("%d /", article.getHitsCount());
			System.out.printf("%d \n", article.getRecommend());
		}
		
	}

	private void add() {
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 후 이용해주세요 =");
			return;
		}
		
		System.out.println("== 게시물 등록 ==");
		
		int missCount = 0;
		int missCountMax = 3;
		
		String title;
		String body;
		
		while(true) {			
			if(missCount >= missCountMax) {
				System.out.println("= 게시물 등록 취소 =");
				return;
			}
			
			System.out.printf("제목 : ");
			title = sc.nextLine();
					
			if(title.trim().length() == 0) {
				System.out.println("= 제목을 입력해주세요 =");
				missCount++;
				continue;
			}else {
				break;
			}
		}
		
		System.out.printf("내용 : ");
		body = sc.nextLine();
		
		int id = articleService.add(title, body, Container.session.getLoginedId(), Container.session.getSelectedBoard());
		System.out.printf("= %d번 게시물이 등록되었습니다 =\n", id);
		
	}
}
