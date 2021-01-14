package com.sbs.example.textBoard.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbs.example.textBoard.dto.Article;

public class Util {
	
	public static void writeFileContents(String filePath, String content) {
		
		BufferedOutputStream bs = null;
		
		try {
			
			bs = new BufferedOutputStream(new FileOutputStream(filePath));
			bs.write(content.getBytes());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
		
	public static boolean copy(String sourcePath, String targetPath) {
		
		Path source = Paths.get(sourcePath);
		
		Path target = Paths.get(targetPath);
		
		if(!Files.exists(target.getParent())) {
			
			try {
				Files.createDirectories(target.getParent());
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		try {
			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
		
		return true;
		
	}

	public static String getFileContents(String filePath) {
		
		String rs = null;
		
		FileInputStream fileStream = null;
		
		try {
			
			fileStream = new FileInputStream(filePath);
			byte[] readBuffer = new byte[fileStream.available()];
			
			while(fileStream.read(readBuffer) != -1) {			
			}
			
			rs = new String(readBuffer);
			
			fileStream.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
						
		return rs;
	}

	public static void mkdirs(String path) {
		
		File dir = new File(path);
		
		if(dir.exists() == false) {
			dir.mkdirs();
		}
	}

	public static boolean rmdir(String path) {
		return rmdir(new File(path));
	}

	public static boolean rmdir(File dirToBeDeleted) {
		File[] allContents = dirToBeDeleted.listFiles();
		if (allContents != null) {
			for (File file : allContents) {
				rmdir(file);
			}
		}

		return dirToBeDeleted.delete();
	}
	
	public static String callApi(String urlStr, String... args) {
		// URL 구성 시작
		StringBuilder queryString = new StringBuilder();

		for (String param : args) {
			if (queryString.length() == 0) {
				queryString.append("?");
			} else {
				queryString.append("&");
			}

			queryString.append(param);
		}

		urlStr += queryString.toString();
		// URL 구성 끝

		// 연결생성 시작
		HttpURLConnection con = null;

		try {
			URL url = new URL(urlStr);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(10000); // 최대통신시간 제한
			con.setReadTimeout(10000); // 최대데이터읽기시간 제한
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (ProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		// 연결생성 끝

		// 연결을 통해서 데이터 가져오기 시작
		StringBuffer content = null;
		try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
			String inputLine;
			content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 연결을 통해서 데이터 가져오기 끝

		return content.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static Object callApiResponseTo(Class cls, String urlStr, String... args) {
		String jsonString = callApi(urlStr, args);

		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.readValue(jsonString, cls);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getJsonText(Object obj) {
		
		ObjectMapper mapper = new ObjectMapper();
		String rs = "";
		try {
			rs = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return rs;
		
	}

	public static String getNowDateStr() {
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");;
		String nowDate = format.format(new Date()); 
		return nowDate;
	}

}
