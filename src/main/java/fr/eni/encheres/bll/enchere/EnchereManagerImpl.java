package fr.eni.encheres.bll.enchere;

import java.util.List;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.enchere.EnchereDAO;
import fr.eni.encheres.dal.enchere.EnchereDAOFact;

public class EnchereManagerImpl implements EnchereManager {
	
	EnchereDAO dao = EnchereDAOFact.getInstance();

	@Override
	public void ajouterEnchere(Enchere enchere) throws BllException, DALException {
		BllException be = new BllException();
		
		verifAll(enchere.getDateEnchere(), enchere.getMontantEnchere(), enchere.getArticleVendu(), enchere.getUtilisateur(), be);
		
		if (be.hasErreur()) {
			throw be;
		}
		
		dao.insert(enchere);
	}

	@Override
	public void supprimerEnchere(Enchere enchere) throws BllException {
		try {
			return dao.selectById(noUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public Enchere afficherEnchere(Utilisateur utilisateur) throws BllException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> afficherTousEnchere() throws BllException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
