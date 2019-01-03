package threads;

import monitor.MonitorBuffer;

public class Consumidor implements Runnable {

	private MonitorBuffer buffer;
	
	public Consumidor(MonitorBuffer buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {

		for (int i = 0; i < 100; i++) {
			//pop item
			int item = buffer.pop();
			
			//consume item
			try {
				Thread.sleep((int)Math.random()*10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Elemento extraído = " + item);
		}

	}

}
