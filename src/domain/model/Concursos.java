package domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import domain.portsin.GestorConcurso;
import domain.portsout.PersistenciaConcursos;
import domain.portsout.PersistenciaParticipantes;

public class Concursos implements GestorConcurso {

	private PersistenciaConcursos     persistenciaConcursos;
	private PersistenciaParticipantes participantes;

	public Concursos(PersistenciaConcursos concursos, PersistenciaParticipantes participantes) {
		this.persistenciaConcursos = concursos;
		this.participantes         = participantes;

	}

	public List<String> listaConcursos() {
		List<String> listaConcurso = new ArrayList<>();
		for (Concurso concurso : todosLosConcursos()) {
			listaConcurso.add(concurso.nombre());
		}

		return listaConcurso;
	}

	public List<Concurso> todosLosConcursos() {

		List<Concurso> listaConcurso = new ArrayList<>();
		String         datos         = persistenciaConcursos.leerDatos();

		String[]       linea         = datos.split("\n");                // cada linea son los datos de un empleado

		for (String lineaEmpleado : linea) {

			String[] datosConcurso = lineaEmpleado.split(",");
			Concurso concurso      = convertirAConcurso(datosConcurso);
			listaConcurso.add(concurso);

		}

		return listaConcurso;

	}

	private Concurso convertirAConcurso(String[] datos) {

		int       id       = Integer.parseInt(datos[0]);
		String    nombre   = datos[1];
		LocalDate fechaIni = LocalDate.parse(datos[2]);
		LocalDate fechaFin = LocalDate.parse(datos[3]);

		return new Concurso(id, nombre, fechaIni, fechaFin);
	}

	private int buscarIDConcurso(String nombre) {

		for (Concurso concurso : todosLosConcursos()) {
			if (concurso.nombre().equals(nombre)) {
				return concurso.id();
			}
		}
		return 0;
	}

	@Override
	public boolean cargarParticipante(String nombre, String apellido, String tel, String dni, String email,
			String nombreConcurso) {

		String       id           = "" + buscarIDConcurso(nombreConcurso);

		Participante participante = new Participante(nombre, apellido, tel, email, dni, id);
		return participantes.registrar(participante.datos());
	}

}
