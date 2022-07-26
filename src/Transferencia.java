import java.io.IOException;


public class Transferencia extends Transaccion implements Reversible {
	
	//Atributos
	private String aliasCuentaDestino;
	private String nombreDeTransaccion;
	
	//Constructor
	public Transferencia(int monto, byte tipoCuentaOrigen, Cuenta cuentaOrigen, String aliasCuentaDestino) {
		// TODO Apéndice de constructor generado automáticamente
		super(monto,tipoCuentaOrigen, cuentaOrigen);
		this.aliasCuentaDestino = aliasCuentaDestino;
		this.nombreDeTransaccion = "Transferencia";
	}

	//Métodos
	public String getNombreDeTransaccion() {
		return nombreDeTransaccion;
	}

	@Override
	public void realizar() throws SaldoInsuficienteException, TransferenciaSoloEnPesosException {
		// TODO Apéndice de método generado automáticamente
		transferir(getMonto(), getTipoCuentaOrigen(), getCuentaOrigen(), aliasCuentaDestino);
	}

	private void transferir(int montoATransferir, byte tipoCuentaOrigen, Cuenta cuentaOrigen, String aliasCuentaDestino) throws SaldoInsuficienteException, TransferenciaSoloEnPesosException {
		Banco banco = new Banco();
		Cuenta cuentaDestino = banco.getCuentaPorAlias(aliasCuentaDestino);
		if(tipoCuentaOrigen != 3 && cuentaDestino.getTipoDeCuenta() != 3) {	
			if(comprobarSaldo(tipoCuentaOrigen, cuentaOrigen)){
				//compruebo si la cantidad de dinero que quiero transferir, lo tengo en mi cuenta
				//si es así transferio, busco la cuenta en el banco con alias 
				//cambio el saldo de la cuenta Destino
				double nuevoSaldoDestino = montoATransferir + cuentaDestino.getSaldo();
				cuentaDestino.setSaldo(nuevoSaldoDestino);
				//cambio saldo de la cuenta origen
				double nuevoSaldoOrigen = cuentaOrigen.getSaldo() - montoATransferir;
				cuentaOrigen.setSaldo(nuevoSaldoOrigen);		
				//Imprimo los archivos
				//Actualizar archivo movimientos
				Movimiento movimientoCuentaOrigen = new Movimiento("Transferencia", cuentaOrigen.getAlias(), montoATransferir);
				//Consulta de impresión
				GestorDeSolicitudes gestor = new GestorDeSolicitudes(new Pantalla(),new Teclado());
				if(gestor.solicitarConfirmacionDeTransferencia()){
					revertir(montoATransferir,tipoCuentaOrigen,cuentaOrigen,aliasCuentaDestino);
				}else{
					//imprimir
					Ticket ticketOrigen = new Ticket(tipoCuentaOrigen, nombreDeTransaccion, montoATransferir, nuevoSaldoOrigen);
					Impresora impresora = new Impresora();
					try {
						impresora.imprimirTicket(ticketOrigen);
					} catch (IOException e) {
						// TODO Bloque catch generado automáticamente
						e.printStackTrace();
					}
				}
			} else {
				throw new SaldoInsuficienteException("El saldo es insuficiente"); //Hacer bien una excepción			
			}
		}else {
			throw new TransferenciaSoloEnPesosException("Las transferencias solo se pueden hacer en pesos");
		}
	}

	@Override
	public boolean comprobarSaldo(byte tipoCuenta, Cuenta cuentaOrigen) {
		// TODO Apéndice de método generado automáticamente
		int montoATransferir = getMonto();	
		if(montoATransferir < cuentaOrigen.getSaldo()){
			return true;
		}
		return false;
	}

	@Override
	public void revertir(int montoATransferir, byte tipoCuentaOrigen, Cuenta cuentaOrigen, String aliasCuentaDestino) {
		//cambio el saldo de la cuenta Destino
		Banco banco = new Banco();
		Cuenta cuentaDestino = banco.getCuentaPorAlias(aliasCuentaDestino);
		double viejoSaldoDestino = cuentaDestino.getSaldo()-montoATransferir;
		cuentaDestino.setSaldo(viejoSaldoDestino);
		//cambio saldo de la cuenta origen
		double viejoSaldoOrigen = cuentaOrigen.getSaldo() + montoATransferir;
		cuentaOrigen.setSaldo(viejoSaldoOrigen);		
		//Imprimo los archivos
		//Actualizar archivo movimientos
		Movimiento movimientoCuentaOrigen = new Movimiento("Transferencia Revertida", cuentaOrigen.getAlias(), montoATransferir);
		//imprimir
		Ticket ticketOrigen = new Ticket(tipoCuentaOrigen, "Transferencia Revertida", montoATransferir, viejoSaldoOrigen);
		Impresora impresora = new Impresora();
		try {
			impresora.imprimirTicket(ticketOrigen);
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}		
		
	}
}
