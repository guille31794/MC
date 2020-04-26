//package com.uca.belousov_zhabotinsky_reaction;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.ListIterator;
import java.awt.Color;

public class plotCurve extends JPanel
{
    private LinkedList<Integer> La, Lb, Lc;
    private int width, heigth;

    public plotCurve()
    {
        this.width = 645;
        heigth = 100;
        setSize(width, heigth);
        setVisible(true);
        La = new LinkedList<Integer>();
        Lb = new LinkedList<Integer>();
        Lc = new LinkedList<Integer>();
    }

    public void addGenerationDensity(int Adensity, int Bdensity, int Cdensity) 
    {
        if (La.size() != width)
            La.addLast(Adensity);
        else 
        {
            La.removeFirst();
            La.addLast(Adensity);
        }

        if (Lb.size() != width)
            Lb.addLast(Bdensity);
        else {
            Lb.removeFirst();
            Lb.addLast(Bdensity);
        }

        if (Lc.size() != width)
            Lc.addLast(Cdensity);
        else {
            Lc.removeFirst();
            Lc.addLast(Cdensity);
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int frame = width / La.size(), x = frame;
        ListIterator<Integer> it = La.listIterator(1);
        int prev = it.previous(), next = it.next();
        
        g2d.setColor(Color.BLUE);
        while (it.hasNext()) 
        {
            g2d.drawLine(x, heigth - prev, x + 1, heigth - next);
            x += frame;
            prev = next;
            next = it.next();
        }
        
        x = 0;
        it = Lb.listIterator(1);
        prev = it.previous(); 
        next = it.next();

        g2d.setColor(Color.GREEN);
        while (it.hasNext()) {
            g2d.drawLine(x, heigth - prev, x + 1, heigth - next);
            x += frame;
            prev = next;
            next = it.next();
        }

        x = 0;
        it = Lc.listIterator(1);
        prev = it.previous(); 
        next = it.next();

        g2d.setColor(Color.GRAY);
        while (it.hasNext()) {
            g2d.drawLine(x, heigth - prev, x + 1, heigth - next);
            x += frame;
            prev = next;
            next = it.next();
        }
    }
}