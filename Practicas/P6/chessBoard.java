/*
* @author Guillermo Girón García
* Clase que implementa el tablero de juego
*/

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JPanel;

public class chessBoard extends JPanel
{
    private static final long serialVersionUID = 1L;
    public int[][] board;
    private AtomicInteger population;
    private int cannonNumber;

    public chessBoard(int d)
    {
        board = new int[d][d];
        population = new AtomicInteger(0);
        setSize(board.length, board.length);
        setVisible(true);
        setBounds(0, 0, board.length, board.length);
        cannonNumber = 0;
    }

    public int getLength()
    {
        return board.length;
    }

    public int getPopulation()
    {
        return population.get();
    }

    public void incrementPopulation()
    {
        population.incrementAndGet();
    }

    public void resetPopulation()
    {
        population.set(0);
    }

    public int getCannonNumber()
    {
        return cannonNumber;
    }

    public void incrementCannonNumber()
    {
        ++cannonNumber;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        
        Graphics2D g2d = (Graphics2D)g;
        
        for(int i = 0; i < board.length; ++i)
            for(int j = 0; j < board.length; ++j)
                if(board[i][j] == 1)
                {
                    g2d.setColor(Color.GREEN);
                    g2d.fillRect(i, j, 1, 1);
                }
                else if(board[i][j] == 2)
                {
                    g2d.setColor(Color.RED);
                    g2d.fillRect(i, j, 1, 1);
                }
    }
}