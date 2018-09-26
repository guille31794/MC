import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;


public class HomeBrewFrame
{
	//JPanel panel = new JPanel();
	
	public HomeBrewFrame()
	{
		JFrame mainFrame = new JFrame("Home Brewed Frame");
		mainFrame.setLayout(null);
		
		gridJP(); bordJP(); flowJP();
		
		mainFrame.setSize(800, 600);
		mainFrame.setResizable(true);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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