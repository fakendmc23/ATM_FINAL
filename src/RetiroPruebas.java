

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

public class RetiroPruebas {
	
	//Atributos
	private int monto;
	private double saldo;
	private byte tipoCuentaARetirar;
	private Cuenta cuentaOrigen;
	private DispensadorDeEfectivo dispensador;
	
	@Before
	public void antes(){
		monto = 1800;
		saldo = 50000;
		tipoCuentaARetirar = 1;
		cuentaOrigen = new CajaDeAhorro(tipoCuentaARetirar,"isla.pez.arbol",saldo);
		dispensador = new DispensadorDeEfectivo(500,500,500);	
	}
	
	@Test
	public void creaClaseRetiro(){
		new Retiro(monto,tipoCuentaARetirar,cuentaOrigen,dispensador);
	}
	
	@Test
	public void metodoRealizar() throws SaldoInsuficienteException{
		Retiro retiro = new Retiro(monto,tipoCuentaARetirar,cuentaOrigen,dispensador);
		retiro.realizar();
	}
	
	@Test
	public void comprobarNuevoSaldoEnCuenta () throws SaldoInsuficienteException {
		Retiro retiro1 = new Retiro(monto,tipoCuentaARetirar,cuentaOrigen,dispensador);
		retiro1.realizar();
		double nuevoSaldo = 48200;
		Assert.assertEquals(nuevoSaldo, cuentaOrigen.getSaldo(),0.01);
	}
	
}
