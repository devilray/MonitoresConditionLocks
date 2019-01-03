package main;

import monitor.Monitor;
import threads.Hilo;

public class Programa {

	public static void main(String[] args) {
		
		Monitor m = new Monitor(0);
		
		Hilo h1 = new Hilo(m, 1, 10);
		Hilo h2 = new Hilo(m, 2, 10);
		Thread t1 = new Thread(h1);
		Thread t2 = new Thread(h2);
		
		t1.start();
		t2.start();

	}

}
