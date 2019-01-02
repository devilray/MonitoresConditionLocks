package main;

import java.util.Random;

import messagepassing.MailBox;
import threads.Hilo;

/**
 * Clase principal donde se lanzan los procesos en forma de paso de mensajes
 * asíncrono.
 * 
 * Consideremos una colección de 30 hilos. Cada uno de ellos tiene un identificador único 
 * id y un número entero que recibe del hilo principal. Interrogando al resto de hilos (enfoque 
 * distribuido con o sin esquema de token en un anillo lógico), cada hilo debe ser capaz de encontrar e imprimir el valor mínimo de los 30 valores asignados por el hilo principal. La
 * impresión de cada hilo será:
 * 
Hilo id con número X
Valor mínimo=

donde X es el número que recibió el hilo y los puntos el valor mínimo.
Desarrollar un programa concurrente en Java para resolver el problema anterior usando
como mecanismo para la sincronización el paso de mensajes asíncrono. 
 * 
 * @author Juan José Marín Peralta
 * @version 3.0
 */
public class Programa {

	/*-----------------------------------------
	 * _______________CONSTANTS_______________
	 ----------------------------------------*/
	/**
	 * Constante que representa la cantidad de hilos que se van a ejecutar
	 */
	public static final int CANT_THREADS = 30;
	
	/*-----------------------------------------
	 * _______________VARIABLES_______________
	 ----------------------------------------*/
	private static MailBox[] buzones = new MailBox[CANT_THREADS];
	private static MailBox sharedScreen = new MailBox();
	private static Hilo[] hilos = new Hilo[CANT_THREADS];
	
	/**
	 * Genera un número aleatorio
	 * @return número Número aleatorio generado.
	 */
	public static int newRandomNumber() {
		return new Random().nextInt(4)+1; //50 + 1 OR 100 + 1
	}
	
	/*-----------------------------------------
	 * ______________MAIN PROGRAM______________
	 ----------------------------------------*/
	public static void main(String[] args) {
		
		//iniciamos cada uno de los buzones para los cada hilo
		for (int i = 0; i < buzones.length; i++) {
			buzones[i] = new MailBox();
		}
		
		//creamos los 30 hilos
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new Hilo(i + 1, newRandomNumber(), buzones, sharedScreen);
		}
		
		//iniciamos los 30 hilos
		for (int i = 0; i < hilos.length; i++) {
			hilos[i].start();
		}
		
		//ejecutamos los 30 hilos
		for (int i = 0; i < hilos.length; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("FINAL DEL HILO.");
		
	}
	
}
