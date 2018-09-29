import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.util.concurrent.*;
import java.lang.*;

public class mandelSet extends JFrame implements Runnable {
	
	private final int MAX_ITER = 1000000; //Iteraciones 
	private final double ZOOM = 150;
	private static BufferedImage Imagen;
	private double zx, zy, cX, cY, tmp;
	private static int nHilos=16; //numero de hilos

	int min, max;

	public mandelSet() {
		super("Mandelbrot Paralelo"); //titulo
		setBounds(100, 100, 800, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Imagen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
	}

	public mandelSet(int minimo, int maximo) {
		this.min=minimo;
		this.max=maximo;
	}

	public void run() {

		for (int y = min; y < max; y++) {	
			for (int x = 0; x < 800; x++) {	
				zx = zy = 0;
				cX = (x - 400) / ZOOM;
				cY = (y - 300) / ZOOM;
				int iter = MAX_ITER;
				while (zx * zx + zy * zy < 4 && iter > 0) {
					tmp = zx * zx - zy * zy + cX;
					zy = 2.0 * zx * zy + cY;
					zx = tmp;
					iter--;
				}
				Imagen.setRGB(x, y, iter | (iter << 8));
				repaint();
			}
		}
	}

	public void paint(Graphics g) {
		g.drawImage(Imagen, 0, 0, this);
	}

	public static void main(String[] args) throws Exception {
		mandelSet ventana = new mandelSet();
		
		ventana.validate();
		ventana.repaint();
		ExecutorService pool=Executors.newFixedThreadPool(nHilos); //Pool de nHilos
		int dimy=600;
		mandelSet objeto;

		long timeIni=0,timeFin=0;

		timeIni = System.currentTimeMillis(); //Comienza el crono

		for(int i=0;i<nHilos;++i){
			objeto = new mandelSet(i*dimy/nHilos,(i+1)*dimy/nHilos); 
			pool.execute(objeto);
		}

		pool.shutdown();						
		pool.awaitTermination(1, TimeUnit.DAYS); //Detiene el hilo main hasta que acaben el resto de hilos
		timeFin = System.currentTimeMillis(); //Fin del crono

		System.out.println("Tiempo: "+(timeFin-timeIni)+" ms"); //Tiempo medido en milisegundos
		ventana.setVisible(true); //Visualiza la ventana
	}
}