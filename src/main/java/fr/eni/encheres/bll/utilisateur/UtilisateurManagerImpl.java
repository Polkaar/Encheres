package fr.eni.encheres.bll.utilisateur;

import java.util.List;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bll.ParameterException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.utilisateur.UtilisateurDAO;
import fr.eni.encheres.dal.utilisateur.UtilisateurDAOFact;

public class UtilisateurManagerImpl implements UtilisateurManager{
	
	UtilisateurDAO dao = UtilisateurDAOFact.getUtilisateurDAO();

	
	@Override
	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) throws BllException{
		BllException be = new BllException();
		
		verifAll(utilisateur.getPseudo(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getTelephone(),
				utilisateur.getRue(), utilisateur.getCodePostal(), utilisateur.getVille(), utilisateur.getMotDePasse(),
				utilisateur.getCredit(), utilisateur.isAdministrateur(), be);
		
		if (be.hasErreur()) {
			throw be;
		}
		
		try {
			utilisateur = dao.insert(utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return utilisateur;
	}

	@Override
	public void modifierUtilisateur(Utilisateur utilisateur) throws BllException {
		BllException be = new BllException();
		
		verifAll(utilisateur.getPseudo(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getTelephone(),
				utilisateur.getRue(), utilisateur.getCodePostal(), utilisateur.getVille(), utilisateur.getMotDePasse(),
				utilisateur.getCredit(), utilisateur.isAdministrateur(), be);
		
		if (be.hasErreur()) {
			throw be;
		}
		
		try {
			dao.update(utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void supprimerUtilisateur(Utilisateur utilisateur) throws BllException {
		try {
			dao.delete(utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public Utilisateur afficherUtilisateur(Integer noUtilisateur) throws BllException {
		try {
			return dao.selectById(noUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public Utilisateur afficherUtilisateurParPseudo(String pseudo) throws BllException {
		try {
			return dao.selectByPseudo(pseudo);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public List<Utilisateur> afficherTousUtilisateurs() throws BllException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}
	
	
	
	private void verifAll(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, Integer credit, Boolean administrateur, BllException be)
			throws BllException {
		verifPseudo(pseudo, be);
		verifNom(nom, be);
		verifPrenom(prenom, be);
		verifEmail(email, be);
		verifTelephone(telephone, be);
		verifRue(rue, be);
		verifCodePostal(codePostal, be);
		verifVille(ville, be);
		verifMotDePasse(motDePasse, be);
		verifCredit(credit, be);
		verifAdministrateur(administrateur, be);

		if (be.hasErreur()) {
			throw be;
		}
	}

	private void verifPseudo(String pseudo, BllException be) throws BllException {
		if (pseudo == null || pseudo.isBlank() || pseudo.length() > 30) {
			be.ajouterErreur(new ParameterException("Le pseudonyme est obligatoire et doit être <= 30"));
		}
	}

	private void verifNom(String nom, BllException be) {
		if (nom == null || nom.isBlank() || nom.length() > 30) {
			be.ajouterErreur(new ParameterException("Le nom est obligatoire et doit être <= 30"));
		}
	}

	private void verifPrenom(String prenom, BllException be) {
		if (prenom == null || prenom.isBlank() || prenom.length() > 30) {
			be.ajouterErreur(new ParameterException("Le prénom est obligatoire et doit être <= 30"));
		}
	}

	private void verifEmail(String email, BllException be) {
		if (email == null || email.isBlank() || email.length() > 30) {
			be.ajouterErreur(new ParameterException("L'email est obligatoire et doit être <= 30"));
		}
	}

	private void verifTelephone(String telephone, BllException be) {
		if (telephone.length() > 15) {
			be.ajouterErreur(new ParameterException("Le téléphone doit être <= 15"));
		}
	}

	private void verifRue(String rue, BllException be) {
		if (rue == null || rue.isBlank() || rue.length() > 30) {
			be.ajouterErreur(new ParameterException("La rue est obligatoire et doit être <= 30"));
		}
	}

	private void verifCodePostal(String codePostal, BllException be) {
		if (codePostal == null || codePostal.isBlank() || codePostal.length() > 10) {
			be.ajouterErreur(new ParameterException("Le code postal est obligatoire et doit être <= 10"));
		}
	}

	private void verifVille(String ville, BllException be) {
		if (ville == null || ville.isBlank() || ville.length() > 50) {
			be.ajouterErreur(new ParameterException("La ville est obligatoire et doit être <= 50"));
		}
	}

	private void verifMotDePasse(String motDePasse, BllException be) {
		if (motDePasse == null || motDePasse.isBlank() || motDePasse.length() > 30) {
			be.ajouterErreur(new ParameterException("Le mot de passe est obligatoire et doit être <= 30"));
		}
	}

	private void verifCredit(Integer credit, BllException be) {
		if (credit == null) {
			be.ajouterErreur(new ParameterException("Un crédit de base doit être spécifié"));
		}
	}

	private void verifAdministrateur(Boolean administrateur, BllException be) {
		if (administrateur == null) {
			be.ajouterErreur(new ParameterException("Le statut de l'utilisateur (admin ou pas ?) doit être spécifié"));
		}
	}


}
