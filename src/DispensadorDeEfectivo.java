public class DispensadorDeEfectivo {
	
	//Atributos
	private int[] billetesPorEntregar;
	private int cantidadBilletesCienAtm;
	private int cantidadBilletesQuinientosAtm;
	private int cantidadBilletesMilAtm;
	int cantidadBilletesMilEntregar;
	int cantidadBilletesQuinientosEntregar;
	int cantidadBilletesCienEntregar;
	
	//Constructor
	public DispensadorDeEfectivo(int cantidadBilletesCienAtm, int cantidadBilletesQuinientosAtm, int cantidadBilletesMilAtm){
		this.billetesPorEntregar = new int[3];
		this.cantidadBilletesCienAtm = cantidadBilletesCienAtm;
		this.cantidadBilletesQuinientosAtm = cantidadBilletesQuinientosAtm;
		this.cantidadBilletesMilAtm = cantidadBilletesMilAtm;
		this.cantidadBilletesMilEntregar = 0;
		this.cantidadBilletesQuinientosEntregar = 0;
		this.cantidadBilletesCienEntregar = 0;
	}

	//Métodos
	

	/* pre: se le da un monto múltiplo de 100 positivo
	 * 
	 * post: devuelve un array con la cantidad de billetes que se entregaron
	 * billetes= [$100,$500,$1000]
	 */	
	public int[] entregarBilletes(int montoAEntregar) { //1800
		// TODO Apéndice de método generado automáticamente		
		billetesPorEntregar[2] = cantidadBilletesMil(montoAEntregar, cantidadBilletesMilEntregar); //1
		int restoSinMil = montoAEntregar - (billetesPorEntregar[2]*1000); //800
		billetesPorEntregar[1] = cantidadBilletesQuinientos(restoSinMil, cantidadBilletesQuinientosEntregar); //1 -300
		int restoSinQuinientos = restoSinMil - (billetesPorEntregar[1]*500); //800 - 500 : 300
		billetesPorEntregar[0] = cantidadBilletesCien(restoSinQuinientos, cantidadBilletesCienEntregar);
		
		actualizarBilletesAtm(billetesPorEntregar);
		return billetesPorEntregar;
	}


	/* pre: Se le da un array
	 * 
	 * post: Se actualiza los billetes del ATM
	 */	
	private void actualizarBilletesAtm(int[] billetesPorEntregar) {
		// TODO Apéndice de método generado automáticamente
		setCantidadBilletesMilAtm(getCantidadBilletesMilAtm() -billetesPorEntregar[2]);
		setCantidadBilletesQuinientosAtm(getCantidadBilletesQuinientosAtm() - billetesPorEntregar[1]);
		setCantidadBilletesCienAtm(getCantidadBilletesCienAtm() - billetesPorEntregar[0]);
	}
	
	/* pre: 
	 * 
	 * post: Se calcula la cantidad de billetes de mil mediante un método recursivo
	 */	
	private int cantidadBilletesMil(int monto, int cantidadBilletesMilEntregar){ //1800
		if(monto<1000){
			return cantidadBilletesMilEntregar; //1
		} else {
			cantidadBilletesMilEntregar = monto/1000;
			if(cantidadBilletesMilEntregar < getCantidadBilletesMilAtm() ){
				int nuevoMonto = monto - (cantidadBilletesMilEntregar*1000); 
				return cantidadBilletesMil(nuevoMonto, cantidadBilletesMilEntregar);
			} else {
				return cantidadBilletesQuinientos(monto,cantidadBilletesQuinientosEntregar);
			}
		}
	}
	
	/* pre: 
	 * 
	 * post: Se calcula la cantidad de billetes de quinientos mediante un método recursivo
	 */	
	private int cantidadBilletesQuinientos(int monto, int cantidadBilletesQuinientosEntregar){ //800
		if(monto<500){
			return cantidadBilletesQuinientosEntregar; //1
		} else {
			cantidadBilletesQuinientosEntregar = monto/500;
			if(cantidadBilletesQuinientosEntregar < getCantidadBilletesQuinientosAtm() ){
				int nuevoMonto = monto - (cantidadBilletesQuinientosEntregar*500);
				return cantidadBilletesQuinientos(nuevoMonto, cantidadBilletesQuinientosEntregar);
			} else {
				return cantidadBilletesCien(monto,cantidadBilletesCienEntregar);
			}
			
		}
	}
	
	/* pre: 
	 * 
	 * post: Se calcula la cantidad de billetes de cien mediante un método recursivo
	 */	
	private int cantidadBilletesCien(int monto, int cantidadBilletesCienEntregar){ //300
		if(monto<100){
			return cantidadBilletesCienEntregar;
		} else {
			cantidadBilletesCienEntregar = monto/100; 
			int nuevoMonto = monto - (cantidadBilletesCienEntregar*100);
			return cantidadBilletesCien(nuevoMonto, cantidadBilletesCienEntregar);
		}
	}

	
	//GETTER y SETTER
	public int getCantidadBilletesCienAtm() {
		return cantidadBilletesCienAtm;
	}

	public void setCantidadBilletesCienAtm(int cantidadBilletesCienAtm) {
		this.cantidadBilletesCienAtm = cantidadBilletesCienAtm;
	}

	public int getCantidadBilletesQuinientosAtm() {
		return cantidadBilletesQuinientosAtm;
	}

	public void setCantidadBilletesQuinientosAtm(int cantidadBilletesQuinientosAtm) {
		this.cantidadBilletesQuinientosAtm = cantidadBilletesQuinientosAtm;
	}

	public int getCantidadBilletesMilAtm() {
		return cantidadBilletesMilAtm;
	}

	public void setCantidadBilletesMilAtm(int cantidadBilletesMilAtm) {
		this.cantidadBilletesMilAtm = cantidadBilletesMilAtm;
	}


	
	
}
