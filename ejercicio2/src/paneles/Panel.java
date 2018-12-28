package paneles;

import java.awt.Dimension;
import java.awt.FlowLayout;


import javax.swing.*;

/**
 * Construye una ventana que permite que un hilo muestre mensajes por ella 
 *  a traves del metodo
 *  
 * 			escribir_mensaje(String str);
 * 
 * Por tanto, se puede utilizar para que en el caso que necesitemos que un
 * conjunto de hilos compartan una consola para mostrar mensajes independiente 
 * de la que utilizan otros conjuntos de hilos. 
 * 
 * 
 * @author Jos� Tom�s Palma
 */

public class Panel
{
    /** La ventana */
    private JFrame v;

    
    /** El campo de texto */
    private JTextArea t;
    
 
    
    
    /**
     * @param Cadena que aparecer� en el t�tulo de la ventana
     * @param posx, coordenada x de la esquina superior izquierda de la ventana
     * @param posy, coordenada y de la esquina superior izquierda de la ventana
     */
    public Panel(String _nombre, int posx, int posy)
    {

        // Nueva ventana.
        v = new JFrame(_nombre);
        v.setLocation(posx, posy);
        v.getContentPane().setLayout(new FlowLayout());
        
        
        // Se crea el campo de texto y se mete en la ventana
        //t = new JTextArea(40,50);
        t = new JTextArea(30,30);
        //JScrollPane s = new JScrollPane(t); 
        t.setEditable(false);
      
        
        JScrollPane areaScrollPane = new JScrollPane(t);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(400, 500));
        v.getContentPane().add(areaScrollPane);
        
      
        // Se le dice a la ventana que termine el programa cuando se la cierre
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        // Se le da un tama�o autom�tico a la ventana para que quepa todo su
        // contenido.
        v.pack();
        
        // Se hace visible la ventana
        v.setVisible(true);
    }
    
    public void escribir_mensaje(String str){
    	t.append (str + "\n");
    	t.setCaretPosition(t.getCaretPosition()+str.length());
    }

}



