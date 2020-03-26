/*
* @author Guillermo Girón García
* Software que crea la ventana base del software
* de simulación de las prácticas de la asignatura
*/

import java.util.concurrent.*;

public class Main
{
    public static void main(String[] args) 
    {
        /// Añado la interfaz a una cola de eventos
        // del paquete de dibujo, en un hilo propio
        /*java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run() 
            {
                new BaseFrame();      
            }
        });*/

        int nThreads = Runtime.getRuntime().availableProcessors();
        randomGenerator r = new randomGenerator(1000l, 7, 45); 
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        for(int rule = 0; rule < 256; ++rule)
        {
            ca1DSimulator.setCA(rg.getGenerated(), 2, 1, false, rule, 4000, nThreads);
            
        }
    }
}