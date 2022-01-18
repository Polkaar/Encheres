package fr.eni.encheres.ihm.accueilconnecte;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;

public class AccueilConnecteModel {

	ArticleVendu article = new ArticleVendu();
	List<Categorie> lstCategories = new ArrayList<>();
	List<List<ArticleVendu>> lstListesArticles = new ArrayList<List<ArticleVendu>>();

	public void addLstCategories(Categorie categorie) {
		lstCategories.add(categorie);
	}

	public void addLstListesArticles (List<ArticleVendu> lstArticles) {
		lstListesArticles.add(lstArticles);
	}

	public AccueilConnecteModel() {
		super();
	}

	public AccueilConnecteModel(ArticleVendu article, List<Categorie> lstCategories,
			List<List<ArticleVendu>> lstListesArticles) {
		super();
		this.article = article;
		this.lstCategories = lstCategories;
		this.lstListesArticles = lstListesArticles;
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

	public List<List<ArticleVendu>> getLstListesArticles() {
		return lstListesArticles;
	}

	public void setLstListesArticles(List<List<ArticleVendu>> lstListesArticles) {
		this.lstListesArticles = lstListesArticles;
	}

	@Override
	public String toString() {
		return "AccueilConnecteModel [article=" + article + ", lstCategories=" + lstCategories + ", lstListesArticles="
				+ lstListesArticles + "]";
	}
	
	
	
}
