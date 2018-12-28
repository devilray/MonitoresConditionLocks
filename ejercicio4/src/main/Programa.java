package main;

import java.util.Random;

import messagepassing.MailBox;
import threads.Hilo;

public class Programa {

	/*-----------------------------------------
	 * _______________CONSTANTS_______________
	 ----------------------------------------*/
	/**
	 * 
	 */
	public static final int CANT_THREADS = 30;
	
	/*-----------------------------------------
	 * ____________GLOBAL VARIABLES____________
	 ----------------------------------------*/
	private static MailBox[] buzones = new MailBox[CANT_THREADS];
	private static MailBox sharedScreen = new MailBox();
	private static Hilo[] hilos = new Hilo[CANT_THREADS];
	
	public static int newRandomNumber() {
		return new Random().nextInt(4)+1; //50 + 1 OR 100 + 1
	}
	
	/*-----------------------------------------
	 * ______________MAIN PROGRAM______________
	 ----------------------------------------*/
	public static void main(String[] args) {
		
		for (int i = 0; i < buzones.length; i++) {
			buzones[i] = new MailBox();
		}
		
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new Hilo(i + 1, newRandomNumber(), buzones, sharedScreen);
		}
		
		for (int i = 0; i < hilos.length; i++) {
			hilos[i].start();
		}
		
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
