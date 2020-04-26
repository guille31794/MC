//package com.uca.belousov_zhabotinsky_reaction;

public class ParallelBelZab implements Runnable
{
    private int start, end;

    public ParallelBelZab(int s, int e)
    {
        start = s;
        end = e;
    }

    @Override
    public void run() 
    {
        float ap = 0.0f, bp = 0.0f, cp = 0.0f;

        for(int x = start; x < end; ++x)
            for(int y = 0; y < Reticle.height; ++y)
            {
                float c_a = 0.0f, c_b = 0.0f, c_c = 0.0f;

                for (int i = x - 1; i <= x + 1; ++i)
                    for (int j = y - 1; j <= y + 1; ++j) {
                        c_a += Reticle.a[(i + Reticle.width) % Reticle.width][(j + Reticle.height) % Reticle.height][Reticle.p];
                        c_b += Reticle.b[(i + Reticle.width) % Reticle.width][(j + Reticle.height) % Reticle.height][Reticle.p];
                        c_c += Reticle.c[(i + Reticle.width) % Reticle.width][(j + Reticle.height) % Reticle.height][Reticle.p];
                    }

                c_a /= 9.0f;
                c_b /= 9.0f;
                c_c /=  9.0f;

                Reticle.a[x][y][Reticle.q] = c_a + c_a * (c_b - c_c);
                Reticle.b[x][y][Reticle.q] = c_b + c_b * (c_c - c_a);
                Reticle.a[x][y][Reticle.q] = c_c + c_c * (c_a - c_b);

                if (Reticle.a[x][y][Reticle.q] > 1.0f)
                    Reticle.a[x][y][Reticle.q] = 1.0f;

                if (Reticle.a[x][y][Reticle.q] < 0.0f)
                    Reticle.a[x][y][Reticle.q] = 0.00f;

                if (Reticle.b[x][y][Reticle.q] > 1.0f)
                    Reticle.b[x][y][Reticle.q] = 1.0f;

                if (Reticle.b[x][y][Reticle.q] < 0.0f)
                    Reticle.b[x][y][Reticle.q] = 0.0f;

                if (Reticle.c[x][y][Reticle.q] > 1.0f)
                    Reticle.c[x][y][Reticle.q] = 1.0f;

                if (Reticle.c[x][y][Reticle.q] < 0.0f)
                    Reticle.c[x][y][Reticle.q] = 0.0f;

                ap += Reticle.a[x][y][Reticle.q];
                bp += Reticle.b[x][y][Reticle.q];
                cp += Reticle.c[x][y][Reticle.q];
            }
        
        Reticle.incrementP(ap, bp, cp);
    }
}