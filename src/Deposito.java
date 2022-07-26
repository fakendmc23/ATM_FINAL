import java.io.IOException;


public class Deposito extends Transaccion {
	
	//Atributos
	private String nombreDeTransaccion;
	
	//Constructor
	public Deposito(int monto, byte tipoCuentaADepositar, Cuenta cuentaOrigen) {
		super(monto, tipoCuentaADepositar, cuentaOrigen); 
		this.nombreDeTransaccion = "Deposito";
	}

	//Métodos
	
	/* pre: 
	 * 
	 * post: se realiza la operación de depositar en una cuenta 
	 */	
	@Override
	public void realizar() {
		//me hace ruidoooooooooooooo
		depositar(this.getMonto(),this.getTipoCuentaOrigen(), this.getCuentaOrigen());
	}

	/* pre: 
	 * 
	 * post: retorna el nombre de la transacción
	 */	
	public String getNombreDeOperacion() {
		return nombreDeTransaccion;
	}

	/* pre: se necesita un monto positivo, un tipo de cuentaDestino, cuentaDestino
	 * 
	 * post: 
	 */	
	private void depositar(int monto, byte cuentaADepositar, Cuenta cuentaOrigen){
		double nuevoSaldo = monto + cuentaOrigen.getSaldo();
		cuentaOrigen.setSaldo(nuevoSaldo);
		//impirmir en pantalla la entrega de billetes
		System.out.println("Se depositó: " + monto);
		//Movimiento dejar constancia del retiro
		Movimiento movimiento = new Movimiento("Deposito", cuentaOrigen.getAlias(), monto);
		Ticket ticket = new Ticket(cuentaADepositar, nombreDeTransaccion, monto, nuevoSaldo);
		Impresora impresora = new Impresora();
		try {
			impresora.imprimirTicket(ticket);
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}		
	}
	
	/* pre: 
	 * 
	 * post: Comprueba el saldo 
	 */		
	@Override
	public boolean comprobarSaldo(byte tipoCuenta, Cuenta cuentaOrigen) {
		// TODO Apéndice de método generado automáticamente
		return false;
	}
}
