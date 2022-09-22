package com.KoreaIT.Java.AM.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.Java.AM.dto.Article;
import com.KoreaIT.Java.AM.service.ArticleService;
import com.KoreaIT.Java.AM.util.DBUtil;
import com.KoreaIT.Java.AM.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArticleController {
	HttpServletRequest request;
	HttpServletResponse response;
	ArticleService articleService;
	
	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.request = request;
		this.response = response;
		articleService = new ArticleService(conn);
	}
	public void showList() throws ServletException, IOException {
		int page = 1;
		if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
			page = Integer.parseInt(request.getParameter("page"));
		}					
		List<Article> articles = articleService.getForPrintArticles(page);
	
		int totalPage = articleService.getForPrintListTotalPage();
		
		request.setAttribute("articles", articles);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);		
	}

}
