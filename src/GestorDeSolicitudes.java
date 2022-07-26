import java.util.ArrayList;


public class GestorDeSolicitudes {
	
	//Atributos
	private Teclado teclado;
	private Pantalla pantalla;
	
	//Constructor
	public GestorDeSolicitudes(Pantalla pantalla, Teclado teclado){
		this.pantalla = pantalla;
		this.teclado = teclado;
	}

	/* pre: 
	 * 
	 * post: Se solicita el alias por teclado
	 */	
	public String solicitarAlias() {
		// TODO Apéndice de método generado automáticamente
		String alias = teclado.ingresoPorTeclado("Ingrese el alias de la cuenta de destino:");
		return alias;	
	}

	/* pre: 
	 * 
	 * post: Se solicita el tipo de cuenta
	 */	
	public byte solicitarTipoCuenta() {
		// TODO Apéndice de método generado automáticamente
		pantalla.mostrarMensaje("Cuentas disponibles para la operación:");
		pantalla.mostrarMensaje("1: Caja de Ahorro | 2: Cuenta Corriente | 3: Caja de Ahorro USD");
		String tipoDeCuenta = teclado.ingresoPorTeclado("Ingrese la cuenta a operar:");
		return Byte.parseByte(tipoDeCuenta);
	}

	/* pre: monto positivo multiplo de 100
	 * 
	 * post: Se solicita el monto por teclado
	 */	
	public int solicitarMonto() {
		// TODO Apéndice de método generado automáticamente
		String monto = teclado.ingresoPorTeclado("Ingrese el monto de la operación:");
		if((Integer.parseInt(monto) % 100) != 0){
			pantalla.mostrarMensaje("Por favor, ingrese un monto múltiplo de 100!!");
			return solicitarMonto();
		} else{
			return Integer.parseInt(monto);
		}
	}
	
	/* pre: 
	 * 
	 * post: Se solicita el pin por teclado
	 */	
	public String solicitarPin() {
		// TODO Apéndice de método generado automáticamente
		String pin = teclado.ingresoPorTeclado("Ingrese su pin:");
		return pin;
	}

	/* pre: 
	 * 
	 * post: Se solicita el código de operación por teclado
	 */	
	public String solicitarCodigoDeOperación() {
		// TODO Apéndice de método generado automáticamente
		String codigoDeOperacion = teclado.ingresoPorTeclado("Ingrese el código de operación:");
		return codigoDeOperacion;
		
	}
	
	/* pre: 
	 * 
	 * post: Se solicita el cuit por teclado
	 */	
	public String solicitarCuit(){
		String cuit = teclado.ingresoPorTeclado("Ingrese su CUIT:");
		return cuit;
	}

	/* pre: 
	 * 
	 * post: Se solicita confirmación de la impresión de ticket. Solo imprime si  indica que si
	 */	
	public boolean solicitarConfirmacionDeImpresion() {
		// TODO Apéndice de método generado automáticamente
		String confirmacion = teclado.ingresoPorTeclado("Desea imprimir el ticket? Y/N");
		if(confirmacion.equals("Y") || confirmacion.equals("y")){
			return true;
		} else if (confirmacion.equals("N")|| confirmacion.equals("n")){
			pantalla.mostrarMensaje("El ticket no se imprimirá! Gracias por cuidar el medio ambiente!");
			return false;
		} else {
			pantalla.mostrarMensaje("El ticket no se imprimirá! Gracias por cuidar el medio ambiente!");
			return false;
		}
	}

	/* pre: 
	 * 
	 * post: Se solicita el numero de tarjeta
	 */	
	public String solicitarNumeroDeTarjeta() {
		String numeroDeTarjeta = teclado.ingresoPorTeclado("Ingrese su Número de Tarjeta:");
		if(numeroDeTarjeta.length() != 8){
			pantalla.mostrarMensaje("Por favor, ingresar un número de tarjeta de longitud válida");
			return solicitarNumeroDeTarjeta();
		} else{
		return numeroDeTarjeta;
		}
	}

	/* pre: 
	 * 
	 * post: Se solicita el alias de cuenta origen por teclado
	 */	
	public String solicitarAliasOrigen() {
		String aliasOrigen = teclado.ingresoPorTeclado("Ingrese el alias de la cuenta de origen:");
		return aliasOrigen;	
	}

	/* pre: 
	 * 
	 * post: Se muestra por mensajes los alias disponibles y se solicita que ingrese uno
	 */	
	public String solicitarAliasDisponibles(ArrayList<String> aliasPesos) {
		pantalla.mostrarMensaje("Alias 1: " + aliasPesos.get(0) + "| Alias 2: " + aliasPesos.get(1));
		String aliasOrigen = teclado.ingresoPorTeclado("Escriba el alias de la cuenta de origen que esta disponible: ");
		return aliasOrigen;	
	}	
	
	/* pre: 
	 * 
	 * post: Se solicita confirmación de transferencia
	 *
	 */	
	public boolean solicitarConfirmacionDeTransferencia() {
		String confirmacion = teclado.ingresoPorTeclado("¿Desea revertir la operación? Y/N");
		if(confirmacion.equals("Y") || confirmacion.equals("y")){
			pantalla.mostrarMensaje("Se revirtió la operación");
			return true;
		} else if (confirmacion.equals("N")|| confirmacion.equals("n")){
			pantalla.mostrarMensaje("Se realizó la transferencia");
			return false;
		} else {
			pantalla.mostrarMensaje("Se realizó la transferencia");
			return false;
		}
	}
	
	/* pre: 
	 * 
	 * post: Se solicita monto para depositar
	 */	

	public int solicitarMontoDeposito() {
		String montoDeposito = teclado.ingresoPorTeclado("Ingrese el monto de la operación:");
		return Integer.parseInt(montoDeposito);
	}
}
