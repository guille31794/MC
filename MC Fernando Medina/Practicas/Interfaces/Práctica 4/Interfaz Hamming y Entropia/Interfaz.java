import javax.imageio.*; // Para usar imagenes
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*; //Importa los metodos de la clase Graphics2D (Figuras)
import java.io.*; //Para variables tipo File
import java.util.*;

public class Interfaz{
	public static void main(String [] args){
		Marco miMarco = new Marco();
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		miMarco.setVisible(true);
	}
}

class Marco extends JFrame implements ActionListener{
	private JMenuBar BarraMenu;
	private JMenu Ayuda,AcercaDe;
	private JMenuItem ayuda,acercade;
	private JComboBox ListaProgramas;
	private LaminaTrabajoGeneral laminaTrabajo;
	
	public Marco(){
		setTitle("Automata Aditivo - Fernando Medina Delgado");
		Toolkit miPantalla = Toolkit.getDefaultToolkit(); //Se almacena en el objeto 'miPantalla' el sistema nativo la ventana. Sirve para poder centrar posteriormente la ventana
		Dimension tamPantalla = miPantalla.getScreenSize(); //En 'tamPantalla' almacenamos el tamaño de la pantalla del objeto 'miPantalla'
		int altPantalla = tamPantalla.height; //obtenemos la altura de nuesta pantalla en un entero		
		int anchPantalla = tamPantalla.width; //obtenemos la anchura de nuetra pantalla en un entero
		setIconImage(new ImageIcon(getClass().getResource("/icons/triforce.png")).getImage());
		setSize(anchPantalla-700, altPantalla-300);
		setLocation(anchPantalla/4, altPantalla/8);
		setExtendedState(MAXIMIZED_BOTH);
		//setResizable(false);
		setLayout(new BorderLayout());
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
		laminaTrabajo = new LaminaTrabajoGeneral();

		add(laminaTrabajo, BorderLayout.CENTER);
	}//constructor Marco

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
			text1.setText("\n\n          GUIA DE USO"+
						 "\n\n          - Puedes seleccionar un generador de numeros pseudoaleatorios para iniciar el vector con valores aleatorios segun el numero de estados"+
						 "\n             seleccionado,o bien, seleccionar -NINGUNO- para iniciar un vector con todos sus elementos a 0 menos el elemento central a 1."+
						 "\n\n          - Puedes optar que la ejecucion del automata contenga fronteras Nulas o Cilindricas."+
						 "\n\n          - Puedes seleccionar el numero de estados que puede tener una celula comprendido entre 2 hasta 15 estados."+
						 "\n\n          - La vecindad indica el numero de celulas vecinas a tener en cuenta en cada celula del automata al generar la nueva generacion."+
						 "\n             Puedes seleccionar un numero entre 1 hasta 899."+
						 "\n\n          - La regla indica la estructura que obtendra el automata. Dependiendo del numero de estados y la vecindad, una regla puede tener"+
						 "\n             varias formas de representarse. Asegurate que la regla es menor o igual a la siguiente ecuacion: regla <=  2^((2r+1)*(k-1)+1) - 1 "+
						 "\n             donde -r- es la vecindad y -k- es el numero de estados."+
						 "\n\n          - Una vez seleccionados los datos correctamente, pulse el boton situado en la esquina inferior derecha para ejecutar el programa."+
						 "\n             Veras la representacion del automata y cuando este finalice, aparecera una nueva ventana indicando una grafica del numero de celulas"+
						 "\n             en cada estado por filas del automata.");

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
			text2.setText("\n\n          INFORMACION"+
						 "\n\n          - El automata empleado en este programa consta de una matriz de 800 filas por 900 columnas. Cada celula del automata puede optar hasta"+
						 "\n             16 estados diferentes y se puede emplear las fronteras nulas o cilindricas. "+
						 "\n\n          - Los automatas son sistemas descubiertos dentro del campo de la fisica computacional por John von Neumann en la decada de 1950. La teoria de los"+
						 "\n             automatas celulares se inicia con su precursor John von Neumann a finales de la decada de 1940 con su libro Theory of Self-reproducing Automata"+
						 "\n\n          - Aunque John von Neumann puso en practica los AA.CC., estos fueron concebidos en los 40 por Konrad Zuse y Stanislaw Ulam. Zuse penso"+
						 "\n             en los -espacios de computo- (computing spaces), como modelos discretos de sistemas fisicos. Las contribuciones de Ulam vinieron al final"+
						 "\n             de los 40, poco despues de haber inventado con Nicholas Metropolis el Metodo de Montecarlo.");

			pane2 = new JScrollPane(text2);
			pane2.setBounds(10,40,875,400);
			marcoSecundario2.add(pane2);
		}//AcercaDe
	}//ActionPerformed
}//Marco

class LaminaTrabajoGeneral extends JPanel implements ActionListener{
	
	//JPanels
	JPanel laminaHerramientas;
	JPanel laminaProgramas;
	JPanel laminaBotones;

	//JLABELS
	JLabel Generadores = new JLabel("   Generadores:");
	JLabel CFront = new JLabel("   C.Fronteras:");
	JLabel Est= new JLabel("   Estado:");
	JLabel Vec = new JLabel("   Vecindad:");
	JLabel Reg = new JLabel("   Regla:");
	JLabel Col = new JLabel("   Columnas:");
	JLabel Gen = new JLabel("   Generaciones:");

	//JPanel laminaGrafica;
	ca1DSimulator laminaGrafica;
	int regla;
	int aleaorio;
	int GV,ST,nColumnas,nGeneraciones;

	//Variables LaminaProgramas
	JComboBox generadores;
	JComboBox fronteras;
	JTextField cajaRegla;
	JTextField cajaEstados;
	JTextField cajaVecindad;
	JTextField cajaColumnas;
	JTextField cajaGeneraciones;

	//Variables LaminaBotones
	JButton Ejecutar;
	JButton gCurvaPoblacion;
	JButton gdHamming;
	JButton gEntropia;
	JButton mdHamming;
	JButton mEntropia;

	//Variable randomGenerator
	int tipoGenerador=0, tipoFrontera=0;
	randomGenerator numeroAleatorio;

	//Flag de botones
	boolean flagButton=false;

	public LaminaTrabajoGeneral(){
		setLayout(new BorderLayout(2,3));
		//laminaGrafica = new JPanel();
		laminaHerramientas = new JPanel();
		laminaProgramas = new JPanel();
		laminaBotones = new JPanel();

		//Botones 
		Ejecutar = new JButton(new ImageIcon("ejecutar.png"));
		Ejecutar.setBounds(103,30,100,100);
		Ejecutar.setOpaque(false);
		Ejecutar.setBackground(Color.GRAY);
		Ejecutar.addActionListener(this);
		//Ejecutar.setContentAreaFilled(false);
		//Ejecutar.setBorderPainted(false);
		gdHamming = new JButton("D.Hamming");
		gdHamming.setBounds(50,150,100,30);
		gdHamming.addActionListener(this);
		gEntropia = new JButton("Entropia");
		gEntropia.setBounds(160,150,100,30);
		gEntropia.addActionListener(this);
		mdHamming = new JButton("Media D.H.");
		mdHamming.setBounds(50,200,100,30);
		mdHamming.addActionListener(this);
		mEntropia = new JButton("Media S");
		mEntropia.setBounds(160,200,100,30);
		mEntropia.addActionListener(this);
		gCurvaPoblacion = new JButton("C.Poblacion");
		gCurvaPoblacion.setBounds(105,300,110,30);
		gCurvaPoblacion.addActionListener(this);

		//LaminaGrafica
		laminaGrafica = new ca1DSimulator();
		setBorder(BorderFactory.createLineBorder(Color.black));
		add(laminaGrafica, BorderLayout.CENTER);

		//LaminaProgramas
		laminaProgramas.setLayout(new GridLayout(7,2,0,2));
		cajaEstados = new JTextField("2");
		cajaVecindad = new JTextField("1");
		cajaRegla = new JTextField("10");
		cajaColumnas = new JTextField("1605");
		cajaGeneraciones = new JTextField("990");
		generadores = new JComboBox();

		generadores.addItem("Ninguno");
		generadores.addItem("26.1a");
		generadores.addItem("26.1b");
		generadores.addItem("26.2");
		generadores.addItem("26.3");
		generadores.addItem("Generador Combinado");
		generadores.addItem("Fishman Y Moore 1");
		generadores.addItem("Fishman Y Moore 2");
		generadores.addItem("RANDU");
		generadores.addActionListener(this);
		fronteras = new JComboBox();
		fronteras.addItem("Nula");
		fronteras.addItem("Cilindrica");
		fronteras.addActionListener(this);
		Generadores.setForeground(new Color(220,220,220));
		Generadores.setFont(new Font("Comic Sans MS", 1, 16));
		CFront.setFont(new Font("Comic Sans MS", 1, 16));
		CFront.setForeground(new Color(220,220,220));
		Est.setFont(new Font("Comic Sans MS", 1, 16));
		Est.setForeground(new Color(220,220,220));
		Vec.setFont(new Font("Comic Sans MS", 1, 16));
		Vec.setForeground(new Color(220,220,220));
		Reg.setFont(new Font("Comic Sans MS", 1, 16));
		Reg.setForeground(new Color(220,220,220));
		Gen.setFont(new Font("Comic Sans MS", 1, 16));
		Gen.setForeground(new Color(220,220,220));
		Col.setFont(new Font("Comic Sans MS", 1, 16));
		Col.setForeground(new Color(220,220,220));

		laminaProgramas.add(Generadores);
		laminaProgramas.add(generadores);
		laminaProgramas.add(CFront);
		laminaProgramas.add(fronteras);
		laminaProgramas.add(Est);
		laminaProgramas.add(cajaEstados);
		laminaProgramas.add(Vec);
		laminaProgramas.add(cajaVecindad);
		laminaProgramas.add(Reg);
		laminaProgramas.add(cajaRegla);
		laminaProgramas.add(Col);
		laminaProgramas.add(cajaColumnas);
		laminaProgramas.add(Gen);
		laminaProgramas.add(cajaGeneraciones);
		laminaProgramas.setBackground(new Color(75,1,55));
		laminaProgramas.setBorder(BorderFactory.createLineBorder(Color.black));

		//LaminaBotones
		laminaBotones.setLayout(null);
		laminaBotones.add(Ejecutar);
		laminaBotones.add(gCurvaPoblacion);
		laminaBotones.add(gdHamming);
		laminaBotones.add(gEntropia);
		laminaBotones.add(mdHamming);
		laminaBotones.add(mEntropia);
		laminaBotones.setBackground(new Color(75,1,55));
		laminaBotones.setBorder(BorderFactory.createLineBorder(Color.black));

		//LaminaHerramientas
		laminaHerramientas.setLayout(new GridLayout(2,1,0,2));
		laminaHerramientas.add(laminaProgramas);
		laminaHerramientas.add(laminaBotones);
		add(laminaHerramientas, BorderLayout.EAST);

	}//Constructor

	public void actionPerformed(ActionEvent e){
		long TInicio, TFin, tiempo;
		if(e.getSource()==generadores){
			tipoGenerador = generadores.getSelectedIndex();	
		}//Evento de JComboBox Generadores

		if(e.getSource()==fronteras){
			tipoFrontera = fronteras.getSelectedIndex();
			ObtenerFrontera(tipoFrontera);
		}//Evento de JComboBox Fronteras

		if(e.getSource()==Ejecutar){
			GV = Integer.parseInt(cajaVecindad.getText());
			ST = Integer.parseInt(cajaEstados.getText());
			regla = Integer.parseInt(cajaRegla.getText());
			nColumnas = Integer.parseInt(cajaColumnas.getText());
			nGeneraciones = Integer.parseInt(cajaGeneraciones.getText());

			if(ST > /*15*/ 5 || ST < 2){
				JOptionPane.showMessageDialog(null,"Solo puedes elegir ESTADOS entre 2-15","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			else if (GV > (nColumnas-1) || GV < 1) {
				JOptionPane.showMessageDialog(null,"La VECINDAD debe estar entre 1-"+(nColumnas-1),"ERROR",JOptionPane.ERROR_MESSAGE);
			}
			else if(regla>(Math.pow(ST,Math.pow(ST,(2*GV+1))))){
				JOptionPane.showMessageDialog(null,"Regla no compatible con estados y vecindad introducidos","AVISO",JOptionPane.WARNING_MESSAGE);
			}
			else if(nColumnas>1605){
				JOptionPane.showMessageDialog(null,"El numero de COLUMNAS sobrepasa los limites del Canvas.\n        MAX = 1605","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			else if(nGeneraciones>990){
				JOptionPane.showMessageDialog(null,"El numero de GENERACIONES sobrepasa los limites del Canvas.\n        MAX = 990","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			else if(nGeneraciones>nColumnas){
				JOptionPane.showMessageDialog(null,"El numero de GENERACIONES debe ser MENOR al de COLUMNAS.\n","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			else{
				laminaGrafica.AjustarDimensiones(nColumnas,nGeneraciones);
				ObtenerPuntos(tipoGenerador);
				TInicio = System.currentTimeMillis(); //Inicio
				laminaGrafica.getRegla(GV,ST,regla);
				laminaGrafica.Reglas();
				try{
					laminaGrafica.llamadaConcurrente();
					laminaGrafica.llamadaPaintConcurrente();
				}catch(InterruptedException pw){};
				TFin = System.currentTimeMillis(); //Final
  				tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
  				System.out.println("Tiempo de ejecucion en milisegundos: " + tiempo);
  				flagButton = true;//Una vez ejecutado el programa se podra mostrar las graficas de DH y S y sus medias correspondientes
			}//else
		}//Ejecutar

		if(e.getSource()==gCurvaPoblacion){
			if(flagButton){
				laminaGrafica.MostrarCurvaPoblacion();
			}
			else{
				JOptionPane.showMessageDialog(null,"Ejecuta el programa al menos 1 vez para ver la grafica de la CURVA DE POBLACION","AVISO",JOptionPane.WARNING_MESSAGE);
			}
		}//Grafica Distancia Hamming

		if(e.getSource()==gdHamming){
			if(flagButton){
				laminaGrafica.MostrarGraficaHamming();
			}
			else{
				JOptionPane.showMessageDialog(null,"Ejecuta el programa al menos 1 vez para ver la grafica de DISTANCIA HAMMING","AVISO",JOptionPane.WARNING_MESSAGE);
			}
		}//Grafica Distancia Hamming

		if(e.getSource()==gEntropia){
			if(flagButton){
				laminaGrafica.MostrarGraficaEntropia();
			}
			else{
					JOptionPane.showMessageDialog(null,"Ejecuta el programa al menos 1 vez para ver la grafica de ENTROPIA","AVISO",JOptionPane.WARNING_MESSAGE);
			}
		}//Grafica Entropia

		if(e.getSource()==mdHamming){
			if(flagButton){
				JOptionPane.showMessageDialog(null,"La distancia de Hamming media es: "+laminaGrafica.MostrarDHMedia(),"DISTANCIA HAMMING MEDIA",JOptionPane.	INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null,"Ejecuta el programa al menos 1 vez para ver la D.H. MEDIA","AVISO",JOptionPane.WARNING_MESSAGE);
			}
		}//Media DH

		if(e.getSource()==mEntropia){
			if(flagButton){
				JOptionPane.showMessageDialog(null,"La Entropia media es: "+laminaGrafica.MostrarSMedia(),"ENTROPIA MEDIA",JOptionPane.	INFORMATION_MESSAGE);
			}
			else{
					JOptionPane.showMessageDialog(null,"Ejecuta el programa al menos 1 vez para ver la ENTROPIA MEDIA","AVISO",JOptionPane.WARNING_MESSAGE);
			}
		}//Media S	
	}//ActionPerformed

	public void ObtenerPuntos(int g){
		numeroAleatorio = new randomGenerator(nColumnas);
			switch(g){

				case 0:laminaGrafica.NoRandom();
				break;

				case 1:laminaGrafica.Random(numeroAleatorio.generador1(ST));
				break;

				case 2:laminaGrafica.Random(numeroAleatorio.generador2(ST));
				break;

				case 3:laminaGrafica.Random(numeroAleatorio.generador3(ST));
				break;

				case 4:laminaGrafica.Random(numeroAleatorio.generador4(ST));
				break;

				case 5:laminaGrafica.Random(numeroAleatorio.generadorCombinado(ST));
				break;

				case 6:laminaGrafica.Random(numeroAleatorio.FishmanYMoore1(ST));
				break;

				case 7:laminaGrafica.Random(numeroAleatorio.FishmanYMoore2(ST));
				break;

				case 8:laminaGrafica.Random(numeroAleatorio.RANDU(ST));
				break;
			}//switch
	}//ObtenerPuntos

	public void ObtenerFrontera(int f){
		switch(f){
			case 0:laminaGrafica.AsignarCondicionFrontera(false);
			break; //Frontera Nula

			case 1:laminaGrafica.AsignarCondicionFrontera(true);
			break; //Frontera Cilindrica
		}
	}//ObtenerFrontera

}//Clase LaminaTrabajoGeneral