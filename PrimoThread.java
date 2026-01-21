package Act5_Ex5_Primos;

// alternativa: extends Thread | Es la tarea (el trabajo que hará un hilo) “Esto es lo que el hilo hará cuando empiece”.
public class PrimoThread implements Runnable 
{
	private int id; //Numero del hilo en cuestión
	private int num; // Numero que vamos a comprobar si es primo o no
	private boolean esPrimo; // Resultado si es primo
	
	public PrimoThread(int param_i, int param_num) 
	{
		this.id = param_i;
		this.num = param_num;
		this.esPrimo = false; //Lo inicializamos
	}
	
	public void run() //Runnable
	{
		System.out.println("Iniciant execució procés hilo " + id + ". Comprobando si es primo el numero: "+ num); //No tiene que ser por orden (1,2,3,4. puede ser 2,1,3,4) 
		//Es indiferente ya que el padre dice que numero es o no es primo, pero para ver que el orden de ejecución no afecta al orden de impresión de los resultados
		
		if (num < 2) {
			esPrimo = false; //Ya que 0 y 1 no son primos
		}
		else 
		{
			esPrimo = true;
			for (int i = 2; i < num; i++) 
			{
				if (num % i == 0)
				{
					esPrimo = false;
					break; //No hace falta seguir, salimos y cogeremos el valor por defecto que es true (antes del bucle)
				}
				
			}
		}
		
	}
	
	public boolean getResultado() 
	{
		return esPrimo;
	}
}
