package PacmanF;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Graphics;

/**
 * A class for producing a Cell with a color.
 */
public class Cell extends JLabel
{
    /**
     * Constructs a Cell with a given color.
     * @param backgroundColor the background color of the cell.
     */
    public Cell(Color backgroundColor)
    {
        this.backgroundColor=backgroundColor;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * Sets the color to the background color.
     * @param g the cell to paint.
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        g.setColor(backgroundColor);
        g.fillRect(0,0,getWidth(),getHeight());
        super.paintComponent(g);
    }

    private Color backgroundColor;
}