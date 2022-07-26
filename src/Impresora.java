

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Impresora {

	//Constructor
	public void imprimirTicket(Ticket ticket) throws IOException {
		if(consultarImpresion()){
			imprimirArchivo(ticket);
		} 
	}

	//Se crea un archivo .txt con nombre �nico con los datos pertinentes de cada transacci�n
	private void imprimirArchivo(Ticket ticket) throws IOException {
		// TODO Ap�ndice de m�todo generado autom�ticamente
			String fecha = ticket.getFecha();
			String hora = java.time.LocalTime.now().toString();
			String horaBien = hora.replace(":", "");
			String horaReBien = horaBien.replace(".", "");
			File file = new File("Ticket_n" + fecha.replace("-", "") + horaReBien + ".txt");
			file.createNewFile();
			FileWriter flwriter = new FileWriter(file);
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			bfwriter.write("Fecha: " +fecha);
			bfwriter.newLine();
			bfwriter.write("Hora: " + hora);
			bfwriter.newLine();	
			bfwriter.write("Tipo de Cuenta: " +String.valueOf(ticket.getCuenta()));
			bfwriter.newLine();	
			bfwriter.write("Transaccion: " + ticket.getTipoDeTransaccion());
			bfwriter.newLine();	
			bfwriter.write("Importe: "+String.valueOf(ticket.getImporte()));
			bfwriter.newLine();
			bfwriter.write(String.valueOf("Nuevo saldo en cuenta: " + ticket.getNuevoSaldo()));
			bfwriter.newLine();
			bfwriter.close();
			flwriter.close();
			System.out.println("El ticket se imprimi� correctamente");
	}	  

	/* pre: 
	 * 
	 * post: Se consulta si que quier eimprimir el ticket
	 */	
	private boolean consultarImpresion() {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		GestorDeSolicitudes gestor = new GestorDeSolicitudes(new Pantalla(),new Teclado());
		return gestor.solicitarConfirmacionDeImpresion();
	}
	
	
}
