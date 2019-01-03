package threads;

import monitor.MonitorBuffer;

public class Productor implements Runnable {

	private MonitorBuffer buffer;
	
	public Productor(MonitorBuffer buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		
		for (int i = 0; i < 100; i++) {
			//produce element
			try {
				Thread.sleep((int)Math.random()*10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int item = (int) (Math.random()*10);
			
			//push item
			buffer.push(item);
		}

	}

}
