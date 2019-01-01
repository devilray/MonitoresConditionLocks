package threads;

import main.Matrix;
import paneles.Panel;

import static main.SharedMemory.*;
import static main.Programa.*;

/**
 * Clase que representa los procesos que van a escribir en cada panel.
 * @author Juan José Marín Peralta
 * @version 3.0
 */
public class Hilo extends Thread {

	private final int identificator;
	private Matrix Ma, Mb, Mresult;
	
	/**
	 * Constuye un proceso con un identificador único para cada Hilo.
	 * @see java.lang.Thread
	 * @param id Representa un número entero que identifica cada hilo y el cuál es inmutable.
	 */
	public Hilo(int identificator) {
		this.identificator = identificator;
		this.Ma = new Matrix(CANT_THREADS, CANT_THREADS);
		this.Mb = new Matrix(CANT_THREADS, CANT_THREADS);
	}

	/**
	 * Método para saber el identificador único e inmutable de cada hilo en su creación.
	 * @return ID único para cada hilo.
	 */
	public int getIdentificator() {
		return identificator;
	}
	
	/**
	 * run-> Pone en marcha el hilo.
	 * CONDICIONES DE SINCRONIZACION
	 * -Que los hilos no usen el mismo panel al mismo tiempo
	 * 
	 * EXLUSION MUTUA:
	 * La exlusion mutua de la pantalla se comprueba poniendo a false la posicion correspondiente del array de
	 * booleanos. De esta forma otro que venga por detras no va a coger el mismo panel.
	 * La exlusion mutua del array de booleanos se garantiza utilizando un semaforo mutex binario que 
	 * se bloquee cuando eestamos accediendo a el. De esta forma cuando dos hilos entran al array al buffer 
	 * al mismo tiempo evitamos que se produzca una situacion en la que dos hilos puedan acceder a la seccion
	 * critica al mismo tiempo.
	 * 
	 */
	@Override
	public void run() {
		
		for (int i = 0; i < DIM_MATRIX; i++) {
			//variable auxiliar para controlar cada panel ocupado
			int aux = 0;
			
			//generamos las matrices de valores aleatorios de A y de B
			Ma.newRandomMatrix();
			Mb.newRandomMatrix();
			
			//guardamos en C el resultado de sumar A y B
			Mresult = Ma.sumMatrix(Mb);
			
			//begin sección crítica.
			
			//wait() semáforo general que representa el semáforo para los paneles libres
			try {
				panelesLibres.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//wait() semáforo binario que representa un semáforo que realiza la exclusión mutua.
			try {
				mutex.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//indicamos en cada panel que se está usando y que éste no está libre
			while(isPanelLibre[aux] == false) aux++;
			
			Panel panel = paneles[aux];
			isPanelLibre[aux] = false;
			
			//signal() de semáforo binario mutex
			mutex.release();
			
			//escribir mensaje en cada uno de los paneles
			panel.escribir_mensaje("Usando panel el hilo " + identificator);
			panel.escribir_mensaje("Matrix A = \n" + Ma.printMatrix());
			panel.escribir_mensaje("Matrix B = \n" + Mb.printMatrix());
			panel.escribir_mensaje("Matrix C = \n" + Mresult.printMatrix());
			panel.escribir_mensaje("Terminando de usar panel el hilo " + identificator);
			
			//wait() de semáforo binario mutex
			try {
				mutex.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//Indicamos que cada panel que se acaba de utilizar vuelve a estar disponible para el siguiente hilo.
			isPanelLibre[aux] = true;
			
			//signal() de semáforo binario mutex
			mutex.release();
			
			//signal() de cada uno de los tres semáforos binarios para escribir en cada uno de los tres paneles.
			panelesLibres.release();
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//end sección crítica
			
		}
		
	}
	
}
