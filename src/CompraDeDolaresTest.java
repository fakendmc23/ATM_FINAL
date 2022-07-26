import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

public class CompraDeDolaresTest {

	private Banco banco;
	private Cuenta cuentaPrueba;
	private Cuenta cuentaAux;
	private CompraDeDolares compraDeDolaresClase;
	

	@Before
	public void beforeTests() throws MontoDolarExcedidoException {
		this.banco = new Banco();
		this.cuentaPrueba = banco.getCuentaPorAlias("casa.patio.globo");
		this.cuentaAux = banco.getCuentaPorAlias("pata.balde.papa");
		this.compraDeDolaresClase = new CompraDeDolares(150, (byte) 02, cuentaPrueba, (byte) 03,banco.getCuentaPorAlias("pata.balde.papa"));
	}

	@Test
	public void crearClase() throws MontoDolarExcedidoException {
		new CompraDeDolares(150, (byte) 02, cuentaPrueba, (byte) 03,banco.getCuentaPorAlias("pata.balde.papa"));	}

	@Test
	public void compraExitosa() throws MontoDolarExcedidoException ,SaldoInsuficienteException {
		this.cuentaPrueba.setSaldo(100000.0);
		this.cuentaAux.setSaldo(0);
		this.compraDeDolaresClase.realizar();
		Assert.assertEquals(150.0, cuentaAux.getSaldo(), 0.1);
	}

	@Test
	public void nombreDeTransaccion() {
		Assert.assertEquals(this.compraDeDolaresClase.getNombreDeTransaccion(), "Compra De Dolares");
	}

	@Test(expected = SaldoInsuficienteException.class)
	public void saldoInsuficiente() throws MontoDolarExcedidoException, SaldoInsuficienteException {
		this.banco = new Banco();
		this.cuentaPrueba.setSaldo(1.0);
		new CompraDeDolares(150, this.cuentaPrueba.getTipoDeCuenta(), this.cuentaPrueba, this.cuentaAux.getTipoDeCuenta(),
				this.cuentaAux).realizar();
	}

	@Test(expected = MontoDolarExcedidoException.class)
	public void elMontoNoPuedeSerMayorA200() throws MontoDolarExcedidoException {
		this.banco = new Banco();
		new CompraDeDolares(250, (byte) 02, banco.getCuentaPorAlias("casa.patio.globo"), (byte) 03,
				banco.getCuentaPorAlias("pata.balde.papa"));
	}
}
