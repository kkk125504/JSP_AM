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

@WebServlet("/member/logout")
public class MemberLogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		session.removeAttribute("loginedMemberId");
		session.removeAttribute("loginedMemberLoginId");
		
		response.getWriter()
		.append("<script>alert('로그아웃 되었습니다.'); location.replace('../home/main');</script>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}