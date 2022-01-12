package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

public class BllException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private List<Exception> erreurs = new ArrayList<Exception>();
	
	public BllException() { }
	
	public BllException(Exception e) {
		ajouterErreur(e);
	}
	
	public void ajouterErreur(Exception e) {
		erreurs.add(e);
	}
	
	public boolean hasErreur() {
		return !erreurs.isEmpty();
	}

	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		for(Exception e : erreurs) {
			sb.append(e.getMessage())
			  .append(System.lineSeparator());
		}
		return sb.toString();
	}
	
	
}
