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

	//M�todos
	public Cuenta[] getCuentas() {
		return cuentas;
	}

	public String getPin() {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return pin;
	}


}
