import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.*;

public class ca1DSimulator extends JPanel implements Runnable{

	static int n=0,m=0;
    static int A[][]=new int[n][m];
    static int vector [] = new int[m];
    static int VectorRegla[];
    static int puntos[][] = new int [1000][16];
    static int vecinos = 0,estados =16, a=0;
    static boolean rand = false;
    static boolean flag = false; //Para no iniciar el metodo paint al crear un objeto de esta clase
    static boolean fronteras = false; //Inicializo la condicion de frontera a Nula
    static curvasPoblacion marcoEmergente;
    static Graphics w;
    static Graphics2D g2d;
    static ArrayList<Integer> array = new ArrayList<Integer>();
    CyclicBarrier barrera = null;

    int nNuc = Runtime.getRuntime().availableProcessors();
	int nHilos = (int)(nNuc/(1));
    ExecutorService pool;
    int id;

    public ca1DSimulator() {}

    public ca1DSimulator(int n, int m)
    {
    	this.n =n;
    	this.m = m;
    	A = new int [n][m];
    	
    }

    public ca1DSimulator(int id, CyclicBarrier barrera)
    {
    	this.barrera = barrera;
    	this.id=id;
    }

    public ca1DSimulator(int id)
    {
    	this.id = id;
    }

    public void AjustarDimensiones(int nColumnas, int nGeneraciones)
    {
    	n = nGeneraciones;
    	m = nColumnas;
    	new ca1DSimulator(n,m);
    }

    public void Random(ArrayList nAleatorios) {
	rand = true;
	array = nAleatorios;
    }

    public void NoRandom() {
	rand = false;
    }
    public void AsignarCondicionFrontera(boolean f)
    {
    	fronteras = f;
    }

    public void Reglas(){
	 
	for(int i=0;i<n;i++){
	    A[0][i]=0;
	}
		
	if (rand == true) {
	   // Random r = new Random();
		for(int j=0; j<m; j++) {  
    		   // boolean truefalse = r.nextBoolean();
    		    A[0][j] = array.get(j);
    		   // A[0][j] = (truefalse == true)?1:0; /*Se genera un vector inicial aleatorio*/
		}
	}else {
	    A[0][m/2]=1; /*Se establece un vector inicial con un pixel encendido a la mitad del vector*/	
	}

    }

/*EL metodo iteracion se encarga de ir evaluando las celulas en toda la matriz; los valores enteros j, k y l son la representacion en la tupla de la celda del 'centro' y sus vecinos */
    public void run() {
    	
    	int l=0;
    	if(a==0)
    	{
    		try
    		{
    			for(int y=0;y<n-1;y++){
					this.barrera.await();
					vector = A[y];
					l=y+1;
					for(int x=id;x<m;x+=nHilos){
						A[l][x]=getEstado(x);
					}//for x
				}//for y 


	     
	      	 //this.barrera.await();
	      		} catch (InterruptedException pp) {
	            pp.printStackTrace();
	       	 } catch (BrokenBarrierException ppp) {
	            ppp.printStackTrace();
	      	 }
    	}
    	else
    	{
    		paint(w);
    		
    	}

	}//iteracion


	public void llamadaConcurrente() throws InterruptedException
	{

		CyclicBarrier barrera = new CyclicBarrier(nHilos);

		pool = Executors.newFixedThreadPool(nHilos);

		for(int i=0;i<nHilos;i++)
		{
			pool.execute(new ca1DSimulator(i,barrera));
		}

		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.DAYS);
		
		a++;//la proxima llamada al run se hara en el else
	}

	public void llamadaPaintConcurrente() throws InterruptedException
	{
		int resetPuntos [][] = new int[n][16];
		w = getGraphics();
		pool = Executors.newFixedThreadPool(nHilos);
		for(int i=0;i<nHilos;i++)
		{
			pool.execute(new ca1DSimulator(i));
		}

		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.DAYS);

		marcoEmergente = new curvasPoblacion(puntos,estados,n);

		a=0; //la proxima llamada al run se hara en el if
		puntos=resetPuntos;
	}

/*El metodo getCasilla es el encargado de ir siguiendo la regla y regresar el nuevo valor que la celula tendra en la siguiente etapa del tiempo (k+1)*/
	public int getEstado(int x) {
	    int total=vector[x];
	    int vec = vecinos;
	    if(fronteras)
	    {
	    	while(vec!=0)
	    	{
	    		//Vecinos anteriores a la celula principal
	    		if((x-vec)<0)
	    		{
	    			total = total + vector[(vector.length) + (x-vec)];
	    		}
	    		else
	    		{
	    			total = total + vector[x-vec];
	    		}
	    		//Vecinos posteriores a la celula principal
	    		if((x+vec) >= vector.length)
	    		{
	    			total = total + vector[(x+vec) - (vector.length)];
	    		}
	    		else
	    		{
	    			total = total + vector[x+vec];
	    		}
	    		vec--;
	    	}//while
	    }//fronteras

	    else
	    {
	    	while(vec!=0)
	    	{
	    		//Vecinos anteriores a la celula principal
	    		if((x-vec)<0)
	    		{
	    			//Si se pasa de la frontera no hace nada
	    		}
	    		else
	    		{
	    			total = total + vector[x-vec];
	    		}

	    		//Vecinos posteriores a la celula principal
	    		if((x+vec) >= vector.length)
	    		{
	    			//Si se pasa de la frontera no hace nada
	    		}
	    		else
	    		{
	    			total = total + vector[x+vec];
	    		}
	    		vec--;
	    	}//while

	    }//else nulas
	    return VectorRegla[total];
	}

	public void getRegla(int GV, int ST, int regla){ //Ver la traza en este punto para vecindad 1 y estados 2
			vecinos = GV;
			int tamRegla = (2*GV+1)*(ST-1)+1;
			VectorRegla = new int[tamRegla];
			int i=0;
			estados = ST;
			while(regla>= ST){
			VectorRegla[i]=regla%ST;
			regla=regla/ST;
			i++;
				if(i==tamRegla)
				{
					regla=0; //Truncamiento
				}
			}
			if(regla!=0)
			{
				VectorRegla[i] = regla;
				for(i+=1; i<VectorRegla.length;i++){VectorRegla[i]=0;}//rellenar de ceros el vector regla
			}

	}

    public void paint(Graphics g){
    	
    	
    	if(flag)
    	{
	    	super.paint(g);
			g2d = (Graphics2D)g;	

	        for(int y=0;y<n;y++){
				try{	    
					for(int x=id;x<m;x+=nHilos) {

		
						
						switch(A[y][x])
						{
							case 0:
							{
									g.setColor(Color.GRAY);
	
									puntos[y][0] += 1;
							}
							break;


							case 1:
							{
									g.setColor(Color.ORANGE);
					
									puntos[y][1] += 1;

							}

							break;

							case 2:
								{
									g.setColor(Color.RED);
														puntos[y][2] += 1;
								}


							break;

							case 3:
								{
									g.setColor(Color.MAGENTA);
			
									puntos[y][3] += 1;
								}
	
							break;

							case 4:
								{
									g.setColor(Color.GREEN);
		
									puntos[y][4] += 1;
								}


							break;
							case 5:
								{
									g.setColor(Color.PINK);

									puntos[y][5] += 1;
								}


							break;
							case 6:
								{
									g.setColor(Color.CYAN);

									puntos[y][6] += 1;
								}


							break;
							case 7:
								{
									g.setColor(Color.BLUE);
		
									puntos[y][7] += 1;
								}


							break;
							case 8:
								{
									g.setColor(Color.WHITE);
	
									puntos[y][8] += 1;
								}


							break;
							case 9:
								{
									g.setColor(Color.BLACK);

									puntos[y][9] += 1;
								}


							break;
							case 10:
								{
									g.setColor(new Color(205,95,107));//purpura

									puntos[y][10] += 1;
								}


							break;
							case 11:
								{
									g.setColor(new Color(185,160,66)); //Dorado
					
									puntos[y][11] += 1;
								}


							break;
							case 12:
								{
									g.setColor(new Color(128,0,0)); //Rojo burdeo

									puntos[y][12] += 1;
								}


							break;							
							case 13:
								{
									g.setColor(new Color(64,0,128)); //Morado
		
									puntos[y][13] += 1;
								}


							break;
							case 14:
								{
									g.setColor(new Color(117,157,34)); //Pistacho
		
									puntos[y][14] += 1;
								}


							break;
							case 15:
								{
									g.setColor(new Color(128,64,0));// Marron

									puntos[y][15] += 1;
								}


							break;
						}
						//posible fallo aqui en la impresion
						g.fillOval(x,y, 1, 1);

		      		}//for
				Thread.sleep(0);
				} catch(InterruptedException e){ 
					System.out.println("Excepcion: " + e.getMessage());
				}//trycatch
	    	}//for
	    	
    	}//if
    }//paint
}//ca

class curvasPoblacion extends JFrame
{
	int puntos [][] = new int[1000][16];
	int estados=0;
	int generaciones;
	public curvasPoblacion(int puntos[][],int estados, int Generaciones)
	{
		generaciones = Generaciones;
		this.puntos = puntos;
		this.estados = estados;
		setTitle("Curva de Poblacion");
		setSize(1100, 1050);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/icons/grafica.jpg")).getImage());
		setVisible(true);
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.black);
	    g.drawLine(50, 0, 50, 1000);
	    g.drawLine(50, 1000,getWidth(), 1000);

	    //Paleta de Estados
	    g.drawString("ESTADOS",950,80);

	    g.setColor(Color.GRAY);
	    g.fillRect(960,100, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 0",980,112);

	    g.setColor(Color.ORANGE);
	    g.fillRect(960,120, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 1",980,132);

	    g.setColor(Color.RED);
	    g.fillRect(960,140, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 2",980,152);

	    g.setColor(Color.MAGENTA);
	    g.fillRect(960,160, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 3",980,172);

	    g.setColor(Color.GREEN);
	    g.fillRect(960,180, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 4",980,192);

	    g.setColor(Color.PINK);
	    g.fillRect(960,200, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 5",980,212);

	    g.setColor(Color.CYAN);
	    g.fillRect(960,220, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 6",980,232);

	    g.setColor(Color.BLUE);
	    g.fillRect(960,240, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 7",980,252);

	    g.setColor(Color.WHITE);
	    g.fillRect(960,260, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 8",980,272);

	    g.setColor(Color.BLACK);
	    g.fillRect(960,280, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 9",980,292);

	   	g.setColor(new Color(205,95,107));//purpura
	    g.fillRect(960,300, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 10",980,312);

	    g.setColor(new Color(185,160,66)); //Dorado
	    g.fillRect(960,320, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 11",980,332);

	    g.setColor(new Color(128,0,0)); //Rojo burdeo
	    g.fillRect(960,340, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 12",980,352);

	    g.setColor(new Color(64,0,128)); //Morado
	    g.fillRect(960,360, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 13",980,372);

	    g.setColor(new Color(117,157,34)); //Pistacho
	    g.fillRect(960,380, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 14",980,392);

	   	g.setColor(new Color(128,64,0));// Marron
	    g.fillRect(960,400, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 15",980,412);

	    //Poblacion
	    g.setColor(Color.red);
	     g.drawString("0",25,1025);
	     g.drawString("25",25,975);
	     g.drawString("50",25,950);
	     g.drawString("75",25,925);
	     g.drawString("100",25,900);
	     g.drawString("150",25,850);
	     g.drawString("200",25,800);
	     g.drawString("250",25,750);
	     g.drawString("300",25,700);
	     g.drawString("400",25,600);
	     g.drawString("500",25,500);
	     g.drawString("600",25,400);
	     g.drawString("700",25,300);
	     g.drawString("800",25,200);
	     g.drawString("900",25,100);

	     //Fila
	     g.drawString("25",75,1025);
	     g.drawString("50",100,1025);
	     g.drawString("150",150,1025);
	     g.drawString("250",250,1025);
	     g.drawString("350",350,1025);
	     g.drawString("450",450,1025);
	     g.drawString("500",550,1025);
	     g.drawString("600",650,1025);
	     g.drawString("700",750,1025);
	     g.drawString("800",850,1025);


	     //Letras
	     g.setColor(Color.blue);
	     g.drawString("P",10,40);
	     g.drawString("O",10,60);
	     g.drawString("B",10,80);
	     g.drawString("L",10,100);
	     g.drawString("A",10,120);
	     g.drawString("C",10,140);
	     g.drawString("I",13,160);
	     g.drawString("O",10,180);
	     g.drawString("N",10,200);

	   	 g.drawString("F",1020,1025);
	     g.drawString("I",1040,1025);
	     g.drawString("L",1060,1025);
	     g.drawString("A",1080,1025);


	     for(int i=0; i<=generaciones-10; i+=5)
	     {
	     	for(int j=0;j<estados;j++)
	     	{
						switch(j)
						{
							case 0:
							{g.setColor(Color.GRAY);}break;

							case 1:
							{g.setColor(Color.ORANGE);}break;

							case 2:
							{g.setColor(Color.RED);}break;

							case 3:
							{g.setColor(Color.MAGENTA);}break;

							case 4:
							{g.setColor(Color.GREEN);}break;

							case 5:
							{g.setColor(Color.PINK);}break;

							case 6:
							{g.setColor(Color.CYAN);}break;

							case 7:
							{g.setColor(Color.BLUE);}break;

							case 8:
							{g.setColor(Color.WHITE);}break;

							case 9:
							{g.setColor(Color.BLACK);}break;

							case 10:
							{g.setColor(new Color(205,95,107));}break;

							case 11:
							{g.setColor(new Color(185,160,66));}break;

							case 12:
							{g.setColor(new Color(128,0,0));}break;			

							case 13:
							{g.setColor(new Color(64,0,128));}break;

							case 14:
							{g.setColor(new Color(117,157,34));}break;

							case 15:
							{g.setColor(new Color(128,64,0));}break;
							
						}//switch
		     	g.fillRect(50 +(i)-2,1000 -(puntos[i][j])-2, 5, 5);
		     	if(i<=generaciones-10)
		     	g.fillRect(50 +(i+5)-2,1000 -(puntos[i+5][j])-2, 5, 5);
		     	g.drawLine(50 +(i), 1000 - (puntos[i][j]),50 +((i+5)), 1000 -(puntos[i+5][j]));
	     	}//for j
	     }//for i
	}//constructor
}//clase