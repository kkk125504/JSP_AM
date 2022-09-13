package com.KoreaIT.Java.AM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home/main3")
public class HomeMainServlet3 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().append("9단<br>");
		
		int dan = 9;
		for (int i = 1; i < 10; i++) {
			response.getWriter().append(String.format("%d * %d = %d<br>", dan, i, dan * i));
		}
	}

}
