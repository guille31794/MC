import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.*;
import static java.lang.Math.*;


public class graficaEntropia extends JFrame{
	double vEntropia[];
	int generaciones,celulas;

	public graficaEntropia(double vectorEntropia[], int Generaciones, int Celulas){	
		vEntropia = new double[Generaciones];
		vEntropia = vectorEntropia;
		generaciones = Generaciones;
		celulas = Celulas;
		setTitle("Entropia");
		setSize(1200, 300);
		setLocation(200,650);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/icons/grafica.jpg")).getImage());
		setVisible(true);
	}//Constructor

	public void paint(Graphics g){
		super.paint(g);

		//Ejes
		g.setColor(Color.black);
	    g.drawLine(50, 0, 50, 260); //Y
	    g.drawLine(50, 260,getWidth(), 260);//X

	    //Grid
	    	//Y
		    g.setColor(Color.GRAY);
		    g.drawLine(150, 60, 150, 260);
		    g.drawLine(250, 60, 250, 260);
		    g.drawLine(350, 60, 350, 260);
		    g.drawLine(450, 60, 450, 260);
		    g.drawLine(550, 60, 550, 260);
		    g.drawLine(650, 60, 650, 260);
		    g.drawLine(750, 60, 750, 260);
		    g.drawLine(850, 60, 850, 260);
		    g.drawLine(950, 60, 950, 260);
		    g.drawLine(1040, 60, 1040, 260);

		    //X
		    g.drawLine(50, 210,1040, 210);//X
		    g.drawLine(50, 160,1040, 160);//X
		    g.drawLine(50, 110,1040, 110);//X
		    g.drawLine(50, 60,1040, 60);//X




		//LOS PIXELES DE LOS NUMEROS INDICADORES DE LA GRAFICA SE HAN AUMENTADO A 2 RESPECTO DE SU VALOR ORIGINAL PARA QUEDAR ESTETICAMENTE MEJOR. PUNTOS DE Y	
	    //Entropia
	     g.setColor(Color.red);
	     g.drawString("0",45,275);
	     g.drawString("0.25",25,212); //210
	     g.drawString("0.50",25,162); //160
	     g.drawString("0.75",25,112); //110
	     g.drawString("     1",25,62);//60

	     //AQUI SE HA REDUCIDO 3 PIXELES RESPECTO DE SU VALOR ORIGIANAL. PUNTOS DE X
	     //Fila
	     g.drawString("100",147,275);
	     g.drawString("200",247,275);
	     g.drawString("300",347,275);
	     g.drawString("400",447,275);
	     g.drawString("500",547,275);
	     g.drawString("600",647,275);
	     g.drawString("700",747,275);
	     g.drawString("800",847,275);
	     g.drawString("900",947,275);
	     g.drawString("990",1037,275);
	     //Letras
	     g.setColor(Color.blue);
	     g.drawString("E",10,40);
	     g.drawString("N",10,60);
	     g.drawString("T",10,80);
	     g.drawString("R",10,100);
	     g.drawString("O",10,120);
	     g.drawString("P",10,140);
	     g.drawString("I",13,160);
	     g.drawString("A",10,180);

	   	 g.drawString("n",1120,275);
	     g.drawString("G",1140,275);
	     g.drawString("E",1160,275);
	     g.drawString("N",1180,275);

	     g.setColor(Color.RED);

	     for(int i=0; i<generaciones-1; i++){
		     	//g.fillRect(50 +(i)-2,260 -(int)(100*vEntropia[i])-2, 5, 5);
		     	g.drawLine(50 +(i), 260 - (int)(200*vEntropia[i]),50 +(i+1), 260 -(int)(200*vEntropia[i+1])); //Multiplico por 200, ya que 200 equivale a 1 en la entropia y si restamos 260-200=60 equivale al punto 1 en el eje Y en la grafica.
		     	try{Thread.sleep(3);}catch(InterruptedException popo){}
		     	//if(i==generaciones-10){g.fillRect(50 +(i+5)-2,260 -(int)(100*vEntropia[i+5])-2, 5, 5);}
		     	//System.out.println(vEntropia[i]+"   ");
	     }//for i
	}//constructor
}//clase