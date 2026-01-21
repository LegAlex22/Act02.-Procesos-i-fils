package Act5_Ex5_Primos;

import java.util.Scanner;

public class PrimoPrincipal 
{
	public static void main(String[] pArguments)
	{
		Scanner sc = new Scanner(System.in);

		int[] numeros = new int[100];
		int nHilos = 0;
		int numero = 0;
		
		//Pedimos el numero de hilos a crear
		
		System.out.println("Introduce numeros (finaliza con -1): ");
		System.out.println("______________________________________");
		System.out.println("");
		
		do 
		{
			System.out.print("Numero " + (nHilos + 1) + ": ");
			
			while (!sc.hasNextInt())  //Validamos si es un numero, sino que repita
			{
				System.out.print("Error. Introduce numeros (finaliza con -1): ");
				sc.next();
			}
			
			numero = sc.nextInt();
			
			if (numero !=-1) 
			{
				numeros[nHilos] = numero;
				nHilos++;
			}
		}
		while(numero != -1);
		
		
		
		//Creamos arrays de tareas e hilos
		PrimoThread[] tareas = new PrimoThread[nHilos]; 
		Thread[] hilos = new Thread[nHilos];
		
		
		
		// Bucle para crear cada tarea e hilo (rangos)
		for (int i=0; i<nHilos; i++) 
		{
			tareas[i] = new PrimoThread(i,numeros[i]); //Creamos la tarea (Osea lo que va a hacer). //El orden de como enviamos los valores a los parametros es imporante, tiene que ser igual.
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
		
		
		
		
		
		
		boolean esNumeroPrimo = false;
		String resultado = "";
		for (int i = 0; i < nHilos; i++) // Se imprimen en orden aunque los hilos no se ejecuten de forma secuencial, todo chill
		{
			esNumeroPrimo = tareas[i].getResultado(); //Lo que llegue del getResultado.
			
			if (esNumeroPrimo == true) { resultado = "Si es Primo"; }
			else { resultado = "No es Primo"; }
			
			System.out.println("El numero " + numeros[i] + ": " + resultado );
		}
		
		
		
		sc.close();
	}
}
