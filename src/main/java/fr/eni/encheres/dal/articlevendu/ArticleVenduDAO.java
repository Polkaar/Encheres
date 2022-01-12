package fr.eni.encheres.dal.articlevendu;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.dal.DALException;

public interface ArticleVenduDAO {

	void insert(ArticleVendu articleVendu) throws DALException;
	void delete(ArticleVendu articleVendu) throws DALException;
	void update(ArticleVendu articleVendu, int nouveauPrix) throws DALException;
	List<ArticleVendu> selectAll() throws DALException;
	List<ArticleVendu> selectArticleByCategorie(int noCategorie) throws DALException;
	ArticleVendu selectById(Integer noArticle) throws DALException;
}
