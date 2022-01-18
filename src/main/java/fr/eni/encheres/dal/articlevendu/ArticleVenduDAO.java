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
	
	/**Méthodes utilisées dans la page AccueilConnecte
	 */
	List<ArticleVendu> selectByNomAndCat(String nomLike, Integer noCategorie) throws DALException;
	List<ArticleVendu> selectEncheresOuvertesByNomAndCat(String nomLike, Integer noCategorie) throws DALException;
	List<ArticleVendu> selectEncheresByAcheteurByNomAndCat(Integer noUtilisateur, String nomLike, Integer noCategorie) throws DALException;
	List<ArticleVendu> selectEncheresRemporteesByAcheteurByNomAndCat(Integer noUtilisateur, String nomLike, Integer noCategorie) throws DALException;
	List<ArticleVendu> selectVentesEnCoursByVendeurByNomAndCat(Integer noUtilisateur, String nomLike, Integer noCategorie) throws DALException;
	List<ArticleVendu> selectVentesNonDebuteesByVendeurByNomAndCat(Integer noUtilisateur, String nomLike, Integer noCategorie) throws DALException;
	List<ArticleVendu> selectVentesTermineesByVendeurByNomAndCat(Integer noUtilisateur, String nomLike, Integer noCategorie) throws DALException;
}
