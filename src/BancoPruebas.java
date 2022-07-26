
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BancoPruebas {
	
	private Banco banco;
	private TreeMap<String, Cuenta> cuentasDelBanco;
	
	@Before
	public void crearBanco(){
		banco = new Banco();
		// Seteo el saldo de todas las cuentas en 10000
		cuentasDelBanco = banco.getDiccionarioDeCuentas();
		Collection<Cuenta> cuentas = cuentasDelBanco.values();
		for(Cuenta cuenta: cuentas){
			cuenta.setSaldo(10000);
		}
	}
	
	@Test
	public void accederPorAliasAlSaldo(){
		Cuenta cuenta = cuentasDelBanco.get("uva.sandalia.halcon");
		Assert.assertEquals(10000, cuenta.getSaldo(), 0.01);
	}
	
	@Test
	public void pruebaContieneCuitsDeUsuarios(){
		TreeSet<String> cuitDeUsuarios = banco.getCuitDeUsuarios();
		Assert.assertEquals(true, cuitDeUsuarios.contains("27102551236"));
		Assert.assertEquals(true, cuitDeUsuarios.contains("20311573951"));
		Assert.assertEquals(true, cuitDeUsuarios.contains("23044303094"));
	}

	@Test
	public void prueba(){
		TreeMap<String, List<Cuenta>> cuentasPorCuit = banco.getCuentasPorCuit();
		List<Cuenta> cuentasDe27102551236 = cuentasPorCuit.get("27102551236");
		List<String> aliases = new ArrayList<String>();
		for( Cuenta cuenta : cuentasDe27102551236){
			aliases.add(cuenta.getAlias());
		}
		Assert.assertEquals(true,aliases.contains("isla.pez.arbol"));
		Assert.assertEquals(true,aliases.contains("sol.monte.valle"));
		Assert.assertEquals(true,aliases.contains("pata.balde.papa"));
	}
}
