package infraestructura.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import domain.portsout.PersistenciaParticipantes;
import domain.portsout.RegistroException;

public class ParticipanteRegistroEnArchivo implements PersistenciaParticipantes {

	private String ruta;

	public ParticipanteRegistroEnArchivo(String ruta) {
		this.ruta = ruta;
	}

	public String leerDatos() {

		String  datos   = "";
		File    archivo = new File(ruta);
		Scanner scanner;

		try {
			scanner = new Scanner(archivo);

			while (scanner.hasNextLine()) {
				datos += scanner.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			throw new RegistroException("Error leer datos en archivo");

		}
		return datos;
	}

	@Override
	public boolean registrar(String dato) {

		try {

			File       archivo  = new File(ruta);
			FileWriter escribir = new FileWriter(archivo, true);

			escribir.write(dato);
			escribir.write("\r\n");
			escribir.close();
		} // Si existe un problema al escribir cae aqui
		catch (Exception e) {
			throw new RegistroException("Error al intentar escribir datos en Archivo.");
		}

		return true;

	}

}
