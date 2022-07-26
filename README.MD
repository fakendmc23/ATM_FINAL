Integrantes: Alan Diaz Pierri, Eduardo Reichel, Nicol�s Racedo, Facundo Flores

Decisiones de dise�o tomadas:
1. Consideramos que todos los clientes del banco tienen las tres tipos de cuentas 
2. Solo se pueden retirar montos m�ltiplos de 100

Descripcion de los archivos ".java":

ATM.java: Contiene constructor de maquina automatica, junto a todos sus atributos.

Banco.java: Contiene constructor de banco donde se encuentran los archivos de cuentas, clientes, validaci�n de tarjetas, etc. Tambi�n aqui se crean las cuentas que utilizaran los clientes.

BusquedaDelCuit.java: Busca el cuit de los usuarios en fomra binaria.

CajaDeAhorro: Clase abstracta heredada de cuenta, padre de las cajas de ahorro.

CajaDeAhorroEnDolares: Contiene constructor de la caja de ahorro en dolares.

CajaDeAhorroEnPesos: Contiene constructor de la caja de ahorro en pesos.

CajeroMain.java: inicializa el cajero atuomatico.

CodigoOperacionInvalidoEception.java: Excepcion para operaciones invalidas.

CompraDeDolares.java: Clase hija de "Transaccion.java", contiene los metodos para compra de dolares.

Cuenta.java: Contiene contstructor de cuenta, con atributos tipoDeCuenta, alias y saldo del usuario.

CuentaCorriente.java: Clase heredada de Cuenta, contiene el constructor de cuenta corriente.

CuitInvalidoException.java: Excepcion al ingresar cuit invalido.

Deposito.java: contiene constructor de deposito, y m�todo depositar que lo termina realizando la clase Transaccion. Deposita dinero en la cuenta seleccionada.

DispensadorDeEfectivo.java: Contiene constructor del dispensador de billetes, entrega la cantidad de billetes necesarias segun su tipo y la transacci�n realizada.

GestorDeSolicitudes.java: gestiona todas las solicitudes que le hace el cajero automatico al usurio como numero de pin, alias, etc.

Impresora.java: Contiene metodos que imprimen los ticket segun el tipo de transacci�n que haya realizado el usuario.

LectorDeArvhios.java: lee los archivos .txt que utiliza el banco para acceder a los datos de los clientes y movimientos de los mismos.

MontoDolarExcedidoException.java: excecion al ingresar un monto mayor al que se tiene en la cuenta.

Movimiento.java: Contiene constructor de movimiento en donde se guardan datos de transaccion realizada por un usuario, y las guarda en un archivo txt.

Pantalla.java: contiene metodo que muestra lo que hay en la pantalla del ATM.

PinInvalidoException.java: excecion al usuario al ingresar un pin invalido.

Retiro.java: contiene constructor de Retiro, y m�todo retirar que lo termina realizando la clase Transaccion. Retira dinero de la cuenta seleccionada.

Reversible.java: interfaz para revertir las transacciones reversibles (compra de dolares y transferencia).

SaldoInsuficienteException.java: excepcion al no tener el saldo suficiente.

TarjetaDeDebito.java: contiene construjtor de tarjeta de debito. Contiene pin, cuentas y numero de tarjeta del usuario.

Teclado.java: Contiene el constructor del teclado del ATM, se utiliza para que el usuario pueda ingresar los datos.

Ticket.java: Contiene constructor de ticket donde se guardan los datos de algun movimiento realizado por el usuario.

Transaccion.java: clase abastracta base para los distintos tipos de transacciones, contiene su constructor y el metodo realizar que acciona la transacci�n.

Transferencia.java: contiene constructor de Transferencia, y m�todo transferir que lo termina realizando la clase Transaccion. Transfiere dinero de una cuenta a otra seleccionadas por el usuario.

Usuario.java: Contiene constructor de Usuario al que se le asignan un cuit y las tarjetas que utilice el usuario.


Conclusiones: 
En el presente trabajo se dise�o, cre� e implement� una versi�n de un cajero autom�tico en el lenguaje de programaci�n JAVA para la materia Algoritmos y Programaci�n II con el paradigma de programaci�n orientado a objetos. 
En una primera instancia, se sentaron las bases del funcionamiento de un cajero autom�tico, pensando en las distintas funcionalidades que ofrece (depositar, retirar, comprar dolares y transferir) y los distintos objetos que componen al mismo (pantalla, teclado, dispensador de billetes, entre otros). Luego de sentar estas bases, se cre� el dise�o UML el cual nos facilit� el desarrollo en Java, dado que todo el esquema estaba armado. A�n as�, sobre la marcha del proyecto nos dimos cuentas que en el dise�o no habiamos tenido muchas consideraciones que tuvieron que ser reemplazadas o modificadas de las originales.
Observamos la importancia de armar un diagrama de clases correctamente estructurado, que luego permita agilizar el proceso de desarrollo del c�digo. Se ha logrado desarrollar una terminal de cajero autom�tico en la cual los usuarios pueden retirar, depositar y transferir efectivo con su tarjeta de debito asociada. Los mismos podr�n recibir un ticket con el movimiento realizado si as� lo desean.  




