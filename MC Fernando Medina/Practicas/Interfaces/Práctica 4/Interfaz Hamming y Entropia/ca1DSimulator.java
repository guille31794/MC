import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.*;
import static java.lang.Math.*;

public class ca1DSimulator extends JPanel implements Runnable, ca1DSim{
	static int n=0,m=0;
    static int A[][]=new int[n][m];
    static int vector [];
    static int VectorRegla[];
    static int puntos[][] = new int [1605][5];
    static int vecinos = 0,estados =5, a=0;
    static boolean rand = false;
    static boolean flag = false; //Para no iniciar el metodo paint al crear un objeto de esta clase
    static boolean fronteras = false; //Inicializo la condicion de frontera a Nula
    static curvasPoblacion marcoPoblacion;

    static Graphics w;
    static Graphics2D g2d;
    static ArrayList<Integer> array = new ArrayList<Integer>();
    static CyclicBarrier barrera = null;

    int nNuc = Runtime.getRuntime().availableProcessors();
	int nHilos = (int)(nNuc/(1));
    ExecutorService pool;
    int id;

    //Hamming y Entropia
    static double[] vHamming;
    static double[] eEspacial;
	static graficaHamming marcoHamming;
	static graficaEntropia marcoEntropia;

    public ca1DSimulator() {} //nulo

    public ca1DSimulator(int id, CyclicBarrier barrera){
    	this.barrera = barrera;
    	this.id=id;
    }//Constructor

    public ca1DSimulator(int id){
    	this.id = id;
    }//Constructor

    public void AjustarDimensiones(int nColumnas, int nGeneraciones){
    	this.n = nGeneraciones;
    	this.m = nColumnas;
    	vHamming=new double[nGeneraciones];
    	eEspacial=new double[nGeneraciones+1];
		
    	A = new int [n][m];
    	vector = new int[m];
    }//AjustarDimensiones

    public void Random(ArrayList nAleatorios) {
	rand = true;
	array = nAleatorios;
    }//Random

    public void NoRandom(){
	rand = false;
    }//NoRandom

    public void AsignarCondicionFrontera(boolean f){
    fronteras = f;
    }//AsginarCondicionFrontera

    public void Reglas(){
		for(int i=0;i<n;i++){
		    A[0][i]=0;
		}

		if (rand == true) {
			for(int j=0; j<m; j++){  
	    		A[0][j] = array.get(j);
			}
		}
		else{
		    A[0][m/2]=1; /*Se establece un vector inicial con un pixel encendido a la mitad del vector*/	
		}
    }//Reglas

	public int nextGen(int x){
	    int total=vector[x];
	    int vec = vecinos;
	    if(fronteras){
	    	while(vec!=0){
	    		//Vecinos anteriores a la celula principal
	    		if((x-vec)<0){
	    			total = total + vector[(vector.length) + (x-vec)];
	    		}
	    		else{
	    			total = total + vector[x-vec];
	    		}
	    		//Vecinos posteriores a la celula principal
	    		if((x+vec) >= vector.length){
	    			total = total + vector[(x+vec) - (vector.length)];
	    		}
	    		else{
	    			total = total + vector[x+vec];
	    		}
	    		vec--;
	    	}//while
	    }//fronteras

	    else{
	    	while(vec!=0){
	    		//Vecinos anteriores a la celula principal
	    		if((x-vec)<0){
	    			//Si se pasa de la frontera no hace nada
	    		}
	    		else{
	    			total = total + vector[x-vec];
	    		}
	    		//Vecinos posteriores a la celula principal
	    		if((x+vec) >= vector.length){
	    			//Si se pasa de la frontera no hace nada
	    		}
	    		else{
	    			total = total + vector[x+vec];
	    		}
	    		vec--;
	    	}//while
	    }//else nulas
	    return VectorRegla[total];
	}//nextGen

	public void caComputation(int nGen){
    	int l=0;
    	if(a==0){
    		try{
    			for(int y=0;y<n-1;y++){
					this.barrera.await();
					vector = A[y];
					l=y+1;
					for(int x=id;x<m;x+=nHilos){
						A[l][x]=nextGen(x);
					}//for x
				}//for y 

	      	}catch(InterruptedException pp){
	            pp.printStackTrace();
	       	}catch(BrokenBarrierException ppp){
	            ppp.printStackTrace();
	      	}
    	}//if
    	else{paint(w);}//else
	}//caComputacion

    public void run(){
    	caComputation(n);
	}//run

	public void llamadaConcurrente() throws InterruptedException{
		int resetPuntos [][] = new int[n][5];
		puntos=resetPuntos;
		CyclicBarrier barrera = new CyclicBarrier(nHilos);
		pool = Executors.newFixedThreadPool(nHilos);
		for(int i=0;i<nHilos;i++){
			pool.execute(new ca1DSimulator(i,barrera));
		}
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.DAYS);
		CalculaHammingEntropia();
		a++;//la proxima llamada pintara el autómata
		flag=true;
	}

	public void CalculaHammingEntropia(){			
		for(int i=0; i<n;i++){
				eEspacial[i]=entropia(i);
				//for(int j=0;j<m;j++)
				vHamming[i]=hamming(i);
				//vector=nueva.clone();
				//win.update(vector, genAct++);
				//System.out.print(A[i][m/2]+"   ");
				//System.out.print(vHamming+"   ");
		}
	}//CalculaHammingEntropia

	public int hamming(int j){
		int c=0;
		if(j<n-1){
			for(int i=0;i<m;++i){
				if(A[j][i]!=A[j+1][i]){
					c++;
				}
			}
		}
		else{
			for(int i=0;i<m;++i){
				if(A[j][i]!=A[0][i]){
					c++;
				}
			}
		}

		//System.out.println("Generacion "+j+": "+c);
		

		return c;
	}//hamming

	private static double log2(double x) {return (log(x)/log(estados));} //logaritmo en base a estados

	public static double entropia(int j){
		double p1=0 ,lp0=0;
		double p0=0 ,lp1=0;
		double p2=0 ,lp2=0;
		double p3=0 ,lp3=0;
		double p4=0 ,lp4=0;
		double entropia=0;
		double contUnos=0;
		double contCeros=0;
		double contDos=0;
		double contTres=0;
		double contCuatros=0;

			for(int i=0;i<m;++i){
			if(A[j][i]==0){
				contCeros++;
			}
			if(A[j][i]==1){
				contUnos++;
			}
			if(A[j][i]==2){
				contDos++;
			}
			if(A[j][i]==3){
				contTres++;
			}
			if(A[j][i]==4){
				contCuatros++;
			}
		}//for

		p0=(double)contCeros/m;
		p1=(double)contUnos/m;
		p2=(double)contDos/m;
		p3=(double)contTres/m;
		p4=(double)contCuatros/m;


		lp0 = p0*log2(p0);
		lp1 = p1*log2(p1);
		lp2 = p2*log2(p2);
		lp3 = p3*log2(p3);
		lp4 = p4*log2(p4);

		if(p0==0){lp0=0;}
		if(p1==0){lp1=0;}
		if(p2==0){lp2=0;}
		if(p3==0){lp3=0;}
		if(p4==0){lp4=0;}

		entropia = -1 *(lp0+lp1+lp2+lp3+lp4);

		return entropia;
	}//entropia


	public void llamadaPaintConcurrente() throws InterruptedException{
		w = getGraphics();
		pool = Executors.newFixedThreadPool(nHilos);

		for(int i=0;i<nHilos;i++){
			pool.execute(new ca1DSimulator(i));
		}
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.DAYS);
		
		a=0; //la proxima llamada al run se hara en el if
		flag=false;
	}//llamadaPaintConcurrente

	public void getRegla(int GV, int ST, int regla){ //Ver la traza en este punto para vecindad 1 y estados 2
		int tamRegla = (2*GV+1)*(ST-1);
		int i=0;
		vecinos = GV;
		VectorRegla = new int[tamRegla+1];
			
		estados = ST;
		while(regla!=0 && i<=tamRegla){
			VectorRegla[i]=regla%ST;
			regla=regla/ST;
			i++;

			//if(i==tamRegla){regla=0;} //Truncamiento
		}//while
		/*if(regla!=0){
			VectorRegla[i] = regla;
			for(i+=1; i<VectorRegla.length;i++){VectorRegla[i]=0;}//rellenar de ceros el vector regla
		}*/
	}//getRegla

    public void paint(Graphics g){
   		if(flag){
	    	super.paint(g);
			g2d = (Graphics2D)g;	
	        for(int y=0;y<n;y++){
				try{	    
					for(int x=id;x<m;x+=nHilos) {

						switch(A[y][x]){
							case 0:{
							g.setColor(Color.GRAY);
							puntos[y][0] += 1;}
							break;

							case 1:{
							g.setColor(Color.ORANGE);
							puntos[y][1] += 1;}
							break;

							case 2:{
				            g.setColor(Color.RED);
							puntos[y][2] += 1;}
							break;

							case 3:{
							g.setColor(Color.MAGENTA);
							puntos[y][3] += 1;}
							break;

							case 4:{
							g.setColor(Color.GREEN);
						    puntos[y][4] += 1;}
							break;
						}//switch
						g.fillOval(x,y, 1, 1); //Dibuja Punto
		      		}//for
				Thread.sleep(0);
				}catch(InterruptedException e){ 
					System.out.println("Excepcion: " + e.getMessage());
				}//trycatch
	    	}//for
    	}//if
    }//paint

    public void MostrarCurvaPoblacion(){
    	marcoPoblacion = new curvasPoblacion(puntos,estados,n,m);
    }
    public void MostrarGraficaHamming(){
    	marcoHamming = new graficaHamming(vHamming,n,m);
    }//MostrarGraficaHamming

    public void MostrarGraficaEntropia(){
    	marcoEntropia = new graficaEntropia(eEspacial,n,m);
    }//MostrarGraficaEntropia

    public double MostrarDHMedia(){
    	double cont=0;
    	for(int i=0; i<n;i++){
    		cont +=vHamming[i];
    		System.out.println("Hamming "+i+") "+vHamming[i]);
    	}

    	System.out.println("Cont: "+cont);
    	return cont/n;
    }//MostrarDHMedia

    public double MostrarSMedia(){
    	double cont=0;
    	for(int i=0; i<n;i++){
    		cont +=eEspacial[i];
    	}
    	return cont/n;
    }//MostrarSMedia
}//ca