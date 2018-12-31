package monitor;

import static main.SharedMemory.*;

import threads.Hilo;

/**
 * Clase que representa el proceso que va a acceder al monitor.
 * @author Juan José Marín Peralta
 * @version 3.0
 */
public class Monitor {

	private volatile boolean threadGotValue;
	
	/**
	 * Crea un Monitor que inicia el conjunto y sus valores a 0
	 */
	public Monitor() {
		for (int i : set) {
			i = 0;
		}
		
	}
	
	/**
	 * Método sincronizado que realiza la entrada al monitor para obtener su valor
	 * @param hilo Proceso que va a entrar el monitor.
	 */
	synchronized public void inMonitor(Hilo h) {
		int result = 0;
		set[h.getIdentificator() - 1] = h.getValue();
		while((result < set.length) && (set[result] != 0)) result++;
		if(result == set.length) threadGotValue = true;
	}
	
	/**
	 * Método sincronizado que va a imprimir por pantalla a través del monitor.
	 * @param hilo Proceso que va a salir el monitor.
	 */
	synchronized public void outMonitor(Hilo h) {
		while(!threadGotValue)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		int cont = 0;
		
		for (int i : set) {
			if(i == h.getValue()) cont++;
		}
		
		System.out.println("Hilo -> " + h.getIdentificator());
		System.out.println("Valor Asignado -> " + h.getValue());
		System.out.println("Hilos con el mismo valor -> " + cont + "\n\n");
		
		notifyAll();
	}
	
}
