package fr.eni.encheres.dal.articlevendu;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.categorie.CategorieDAO;
import fr.eni.encheres.dal.categorie.CategorieDAOFact;
import fr.eni.encheres.dal.retrait.RetraitDAO;
import fr.eni.encheres.dal.retrait.RetraitDAOFact;
import fr.eni.encheres.dal.utilisateur.UtilisateurDAO;
import fr.eni.encheres.dal.utilisateur.UtilisateurDAOFact;

public class ArticleVenduDAOJdbc implements ArticleVenduDAO {
	
	UtilisateurDAO daoUtilisateur = UtilisateurDAOFact.getUtilisateurDAO();
	CategorieDAO daoCategorie = CategorieDAOFact.getCategorieDAO();
	RetraitDAO daoRetrait = RetraitDAOFact.getRetraitDAO();
	
	private final String INSERT = "INSERT  INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie, no_retrait) VALUES(?, ?, ?, ?, ?, ?, ?, ?)"; 
	private final String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";
	private final String SELECT_BY_ID = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS WHERE no_article=?";
	private final String UPDATE = "UPDATE ARTICLES_VENDUS SET prix_vente = ? WHERE no_article = ?";
	private final String SELECT_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS";
	private final String SELECT_BY_CATEGORIE = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS WHERE no_categorie=?";
	
	private final String SELECT_BY_NOM_AND_CAT = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ " prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS"
			+ " WHERE nom_article LIKE ? AND no_categorie LIKE ?"
			+ " ORDER BY date_fin_encheres DESC";
	private final String SELECT_ENCHERES_OUVERTES_BY_NOM_AND_CAT = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ " prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS"
			+ " WHERE date_debut_encheres <= GETDATE() AND GETDATE() < date_fin_encheres"
			+ " AND nom_article LIKE ? AND no_categorie LIKE ?"
			+ " ORDER BY date_fin_encheres DESC";
	private final String SELECT_MES_ENCHERES_BY_NOM_AND_CAT = "SELECT a.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,"
			+ " prix_vente, a.no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS AS a"
			+ " inner join ENCHERES AS e ON a.no_article = e.no_article"
			+ " WHERE a.date_debut_encheres <= GETDATE() AND GETDATE() < a.date_fin_encheres AND e.no_utilisateur = '?'"
			+ " AND nom_article LIKE ? AND no_categorie LIKE ?"
			+ " ORDER BY date_fin_encheres DESC";
	private final String SELECT_MES_ENCHERES_REMPORTEES_BY_NOM_AND_CAT = "SELECT a.no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ " prix_initial, prix_vente, a.no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS AS a"
			+ " inner join ENCHERES AS e ON a.no_article = e.no_article"
			+ " WHERE GETDATE() >= a.date_fin_encheres AND a.prix_vente = e.montant_enchere AND e.no_utilisateur = '?'"
			+ " AND nom_article LIKE ? AND no_categorie LIKE ?"
			+ " ORDER BY date_fin_encheres DESC";
	private final String SELECT_MES_VENTES_EN_COURS_BY_NOM_AND_CAT ="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ " prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS"
			+ " WHERE date_debut_encheres <= GETDATE() AND GETDATE() < date_fin_encheres AND no_utilisateur = ?"
			+ " AND nom_article LIKE ? AND no_categorie LIKE ?"
			+ " ORDER BY date_fin_encheres DESC";
	private final String SELECT_MES_VENTES_NON_DEBUTEES_BY_NOM_AND_CAT ="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ " prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS"
			+ " WHERE GETDATE() < date_debut_encheres AND no_utilisateur = ?"
			+ " AND nom_article LIKE ? AND no_categorie LIKE ?"
			+ " ORDER BY date_debut_encheres DESC";
	private final String SELECT_MES_VENTES_TERMINEES_BY_NOM_AND_CAT ="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ " prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS"
			+ " WHERE date_fin_encheres <= GETDATE() AND no_utilisateur = ?"
			+ " AND nom_article LIKE ? AND no_categorie LIKE ?"
			+ " ORDER BY date_fin_encheres DESC";
	

	@Override
	public void insert(ArticleVendu articleVendu) throws DALException{
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, articleVendu.getNomArticle());
			pStmt.setString(2, articleVendu.getDescription());
			pStmt.setDate(3, Date.valueOf(articleVendu.getDateDebutEncheres()));
			pStmt.setDate(4, Date.valueOf(articleVendu.getDateFinEncheres()));
			pStmt.setInt(5, articleVendu.getPrixInitial());
			pStmt.setInt(6, articleVendu.getUtilisateur().getNoUtilisateur());
			pStmt.setInt(7, articleVendu.getCategorie().getNoCategorie());
			pStmt.setInt(8, articleVendu.getRetrait().getNoRetrait());
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				int noArticle = rs.getInt(1);
				articleVendu.setNoArticle(noArticle);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
	}

	@Override
	public void delete(ArticleVendu articleVendu) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(DELETE);
			pStmt.setInt(1, articleVendu.getNoArticle());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
	}
	
	@Override
	public ArticleVendu selectById(Integer noArticle) throws DALException {
		ArticleVendu articleVendu = new ArticleVendu();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID);
			pStmt.setInt(1, noArticle);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				map(articleVendu, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		return articleVendu;
	}

	@Override
	public void update(ArticleVendu articleVendu, int nouveauPrix) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
			pStmt.setInt(1, nouveauPrix);
			pStmt.setInt(2, articleVendu.getNoArticle());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
	}

	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		List<ArticleVendu> lstArticlesVendus = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				map(articleVendu, rs);
				lstArticlesVendus.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
		return lstArticlesVendus;
	}

	@Override
	public List<ArticleVendu> selectArticleByCategorie(int noCategorie) throws DALException {
		List<ArticleVendu> lstArticleByCategorie = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_CATEGORIE);
			pStmt.setInt(1, noCategorie);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				map(articleVendu, rs);
				lstArticleByCategorie.add(articleVendu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		
		return lstArticleByCategorie;
	}


	@Override
	public List<ArticleVendu> selectByNomAndCat(String nomLike, Integer noCategorie) throws DALException {
		List<ArticleVendu> lstArticlesVendus = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_NOM_AND_CAT);
			pStmt.setString(1, "%"+nomLike+"%");
			if(noCategorie == 0) {
				pStmt.setString(2, "%%");
			}else {
				pStmt.setString(2, "%"+noCategorie+"%");
			}
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				map(articleVendu, rs);
				lstArticlesVendus.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
		return lstArticlesVendus;
	}

	@Override
	public List<ArticleVendu> selectEncheresOuvertesByNomAndCat(String nomLike, Integer noCategorie) throws DALException {
		List<ArticleVendu> lstArticlesVendus = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ENCHERES_OUVERTES_BY_NOM_AND_CAT);
			pStmt.setString(1, "%"+nomLike+"%");
			if(noCategorie == 0) {
				pStmt.setString(2, "%%");
			}else {
				pStmt.setString(2, "%"+noCategorie+"%");
			}
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				map(articleVendu, rs);
				lstArticlesVendus.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
		return lstArticlesVendus;
	}

	@Override
	public List<ArticleVendu> selectEncheresByAcheteurByNomAndCat(Integer noUtilisateur, String nomLike, Integer noCategorie) throws DALException {
		List<ArticleVendu> lstArticlesVendus = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_MES_ENCHERES_BY_NOM_AND_CAT);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+nomLike+"%");
			if(noCategorie == 0) {
				pStmt.setString(3, "%%");
			}else {
				pStmt.setString(3, "%"+noCategorie+"%");
			}
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				map(articleVendu, rs);
				lstArticlesVendus.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
		return lstArticlesVendus;
	}

	@Override
	public List<ArticleVendu> selectEncheresRemporteesByAcheteurByNomAndCat(Integer noUtilisateur, String nomLike, Integer noCategorie) throws DALException {
		List<ArticleVendu> lstArticlesVendus = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_MES_ENCHERES_REMPORTEES_BY_NOM_AND_CAT);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+nomLike+"%");
			if(noCategorie == 0) {
				pStmt.setString(3, "%%");
			}else {
				pStmt.setString(3, "%"+noCategorie+"%");
			}
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				map(articleVendu, rs);
				lstArticlesVendus.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
		return lstArticlesVendus;
	}

	@Override
	public List<ArticleVendu> selectVentesEnCoursByVendeurByNomAndCat(Integer noUtilisateur, String nomLike, Integer noCategorie) throws DALException {
		List<ArticleVendu> lstArticlesVendus = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_MES_VENTES_EN_COURS_BY_NOM_AND_CAT);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+nomLike+"%");
			if(noCategorie == 0) {
				pStmt.setString(3, "%%");
			}else {
				pStmt.setString(3, "%"+noCategorie+"%");
			}
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				map(articleVendu, rs);
				lstArticlesVendus.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
		return lstArticlesVendus;
	}

	@Override
	public List<ArticleVendu> selectVentesNonDebuteesByVendeurByNomAndCat(Integer noUtilisateur, String nomLike, Integer noCategorie) throws DALException {
		List<ArticleVendu> lstArticlesVendus = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_MES_VENTES_NON_DEBUTEES_BY_NOM_AND_CAT);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+nomLike+"%");
			if(noCategorie == 0) {
				pStmt.setString(3, "%%");
			}else {
				pStmt.setString(3, "%"+noCategorie+"%");
			}
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				map(articleVendu, rs);
				lstArticlesVendus.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
		return lstArticlesVendus;
	}

	@Override
	public List<ArticleVendu> selectVentesTermineesByVendeurByNomAndCat(Integer noUtilisateur, String nomLike, Integer noCategorie) throws DALException {
		List<ArticleVendu> lstArticlesVendus = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_MES_VENTES_TERMINEES_BY_NOM_AND_CAT);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+nomLike+"%");
			if(noCategorie == 0) {
				pStmt.setString(3, "%%");
			}else {
				pStmt.setString(3, "%"+noCategorie+"%");
			}
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				map(articleVendu, rs);
				lstArticlesVendus.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
		return lstArticlesVendus;
	}

	
	
	private void map(ArticleVendu articleVendu, ResultSet rs) throws SQLException, DALException {
		articleVendu.setNoArticle(rs.getInt("no_article"));
		articleVendu.setNomArticle(rs.getString("nom_article"));
		articleVendu.setDescription(rs.getString("description"));
		articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
		articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
		articleVendu.setPrixInitial(rs.getInt("prix_initial"));
		articleVendu.setPrixVente(rs.getInt("prix_vente"));	
		articleVendu.setUtilisateur((daoUtilisateur.selectById(rs.getInt("no_utilisateur"))));
		articleVendu.setCategorie((daoCategorie.selectById(rs.getInt("no_categorie"))));
		articleVendu.setRetrait(daoRetrait.selectById(rs.getInt("no_retrait")));
	}	
	
	
}
