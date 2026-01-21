package Act5_Ex2_Fibonacci;

// alternativa: extends Thread | Es la tarea (el trabajo que hará un hilo) “Esto es lo que el hilo hará cuando empiece”.
public class FibonacciThread implements Runnable 
{
	private int id; //Numero del hilo en cuestión
	private int resultado; //Calculo del fibonacci
	
	int fibAntes = 0;
	int fibAhora = 1;
	
	public FibonacciThread(int param_i) 
	{
		this.id = param_i;
	}
	
	public void run() //Runnable
	{
		System.out.println("Iniciant execució procés hilo " + id);
		
		// Casos simples de Fibonacci
		if (id == 0) 
		{
			resultado = 0;
			return;
		}
		if (id == 1) 
		{
			resultado = 1;
			return;
		}
		
		
		//RECUERDA: F(n) = F(n-1) + F(n-2)
		//El n del F(n-1) no es el n del inicio F(n), la idea es pq va por filas entonces eso te indica la fila y tu lo que coges es el valor de la fila. Hago un ejemplo
		
		//"FILAS"
		//F(0) = 0
		//F(1) = 1
		//F(2) = 1 -> 0 + 1
		//F(3) = 2 -> 1 + 1
		//F(4) = 3 -> 1 + 2
		//F(5) = 5 -> 2 + 3
		//F(6) = 8 -> 3 + 5
		
		//Si quisieramos saber el F(7) deberiamos de buscar los valores de las filas de la operación, si es F(n-1) + F(n-2) es F(7-1) + F(7-2) que da F(6) + F(5), 
		//pero eso no son los valores, los valores estan dentro, entonces tenemos que recogerlos de esas filas, que hay en el F(6)? Un 8, y en F(5)? Un 5. Pues será F(7) = 8 + 5. 
		//Como resultado F(7) = 13
		//Operación: F(7) = F(7-1) + F(7-2) === F(7) = F(6) + F(5)  === F(7) = 8 + 5
		
		
		//Casos iterativos para id mayor o igual de 2.
		fibAhora = 1; // F(n-1)
		fibAntes = 0; // F(n-2)

		for(int i=2; i<=id; i++) 
		{
			int fibSiguiente = fibAhora + fibAntes; // F(n) = F(n-1) + F(n-2)
			fibAntes = fibAhora; //Desplazamos
			fibAhora = fibSiguiente;
		}
		resultado = fibAhora;
	}
	
	public int getResultado() 
	{
		return resultado;
	}
}
