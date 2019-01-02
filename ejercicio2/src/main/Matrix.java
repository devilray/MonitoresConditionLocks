package main;

import java.util.Random;

/**
 * Clase que representa el tipo de una Matriz con una anchura y una altura que se construyen al comienzo.
 * @author juanico
 * @version 3.0
 */
public class Matrix {

	private final int ancho, alto;
	private int[][] values;
	
	/**
	 * Constuye una matriz de anchura y altura (Ancho X Alto) establecidos en los parámetros del constructor.
	 * @param ancho Anchura que tendrá la matriz. Número de Filas.
	 * @param alto Altura que tendrá la matriz. Número de Columnas.
	 */
	public Matrix(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;
		this.values = new int[ancho][alto];
	}

	/**
	 * Obtiene la anchura de la matriz
	 * @return anchura de la matriz.
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Obtiene la altura de la matriz.
	 * @return altura de la matriz.
	 */
	public int getAlto() {
		return alto;
	}
	
	/**
	 * Genera una nueva matriz con valores aleatorios de anchura x altura establecidos en la construcción.
	 */
	public void newRandomMatrix() {
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				values[i][j] = new Random().nextInt(9) + 1;
			}
		}
	}
	
	/**
	 * Método que realiza la suma de dos matrices. La primera matriz es la instancia actual (el objeto recetor de la llamada) y la segunda matriz es la que se le pasa por parámetro. 
	 * @param m Matriz que se le sumará al objeto receptor que será otra matriz del mismo tipo.
	 * @return Matriz que resulta la suma de la matriz de la instancia actual y la matriz pasada por parámetro.
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
	 * Método que imprime la matriz con formato propio de una matriz en filas y columnas.
	 * @return cadena de caracteres que representa a una matriz.
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
