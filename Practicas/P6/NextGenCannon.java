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
    private final boolean neightborCondition;
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

    public NextGenCannon(int s, int e, chessBoard c, int n)
    {
        start = s;
        end = e;
        neightborCondition = n > 0 ? true : false;
        board = c;
        r = new Random();
    }

    @Override
    public void run() 
    {
        for(int i = start; i < end; ++i)
            for(int j = 0; j < board.getLength(); ++j)
                if(board.board[i][j] == 2)
                {
                    shootGlider(i, j);
                    i += 7;
                    break;
                }
    }

    public void shootGlider(int x, int y)
    {
        
    }
}