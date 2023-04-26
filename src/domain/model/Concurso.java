package domain.model;

import java.time.LocalDate;

public class Concurso {

	private LocalDate fechaInicio, fechaFin;
	private int       ID_Concurso;
	private String    nombreConcurso;

	// idconcurso, nombre, fechaInicioInscripcion, fechaFinInscripcion
	public Concurso(int idConcurso, String nombre, LocalDate fechaIni, LocalDate fechaFin) {

		this.fechaInicio    = fechaIni;
		this.fechaFin       = fechaFin;
		this.ID_Concurso    = idConcurso;
		this.nombreConcurso = nombre;
	}

	public int id() {
		return ID_Concurso;
	}

	public String nombre() {
		return nombreConcurso;
	}

	private void verificarFechaYsumarPuntos(Participante p) {
		LocalDate fechaDeHoy = LocalDate.now();
		if (fechaInicio.equals(fechaDeHoy)) {
			p.sumarPuntos();
		}
	}

	private boolean puedeInscribirse() {// si la fecha es valida, entonces True;

		LocalDate fechaHoy = LocalDate.now();
		if (fechaHoy.equals(fechaInicio))
			return true;
		if (fechaHoy.isAfter(fechaInicio) && fechaHoy.isBefore(fechaFin))
			return true;

		return false;
	}

//	public void mostrarParticipantes() {
//
//		int i = 1;
//		if (listParticipantes.size() > 0)
//			for (Participante p : listParticipantes) {
//				System.out.println(i + " - " + p.mostrarDatos());
//				i++;
//			}
//	}

}
