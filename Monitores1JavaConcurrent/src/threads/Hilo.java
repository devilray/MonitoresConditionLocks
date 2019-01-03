package threads;

import monitor.Monitor;

public class Hilo implements Runnable {

	private Monitor monitor;
	private int tipo;
	private int cantidad;
	
	public Hilo(Monitor monitor, int tipo, int cantidad) {
		this.monitor = monitor;
		this.tipo = tipo;
		this.cantidad = cantidad;
	}
	
	@Override
	public void run() {
		
		for (int i = 0; i < 100; i++) {
			if(tipo == 2) monitor.up(cantidad);
			else monitor.down(cantidad);
		}

	}

}
