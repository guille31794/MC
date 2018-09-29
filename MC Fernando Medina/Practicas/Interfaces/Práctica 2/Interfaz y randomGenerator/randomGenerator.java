import java.util.*;
import java.io.*;

public class randomGenerator implements Serializable
{	
	public ArrayList generador1(int variador)
 	{
 		ArrayList<Integer> listaPuntos = new ArrayList<Integer>();
 		int n=30;
 		int m=1; //Semilla
 		int  a=5, b=0;

 		for(int i=0; i<n; i++)
 		{
 			
 			listaPuntos.add(variador + ((a*(m) + b) % (int)Math.pow(2,5)));
 			
 			m = listaPuntos.get(i);
 		}
 		return listaPuntos;
 	}//26_1a

 	public ArrayList generador2(int variador)
 	{
 		ArrayList<Integer> listaPuntos = new ArrayList<Integer>();
 		int  n=30;
 		int  m=1; //Semilla
 		int  a=7, b=0;

 		for(int i=0; i<n; i++)
 		{
 			
 			listaPuntos.add(variador + ((a*(m) + b) % (int)Math.pow(2,5)));
 			
 			m = listaPuntos.get(i);

 		}

 		return listaPuntos;
 	}//26_1b

 	public ArrayList generador3(int variador)
 	{
 		ArrayList<Integer> listaPuntos = new ArrayList<Integer>();
 		int n=30;
 		int m=1; //Semilla
 		int a=3, b=0;

 		for(int i=0; i<n; i++)
 		{
	
 			listaPuntos.add(variador +((a*(m) + b) % 31));
 			
 			m = listaPuntos.get(i);

 		}

 		return listaPuntos;
 	}//26_2

  	public ArrayList generador4(int variador)
 	{
 		ArrayList<Integer> listaPuntos = new ArrayList<Integer>();
 			 int n=30;
 			 int m=1; //Semilla
 		 	 int a=(int)Math.pow(7,5), b=0;
 			 int x;

 		for(int i=0; i<n; i++)
 		{
 			
 			listaPuntos.add(((a*(m) + b) % ((int)Math.pow(2,31) - 1))/variador);
 			m = listaPuntos.get(i);

 		}

 		return listaPuntos;
 	}//26_3

 	public ArrayList generadorCombinado(int variador)
 	{
 		ArrayList<Integer> listaPuntos = new ArrayList<Integer>();

 		int  n=30, resultado;
 		int m1 = 6, m2=6 ;// m es la semilla
 		int a=5, b=0;
 		int x,y,z;

 		for(int i=0; i<n; i++)
 		{
 			
 			x = (40014*(m1) + b) % (2147483563);
 			y = (40692*(m2) + b) % (2147483399);
 			listaPuntos.add((Math.abs((x-y) % (2147483562)))/variador);
 			m1 = x;
 			m2 = y;
 		}

 		return listaPuntos;


 	}//GeneradorCombinado

 	public ArrayList FishmanYMoore1(int variador)
 	{
 		ArrayList<Integer> listaPuntos = new ArrayList<Integer>();
 		int n=30;
 		int m=1; //Semilla
 		int a=48271, b=0;
 		

 		for(int i=0; i<n; i++)
 		{
 			
 			listaPuntos.add(((a*(m) + b) % ((int)Math.pow(2,31) - 1))/variador);

 			m = listaPuntos.get(i);

 		}

 		return listaPuntos;
 	}//FishmanYMoore1

 	public ArrayList FishmanYMoore2(int variador)
 	{
 		ArrayList<Integer> listaPuntos = new ArrayList<Integer>();
 		int n=30;
 		int m=1; //Semilla
 		int a=69621, b=0;
 		

 		for(int i=0; i<n; i++)
 		{
 			
 			listaPuntos.add(((a*(m) + b) % ((int)Math.pow(2,31) - 1))/variador);
 			m = listaPuntos.get(i);

 		}

 		return listaPuntos;
 	}//FishmanYMoore2

 	public ArrayList RANDU(int variador)
 	{
 		ArrayList<Integer> listaPuntos = new ArrayList<Integer>();
 		int n=30;
 		int m=1; //Semilla
 		int a=(int)Math.pow(2,16), b=3;


 		for(int i=0; i<n; i++)
 		{
 			listaPuntos.add(((a*(m) + b) % (int)Math.pow(2,31))/variador);
 			m = listaPuntos.get(i);
 		}

 		return listaPuntos;
 	}//RANDU
}
