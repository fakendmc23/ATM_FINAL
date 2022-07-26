import java.util.Scanner;

public class Teclado {
	
	private Scanner entradaEscaner;
	private String entradaTeclado;
	private Pantalla pantalla;
	
	public Teclado(){
		entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
		pantalla = new Pantalla();
	}
	
	public String ingresoPorTeclado(String mensaje){
		// TODO chequear q lo q se ingresa sea numerico
		pantalla.mostrarMensaje(mensaje);
		entradaTeclado = entradaEscaner.nextLine(); //Invocamos un método sobre un objeto Scanner
		return entradaTeclado;	
	}

}
