package main;

import static main.SharedMemory.*;

import threads.Hilo;

/**
 * 
 * Desarrollar un programa concurrente en Java para resolver el problema siguiente: 
 * Supongamos que hay 30 procesos que tras estar un tiempo en ejecución (representado como 
 * una llamada a Thread.sleep(tiempoAleatorio)) generan aleatoriamente un número del conjunto 
 * {1,2,3,4}. Tras continuar durante un tiempo su ejecución (representado como otra llamada a 
 * Thread.sleep(tiempoAleatorio)) cada proceso debe imprimir la cantidad de procesos en el 
 * sistema que tienen su mismo número (incluyéndose él mismo) sabiendo todos que hay 30 
 * procesos en ejecución. La impresión debe ser:

Hilo id
Valor asignado = 
Hilos con el mismo valor = 

La sincronización hay que resolverla con monitores de la forma que consideres más
eficiente.

 * @author Juan José Marín Peralta
 * @version 3.0
 */
public class Programa {

	public static void main(String[] args) {
		
		Hilo[] hilos = new Hilo[NUMERO_PROCESOS];
		
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new Hilo(i+1);
		}
		
		for (Hilo hilo : hilos) {
			hilo.start();
		}
		
		for (Hilo hilo : hilos) {
			try {
				hilo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
