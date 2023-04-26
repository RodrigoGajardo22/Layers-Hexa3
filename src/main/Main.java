package main;

import domain.model.Concursos;
import domain.portsin.GestorConcurso;
import domain.portsout.PersistenciaConcursos;
import domain.portsout.PersistenciaParticipantes;
import infraestructura.data.ConcursosRegistroEnArchivo;
import infraestructura.data.ParticipanteRegistroEnArchivo;
import infraestructura.ui.UI;

public class Main {
	public static void main(String[] args) {

		String                    rutaConcurso         = "C:\\Users\\Usuario\\Documents\\2 - UNIVERSIDAD RODRI\\Orientacion a Objetos 2\\Persistencia\\Registro De Concursos.txt";
		String                    rutaParticipantes    = "C:\\Users\\Usuario\\Documents\\2 - UNIVERSIDAD RODRI\\Orientacion a Objetos 2\\Persistencia\\\\Inscriptos.txt";
		PersistenciaConcursos     persistenciaConcurso = new ConcursosRegistroEnArchivo(rutaConcurso);
		PersistenciaParticipantes participantes        = new ParticipanteRegistroEnArchivo(rutaParticipantes);

		GestorConcurso            gestor               = new Concursos(persistenciaConcurso, participantes);

		UI                        ui                   = new UI(gestor);

	}
}
