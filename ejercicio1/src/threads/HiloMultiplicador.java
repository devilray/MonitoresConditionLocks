package threads;

import static main.SharedMemory.*;

import java.util.Random;

public class HiloMultiplicador implements Runnable {

	private int[][] m, result;
	
	public HiloMultiplicador() {
		
		this.m = new int[DIM_MATRIX][DIM_MATRIX];
		this.result = new int[DIM_MATRIX][DIM_MATRIX];
		
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				this.m[i][j] = new Random().nextInt(9) + 1; 
			}
		}
		
	}
	
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
