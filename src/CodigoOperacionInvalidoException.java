
public class CodigoOperacionInvalidoException extends Exception {
	
	private String tipo;
	private String causa;
	
	public CodigoOperacionInvalidoException(String tipo, String causa){
		this.tipo = tipo;
		this.causa = causa;
	}
	
	/* pre:  
	 * 
	 * post: Se sobreescribe el m�todo tooString. Devuelve un mensaje para un error espec�fico
	 */
	@Override
	public String toString(){
		return "Error de " + this.tipo + ". Causado por: " + this.causa;
	}
}
