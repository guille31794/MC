import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class ca2DGraphicSimulation{
	static int dim;
    static JPanel panelca2d;
	static panelImage2D panelimg;
    static panelOptions2D panelopt;
    static curvasPoblacion winGraf;
    static String[] namesSeries = {"Vivas","Muertas"};
 
	ca2DGraphicSimulation(){
        panelca2d = new JPanel();
        panelopt=new panelOptions2D();

		panelca2d.setLayout(new BorderLayout());
        panelca2d.add(panelopt,BorderLayout.EAST);
		panelca2d.setVisible(true);
	}

    public static void dibujarGraficas(){
        
        winGraf = new curvasPoblacion(ca2DSimulator.matTotalPob,dim);
        winGraf.setLocationRelativeTo(null);
    }

	public static void addPanelImage(int d){
        dim=d;
        panelimg=new panelImage2D(dim);
        panelca2d.add(panelimg,BorderLayout.CENTER);       
    }

    public static void restart(){
        ca2DSimulator.dim=0;
        ca2DSimulator.genActual=0;
        panelimg.setVisible(false);
    }

	public static void update(int[][] mat) {
       	
        for(int y=0;y<ca2DSimulator.dim;++y){
        	for(int x=0;x<ca2DSimulator.dim;++x){
        		Color c=null;
        		if(mat[y][x]==1) c=new Color(255,255,0);
        		else if(mat[y][x]==0) c=new Color(20,20,20);

            	panelimg.image.setRGB(x, y, c.getRGB());
            	panelimg.repaint();
        	}
        	
        }
        panelopt.lbVivas.setText(" : "+ca2DSimulator.totalVivas);
        panelopt.lbMuertas.setText(" : "+ca2DSimulator.totalMuertas);
    }

}

class panelImage2D extends JPanel{
	static BufferedImage image;

	public panelImage2D(int dim){
		image = new BufferedImage(dim, dim, BufferedImage.TYPE_INT_ARGB);
	}
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}

class panelOptions2D extends JPanel{

	ImageIcon eje = new ImageIcon(getClass().getResource("/icons/ejecutar.png"));
	ImageIcon gra = new ImageIcon(getClass().getResource("/icons/graficas.png"));
	ImageIcon ptos = new ImageIcon(getClass().getResource("/icons/ptos.png"));

    private JSpinner SpinnerDimRet = new JSpinner();
    private JLabel lbDimRet = new JLabel(" Dimension: ");
    private JRadioButton op1 = new JRadioButton(" Von Neumann");
    private JRadioButton op2 = new JRadioButton(" Moore");
    private int op=2;
    private JButton btnGrafica = new JButton(gra);
    JButton btnStart = new JButton(eje);
    JLabel lbVivas = new JLabel(new ImageIcon(getClass().getResource("/icons/vivas.png")));
    JLabel lbMuertas = new JLabel(new ImageIcon(getClass().getResource("/icons/muertas.png")));
    static GridBagConstraints c;
    





    panelOptions2D(){
        setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
        setLayout(new GridBagLayout());
        setBackground(new Color(220,200,155));
        c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;

        add(lbDimRet,c);

        SpinnerDimRet.setModel(new javax.swing.SpinnerNumberModel(200, 200, null, 1));

        c.gridx = 1;
        c.gridy = 0;

        add(SpinnerDimRet);

        ButtonGroup group = new ButtonGroup();
        group.add(op1);
        group.add(op2);
        op2.setSelected(true);

        op1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op=1;
            }
        });

        op2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op=2;
            }
        });

        c.gridx = 0;
        c.gridy = 1;

        add(op1,c);

        c.gridx = 1;
        c.gridy = 1;

        add(op2,c);

        c.gridx = 0;
        c.gridy = 2;
        
        btnGrafica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(btnStart.getIcon().equals(ca2DSimulator.res)) ca2DGraphicSimulation.dibujarGraficas();
            }
        });
        add(btnGrafica,c);

        c.gridx = 1;
        c.gridy = 2;

        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(btnStart.getIcon().equals(eje)){
                    btnStart.setIcon(ptos);

                    ca2DSimulator.dim=(Integer)SpinnerDimRet.getValue();

                    ca2DSimulator.runSimulation(ca2DSimulator.dim, 1000, op);

                }
                else if(btnStart.getIcon().equals(ca2DSimulator.res)) {
                    ca2DGraphicSimulation.restart();
                    btnStart.setIcon(eje);
                }   
            }
        });
        add(btnStart,c);

        c.gridx = 0;
        c.gridy = 3;

        add(lbVivas,c);

        c.gridx = 0;
        c.gridy = 4;

        add(lbMuertas,c);
    }

}