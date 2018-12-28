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
	public static int newRandomNumber() {
		return new Random().nextInt(4) + 1;
	}
	
	/*-----------------------------------------
	 * ______________MAIN PROGRAM______________
	 ----------------------------------------*/
	public static void main(String[] args) {
		
		MailBox[] mailBoxes = new MailBox[CANT_THREADS];
		MailBox sharedScreen = new MailBox();
		
		Hilo[] hilos = new Hilo[CANT_THREADS];
		
		for (int i = 0; i < CANT_THREADS; i++) {
			mailBoxes[i] = new MailBox();
			hilos[i] = new Hilo(i + 1, newRandomNumber(), mailBoxes, sharedScreen);
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
