package entidades;

public class Credenciales {
	private String usuario;
	private String password;

	public Credenciales() {
	}

	public Credenciales(String usuario, String password) {
		super();
		this.setUsuario(usuario);
		this.setPassword(password);
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
