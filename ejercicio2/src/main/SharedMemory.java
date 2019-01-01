package main;

import java.util.concurrent.Semaphore;

import paneles.Panel;

/**
 * Clase que representa a los atributos compartidos entre todos los hilos y que están alojados en memoria global compartida.
 * @author Juan José Marín Peralta
 * @version 3.0
 */
public class SharedMemory {

	/*-----------------------------------------
	 * _______________CONSTANTS_______________
	 ----------------------------------------*/
	/**
	 * Constante numéric que representa el números de hilos que se van a ejecutar.
	 */
	public static final int CANT_THREADS = 10;
	
	/**
	 * Constante numérica que representa las dimensiones de la matriz.
	 */
	public static final int DIM_MATRIX = 3;
	
	/*-----------------------------------------
	 * ___________GLOBAL VARIABLES____________
	 ----------------------------------------*/
	/**
	 * Semáforo binario encargado de garantizar la exclusión mutua de la sección crítica.
	 */
	public static Semaphore mutex = new Semaphore(1);
	
	/**
	 * Semáforo general encargado de que sólo puedan existir tres hilos escribiendo en los paneles al mismo tiempo (Uno por panel).
	 */
	public static Semaphore panelesLibres = new Semaphore(DIM_MATRIX);
	
	/**
	 * Variable que representa un array de tres paneles para controlar el acceso a cada uno de manera que sólo pueda escribir un hilo al mismo tiempo en cada panel, garantizando exclusión mutua.
	 */
	public static Panel[] paneles = new Panel[DIM_MATRIX];
	
}
