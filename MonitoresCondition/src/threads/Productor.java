package threads;

import main.Programa;
import monitor.Monitor;

public class Productor implements Runnable {

	private Monitor buffer;
	
	public Productor(Monitor buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		
		for (int i = 0; i < 100; i++) {
			//produce item
			int item = (int) (Math.random()*Programa.CAPACITY_BUFFER);
			try {
				Thread.sleep((int)Math.random()*Programa.CAPACITY_BUFFER);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//push element
			try {
				buffer.push((Object) new Integer(item)); //new Integer(item)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Insertado elemento -> " + item);
		}

	}

}
