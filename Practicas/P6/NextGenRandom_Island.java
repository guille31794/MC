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
    private boolean neightborCondition;

    public NextGenRandom_Island(int s, int e, chessBoard c, int n)
    {
        start = s;
        end = e;
        board = c;
        neightborCondition = n > 0 ? true : false;
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
        int alive = 0;

        if(neightborCondition)
        {
            if(x - 1 >= 0 && board.board[x-1][y] == 1)
                alive++;

            if(x + 1 < board.getLength() && board.board[x+1][y] == 1)
                alive++;

            if(y - 1 >= 0 && board.board[x][y-1] == 1)
                alive++;

            if(y + 1 < board.getLength() && board.board[x][y+1] == 1)
                alive++;
        }
        else
            for(int i = x - 1; i <= x + 1; ++i)
                for(int j = y - 1; j <= y + 1; ++j)
                    if(i >= 0 && j >= 0 && i < board.getLength() && j < board.getLength())
                        if(board.board[i][j] == 1)
                            ++alive;
        
        if(board.board[x][y] == 1)
        {
            if(alive < 2)
                board.board[x][y] = 0;
            else if(alive > 3)  
                board.board[x][y] = 0;
        }
        else if(alive == 3)
            board.board[x][y] = 1;

        if(board.board[x][y] == 1)
            board.incrementPopulation();
    }
}