import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import javax.imageio.*; // Para usar imagenes
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.awt.geom.*; //Importa los metodos de la clase Graphics2D (Figuras)
import java.io.*; //Para variables tipo File
import java.util.*;

public class mainGUI implements ActionListener{

	static int dim=0;
	static JFrame frame;
	private JTabbedPane jTab = new javax.swing.JTabbedPane();
	static ca2DGraphicSimulation ca2dg;

	//marco
	private JMenuBar BarraMenu;
	private JMenu Ayuda,AcercaDe;
	private JMenuItem ayuda,acercade;
	private JComboBox ListaProgramas;

	public mainGUI(){
		Toolkit miPantalla = Toolkit.getDefaultToolkit(); //Se almacena en el objeto 'miPantalla' el sistema nativo la ventana. Sirve para poder centrar posteriormente la ventana
		Dimension tamPantalla = miPantalla.getScreenSize(); //En 'tamPantalla' almacenamos el tamaño de la pantalla del objeto 'miPantalla'
		int altPantalla = tamPantalla.height; //obtenemos la altura de nuesta pantalla en un entero		
		int anchPantalla = tamPantalla.width; //obtenemos la anchura de nuetra pantalla en un entero

		frame = new JFrame();
		frame.setTitle("Juego de la vida - Fernando Medina Delgado");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setIconImage(new ImageIcon(getClass().getResource("/icons/triforce.png")).getImage());
		frame.setSize(anchPantalla-700, altPantalla-300);
		frame.setLocation(anchPantalla/4, altPantalla/8);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		//setResizable(false);
		frame.setLayout(new BorderLayout());
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
		ayuda.addActionListener(this);
		acercade.addActionListener(this);
		Ayuda.add(ayuda);
		AcercaDe.add(acercade);
		BarraMenu.add(Ayuda);
		BarraMenu.add(AcercaDe);
		
		ca2dg = new ca2DGraphicSimulation();
		jTab.addTab("Juego de la vida Conway", ca2dg.panelca2d);
		frame.getContentPane().setLayout(new BorderLayout ());
		frame.getContentPane().add(jTab, BorderLayout.CENTER);
		frame.pack();
        frame.setVisible(true);

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
			text1.setText("\n\n          GUIA DE USO"+
						 "\n\n          - Selecciona el numero de dimensiones. Cuanta mas dimensiones mas durara la ejecucion"+
						 "\n\n          - Presiona el boton PLAY para iniciar la ejecucion. Espere a que se termine la ejecucion para ver el grafico de celulas vivas/muertas."+
						 "\n\n          - Presiona el boton con el icono de la GRAFICA para visualizar la grafica de la ejecucion de la secuencia."+
						 "\n\n          - Durante la ejecucion podras visualizar la cantidad de celulas vivas y muertas en cada instante."
						 );

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
						 "\n\n             El juego de la vida es un automata celular creado por el matematico britanico John Horton Conway en 1970."+
						 "\n             Hizo su primera aparicion publica en el numero de octubre de 1970 de la revista Scientific American, en la columna de juegos matematicos"+
						 "\n             algoritmicamente se puede computar en el juego de la vida."+
						 "\n             Desde un punto de vista teorico, es interesante porque es equivalente a una maquina universal de Turing, es decir, todo lo que se puede computar "+
						 "\n             algoritmicamente se puede computar en el juego de la vida.");

			pane2 = new JScrollPane(text2);
			pane2.setBounds(10,40,875,400);
			marcoSecundario2.add(pane2);
		}//AcercaDe
	}//ActionPerformed


	public static void main(String [] args){
		System.out.println("ejecutando...");
		mainGUI objeto = new mainGUI();
	}

}