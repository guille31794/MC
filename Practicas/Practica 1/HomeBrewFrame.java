import javax.swing.*;
import java.awt.*;


public class HomeBrewFrame extends JFrame
{
	 private JPanel panel1, panel2, panel3;
	 private JLabel label1, label2, label3;
	 private JButton button1, button2, button3;
	 private JMenu menu1;
	 private JMenuBar menuBar;

	 private void initComponents()
	 {
		 		label1 = new JLabel();
		 		label1.setText("Opcion");
		 		label1.setBounds(120, 400, 150, 20);
		 		label1.setPreferredSize(new Dimension(175, 100));

		 		button1 = new JButton();
		 		button1.setText("Pinche");
		 		button1.setBounds(120, 50, 150, 20);

				menu1 = new JMenu();
		 		menuBar = new JMenuBar();

				panel1 = new JPanel();
				//panel1.setLayout(new BorderLayout());
				panel1.add(menuBar);//, BorderLayout.NORTH);
		 		panel1.setPreferredSize(new Dimension(800, 600));
		 		panel1.add(button1);
		 		panel1.add(label1);
	 }

	public HomeBrewFrame()
	{
		initComponents();

		//this.setLayout(null);
		this.setSize(800, 600);
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(panel1);
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run(){
				new HomeBrewFrame();
			}
		});
	}
}

//https://es.wikihow.com/hacer-una-interfaz-gr%C3%A1fica-de-grilla-en-Java
//https://jagonzalez.org/usar-jpanel-en-java/
//http://www.forosdelweb.com/f45/jmenubar-jframe-756220/#post3182175
