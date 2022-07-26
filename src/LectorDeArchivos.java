

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LectorDeArchivos {
	
	//Atributos
	private String nombreDeArchivo;
	private FileReader lectorDeArchivo;
	private BufferedReader bufferDeArchivo;
	
	//Constructor
	public LectorDeArchivos(String nombreDeArchivo){
		this.nombreDeArchivo = nombreDeArchivo;
		try {
			this.lectorDeArchivo = new FileReader(new File(nombreDeArchivo));
			this.bufferDeArchivo = new BufferedReader(lectorDeArchivo);
		} catch (FileNotFoundException e) {
			// TODO Bloque catch generado automáticamente
			System.out.println("Archivo no encontrado.");
			e.printStackTrace();
		}
	}

	//Métodos
	
	
	public String[] leerLinea() {
		try {
			String linea = bufferDeArchivo.readLine();
			// Me quedo con los parametros en un array.
			if(linea == null){
				return null;
			}
			String[] parametros = linea.split(",");
			return parametros;
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return null;
	}

	public String getNombreDeArchivo() {
		return nombreDeArchivo;
	}
	
	public void setNombreDeArchivo(String nombreDeArchivo) {
		this.nombreDeArchivo = nombreDeArchivo;
	}
}
