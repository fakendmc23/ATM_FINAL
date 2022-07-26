import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransferenciaTest {

    private Banco banco;
    private Cuenta cuenta;
    private Cuenta cuenta2;

    @Before
    public void crearBanco(){
        banco = new Banco();
        cuenta = banco.getCuentaPorAlias("uva.sandalia.halcon");
        cuenta2 = banco.getCuentaPorAlias("isla.pez.arbol");
    }

    @Test
    public void obtenerSaldoDeCuenta(){
    	cuenta.setSaldo(10000);
        cuenta2.setSaldo(30000);
        Assert.assertEquals(10000, cuenta.getSaldo(), 0.01);
        Assert.assertEquals(30000, cuenta2.getSaldo(), 0.01);
    }

    @Test
    public void realizarTransferencia() throws SaldoInsuficienteException, TransferenciaSoloEnPesosException{
    	cuenta.setSaldo(10000);
        cuenta2.setSaldo(30000);
        Transferencia transferencia = new Transferencia(500, cuenta.getTipoDeCuenta(), cuenta,"isla.pez.arbol");
        transferencia.realizar();
        Assert.assertEquals(9500.00, cuenta.getSaldo(), 0.01);
    }
    
    public void realizarTransferenciaYchequearDestino() throws SaldoInsuficienteException, TransferenciaSoloEnPesosException{
    	cuenta.setSaldo(10000);
        cuenta2.setSaldo(30000);
        Transferencia transferencia2 = new Transferencia(500, cuenta.getTipoDeCuenta(), cuenta,"isla.pez.arbol");
        transferencia2.realizar();
        Assert.assertEquals(30500.00, cuenta2.getSaldo(), 0.01);
    }


}