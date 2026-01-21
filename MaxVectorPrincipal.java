package Act5_Ex3_MaxVector;

import java.util.Scanner;

public class MaxVectorPrincipal 
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
			sc.next(); // Limpiamos la entrada del scanner
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
		MaxVectorThread[] tareas = new MaxVectorThread[nHilos]; 
		Thread[] hilos = new Thread[nHilos];
		
		
		
		
	
		
		int tamPorHilo = tVector / nHilos; // Son las posiciones mínimas del vector recorrerá cada hilo, cada hilo see una parte del vector en busca del maximo valor.
		//int sobras = tVector % nHilos; //Son las posiciones que no caben en el hilo y las hará el padre principal (Ejemplo 10 % 3 = 1). Pero para el bucle del padre usamos Inicio sin mas, si usaramos sobras, seria hacer int sobras = inicio mas abajo en la linea: 132 aprox antes del bucle padres
		
		
		
		
		
		// Bucle para crear cada tarea e hilo
		int inicio = 0;
		for (int i=0; i<nHilos; i++) 
		{
			int fin = inicio + tamPorHilo - 1; //Importante el -1, pq sino seria del 0 al 4 (imaginando el ejemplo de abajo)
			
			tareas[i] = new MaxVectorThread(i,vector, inicio, fin); //Creamos la tarea (Osea lo que va a hacer)
			hilos[i] = new Thread(tareas[i]); // Creamos el hilo real que ejecutará esa tarea
			
			inicio = fin + 1; // el siguiente hilo empieza aquí ya que hemos quitado -1 antes
		}
		
		/*
		 * Esto se hará por tantos hilos que haya y dentro el fin para que sepamos cuando acaba es (imaginando el hilo0), fin = 0 + 3 - 1. 
		 * Esto es pq lee la pisicion 0 hasta la 2 pero tecnicamente ha leido 3 posiciones, 0,1,2. pero como no tenemos un valor en si, 
		 * usamos el tamaño minimo osea la variable y le restamos 1, truco rumano jaja. luego se crea la tarea osea tarea0 y se pasa 
		 * los parametros, i osea el hilo tal, el vector entero, inicio = 0, fin= 2. y se crea el hilo trabajador que su tarea 
		 * será la tarea0 y una vez acaba para tener el inicio del siguiente hilo y no empezar desde el 0, le ponemos el valor de fin 
		 * pero +1 para que empiece por el siguiente y no haya un valor guardado en 2 hilos distintos, si acaba en 2, el otro empieza en el 3.
		 */
		
		//Imaginemos que el vector que mete el usuario es 10 y el numero de hilos es 3. 
		//El tamPorHilos será 10/3 que es 3. Eso significa que el tamaño de los array "VEC" de los hilos solo tendrán ese tamaño
		
		//Hilo 0: Inicio/In: 0 | Fin/Fi: 2
		//Hilo 1: Inicio/In: 3 | Fin/Fi: 5
		//Hilo 2: Inicio/In: 6 | Fin/Fi: 8
		//Padre:  Inicio/In: 9 | Fin/Fi: 9 (Aqui el 10 no, ya que hemos empezado desde el 0)
		
		
		
		
		
		
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
		
		
		
		
		
		
		
		//Procesar las posiciones sobrantes (Esto solo entrará en el bucle si sobran posiciones, simple. Si loas posiciones se acaban en los hilos hijos allá dentro ya habremos encontrado el valor maximo y ya).
		int maxPadre = 0;
		
		//Con el ejemplo Inicio tiene el valor de 9 ahora. entonces será haz esto desde el inicio=9 hasta llegar al tamaño del vector=10. Osea 1 vez vamos
		for (int i = inicio; i < tVector; i++)
		{
			//Si el vector en la posicion 9 es mas grande que maxPadre que será en la 1r iteracion = 0, lo actualizará.
			//Atento el padre no comparará si el MaxHijo es mayor que su maxPadre, el solo gestiona lo que sobra, como el hilo hijo solo gestiona lo que se quedan/no sobra.
			if (vector[i] > maxPadre) 
				
			{
				maxPadre = vector[i];
			}
		}
		
		
		
		
		
		
		
		//Decidir el máximo global y quién lo encontró
		int maxFinal = maxPadre; //Damos como ganador el padre pq si sobra posiciones es pq el será el mas grande Fin, pero sino sobrá ninguno el valor de MaxFinal será 0, ya que es el valor que se inicializo y porque ni siquiera entró en el bucle de arriba.
		String quien = "Padre"; //Igual, intuimos que el padre
		
		for (int i = 0; i < nHilos; i++)
		{
			if (tareas[i].getResultado() > maxFinal) //Aqui es para contrarestar lo otro, si el valor del get que es maxHijo es mayor que maxPadre, pues se intuye que ese es el mas grande y se actualiza los datos, pero sino es pq el padre es mas grande y ya, no hay que dar mas vueltas.
			{
				maxFinal = tareas[i].getResultado();
				quien = "Hilo " + i;
			}
		}
		
		
		
		
		
		
		System.out.println("");
		System.out.println("Resultados:");
		System.out.println("Valor máximo del vector: " + maxFinal);
		System.out.println("Encontrado por: " + quien);
		
		sc.close();
	}
}
