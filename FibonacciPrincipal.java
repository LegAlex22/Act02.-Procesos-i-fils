package Act5_Ex2_Fibonacci;

import java.util.Scanner;

public class FibonacciPrincipal 
{
	public static void main(String[] pArguments)
	{
		Scanner sc = new Scanner(System.in);
		int nHilos = 0;
		
		System.out.print("Introduce el número de hilos a crear: ");
		
		if (sc.hasNextInt())
		{
			nHilos = sc.nextInt();
		}
		else
		{
			System.out.println("Entrada incorrecta. Se usará 1 hijo por defecto.");
			sc.next(); // Limpiamos la entrada del scanner
			nHilos = 1;
		}
		
		if (nHilos < 1)
		{
			System.out.println("Número mínimo 1. Se usará 1 por defecto.");
			nHilos = 1;
		}
		
		
		FibonacciThread[] tareas = new FibonacciThread[nHilos]; 
		Thread[] hilos = new Thread[nHilos];
		
		
		
		// Bucle para crear cada tarea e hilo
		for (int i=0; i<nHilos; i++) 
		{
			tareas[i] = new FibonacciThread(i); //Creamos la tarea (Osea lo que va a hacer)
			hilos[i] = new Thread(tareas[i]); // Creamos el hilo real que ejecutará esa tarea
		}
		
		
		
		//Iniciamos todos los hilos hijos
		for (int i=0; i<nHilos; i++)
		{
			hilos[i].start(); 
		}
		
		
		
		// Bucle para que el padre (principal) espere a que todos los hilos terminen
		for (int i=0; i<nHilos; i++)
		{
			try
			{
				hilos[i].join(); 
			}
			catch (InterruptedException e) { System.out.println("El hilo principal ha sido interrumpido mientras esperaba al hilo " + i + "."); }
		}
		
		
		
		System.out.println("");
		System.out.println("Resultados:");
		for (int i=0; i<nHilos; i++) 
		{
			System.out.println("Hilo "+i+ ": Fibonacci ("+i+") = "+ tareas[i].getResultado()); //Dato importante, no es hilos[i] porque es un Thread, y la clase thread no sabe nada de sumas ni tiene getResultado, solo sabe tipo start(), join(), sleep(), getname() etc. Es como el trabajador, no la tarea con los datos importantes. Siempre que necesitemos un resultado se lo pedimos al Runnable, no al Thread.
		}
		
		sc.close();
	}
}
