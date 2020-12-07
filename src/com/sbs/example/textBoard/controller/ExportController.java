package com.sbs.example.textBoard.controller;

import com.sbs.example.textBoard.container.Container;
import com.sbs.example.textBoard.service.ExportService;

public class ExportController extends Controller{
	
	private ExportService exportService;
	
	public ExportController(){
		
		exportService = Container.exportService;
		
	}

	public void doCommand(String cmd) {
		
		if(cmd.equals("export html")) {
			doHtml();
		}
		
	}

	private void doHtml() {
		
		System.out.println("== HTML 생성을 시작합니다 ==");
		exportService.makeHtml();
		
	}
	
}
