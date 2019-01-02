package threads;

import messagepassing.CommunicationScheme;

import static main.Programa.*;

/**
 * Clase que representa un proceso que va a utilizar el buzón.
 * @author Juan José Marín Peralta
 * @version 3.0
 */
public class Hilo extends Thread {

	private final int identificator;
	private int n, min;
	private CommunicationScheme[] mailboxes;
	private CommunicationScheme sharedScreen;
	
	/**
	 * Crea un proceso con número de identificación único e inmutable, valor del hilo y esquema de comunicación a emplear, en nuestro caso, buzones para 
	 * paso de mensajes asíncrono.
	 * @param identificator Identificador numérico único e inmutable que representa a cada proceso.
	 * @param n Valor que maneja cada uno de los procesos.
	 * @param mailboxes Esquema que envía o recibe del Buzón central los mensajes para que los procese cada hilo.
	 * @param sharedScreen Buzón que envía o recibe los mensajes a la pantalla (Recurso No Compartible) donde se van a imprimir todos los hilos. 
	 */
	public Hilo(int identificator, int n, CommunicationScheme[] mailboxes, CommunicationScheme sharedScreen) {
		this.identificator = identificator;
		this.mailboxes = mailboxes;
		this.sharedScreen = sharedScreen;
		this.n = n;
		this.min = n;
	}
	
	/**
	 * Ejecuta los hilos para el paso de mensajes.
	 */
	@Override
	public void run() {
		
		for (int i = 0; i < CANT_THREADS; i++) {
			if(i != identificator - 1) mailboxes[i].send(n);
		}
		
		for (int i = 0; i < CANT_THREADS - 1; i++) {
			int result = (Integer) mailboxes[identificator - 1].receive();
			if(result < min) min = result;
		}
		
		if(identificator != 1) sharedScreen.receive();
		
		System.out.println("Hilo Nº " + identificator + " con número = " + n);
		System.out.println("Valor mínimo = " + min + "\n\n");
		
		sharedScreen.send("turno");
		
	}
	
}
