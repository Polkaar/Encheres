package fr.eni.encheres.dal;

import java.util.ArrayList;
import java.util.List;

public class DALException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DALException(String message) {
		super(message);
	}
	
	private List<Exception> erreurs = new ArrayList<Exception>();

	public void ajouterErreur(Exception e) {
		erreurs.add(e);
	}

	public boolean hasErreur() {
		return !erreurs.isEmpty();
	}

	public DALException() {
		super();
	}

	public DALException(List<Exception> erreurs) {
		super();
		this.erreurs = erreurs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("");
		builder.append(System.lineSeparator());
		for (Exception exception : erreurs) {
			builder.append(exception.getMessage()).append(System.lineSeparator());
		}

		return builder.toString();
	}

}
