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

public class LaminaTrabajoGeneral extends JFrame implements ActionListener{
	private JButton btnCifrar1;
    private JButton btnCifrar2;
    private JButton btnExaminar;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JTabbedPane jTabbedPane1;
    static JTextArea txtComplejoCifrado;
    private JTextField txtRuta;
    static JTextArea txtSimpleCifrado;
   	static JTextArea txtSimpleCifrar;
    private Component modalToComponent;
    static String lines;
    
    private JMenuBar BarraMenu;
    private JMenu Ayuda,AcercaDe;
    private JMenuItem ayuda,acercade;
    private JComboBox ListaProgramas;
    private LaminaTrabajoGeneral laminaTrabajo;

	public LaminaTrabajoGeneral(){
        setTitle("Automata Celular Cifrado de Texto - Fernando Medina Delgado");
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
	        jScrollPane1 = new JScrollPane();
	        txtSimpleCifrar = new JTextArea();
	        jLabel1 = new JLabel();
	        jLabel2 = new JLabel();
	        jScrollPane2 = new JScrollPane();
	        txtSimpleCifrado = new JTextArea();
	        btnCifrar1 = new JButton();
	        jPanel2 = new JPanel();
	        jLabel3 = new JLabel();
	        txtRuta = new JTextField();
	        btnExaminar = new JButton();
	        jLabel4 = new JLabel();
	        jScrollPane3 = new JScrollPane();
	        txtComplejoCifrado = new JTextArea();
	        btnCifrar2 = new JButton();

	        txtSimpleCifrar.setColumns(20);
	        txtSimpleCifrar.setRows(5);
	        jScrollPane1.setViewportView(txtSimpleCifrar);

	        jLabel1.setText("Introduce el texto a cifrar:");

	        jLabel2.setText("Texto cifrado:");

	        txtSimpleCifrado.setColumns(20);
	        txtSimpleCifrado.setRows(5);
	        jScrollPane2.setViewportView(txtSimpleCifrado);

	        btnCifrar1.setText("Cifrar");
	        btnCifrar1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                btnCifrar1ActionPerformed(evt);
	            }
	        });

	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jScrollPane1)
	                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel1)
	                            .addComponent(jLabel2))
	                        .addGap(0, 0, Short.MAX_VALUE))
	                    .addComponent(btnCifrar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addContainerGap())
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addGap(14, 14, 14)
	                .addComponent(jLabel1)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(18, 18, 18)
	                .addComponent(btnCifrar1)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
	                .addComponent(jLabel2)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap())
	        );

	        jTabbedPane1.addTab("Cifrar texto", jPanel1);

	        jLabel3.setText("Ruta:");

	        btnExaminar.setText("Examinar ...");
	        btnExaminar.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                btnExaminarActionPerformed(evt);
	            }
	        });

	        jLabel4.setText("Archivo de texto cifrado:");

	        txtComplejoCifrado.setColumns(20);
	        txtComplejoCifrado.setRows(5);
	        jScrollPane3.setViewportView(txtComplejoCifrado);

	        btnCifrar2.setText("Cifrar");
	        btnCifrar2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                btnCifrar2ActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
	        jPanel2.setLayout(jPanel2Layout);
	        jPanel2Layout.setHorizontalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel2Layout.createSequentialGroup()
	                        .addComponent(jLabel3)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(txtRuta)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(btnExaminar))
	                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
	                    .addGroup(jPanel2Layout.createSequentialGroup()
	                        .addComponent(jLabel4)
	                        .addGap(0, 0, Short.MAX_VALUE))
	                    .addComponent(btnCifrar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addContainerGap())
	        );
	        jPanel2Layout.setVerticalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel3)
	                    .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(btnExaminar))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(btnCifrar2)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
	                .addComponent(jLabel4)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap())
	        );

	        jTabbedPane1.addTab("Cifrar archivo", jPanel2);

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jTabbedPane1)
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jTabbedPane1)
	        );

	        pack();
	    }//iniciar componentes

    private void btnCifrar1ActionPerformed(java.awt.event.ActionEvent evt) {
        ventanaGenCif.tab=1;                                         
        new ventanaGenCif().setVisible(true);
    }   
    private void btnCifrar2ActionPerformed(java.awt.event.ActionEvent evt) {
        ventanaGenCif.tab=2;                                        
        new ventanaGenCif().setVisible(true);
    }
    private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {
    	JFileChooser fileChooser = new JFileChooser();                                       
        if (fileChooser.showOpenDialog(modalToComponent) == JFileChooser.APPROVE_OPTION) {
    		File file = fileChooser.getSelectedFile();
    		lines=openFile(file);
		}
    }

    private String openFile(final File inputFile) {
        int i=0;
        String line;
        String lines = new String();
    	try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            txtRuta.setText(inputFile.getAbsolutePath());       	
            
	        while ((line = reader.readLine()) != null) {
                lines += line + "\n";
                i++;
            }

	    } catch (final IOException e) {
	        e.printStackTrace();
	        // todo: handle exception.
	    }

        return lines;
	}               

    public static void main(String args[]) {
        new LaminaTrabajoGeneral().setVisible(true);
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
}//Clase LaminaTrabajoGeneral





class ventanaGenCif extends JFrame {
	private JMenuBar BarraMenu;
    private JButton btnRun;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JLabel lbEEspacial;
    private JLabel lbETemporal;
    private JLabel lbHamming;
    private JList<String> listReglas;
    private static JTextField txtKey;
    private int nCel;
    private int nGen=4000;
    private static int indexList=0;
    private static int[] celulaInicial;
    private static int[] vCifrante;
    private static String txtCifrado;
    static int tab; 

    public ventanaGenCif() {
    	setTitle("Selecion de cifrado - Fernando Medina Delgado");
        Toolkit miPantalla = Toolkit.getDefaultToolkit(); //Se almacena en el objeto 'miPantalla' el sistema nativo la ventana. Sirve para poder centrar posteriormente la ventana
        Dimension tamPantalla = miPantalla.getScreenSize(); //En 'tamPantalla' almacenamos el tamaño de la pantalla del objeto 'miPantalla'
        int altPantalla = tamPantalla.height; //obtenemos la altura de nuesta pantalla en un entero     
        int anchPantalla = tamPantalla.width; //obtenemos la anchura de nuetra pantalla en un entero
        setIconImage(new ImageIcon(getClass().getResource("/icons/triforce.png")).getImage());
        setSize(anchPantalla-700, altPantalla-300);
        setLocation(anchPantalla/4, altPantalla/8);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        getContentPane().setBackground(new Color(255,175,175));
        iniciarComponentes();
    }
                         
    private void iniciarComponentes() {

        btnRun = new JButton();
        jLabel1 = new JLabel();
        txtKey = new JTextField();
        jScrollPane1 = new JScrollPane();
        listReglas = new JList<>();
        jLabel2 = new JLabel();
        lbHamming = new JLabel();
        lbEEspacial = new JLabel();
        lbETemporal = new JLabel();
                                 
        btnRun.setText("Run");
        btnRun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRunActionPerformed(evt);
            }
        });

        jLabel1.setText("Key:");

        txtKey.setText("1234567890");

        String[] reglas;
        String[][] calcReglas=new String[5][3];

        DefaultListModel listModel = new DefaultListModel();
        try (final BufferedReader reader = new BufferedReader(new FileReader("reglas"))) {
        	String line;
            
            int i=0;
   			
	        while ((line = reader.readLine()) != null) {
                reglas = line.split(" ");
                listModel.addElement(reglas[0]);
                calcReglas[i][0]=reglas[1];
                calcReglas[i][1]=reglas[2];
                calcReglas[i][2]=reglas[3];
                i++;
	        }
    	} catch (final IOException e) {
	        e.printStackTrace();
	        // todo: handle exception.
    	}
    	listReglas = new JList(listModel);
        jScrollPane1.setViewportView(listReglas);

        
		listReglas.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList listReglas = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {

		            // Double-click detected
		            indexList = listReglas.locationToIndex(evt.getPoint());
                    lbHamming.setText("Distancia Hamming media: "+calcReglas[indexList][0]);
                    lbEEspacial.setText("Entropia espacial media: "+calcReglas[indexList][1]);
                    lbETemporal.setText("Entropia temporal: "+calcReglas[indexList][2]);
                }
		    }
		});


        jLabel2.setText("Reglas recomendadas:");

        lbHamming.setText("Distancia Hamming media: ");

        lbEEspacial.setText("Entropia espacial media:");

        lbETemporal.setText("Entropia temporal:");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKey)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRun))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbHamming)
                                    .addComponent(lbEEspacial)
                                    .addComponent(lbETemporal))))
                        .addGap(0, 113, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRun)
                    .addComponent(txtKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbHamming)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbEEspacial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbETemporal)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }

    private static int[] getCelulaInicial(){
        if(txtKey.getText().equals("")){
            return new int[0];
        }
        else{
        	String binary = new BigInteger(txtKey.getText().getBytes()).toString(2);
            int[] res=new int[binary.length()];
            int i=0, j=0;
            while(i<binary.length()){
                
                if(Character.isDigit(binary.charAt(i))){
                    res[j]=Character.getNumericValue(binary.charAt(i));
                
                    j++;
                }
                i++;
            }

            return res;
        }
    }

    private static int[] getTextoSimpleCifrar(){
        if(LaminaTrabajoGeneral.txtSimpleCifrar.getText().equals("")){
            return new int[0];
        }
        else{
        	String binary = new BigInteger(LaminaTrabajoGeneral.txtSimpleCifrar.getText().getBytes()).toString(2);
            int[] res=new int[binary.length()];
            int i=0, j=0;
            while(i<binary.length()){
                
                if(Character.isDigit(binary.charAt(i))){
                    res[j]=Character.getNumericValue(binary.charAt(i));
                
                    j++;
                }
                i++;
            }

            return res;
        }
    }

    private static int[] getTextoComplejoCifrar(){
        if(LaminaTrabajoGeneral.lines.equals("")){
            return new int[0];
        }
        else{
            String binary = new BigInteger(LaminaTrabajoGeneral.lines.getBytes()).toString(2);
            int[] res=new int[binary.length()];
            int i=0, j=0;
            while(i<binary.length()){
                
                if(Character.isDigit(binary.charAt(i))){
                    res[j]=Character.getNumericValue(binary.charAt(i));
                
                    j++;
                }
                i++;
            }

            return res;
        }
    }

    private static int[] getRegla(int numRegla){
        int[] res = new int[8];
            
        int number = numRegla;
        for(int i=0;i<8;++i){
            if(number==0){
                res[i]=0;
            }
            else{
                res[i]=number%2;
                number/=2;
            }
        }
        return res;
    }

    private static void cifrar(){
        int[] vcifrar=null;
        if(tab==1){
            LaminaTrabajoGeneral.txtSimpleCifrado.setText(null);
            vcifrar=getTextoSimpleCifrar();           
        }else if(tab==2){
            LaminaTrabajoGeneral.txtComplejoCifrado.setText(null);
            vcifrar=getTextoComplejoCifrar(); 
        }

        vCifrante = ca1DSimulator.eVectTemporal.clone();
        int[] vcifrado = new int[vcifrar.length];

        for(int i=0;i<vcifrar.length;++i){
            vcifrado[i]=vcifrar[i]^vCifrante[i];
        }

        String strCifradobits = Arrays.toString(vcifrado).replaceAll("\\[|\\]|,|\\s", "");
        String strCifrado = new String(new BigInteger(strCifradobits, 2).toByteArray());

        if(tab==1){
            LaminaTrabajoGeneral.txtSimpleCifrado.append(strCifrado);
        }else if(tab==2){
            LaminaTrabajoGeneral.txtComplejoCifrado.append(strCifrado);
        }

    }

    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {
    	celulaInicial=getCelulaInicial();
    	int i_regla=Integer.parseInt(listReglas.getModel().getElementAt(indexList));
    	nCel=celulaInicial.length;                           
     	ca1DSimulator.init(nCel,nGen,celulaInicial,getRegla(i_regla));
     	try{
     		ca1DSimulator.runSimulation();

    	}catch(InterruptedException e) {}

    	this.setVisible(false);
    	cifrar();
    }                                                 
                
}