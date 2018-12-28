package main;

import java.util.Random;

/**
 * 
 * @author juanico
 *
 */
public class Matrix {

	private final int ancho, alto;
	private int[][] values;
	
	/**
	 * 
	 * @param ancho
	 * @param alto
	 */
	public Matrix(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;
		this.values = new int[ancho][alto];
	}

	/**
	 * 
	 * @return
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * 
	 * @return
	 */
	public int getAlto() {
		return alto;
	}
	
	/**
	 * 
	 */
	public void newRandomMatrix() {
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				values[i][j] = new Random().nextInt(9) + 1;
			}
		}
	}
	
	/**
	 * 
	 * @param m
	 * @return
	 */
	public Matrix sumMatrix(Matrix m) {
		Matrix result = new Matrix(ancho, alto);
		
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				result.values[i][j] = this.values[i][j] + m.values[i][j];
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public String printMatrix() {
		String result = new String();
		
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				if(j != ancho) result += values[i][j] + " ";
				else result += values[i][j];
			}
			result += "\n";
		}
		
		return result;
	}
	
}
