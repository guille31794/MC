//package com.uca.belousov_zhabotinsky_reaction;

import java.util.Random;

public class belZab 
{
    public static void setup()
    {
        Random r = new Random();

        for (int i = 0; i < Reticle.width; ++i)
            for (int j = 0; j < Reticle.height; ++j) {
                Reticle.a[i][j][Reticle.p] = r.nextFloat();
                Reticle.b[i][j][Reticle.p] = r.nextFloat();
                Reticle.c[i][j][Reticle.p] = r.nextFloat();
            }
    }

    public static void reaction()
    {
        int w = Reticle.width, h = Reticle.height;

        for(int x = 0; x < w; ++x)
            for(int y = 0; y < h; ++y)
            {
                float c_a = 0.0f, c_b = 0.0f, c_c = 0.0f;

                for(int i = x - 1; i <= x +1; ++i)
                    for(int j = y - 1; j <= y + 1; ++j)
                    {
                        c_a = c_a + Reticle.a[(i + w) % w][(j + h) % h][Reticle.p];
                        c_b = c_b + Reticle.b[(i + w) % w][(j + h) % h][Reticle.p];
                        c_c = c_c + Reticle.c[(i + w) % w][(j + h) % h][Reticle.p];
                    }

                c_a = c_a / 9.0f;
                c_b =  c_b / 9.0f;
                c_c = c_c / 9.0f;

                Reticle.a[x][y][Reticle.q] = c_a + c_a * (c_b - c_c);
                Reticle.b[x][y][Reticle.q] = c_b + c_b * (c_c - c_a);
                Reticle.a[x][y][Reticle.q] = c_c + c_c * (c_a - c_b);

                if(Reticle.a[x][y][Reticle.q] > 1.0f)
                    Reticle.a[x][y][Reticle.q] = 1.0f;
                
                if(Reticle.a[x][y][Reticle.q] < 0.0f)
                    Reticle.a[x][y][Reticle.q] = 0.00f;

                if (Reticle.b[x][y][Reticle.q] > 1.0f)
                    Reticle.b[x][y][Reticle.q] = 1.0f;

                if (Reticle.b[x][y][Reticle.q] < 0.0f)
                    Reticle.b[x][y][Reticle.q] = 0.0f;

                if (Reticle.c[x][y][Reticle.q] > 1.0f)
                    Reticle.c[x][y][Reticle.q] = 1.0f;

                if (Reticle.c[x][y][Reticle.q] < 0.0f)
                    Reticle.c[x][y][Reticle.q] = 0.0f;
            }
    }
}