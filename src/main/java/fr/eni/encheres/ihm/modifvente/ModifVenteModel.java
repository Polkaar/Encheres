package fr.eni.encheres.ihm.modifvente;

import fr.eni.encheres.bo.ArticleVendu;

public class ModifVenteModel {
	
	private ArticleVendu articleVendu;
	
	public ModifVenteModel() {
	}

	public ModifVenteModel(ArticleVendu articleVendu) {
		super();
		this.articleVendu = articleVendu;
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	
}
