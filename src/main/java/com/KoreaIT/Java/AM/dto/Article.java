package com.KoreaIT.Java.AM.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class Article {
	public int id;
	public LocalDateTime regDate;
	public String title;
	public String body;
	public int memberId;
	public String writer__name;
	
	public Article(Map<String,Object> articleRow){
		this.id = (int)articleRow.get("id");
		this.regDate = (LocalDateTime)articleRow.get("regDate");
		this.title = (String)articleRow.get("title");
		this.body = (String)articleRow.get("body");
		this.memberId = (int)articleRow.get("memberId");
		
		if(articleRow.get("writer__name")!= null) {			
			this.writer__name = (String) articleRow.get("writer__name");
		}
	}
}
