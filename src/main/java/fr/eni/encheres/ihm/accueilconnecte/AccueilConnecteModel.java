package fr.eni.encheres.ihm.accueilconnecte;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;

public class AccueilConnecteModel {

	ArticleVendu article = new ArticleVendu();
	List<ArticleVendu> lstArticles = new ArrayList<>();
	List<Categorie> lstCategories = new ArrayList<>();
	
	public void addLstArticles(ArticleVendu article) {
		lstArticles.add(article);
	}

	public void addLstCategories(Categorie categorie) {
		lstCategories.add(categorie);
	}
	
	public AccueilConnecteModel() {
		super();
	}

	public AccueilConnecteModel(ArticleVendu article, List<ArticleVendu> lstArticles, List<Categorie> lstCategories) {
		super();
		this.article = article;
		this.lstArticles = lstArticles;
		this.lstCategories = lstCategories;
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

	public List<Categorie> getLstCategories() {
		return lstCategories;
	}

	public void setLstCategories(List<Categorie> lstCategories) {
		this.lstCategories = lstCategories;
	}

	@Override
	public String toString() {
		return "AccueilConnecteModel [article=" + article + ", lstArticles=" + lstArticles + ", lstCategories="
				+ lstCategories + "]";
	}
	

	
}
