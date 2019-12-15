package PacmanF;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Cell extends JLabel
{
	
    Color backgroundColor;
    Border blackBorder=BorderFactory.createLineBorder(Color.BLACK);

    /**
     * Sets the background image 
     * @param backgroundImage
     */
    public void setBackgroundImage(ImageIcon backgroundImage)
    {
        setIcon(backgroundImage);
    }

    /**
     * Sets the background color 
     * @param backgroundColor
     */
    public Cell(Color backgroundColor)
    {
        this.backgroundColor=backgroundColor;
        setBorder(blackBorder);
    }

    /**
     * @param c
     */
    public void setBackgroundColor(Color c) {
        backgroundColor = c;
    }

    /**
     * paints the cells
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        g.setColor(backgroundColor);
        g.fillRect(0,0,getWidth(),getHeight());
        super.paintComponent(g);
    }

}