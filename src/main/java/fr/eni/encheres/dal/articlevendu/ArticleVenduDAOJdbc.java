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
	
	UtilisateurDAO daoUtilisateur = UtilisateurDAOFact.getInstance();
	CategorieDAO daoCategorie = CategorieDAOFact.getInstance();
	RetraitDAO daoRetrait = RetraitDAOFact.getInstance();
	
	private final String INSERT = "INSERT  INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie, no_retrait) VALUES(?, ?, ?, ?, ?, ?, ?, ?)"; 
	private final String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";
	private final String SELECT_BY_ID = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS WHERE no_article=?";
	private final String UPDATE = "UPDATE ARTICLES_VENDUS SET prix_vente = ? WHERE no_article = ?";
	private final String SELECT_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS";
	private final String SELECT_BY_CATEGORIE = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS WHERE no_categorie=?";


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
				articleVendu.setNoArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));	
				articleVendu.setUtilisateur(daoUtilisateur.selectById(rs.getInt("no_utilisateur")));
				articleVendu.setCategorie(daoCategorie.selectById(rs.getInt("no_categorie")));
				articleVendu.setRetrait(daoRetrait.selectById(rs.getInt("no_retrait")));
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
				articleVendu.setNoArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));	
				articleVendu.setUtilisateur(daoUtilisateur.selectById(rs.getInt("no_utilisateur")));
				articleVendu.setCategorie(daoCategorie.selectById(rs.getInt("no_categorie")));
				articleVendu.setRetrait(daoRetrait.selectById(rs.getInt("no_retrait")));
				lstArticleByCategorie.add(articleVendu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		
		return lstArticleByCategorie;
	}
	
}
