/*
* @author Guillermo Girón García
* Clase que implementa el juego de la vida
*/

import java.util.Random;

public class LifeGame implements Runnable
{
    private int start, end, option;
    private chessBoard board;
    private Random r;

    public LifeGame(int s, int e, chessBoard c, int option)
    {
        start = s;
        end = e;
        board = c;
        r = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() 
    {
        for(int i = start; i < end; ++i)
            for(int j = 0; j < board.getLength(); ++j)
                board.board[i][j] = r.nextInt(2);
    }
}