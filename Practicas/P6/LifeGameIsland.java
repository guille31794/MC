/*
* @author Guillermo Girón García
* Clase que implementa la 
* inicialización por islas del juego de la vida
*/

import java.util.Random;

public class LifeGameIsland implements Runnable
{
    private int start, end, distance, counter;
    private chessBoard board;
    private Random r;
    private boolean flag;
    private static int[][][] islands = 
    {
        {
            {0, 0, 1, 0, 0}, 
            {0, 1, 0, 1, 0}, 
            {0, 1, 0, 1, 0}, 
            {0, 1, 0, 1, 0}, 
            {0, 0, 1, 0, 0}
        },
        {
            {0, 0, 1, 0, 0}, 
            {0, 0, 1, 0, 0}, 
            {1, 1, 1, 1, 1}, 
            {0, 0, 1, 0, 0}, 
            {0, 0, 1, 0, 0}
        },
        {
            {1, 1, 1, 1, 1},
            {1, 1, 0, 0, 1},
            {1, 0, 1, 0, 1},
            {1, 0, 0, 1, 1},
            {1, 1, 1, 1, 1}
        },
    };

    public LifeGameIsland(int s, int e, chessBoard c, int d)
    {
        start = s;
        end = e;
        board = c;
        r = new Random(System.currentTimeMillis());
        distance = d;
        flag = true;
        counter = 0;
    }

    @Override
    public void run() 
    {
        for (int i = start; i < end; ++i)
        {
            for(int j = 0; j < board.getLength(); ++j)
            {
                if(distance < counter)
                {
                    counter = 0;
                    insertFigure(r.nextInt(3), i, j);
                }

                counter++;
            } 
            
            ++counter;
        }
    }

    public void insertFigure(int f, int x, int y)
    {
        for(int i = 0; i < islands[f].length && x + i < board.getLength(); ++i)
            for(int j = 0; j < islands[f].length && y + j < board.getLength(); ++j)
                if(board.board[i][j] != 1)
                    board.board[x+i][y+j] = islands[f][i][j];
    }
}