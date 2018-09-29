import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.*;
import static java.lang.Math.*;


public class curvasPoblacion extends JFrame{
	int puntos [][] = new int[1605][5];
	int estados=0;
	int generaciones,celulas;
	public curvasPoblacion(int puntos[][],int estados, int Generaciones, int Celulas){
		generaciones = Generaciones;
		celulas = Celulas;
		this.puntos = puntos;
		this.estados = estados;
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
	     g.drawString("200",22,450);
	     g.drawString("400",22,400); 
	     g.drawString("600",22,350); 
	     g.drawString("800",22,300);
	     g.drawString("1000",22,250);
	     g.drawString("1200",22,200);
	     g.drawString("1400",22,150);
	     g.drawString("1605",22,95);

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
	     g.drawString("990",1037,525);

	    //Paleta de Estados
	    g.drawString("ESTADOS",1100,80);

	    g.setColor(Color.GRAY);
	    g.fillRect(1120,100, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 0",1140,112);

	    g.setColor(Color.ORANGE);
	    g.fillRect(1120,120, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 1",1140,132);

	    g.setColor(Color.RED);
	    g.fillRect(1120,140, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 2",1140,152);

	    g.setColor(Color.MAGENTA);
	    g.fillRect(1120,160, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 3",1140,172);

	    g.setColor(Color.GREEN);
	    g.fillRect(1120,180, 15, 15);
	    g.setColor(Color.black);
	    g.drawString(" = 4",1140,192);

/*
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
*/


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


	     for(int i=0; i<=generaciones-10; i+=5){
	     	for(int j=0;j<estados;j++){
						switch(j){
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
							/*
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
							*/
						}//switch
		     	//g.fillRect(50 +(i)-2,1000 -(puntos[i][j])-2, 5, 5);
		     	g.drawLine(50 +(i),500-puntos[i][j]*405/1605,50 +(i+5),500-puntos[i+5][j]*405/1605);
		     	try{Thread.sleep(3);}catch(InterruptedException popo){}
		     	//System.out.println("Fila "+i+") "+ puntos[i][j]%405);
		     	//if(i==generaciones-10){g.fillRect(50 +(i+5)-2,1000 -(puntos[i+5][j])-2, 5, 5);}
	     	}//for j
	     }//for i
	}//constructor
}//clase