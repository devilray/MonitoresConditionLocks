package main;

import paneles.Panel;
import threads.Hilo;

import static main.SharedMemory.*;

/**
 * 
 * @author juanico
 *
 */
public class Programa {
	
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
