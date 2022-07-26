

import java.util.TreeSet;

public class BusquedaDelCuit {
	
	//Atributo
	private String cuit;
	
	//Constructor
	public BusquedaDelCuit(String cuit){
		this.cuit = cuit;
	}

	//Método
	
	/* pre: Necesita un array de cuits y el valor por buscar 
	 * 
	 * post: Devuelve un true si se encuentra el cuit deseeado en el array otorgado
	 */
	public boolean buscarCuit(Object[] array,String cuitUsuario){
		
		int inf = 0;
		int max = array.length - 1;
		int mid;
		long cuit = Long.parseLong(cuitUsuario);
		
		while(inf <= max){
			mid = inf + ((max-inf)/2);
			
			if(Long.parseLong((String)array[mid])== cuit){
				return true;
			} else if(Long.parseLong((String)array[mid]) < cuit){
				inf = mid + 1;
			} else{
				max = mid - 1;
			}
		}
		return false;
	}

}
