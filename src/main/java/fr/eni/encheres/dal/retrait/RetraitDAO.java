package fr.eni.encheres.dal.retrait;


import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DALException;

public interface RetraitDAO {

	void insert(Retrait retrait) throws DALException;
	void delete(Retrait retrait) throws DALException;
	void update(Retrait retrait) throws DALException;
	Retrait selectById(Integer noArticle) throws DALException;
}
