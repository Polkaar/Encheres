package fr.eni.encheres.dal.utilisateur;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;

public interface UtilisateurDAO {

	public Utilisateur insert(Utilisateur utilisateur) throws DALException;
	public void update(Utilisateur utilisateur) throws DALException;
	public void delete(Utilisateur utilisateur) throws DALException;
	public Utilisateur selectById(Integer noUtilisateur) throws DALException;
	public Utilisateur selectByPseudo(String pseudo) throws DALException;
	public List<Utilisateur> selectAll() throws DALException;
	
}
