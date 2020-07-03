package PacmanF;

import java.util.Comparator;
import javax.swing.JOptionPane;

/**
 * Creates a game record which tracks a player's name and score
 * after they play a game.
 */
public class GameRecord {
    private String name;
    private int score;

    /**
     * Constructs a game record object which contains a score and 
     * prompts user to enter their name.
     * @param score the user's game score.
     * @precondition score <= (Board.NUM_BLOCKS * Board.NUM_BLOCKS);
     */
    public GameRecord(int score) {
        assert score <= (Board.NUM_BLOCKS * Board.NUM_BLOCKS) : "Score is invalid";
        this.name = JOptionPane.showInputDialog("Score : " + score + "\nPlease enter your name");
        this.score = score;
    }

    /**
     * Gets the name of the player.
     * @return the player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's score.
     * @return the player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the players formatted
     * name and score.
     */
    public String getInfo() {
        return name + " : " + score;
    }

    /**
     * Compares two game records.
     * @return Whether the two game records have
     *  the same name and score.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        GameRecord other = (GameRecord) otherObject;
        return name.equals(other.name) && score == other.score;
    }

    /**
     * Compares two records and returns 1 if record1 > record2, 0
     * if record1 == record2 and -1 if record1 < record2.
     * @param record2 the record to compare to this record.
     * @return an integer value after testing equality.
     */
    public int compareTo( GameRecord record2) {
        int cmp = Integer.compare(record2.getScore(), getScore());
        if (cmp != 0) {
            return cmp;
        }
        return getName().compareTo(record2.getName());
    }
    /**
     * Creates an object in an anoymous class to compare game records.
     */
    public static Comparator<GameRecord> GameRecordComparator
        = new Comparator<GameRecord>() {
            public int compare(GameRecord record1, GameRecord record2) {
                return record1.compareTo(record2);
            }
        };
    
}