import java.util.*;
import java.io.*;

public class randomGenerator implements Serializable
{	
	int n=0;

	public randomGenerator(int nColumnas)
	{
		n = 1605;
	}
	public ArrayList generador1(int ST)
 	{
 		ArrayList<Integer> listaPuntos = new ArrayList<Integer>();
 		
 		int m=1; //Semilla
 		int  a=5, b=0;
 		int resultado;
 		for(int i=0; i<n; i++)
 		{
 			
 			resultado = ((a*(m) + b) % (int)Math.pow(2,5));
 			
 			m = resultado;
 			

 			listaPuntos.add(Math.abs(resultado%ST));

 		}
 		
 		return listaPuntos;
 	}//26_1a

 	public ArrayList generador2(int ST)
 	{
 		ArrayList<Integer> listaPuntos = new ArrayList<Integer>();
 		
 		int  m=1; //Semilla
 		int  a=7, b=0;
 		int resultado;
 		for(int i=0; i<n; i++)
 		{
 			
 			resultado = ((a*(m) + b) % (int)Math.pow(2,5));
 			
 			m = resultado;
 		

			listaPuntos.add(Math.abs(resultado%ST));
 		}
 		
 		return listaPuntos;
 	}//26_1b

 	public ArrayList generador3(int ST)
 	{
 		ArrayList<Integer> listaPuntos = new ArrayList<Integer>();
 		int resultado;
 		int m=1; //Semilla
 		int a=3, b=0;

 		for(int i=0; i<n; i++)
 		{
	
 			resultado = ((a*(m) + b) % 31);
 			
 			m = resultado;
 		

 			listaPuntos.add(Math.abs(resultado%ST));
 		}
 		
 		return listaPuntos;
 	}//26_2

  	public ArrayList generador4(int ST)
 	{
 		ArrayList<Integer> listaPuntos = new ArrayList<Integer>();
 			
 			 int m=1; //Semilla
 		 	 int a=(int)Math.pow(7,5), b=0;
 			 int x;
 			 int resultado;

 		for(int i=0; i<n; i++)
 		{
 			
 			resultado = (((a*(m) + b) % ((int)Math.pow(2,31) - 1)));
 			m = resultado;
 		


 			listaPuntos.add(Math.abs(resultado%ST));
 		}
		
 		return listaPuntos;
 	}//26_3

 	public ArrayList generadorCombinado(int ST)
 	{
 		ArrayList<Integer> listaPuntos = new ArrayList<Integer>();

 		int resultado;
 		int m1 = 6, m2=6 ;// m es la semilla
 		int a=5, b=0;
 		int x,y,z;

 		for(int i=0; i<n; i++)
 		{
 			
 			x = (40014*(m1) + b) % (2147483563);
 			y = (40692*(m2) + b) % (2147483399);
 			resultado = ((Math.abs((x-y) % (2147483562))));
 			m1 = x;
 			m2 = y;

  		

  			listaPuntos.add(Math.abs(resultado%ST));
 		}
 	
 		return listaPuntos;


 	}//GeneradorCombinado

 	public ArrayList FishmanYMoore1(int ST)
 	{
 		ArrayList<Integer> listaPuntos = new ArrayList<Integer>();
 		
 		int m=1; //Semilla
 		int a=48271, b=0;
 		int resultado;
 		

 		for(int i=0; i<n; i++)
 		{
 			
 			resultado = (((a*(m) + b) % ((int)Math.pow(2,31) - 1)));

 			m = resultado;

  	
 			listaPuntos.add(Math.abs(resultado%ST));

 		}
 	
 		return listaPuntos;
 	}//FishmanYMoore1

 	public ArrayList FishmanYMoore2(int ST)
 	{
 		ArrayList<Integer> listaPuntos = new ArrayList<Integer>();
 		int  resultado;
 		int m=1; //Semilla
 		int a=69621, b=0;
 		

 		for(int i=0; i<n; i++)
 		{
 			
 			resultado = (((a*(m) + b) % ((int)Math.pow(2,31) - 1)));
 			m = resultado;

 			
 			listaPuntos.add(Math.abs(resultado%ST));
 		}
 		
 		return listaPuntos;
 	}//FishmanYMoore2

 	public ArrayList RANDU(int ST)
 	{
 		ArrayList<Integer> listaPuntos = new ArrayList<Integer>();
 		int  resultado;
 		int m=1; //Semilla
 		int a=(int)Math.pow(2,16), b=3;


 		for(int i=0; i<n; i++)
 		{
 			resultado = (((a + b)*m % (int)Math.pow(2,31)));
 			m = resultado;

 		
 			listaPuntos.add(Math.abs(resultado%ST));
 		}
 	
 		return listaPuntos;
 	}//RANDU
}
