package monitor;

public class Monitor {

	private volatile int comp;
	
	public Monitor(int comp) {
		this.comp = comp;
	}
	
	synchronized public void down(int cant) {
		while(cant > comp) 
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		comp -= cant;
		System.out.println("Variable = " + comp);
	}
	
	synchronized public void up(int cant) {
		comp += cant;
		notify();
		//notifyAll();
		System.out.println("Variable = " + comp);
	}
	
}
