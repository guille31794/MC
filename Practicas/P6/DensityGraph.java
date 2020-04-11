/*
* @author Guillermo Girón García
* Clase que implementa la gráfica
* de densidad poblacional
*/

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ListIterator;
import java.util.LinkedList;

public class DensityGraph extends JPanel
{
    private static final long serialVersionUID = 1L;
    private LinkedList<Integer> L;
    private final int width, heigth;

    public DensityGraph(int x, int y, int w, int h)
    {
        setBounds(x, y, w, h);
        L = new LinkedList<Integer>();
        width = w;
        heigth = h;
    }

    public void addGenerationDensity(int density)
    {
        if(L.size() != width)
            L.addLast(density);
        else
        {
            L.removeFirst();
            L.addLast(density);
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int x = 0;
        ListIterator<Integer> it = L.listIterator(1);
        int prev = it.previous(), next = it.next();
        while(it.hasNext())
        {
            g2d.drawLine(x, heigth-prev, x + 1, heigth-next);
            ++x;
            prev = next;
            next = it.next();
        }
    }
}