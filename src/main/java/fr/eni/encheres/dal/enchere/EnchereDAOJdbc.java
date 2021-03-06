package fr.eni.encheres.dal.enchere;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.articlevendu.ArticleVenduDAO;
import fr.eni.encheres.dal.articlevendu.ArticleVenduDAOFact;
import fr.eni.encheres.dal.utilisateur.UtilisateurDAO;
import fr.eni.encheres.dal.utilisateur.UtilisateurDAOFact;

public class EnchereDAOJdbc implements EnchereDAO{
	
	UtilisateurDAO daoUtilisateur = UtilisateurDAOFact.getUtilisateurDAO();
	ArticleVenduDAO daoArticle = ArticleVenduDAOFact.getArticleVenduDAO();

	
	private final String INSERT = "INSERT  INTO ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur) VALUES(?, ?, ?, ?)"; 
	private final String DELETE = "DELETE FROM ENCHERES WHERE no_enchere=?";
	private final String SELECT_BY_UTILISATEUR = "SELECT date_enchere, montant_enchere, no_article, no_utilisateur FROM ENCHERES WHERE no_utilisateur=?";
	private final String SELECT_BY_ARTICLE = "SELECT date_enchere, montant_enchere, no_article, no_utilisateur FROM ENCHERES WHERE no_article=?";
	private final String SELECT_DERNIERE_ENCHERE = "SELECT no_enchere, date_enchere, montant_enchere, e.no_article, e.no_utilisateur FROM ENCHERES AS e INNER JOIN ARTICLES_VENDUS AS a ON e.no_article = a.no_article WHERE e.no_article = ? AND e.montant_enchere = a.prix_vente;";
	private final String SELECT_ALL = "SELECT date_enchere, montant_enchere, no_article, no_utilisateur FROM ENCHERES";
	

	@Override
	public void insert(Enchere nouvelleEnchere) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setDate(1, Date.valueOf(nouvelleEnchere.getDateEnchere()));
			pStmt.setInt(2, nouvelleEnchere.getMontantEnchere());
			pStmt.setInt(3, nouvelleEnchere.getArticleVendu().getNoArticle());
			pStmt.setInt(4, nouvelleEnchere.getUtilisateur().getNoUtilisateur());
			
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				int noEnchere = rs.getInt(1);
				nouvelleEnchere.setNoEnchere(noEnchere);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
	}

	@Override
	public void delete(Enchere enchere) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(DELETE);
			pStmt.setInt(1, enchere.getNoEnchere());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
		
	}

	@Override
	public List<Enchere> selectByUtilisateur(Integer noUtilisateur) throws DALException {
		List<Enchere> lstEncheres = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_UTILISATEUR);
			pStmt.setInt(1, noUtilisateur);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setNoEnchere(rs.getInt("no_enchere"));
				enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontantEnchere(rs.getInt("description"));
				enchere.setArticleVendu(daoArticle.selectById(rs.getInt("no_article")));
				enchere.setUtilisateur(daoUtilisateur.selectById(rs.getInt("no_utilisateur")));
				lstEncheres.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		return lstEncheres;
	}
	
	@Override
	public Enchere selectDerniereEnchere(Integer noArticle) throws DALException {
		Enchere enchere = new Enchere();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_DERNIERE_ENCHERE);
			pStmt.setInt(1, noArticle);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				enchere.setNoEnchere(rs.getInt("no_enchere"));
				enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontantEnchere(rs.getInt("montant_enchere"));
				enchere.setArticleVendu(daoArticle.selectById(rs.getInt("no_article")));
				enchere.setUtilisateur(daoUtilisateur.selectById(rs.getInt("no_utilisateur")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		return enchere;
	}
	
	@Override
	public List<Enchere> selectByArticle(Integer noArticle) throws DALException {
		List<Enchere> lstEncheres = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ARTICLE);
			pStmt.setInt(1, noArticle);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setNoEnchere(rs.getInt("no_enchere"));
				enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontantEnchere(rs.getInt("description"));
				enchere.setArticleVendu(daoArticle.selectById(rs.getInt("no_article")));
				enchere.setUtilisateur(daoUtilisateur.selectById(rs.getInt("no_utilisateur")));
				lstEncheres.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		return lstEncheres;
	}

	@Override
	public List<Enchere> selectAll() throws DALException {
		List<Enchere> lstEncheres = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setNoEnchere(rs.getInt("no_enchere"));
				enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setMontantEnchere(rs.getInt("description"));
				enchere.setArticleVendu(daoArticle.selectById(rs.getInt("no_article")));
				enchere.setUtilisateur(daoUtilisateur.selectById(rs.getInt("no_utilisateur")));
				lstEncheres.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		return lstEncheres;
	}
	

}
