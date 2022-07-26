public class Usuario {
	
	private TarjetaDeDebito[] tarjetas;
	private String cuit;
	
	public Usuario(String cuit, TarjetaDeDebito[] tarjetas){
		this.cuit = cuit;
		this.tarjetas = tarjetas;
	}
	
	public String getCuit(){
		return this.cuit;
	}
	
	public TarjetaDeDebito[] getTarjeta(){
		return this.tarjetas;
	}

	public void setTarjetas(TarjetaDeDebito[] tarjetas) {
		this.tarjetas = tarjetas;
	}

}
