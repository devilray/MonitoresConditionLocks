package main;

import java.util.concurrent.Semaphore;

import paneles.Panel;

public class SharedMemory {

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
	
}
