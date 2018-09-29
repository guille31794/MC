import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.*;
import static java.lang.Math.*;


public class graficaHamming extends JFrame{
	double vHamming [];
	int generaciones,celulas;

	public graficaHamming(double vectorHamming[], int Generaciones, int Celulas){	
		vHamming = new double[Generaciones];
		vHamming = vectorHamming;
		generaciones = Generaciones;
		celulas = Celulas;
		setTitle("Distancia Hamming");
		setSize(1200, 300);
		setLocation(200,150);
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
		    g.drawLine(50, 235,1040, 235);//X
		    g.drawLine(50, 210,1040, 210);//X
		    g.drawLine(50, 185,1040, 185);//X
		    g.drawLine(50, 160,1040, 160);//X
		    g.drawLine(50, 135,1040, 135);//X
		    g.drawLine(50, 110,1040, 110);//X
		    g.drawLine(50, 85,1040, 85);//X
		    g.drawLine(50, 60,1040, 60);//X

	    //DistanciaHamming
	    g.setColor(Color.red);
	     g.drawString("0",45,275);
	     g.drawString("200",25,237); //210
	     g.drawString("400",25,212); //160
	     g.drawString("600",25,187); //110
	     g.drawString("800",25,162);//60
	     g.drawString("1000",25,137);//60
	     g.drawString("1200",25,112);//60
	     g.drawString("1400",25,87);//60
	     g.drawString("1600",25,62);//60

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
	     g.drawString("D",10,40);
	     g.drawString(".",13,60);
	     g.drawString("H",10,80);
	     g.drawString("A",10,100);
	     g.drawString("M",10,120);
	     g.drawString("M",10,140);
	     g.drawString("I",13,160);
	     g.drawString("N",10,180);
	     g.drawString("G",10,200);

	   	 g.drawString("n",1120,275);
	     g.drawString("G",1140,275);
	     g.drawString("E",1160,275);
	     g.drawString("N",1180,275);

	     g.setColor(Color.RED);

	     for(int i=0; i<generaciones-2; i++){
		     //	g.fillRect(50 +(i)-2,1000 -(int)(vHamming[i])-2, 5, 5);
		     	g.drawLine(50 +(i), 260 -(int)vHamming[i]*200/1605,50 +(i+1), 260 -(int)vHamming[i+1]*200/1605);
		     	//System.out.println(260 -(vHamming[i])%((celulas*200)/1605));
		     	try{Thread.sleep(3);}catch(InterruptedException popo){}
		     //	if(i==generaciones-10){g.fillRect(50 +(i+5)-2,1000 -(int)(vHamming[i+5])-2, 5, 5);}
		     	//System.out.print(vHamming[i]+"    ");
	     }//for i
	}//constructor
}//clase