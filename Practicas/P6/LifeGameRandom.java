/*
* @author Guillermo Girón García
* Clase que implementa la 
* inicialización aleatoria del juego de la vida
*/

import java.util.Random;

public class LifeGameRandom implements Runnable
{
    private int start, end, option;
    private chessBoard board;
    private Random r;

    public LifeGameRandom(int s, int e, chessBoard c)
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