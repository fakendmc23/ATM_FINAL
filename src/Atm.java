
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Atm {

	// Atributos
	private Pantalla pantalla;
	private Teclado teclado;
	private DispensadorDeEfectivo dispensador;
	private Transaccion transaccionEnCurso;
	private boolean atmIniciado;
	private GestorDeSolicitudes gestorDeSolicitudes;
	private String numeroDeTarjetaActual;

	// Constructor
	public Atm() {
		this.pantalla = new Pantalla();
		this.teclado = new Teclado();
		this.dispensador = new DispensadorDeEfectivo(500, 500, 500);
		this.atmIniciado = false;
		this.gestorDeSolicitudes = new GestorDeSolicitudes(pantalla, teclado);
	}

	// Métodos
	/* pre: Se considera que todas los usuarios tiene los tres tipos de cuentas posibles. 
	 * 
	 * post: Inicializa un ATM y deja mostrado un mensaje de bienvenida en la pantalla.
	 */
	public void iniciar() {
		this.atmIniciado = true;
		pantalla.mostrarMensaje("El cajero esta operativo. Por favor, ingrese su tarjeta seguido de la clave");
	}
	
	/* pre: Atm debe haber un ATM iniciado previamente
	 * 
	 * post: Finaliza la sesión del ATM y deja mostrado un mensaje de despedida en la pantalla.
	 */
	public void finalizar() {
		this.atmIniciado = false;
		pantalla.mostrarMensaje("Se ha finalizado la sesión. Gracias por utilizar el cajero.");
	}
	
	/* pre: Debe haber una sesióna activa en el ATM con un usuario perteneciente al banco
	 * 
	 * post: Una transacción esta en curso para ser completada
	 */
	public boolean realizarTransaccion(byte codigoOperacion)
			throws CodigoOperacionInvalidoException, SaldoInsuficienteException, MontoDolarExcedidoException, TransferenciaSoloEnPesosException {
		boolean estadoTransaccion;

		if (codigoOperacion > 0 && codigoOperacion < 5) {
			estadoTransaccion = true;
			asignarTipoDeTransaccion(codigoOperacion);
			if (transaccionEnCurso != null) {
				transaccionEnCurso.realizar();
			} else {
				pantalla.mostrarMensaje("Operación invalidada");
			}
		} else {
			throw new CodigoOperacionInvalidoException("Input", "Codigo de transacción inválido");
			// Se debería reintentar la operación realizar transacción.
		}

		return estadoTransaccion;
	}
	
	/* pre: Se asigno un tipo de transacción
	 * 
	 * post: Es elegido un tipo de transacción
	 */

	private void asignarTipoDeTransaccion(byte codigoOperacion) throws MontoDolarExcedidoException {
		Banco banco = new Banco();
		// ALIAS/CUENTAS DE TODO EL BANCO
		TreeMap<String, Cuenta> datosCuentas = banco.getDiccionarioDeCuentas();

		switch (codigoOperacion) {

		case 1:
			String aliasDeposito = gestorDeSolicitudes.solicitarAlias();
			if (datosCuentas.containsKey(aliasDeposito)) {
				transaccionEnCurso = new Deposito(gestorDeSolicitudes.solicitarMontoDeposito(),
						datosCuentas.get(aliasDeposito).getTipoDeCuenta(), datosCuentas.get(aliasDeposito));
				break;
			} else {
				pantalla.mostrarMensaje("No se encuentra ese alias");
			}

			// VER TEMA DEL PARÁMETRO DE RETIRO. COMO OBTENGO LA CUENTA DE ORIGEN
		case 2:
			String aliasRetiro = gestorDeSolicitudes.solicitarAlias();
			byte tipoDeCuenta = datosCuentas.get(aliasRetiro).getTipoDeCuenta();
			if (tipoDeCuenta == 3) {
				pantalla.mostrarMensaje("Solo re puede retirar efectivo de cuenta en pesos");
				break;
			} else {
				transaccionEnCurso = new Retiro(gestorDeSolicitudes.solicitarMonto(), tipoDeCuenta,
						datosCuentas.get(aliasRetiro), dispensador);
				break;
			}
		case 3:
			TreeMap<String, String> tarjetaYCuit = banco.getDatosTarjetaDeUsuariosCuit();
			String cuit = tarjetaYCuit.get(numeroDeTarjetaActual);
			TreeMap<String, List<Cuenta>> listaCuentaCuit = banco.getCuentasPorCuit();
			List<Cuenta> listaDeCuentas = listaCuentaCuit.get(cuit);
			ArrayList<String> aliasPesos = new ArrayList<String>();
			String aliasDolarValido = null;
			for (Cuenta cuenta : listaDeCuentas) {
				if (cuenta.getTipoDeCuenta() != 3) {
					aliasPesos.add(cuenta.getAlias());
				} else {
					aliasDolarValido = cuenta.getAlias();
				}
			}
			String aliasOrigenPesos = gestorDeSolicitudes.solicitarAliasDisponibles(aliasPesos);
			byte tipoDeCuentaPesos = datosCuentas.get(aliasOrigenPesos).getTipoDeCuenta();
			pantalla.mostrarMensaje("El alias válido de su cuenta en dolar es: " + aliasDolarValido);
			String aliasDolar = gestorDeSolicitudes.solicitarAlias();
			byte tipoDeCuentaDolar = datosCuentas.get(aliasDolar).getTipoDeCuenta();
			if (tipoDeCuentaPesos == 3) {
				pantalla.mostrarMensaje("Solo se puede comprar con cuentas en pesos");
				break;
			} else {
				transaccionEnCurso = new CompraDeDolares(gestorDeSolicitudes.solicitarMonto(), tipoDeCuentaPesos,
						datosCuentas.get(aliasOrigenPesos), tipoDeCuentaDolar, datosCuentas.get(aliasDolar));
				break;
			}
		case 4:
			TreeMap<String, String> tarjetaYCuitTransferencia = banco.getDatosTarjetaDeUsuariosCuit();
			String cuitTransferencia = tarjetaYCuitTransferencia.get(numeroDeTarjetaActual);
			TreeMap<String, List<Cuenta>> listaCuentaCuitTransferencia = banco.getCuentasPorCuit();
			List<Cuenta> listaDeCuentasTransferencia = listaCuentaCuitTransferencia.get(cuitTransferencia);
			ArrayList<String> aliasPesosTransferencia = new ArrayList<String>();

			for (Cuenta cuenta : listaDeCuentasTransferencia) {
				if (cuenta.getTipoDeCuenta() != 3) {
					aliasPesosTransferencia.add(cuenta.getAlias());
				}
			}
			String aliasOrigenPesosTransferencia = gestorDeSolicitudes
					.solicitarAliasDisponibles(aliasPesosTransferencia);
			byte tipoDeCuentaPesosTransferencia = datosCuentas.get(aliasOrigenPesosTransferencia).getTipoDeCuenta();
			transaccionEnCurso = new Transferencia(gestorDeSolicitudes.solicitarMonto(), tipoDeCuentaPesosTransferencia,
					datosCuentas.get(aliasOrigenPesosTransferencia), gestorDeSolicitudes.solicitarAlias());
			break;
		}
	}
	
	/* pre: Debe haber un ATM inizializado
	 * 
	 * post: Una sesión es inicializa y se le da al usuario la opción de elegir operación
	 */
	public void ingresoDeTarjeta() throws CuitInvalidoException, PinInvalidoException, SaldoInsuficienteException {
		if (atmIniciado) {
			int cantidadDeIntentosTarjeta = 0;
			String numeroTarjeta = gestorDeSolicitudes.solicitarNumeroDeTarjeta();
			this.numeroDeTarjetaActual = numeroTarjeta;
			Banco banco = new Banco();
			TreeMap<String, String> numeroDeTarjetas = banco.getDatosTarjetaDeUsuariosPin();
			if (numeroDeTarjetas.containsKey(numeroTarjeta)) {
				String pin = gestorDeSolicitudes.solicitarPin();
				if (numeroDeTarjetas.containsValue(pin)) {
					elegirOperacion();
				} else {
					pantalla.mostrarMensaje("El número de pin es invalido. Comience nuevamente");
					ingresoDeTarjeta();
				}
			} else {
				while (cantidadDeIntentosTarjeta < 3) {
					cantidadDeIntentosTarjeta++;
					pantalla.mostrarMensaje(
							"El número de tarjeta que ingresó no se encuentra en nuestra base de datos. Por favor ingrese de nuevo"); // debe
																																		// poder
																																		// repetir
																																		// acción
					ingresoDeTarjeta();
				}
			}
		}
	}
    // post: devuelve una pantalla
	public Pantalla getPantalla() {
		return pantalla;
	}

	// post: devuelve un teclado
	public Teclado getTeclado() {
		return teclado;
	}
	
	/* Pre: debe haber un ATM inicializado
	 * 
	 * Post: muestra en la pantalla las operaciones que puede realizar el usuario
	 */

	public void elegirOperacion() throws SaldoInsuficienteException {
		pantalla.mostrarMensaje("¡Bienvenido!");
		pantalla.mostrarMensaje("¿Qué operación desea realizar?");
		pantalla.mostrarMensaje("1: Depositar | 2: Retirar | 3: Comprar Dólares | 4: Transferir");
		String codigoDeOperacion = gestorDeSolicitudes.solicitarCodigoDeOperación();
		try {
			realizarTransaccion(Byte.parseByte(codigoDeOperacion));
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (CodigoOperacionInvalidoException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
