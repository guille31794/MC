/*
* @author Guillermo Girón García
* @version 1.0
* Clase que implementa el algoritmo de Mandelbrot
* de forma concurrente
*/

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class MandelbrotParalel extends JFrame implements Runnable
{
    private final int MAX_ITER = 100000;
    private final double ZOOM = 150;
    private static BufferedImage Imagen;
    private double zx, zy, cX, cY, tmp;
    private int start, end;
    private final static int height = 600, width = 800,
    originX = 100, originY = 100;

  public MandelbrotParalel(int s, int e) 
  {
    super("Conjunto de Mandelbrot");
    setBounds(originX, originY, width, height);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    start = s;
    end = e;
  }

  public static void setParameters()
  {
      Imagen = new BufferedImage(width(), height(), BufferedImage.TYPE_INT_RGB);
  }

  @Override
  public void run() 
  {
      // aqui comienza la rutina a paralelizar
      for (int y = start; y < end; y++) 
      {
          for (int x = 0; x < getWidth(); x++) 
          {
              zx = zy = 0;
              cX = (x - 400) / ZOOM;
              cY = (y - 300) / ZOOM;
              int iter = MAX_ITER;
              while (zx * zx + zy * zy < 4 && iter > 0) 
              {
                  tmp = zx * zx - zy * zy + cX;
                  zy = 2.0 * zx * zy + cY;
                  zx = tmp;
                  iter--;
              }

              Imagen.setRGB(x, y, iter | (iter << 8));
          }
      } // aqui finaliza la rutina a paralelizar
  }

  public void paint(Graphics g) 
  {
    g.drawImage(Imagen, 0, 0, this);
  }

  public static int height()
  {
      return height;
  }

  public static int width()
  {
      return width;
  }

  public static void main(String[] args) 
  {
        int nThreads = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads);
        int start = 0, frame = height() / nThreads, end = frame;
        MandelbrotParalel m = null;
        setParameters();
        long iniTime = System.currentTimeMillis(), totalTime = 0;

        for(int i = 0; i < nThreads; ++i)
        {
            tpe.execute(m = new MandelbrotParalel(start, end));
            start = end;
            end += frame; 
        }

        if(end != height())
        {
            tpe.execute(m = new MandelbrotParalel(start, 600));
        }

        tpe.shutdown();

        try 
        {
            if(tpe.awaitTermination(10, TimeUnit.SECONDS))
                tpe.shutdownNow();
        } catch (InterruptedException e) {}
        
        totalTime = System.currentTimeMillis() - iniTime;
        System.out.println("El tiempo utilizado has sido " + totalTime + "ms." );

        m.repaint();
        m.setVisible(true);
  }
}