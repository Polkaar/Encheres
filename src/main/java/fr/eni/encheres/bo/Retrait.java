package fr.eni.encheres.bo;

public class Retrait {

	private ArticleVendu articleVendu;
	private String rue;
	private String codePostal;
	private String ville;
	
	public Retrait() {
	}

	public Retrait(ArticleVendu articleVendu, String rue, String codePostal, String ville) {
		super();
		this.articleVendu = articleVendu;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Retrait [articleVendu=");
		builder.append(articleVendu);
		builder.append(", rue=");
		builder.append(rue);
		builder.append(", codePostal=");
		builder.append(codePostal);
		builder.append(", ville=");
		builder.append(ville);
		builder.append("]");
		return builder.toString();
	}
}
