/*
* @author Guillermo Girón García
* Software que crea la ventana base del software
* de simulación de las prácticas de la asignatura
*/

public class Main
{
    public static void main(String[] args) 
    {
        // Añado la interfaz a una cola de eventos
        // del paquete de dibujo, en un hilo propio
        java.awt.EventQueue.invokeLater(new Runnable()
        {
        
            @Override
            public void run() 
            {
                new BaseFrame();      
            }
        });
    }
}