package PacmanF;

import java.util.ArrayList;

public class Candies extends ArrayList<Integer> {

    /**
     * Places candies on all the cells
     */
    public Candies() {
        for (int i=0; i<Board.NUM_BLOCKS; i++) {
            for (int j=0; j<Board.NUM_BLOCKS; j++) {
                add(1);
            }
        }
    }

    /**
     *  Resets the candies after the game is ended
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
     * Gets the score for the player
     * @return
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
     * @param pos
     * @return
     */
    public boolean isEmpty(int pos) {
        if (get(pos) == 0) {
            return true;
        }
        return false;
    }

    /**
     * @param pos
     */
    public void removeCandy(int pos) {
        set(pos, 0);
    }
}