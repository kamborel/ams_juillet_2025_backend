package com.sip.ams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sip.ams.entities.Article;
import com.sip.ams.repositories.ArticleRepository;

@Service
public class ArticleServiceImp implements ArticleService{
	
	@Autowired
	ArticleRepository articleRepository;

	@Override
	public List<Article> getAllArticles() {
		// TODO Auto-generated method stub
		return (List <Article>)this.articleRepository.findAll();
	}

	@Override
	public Article saveArticle(Article article) {
		// TODO Auto-generated method stub
		return this.articleRepository.save(article);
	}

	@Override
	public Optional<Article> getArticleById(int id) {
		// TODO Auto-generated method stub
		return  this.articleRepository.findById(id);
	}

	@Override
	public void deleteArticleById(int id) {
		// TODO Auto-generated method stub
		this.articleRepository.deleteById(id);
	}

}
