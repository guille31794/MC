/*
* @author Guillermo Girón García
* Clase que implementa la 
* inicialización por Cañones de planeadores
*/

import java.util.Random;

public class LifeGameCannon implements Runnable
{
    private final int start, end, totalCannons;
    private int cannonCounter;
    private final Random r;
    private chessBoard board;
    private final int[][][] figures = 
    {
        {
            {0, 0, 0, 0, 2},
            {0, 2, 2, 2, 2},
            {2, 2, 2, 2, 0},
            {2, 0, 0, 2, 0},
            {2, 2, 2, 2, 0},
            {0, 2, 2, 2, 2},
            {0, 0, 0, 0, 2}
        },
        {
            {2, 2, 0, 0, 0},
            {2, 0, 2, 0, 0},
            {0, 2, 2, 2, 0},
            {0, 0, 2, 2, 2},
            {0, 2, 2, 2, 0},
            {2, 0, 2, 0, 0},
            {2, 2, 0, 0, 0}
        }
    };

    public LifeGameCannon(int s, int e, chessBoard c, int cannons)
    {
        start = s;
        end = e;
        board = c;
        totalCannons = cannons;
        cannonCounter = 0;
        r = new Random();
    }

    @Override
    public void run() 
    {
        for(int i = 0; i < totalCannons; ++i)
        {
            int x = r.nextInt(end) + (end - start),
            y = r.nextInt(board.getLength()),
            f = r.nextInt(figures.length);
            InsertCannon(f, x, y);
        }
    }

    public void InsertCannon(int f, int x, int y)
    {
        for(int i = 0; i < figures[f].length && x + i < board.getLength(); ++i)
            for(int j = 0; j < figures[f][i].length && y + j < board.getLength(); ++j)
                if(board.board[i][j] != 2)
                    board.board[x+i][y+j] = figures[f][i][j];
    }
}