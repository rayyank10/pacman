package PacmanF;

import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

/**
 * @author user
 *
 */
public class Ghost extends Cell {

    public static Random r = new Random();

    /**
     * picks the color of the ghost
     * @param c
     */
    public Ghost(Color c) {
        super(c);
    }

    /**
     * paints ghost
     *@param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.fillRect(0,0,getWidth(),getHeight());
        super.paintComponent(g);
    }

    /**
     * picks a random number between 1 and 4 for ghost movement
     * @return
     */
    public int chooseMove() {
        return r.nextInt(4);
    }
}