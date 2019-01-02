package threads;

import static main.SharedMemory.*;

import java.util.Random;

/**
 * Proceso que representa una multiplicación de matrices. Genera una matriz aleatoria e imprime por pantalla el resultado de multiplicarla.
 * @author Juan José Marín Peralta
 * @version 3.0
 */
public class HiloMultiplicador implements Runnable {

	private int[][] m, result;
	
	/**
	 * Construye una matriz aleatoria de dimensiones 3x3.
	 */
	public HiloMultiplicador() {
		
		this.m = new int[DIM_MATRIX][DIM_MATRIX];
		this.result = new int[DIM_MATRIX][DIM_MATRIX];
		
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				this.m[i][j] = new Random().nextInt(9) + 1; 
			}
		}
		
	}
	
	/**
	 * Ejecutamos el hilo. 
	 * 1º Hará la multiplicación de las matrices y la guardará en una variable auxiliar.
	 * 2º Mostrará por pantalla en Exclusión Mutua la matriz original.
	 * 3º Mostrará por pantalla en Exclusión Mutua la matriz resultante de multiplicar las matrices originales.
	 */
	@Override
	public void run() {
		
		System.out.println("Hilo Producto \n");
		
		result = productoMatriz(m, m);
		
		for (int i = 0; i < NUM_THREADS; i++) {
			System.out.println("Matriz Nº " + (i+1) + " para Multiplicar.");
			show(m);
			System.out.println("Hilo Nº " + (i+1) + " de Producto. M * M");
			show(result);
			System.out.println("Fin del hilo Nº " + (i+1) + " de Producto.");
		}

	}

}
