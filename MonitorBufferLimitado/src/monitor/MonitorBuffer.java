package monitor;

public class MonitorBuffer {

	private int buf[], in, out, tama;
	
	public MonitorBuffer(int ele) {
		this.buf = new int[ele];
		for (int i = 0; i < buf.length; i++) buf[i] = 0;
		this.in = this.out = this.tama = 0;
	}
	
	synchronized public void push(int ele) {
		while(tama >= buf.length)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		buf[in] = ele;
		in = (in+1)%buf.length;
		tama++;
		System.out.println(this);
		notifyAll();
	}
	
	synchronized public int pop() {
		int ele = 0;
		while(tama <= 0)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		ele = buf[out];
		buf[out] = 0;
		out = (out+1)%buf.length;
		tama--;
		System.out.println(this);
		notifyAll();
		
		return ele;
	}
	
	synchronized public String toString() {
		String texto = new String();
		for (int i = 0; i < buf.length; i++) {
			texto = texto.concat(String.valueOf(buf[i]) + " ");
		}
		return texto;
	}
	
}
