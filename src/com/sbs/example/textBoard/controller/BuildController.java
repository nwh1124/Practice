package com.sbs.example.textBoard.controller;

import com.sbs.example.textBoard.container.Container;
import com.sbs.example.textBoard.service.BuildService;

public class BuildController extends Controller{
	
	private BuildService buildService;
	
	public BuildController(){
		
		buildService = Container.exportService;
		
	}

	public void doCommand(String cmd) {
		
		if(cmd.equals("build site")) {
			doHtml();
		}
		
	}

	private void doHtml() {
		
		System.out.println("== HTML 생성을 시작합니다 ==");
		buildService.buildSite_article();
		
	}
	
}
