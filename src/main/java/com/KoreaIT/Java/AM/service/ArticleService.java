package com.KoreaIT.Java.AM.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.Java.AM.dao.ArticleDao;

public class ArticleService {
	private ArticleDao articleDao;
	public ArticleService(Connection conn) {
		articleDao = new ArticleDao(conn);
	}
	private int getItemsInAPage() {		
		return 10;
	}
	
	public List<Map<String, Object>> getForPrintArticleRows(int page) {
		int itemsInAPage = getItemsInAPage();
		int limitFrom = (page - 1) * itemsInAPage;
		
		List<Map<String, Object>> articleRows = articleDao.getArticleRows(limitFrom, itemsInAPage);
		
		return articleRows;
	}
	public int getForPrintListTotalPage() {
		int itemsInAPage = getItemsInAPage();
		int totalCount = articleDao.getTotalCount();
		
		int totalPage = (int) Math.ceil(((double) totalCount / itemsInAPage));
		
		return totalPage;
	}

}
