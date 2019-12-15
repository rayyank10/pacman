package PacmanF;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

/**
 * @author user
 *
 */
public class Board extends JPanel {

    /**
     * 
     */
    public Board() {

        // Sets the layout & arranges grid elements. 
        setLayout(new GridLayout(NUM_BLOCKS, NUM_BLOCKS));
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setPreferredSize(new Dimension(GRID_SIZE, GRID_SIZE));

        // Initialize pacman.
        player = new Pacman();
        pacx = 10;
        pacy = 10;

        // Initialize ghosts
        ghost = new Ghost(Color.GREEN);
        ghostx = 5;
        ghosty = 5;

        // Initialize level 1 walls.
        walls = new Walls();
        walls.addLevel1();

        // Initialize candies.
        candies = new Candies();

        // Initialize & fill grid.
        grid = new Cell[NUM_BLOCKS][NUM_BLOCKS];
        updateGrid();
    }

    /**
     * Removes all grid elements and creates new grid with 
     * updated pacman, ghost, and candy positions. 
     */
    public void updateGrid() {
        removeAll();
        updateUI();
        for (int i=0; i<NUM_BLOCKS; i++) {
            for (int j=0; j<NUM_BLOCKS; j++) {
                int pos = (i*NUM_BLOCKS) + j;
                
                // If player and ghost collide, reset the game.
                if (ghostx == pacx && ghosty == pacy) {
                    resetGame();


                }
                else if (isWon()) {
                    resetGame();
                }
                // Draw pacman at position.
                else if (i == pacx && j == pacy) {
                    grid[i][j] = player;
                    if (!candies.isEmpty()) {
                        candies.removeCandy(pos);
                    }
                }
                // Draw ghost at position.
                else if (i == ghostx && j == ghosty) {
                    grid[i][j] = ghost;
                }
                // Draw walls at positions.
                else if (walls.isWall(i, j)) {
                    // Tracks score in corner wall.
                    if (i == NUM_BLOCKS-1 && j == NUM_BLOCKS-1) {
                        grid[i][j] = new Cell(Color.WHITE);
                        grid[i][j].setText(String.valueOf(candies.getScore()));
                    }
                    else {
                        grid[i][j] = new Cell(Color.BLACK);
                    }
                }
                // Draw candies at positions
                else if (candies.get(pos) == 1) {
                    grid[i][j] = new Cell(Color.BLUE);
                }
                // Everything else is an empty square.
                else {
                    grid[i][j] = new Cell(Color.WHITE);
                }
                // Add the grid element to the frame.
                add(grid[i][j]);
            }
        }
    }

    /**
     * 
     */
    public void resetGame() {
        Game.records.add(new GameRecord(candies.getScore()));
        Game.showHighScores();
        ghostx = 5;
        ghosty = 5;
        pacx = 10;
        pacy = 10;
        candies.reset();
    }

    /**
     * 
     */
    public void printScores() {
        for (GameRecord record : Game.records) {
            System.out.println(record.getInfo());
        }
    }
    
    /**
     * @return
     */
    public boolean isWon() {
        for (int i=0; i<candies.size(); i++) {
            int row = i / 15;
            int col = i % 15;
            if (!walls.isWall(row, col)) {
                if (!candies.isEmpty(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Moves pacman around the board
     * @param row
     * @param col
     */
    public void movePacman(int row, int col) {
        if (!walls.isWall(row, col)) {
            pacx = row;
            pacy = col;
            updateGrid();
            randomGhostMove();
        }
    }

    /**
     * @precondition ghost != null;
     */
    public void randomGhostMove() {
        int move = ghost.chooseMove();
        assert ghost != null : "Violated Precondition ghost != null";
        switch (move) {
            case 0:
                ghostx++;
                if (!walls.isWall(ghostx, ghosty)) {
                    updateGrid();
                    break;
                }
                ghostx--;
                randomGhostMove();
            case 1:
                ghostx--;
                if (!walls.isWall(ghostx, ghosty)) {
                    updateGrid();
                    break;
                }
                ghostx++;
                randomGhostMove();
            case 2:
                ghosty++;
                if (!walls.isWall(ghostx, ghosty)) {
                    updateGrid();
                    break;
                }
                ghosty--;
                randomGhostMove();
            case 3:
                ghosty--;
                if(!walls.isWall(ghostx, ghosty)) {
                    updateGrid();
                    break;
                }
                ghosty++;
                randomGhostMove();
        }
    }
    
    /**
     * @precondition ghost != null;
     */
    public void moveGhost() {
    	assert ghost != null : "Violated Precondition ghost != null";
        int move = ghost.chooseMove();
        switch (move) {
            case 0:
                ghostx++;
                if (walls.isWall(ghostx, ghosty)) {
                    ghostx--;
                    randomGhostMove();
                }
                else {
                    updateGrid();
                    break;
                }
            case 1:
                ghostx--;
                if (walls.isWall(ghostx, ghosty)) {
                    ghostx++;
                    randomGhostMove();
                }
                else {
                    updateGrid();
                    break;
                }
            case 2:
                ghosty++;
                if (walls.isWall(ghostx, ghosty)) {
                    ghosty--;
                    randomGhostMove();
                }
                else {
                    updateGrid();
                    break;
                }
            case 3:
                ghosty--;
                if (walls.isWall(ghostx, ghosty)) {
                    ghosty++;
                    randomGhostMove();
                }
                else {
                    updateGrid();
                    break;
                }
        }
        updateGrid();
    }

    /**
     * @return
     */
    public int getPacRow() {
        return pacx;
    }

    /**
     * @return
     */
    public int getPacCol() {
        return pacy;
    }

    public static final int NUM_BLOCKS = 15;
    private static final int BLOCK_SIZE = 30;
    private static final int GRID_SIZE = BLOCK_SIZE * NUM_BLOCKS;

    private int pacx, pacy;
    private int ghostx, ghosty;
    private Walls walls;
    private Cell[][] grid;
    private Pacman player;
    private Ghost ghost;
    private static Candies candies;
}

