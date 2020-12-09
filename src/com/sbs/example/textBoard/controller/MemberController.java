package com.sbs.example.textBoard.controller;

import java.util.Scanner;

import com.sbs.example.textBoard.container.Container;
import com.sbs.example.textBoard.dto.Member;
import com.sbs.example.textBoard.service.MemberService;

public class MemberController extends Controller{
	
	private Scanner sc;
	private MemberService memberService;
	
	public MemberController() {
		
		sc = Container.sc;
		memberService = Container.memberService;
		
	}

	public void doCommand(String cmd) {
		
		if(cmd.equals("member join")) {
			join();
		}else if(cmd.equals("member login")) {
			login();
		}else if(cmd.equals("member logout")) {
			logout();
		}else if(cmd.equals("member whoami")) {
			whoami();
		}
		
	}

	private void whoami() {
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 비회원입니다 =");
			return;
		}
		
		System.out.println("== 회원 정보 상세 ==");
		
		Member member = memberService.getMemberById(Container.session.getLoginedId());
		
		System.out.printf("번호 : %d\n", member.id);
		System.out.printf("가입일 : %s\n", member.regDate);
		System.out.printf("아이디 : %s\n", member.loginId);
		System.out.printf("이름 : %s\n", member.name);
		
	}

	private void logout() {
		
		System.out.println("== 로그아웃 ==");
		
		if(Container.session.getLogined() == false) {
			System.out.println("= 로그인 중이 아닙니다 =");
			return;
		}
		
		Container.session.logout();
		System.out.println("= 로그아웃 되었습니다 =");
		
	}

	private void login() {
		
		if(Container.session.getLogined()) {
			System.out.println("= 이미 로그인 중입니다 =");
			return;
		}
		
		System.out.println("== 로그인 ==");
		
		int missCount = 0;
		int missCountMax = 3;
		
		String loginId;
		String loginPw;
		
		Member member;
		
		while(true) {
			
			if(missCount >= missCountMax) {
				System.out.println("= 로그인 취소 =");
				return;
			}
			
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
					
			member = memberService.getMemberByLoginId(loginId);
					
			if(loginId.trim().length() == 0) {
				System.out.println("= 아이디를 입력해주세요 =");
				missCount++;
				continue;
			}else if(member == null) {
				System.out.println("= 존재하지 않는 아이디입니다 =");
				missCount++;
				continue;
			}else {
				break;
			}
		}
		
		missCount = 0;
		
		while(true) {
			
			if(missCount >= missCountMax) {
				System.out.println("= 회원 가입 취소 =");
				return;
			}
			
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
					
			if(loginPw.trim().length() == 0) {
				System.out.println("= 비밀번호를 입력해주세요 =");
				missCount++;
				continue;
			}else if(member.loginPw.equals(loginPw) == false) {
				System.out.println("= 비밀번호가 일치하지 않습니다 =");
				missCount++;
				continue;
			}else {
				break;
			}
		}
		
		Container.session.login(member.id);
		System.out.printf("= %s님, 로그인 되었습니다 =\n", member.name);
		
	}

	private void join() {
		
		if(Container.session.getLogined()) {
			System.out.println("= 로그아웃 후 이용해주세요 =");
			return;
		}
		
		System.out.println("== 회원 가입 ==");
		
		int missCount = 0;
		int missCountMax = 3;
		
		String loginId;
		String loginPw;
		String name;
		
		Member member;
		
		while(true) {
			
			if(missCount >= missCountMax) {
				System.out.println("= 회원 가입 취소 =");
				return;
			}
			
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
					
			member = memberService.getMemberByLoginId(loginId);
					
			if(loginId.trim().length() == 0) {
				System.out.println("= 아이디를 입력해주세요 =");
				missCount++;
				continue;
			}else if(member != null) {
				System.out.println("= 이미 존재하는 아이디입니다 =");
				missCount++;
				continue;
			}else {
				break;
			}
		}
		
		missCount = 0;
		
		while(true) {
			
			if(missCount >= missCountMax) {
				System.out.println("= 회원 가입 취소 =");
				return;
			}
			
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
					
			if(loginPw.trim().length() == 0) {
				System.out.println("= 비밀번호를 입력해주세요 =");
				missCount++;
				continue;
			}else {
				break;
			}
		}
		
		missCount = 0;
		
		while(true) {
			
			if(missCount >= missCountMax) {
				System.out.println("= 회원 가입 취소 =");
				return;
			}
			
			System.out.printf("이름 : ");
			name = sc.nextLine();
					
			if(name.trim().length() == 0) {
				System.out.println("= 이름을 입력해주세요 =");
				missCount++;
				continue;
			}else {
				break;
			}
		}
		
		int id = memberService.join(loginId, loginPw, name);
		System.out.printf("= %d번 회원으로 가입되셨습니다 =\n", id);
		
	}
}
