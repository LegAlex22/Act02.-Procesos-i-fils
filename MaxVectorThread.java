package Act5_Ex3_MaxVector;

// alternativa: extends Thread | Es la tarea (el trabajo que hará un hilo) “Esto es lo que el hilo hará cuando empiece”.
public class MaxVectorThread implements Runnable 
{
	private int id; //Numero del hilo en cuestión
	
	private int[] vec; //Vector completo recibido por Principal
	private int in; //Posicion inicial del rango
	private int fi; //Posicion final del rango
	private int maxHijo = 0; //Maximo encontrado por el hilo

	
	public MaxVectorThread(int param_i, int[] param_vector, int param_inicio, int param_fin) 
	{
		this.id = param_i;
		this.vec = param_vector;
		this.in = param_inicio;
		this.fi = param_fin;
	}
	
	public void run() //Runnable
	{
		System.out.println("Iniciant execució procés hilo " + id); //No tiene que ser por orden (1,2,3,4. puede ser 2,1,3,4)
		
		//Osea el valor de param_inicio que recibiremos, lo metemos en el atributo inicio, normalemtne se usa el tipico inicio[0] ya que es la 1r posicion, pero esta vez son aletarorios, por eso los atributos de inicio y fin.
		
		for(int i= in; i<= fi; i++) 
		{
			if (vec[i] > maxHijo) 
			{
				maxHijo = vec[i]; //Va comparando uno con otro y se actualizará si el que ha tocado esmas grande que el anterior / valor que tenga el atributo maxLocal en ese momento.
			}
		}
	}
	
	public int getResultado() 
	{
		return maxHijo;
	}
}
