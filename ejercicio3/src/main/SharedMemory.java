package main;

import monitor.Monitor;

public class SharedMemory {

	//************************************************************************
	//************************ CONSTANTES NUMÉRICAS **************************
	//************************************************************************	
	/**
	 * Constante numérica que representa el número de procesos que se van a ejecutar
	 */
	public static final int NUMERO_PROCESOS = 30;			
	
	//************************************************************************
	//************************* VARIABLES GLOBALES ***************************
	//************************************************************************	
	/**
	 * Variable global que representa el conjunto de números aleatorios que vamos a generar entre 1 y 4, ambos inclusive y que se obtienen en cada hilo.
	 */
	public static int[] set = new int [NUMERO_PROCESOS]; 
	
	/**
	 * Variable global que representa el monitor que va a controlar el acceso a la sección crítica.
	 */
	public static Monitor monitor = new Monitor();
	
}
