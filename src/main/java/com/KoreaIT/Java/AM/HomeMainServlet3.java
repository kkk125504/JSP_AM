package com.KoreaIT.Java.AM;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home/printDan")
public class HomeMainServlet3 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		String inputedDan = request.getParameter("dan");
		if (inputedDan == null) {
			inputedDan = "1";
		}
		
		String inputedLimit = request.getParameter("limit");		
		
		if (inputedLimit == null) {
			inputedLimit = "9";
		}
		
		String inputedColor = request.getParameter("color");	
		
		if (inputedColor == null) {
			inputedColor = "black";
		}
		
		int dan = Integer.parseInt(inputedDan);
		int limit = Integer.parseInt(inputedLimit);

		response.getWriter().append(String.format("<div style=\"color:%s;\">%d단</div>", inputedColor, dan));

		for (int i = 1; i <= limit; i++) {
			response.getWriter().append(String.format("<div style=\"color:%s\">%d * %d = %d</div>",inputedColor, dan, i, dan * i));
		}
	
	}

}
