/*
* @author Guillermo Girón García
* Clase que implementa el tablero de juego
*/

import java.awt.*;
import javax.swing.JPanel;

public class chessBoard extends JPanel
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public int[][] board;

    public chessBoard(int d)
    {
        board = new int[d][d];
        setSize(board.length, board.length);
        setVisible(true);
        setBounds(0, 0, board.length, board.length);
    }

    public int getLength()
    {
        return board.length;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        
        Graphics2D g2d = (Graphics2D)g;

        g2d.setBackground(Color.BLACK);
        g2d.setColor(Color.GREEN);

        for(int i = 0; i < board.length; ++i)
            for(int j = 0; j < board.length; ++j)
                if(board[i][j] == 1)
                    g2d.fillRect(i, j, 1, 1);
    }
}