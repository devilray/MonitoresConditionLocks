package main;

import monitor.Monitor;
import threads.Consumidor;
import threads.Productor;

public class Programa {

	public static final int CAPACITY_BUFFER = 10;

	public static void main(String[] args) {
		
		Monitor m = new Monitor(CAPACITY_BUFFER);
		
		Productor p = new Productor(m);
		Consumidor c = new Consumidor(m);
		Thread tP = new Thread(p);
		Thread tC = new Thread(c);
		
		tP.start();
		tC.start();
		
	}

}
