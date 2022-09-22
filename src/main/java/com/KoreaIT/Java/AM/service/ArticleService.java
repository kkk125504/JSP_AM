package com.KoreaIT.Java.AM.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.Java.AM.dao.ArticleDao;
import com.KoreaIT.Java.AM.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;
	public ArticleService(Connection conn) {
		articleDao = new ArticleDao(conn);
	}
	private int getItemsInAPage() {		
		return 10;
	}
	
	public List<Article> getForPrintArticles(int page) {
		int itemsInAPage = getItemsInAPage();
		int limitFrom = (page - 1) * itemsInAPage;
		
		List<Article> articles = articleDao.getArticles(limitFrom, itemsInAPage);
		
		return articles;
	}
	public int getForPrintListTotalPage() {
		int itemsInAPage = getItemsInAPage();
		int totalCount = articleDao.getTotalCount();
		
		int totalPage = (int) Math.ceil(((double) totalCount / itemsInAPage));
		
		return totalPage;
	}

}
