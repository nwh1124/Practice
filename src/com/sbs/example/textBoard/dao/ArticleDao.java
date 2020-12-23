package com.sbs.example.textBoard.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;
import com.sbs.example.textBoard.dto.Article;
import com.sbs.example.textBoard.dto.Recommand;
import com.sbs.example.textBoard.dto.Reply;

public class ArticleDao {

	public int add(String title, String body, int loginedId, int selectedBoardId) {
		
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", body = ?", body);
		sql.append(", hit = 0");
		sql.append(", recommand = 0");
		sql.append(", memberId = ?", loginedId);
		sql.append(", boardId = ?", selectedBoardId);
		
		return MysqlUtil.insert(sql);
	}

	public List<Article> getArticles() {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM article");
		
		List<Map<String, Object>> listMap = MysqlUtil.selectRows(sql);
		
		List<Article> articles = new ArrayList<>();
		
		for(Map<String, Object> map : listMap) {
			articles.add(new Article(map));
		}
		
		return articles;
		
	}

	public Article getArticle(int inputedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id = ?", inputedId);
		
		Map<String, Object> map = MysqlUtil.selectRow(sql);
		
		if(map.size() == 0) {
			return null;
		}
		
		Article article = new Article(map);
		
		
		return article;
	}

	public List<Article> getArticleByBoardId(int inputedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE boardId = ?", inputedId);
		
		List<Map<String, Object>> listMap = MysqlUtil.selectRows(sql);
		
		List<Article> articles = new ArrayList<>();
		
		for(Map<String, Object> map : listMap) {
			articles.add(new Article(map));
		}
		
		return articles;
	}

	public List<Article> getArticles(String boardCode) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT id");
		sql.append("FROM board");
		sql.append("WHERE code = ?", boardCode);
		
		int boardId = MysqlUtil.selectRowIntValue(sql);
		
		sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE boardId = ?", boardId);
		
		List<Map<String, Object>> listMap = MysqlUtil.selectRows(sql);
		
		List<Article> articles = new ArrayList<>();
		
		for(Map<String, Object> map : listMap) {
			articles.add(new Article(map));
		}
		
		return articles;
	}

	public void delete(int inputedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?", inputedId);
		
		MysqlUtil.delete(sql);
		
	}

	public void modify(String modTitle, String modBody, int inputedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW()");
		sql.append(", title = ?", modTitle);
		sql.append(", body = ?", modBody);
		sql.append("WHERE id = ?", inputedId);
		
		MysqlUtil.update(sql);
		
	}

	public void doHitPlus(int inputedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("UPDATE article");
		sql.append("SET hit = hit + 1");
		sql.append("WHERE id = ?", inputedId);
		
		MysqlUtil.update(sql);
		
	}

	public List<Reply> getReplys() {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM reply");
		
		List<Map<String, Object>> listMap = MysqlUtil.selectRows(sql);
		
		List<Reply> replys = new ArrayList<>();
		
		for(Map<String, Object> map : listMap) {
			replys.add(new Reply(map));
		}		
		
		return replys;
	}

	public List<Recommand> getReco(int inputedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM recommand");
		sql.append("WHERE articleId = ?", inputedId);
		
		List<Map<String, Object>> listMap = MysqlUtil.selectRows(sql);
		
		List<Recommand> recos = new ArrayList<>();
		
		for(Map<String, Object> map : listMap) {
			recos.add(new Recommand(map));
		}
		
		return recos;
	}

	public void recommand(int inputedId, int loginedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO recommand");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", recommand = true");
		sql.append(", articleId = ?", inputedId);
		sql.append(", memberId = ?", loginedId);
	
		MysqlUtil.insert(sql);
		sql = new SecSql();
		
		sql.append("UPDATE article");
		sql.append("SET recommand = recommand + 1");
		sql.append("WHERE id = ?", inputedId);
		
		MysqlUtil.update(sql);			
		
	}

	public void cancelRecommand(int inputedId, int loginedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("UPDATE recommand");
		sql.append("SET updateDate = NOW()");
		sql.append(", recommand = false");
		sql.append("WHERE articleId = ?", inputedId);
		sql.append("AND memberId = ?", loginedId);
	
		MysqlUtil.update(sql);
		sql = new SecSql();
		
		sql.append("UPDATE article");
		sql.append("SET recommand = recommand - 1");
		sql.append("WHERE id = ?", inputedId);
		
		MysqlUtil.update(sql);		
		
	}

	public void writeReply(String reply, int inputedId, int loginedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO reply");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", body = ?", reply);
		sql.append(", articleId = ?", inputedId);
		sql.append(", memberId = ?", loginedId);
		
		MysqlUtil.update(sql);
		
	}

	public List<Reply> getReplysByMemberId(int loginedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM reply");
		sql.append("WHERE memberId = ?", loginedId);
		
		List<Map<String, Object>> listMap = MysqlUtil.selectRows(sql);	
		
		List<Reply> replys = new ArrayList<>();
		
		for(Map<String, Object> map : listMap) {
			replys.add(new Reply(map));
		}
		
		return replys;
		
	}

	public int deleteReply(String deleteReplyId) {
		
		SecSql sql = new SecSql();
		
		sql.append("DELETE FROM reply");
		sql.append("WHERE id = ?", deleteReplyId);
		
		return MysqlUtil.delete(sql);
		
	}

	public void modifyReply(String modReplyId, String modReplyBody) {
		
		SecSql sql = new SecSql();
		
		sql.append("UPDATE reply");
		sql.append("SET updateDate = NOW()");
		sql.append(", body = ?", modReplyBody);
		sql.append("WHERE id = ?", modReplyId);
		
		MysqlUtil.update(sql);
		
	}

	public List<Article> getLatestArticles() {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("ORDER BY regDate DESC");
		sql.append("limit 3");
		
		List<Map<String, Object>> listMap = MysqlUtil.selectRows(sql);	
		
		List<Article> articles = new ArrayList<>();
		
		for(Map<String, Object> map : listMap) {
			articles.add(new Article(map));
		}
		
		return articles;
	}

}
