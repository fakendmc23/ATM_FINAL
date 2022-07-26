public abstract class Transaccion   {
	
	//Atributo
	private int monto;
	private byte tipoCuentaOrigen;
	private Cuenta cuentaOrigen;
	
	//Constructor
	public Transaccion(int monto, byte tipoCuentaOrigen, Cuenta cuentaOrigen){
		this.monto = monto;
		this.tipoCuentaOrigen = tipoCuentaOrigen;
		this.cuentaOrigen = cuentaOrigen;
	}
	
	//Métodos	
	public abstract void realizar() throws SaldoInsuficienteException, TransferenciaSoloEnPesosException;

	public abstract boolean comprobarSaldo(byte tipoCuenta, Cuenta cuentaOrigen);
	
	public int getMonto(){
		return monto;
	}
	
	public byte getTipoCuentaOrigen(){
		return tipoCuentaOrigen;
	}
	
	public Cuenta getCuentaOrigen(){
		return cuentaOrigen;
	}
}
