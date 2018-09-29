import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.*;

public class parallelTumoralGrowth implements Runnable{
	static Random rn = new Random();
	static boolean start = false;
	static int[][] actual, nueva;

	private static float ps, pd, pm, pp, pq, NP;
	private static float rr, rrm, rrp;
	private static int[][] PH;
	static float p1,p2,p3,p4;

	static int genActual=0;
	static int dim;

	static JFrame frame = new JFrame();
	static panelImage panelimg;

	static ExecutorService ex;
	static CyclicBarrier barrera;
	
	int ini, fin;
	static int nHilos = 1;

	static int ngen=0;
		int generaciones=0,generaciones1=0;
	static int max = 0;
	static int vivas[];

	static int cells = 1;

	private static JMenuBar BarraMenu;
	private static JMenu Ayuda,AcercaDe;
	private static JMenuItem ayuda,acercade;

	public parallelTumoralGrowth(int ini, int fin){
		this.ini=ini;
		this.fin=fin;
		generaciones = ngen;
	}

	public void run(){
		caComputation();
	}


	public static void init(){
		vivas = new int[max];
		actual=new int[dim][dim];
		nueva=new int[dim][dim];
		PH=new int[dim][dim];

		// Inicial.
		if(cells==1){
			actual[dim/2][dim/2]=1;
		}
		else{
			for(int i=1; i<=cells;i++){
				actual[(int)(Math.random()*dim)][(int) (Math.random()*dim)]=1;
			}
		}
		

	}

	public static void update(){
		for (int x=0; x<dim;++x) {
			for(int y=0; y<dim;++y){
			            Color c=new Color((actual[x][y]*125)%255,(actual[x][y]*125)%255,(actual[x][y]*125)%255);
			            panelimg.image.setRGB(x, y, c.getRGB());
	        }
	        
        }

		panelimg.repaint();
	}

	public static void restart(){
		ngen = 0;
        panelimg.setVisible(false);
    }

	public static void runSimulation(int dim_, float ps_, float pm_, float pp_, int NP_){

		dim = dim_;
		init();

		ps=ps_;
		pm=pm_;
		pp=pp_;
		NP=NP_;

		panelimg = new panelImage(dim);
		panelimg.setVisible(true);
		frame.add(panelimg,BorderLayout.CENTER);

		frame.validate();
		frame.repaint();

		ex = Executors.newFixedThreadPool(nHilos);

		barrera = new CyclicBarrier(nHilos, 
			new Runnable() {
				public void run() {
                    update();
                    for(int i = 0; i < actual.length; ++i){
						actual[i] = nueva[i].clone();
					}
                }
            }
        );

		parallelTumoralGrowth tum;
		for(int i=0;i<nHilos;++i){
			tum = new parallelTumoralGrowth(i*dim/nHilos,(i+1)*dim/nHilos);
			ex.execute(tum);
		}
		ex.shutdown();
	}
	
	public void nextGen(){


		for(int i=ini;i<fin;++i){
			if(i!=0 && i!=dim){

				for(int j=1;j<dim-1;++j){
					
					if (rn.nextFloat() < ps){

						if (rn.nextFloat() < pp){
							synchronized(barrera){
								PH[i][j]++;
								if(PH[i][j] >= NP) proliferation(i,j,generaciones1);
								else migration(i,j);
							}
							
						}
						else{
							migration(i,j);
						}
						
					}
					else{
						synchronized(barrera){
							nueva[i][j]=0;
							PH[i][j]=0;
						}
						
					}
				}

			}
			
		}
	}

	static void proliferation(int i, int j, int gen){
		if(actual[i][j]==1){
			updateCell(i,j,probs(i,j));
			vivas[gen]++;
			PH[i][j]=0;
		}
		
	}

	static void migration(int i, int j){
		if(rn.nextFloat() < pm){
			if(actual[i][j]==1){
				updateCell(i,j,probs(i,j));
				nueva[i][j]=0;
				PH[i][j]=0;
			}
		}
		
	}

	static float[] probs(int i, int j){
		float denominador = (4-actual[(i+1)%dim][j]-actual[(i-1+dim)%dim][j]-actual[i][(j+1)%dim]-actual[i][(j-1+dim)%dim]);

		p1 = (1-actual[(i-1+dim)%dim][j])/denominador;
		p2 = (1-actual[(i+1)%dim][j])/denominador;
		p3 = (1-actual[i][(j-1+dim)%dim])/denominador;
		p4 = (1-actual[i][(j+1)%dim])/denominador;

		float[] res={p1,p2,p3,p4};
		return res;
	}

	static void updateCell(int i, int j, float[] p){
		if (rn.nextFloat() <= p[0]){
			if(nueva[(i-1+dim)%dim][j]==0){
				nueva[(i-1+dim)%dim][j]=1;
				PH[(i-1+dim)%dim][j]=0;
			}
		}
		else if (rn.nextFloat() <= p[0]+p[1]){
			if(nueva[(i+1)%dim][j]==0){
				nueva[(i+1)%dim][j]=1;
				PH[(i+1)%dim][j]=0;
			}
		}
		else if (rn.nextFloat() <= p[0]+p[1]+p[2]){
			if(nueva[i][(j-1+dim)%dim]==0){
				nueva[i][(j-1+dim)%dim]=1;
				PH[i][(j-1+dim)%dim]=0;

			}
		}
		else if (rn.nextFloat() <= p[0]+p[1]+p[2]+p[3]){
			if(nueva[i][(j+1)%dim]==0){
				nueva[i][(j+1)%dim]=1;
				PH[i][(j+1)%dim]=0;
			}
		}

	}

	public void caComputation(){
		try{
			while(generaciones!=generaciones1){
				nextGen();
				barrera.await();
				generaciones1++;		        
			}
		}
		
		catch(InterruptedException ie){}
		catch(BrokenBarrierException be){}
	}

	public static void main(String[] args) throws Exception{

panelOptions panelopt = new panelOptions();

		frame.setLayout(new BorderLayout());
        frame.add(panelopt,BorderLayout.EAST);
		
		frame.setVisible(true);
        frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit miPantalla = Toolkit.getDefaultToolkit(); //Se almacena en el objeto 'miPantalla' el sistema nativo la ventana. Sirve para poder centrar posteriormente la ventana
		Dimension tamPantalla = miPantalla.getScreenSize(); //En 'tamPantalla' almacenamos el tamaño de la pantalla del objeto 'miPantalla'
		int altPantalla = tamPantalla.height; //obtenemos la altura de nuesta pantalla en un entero		
		int anchPantalla = tamPantalla.width; //obtenemos la anchura de nuetra pantalla en un entero
		frame.setTitle("TumoralGrowth - Fernando Medina Delgado");

		//frame.setIconImage(new ImageIcon(getClass().getResource("/icons/triforce.png")).getImage());
		frame.setSize(anchPantalla-700, altPantalla-300);
		frame.setLocation(anchPantalla/4, altPantalla/8);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//setResizable(false);
		
		Dimension tamMinimo = new Dimension(anchPantalla/4, altPantalla/4);
		frame.setMinimumSize(tamMinimo);
		frame.getContentPane().setBackground(new Color(186,205,200));
		BarraMenu = new JMenuBar();
		BarraMenu.setBackground(new Color(180,0,50));
		frame.setJMenuBar(BarraMenu);

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
		//ayuda.addActionListener(this);
		//acercade.addActionListener(this);
		Ayuda.add(ayuda);
		AcercaDe.add(acercade);
		BarraMenu.add(Ayuda);
		BarraMenu.add(AcercaDe);               
	}
}

class panelImage extends JPanel{
	static BufferedImage image;

	public panelImage(int dim){
		image = new BufferedImage(dim, dim, BufferedImage.TYPE_INT_ARGB);
	}


    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}

class panelOptions extends JPanel{
   	private JTextField boxDim = new JTextField("200");
    private JLabel lbDim = new JLabel(" Dimension: ");
    private JTextField boxGen = new JTextField("200");
    private JLabel lbGen = new JLabel(" Generaciones : ");
    private JTextField boxPS = new JTextField("100");
    private JLabel lbCell = new JLabel(" Celulas iniciales : ");
    private JTextField boxCell = new JTextField("1");
    private JLabel lbPS = new JLabel(" Supervivencia %: ");
    private JTextField boxPM = new JTextField("25");
    private JLabel lbPM = new JLabel(" Migracion %: ");
    private JTextField boxPP = new JTextField("90");
    private JLabel lbPP = new JLabel(" Proliferacion %: ");
    private JTextField boxNP = new JTextField("1");
    private JLabel lbNP = new JLabel(" NP: ");

    private JComboBox config = new JComboBox();
    private JLabel lbConf = new JLabel(" Configuraciones: ");

    private JButton btnStart = new JButton("Iniciar");
    private JButton btnGrafic = new JButton("Grafica");

    panelOptions(){
        setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
        setLayout(new GridLayout(9,2,1,1));
		setBackground(new Color(220,200,155));
		
        add(lbGen); 
        add(boxGen);
        add(lbCell); 
        add(boxCell);
        add(lbDim); 
        add(boxDim);
        add(lbPS);
        add(boxPS);
        add(lbPM);
        add(boxPM);
        add(lbPP);
        add(boxPP);
        add(lbNP);
        add(boxNP);

        config.addItem("Ninguno");
		config.addItem("A");
		config.addItem("B");
		config.addItem("C");
		config.addItem("D");

        
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	Thread hiloSim;
                if(btnStart.getText().equals("Iniciar")){
                	
                    btnStart.setText("Reiniciar");
                   	parallelTumoralGrowth.ngen= Integer.parseInt(boxGen.getText());
                   	parallelTumoralGrowth.max= Integer.parseInt(boxGen.getText());
                   	parallelTumoralGrowth.cells= Integer.parseInt(boxCell.getText());
                    hiloSim = new Thread(()->{parallelTumoralGrowth.runSimulation(Integer.parseInt(boxDim.getText()),
                    							 (float)(Integer.parseInt(boxPS.getText()))*0.01f, 
                    							 (float)(Integer.parseInt(boxPM.getText()))*0.01f,
                    							 (float)(Integer.parseInt(boxPP.getText()))*0.01f,
                    							 Integer.parseInt(boxNP.getText())
                    							);});

                    
                    hiloSim.start();
                    

                }
                else if(btnStart.getText().equals("Reiniciar")) {
                    parallelTumoralGrowth.restart();

                    btnStart.setText("Iniciar");
                     
                }
            }
        });

        btnGrafic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	Thread hiloSim;
                if(btnGrafic.getText().equals("Grafica") && btnStart.getText().equals("Reiniciar")){
                	curvasPoblacion objeto = new curvasPoblacion(parallelTumoralGrowth.vivas, Integer.parseInt(boxDim.getText()));
                }
                else if(btnStart.getText().equals("Iniciar")){
                	JOptionPane.showMessageDialog(null,"Inicia la simulacion para ver la grafica","AVISO",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        config.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	switch(config.getSelectedIndex()){

            		case 0:
            			boxDim.setText("200");
            			boxGen.setText("200");
            			boxPS.setText("100");
            			boxPM.setText("25");
            			boxPP.setText("90");
            			boxNP.setText("1");

            		break;//Ninguno

            		case 1:
            			boxDim.setText("300");
            			boxGen.setText("1000");
            			boxPS.setText("100");
            			boxPM.setText("20");
            			boxPP.setText("25");
            			boxNP.setText("1");
            		break;//A

            		case 2:
            			boxDim.setText("300");
            			boxGen.setText("1000");
            			boxPS.setText("100");
            			boxPM.setText("80");
            			boxPP.setText("25");
            			boxNP.setText("1");      		
            		break;//B

            		case 3:
            			boxDim.setText("300");
            			boxGen.setText("1000");
            			boxPS.setText("100");
            			boxPM.setText("20");
            			boxPP.setText("25");
            			boxNP.setText("2");
            		break;//C

            		case 4:
            			boxDim.setText("300");
            			boxGen.setText("1000");
            			boxPS.setText("100");
            			boxPM.setText("80");
            			boxPP.setText("25");
            			boxNP.setText("2");
            		break;//D
            	}

            }
        });
        add(lbConf);
        add(config);
        add(btnStart);
        add(btnGrafic);
    }
}