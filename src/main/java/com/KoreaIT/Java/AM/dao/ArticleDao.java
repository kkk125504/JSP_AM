package com.KoreaIT.Java.AM.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.Java.AM.util.DBUtil;
import com.KoreaIT.Java.AM.util.SecSql;

public class ArticleDao {
	private Connection conn;
	
	public ArticleDao(Connection conn) {
		this.conn = conn;
	}
	
	public List<Map<String, Object>> getArticleRows(int limitFrom, int itemsInAPage) {
		
		SecSql sql = SecSql.from("SELECT A.*, M.name AS writer__name");
		sql.append("FROM article AS A INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("ORDER BY A.id DESC");
		sql.append("LIMIT ?,?", limitFrom, itemsInAPage);
		List<Map<String,Object>> articleRows = DBUtil.selectRows(conn, sql);
		return articleRows;
	}
	public int getTotalCount() {
		SecSql sql = SecSql.from("SELECT COUNT(*) FROM article");		
		return DBUtil.selectRowIntValue(conn, sql);		 
	}

}
