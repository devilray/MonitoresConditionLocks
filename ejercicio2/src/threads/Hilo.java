package threads;

import main.Matrix;
import paneles.Panel;

import static main.SharedMemory.*;
import static main.Programa.*;

/**
 * 
 * @author juanico
 *
 */
public class Hilo extends Thread /*implements Runnable*/ {

	private final int identificator;
	private Matrix Ma, Mb, Mresult;
	
	/**
	 * 
	 * @param identificator
	 */
	public Hilo(int identificator) {
		this.identificator = identificator;
		this.Ma = new Matrix(CANT_THREADS, CANT_THREADS);
		this.Mb = new Matrix(CANT_THREADS, CANT_THREADS);
	}

	/**
	 * 
	 * @return
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
			int aux = 0;
			
			Ma.newRandomMatrix();
			Mb.newRandomMatrix();
			Mresult = Ma.sumMatrix(Mb);
			
			try {
				panelesLibres.acquire();
				mutex.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			/*
			try {
				mutex.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			while(isPanelLibre[aux] == false) aux++;
			
			Panel panel = paneles[aux];
			isPanelLibre[aux] = false;
			
			mutex.release();
			
			panel.escribir_mensaje("Usando panel el hilo " + identificator);
			panel.escribir_mensaje("Matrix A = \n" + Ma.printMatrix());
			panel.escribir_mensaje("Matrix B = \n" + Mb.printMatrix());
			panel.escribir_mensaje("Matrix C = \n" + Mresult.printMatrix());
			panel.escribir_mensaje("Terminando de usar panel el hilo " + identificator);
			
			try {
				mutex.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			isPanelLibre[aux] = true;
			
			mutex.release();
			panelesLibres.release();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
