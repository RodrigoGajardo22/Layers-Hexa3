package domain.model;

import domain.portsin.DomainException;

public class Participante {

	private int    puntos;
	private String nombre;
	private String apellido;
	private String tel;
	private String email;
	private String dni;
	private String idConcurso;

	public Participante(String nombre, String apellido, String tel, String email, String dni, String idConcurso) {

		this.nombre     = nombre;
		this.apellido   = apellido;
		this.tel        = tel;
		this.puntos     = 0;
		this.email      = email;
		this.dni        = dni;
		this.idConcurso = idConcurso;

		validarDatos();

	}

	private void validarDatos() {

		if (apellido == null || apellido.equals(""))
			throw new DomainException("APELLIDO no puede ser vacio.");

		if (nombre == null || nombre.equals(""))
			throw new DomainException("NOMBRE no puede ser vacio.");

		if (!checkEmail(email))
			throw new DomainException("Ingrese un Email");

		if (!validarDni(dni))
			throw new DomainException("DNI no puede ser vacio");

		if (!validarTelefono(tel)) {
			throw new DomainException("El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN");

		}

	}

	private boolean validarDni(String dni) {
		int longitud = dni.length();
		return (longitud >= 7 && longitud <= 10) && dni.matches("\\d+");

	}

	public boolean validarTelefono(String telefono) {
		String regex = "\\d{4}-\\d{6}";
		return telefono.matches(regex);
	}

	private boolean checkEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	public int puntos() {
		return puntos;
	}

	public String email() {
		return email;
	}

	public void sumarPuntos() {
		puntos = +10;
	}

	public String datos() {
		if (nombre != null)
			return "" + nombre + "|" + apellido + "|" + tel + "|" + email + "|" + idConcurso;

		return "No se encontraron datos.";
	}

	public String nombre() {

		return nombre;
	}

}
