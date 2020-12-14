package com.sbs.example.textBoard.service;

import com.sbs.example.textBoard.container.Container;
import com.sbs.example.textBoard.dao.MemberDao;
import com.sbs.example.textBoard.dto.Member;

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService() {
		
		memberDao = Container.memberDao;
		
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberById(loginId);
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);
	}

	public Member getMemberById(int loginedId) {
		return memberDao.getMemberById(loginedId);
	}

	public String getMemberNameById(int memberId) {
		return memberDao.getMemberNameById(memberId);
	}
}
