public class TarjetaDeDebito {
	
	//Atributos
	private String numeroDeTarjeta;
	private String pin;
	private Cuenta[] cuentas;

	//Constructor
	public TarjetaDeDebito(String numeroDeTarjeta, String pin, Cuenta[] cuentas) {
		this.numeroDeTarjeta = numeroDeTarjeta;
		this.pin = pin;
		this.cuentas = cuentas;
	}

	//Métodos
	public Cuenta[] getCuentas() {
		return cuentas;
	}

	public String getPin() {
		// TODO Apéndice de método generado automáticamente
		return pin;
	}


}
