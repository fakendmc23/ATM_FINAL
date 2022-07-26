

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;


public class Banco {
	
	//Atributos

	private String archivoCuentas;
	private String archivoClientes;
	private String archivoValidacionDeTarjetas;
	private double cotizacionDelDolarDelDia;
	private TreeMap<String, Cuenta> cuentas;

	//Constructor
	
	public Banco() {
		this.archivoCuentas = "ArchivoCuentas.txt";
		this.archivoClientes = "ArchivoClientes.txt";
		this.archivoValidacionDeTarjetas = "ArchivoValidacionDeTarjetas.txt";
		this.cotizacionDelDolarDelDia = 51.03;
		this.cuentas = getDiccionarioDeCuentas();
	}
	
	//Métodos
	
	
	/* pre: Debe haber un archivo de Clientes 
	 * 
	 * post: Crea un TreeMap que contiene todas las cuenta por cada cuit
	 */
	public TreeMap<String, List<Cuenta>> getCuentasPorCuit() {
		// TODO Apéndice de método generado automáticamente
		TreeMap<String, List<Cuenta>> cuentasPorCuit = new TreeMap<String, List<Cuenta>>();
		for(String cuit: getCuitDeUsuarios()){
			cuentasPorCuit.put(cuit, new ArrayList<>());
		}
		LectorDeArchivos lector = new LectorDeArchivos(archivoClientes);
		String[] valoresDeLaLinea = lector.leerLinea();
		while(valoresDeLaLinea != null){
			String cuit = valoresDeLaLinea[0];
			String alias = valoresDeLaLinea[1];
			Cuenta cuenta  = this.cuentas.get(alias);
			cuentasPorCuit.get(cuit).add(cuenta);
			valoresDeLaLinea = lector.leerLinea();
		}	
		return cuentasPorCuit;
	}

	/* pre: 
	 * 
	 * post: Crea un TreeMap que contiene las datos de cuenta de cada usuario
	 */
	public TreeMap<String, Cuenta> getDiccionarioDeCuentas(){
		LectorDeArchivos lector = new LectorDeArchivos(archivoCuentas);
		String[] valoresDeLaLinea = lector.leerLinea();
		TreeMap<String, Cuenta> cuentas = new TreeMap<>();
		while(valoresDeLaLinea != null){
			Cuenta cuenta = crearObjetoCuenta(valoresDeLaLinea);
			String alias = valoresDeLaLinea[1];
			cuentas.put(alias, cuenta);
			valoresDeLaLinea = lector.leerLinea();
		}
		return cuentas;
	}
	
	/* pre: 
	 * 
	 * post: Crea una cuenta dependiendo del tipo que sea
	 */
	private Cuenta crearObjetoCuenta(String[] valoresDeLaLinea) {
		// TODO Apéndice de método generado automáticamente
		int tipoDeCuenta = Integer.parseInt(valoresDeLaLinea[0]);
		
		double saldo = Double.parseDouble(valoresDeLaLinea[2]);
	
		if(tipoDeCuenta == 1){
			Cuenta cuentaUnos = new CajaDeAhorro((byte)tipoDeCuenta, valoresDeLaLinea[1], saldo);
			return cuentaUnos;
		} else if(tipoDeCuenta == 2){
			int descubierto = (int) Double.parseDouble(valoresDeLaLinea[3]);
			Cuenta cuentaDos = new CuentaCorriente((byte)tipoDeCuenta, valoresDeLaLinea[1], saldo, descubierto);
			return cuentaDos;
		} else if(tipoDeCuenta == 3){
			Cuenta cuentaTres = new CajaDeAhorroEnDolares((byte)tipoDeCuenta, valoresDeLaLinea[1], saldo);
			return cuentaTres;
		} else {
			return null;
		}
	}
	
	/* pre: Necesita un monto positivo
	 * 
	 * post: Calcula el impuesto pais
	 */
	public double impuestoPais(double montoDeCompra){
		return montoDeCompra*1.3;
	}
	
	/* pre: 
	 * 
	 * post: retorna la cotización del dolar del día
	 */
	public double getCotizacionDelDolarDelDia() {
		return cotizacionDelDolarDelDia;
	}

	/* pre: 
	 * 
	 * post: Setea la cotización del dolar del dia 
	 */
	public void setCotizacionDelDolarDelDia(double cotizacionDelDolarDelDia) {
		this.cotizacionDelDolarDelDia = cotizacionDelDolarDelDia;
	}

	/* pre: Debe existir la cuenta
	 * 
	 * post: notifica el saldo de una cuenta
	 */
	public void notificarNuevoSaldo(Cuenta cuenta) {
		// TODO Apéndice de método generado automáticamente
		String alias = cuenta.getAlias();
		cuentas.replace(alias, cuenta);
		actualizarArchivoCuentas();
	}

	/* pre: 
	 * 
	 * post: luego de realizar una operación, se actualizan los datos del archivo de cuentas
	 */
	private void actualizarArchivoCuentas() {
		// TODO Apéndice de método generado automáticamente
		// crea el flujo para escribir en el archivo
		try (FileWriter flwriter = new FileWriter("ArchivoCuentas.txt")) {
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			for(Cuenta cuenta: cuentas.values()){
				if(cuenta.getTipoDeCuenta() == 2){
					CuentaCorriente cuentaCorriente = (CuentaCorriente) cuenta;
					bfwriter.write("0" + cuentaCorriente.getTipoDeCuenta() + "," + cuentaCorriente.getAlias() + "," + cuentaCorriente.getSaldo()+ "," + cuentaCorriente.getDescubierto());
					bfwriter.newLine();
				}else{
					bfwriter.write("0" + cuenta.getTipoDeCuenta() + "," + cuenta.getAlias() + "," + cuenta.getSaldo());
					bfwriter.newLine();
				}
			}
			bfwriter.close();
			System.out.println("Archivo cuentas actualizado satisfactoriamente...");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* pre: 
	 * 
	 * post: 
	 */
	public Cuenta getCuentaPorAlias(String alias){
		return cuentas.get(alias);
	}
	
	/* pre: 
	 * 
	 * post: Creaun TreeSet que contiene los cuits de cada usuario
	 */
	public TreeSet<String> getCuitDeUsuarios(){
		TreeSet<String> cuits = new TreeSet<>();
		LectorDeArchivos lector = new LectorDeArchivos(archivoClientes);
		String[] valoresDeLaLinea = lector.leerLinea();
		while(valoresDeLaLinea != null){
			String cuit = valoresDeLaLinea[0];
			cuits.add(cuit);
			valoresDeLaLinea = lector.leerLinea();
		}
		return cuits;
	}

	/* pre: 
	 * 
	 * post: Crea un mapa con el númeor de tarjeta y el pin
	 */
	public TreeMap<String, String> getDatosTarjetaDeUsuariosPin() {
		TreeMap<String, String> datosUsuarioTarjeta = new TreeMap<>();		
		LectorDeArchivos lector = new LectorDeArchivos(archivoValidacionDeTarjetas);
		String[] valoresDeLaLinea = lector.leerLinea();
		while(valoresDeLaLinea != null){
			String numeroDeTarjeta = valoresDeLaLinea[0];
			String pin = valoresDeLaLinea[1];
			datosUsuarioTarjeta.put(numeroDeTarjeta,pin);
			valoresDeLaLinea = lector.leerLinea();
		}
		return datosUsuarioTarjeta;
	}
	
	/* pre: 
	 * 
	 * post: Crea un mapa con el número de tarjeta y el cuit
	 */
	public TreeMap<String, String> getDatosTarjetaDeUsuariosCuit() {
		TreeMap<String, String> datosUsuarioTarjeta = new TreeMap<>();		
		LectorDeArchivos lector = new LectorDeArchivos(archivoValidacionDeTarjetas);
		String[] valoresDeLaLinea = lector.leerLinea();
		while(valoresDeLaLinea != null){
			String numeroDeTarjeta = valoresDeLaLinea[0];
			String cuit = valoresDeLaLinea[2];
			datosUsuarioTarjeta.put(numeroDeTarjeta,cuit);
			valoresDeLaLinea = lector.leerLinea();
		}
		return datosUsuarioTarjeta;
	}
}
