package fr.eni.encheres.bll.categorie;

import java.util.List;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bll.ParameterException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.categorie.CategorieDAO;
import fr.eni.encheres.dal.categorie.CategorieDAOFact;

public class CategorieManagerImpl implements CategorieManager {
	
	CategorieDAO dao = CategorieDAOFact.getCategorieDAO();

	@Override
	public void ajouterCategorie(Categorie categorie) throws BllException {
		BllException be = new BllException();
		
		verifLibelle(categorie.getLibelle(), be);
		
		if (be.hasErreur()) {
			throw be;
		}
		
		try {
			dao.insert(categorie);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public Categorie afficherCategorie(ArticleVendu articleVendu) throws BllException {
		try {
			return dao.selectById(articleVendu.getCategorie().getNoCategorie());
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}
	
	

	@Override
	public void supprimerCategorie(Categorie categorie) throws BllException {
		try {
			dao.delete(categorie);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	
	private void verifLibelle(String libelle, BllException be) throws BllException {
		if (libelle == null || libelle.isBlank() || libelle.length() > 30) {
			be.ajouterErreur(new ParameterException("Le libellé est obligatoire et doit être <= 30"));
		}
	}

	@Override
	public Categorie afficherCategorieById(Integer noCategorie) throws BllException {
		try {
			return dao.selectById(noCategorie);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public List<Categorie> afficherTousCategories() throws BllException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}
	
}
