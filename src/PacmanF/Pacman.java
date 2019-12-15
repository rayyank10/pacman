package PacmanF;

import java.awt.Color;
import java.awt.Graphics;

public class Pacman extends Cell {

    /**
     * 
     */
    public Pacman() {
        super(Color.YELLOW);
    }

    /**
     * draws pacman
     *@param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.fillRect(0,0,getWidth(),getHeight());
        super.paintComponent(g);
    }


}