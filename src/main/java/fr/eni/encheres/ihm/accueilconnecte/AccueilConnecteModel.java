package fr.eni.encheres.ihm.accueilconnecte;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;

public class AccueilConnecteModel {

	ArticleVendu article = new ArticleVendu();
	List<ArticleVendu> lstArticles = new ArrayList<>();
	
	public void addLstArticles(ArticleVendu article) {
		lstArticles.add(article);
	}
	
	
	public AccueilConnecteModel() {
		super();
	}
	public AccueilConnecteModel(ArticleVendu article, List<ArticleVendu> lstArticles) {
		super();
		this.article = article;
		this.lstArticles = lstArticles;
	}
	public ArticleVendu getArticle() {
		return article;
	}
	public void setArticle(ArticleVendu article) {
		this.article = article;
	}
	public List<ArticleVendu> getLstArticles() {
		return lstArticles;
	}
	public void setLstArticles(List<ArticleVendu> lstArticles) {
		this.lstArticles = lstArticles;
	}
	@Override
	public String toString() {
		return "ArticleModel [article=" + article + ", lstArticles=" + lstArticles + "]";
	}
	
}
