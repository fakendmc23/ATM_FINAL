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
		// TODO Ap�ndice de m�todo generado autom�ticamente
		String alias = teclado.ingresoPorTeclado("Ingrese el alias de la cuenta de destino:");
		return alias;	
	}

	/* pre: 
	 * 
	 * post: Se solicita el tipo de cuenta
	 */	
	public byte solicitarTipoCuenta() {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		pantalla.mostrarMensaje("Cuentas disponibles para la operaci�n:");
		pantalla.mostrarMensaje("1: Caja de Ahorro | 2: Cuenta Corriente | 3: Caja de Ahorro USD");
		String tipoDeCuenta = teclado.ingresoPorTeclado("Ingrese la cuenta a operar:");
		return Byte.parseByte(tipoDeCuenta);
	}

	/* pre: monto positivo multiplo de 100
	 * 
	 * post: Se solicita el monto por teclado
	 */	
	public int solicitarMonto() {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		String monto = teclado.ingresoPorTeclado("Ingrese el monto de la operaci�n:");
		if((Integer.parseInt(monto) % 100) != 0){
			pantalla.mostrarMensaje("Por favor, ingrese un monto m�ltiplo de 100!!");
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
		// TODO Ap�ndice de m�todo generado autom�ticamente
		String pin = teclado.ingresoPorTeclado("Ingrese su pin:");
		return pin;
	}

	/* pre: 
	 * 
	 * post: Se solicita el c�digo de operaci�n por teclado
	 */	
	public String solicitarCodigoDeOperaci�n() {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		String codigoDeOperacion = teclado.ingresoPorTeclado("Ingrese el c�digo de operaci�n:");
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
	 * post: Se solicita confirmaci�n de la impresi�n de ticket. Solo imprime si  indica que si
	 */	
	public boolean solicitarConfirmacionDeImpresion() {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		String confirmacion = teclado.ingresoPorTeclado("Desea imprimir el ticket? Y/N");
		if(confirmacion.equals("Y") || confirmacion.equals("y")){
			return true;
		} else if (confirmacion.equals("N")|| confirmacion.equals("n")){
			pantalla.mostrarMensaje("El ticket no se imprimir�! Gracias por cuidar el medio ambiente!");
			return false;
		} else {
			pantalla.mostrarMensaje("El ticket no se imprimir�! Gracias por cuidar el medio ambiente!");
			return false;
		}
	}

	/* pre: 
	 * 
	 * post: Se solicita el numero de tarjeta
	 */	
	public String solicitarNumeroDeTarjeta() {
		String numeroDeTarjeta = teclado.ingresoPorTeclado("Ingrese su N�mero de Tarjeta:");
		if(numeroDeTarjeta.length() != 8){
			pantalla.mostrarMensaje("Por favor, ingresar un n�mero de tarjeta de longitud v�lida");
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
	 * post: Se solicita confirmaci�n de transferencia
	 *
	 */	
	public boolean solicitarConfirmacionDeTransferencia() {
		String confirmacion = teclado.ingresoPorTeclado("�Desea revertir la operaci�n? Y/N");
		if(confirmacion.equals("Y") || confirmacion.equals("y")){
			pantalla.mostrarMensaje("Se revirti� la operaci�n");
			return true;
		} else if (confirmacion.equals("N")|| confirmacion.equals("n")){
			pantalla.mostrarMensaje("Se realiz� la transferencia");
			return false;
		} else {
			pantalla.mostrarMensaje("Se realiz� la transferencia");
			return false;
		}
	}
	
	/* pre: 
	 * 
	 * post: Se solicita monto para depositar
	 */	

	public int solicitarMontoDeposito() {
		String montoDeposito = teclado.ingresoPorTeclado("Ingrese el monto de la operaci�n:");
		return Integer.parseInt(montoDeposito);
	}
}
