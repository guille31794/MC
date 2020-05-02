
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author guillermogirongarcia
 */
public class Curve extends JPanel
{
    private final LinkedList<Integer> L;
    
    public Curve()
    {
        setVisible(true);
        setBounds(0, 0, 300, 300);
        L = new LinkedList<>();
    }
    
    public void addDensity(int d)
    {
        if(L.size() == 300)
        {
            L.removeFirst();
            L.addLast(d);
        }
        else
            L.addLast(d);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        int frame = 300 / L.size(), x = 0;
        ListIterator<Integer> it = L.listIterator(1);
        int prev = it.previous(), next = it.next();
        g2d.setColor(Color.BLUE);
        
        while (it.hasNext()) 
        {
            g2d.drawLine(x, 300-prev, x + 1, 300-next);
            x++;
            prev = next;
            next = it.next();
        }
    }
}
