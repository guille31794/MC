/*
* @author Guillermo Girón García
* Clase que cuenta la población del
* tablero en la modalidad con cañones
*/


public class countCells implements Runnable
{
    private final chessBoard board;
    private final int start, end;

    public countCells(chessBoard c, int s, int e)
    {
        board = c;
        start = s;
        end = e;
    }

    @Override
    public void run() 
    {
        for (int i = start; i < end; ++i)
            for (int j = 0; j < board.getLength(); ++j)
                if (board.board[i][j] == 1 || board.board[i][j] == 2)
                    board.incrementPopulation();
    }
}