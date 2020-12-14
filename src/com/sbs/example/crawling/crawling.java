package com.sbs.example.crawling;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

public class crawling {
	
	
	crawling(){
		
		
	}

	public static void run() {
		
		doWriteArticleTodayHumorToDb();
		
	}

	private static void doWriteArticleTodayHumorToDb() {

		Path path = Paths.get(System.getProperty("user.dir"), "driver/chromedriver.exe");
		
		System.setProperty("webdriver.chrome.driver", path.toString());
		
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");
		
		ChromeDriver driver = new ChromeDriver(options);
		
		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		
		driver.switchTo().window(tabs.get(0));
		driver.get("http://www.todayhumor.co.kr/board/list.php?table=bestofbest");
		
		List<WebElement> elements = driver.findElements(By.cssSelector(".table_list>tbody>.view"));
		
		MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "exam");
		
		for(WebElement element : elements) {			
			
			int num = Integer.parseInt(element.findElement(By.cssSelector(".view>.no>a")).getText().trim());
			String regDate = element.findElement(By.cssSelector(".view .date")).getText().trim();
			String updateDate = regDate;
			String title = element.findElement(By.cssSelector(".view>.subject>a")).getText().trim();
			String name = element.findElement(By.cssSelector(".view>.name>a")).getText().trim();
			int hit = Integer.parseInt(element.findElement(By.cssSelector(".view>.hits")).getText().trim());
			int recommend = Integer.parseInt(element.findElement(By.cssSelector(".view>.oknok")).getText().trim());
			
			System.out.println(num);
			System.out.println(regDate);
			System.out.println(updateDate);
			System.out.println(title);
			System.out.println(name);
			System.out.println(hit);
			System.out.println(recommend);

			SecSql sql = new SecSql();
			
			sql.append("INSERT INTO article");
			sql.append("SET regDate = ?", regDate.replaceAll("/", "-"));
			sql.append(", updateDate = ?", updateDate.replaceAll("/", "-"));
			sql.append(", title = ?", title);
			sql.append(", body = ?", name);
			sql.append(", hit = ?", hit);
			sql.append(", recommend = ?", recommend);
			sql.append(", memberId = ?", 2);
			sql.append(", boardId = ?", 1);
			
			MysqlUtil.insert(sql);
			
		}
		
		MysqlUtil.closeConnection();
		
	}

}
