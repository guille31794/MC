/*
* @author Guillermo Girón García
* Clase que implementa el avance
* generacinal para las inicializaciones
* aleatorias y con islas
*/

public class NextGenRandom_Island implements Runnable
{
    private final int start, end;
    private chessBoard board;

    public NextGenRandom_Island(int s, int e, chessBoard c)
    {
        start = s;
        end = e;
        board = c;
    }

    @Override
    public void run() 
    {
        for(int i = start; i < end; ++i)
            for(int j = 0; j < board.getLength(); ++j)
                    modifyState(i, j);
    }

    public void modifyState(int x, int y)
    {
        
    }
}