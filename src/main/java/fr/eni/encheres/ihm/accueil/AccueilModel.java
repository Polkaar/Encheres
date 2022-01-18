package fr.eni.encheres.ihm.accueil;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;

public class AccueilModel {

	ArticleVendu article = new ArticleVendu();
	List<Categorie> lstCategories = new ArrayList<>();
	List<ArticleVendu> lstArticles = new ArrayList<>();
	
	public void addLstArticles(ArticleVendu article) {
		lstArticles.add(article);
	}
	
	public void addLstCategories(Categorie categorie) {
		lstCategories.add(categorie);
	}

	public AccueilModel() {
		super();
	}

	public AccueilModel(ArticleVendu article, List<Categorie> lstCategories, List<ArticleVendu> lstArticles) {
		super();
		this.article = article;
		this.lstCategories = lstCategories;
		this.lstArticles = lstArticles;
	}

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	public List<Categorie> getLstCategories() {
		return lstCategories;
	}

	public void setLstCategories(List<Categorie> lstCategories) {
		this.lstCategories = lstCategories;
	}

	public List<ArticleVendu> getLstArticles() {
		return lstArticles;
	}

	public void setLstArticles(List<ArticleVendu> lstArticles) {
		this.lstArticles = lstArticles;
	}

	@Override
	public String toString() {
		return "AccueilModel [article=" + article + ", lstCategories=" + lstCategories + ", lstArticles=" + lstArticles
				+ "]";
	}
	
	
	
}
