public class SaldoInsuficienteException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String causa;
	
	public SaldoInsuficienteException(String causa){
		this.causa = causa;
	}
	
	@Override
	public String toString(){
		return this.causa;
	}
}
