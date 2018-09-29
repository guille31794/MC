import javax.imageio.*; // Para usar imagenes
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*; //Importa los metodos de la clase Graphics2D (Figuras)
import java.io.*; //Para variables tipo File
import java.util.*;
import java.math.BigInteger;

public class urm_simulator extends JFrame implements ActionListener{
	private JButton btnCifrar1;
    private JButton Ejecutar;
    private JButton btnExaminar;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JTabbedPane jTabbedPane1;
    static JTextField txtComplejoCifrado;
    static JTextField txtRegistros; //Texto de registros
    private JTextField txtRuta;
    private Component modalToComponent;
   // static String lines;
    
    private JMenuBar BarraMenu;
    private JMenu Ayuda,AcercaDe;
    private JMenuItem ayuda,acercade;
    private JComboBox ListaProgramas;
    private urm_simulator laminaTrabajo;

    private static int[] vCifrante;
    private static String txtCifrado;

    String [] lineas = new String[15];
    int nlineas=0;

	public urm_simulator(){
        setTitle("Micro Interpetre URM - Fernando Medina Delgado");
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
		iniciarComponentes();
	}//Constructor

	 private void iniciarComponentes() {

	        jTabbedPane1 = new JTabbedPane();
	        jPanel1 = new JPanel();
	        jLabel1 = new JLabel();
	        jLabel2 = new JLabel();
	        jScrollPane2 = new JScrollPane();
	        
	        jPanel2 = new JPanel();
	        jLabel3 = new JLabel();
	        jLabel5 = new JLabel();
	        txtRuta = new JTextField();
	        btnExaminar = new JButton();
	        jLabel4 = new JLabel();
	        jScrollPane3 = new JScrollPane();
	        txtRegistros = new JTextField();
	        txtComplejoCifrado = new JTextField();
	        Ejecutar = new JButton();

	        //----------------------------------- JTAB---------------------------------------------------
	        jLabel3.setText("Ruta:");

	        btnExaminar.setText("Examinar");
	        btnExaminar.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent evt) {btnExaminarActionPerformed(evt);}});

	        jLabel5.setText("Registros:");

	        jLabel4.setText("Resultado:");

	        Ejecutar.setText("Ejecutar");
	        Ejecutar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                EjecutarActionPerformed(evt);
	            }
	        });

	        txtRegistros.setText("0,0,0,0,0,0,0,0,0");
	        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
	        jPanel2.setLayout(new GridLayout(8,1,0,2));
	        jPanel2.add(jLabel3);
	        jPanel2.add(txtRuta);
	        jPanel2.add(btnExaminar);
	        jPanel2.add(jLabel5);
	        jPanel2.add(txtRegistros);
	        jPanel2.add(Ejecutar);
	        jPanel2.add(jLabel4);
	        jPanel2.add(txtComplejoCifrado);
	        jTabbedPane1.addTab("Ejecutar Archivo", jPanel2);


	        //---------------------------------------------------------------------------------------------------------

	       GroupLayout layout = new GroupLayout(getContentPane());
	       getContentPane().setLayout(layout);
	       layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jTabbedPane1));
	       layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jTabbedPane1));
	       pack();
	    }//iniciar componentes
 
    private void EjecutarActionPerformed(ActionEvent evt) {
    	String cadena, regis;
    	int [] registros = new int[10];
    	int k=0,j=0;

    	regis = txtRegistros.getText();

    	registros[1]=Integer.parseInt(""+regis.charAt(0));
    	registros[2]=Integer.parseInt(""+regis.charAt(2));
    	registros[3]=Integer.parseInt(""+regis.charAt(4));
    	registros[4]=Integer.parseInt(""+regis.charAt(6));
    	registros[5]=Integer.parseInt(""+regis.charAt(8));
    	registros[6]=Integer.parseInt(""+regis.charAt(10));
    	registros[7]=Integer.parseInt(""+regis.charAt(12));
    	registros[8]=Integer.parseInt(""+regis.charAt(14));
    	registros[9]=Integer.parseInt(""+regis.charAt(16));

    	int q=0;
    	for(int i=1; i<nlineas;i++)
    	{
    		q=i;
    		cadena = lineas[i];
    		System.out.println("("+q+"< R1 = "+registros[1]+",R2 = "+registros[2]+",R3 = "+registros[3]+",R4 = "+registros[4]+",R5 = "+registros[5]+",R6 = "+registros[6]+",R7 = "+registros[7]+",R8 = "+registros[8]+",R9 = "+registros[9]+" >)");
    		if(cadena.charAt(0)=='Z'){
    			//System.out.println("Instruccion Z");
    			registros[Integer.parseInt(""+cadena.charAt(2))] = 0;
    			
    		}
    		else if(cadena.charAt(0)=='S'){
    			//System.out.println("Instruccion S");
    			registros[Integer.parseInt(""+cadena.charAt(2))]++;
    		}
    		else if(cadena.charAt(0)=='T'){
    			//System.out.println("Instruccion T");
    			registros[Integer.parseInt(""+cadena.charAt(4))] = registros[Integer.parseInt(""+cadena.charAt(2))];
    		}
    		else if(cadena.charAt(0)=='J'){
    			//System.out.println("Instruccion J");
    			if(registros[Integer.parseInt(""+cadena.charAt(2))] == registros[Integer.parseInt(""+cadena.charAt(4))]){
    				if(Integer.parseInt(""+cadena.charAt(6))>nlineas){
    					i=10000;
    				}
    				else{
    					if(cadena.charAt(7)!=')')
    						i=Integer.parseInt(""+cadena.charAt(6)+cadena.charAt(7))-1;
    					else
    						i=Integer.parseInt(""+cadena.charAt(6))-1;
    				}
    			}
    		}

    		//System.out.println("("+q+"< R1 = "+registros[1]+",R2 = "+registros[2]+",R3 = "+registros[3]+",R4 = "+registros[4]+",R5 = "+registros[5]+",R6 = "+registros[6]+",R7 = "+registros[7]+",R8 = "+registros[8]+",R9 = "+registros[9]+" >)");
    	}//for

    	
		System.out.println("("+nlineas+"< R1 = "+registros[1]+",R2 = "+registros[2]+",R3 = "+registros[3]+",R4 = "+registros[4]+",R5 = "+registros[5]+",R6 = "+registros[6]+",R7 = "+registros[7]+",R8 = "+registros[8]+",R9 = "+registros[9]+" >)"); 

    	txtComplejoCifrado.setText(String.valueOf(registros[1]));
    	System.out.println("Resultado = "+registros[1]);
    }
    private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {
    	JFileChooser fileChooser = new JFileChooser();                                       
        if (fileChooser.showOpenDialog(modalToComponent) == JFileChooser.APPROVE_OPTION) {
    		File file = fileChooser.getSelectedFile();
    		//lines = openFile(file);
    		nlineas = openFile(file);
		}
    }

    private int openFile(final File inputFile) {
        int i=1;
        String line;
        //String lines = new String();
    	try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            txtRuta.setText(inputFile.getAbsolutePath());       	
            
	        while ((line = reader.readLine()) != null) {
	        	//Comprobar de cada linea el tipo de instruccion y enviarla la funcion correspondiente
                //lines += line + "\n";
                lineas[i]=line;
                i++;
            }

	    } catch (final IOException e) {
	        e.printStackTrace();
	        // todo: handle exception.
	    }
	    return i;
	}
          

    public static void main(String args[]) {
        new urm_simulator().setVisible(true);
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
                         "\n\n- El resultado se debe almacenar siempre en el primer registro"+
                         "\n\n- Dispone de 9 Registros para su uso del R1 hasta el R9"+
                         "\n\n- En su programa no debe aparecer el Registro R0, ni puede usar la linea 0 como condicion de salida, use un numero de gran valor como 100000"+
                         "\n\n- En el campo de registros viene por defecto todos los registros a 0 ordenados ascendentemente de izquierda a derecha separado unicamente por ','"+
                         "\n\n- Si hay algun registro que no se vaya a usar, matenga su valor a 0");

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
            text2.setText("\n\n          INFORMACION");

            pane2 = new JScrollPane(text2);
            pane2.setBounds(10,40,875,400);
            marcoSecundario2.add(pane2);
        }//AcercaDe
    }//ActionPerformed
}//Clase urm_simulator
