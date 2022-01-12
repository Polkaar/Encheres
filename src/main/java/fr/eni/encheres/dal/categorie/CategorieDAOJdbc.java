package fr.eni.encheres.dal.categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;

public class CategorieDAOJdbc implements CategorieDAO {

	private final String INSERT = "INSERT INTO CATEGORIES (libelle) VALUES(?)";
	private final String SELECT_BY_ID = "SELECT no_categorie, libelle FROM CATEGORIES WHERE no_categorie=?";
	private final String DELETE = "DELETE FROM CATEGORIES WHERE no_categorie=?";

	@Override
	public void insert(Categorie nouvelleCategorie) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pStmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, nouvelleCategorie.getLibelle());

			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if (rs.next()) {
				int noCategorie = rs.getInt(1);
				nouvelleCategorie.setNoCategorie(noCategorie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public Categorie selectById(int noCategorie) throws DALException {
		Categorie categorie = new Categorie();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID);
			pStmt.setInt(1, noCategorie);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}

		return categorie;
	}

	@Override
	public void delete(Categorie categorie) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(DELETE);
			pStmt.setInt(1, categorie.getNoCategorie());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
	}

}
