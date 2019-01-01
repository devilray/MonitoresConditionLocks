package main;

import paneles.Panel;
import threads.Hilo;

import static main.SharedMemory.*;

/**
 * Programa principal que ejcuta los hilos para calcular el siguiente problema por semáforos:
 * 
 * Desarrollar un programa concurrente en Java para resolver el problema siguiente:
Supongamos que en el sistema tenemos tres instancias de la clase Panel que se proporciona.
Hay 10 hilos cuya funcionalidad es la siguiente: 1) generar aleatoriamente dos matrices de
tamaño 10 X 10 (matrices A y B ), 2) sumar A y B obteniendo la matriz C y 3) mostrar en un
panel que esté disponible la siguiente salida

Usando panel el hilo id
Matriz A=
Matriz B=
Matriz C=
Terminando de usar panel el hilo id

donde los puntos representan los valores de las matrices. Los pasos 1, 2 y 3 los repiten
cada hilo tres veces.
Por lo tanto, los hilos comparten los tres paneles del sistema y usando semáforos se
debe de añadir la sincronización necesaria para que los hilos puedan mostrar su salida en
pantalla a través de un panel de forma correcta y el uso de los paneles sea eficiente. La
sincronización hay que resolverla con semáforos.
 * 
 * @author Juan José Marín Peralta
 * @version 3.0
 */
public class Programa {
	
	/**
	 * Variable booleana que indica en cada acceso si un determinado panel está libre o no para escribir en él.
	 */
	public static boolean[] isPanelLibre = new boolean[DIM_MATRIX];
	
	private static Thread[] hilos = new Thread[CANT_THREADS];
	
	/*-----------------------------------------
	 * _________PROGRAMA PRINCIPAL____________
	 ----------------------------------------*/
	public static void main(String[] args) {
		
		//Inicialmente todos los paneles libres
		//Creamos los paneles
		for (int i = 0; i < DIM_MATRIX; i++) {
			isPanelLibre[i] = true;
			paneles[i] = new Panel("Panel Nº " + (i + 1), 20+450*i, 100);
		}
		
		//creamos cada uno de los 30 hilos
		for (int i = 0; i < CANT_THREADS; i++) {
			hilos[i] = new Hilo(i+1);
		}
		
		//iniciamos los hilos
		for (Thread threads : hilos) {
			threads.start();
		}
		
		//esperamos a que cada hilo finalices su ejecución para iniciar el siguiente.
		for (Thread threads : hilos) {
			try {
				threads.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
