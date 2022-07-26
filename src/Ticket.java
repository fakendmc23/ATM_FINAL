public class Ticket {
	
	private String fechaYHora;
	private byte cuenta;
	private String tipoDeTransaccion;
	private double importe;
	private double nuevoSaldo;
	
	public Ticket( byte cuenta,
		String tipoDeTransaccion, double importe, double nuevoSaldo) {
		this.fechaYHora = calcularFechaYHora();
		this.cuenta = cuenta;
		this.tipoDeTransaccion = tipoDeTransaccion;
		this.importe = importe;
		this.nuevoSaldo = nuevoSaldo;
	}

	private String calcularFechaYHora() {
		// TODO Apéndice de método generado automáticamente
		return java.time.LocalDate.now().toString();
	}

	/**
	 * @return el fecha
	 */
	public String getFecha() {
		return fechaYHora;
	}



	/**
	 * @return el cuenta
	 */
	public byte getCuenta() {
		return cuenta;
	}

	/**
	 * @return el tipoDeTransaccion
	 */
	public String getTipoDeTransaccion() {
		return tipoDeTransaccion;
	}

	/**
	 * @return el importe
	 */
	public double getImporte() {
		return importe;
	}

	/**
	 * @return el nuevoSaldo
	 */
	public double getNuevoSaldo() {
		return nuevoSaldo;
	}
	
	

}
