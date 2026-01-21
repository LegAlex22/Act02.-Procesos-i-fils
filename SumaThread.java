package Act5_Ex1_SumaParcial;

// alternativa: extends Thread | Es la tarea (el trabajo que hará un hilo) “Esto es lo que el hilo hará cuando empiece”.
public class SumaThread implements Runnable 
{
	private int id; //Numero del hilo en cuestión
	private int n; //Numero introducido por el user. Es private por tema seguridad na mas.
	private int sumaTotal; //Suma desde 1 hasta la variable N
	
	//TRUCAZO ATENTO A ESTO: el hilo padre envia el valor de las variables "i" y "num_n" y esos valores al introducirse en el hilo hijo se guardan en los parametros "param_i" y "param_num_n") y de ahí los pasamos a los atrivutos privados del hilo hijo "id" y "n".
	//Los parametros pueden obtener valores de afuera para asi darselos a los atributos ya que ellos de por si no pueden obtener valores de afura, solamente de adentro de otra variale/parametro/etc. (Parametro = Acceso Exterior e interior) y Atributo (Acceso interior)
	public SumaThread(int param_i, int param_num_n) 
	{
		this.id = param_i;
		this.n = param_num_n;
	}
	
	public void run()
	{
		System.out.println("Iniciant execució procés hilo " + id);
		sumaTotal = 0;
		
		for(int i=1; i<=n; i++) 
		{
			sumaTotal += i;
		}
	}
	
	public int getResultado() 
	{
		return sumaTotal;
	}
}
