import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.*;
import static java.lang.Math.*;


public class curvasPoblacion extends JFrame{
	int puntos [][] = new int[1000][2];
	int estados=0;
	int dimension;
	public curvasPoblacion(int puntos[][], int Dimension){
		dimension = Dimension;
		this.puntos = puntos;
		setTitle("Curva de Poblacion");
		setSize(1200, 550);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/icons/grafica.jpg")).getImage());
		setVisible(true);
	}

	public void paint(Graphics g){
		super.paint(g);
		//Ejes
		g.setColor(Color.black);
	    g.drawLine(50, 0, 50, 500); //Y
	    g.drawLine(50, 500,getWidth(), 500);//X

	    //Grid
	    	//Y
		    g.setColor(new Color(180,150,120));
		    g.drawLine(150, 95, 150, 500);
		    g.drawLine(250, 95, 250, 500);
		    g.drawLine(350, 95, 350, 500);
		    g.drawLine(450, 95, 450, 500);
		    g.drawLine(550, 95, 550, 500);
		    g.drawLine(650, 95, 650, 500);
		    g.drawLine(750, 95, 750, 500);
		    g.drawLine(850, 95, 850, 500);
		    g.drawLine(950, 95, 950, 500);
		    g.drawLine(1040, 95, 1040, 500);

		    //X
		    g.drawLine(50, 450,1040, 450);//X
		    g.drawLine(50, 400,1040, 400);//X
		    g.drawLine(50, 350,1040, 350);//X
		    g.drawLine(50, 300,1040, 300);//X
		    g.drawLine(50, 250,1040, 250);//X
		    g.drawLine(50, 200,1040, 200);//X
		    g.drawLine(50, 150,1040, 150);//X
		    g.drawLine(50, 95,1040, 95);//X




		//LOS PIXELES DE LOS NUMEROS INDICADORES DE LA GRAFICA SE HAN AUMENTADO A 2 RESPECTO DE SU VALOR ORIGINAL PARA QUEDAR ESTETICAMENTE MEJOR. PUNTOS DE Y	
	    //Entropia
	     g.setColor(Color.red);
	     g.drawString("0",40,525);
	     g.drawString(Integer.toString((dimension*dimension)/8),22,450);
	     g.drawString(Integer.toString(2*(dimension*dimension)/8),22,400); 
	     g.drawString(Integer.toString(3*(dimension*dimension)/8),22,350); 
	     g.drawString(Integer.toString(4*(dimension*dimension)/8),22,300);
	     g.drawString(Integer.toString(5*(dimension*dimension)/8),22,250);
	     g.drawString(Integer.toString(6*(dimension*dimension)/8),22,200);
	     g.drawString(Integer.toString(7*(dimension*dimension)/8),22,150);
	     g.drawString(Integer.toString(8*(dimension*dimension)/8),22,95);

	     //AQUI SE HA REDUCIDO 3 PIXELES RESPECTO DE SU VALOR ORIGIANAL. PUNTOS DE X
	     //Fila
	     g.drawString("100",147,525);
	     g.drawString("200",247,525);
	     g.drawString("300",347,525);
	     g.drawString("400",447,525);
	     g.drawString("500",547,525);
	     g.drawString("600",647,525);
	     g.drawString("700",747,525);
	     g.drawString("800",847,525);
	     g.drawString("900",947,525);
	     g.drawString("1000",1037,525);

	    //Paleta de Estados
	    g.drawString("ESTADOS",1100,80);

	    g.setColor(Color.RED);
	    g.fillRect(1120,100, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = Muertas",1140,112);

	    g.setColor(Color.BLUE);
	    g.fillRect(1120,120, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = Vivas",1140,132);

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

	   	 g.drawString("n",1120,525);
	     g.drawString("G",1140,525);
	     g.drawString("E",1160,525);
	     g.drawString("N",1180,525);

	     	for(int j=0;j<dimension;j++){
	     		g.setColor(Color.BLUE);
		     	g.drawLine(50 +(j),500-puntos[0][j]*405/(dimension*dimension),50 +(j+1),500-puntos[0][j+1]*405/(dimension*dimension));
		     	g.setColor(Color.RED);
		     	g.drawLine(50 +(j),500-puntos[1][j]*405/(dimension*dimension),50 +(j+1),500-puntos[1][j+1]*405/(dimension*dimension));
		     	try{Thread.sleep(3);}catch(InterruptedException popo){}
	     	}//for j
	}//constructor
}//clase