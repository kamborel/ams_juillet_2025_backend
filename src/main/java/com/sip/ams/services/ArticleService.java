package com.sip.ams.services;

import java.util.List;
import java.util.Optional;

import com.sip.ams.entities.Article;
import com.sip.ams.dto.ArticleDTO;

public interface ArticleService {  //CRUD
	
	public List<Article> getAllArticles(); // SELECT  R(Read)

	public Article saveArticle(Article provider); // INSERT ou bien update C(Create)

	public Optional<Article> getArticleById(int id);  // SELECT

	public void deleteArticleById(int id); // DELETE

	public Article updateArticle(ArticleDTO articleModel); // Update d'un article
}
