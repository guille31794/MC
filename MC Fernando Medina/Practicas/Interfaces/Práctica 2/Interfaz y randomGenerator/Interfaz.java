import javax.imageio.*; // Para usar imagenes
import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*; //Importa los metodos de la clase Graphics2D (Figuras)

import java.io.*; //Para variables tipo File
import java.util.*;

//Grafica


public class Interfaz
{
	public static void main(String [] args)
	{
		Marco miMarco = new Marco();
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		miMarco.setVisible(true);

	}
}

class Marco extends JFrame implements ActionListener
{
	private JMenuBar BarraMenu;
	private JMenu Archivo,Ayuda,AcercaDe,Opciones;
	private JMenuItem opcion1;
	private JComboBox ListaProgramas;
	private LaminaTrabajoGeneral laminaTrabajo;

	public Marco()
	{
		setTitle("Modelo de Computacion");
		Toolkit miPantalla = Toolkit.getDefaultToolkit(); //Se almacena en el objeto 'miPantalla' el sistema nativo la ventana. Sirve para poder centrar posteriormente la ventana
		Dimension tamPantalla = miPantalla.getScreenSize(); //En 'tamPantalla' almacenamos el tamaño de la pantalla del objeto 'miPantalla'
		int altPantalla = tamPantalla.height; //obtenemos la altura de nuesta pantalla en un entero
		int anchPantalla = tamPantalla.width; //obtenemos la anchura de nuetra pantalla en un entero
		setSize(anchPantalla/2, altPantalla/2);
		setLocation(anchPantalla/4, altPantalla/4);

		setLayout(new BorderLayout());

		Dimension tamMinimo = new Dimension(anchPantalla/3, altPantalla/3);
		setMinimumSize(tamMinimo);
		getContentPane().setBackground(new Color(186,205,200));

		BarraMenu = new JMenuBar();
		BarraMenu.setBackground(new Color(173,173,218));
		setJMenuBar(BarraMenu);

		Archivo = new JMenu("Archivo");
		Opciones = new JMenu("Opciones");
		Ayuda = new JMenu("Ayuda");
		AcercaDe = new JMenu("Acerca de...");

		opcion1 = new JMenuItem("Opcion 1");

		Archivo.setBackground(new Color(173,173,218));
		Archivo.setFont(new Font("Calibri", 1, 14));
		Archivo.setForeground(new Color(90,90,90));
		Ayuda.setBackground(new Color(173,173,218));
		Ayuda.setFont(new Font("Calibri", 1, 14));
		Ayuda.setForeground(new Color(90,90,90));
		AcercaDe.setBackground(new Color(173,173,218));
		AcercaDe.setFont(new Font("Calibri", 1, 14));
		AcercaDe.setForeground(new Color(90,90,90));
		Opciones.setBackground(new Color(173,173,218));
		Opciones.setFont(new Font("Calibri", 1, 14));
		Opciones.setForeground(new Color(90,90,90));
		opcion1.setBackground(new Color(173,173,218));
		opcion1.setFont(new Font("Calibri", 1, 14));
		opcion1.setForeground(new Color(90,90,90));

		
		Opciones.add(opcion1);
		Archivo.add(Opciones);


		//eventos barra menu
		opcion1.addActionListener(this);





		BarraMenu.add(Archivo);
		BarraMenu.add(Ayuda);
		BarraMenu.add(AcercaDe);


		laminaTrabajo = new LaminaTrabajoGeneral();
		add(laminaTrabajo, BorderLayout.CENTER);

	}

	public void actionPerformed(ActionEvent e) //como el metodo run de la interfaz Runnable pero este es el de la interfaz ActionListener
	{
		JFrame marcoSecundario;
		lamina l;
		if(e.getSource() == opcion1)
		{
			marcoSecundario = new JFrame();
			marcoSecundario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			marcoSecundario.setVisible(true);
			marcoSecundario.setBounds(300,300,700,700);
			l = new lamina("opcion1");
			marcoSecundario.add(l);
		}

	}

	class lamina extends JPanel
	{
		String nombre;
		public lamina(String nombre)
		{
			this.nombre=nombre;
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawString(nombre,500,500);
		}
	}
}//Marco


class LaminaTrabajoGeneral extends JPanel implements ActionListener
{
	//JPanel laminaGrafica;
	LaminaGrafica laminaGrafica;


	JPanel laminaHerramientas;
	JPanel laminaProgramas;
	JPanel laminaBotones;
	//Variables LaminaProgramas
	JComboBox generadores;

	//Variables LaminaBotones
	JButton boton;

	//Variable randomGenerator
	int tipo=0;
	randomGenerator numeroAleatorio;


	public LaminaTrabajoGeneral()
	{
		setLayout(new BorderLayout(2,3));

		//laminaGrafica = new JPanel();
		laminaHerramientas = new JPanel();
		laminaProgramas = new JPanel();
		laminaBotones = new JPanel();

		boton = new JButton("Ejecutar");
		
		//LaminaGrafica
		laminaGrafica = new LaminaGrafica();
		laminaGrafica.setBackground(new Color(176,176,176));
		laminaGrafica.setBorder(BorderFactory.createLineBorder(Color.black));
		add(laminaGrafica, BorderLayout.CENTER);




		//LaminaProgramas
		generadores = new JComboBox();
		generadores.addItem("");
		generadores.addItem("26.1a");
		generadores.addItem("26.1b");
		generadores.addItem("26.2");
		generadores.addItem("26.3");
		generadores.addItem("Generador Combinado");
		generadores.addItem("Fishman Y Moore 1");
		generadores.addItem("Fishman Y Moore 2");
		generadores.addItem("RANDU");
		generadores.addActionListener(this);

		laminaProgramas.add(new JLabel("Generadores:"));
		laminaProgramas.add(generadores);
		laminaProgramas.setBackground(new Color(176,176,176));
		laminaProgramas.setBorder(BorderFactory.createLineBorder(Color.black));


		//LaminaBotones
		boton.addActionListener(this);
		laminaBotones.add(boton);
		laminaBotones.setBackground(new Color(176,176,176));
		laminaBotones.setBorder(BorderFactory.createLineBorder(Color.black));

		//LaminaHerramientas
		laminaHerramientas.setLayout(new GridLayout(2,1,0,2));
		laminaHerramientas.add(laminaProgramas);
		laminaHerramientas.add(laminaBotones);
		add(laminaHerramientas, BorderLayout.EAST);

	}//Constructor

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==generadores)
		{
			tipo = generadores.getSelectedIndex();
		}
		if(e.getSource()==boton)
		{
			laminaGrafica.seleccion=0;
			Graphics g = laminaGrafica.getGraphics();
			laminaGrafica.paintComponent(g);
			ImprimePuntos(tipo);
			tipo=0;
		}	
			
	}

	public void ImprimePuntos(int tipo)
	{
		ArrayList<Integer> numerosX = new ArrayList<Integer>();
		ArrayList<Integer> numerosY = new ArrayList<Integer>();
		numeroAleatorio = new randomGenerator();
			switch(tipo)
			{
				case 1: 
						numerosX = numeroAleatorio.generador1(1);
						numerosY = numeroAleatorio.generador1(7);
						laminaGrafica.seleccion = 1;
						for(int i=0; i<numerosX.size(); i++)
						{
							laminaGrafica.puntoX=numerosX.get(i);
							laminaGrafica.puntoY=numerosY.get(i);
							Graphics g = laminaGrafica.getGraphics();
							laminaGrafica.paintComponent(g);
						}
				break;
				case 2: 
						numerosX = numeroAleatorio.generador2(1);
						numerosY = numeroAleatorio.generador2(7);
						laminaGrafica.seleccion = 1;
						for(int i=0; i<numerosX.size(); i++)
						{
							laminaGrafica.puntoX=numerosX.get(i);
							laminaGrafica.puntoY=numerosY.get(i);
							Graphics g = laminaGrafica.getGraphics();
							laminaGrafica.paintComponent(g);
						}
				break;
				case 3: 
						numerosX = numeroAleatorio.generador3(1);
						numerosY = numeroAleatorio.generador3(7);
						laminaGrafica.seleccion = 1;
						for(int i=0; i<numerosX.size(); i++)
						{
							laminaGrafica.puntoX=numerosX.get(i);
							laminaGrafica.puntoY=numerosY.get(i);
							Graphics g = laminaGrafica.getGraphics();
							laminaGrafica.paintComponent(g);
						}
				break;
				case 4: 
						numerosX = numeroAleatorio.generador4(100000);
						numerosY = numeroAleatorio.generador4(300000);
						laminaGrafica.seleccion = 1;
						for(int i=0; i<numerosX.size(); i++)
						{
							laminaGrafica.puntoX=numerosX.get(i);
							laminaGrafica.puntoY=numerosY.get(i);
							Graphics g = laminaGrafica.getGraphics();
							laminaGrafica.paintComponent(g);
						}
				break;
				case 5: 
						numerosX = numeroAleatorio.generadorCombinado(2500);
						numerosY = numeroAleatorio.generadorCombinado(5000);
						laminaGrafica.seleccion = 1;
						for(int i=0; i<numerosX.size(); i++)
						{
							laminaGrafica.puntoX=numerosX.get(i);
							laminaGrafica.puntoY=numerosY.get(i);
							Graphics g = laminaGrafica.getGraphics();
							laminaGrafica.paintComponent(g);
						}
				break;
				case 6: 
						numerosX = numeroAleatorio.FishmanYMoore1(4000);
						numerosY = numeroAleatorio.FishmanYMoore1(2000);
						laminaGrafica.seleccion = 1;
						for(int i=0; i<numerosX.size(); i++)
						{
							laminaGrafica.puntoX=numerosX.get(i);
							laminaGrafica.puntoY=numerosY.get(i);
							Graphics g = laminaGrafica.getGraphics();
							laminaGrafica.paintComponent(g);
						}
				break;
				case 7: 
						numerosX = numeroAleatorio.FishmanYMoore2(1);
						numerosY = numeroAleatorio.FishmanYMoore2(7);
						laminaGrafica.seleccion = 1;
						for(int i=0; i<numerosX.size(); i++)
						{
							laminaGrafica.puntoX=numerosX.get(i);
							laminaGrafica.puntoY=numerosY.get(i);
							Graphics g = laminaGrafica.getGraphics();
							laminaGrafica.paintComponent(g);
						}
				break;
				case 8: 
						numerosX = numeroAleatorio.RANDU(1);
						numerosY = numeroAleatorio.RANDU(7);
						laminaGrafica.seleccion = 1;
						for(int i=0; i<numerosX.size(); i++)
						{
							laminaGrafica.puntoX=numerosX.get(i);
							laminaGrafica.puntoY=numerosY.get(i);
							Graphics g = laminaGrafica.getGraphics();
							laminaGrafica.paintComponent(g);
						}
				break;
			}//switch
	}//ImprimePuntos



	class LaminaGrafica extends JPanel
	{
		//private ArrayList<Integer> numeros = new ArrayList<Integer>();
		int puntoX = 0;
		int puntoY = 0;
		int seleccion=0;
		public LaminaGrafica(){}
		/*public LaminaGrafica(ArrayList<Integer> numeros)
		{laminaGrafica.getHeight()/2
			this.numeros = numeros;
		}*/
		public void paintComponent(Graphics g)
		{
				switch(seleccion)
				{
					case 0:
							super.paintComponent(g);
							g.setColor(Color.red);
	            			g.drawLine(0, laminaGrafica.getHeight()/2, laminaGrafica.getWidth(), laminaGrafica.getHeight()/2);
	            			g.drawLine(laminaGrafica.getWidth()/2, 0,laminaGrafica.getWidth()/2 , laminaGrafica.getHeight());
					break;

					case 1:
							g.setColor(Color.red);
	          			  	g.drawOval(((laminaGrafica.getWidth()/2)+puntoX),((laminaGrafica.getHeight()/2)-puntoY),2,2);
	          			  	break;
				}//switch

		}
	}



}//LaminaTrabajoGeneral