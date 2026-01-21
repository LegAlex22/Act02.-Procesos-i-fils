package Act5_Ex4_ContarParells;

import java.util.Scanner;

public class ContarParellsPrincipal 
{
	public static void main(String[] pArguments)
	{
		Scanner sc = new Scanner(System.in);
		int nHilos = 0;
		int tVector = 0;
		int[] vector = new int[tVector];
		
		
		//Pedimos el numero de hilos a crear
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
		
		
		
		
		//Pedimos el tamaño del vector a crear
		System.out.print("Introduce el tamaño del vector a crear: ");
		if (sc.hasNextInt())
		{
			tVector = sc.nextInt();
		}
		else
		{
			System.out.println("Entrada incorrecta. El tamaño del vector será de 10 por defecto.");
			sc.next(); // Limpiamos la entrada del scanner, lo hice una vez y decia que era bien hacerlo.
			tVector = 10;
		}
			
		if (tVector < 1)
		{
			System.out.println("Número mínimo 1. El tamaño será de 10 por defecto.");
			tVector = 10;
		}
		
		
		
		
		
		//Crear el vector e inicializarlo con valorres aleatorios
		vector = new int[tVector];
		for (int i = 0; i<tVector; i++) 
		{
			vector[i] = (int)(Math.random()*100); // números aleatorios del 0 al 99
		}
		
		
		
		
	
		
		//Creamos arrays de tareas e hilos
		ContarParellsThread[] tareas = new ContarParellsThread[nHilos]; 
		Thread[] hilos = new Thread[nHilos];
		
		
		int tamPorHilo = tVector / nHilos; // Son las posiciones mínimas del vector recorrerá cada hilo, cada hilo see una parte del vector en busca del maximo valor.
		
		
		// Bucle para crear cada tarea e hilo (rangos)
		int inicio = 0;
		for (int i=0; i<nHilos; i++) 
		{
			int fin; //Importante el -1, pq sino seria del 0 al 4 (imaginando el ejemplo de abajo)
			
			if (i == nHilos - 1)
			{
				fin = tVector - 1; //El ultimo hilo procesa el resto
			}
			else
			{
				fin = inicio + tamPorHilo - 1;
			}
			
			tareas[i] = new ContarParellsThread(i,vector, inicio, fin); //Creamos la tarea (Osea lo que va a hacer)
			hilos[i] = new Thread(tareas[i]); // Creamos el hilo real que ejecutará esa tarea
			
			inicio = fin + 1; // el siguiente hilo empieza aquí ya que hemos quitado -1 antes
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
		
		
		
		
		
		
		int sumaParesTotal = 0;
		for (int i = 0; i < nHilos; i++)
		{
			sumaParesTotal += tareas[i].getResultado(); //Lo que llegue del getResultado de todos los hilos, se acumula en sumaParesTotal.
			
		}
		
		
		
		System.out.println("");
		System.out.println("Total de numeros pares encontrados: " + sumaParesTotal);
		
		
		sc.close();
	}
}
