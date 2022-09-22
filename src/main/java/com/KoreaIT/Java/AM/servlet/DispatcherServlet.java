package com.KoreaIT.Java.AM.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.KoreaIT.Java.AM.config.Config;
import com.KoreaIT.Java.AM.controller.ArticleController;
import com.KoreaIT.Java.AM.exception.SQLErrorException;
import com.KoreaIT.Java.AM.util.DBUtil;
import com.KoreaIT.Java.AM.util.SecSql;

@WebServlet("/s/*")
public class DispatcherServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		String requestUri = request.getRequestURI();
		String[] requestUriBits = requestUri.split("/");		
		if(requestUriBits.length < 5) {
			response.getWriter().append("올바른 요청이 아닙니다.");
			return;
		}
		
		Connection conn = null;

		String driverName = Config.getDBDriverClassName();

		try {
			Class.forName(driverName);

		} catch (ClassNotFoundException e) {
			System.out.println("예외 : 클래스가 없습니다.");
			System.out.println("프로그램을 종료합니다.");
			return;
		}

		try {
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUser(), Config.getDBPassword());

			HttpSession session = request.getSession();

			boolean isLogined = false;
			int loginedMemberId = -1;
			Map<String, Object> memberRow = null;

			if (session.getAttribute("loginedMemberLoginId") != null) {
				isLogined = true;
				loginedMemberId = (int) session.getAttribute("loginedMemberId");

				SecSql sql = SecSql.from("SELECT * FROM `member`");
				sql.append("WHERE id = ?", loginedMemberId);
				memberRow = DBUtil.selectRow(conn, sql);
			}

			request.setAttribute("isLogined", isLogined);
			request.setAttribute("loginedMemberId", loginedMemberId);
			request.setAttribute("memberRow", memberRow);
			
			
			String controllerName = requestUriBits[3];
			String actionMethodName = requestUriBits[4];
			if(controllerName.equals("article")) {
				ArticleController articleController = new ArticleController(request,response,conn);
				if(actionMethodName.equals("list")) {
					articleController.showList();
				}
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SQLErrorException e) {
			e.getOrigin().printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
