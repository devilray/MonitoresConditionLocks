package main;

import monitor.MonitorBuffer;
import threads.Consumidor;
import threads.Productor;

public class Programa {

	public static final int CAPACITY_BUFFER = 10;
	
	public static void main(String[] args) {
		
		MonitorBuffer buffer = new MonitorBuffer(CAPACITY_BUFFER);

		Productor p = new Productor(buffer);
		Consumidor c = new Consumidor(buffer);
		Thread tProductor = new Thread(p);
		Thread tConsumidor = new Thread(c);
		
		tProductor.start();
		tConsumidor.start();
		
	}

}
