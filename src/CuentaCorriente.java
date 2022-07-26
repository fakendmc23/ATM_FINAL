
public class CuentaCorriente extends Cuenta {
	
	// Atributos
	private int descubierto;
	
	// Métodos
	public CuentaCorriente(byte tipoDeCuenta, String alias, double saldo, int descubierto) {
		super(tipoDeCuenta, alias, saldo);
		this.descubierto = descubierto;
	}

	/**
	 * @return el descubierto
	 */
	public int getDescubierto() {
		return descubierto;
	}

}
