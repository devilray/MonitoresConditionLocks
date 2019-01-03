package monitor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {

	private ReentrantLock lock = new ReentrantLock();
	private Condition noLLeno = lock.newCondition();
	private Condition noVacio = lock.newCondition();
	private Object buffer[];
	private int in, out, size;
	
	public Monitor(int size) {
		this.buffer = new Object[size];
		for (int i = 0; i < buffer.length; i++) buffer[i] = null;
		this.in = this.out = this.size = 0;
	}
	
	public void push(Object item) throws InterruptedException {
		try {
			lock.lock();
			
			//delay(noLleno)
			while(size >= buffer.length) noLLeno.await();
			
			buffer[in] = item;
			in = (in+1)%buffer.length;
			size++;
			System.out.println(this);
			
			//resume(noVacio)
			noVacio.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public Object pop() throws InterruptedException {
		try {
			lock.lock();
			
			//delay(noVacio)
			while(size <= 0) noVacio.await();
			
			Object item = buffer[out];
			buffer[out] = null;
			out = (out+1)%buffer.length;
			size--;
			System.out.println(this);
			
			//resume(noLleno)
			noLLeno.signal();
			
			return item;
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public String toString() {
		String texto = new String();
		for (int i = 0; i < buffer.length; i++) 
			texto = texto.concat(String.valueOf(buffer[i]) + " ");
		
		return texto;
	}
	
}
