package fr.eni.encheres.dal.enchere;

import java.util.List;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DALException;

public interface EnchereDAO {

	void insert(Enchere nouvelleEnchere) throws DALException;
	void delete(Enchere enchere)throws DALException;
	List<Enchere> selectByUtilisateur(Integer noUtilisateur)throws DALException;
	List<Enchere> selectByArticle(Integer noArticle)throws DALException;
	List<Enchere> selectAll()throws DALException;
}
