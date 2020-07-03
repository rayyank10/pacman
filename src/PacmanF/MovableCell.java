package PacmanF;

import java.awt.Color;
import java.util.Random;

/**
 * A class for cells that can move positions.
 */
public class MovableCell extends Cell {

    public static Random r = new Random();

    /**
     * Constructs a Movable cell that can move
     * to a position and keep track of it.
     * @param c the color of the cell.
     * @param x the cell's row position.
     * @param y the cell's column position.
     * @precondition x < Board.NUM_BLOCKS:
     * @precondition y < Board.NUM_BLOCKS;
     */
    public MovableCell(Color c,int x, int y) {
        super(c);
        assert x < Board.NUM_BLOCKS : "Board row position is invalid";
        assert y < Board.NUM_BLOCKS : "Board column position is invalid";
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the cell's row position.
     * @return the row.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the cell's column position.
     * @return the column.
     */
    public int getY() {
        return y;
    }

    /**
     * Moves the cell to a position.
     * @param x the row.
     * @param y the column.
     * @precondition x >= 0 && row < Board.NUM_BLOCKS
     * @precondition y >=  0 && col < Board.NUM_BLOCKS
     */
    public void move(int x, int y) {
        assert x >= 0 && x < Board.NUM_BLOCKS : "Board row position doesn't exist.";
        assert y >= 0 && y < Board.NUM_BLOCKS : "Board column position doesn't exist.";
        this.x = x;
        this.y = y;
    }

    /**
     * Randomly gets position left or right of x.
     * @return the left or right position.
     */
    public int getRandX() {
        if (r.nextBoolean()) {
            return x+1;
        }
        else {
            return x-1;
        }
    }

    /**
     * Randomly gets a position above or below y.
     * @return the top or bottom position.
     */
    public int getRandY() {
        if (r.nextBoolean()) {
            return y+1;
        }
        else {
            return y-1;
        }
    }

    private int x, y;
}