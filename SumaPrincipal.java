package Act5_Ex1_SumaParcial;


import java.util.Scanner;

public class SumaPrincipal 
{
	public static void main(String[] pArguments)
	{
		Scanner sc = new Scanner(System.in);
		int nHilos = 0; //Numero de hilos que se crearán
		int num_n = 0; // Número N que usará cada hilo para la sumaTotal.
		
		//Pedimos el numero de hilos a crear
		System.out.print("Introduce el número de hilos a crear: ");
		if (sc.hasNextInt())
		{
			nHilos = sc.nextInt();
		}
		else //Si lo que se mete al scanner es incorrecta:
		{
			System.out.println("Entrada incorrecta. Se usará 1 hijo por defecto.");
			sc.next(); // Limpiamos la entrada del scanner
			nHilos = 1;
		}
		
		//Ajustar a 1 si el numero de hijos es inferior a 0
		if (nHilos < 1)
		{
			System.out.println("Número mínimo 1. Se usará 1 por defecto.");
			nHilos = 1;
		}
		
		// Array Tareas (Para Runnable = Lo usaremos para acceder al valor de getResultado() de cada hilo.)
		//Guardamos los objetos SumaThread, cada hilo tiene: Numero N, ID hilo, SumaTotal
		SumaThread[] tareas = new SumaThread[nHilos]; 
		
		
		//Array Hilos (Lo usaremos para iniciar la ejecución start() y esperar a que terminen join() de cada hilo.)
		//Guardamos los hilos reales de java.
		Thread[] hilos = new Thread[nHilos];
		
		//Pedir el Num N a cada hilo y los creamos
		for (int i=0; i<nHilos; i++) 
		{
			System.out.print("Introduce el numero N para el hilo "+ i + ": ");
			num_n = sc.nextInt();
			
			//Creamos la tarea (Osea lo que va a hacer)
			tareas[i] = new SumaThread(i, num_n); // El valor de "i" se lo metemos al atributo "id" y el valor de "num_n" se lo pasamos al atributo "n" del hilo. El nombre de estas vaiables es pq el i lo hemos creado en el bucle y el num_n arriba del todo, 
			
			// Creamos el hilo real que ejecutará esa tarea
			hilos[i] = new Thread(tareas[i]);
		}
		
		//Piensalo de esta manera, es raro pero así lo entiendo
		//Las tareas son como post-its (Limpiar, Cocinar), las variables i y num_n son (Escoba, fregona y plato y comida) es como los recursos que usará. Los hilos son los trabajadores que recien hemos contratado, con hilos[i] = new Thread(tareas[i]); les decimos que el trabajador1 hará la tarea1. Y AHORA lo que haremos será con el .start decirles que empiezen a hacer su tarea tocada (El trabajador1 hara su tarea1 con sus recursos (escoba y fregona) y cuando acabé con el get() enviará el resultado) 
		
		//Iniciamos todos los hilos hijos
		for (int i=0; i<nHilos; i++)
		{
			hilos[i].start(); // Lanzamos la ejecución del hilo en paralelo
		}
		
		
		// Bucle para que el padre (principal) espere a que todos los hilos terminen
		for (int i=0; i<nHilos; i++)
		{
			try //Usamos try catch obligatorio al usar el join.
			{
				hilos[i].join(); //Bloqueamos el hilo padre (principal) hasta que el hilo "i" termine
			}
			catch (InterruptedException e) { System.out.println("El hilo principal ha sido interrumpido mientras esperaba al hilo " + i + "."); }
		}
		
		System.out.println("");
		System.out.println("Resultados:");
		for (int i=0; i<nHilos; i++) 
		{
			System.out.println("Hilo "+ i + "resultado de suma de 1 a N = "+ tareas[i].getResultado()); //Dato importante, no es hilos[i] porque es un Thread, y la clase thread no sabe nada de sumas ni tiene getResultado, solo sabe tipo start(), join(), sleep(), getname() etc. Es como el trabajador, no la tarea con los datos importantes. Siempre que necesitemos un resultado se lo pedimos al Runnable, no al Thread.
		}
		sc.close();
	}
}
