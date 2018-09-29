import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.*;
import java.lang.Math;
import java.awt.event.*;

import java.lang.*;

public class parallelBelZab extends JFrame implements ca2DSim, Runnable,ActionListener{
	//static boolean start = false;
	static belZabGraphicSimulation bzg = new belZabGraphicSimulation();
	static int dim;
	static int nGen;
	int ini, fin;

	static float [][][] a;
	static float [][][] b;
	static float [][][] c;
	int p = 0;
	int q = 1;
	static float alfa;
	static float beta;
	static float gamma;

	static Color color[][];

	static ExecutorService ex;
	static CyclicBarrier barrera;

	private static JMenuBar BarraMenu;
	private static JMenu Ayuda,AcercaDe;
	private static JMenuItem ayuda,acercade;
	private static JComboBox ListaProgramas;

	//private  static long timeIni, timeFin, time;

	public parallelBelZab(int ini, int fin){
		this.ini=ini;
		this.fin=fin;
	}

	public void run(){
		caComputation(nGen);
	}


	public static void runSimulation(int d, int ngen, float alf, float bet, float gamm){
		int nHilos=16;
		
		dim=d;
		nGen=ngen;
		alfa=alf;
		beta=bet;
		gamma=gamm;

		setup();

		ex = Executors.newFixedThreadPool(nHilos);

		barrera = new CyclicBarrier(nHilos, new Runnable() {public void run() {belZabGraphicSimulation.update();}});

		parallelBelZab bz;
		//timeIni = System.currentTimeMillis();

		//try{
				for(int i=0;i<nHilos;++i){
					bz = new parallelBelZab(i*parallelBelZab.dim/nHilos,(i+1)*parallelBelZab.dim/nHilos);
					ex.execute(bz);
				}
				ex.shutdown();
			//	ex.awaitTermination(1, TimeUnit.DAYS);
		//}catch(InterruptedException eeep){}


		/*timeFin = System.currentTimeMillis();
        time = timeFin - timeIni;
		System.out.println("Tiempo ejecucion: "+time+" milisegundos");*/
	}

	public void nextGen(){
		for (int x = ini; x < fin ; x++){
			for (int y = 0; y < dim ; y++){
				float c_a = 0.0f;
				float c_b = 0.0f;
				float c_c = 0.0f;
				for (int i = x-1; i <= x+1; i++){
					for (int j = y-1; j <= y+1; j++) {
						c_a += a[(i+ dim)%dim][(j+dim)%dim][p];
						c_b += b[(i+ dim)%dim][(j+dim)%dim][p];
						c_c += c[(i+ dim)%dim][(j+dim)%dim][p];
					}
				}
				c_a /= 9.0f;
				c_b /= 9.0f;
				c_c /= 9.0f;
				a[x][y][q] = constrain(c_a+c_a*(alfa*c_b-gamma*c_c),0.0f,1.0f);
				b[x][y][q] = constrain(c_b+c_b*(beta*c_c-alfa*c_a),0.0f,1.0f);
				c[x][y][q] = constrain(c_c+c_c*(gamma*c_a-beta*c_b),0.0f,1.0f);

				color[x][y]=new Color(a[x][y][q],b[x][y][q],c[x][y][q]);
			}
		}
		if(p==0){
			p = 1;
			q = 0;
		}
		else {
			p = 0;
			q = 1;
		}	
	}

	public void caComputation(int nGen){
		try{
			while(nGen>0){
				nextGen();
				nGen--;
				barrera.await();
			}
		}
		
		catch(InterruptedException ie){}
		catch(BrokenBarrierException be){}
	}

	public static float constrain(float valor, float min, float max){
        return Math.min(Math.max(valor, min),max); 
	}

	public static void setup(){
		color = new Color[dim][dim];
		Random r = new Random();
		a = new float [dim][dim][2];
		b = new float [dim][dim][2];
		c = new float [dim][dim][2];
		for (int x = 0; x < dim ; x ++) {
			for (int y = 0; y < dim ; y ++) {
				a[x][y][0] = r.nextFloat();
				b[x][y][0] = r.nextFloat();
				c[x][y][0] = r.nextFloat();
			}
		}
	}

	public parallelBelZab(){

		setLayout(new BorderLayout());
		Toolkit miPantalla = Toolkit.getDefaultToolkit(); //Se almacena en el objeto 'miPantalla' el sistema nativo la ventana. Sirve para poder centrar posteriormente la ventana
		Dimension tamPantalla = miPantalla.getScreenSize(); //En 'tamPantalla' almacenamos el tamaño de la pantalla del objeto 'miPantalla'
		int altPantalla = tamPantalla.height; //obtenemos la altura de nuesta pantalla en un entero		
		int anchPantalla = tamPantalla.width; //obtenemos la anchura de nuetra pantalla en un entero
		setTitle("ParallelBelZab - Fernando Medina Delgado");
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);

		setIconImage(new ImageIcon(getClass().getResource("/icons/triforce.png")).getImage());
		setSize(anchPantalla-700, altPantalla-300);
		setLocation(anchPantalla/4, altPantalla/8);
		setExtendedState(this.MAXIMIZED_BOTH);
		//setResizable(false);
		
		Dimension tamMinimo = new Dimension(anchPantalla/4, altPantalla/4);
		setMinimumSize(tamMinimo);
		getContentPane().setBackground(new Color(186,205,200));
		BarraMenu = new JMenuBar();
		BarraMenu.setBackground(new Color(180,0,50));
		setJMenuBar(BarraMenu);

		Ayuda = new JMenu("Ayuda");
		AcercaDe = new JMenu("Acerca de...");
		ayuda = new JMenuItem("Guia de uso");
		acercade = new JMenuItem("Informacion");

		Ayuda.setBackground(new Color(120,0,50));
		Ayuda.setFont(new Font("Comic Sans MS", 1, 14));
		Ayuda.setForeground(new Color(220,220,220));
		AcercaDe.setBackground(new Color(120,0,50));
		AcercaDe.setFont(new Font("Comic Sans MS", 1, 14));
		AcercaDe.setForeground(new Color(220,220,220));

		//eventos barra menu
		ayuda.addActionListener(this);
		acercade.addActionListener(this);
		Ayuda.add(ayuda);
		AcercaDe.add(acercade);
		BarraMenu.add(Ayuda);
		BarraMenu.add(AcercaDe);
		
		add(bzg.mainBZ, BorderLayout.CENTER);
        setVisible(true);
		
	}
public void actionPerformed(ActionEvent e){
		JFrame marcoSecundario1, marcoSecundario2;
		JLabel label1,label2;
		JTextArea text1, text2;
		JScrollPane pane1,pane2;

		if(e.getSource() == ayuda){
			marcoSecundario1 = new JFrame();
			marcoSecundario1.setLayout(null);
			marcoSecundario1.setResizable(false);
			marcoSecundario1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			marcoSecundario1.setVisible(true);
			marcoSecundario1.setBounds(20,20,900,500);
			marcoSecundario1.setTitle("Ayuda");
			marcoSecundario1.setIconImage(new ImageIcon(getClass().getResource("/icons/ayuda.jpg")).getImage());

			label1 = new JLabel("GUIA DE USO");
			label1.setBounds(380,5,200,30);
			label1.setFont(new Font("Gill Sans", 1, 16));
			label1.setForeground(Color.BLACK);
			marcoSecundario1.add(label1);

			text1 = new JTextArea();
			text1.setEditable(false);
			text1.setFont(new Font("Gill Sans", 0, 12));
			text1.setText("\n\n          GUIA DE USO");

			pane1 = new JScrollPane(text1);
			pane1.setBounds(10,40,875,400);
			marcoSecundario1.add(pane1);
		}//ayuda

		if(e.getSource() == acercade){
			marcoSecundario2 = new JFrame();
			marcoSecundario2.setLayout(null);
			marcoSecundario2.setResizable(false);
			marcoSecundario2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			marcoSecundario2.setVisible(true);
			marcoSecundario2.setBounds(1000,20,900,500);
			marcoSecundario2.setTitle("Acerca De");
			marcoSecundario2.setIconImage(new ImageIcon(getClass().getResource("/icons/acercade.png")).getImage());

			label2 = new JLabel("INFORMACION");
			label2.setBounds(400,5,200,30);
			label2.setFont(new Font("Gill Sans", 1, 16));
			label2.setForeground(Color.BLACK);
			marcoSecundario2.add(label2);

			text2 = new JTextArea();
			text2.setEditable(false);
			text2.setFont(new Font("Gill Sans", 0, 12));
			text2.setText("\n\n          INFORMACION");

			pane2 = new JScrollPane(text2);
			pane2.setBounds(10,40,875,400);
			marcoSecundario2.add(pane2);
		}//AcercaDe
	}//ActionPerformed

	public static void main(String [] args)
	{
		System.out.println("ejecutando...");
		parallelBelZab objeto = new parallelBelZab();
	}
}

