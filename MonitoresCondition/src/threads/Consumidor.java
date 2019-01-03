package threads;

import main.Programa;
import monitor.Monitor;

public class Consumidor implements Runnable {

	private Monitor buffer;
	
	public Consumidor(Monitor buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		
		for (int i = 0; i < 100; i++) {
			//Integer item = null;
			
			//pop element
			try {
				Integer item = (Integer) buffer.pop();
				System.out.println("Extraído elemento -> " + item);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//consume item
			try {
				Thread.sleep((int)Math.random()*Programa.CAPACITY_BUFFER);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
