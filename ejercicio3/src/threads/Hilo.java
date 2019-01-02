package threads;

import java.util.Random;

import static main.SharedMemory.*;

/**
 * Clase que representa los procesos que van a escribir en pantalla utilizando los monitores.
 * @author Juan José Marín Peralta
 * @version 3.0
 */
public class Hilo extends Thread {

	private final int identificator;
	private int value;
	
	/**
	 * Crea un proceso con identificador único.
	 * @param identificator Valor numérica e inmutable que identifica a cada proceso.
	 */
	public Hilo(int identificator) {
		this.identificator = identificator;
	}
	
	/**
	 * Método de obtención del Identificador del proceso.
	 * @return ID del proceso.
	 */
	public int getIdentificator() {
		return identificator;
	}

	/**
	 * Método de obtención del valor de cada proceso.
	 * @return Valor del proceso
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Ejecutamos el hilo para entrar al monitor.
	 */
	@Override
	public void run() {
		
		try {
			Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		value = new Random().nextInt(4) + 1;
		
		monitor.inMonitor(this);
		
		try {
			Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		monitor.outMonitor(this);
		
	}
	
}
