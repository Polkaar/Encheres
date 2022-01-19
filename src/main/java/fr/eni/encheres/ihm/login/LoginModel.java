package fr.eni.encheres.ihm.login;

public class LoginModel {

	private static LoginModel loginModel = new LoginModel();
	public static LoginModel getInstance() {
		if(loginModel == null) {
			loginModel = new LoginModel();
		}
		return loginModel;
	}
	
	private String pseudo;
	private String motDePasse;
	
	public LoginModel() {
		super();
	}
	public LoginModel(String pseudo, String motDePasse) {
		super();
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	@Override
	public String toString() {
		return "LoginModel [pseudo=" + pseudo + ", motDePasse=" + motDePasse + "]";
	}
	
}
