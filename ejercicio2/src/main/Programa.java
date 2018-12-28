package main;

import java.util.concurrent.Semaphore;

import paneles.Panel;
import threads.Hilo;

/**
 * 
 * @author juanico
 *
 */
public class Programa {
	
	/*-----------------------------------------
	 * _______________CONSTANTS_______________
	 ----------------------------------------*/
	/**
	 * 
	 */
	public static final int CANT_THREADS = 10;
	
	/**
	 * 
	 */
	public static final int DIM_MATRIX = 3;
	
	/*-----------------------------------------
	 * ___________GLOBAL VARIABLES____________
	 ----------------------------------------*/
	/**
	 * 
	 */
	public static Semaphore mutex = new Semaphore(1);
	
	/**
	 * 
	 */
	public static Semaphore panelesLibres = new Semaphore(DIM_MATRIX);
	
	/**
	 * 
	 */
	public static Panel[] paneles = new Panel[DIM_MATRIX];
	
	/**
	 * 
	 */
	public static boolean[] isPanelLibre = new boolean[DIM_MATRIX];
	
	private static Thread[] hilos = new Thread[CANT_THREADS];
	
	/*-----------------------------------------
	 * _________PROGRAMA PRINCIPAL____________
	 ----------------------------------------*/
	public static void main(String[] args) {
		
		//Inicialmente todos los paneles libres
		for (int i = 0; i < DIM_MATRIX; i++) {
			isPanelLibre[i] = true;
			paneles[i] = new Panel("Panel Nº " + (i + 1), 20+450*i, 100);
		}
		
		for (int i = 0; i < CANT_THREADS; i++) {
			hilos[i] = new Hilo(i + 1); 
		}
		
		for (Thread threads : hilos) {
			threads.start();
		}
		
		for (Thread threads : hilos) {
			try {
				threads.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
