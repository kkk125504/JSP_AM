package com.KoreaIT.Java.AM.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.KoreaIT.Java.AM.config.Config;
import com.KoreaIT.Java.AM.exception.SQLErrorException;
import com.KoreaIT.Java.AM.util.DBUtil;
import com.KoreaIT.Java.AM.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/article/doDelete")
public class ArticleDoDeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginedMemberLoginId") == null) {
			response.getWriter()
			.append("<script>alert('로그인 후 이용해주세요.'); location.replace('../member/login');</script>");
			return;
		}
				
		int loginedMemberId = (int)session.getAttribute("loginedMemberId");
		// DB 연결
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

			int id = Integer.parseInt(request.getParameter("id"));

			SecSql sql = SecSql.from("SELECT memberId");
			sql.append("FROM article");
			sql.append("WHERE id = ?", id);
			
			int MemberIdByArticle = DBUtil.selectRowIntValue(conn, sql);
			
			if(loginedMemberId != MemberIdByArticle ) {
				response.getWriter()
				.append(String.format("<script>alert('%d번 글에 대한 삭제 권한이 없습니다.'); location.replace('list');</script>", id));
				return;
			}
			
			sql = SecSql.from("DELETE");
			sql.append("FROM article");
			sql.append("WHERE id = ?", id);

			DBUtil.delete(conn, sql);

			response.getWriter()
					.append(String.format("<script>alert('%d번 글이 삭제 되었습니다.'); location.replace('list');</script>", id));

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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}