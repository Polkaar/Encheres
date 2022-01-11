package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

public class ArticleVenduJdbcImpl implements ArticleVenduDAO {
	
	private final String INSERT = "INSERT  INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES(?, ?, ?, ?, ?, ?, ?, ?)"; 
	private final String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";
	private final String UPDATE = "UPDATE ARTICLES_VENDUS SET prix_vente = ? WHERE no_article = ?";
	private final String SELECT_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS";
	private final String SELECT_BY_CATEGORIE = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_categorie=?";


	@Override
	public void insert(ArticleVendu articleVendu) throws DALException{
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, articleVendu.getNomArticle());
			pStmt.setString(1, articleVendu.getDescription());
			pStmt.setDate(3, Date.valueOf(articleVendu.getDateDebutEncheres()));
			pStmt.setDate(1, Date.valueOf(articleVendu.getDateFinEncheres()));
			pStmt.setInt(1, articleVendu.getPrixInitial());
			pStmt.setInt(1, articleVendu.getUtilisateur().getNoUtilisateur());
			pStmt.setInt(1, articleVendu.getCategorie().getNoCategorie());
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				int numArticleVendu = rs.getInt(1);
				articleVendu.setNoArticle(numArticleVendu);
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
	public List<ArticleVendu> getAll() throws DALException {
//		UtilisateurDAO daoUtilisateur = UtilisateurDAOFact.getUtilisateurDAO();
//		CategorieDAO daoCategorie = CategorieDAOFact.getCategorieDAO();
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
//				articleVendu.setUtilisateur(new Utilisateur(daoUtilisateur.getById(rs.getInt("no_utilisateur"))));
//				articleVendu.setCategorie(new Categorie(daoCategorie.getById(rs.getInt("no_categorie"))));
				lstArticlesVendus.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
		return lstArticlesVendus;
	}

	@Override
	public List<ArticleVendu> getArticleByCategorie(int noCategorie) throws DALException {
//		UtilisateurDAO daoUtilisateur = UtilisateurDAOFact.getUtilisateurDAO();
//		CategorieDAO daoCategorie = CategorieDAOFact.getCategorieDAO();
		List<ArticleVendu> lstArticleByCategorie = new ArrayList<>();
		ArticleVendu articleVendu = null;
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_CATEGORIE);
			pStmt.setInt(1, noCategorie);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				articleVendu.setNoArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));	
//				articleVendu.setUtilisateur(new Utilisateur(daoUtilisateur.getById(rs.getInt("no_utilisateur"))));
//				articleVendu.setCategorie(new Categorie(daoCategorie.getById(rs.getInt("no_categorie"))));
				lstArticleByCategorie.add(articleVendu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		
		return lstArticleByCategorie;
	}
	
}
