

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Movimiento{

	// fechaString es la Date fecha escrita de manera que se pueda imprimir
	//Atributos
	private String concepto;
	private String fechaString;
	private double monto;
	private String alias;

	//Constructor
	public Movimiento(String concepto, String alias, double monto) {
		this.concepto = concepto;
		this.alias = alias;
		this.fechaString = java.time.LocalDate.now().toString();
		this.monto = monto;
		actualizarMovimientos();
	}

	//Métodos
	public void actualizarMovimientos() {
		// crea el flujo para escribir en el archivo
		try (FileWriter flwriter = new FileWriter("ArchivoMovimientos.txt", true)) {
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			bfwriter.write(fechaString + "," + concepto + "," + alias + "," + monto);
			bfwriter.newLine();
			bfwriter.close();
			System.out.println("Movimiento actualizado satisfactoriamente...");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
