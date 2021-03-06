package fr.eni.encheres.dal.categorie;

import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.DALException;

public interface CategorieDAO {

	public void insert(Categorie categorie) throws DALException;
	void delete(Categorie categorie) throws DALException;
	Categorie selectById(int noCategorie) throws DALException;
	List<Categorie> selectAll() throws DALException;
}
