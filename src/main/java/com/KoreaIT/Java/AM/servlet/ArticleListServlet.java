package com.KoreaIT.Java.AM.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.KoreaIT.Java.AM.config.Config;
import com.KoreaIT.Java.AM.util.DBUtil;
import com.KoreaIT.Java.AM.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");		

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
			conn = DriverManager.getConnection(Config.getDBUrl(),Config.getDBUser(),Config.getDBPassword());
			
			HttpSession session = request.getSession();
			
			boolean isLogined = false;
			int loginedMemberId = -1;
			Map<String , Object> memberRow = null;
			if(session.getAttribute("loginedMemberLoginId") != null) {
				isLogined =  true;
				loginedMemberId = (int)session.getAttribute("loginedMemberId");
				SecSql sql = SecSql.from("SELECT * FROM `member`");
				sql.append("WHERE id = ?", loginedMemberId);				
				memberRow = DBUtil.selectRow(conn, sql);
			}
						
			request.setAttribute("isLogined", isLogined);
			request.setAttribute("loginedMemberId", loginedMemberId);		
			request.setAttribute("memberRow", memberRow);	
			
			int page = 1;
			if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			int itemsInAPage = 10;
			int limitFrom = (page - 1) * itemsInAPage;
			int limitTake = itemsInAPage;
			
			SecSql sql = SecSql.from("SELECT A.*, M.name AS writer__name");
			sql.append("FROM article AS A INNER JOIN `member` AS M");
			sql.append("ON A.memberId = M.id");
			sql.append("ORDER BY A.id DESC");
			sql.append("LIMIT ?,?", limitFrom, limitTake);

			List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);
			sql = new SecSql();
			sql.append("SELECT COUNT(*) FROM article");
			int totalCount = DBUtil.selectRowIntValue(conn, sql);
			int totalPage = (int) Math.ceil(((double) totalCount / itemsInAPage));

			request.setAttribute("articleRows", articleRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("page", page);
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
