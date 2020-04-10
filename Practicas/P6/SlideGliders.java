/*
* @author Guillermo Girón García
* Clase que implementa el avance
* generacinal para las inicializaciones
* con cañones de planeadores
*/

public class SlideGliders implements Runnable
{
    private final int start, end, offset;
    private chessBoard board;

    public SlideGliders(int s, int e, chessBoard c, int o)
    {
        start = s;
        end = e;
        board = c;
        offset = o;
    }

    @Override
    public void run() 
    {
        for(int i = end-1; i >= start; --i)
            for(int j = board.getLength()-1; j >= 0; --j)
                if(board.board[i][j] != 2)
                    Slide(i, j);    
    }

    protected void Slide(int x, int y)
    {   
        if(x - offset >= 0 && y - offset >= 0 && board.board[x-offset][y-offset] != 2)
            board.board[x][y] = board.board[x - offset][y - offset];
        else 
            board.board[x][y] = 0;
    }   
}