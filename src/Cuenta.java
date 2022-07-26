


public abstract class Cuenta {

	// Atributos
	private byte tipoDeCuenta;
	private String alias;
	private double saldo;
	
	//Constructor
	public Cuenta(byte tipoDeCuenta, String alias, double saldo) {
		this.tipoDeCuenta = tipoDeCuenta;
		this.alias = alias;
		this.saldo = saldo;
	}

	// Métodos
	public byte getTipoDeCuenta() {
		return tipoDeCuenta;
	}
	
	/* pre: 
	 * 
	 * post: retorna el alias
	 */	
	public String getAlias() {
		return this.alias;
	}
	
	/* pre: 
	 * 
	 * post: retorna el saldo 
	 */	
	public double getSaldo() {
		return this.saldo;
	}

	/* pre: 
	 * 
	 * post: setea el saldo de una cuenta
	 */	
	public void setSaldo(double saldo) {
		// TODO Apéndice de método generado automáticamente
		this.saldo = saldo;
		Banco banco = new Banco();
		banco.notificarNuevoSaldo(this);
	}
	
	
}
