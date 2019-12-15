package PacmanF;

import java.util.Comparator;

import javax.swing.JOptionPane;

public class GameRecord {
    /**
     * 
     */
    private String name;
    private int score;

    /**
     * @param score
     */
    public GameRecord(int score) {
        this.name = JOptionPane.showInputDialog("Score : " + score + "\nPlease enter your name");
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getInfo() {
        return name + " : " + score;
    }

    /**
     * Checks to see if two scores are the same to sort it
     * @param otherObject
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
     * sorts scores from greatest to least
     * @param record2
     * @return
     */
    public int compareTo( GameRecord record2) {
        int cmp = Integer.compare(record2.getScore(), getScore());
        if (cmp != 0) {
            return cmp;
        }
        return getName().compareTo(record2.getName());
    }

    
    public static Comparator<GameRecord> GameRecordComparator
        = new Comparator<GameRecord>() {
    	/**
         * @param record1
         * @param record2
         * compares the scores to one another
         */
    	public int compare(GameRecord record1, GameRecord record2) {
                return record1.compareTo(record2);
            }
        };
    
}