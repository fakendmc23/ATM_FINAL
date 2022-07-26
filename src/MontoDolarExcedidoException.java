

public class MontoDolarExcedidoException extends Exception {

	private String causa;
		
		public MontoDolarExcedidoException(String causa){
			this.causa = causa;
		}
		
		@Override
		public String toString(){
			return this.causa;
	}
}
