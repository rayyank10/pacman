package PacmanF;
import java.util.ArrayList;

/**
 * A class that tracks candy positions.
 */
public class Candies extends ArrayList<Integer> {

    /**
     * Constructs a list of candy positions. If
     * the position contains 1, it has a candy. Otherwise
     * the position contains 0, indicating no candy.
     */
    public Candies() {
        for (int i=0; i<Board.NUM_BLOCKS; i++) {
            for (int j=0; j<Board.NUM_BLOCKS; j++) {
                add(1);
            }
        }
    }

    /**
     * Gets the current amount of candies eaten.
     * @return the number of candies eaten.
     */
    public int getScore() {
        int candyCounter = 0;
        for (int i=0; i<size(); i++) {
            if (isEmpty(i)) {
                candyCounter++;
            }
        }
        return candyCounter;
    }

    /**
     * Resets all eaten candies to uneaten.
     */
    public void reset() {
        clear();
        for (int i=0; i<Board.NUM_BLOCKS; i++) {
            for (int j=0; j<Board.NUM_BLOCKS; j++) {
                add(1);
            }
        }
    }
    
    /**
     * Checks if candy position has candy or not.
     * @param pos the position to check for candy.
     * @return A binary number, 1 if candy exists or 0 if not.
     * @precondition pos < (Board.NUM_BLOCKS * Board.NUM_BLOCKS) 
     */
    public boolean isEmpty(int pos) {
        assert pos < Board.NUM_BLOCKS * Board.NUM_BLOCKS : "Board position doesn't exist";
        if (get(pos) == 0) {
            return true;
        }
        return false;
    }

}