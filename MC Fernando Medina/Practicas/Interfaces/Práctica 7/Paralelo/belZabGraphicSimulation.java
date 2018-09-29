import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;


public class belZabGraphicSimulation{
	
	static JFrame win;
	static int dim;
	static JPanel mainBZ;
	static panelBelZab panelbelzab;
	static panelOptionsBZ panelopt;

	belZabGraphicSimulation(){
		mainBZ = new JPanel();

        panelopt=new panelOptionsBZ();
        mainBZ.setLayout(new BorderLayout());
        mainBZ.add(panelopt,BorderLayout.EAST);
		mainBZ.setVisible(true);		
	}

	public static void addPanelImage(int d){
        dim=d;
        panelbelzab=new panelBelZab(dim); 
		mainBZ.add(panelbelzab,BorderLayout.CENTER);
    }

    public static void restart(){
        parallelBelZab.dim=0;
        //parallelBelZab.start=false;
        panelbelzab.setVisible(false);
    }

	public static void update() {
        for(int x=0;x<dim;++x){
        	for(int y=0;y<dim;++y){	
        		panelbelzab.image.setRGB(y, x, parallelBelZab.color[x][y].getRGB());
        	}
        }
        panelbelzab.repaint();
    }
}

class panelBelZab extends JPanel{
	static BufferedImage image;

	panelBelZab(int dim){
		image = new BufferedImage(dim, dim, BufferedImage.TYPE_INT_ARGB);
	}

	
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}

class panelOptionsBZ extends JPanel{



    //ImageIcon eje = new ImageIcon(getClass().getResource("/icons/ejecutar.png"));
    //ImageIcon ptos = new ImageIcon(getClass().getResource("/icons/stop.png"));

    private JSpinner SpinnerDim = new JSpinner();
    private JLabel lbDim = new JLabel(" Dimension : ");
    private JSpinner SpinnerGen = new JSpinner();
    private JLabel lbGen = new JLabel(" Generaciones : ");
    JTextField txtAlfa = new JTextField("1.2");
    private JLabel lbAlfa = new JLabel(" Alfa : ");
    JTextField txtBeta = new JTextField("1.0");
    private JLabel lbBeta = new JLabel(" Beta : ");
  	JTextField txtGamma = new JTextField("1.0");
    private JLabel lbGamma = new JLabel(" Gamma : ");

    JButton btnStart = new JButton("Iniciar");
   // JButton btnSpeedUp = new JButton("SpeedUp"); //Crear un marco con una imagen de fondo de la grafica de speedup

    

    //static GridBagConstraints c;

    panelOptionsBZ(){
        setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
        setLayout(new GridLayout(6,2,1,1));
        setBackground(new Color(220,200,155));

        SpinnerDim.setModel(new javax.swing.SpinnerNumberModel(200, 200, null, 1));
        SpinnerGen.setModel(new javax.swing.SpinnerNumberModel(200, 200, null, 1));
        
        add(lbDim);
        add(SpinnerDim);
        add(lbGen);
        add(SpinnerGen);
        add(lbAlfa);
        add(txtAlfa);
        add(lbBeta);
        add(txtBeta);
        add(lbGamma);
        add(txtGamma);
  

        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(btnStart.getText().equals("Iniciar")){
                 
                    btnStart.setText("Reiniciar");

                    int dim=(Integer)SpinnerDim.getValue();
                    int ngen=(Integer)SpinnerGen.getValue();
                    float alf=Float.parseFloat(txtAlfa.getText());
                    float bet=Float.parseFloat(txtBeta.getText());
                    float gamm=Float.parseFloat(txtGamma.getText());
                    belZabGraphicSimulation.addPanelImage(dim);

                    parallelBelZab.runSimulation(dim,ngen,alf,bet,gamm);


                    
                }
                else if(btnStart.getText().equals("Reiniciar")) {
                    belZabGraphicSimulation.restart();
                    btnStart.setText("Iniciar");
                }
            }
        });

       /* btnSpeedUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                if(btnSpeedUp.getText().equals("SpeedUp")) {
                    System.out.println("SpeedUp");

                }   
            }
        });*/
        add(btnStart);
        //add(btnSpeedUp);

    }

}