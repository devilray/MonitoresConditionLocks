package threads;

import messagepassing.MailBox;

import static main.Programa.*;

public class Hilo extends Thread {

	private final int identificator;
	private int n, min;
	private MailBox[] mailboxes;
	private MailBox sharedScreen;
	
	public Hilo(int identificator, int n, MailBox[] mailboxes, MailBox sharedScreen) {
		this.identificator = identificator;
		this.mailboxes = mailboxes;
		this.sharedScreen = sharedScreen;
		this.n = n;
		this.min = n;
	}
	
	/**
	 * Run() -> Los hilos mandan su valor a todos los demas, pues este valor es el que queremos que sepan el resto de hilos. 
	 * Por lo tanto se lo mando a los otros 29, con la excepcion del emisor (no hace falta que el hilo se mande su valor
	 * a sí mismo).
	 * Posteriormente cada hilo irá recibiendo el valor de los demás y dicho valor lo voy almacenando en una variable 
	 * auxiliar para ir comparándolo con el minimo provisional.
	 * Una vez que ya tengamos el valor minimo de todos los recibidos tendremos que imprimirlo por pantalla. Sin embargo
	 * hemos de hacerlo correctamente, pues la pantalla es un recurso comparido al cual tenemos que acceder en exlusion
	 * mutua. Lo ideal es que cuando un hilo termine de calcular el minimo use la pantalla, sin embargo, no sabemos cual va
	 * a terminar su ejecución primero, pues todos se ejecutan de forma concurrente. Algun hilo tiene que ser el primero 
	 * en usar la pantalla (de lo contrario se podria producir un deadlock) y el hilo elegido es el que tiene el id 1.
	 * Todos los hilos se van a poner a esperar a que la pantalla se quede libre excepto el que tenga el id 1, que es el
	 * unico que no quedará bloqueado. Al final cuando se imprime su valor y el minimo, dicho hilo deja libre la pantalla, 
	 * la cual será cogida por otro hilo de los que estaban bloqueados.
	 * Siempre se imprime primero el hilo 1 por esta razón.
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
