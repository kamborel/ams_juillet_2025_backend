package com.sip.ams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sip.ams.entities.Article;
import com.sip.ams.entities.Provider;
import com.sip.ams.dto.ArticleDTO;
import com.sip.ams.repositories.ArticleRepository;
import com.sip.ams.repositories.ProviderRepository;

@Service
public class ArticleServiceImp implements ArticleService{
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	ProviderRepository providerRepository;
	//ProviderService providerService;

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

	@Override
	public Article updateArticle(ArticleDTO articleModel) {
		
		int idArticle = articleModel.getId();
		int idProvider = articleModel.getIdProvider();
				
		Optional<Article> optArticle = this.getArticleById(idArticle);
		Optional<Provider> optProvider = this.providerRepository.findById(idProvider);
		
		
			Article savedArticle = optArticle.get();
			Provider savedProvider = optProvider.get();
			
			savedArticle.setLibelle(articleModel.getLibelle());
			savedArticle.setPrice(articleModel.getPrice());
			savedArticle.setProvider(savedProvider);
			
			return this.saveArticle(savedArticle);   
		
	}
	

}
