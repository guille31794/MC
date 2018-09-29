import javax.swing.*;
import java.awt.*;
import java.awt.event.*; //Para llevar a cabo eventos



public class InterfazEvento
{
	public static void main(String [] args)
	{
			MarcoBotones marco = new MarcoBotones();
			marco.setVisible(true); //Permite que se vea la ventana con 'true'
			marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //JFrame.EXIT_ON_CLOSE permite cerrar el programa al cerrar la ventana

	}
}
class MarcoBotones extends JFrame //Necesario java.swing.*; para heredar JFrame
{
	public MarcoBotones()
	{
		//setSize(250,250);     //Establecer tamaño de ventana
		//setLocation(550,450); //Abre la ventana en una posicion de la pantalla - El pixel 0 de la ventana es el de la esquina superior de la propia ventana
		//setBounds(650,300,500,500); //setBounds nos proporciona las funciones de las dos anteriores en una misma - setBounds(posX,posY,tamX,tamY);

		//setResizable(false); //No permite al usuario redimensionar la ventana de la aplicacion
		//setExtendedState(MAXIMIZED_BOTH); // Abre la aplicacion en pantalla completa

		//*****Para poder centrar la pantalla-----

		//Primero necesitamos obtener unos datos de la pantalla de forma generica
		Toolkit miPantalla = Toolkit.getDefaultToolkit(); //Se almacena en el objeto 'miPantalla' el sistema nativo la ventana. Sirve para poder centrar posteriormente la ventana
		Dimension tamPantalla = miPantalla.getScreenSize(); //En 'tamPantalla' almacenamos el tamaño de la pantalla del objeto 'miPantalla'
		int altPantalla = tamPantalla.height; //obtenemos la altura de nuesta pantalla en un entero
		int anchPantalla = tamPantalla.width; //obtenemos la anchura de nuetra pantalla en un entero

		//Ahora ya podemos centrar la ventana realizando lo siguiente
		setSize(anchPantalla/2, altPantalla/2);
		setLocation(anchPantalla/4, altPantalla/4);
		//********---------------------------------

		setTitle("Interfaz Eventos"); //Establece un titulo en la ventana
		Image miIcono = miPantalla.getImage("Images/icono.png"); //Seleccionamos la imagen que queremos imprimir como icono en la esquina
		setIconImage(miIcono); //Establecemos el icono en la ventana de la interfaz

		LaminaBotones Boton = new LaminaBotones();
		add(Boton);
	}
}//miMarco

class LaminaBotones extends JPanel
{
	JButton botonAzul = new JButton("Azul");
	JButton botonAmarillo = new JButton("Amarillo");
	JButton botonRojo = new JButton("Rojo");

	public LaminaBotones()
	{
		add(botonAzul);
		add(botonAmarillo);
		add(botonRojo);

		ColorBackground Amarillo = new ColorBackground(Color.YELLOW);
		ColorBackground Rojo = new ColorBackground(Color.RED);
		ColorBackground Azul = new ColorBackground(Color.BLUE);

		botonAzul.addActionListener(Azul); //botonAzul desencadena un evento que lo recibe la propia clase LaminaBotonoes por eso ponemos this. Si fuera otra clase pondriamos el nombre del objeto creado de otra clase que quisieramos que fuera la oyente.
		botonAmarillo.addActionListener(Amarillo);
		botonRojo.addActionListener(Rojo);

	}
	private class ColorBackground implements ActionListener //Las clases internan herendan todos lo metodos de la clase donde se ubican por lo que no necesita heredar de JPanel para usar el metodo setBackground por ejemplo.
	{
		Color ColorDeFondo;

		public ColorBackground(Color C)
		{
			ColorDeFondo = C;
		}

		public void actionPerformed(ActionEvent e) //como el metodo run de la interfaz Runnable pero este es el de la interfaz ActionListener
		{
			Object BotonPulsado = e.getSource();

			if(BotonPulsado==botonAzul)
				setBackground(ColorDeFondo);

			else if(BotonPulsado==botonAmarillo)
				setBackground(ColorDeFondo);
			else
				setBackground(ColorDeFondo);
		}
	}//Class Background
	
}//Class LaminaBotones

