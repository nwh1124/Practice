package com.sbs.example.textBoard.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;
import com.sbs.example.textBoard.dto.Article;
import com.sbs.example.textBoard.dto.Recommend;
import com.sbs.example.textBoard.dto.Reply;
import com.sbs.example.textBoard.dto.Tag;

public class ArticleDao {

	public int add(String title, String body, int loginedId, int selectedBoardId) {
		
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", body = ?", body);
		sql.append(", hitsCount = 0");
		sql.append(", recommend = 0");
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

	public List<Recommend> getReco(int inputedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM recommend");
		sql.append("WHERE articleId = ?", inputedId);
		
		List<Map<String, Object>> listMap = MysqlUtil.selectRows(sql);
		
		List<Recommend> recos = new ArrayList<>();
		
		for(Map<String, Object> map : listMap) {
			recos.add(new Recommend(map));
		}
		
		return recos;
	}

	public void recommend(int inputedId, int loginedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO recommend");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", recommend = true");
		sql.append(", articleId = ?", inputedId);
		sql.append(", memberId = ?", loginedId);
	
		MysqlUtil.insert(sql);
		sql = new SecSql();
		
		sql.append("UPDATE article");
		sql.append("SET recommend = recommend + 1");
		sql.append("WHERE id = ?", inputedId);
		
		MysqlUtil.update(sql);			
		
	}

	public void cancelrecommend(int inputedId, int loginedId) {
		
		SecSql sql = new SecSql();
		
		sql.append("UPDATE recommend");
		sql.append("SET updateDate = NOW()");
		sql.append(", recommend = false");
		sql.append("WHERE articleId = ?", inputedId);
		sql.append("AND memberId = ?", loginedId);
	
		MysqlUtil.update(sql);
		sql = new SecSql();
		
		sql.append("UPDATE article");
		sql.append("SET recommend = recommend - 1");
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

	public int modify(Map<String, Object> args) {
		SecSql sql = new SecSql();

		int id = (int) args.get("id");
		String title = args.get("title") != null ? (String) args.get("title") : null;
		String body = args.get("body") != null ? (String) args.get("body") : null;
		int likesCount = args.get("likesCount") != null ? (int) args.get("likesCount") : -1;
		int commentsCount = args.get("commentsCount") != null ? (int) args.get("commentsCount") : -1;

		sql.append("UPDATE article");
		sql.append(" SET updateDate = NOW()");

		if (title != null) {
			sql.append(", title = ?", title);
		}

		if (body != null) {
			sql.append(", body = ?", body);
		}

		if (likesCount != -1) {
			sql.append(", likesCount = ?", likesCount);
		}

		if (commentsCount != -1) {
			sql.append(", commentsCount = ?", commentsCount);
		}

		sql.append("WHERE id = ?", id);

		return MysqlUtil.update(sql);
	}

	public List<Article> getArticlesWithMemberName() {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		
		List<Map<String, Object>> listMap = MysqlUtil.selectRows(sql);
		
		List<Article> articles = new ArrayList<>();
		
		for(Map<String, Object> map : listMap) {
			articles.add(new Article(map));
		}
		
		return articles;
		
	}
	
	public int updatePageHits() {
		SecSql sql = new SecSql();
		sql.append("UPDATE article AS AR");
		sql.append("INNER JOIN (");
		sql.append("    SELECT CAST(REPLACE(REPLACE(GA4_PP.pagePathWoQueryStr, '/article_detail_', ''), '.html', '') AS UNSIGNED) AS articleId,");
		sql.append("    hit");
		sql.append("    FROM (");
		sql.append("        SELECT");
		sql.append("        IF(");
		sql.append("            INSTR(GA4_PP.pagePath, '?') = 0,");
		sql.append("            GA4_PP.pagePath,");
		sql.append("            SUBSTR(GA4_PP.pagePath, 1, INSTR(GA4_PP.pagePath, '?') - 1)");
		sql.append("        ) AS pagePathWoQueryStr,");
		sql.append("        SUM(GA4_PP.hitsCount) AS hit");
		sql.append("        FROM ga4DataPagePath AS GA4_PP");
		sql.append("        WHERE GA4_PP.pagePath LIKE '/article_detail_%.html%'");
		sql.append("        GROUP BY pagePathWoQueryStr");
		sql.append("    ) AS GA4_PP");
		sql.append(") AS GA4_PP");
		sql.append("ON AR.id = GA4_PP.articleId");
		sql.append("SET AR.hitsCount = GA4_PP.hit");
		
		return MysqlUtil.update(sql);
	}

	public String getTagsByRelTypeCodeAndRelId(String relTypeCode, int relId) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT body");
		sql.append("FROM tag");
		sql.append("WHERE relTypeCode = ?", relTypeCode);
		sql.append("AND relId = ?", relId);
		
		return MysqlUtil.selectRowStringValue(sql);
		
	}

	public List<Tag> getTags() {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM tag");
		
		List<Map<String, Object>> listMap = MysqlUtil.selectRows(sql);
		
		List<Tag> tags = new ArrayList<>();
		
		for(Map<String, Object> map : listMap) {
			tags.add(new Tag(map));
		}
		
		return tags;
	}
	
}
