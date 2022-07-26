
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DepositoPruebas {
	
	private Banco banco;
	private Cuenta cuenta;
	
	@Before
	public void crearBanco(){
		banco = new Banco();
		cuenta = banco.getCuentaPorAlias("uva.sandalia.halcon");
		cuenta.setSaldo(10000);
	}
	
	@Test
	public void obtenerSaldoDeCuenta(){
		Assert.assertEquals(10000, cuenta.getSaldo(), 0.01);
	}
	
	@Test
	public void setearSaldoEnCuenta(){
		cuenta.setSaldo(12000);
		Assert.assertEquals(12000, cuenta.getSaldo(), 0.01);
	}
	
	@Test
	public void realizarDeposito(){
		cuenta.setSaldo(10000);
		Deposito deposito = new Deposito(333, cuenta.getTipoDeCuenta(), cuenta);
		deposito.realizar();
		Assert.assertEquals(10333.00, cuenta.getSaldo(), 0.01);
	}

}
