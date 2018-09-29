import javax.swing.*;
//import java.awt.Toolkit; //Necesario para objetos Toolkit
//import java.awt.Dimension; //Necesario para objetos Dimension
import java.awt.*; //Importando todos nos aseguramos el Toolkit, Dimension e Image
import java.awt.geom.*; //Importa los metodos de la clase Graphics2D (Figuras)
import javax.imageio.*; // Para usar imagenes
import java.io.*; //Para variables tipo File
public class InterfazSwing
{
	public static void main(String [] args)
	{
			miMarco marco = new miMarco();
			marco.setVisible(true); //Permite que se vea la ventana con 'true'
			marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //JFrame.EXIT_ON_CLOSE permite cerrar el programa al cerrar la ventana

	}
}//InterfazSwing

//Clase miMarco solo servira para dar tamaño a la ventana
class miMarco extends JFrame //Necesario java.swing.*; para heredar JFrame
{
	public miMarco()
	{
		/*
		String fuente; //Declaro fuera para que lo podamos mandar al constructor mas adelante
		boolean fuenteOK = false; //necesario que esté fuera del bucle doWhile
		do
		{
			//Fuentes de texto
			fuente = JOptionPane.showInputDialog("Introduce el nombre de la fuente");
			
			String [] NombresDeFuentes = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
			for(String nombreFuentes : NombresDeFuentes)
			{
				if(nombreFuentes.equals(fuente))
				{
					fuenteOK = true;
				}
			}

			if(fuenteOK)
				System.out.println("Fuente seleccionada satisfatoriamente");
			else
				System.out.println("La fuente introducida no corresponde a ninguna de las disponibles");


		}while(!fuenteOK);
		*/
		



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

		setTitle("Interfaz Java Swing - 15442784D"); //Establece un titulo en la ventana
		Image miIcono = miPantalla.getImage("Images/icono.png"); //Seleccionamos la imagen que queremos imprimir como icono en la esquina
		setIconImage(miIcono); //Establecemos el icono en la ventana de la interfaz

		/*Lamina1 miLamina1 = new Lamina1();
		add(miLamina1);*/

		/*Lamina2 miLamina2 = new Lamina2();
		add(miLamina2);*/

		/*Lamina3 miLamina3 = new Lamina3(fuente);
		add(miLamina3);*/
		//miLamina3.setForeground(Color.BLUE); //Establece todo lo que se imprime en esta lamina del color seleccionado

		Lamina4 miLamina4 = new Lamina4();
		add(miLamina4);

	}
}//miMarco

//Clase de Graphics
class Lamina1 extends JPanel
{
	//Estamos alterando el metodo que ya heredamos de JPanel, por ello, necesitamos declararlo dentro de la clase y añadir los metodos que queramos que haga aparte de los suyos
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawString("Aprediendo Swing", 100, 100); //Para imprimir un texto en la lamina
		g.drawRect(95,50,110,100); //Dibuja un cuadrado o rectangulo. Las dos primeras cifras son la posicion y la dos ultimas las dimensiones del cuadrado
		g.drawLine(100,101,200,101); //Dibuja una linea recta. Las dos primeras cifras son el punto inicial y las dos ultimas el punto final de la recta.
		g.drawArc(100,200,50,100,180,180); //Dibuja un arco. Las dos primeras cifras situan el punto inicial. Las dos centrales el ancho y el alto. Las dos finales El angulo inicial y el arco de angulo.
	}
}

//Clase de Graphics2D
class Lamina2 extends JPanel
{
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		//Rectangulo
		Rectangle2D rectangulo = new Rectangle2D.Double(600,100,300,200); //pos x,y - ancho y altura
		g2.setPaint(Color.BLUE); //Se asigna color
		g2.draw(rectangulo); //dibuja el trazo con el color seleccionado	
		g2.setPaint(Color.GRAY); //Se asigna color
		g2.fill(rectangulo); //Se rellena la figura
		

		//Elipse
		Ellipse2D elipse = new Ellipse2D.Double();
		elipse.setFrame(rectangulo); //Imprimimos las elipse dentro del rectangulo anteriormente impreso, si no quisieramos que se viera el rectangulo, no se escribe  g2.draw(rectangulo)
		g2.setPaint(Color.RED); //Para el trazo
		g2.draw(elipse);
		g2.setPaint(Color.CYAN); //Para el fondo
		g2.fill(elipse); //Rellena la elipse
		g2.draw(elipse); //dibuja la elipse
	}
}

//Clase de Fuentes
class Lamina3 extends JPanel
{
	String FuenteSeleccionada;
	Lamina3(String F)
	{
		FuenteSeleccionada = F;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g3 = (Graphics2D) g;

		Font miFuente = new Font(FuenteSeleccionada,Font.BOLD,46);
		g3.setFont(miFuente);
		g3.setColor(Color.GREEN);
		g3.drawString("Negrita", 400,200);

		g3.setFont(new Font("Courier New", Font.ITALIC, 26));
		g3.setColor(Color.BLUE);
		g3.drawString("Cursivo", 400,250);
	}
}


//Clase de Imagenes
class Lamina4 extends JPanel
{
	private Image imagen;
	private File miImagen = new File("Images/java.jpg");
	
	public Lamina4(){
			try{
				imagen = ImageIO.read(miImagen);
			}catch(IOException e){
				System.out.println("La imagen de la Lamina4 no se encuentra");
			}
	}


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(imagen,0,0,null); //Nombre de la variable tipo Image, coordenadas donde situarla en la lamina, null para el metodo observador
		//g.copyArea(0,0,imagen.getWidth(this),imagen.getHeight(this), 10, 10); //Copia un rango de la image y lo pega en otra zona de la lamina
	}
}