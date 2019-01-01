package main;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase que representa a los atributos compartidos entre todos los hilos y que están alojados en memoria global compartida.
 * @author Juan José Marín Peralta
 * @version 3.0
 */
public class SharedMemory {

	/**
	 * Constante numérica que representa las dimensiones de la matriz.
	 */
	public static final int DIM_MATRIX = 3;
		
	/**
	 * Constante numéric que representa el números de hilos que se van a ejecutar.
	 */
	public static final int NUM_THREADS = 10;
	
	/**
	 * Variable que representa un cerrojo para garantizar la exclusión mutua.
	 */
	public static ReentrantLock cerrojo = new ReentrantLock();
	
	/**
	 * Método global que muestra por pantalla una matriz que se le pasa por parámetro.
	 * La impresión por pantalla se realizará en exclusión mutua debido a que el Recurso
	 * no Compartible es la pantalla.
	 * @param m Matriz a imprimir por pantalla.
	 */
	public static void show(int[][] m) {
		try {
			cerrojo.lock();
			
			for(int i = 0; i < m.length; i++) {
				for(int j = 0; j < m[i].length; j++) {
					System.out.println("[" + m[i][j] + "]" + " "); 
				}
				System.out.println();
			}
			System.out.println();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			cerrojo.unlock();
		}
	}
	
	/**
	 * Método global que realiza el producto entre dos matrices que se la pasan por parámetro y 
	 * devuelve un matriz resultado de la multiplicación.
	 * @param a Primera matriz a multiplicar.
	 * @param b Segunda matriz a multiplicar.
	 * @return Matriz C resultado de multiplicar A y B.
	 */
	public static int[][] productoMatriz(int[][] a, int[][] b) {
		int c[][] = new int[DIM_MATRIX][DIM_MATRIX];
		
		if((a.length == b.length) && (a[0].length == b[0].length)) {
			for(int i = 0; i < a.length; i++)
				for(int j = 0; j < a[i].length; j++)
					c[i][j] = a[i][j] * b[i][j];
		} else
			throw new IllegalArgumentException("ERROR. Las longitudes de alguna de las matrices son incorrectas.");
		
		return c;
	}
	
	/**
	 * Método global que realiza la suma entre dos matrices que se la pasan por parámetro y 
	 * devuelve un matriz resultado de la suma.
	 * @param a Primera matriz a sumar.
	 * @param b Segunda matriz a sumar.
	 * @return Matriz C resultado de sumar A y B.
	 */
	public static int[][] sumarMatriz(int[][] a, int[][] b) {
		int[][] c = new int[DIM_MATRIX][DIM_MATRIX];
		
		if((a.length == b.length) && (a[0].length == b[0].length)) {
			for(int i = 0; i < a.length; i++)
				for(int j = 0; j < a[i].length; j++)
					c[i][j] = a[i][j] + b[i][j];
		} else
			throw new IllegalArgumentException("ERROR. Las longitudes de alguna de las matrices son incorrectas.");
		
		return c;
	}
	
}
