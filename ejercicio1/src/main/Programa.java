package main;

import threads.HiloMultiplicador;
import threads.HiloSumador;

/**
 * Programa principal que ejecuta los dos procesos para sumar y potencia.
 *
 * Supongamos dos hilos con la siguiente funcionalidad: uno se dedica a generar una
matriz A de valores enteros de tamaño 3x3, calcular el cuadrado de dicha matriz (A2) e
imprimir en pantalla:
A x A

a1 a2 a3
a4 a5 a6
a7 a8 a9

x

a1 a2 a3
a4 a5 a6
a7 a8 a9

A^2
r1 r2 r3
r4 r5 r6
r7 r8 r9

El otro hilo hace lo mismo pero en lugar de calcular A2, calcula A+A y por lo tanto
imprime en pantalla:

A + A

a1 a2 a3
a4 a5 a6
a7 a8 a9

+

a1 a2 a3
a4 a5 a6
a7 a8 a9


2A
r1 r2 r3
r4 r5 r6
r7 r8 r9


La actividad anterior la repite cada hilo 10 veces. Desarrollar un programa concurrente
en Java que resuelva el problema anterior. Para resolver la sincronización necesaria entre los
hilos se usarán los cerrojos de la clase ReentrantLock.

 * @author Juan José Marín Peralta
 * @version 3.0
 */
public class Programa {
	
	public static void main(String[] args) {
		
		HiloSumador hs = new HiloSumador();
		HiloMultiplicador hm = new HiloMultiplicador();
		
		Thread suma = new Thread(hs);
		Thread prod = new Thread(hm);
		
		suma.start();
		prod.start();
		
		try {
			suma.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			prod.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
