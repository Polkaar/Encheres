package fr.eni.encheres.dal.retrait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;

public class RetraitDAOJdbc implements RetraitDAO {
	

	
	private final String INSERT = "INSERT  INTO RETRAITS (rue, code_postal, ville) VALUES(?, ?, ?)"; 
	private final String DELETE = "DELETE FROM RETRAITS WHERE no_retrait=?";
	private final String UPDATE = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ?  WHERE no_retrait = ?";
	private final String SELECT_BY_ID = "SELECT no_retrait, rue, code_postal, ville FROM RETRAITS WHERE no_retrait=?";

	@Override
	public void insert(Retrait nouveauRetrait) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, nouveauRetrait.getRue());
			pStmt.setString(2, nouveauRetrait.getCodePostal());
			pStmt.setString(3, nouveauRetrait.getVille());
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				int noRetrait = rs.getInt(1);
				nouveauRetrait.setNoRetrait(noRetrait);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
	}

	@Override
	public void delete(Retrait retrait) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(DELETE);
			pStmt.setInt(1, retrait.getNoRetrait());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
	}

	@Override
	public void update(Retrait retrait) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
			pStmt.setString(1, retrait.getRue());
			pStmt.setString(2, retrait.getCodePostal());
			pStmt.setString(3, retrait.getVille());
			pStmt.setInt(4, retrait.getNoRetrait());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
	}

	@Override
	public Retrait selectById(Integer noRetrait) throws DALException {
		Retrait retrait = new Retrait();
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID);
			pStmt.setInt(1, noRetrait);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				retrait.setNoRetrait(rs.getInt("no_retrait"));;
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Probleme de connexion");
		}
		return retrait;
	}

}
