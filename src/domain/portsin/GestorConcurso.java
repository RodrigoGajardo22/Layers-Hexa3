package domain.portsin;

import java.util.List;

public interface GestorConcurso {

	public List<String> listaConcursos();

	public boolean cargarParticipante(String nombre, String apellido, String tel, String dni, String email,
			String IDConcurso);

}
