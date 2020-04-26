//package com.uca.belousov_zhabotinsky_reaction;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Reticle extends JPanel implements Runnable
{
    public static float[][][] a, b, c;
    public static int width, height, p, q;
    private int start, end;
    private static float ap, bp, cp;

    // Constructor for concurrency
    public Reticle(int s, int e)
    {
        start = s;
        end = e;
    }

    // Constructor for painting
    public Reticle(int w, int h, boolean bo)
    {
        start = 0;
        end = 0;
        width = w;
        height = h;
        p = 0;
        q = 1;
        a = new float[width][height][2];
        b = new float[width][height][2];
        c = new float[width][height][2];
        setSize(width*4, height*4);
        setVisible(true);
        setBounds(0, 0, width*4, height*4);
    }

    @Override
    public void run() 
    {
        Random r = new Random();
        float a_p = 0.0f, b_p = 0.0f, c_p = 0.0f;

        for(int i = start; i < end; ++i)
            for(int j = 0; j < height; ++j)
            {
                a_p += a[i][j][p] = r.nextFloat();
                b_p += b[i][j][p] = r.nextFloat();
                c_p += c[i][j][p] = r.nextFloat();
                incrementP(a_p, b_p, c_p);
            }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        for(int x = 0; x < width; ++x)
            for(int y = 0; y < height; ++y)
            {
                Color co = new Color(a[x][y][q], b[x][y][q], c[x][y][q]);
                g2d.setColor(co);
                //g2d.setColor(Color.getHSBColor(a[x][y][q], b[x][y][q], c[x][y][q]));
                g2d.fillRect(x, y, 1, 1);
            }

        if (Reticle.p == 0) 
        {
            Reticle.p = 1;
            Reticle.q = 0;
        } else 
        {
            Reticle.p = 0;
            Reticle.q = 1;
        }
    }

    synchronized public static void incrementP(float a, float b, float c)
    {
        ap += a;
        bp += b;
        cp += c;
    }

    public static float aPercentage()
    {
        return ((ap / (width * height)) * 100);
    }

    public static float bPercentage() 
    {
        return ((bp / (width * height)) * 100);
    }
    
    public static float cPercentage() 
    {
        return ((cp / (width * height)) * 100);
    }

    public static void resetPercentage()
    {
        ap = 0.0f;
        bp = 0.0f;
        cp = 0.0f;
    }
}