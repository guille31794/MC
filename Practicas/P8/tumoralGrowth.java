import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;
import javax.swing.JPanel;

public class tumoralGrowth extends JPanel
{
    private final byte[][][] reticle;
    private final int Ps; 
    private final int NP; 
    private int PH; 
    private final int size; 
    private int p;
    private float population;
    private final float Pp;
    private final float Pm;
    private final Random rr;
    private boolean flag;
    
    public tumoralGrowth(int np, float pm)
    {
        reticle = new byte[1000][1000][2];
        size = 1000;
        NP = np;
        Pm = pm;
        Ps = 1;
        Pp = 0.25f;
        PH = 0;
        rr = new Random();
        p = 0;
        flag = false;

        reticle[size/2][size/2][p] = 1;
        reticle[size / 2 - 2][size / 2 - 2][p] = 1;
        reticle[size / 2 - 1][size / 2 - 1][p] = 1;
        reticle[size / 2 + 1][size / 2][p] = 1;
        reticle[size / 2][size / 2 + 1][p] = 1;
        
        setVisible(true);
        setBounds(0,0, size, size);
    }
    
    public float density()
    {
        return (population / (size*size))*100;
    }

    public void growth()
    {
        for(int x = 0; x < size; ++x)
            for(int y = 0; y < size; ++y)
                if(reticle[x][y][p] == 1)
                    // Compute if the cell survives
                    if(rr.nextFloat() < Ps)
                    {
                        //Compute if cell proliferate
                        if(!proliferation(x,y))
                            //Compute if cell migrate
                            migration(x, y);
                        
                        reticle[x][y][(p+1)%2] = reticle[x][y][p];
                    }
                    else
                        reticle[x][y][(p+1)%2] = 0;
    }

    private boolean proliferation(int x, int y)
    {
        boolean result = false;
        float rrp = rr.nextFloat();
 
        if(rrp < Pp)
            if(++PH == NP)
                result = proliferationProbabilities(x, y, rrp);
                      
        return result;
    }

    private boolean proliferationProbabilities(int x, int y, float rrp)
    {
        boolean result = false;
        int sum = 0;

        if(x-1 > 0)
            sum += reticle[x-1][y][p];
        if (x+1 < size)
            sum += reticle[x+1][y][p];
        if (y-1 > 0)
            sum += reticle[x][y-1][p];
        if (y+1 < size)
            sum += reticle[x][y+1][p];

        float p1 = 0, p2 = 0, p3 = 0, p4 = 0;

        if(sum < 4)
        {
            if(x-1 > 0 && reticle[x-1][y][p] == 0 )
                p1 = 1.0f / (4.0f - sum);

            if (x+1 < size && reticle[x+1][y][p] == 0)
                p2 = 1.0f / (4.0f - sum);

            if (y-1 > 0 && reticle[x][y-1][p] == 0)
                p3 = 1.0f / (4.0f - sum);

            if (y+1 < size && reticle[x][y+1][p] == 0)
                p4 = 1.0f / (4.0f - sum);
        }

        if(rrp <= p1)
        {
            reticle[x-1][y][(p+1)%2] = 1;
            result = true;
        }
        else if(rrp <= p1 + p2)
        {
            reticle[x+1][y][(p+1)%2] = 1;
            result = true;
        }
        else if(rrp <= p1 + p2 + p3)
        {
            reticle[x][y-1][(p+1)%2] = 1;
            result = true;
        }
        else if(rrp <= p1 + p2 + p3 + p4)
        {
            reticle[x][y+1][(p+1)%2] = 1;
            result = true;
        }
        
        return result;
    }

    private void migration(int x, int y)
    {
        float rrm = rr.nextFloat();

        if(rrm < Pm)
        {
            int sum = 0;

            if (x - 1 > 0)
                sum += reticle[x - 1][y][p];
            if (x + 1 < size)
                sum += reticle[x + 1][y][p];
            if (y - 1 > 0)
                sum += reticle[x][y - 1][p];
            if (y + 1 < size)
                sum += reticle[x][y + 1][p];

            float p1 = 0, p2 = 0, p3 = 0, p4 = 0;

            if (sum < 4) {
                if (x - 1 > 0 && reticle[x - 1][y][p] == 0)
                    p1 = 1.0f / (4.0f - sum);

                if (x + 1 < size && reticle[x + 1][y][p] == 0)
                    p2 = 1.0f / (4.0f - sum);

                if (y - 1 > 0 && reticle[x][y - 1][p] == 0)
                    p3 = 1.0f / (4.0f - sum);

                if (y + 1 < size && reticle[x][y + 1][p] == 0)
                    p4 = 1.0f / (4.0f - sum);
            }

            if (rrm <= p1)
            {
                reticle[x - 1][y][(p+1)%2] = 1;
                reticle[x][y][(p+1)%2] = 0;
            }
            else if (rrm <= p1 + p2)
            {
                reticle[x + 1][y][(p+1)%2] = 1;
                reticle[x][y][(p+1)%2] = 0;
            }
            else if (rrm <= p1 + p2 + p3)
            {
                reticle[x][y - 1][(p+1)%2] = 1;
                reticle[x][y][(p+1)%2] = 0;
            }
            else if(rrm <= p1 + p2 + p3 + p4)
            {
                reticle[x][y + 1][(p+1)%2] = 1;
                reticle[x][y][(p+1)%2] = 0;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        population = 0;
        g.setColor(Color.BLACK);
        int other = 0;

        for(int x = 0; x < size; ++x)
            for(int y = 0; y < size; ++y)
            {
                if(reticle[x][y][p] == 1)
                {
                    g.fillRect(x, y, 1, 1);
                    ++population;
                }
                
                if(reticle[x][y][(p+1)%2] == 1)
                    ++other;
            }
                
        population = Math.max(other, population);
        
        if(flag)
            p = (p+1)%2;
        
        flag = true;
        
        
    }
}