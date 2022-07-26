
import java.io.IOException;

public class CompraDeDolares extends Transaccion {
	
	//Atributos
	private byte tipoCuentaDolar;
	private Cuenta cuentaDolar;
	private String nombreDeTransaccion;

	//Constructor
	public CompraDeDolares(int montoDolar, byte tipoCuentaDeOrigen, Cuenta cuentaOrigen, byte tipoCuentaDolar,
			Cuenta cuentaDolar){
		super(montoDolar, tipoCuentaDeOrigen, cuentaOrigen);
		if (montoDolar > 200) {
			System.out.println("Solo se puede comprar hasta 200 dolares");
		}
		this.tipoCuentaDolar = tipoCuentaDolar;
		this.cuentaDolar = cuentaDolar;
		this.nombreDeTransaccion = "Compra De Dolares";
	}

	//Métodos
	
	/* pre:  
	 * 
	 * post: Devuelve el nombre de la transacción
	 */
	public String getNombreDeTransaccion() {
		return nombreDeTransaccion;
	}
	
	/* pre: 
	 * 
	 * post: Se realiza la compra de dolares
	 */	
	@Override
	public void realizar() throws SaldoInsuficienteException {
		comprarDolares(getMonto(), getTipoCuentaOrigen(), getCuentaOrigen(), tipoCuentaDolar, cuentaDolar);
	}

	/* pre: 
	 * 
	 * post: 
	 */	
	private void comprarDolares(int montoDolar, byte tipoCuentaPesos, Cuenta cuentaPesos, byte tipoCuentaDolar,
			Cuenta cuentaDolar) throws SaldoInsuficienteException {
		boolean habilitado = comprobarSaldo(tipoCuentaPesos, cuentaPesos);
		if (habilitado) {
			// calcule cantidad de pesos a debitar
			double cantidadDePesosADebitar = dolarAPesos(montoDolar);
			// resta la cantidad de pesos de cuenta pesos
			cuentaPesos.setSaldo(cuentaPesos.getSaldo() - cantidadDePesosADebitar);
			double nuevoSaldoPesos = cuentaPesos.getSaldo();
			Movimiento movimientoPesos = new Movimiento("Compra dolar", cuentaPesos.getAlias(),
					cantidadDePesosADebitar);
			// agarre el monto en dolares y los deposite en la cuenta dolar
			cuentaDolar.setSaldo(montoDolar + cuentaDolar.getSaldo());
			double nuevoSaldoDolar = cuentaDolar.getSaldo();
			Movimiento movimientoDolar = new Movimiento("Compra dolar", cuentaDolar.getAlias(), montoDolar);
			// imprimir
			Ticket ticketDolar = new Ticket(tipoCuentaDolar, nombreDeTransaccion, montoDolar, nuevoSaldoDolar);
			Ticket ticketPesos = new Ticket(tipoCuentaPesos, nombreDeTransaccion, cantidadDePesosADebitar,
					nuevoSaldoPesos);
			Impresora impresora = new Impresora();
			try {
				impresora.imprimirTicket(ticketDolar);
				impresora.imprimirTicket(ticketPesos);
			} catch (IOException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}

		} else {
			throw new SaldoInsuficienteException("El saldo es insuficiente"); // Hacer bien una excepción
		}

	}

	/* pre: Recibe un monton en dolares
	 * 
	 * post: realiza la conversión a pesos con el impuesto pais
	 */	
	private double dolarAPesos(int montoDolar) {
		// TODO Apéndice de método generado automáticamente
		Banco banco = new Banco();
		double cotizacionDolarActual = banco.getCotizacionDelDolarDelDia();
		return banco.impuestoPais(montoDolar * cotizacionDolarActual);
	}

	/* pre: 
	 * 
	 * post: Comprueba si el saldo de la cuenta es suficiente para realizar la operación
	 */	
	@Override
	public boolean comprobarSaldo(byte tipoCuenta, Cuenta cuentaPesos) {
		// TODO Apéndice de método generado automáticamente
		double pesosPorDebitar = dolarAPesos(getMonto());
		if (pesosPorDebitar < cuentaPesos.getSaldo()) {
			return true;
		} else {
			return false;
		}

	}
}
