/*
* @author Guillermo Girón García
* Clase que implementa el avance
* generacinal para las inicializaciones
* con cañones de planeadores
*/

import java.util.Random;

public class NextGenCannon implements Runnable
{
    private final int start, end;
    private chessBoard board;
    private final Random r;
    private final int[][][] gliders =  
    {
        {
            {0, 1, 0},
            {0, 0, 1},
            {1, 1, 1}
        },
        {
            {1, 0, 0},
            {0, 1, 1},
            {1, 1, 0}
        }
    };

    public NextGenCannon(int s, int e, chessBoard c)
    {
        start = s;
        end = e;
        board = c;
        r = new Random();
    }

    @Override
    public void run() 
    {
        int x = 0, y = 0, g = 0;
        for(int i = start; i < end; ++i)
        {
            x = r.nextInt(board.getLength());
            y = r.nextInt(board.getLength());
            g = r.nextInt(gliders.length);
            shootGlider(g, x, y);
        }
    }

    public void shootGlider(int g, int x, int y)
    {
        for(int i = 0; i < gliders[g].length && x + i < board.getLength(); ++i)
            for(int j = 0; j < gliders[g].length && y + j < board.getLength(); ++j)
                if(board.board[x+i][y+j] != 2 && board.board[x + i][y + j] != 1)
                    board.board[x+i][y+j] = gliders[g][i][j];    
    }
}