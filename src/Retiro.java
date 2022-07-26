import java.io.IOException;
import java.util.Arrays;

public class Retiro extends Transaccion  {
	
	//Atributos
	private DispensadorDeEfectivo dispensador;
	private String nombreDeTransaccion;
	//Constructor
	public Retiro(int monto, byte tipoCuentaARetirar, Cuenta cuentaOrigen, DispensadorDeEfectivo dispensador) {
		super(monto,tipoCuentaARetirar,cuentaOrigen);
		this.nombreDeTransaccion = "Retiro";
		this.dispensador = dispensador;
	}

	//Métodos
	
	
	@Override
	public void realizar() throws SaldoInsuficienteException {
		// TODO Apéndice de método generado automáticamente
		retirar(getMonto(), getTipoCuentaOrigen(), getCuentaOrigen());
	}

	/* pre: 
	 * 
	 * post: retorna el nombre de la transacción
	 */	
	public String getNombreDeTransaccion() {
		return nombreDeTransaccion;
	}

	/* pre: 
	 * 
	 * post: Comprueba el saldo
	 */	
	@Override
	public boolean comprobarSaldo(byte tipoCuenta, Cuenta cuentaOrigen) {
		// TODO Apéndice de método generado automáticamente
		int descubierto = 0;
		if(cuentaOrigen.getTipoDeCuenta() == 2){
			CuentaCorriente cuentaCC = (CuentaCorriente) cuentaOrigen;
			descubierto = cuentaCC.getDescubierto();
		}
		int montoARetirar = getMonto();
		
		if(montoARetirar < cuentaOrigen.getSaldo() + descubierto){
			return true;
		} else {
			return false;
		}
	}

	/* pre: 
	 * 
	 * post: Se retira el saldo de la cuenta pesos
	 */	
	private void retirar(int monto, byte tipoCuenta, Cuenta cuentaOrigen) throws SaldoInsuficienteException{
		boolean habilitado = comprobarSaldo(tipoCuenta, cuentaOrigen);
		if(habilitado){
			//  TODO Si tiene saldo hay que hacer la operación.
			int[] billetesEntregados = dispensador.entregarBilletes(monto);
			//imprimir en pantalla la entrega de billetes
			System.out.println("Se entregaron: " + monto);
			System.out.println("Billetes:" + Arrays.toString(billetesEntregados));
			//actualizo saldo en cuenta
			double saldoRestante = cuentaOrigen.getSaldo() - monto;
			System.out.println("Se entregaron: " + saldoRestante);
			cuentaOrigen.setSaldo(saldoRestante);		
			//Movimiento dejar constancia del retiro
			Movimiento movimiento = new Movimiento("Retiro", cuentaOrigen.getAlias(), monto);
			Ticket ticket = new Ticket(tipoCuenta, nombreDeTransaccion, monto, saldoRestante);
			Impresora impresora = new Impresora();
			try {
				impresora.imprimirTicket(ticket);
			} catch (IOException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}			
		} else {
			Pantalla pantalla = new Pantalla();
			pantalla.mostrarMensaje("El saldo es insuficiente");
		}
	}
}
