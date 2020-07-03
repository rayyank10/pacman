package PacmanF;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;

/**
 * Creates a board which contains a pacman, ghosts, walls, &
 * canides. Every board position that isn't a wall has a 
 * candy which the player must collect to win a game. The game resets
 * when a player collides with a ghost or they collect all the candies.
 */
public class Board extends JPanel {

    public static final int NUM_BLOCKS = 15;
    public static final int BLOCK_SIZE = 30;
    public static final int BOARD_SIZE = BLOCK_SIZE * NUM_BLOCKS;

    // Default/Starting board positions.
    public static final int START_POS = 10;
    public static final int G1_POS = 8;
    public static final int G2_POS = 6;

    /**
     * Constructs a board with a pacman, two ghosts, candies, and walls.
     * A 2D Board stores a component in each position.
     */
    public Board() {
        // Sets layout & arranges Board elements. 
        setLayout(new GridLayout(NUM_BLOCKS, NUM_BLOCKS));
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));

        // Initializes pacman.
        pacman = new MovableCell(Color.YELLOW, START_POS, START_POS);
        
        // Initialize ghosts.
        ghost = new MovableCell(Color.GREEN, G1_POS, G1_POS);
        ghost2 = new MovableCell(Color.RED, G2_POS, G2_POS);

        // Initialize level 1 walls.
        walls = new WallPos();
        walls.addLevel1();

        // Initialize candies.
        candies = new Candies();

        // Initialize & fill Board.
        Board = new Cell[NUM_BLOCKS][NUM_BLOCKS];
        updateBoard();
    }

    /**
     * If the player collides with a ghost or all the candies
     * are collected, the game resets. Otherwise, updates the
     * board with new pacman, ghost, and candy positions. 
     */
    public void updateBoard() {
        removeAll();
        updateUI();
        for (int i=0; i<NUM_BLOCKS; i++) {
            for (int j=0; j<NUM_BLOCKS; j++) {
                int pos = (i*NUM_BLOCKS) + j;

                // If pacman & ghost collide or game is won, reset the game.
                if (getPacX() == ghost.getX() && getPacY() == ghost.getY() ||
                    getPacX() == ghost2.getX() && getPacY() == ghost2.getY() ||
                    isWon()) {
                        resetGame();
                }
                // Add pacman to position.
                else if (i == pacman.getX() && j == pacman.getY()) {
                    Board[i][j] = new Cell(Color.YELLOW);
                    if (!candies.isEmpty()) {
                        candies.set(pos, 0);
                    }
                }
                // Add ghosts to their positions.
                else if (i == ghost.getX() && j == ghost.getY()) {
                    Board[i][j] = new Cell(Color.GREEN);
                }
                else if (i == ghost2.getY() && j == ghost2.getY()) {
                    Board[i][j] = new Cell(Color.RED);
                }
                // Add walls to their positions.
                else if (isWall(i, j)) {
                    // Tracks score in corner block.
                    if (i == NUM_BLOCKS-1 && j == NUM_BLOCKS-1) {
                        Board[i][j] = new Cell(Color.WHITE);
                        Board[i][j].setText(String.valueOf(candies.getScore()));
                    }
                    else {
                        Board[i][j] = new Cell(Color.BLACK);
                    }
                }
                // Add candies to their positions.
                else if (candies.get(pos) == 1) {
                    Board[i][j] = new Cell(Color.BLUE);
                }
                // Everything else is an empty square.
                else {
                    Board[i][j] = new Cell(Color.WHITE);
                }
                // Add the cell to the board.
                add(Board[i][j]);
            }
        }
    }

    /**
     * Resets the game by first displaying high scores.
     * Then, resets the pacman, ghost, and candy positions.
     */
    public void resetGame() {
        Game.records.add(new GameRecord(candies.getScore()));
        Game.showHighScores();
        candies.reset();
        pacman.move(START_POS,START_POS);
        ghost.move(8, 5);
        ghost2.move(3, 4);
    }

    /**
     * Checks if pacman won by collecting all the candies.
     * @return whether pacman has collected every candy.
     */
    public boolean isWon() {
        for (int i=0; i<candies.size(); i++) {
            int row = i/NUM_BLOCKS;
            int col = i%NUM_BLOCKS;
            if (!isWall(row, col)) {
                if (!candies.isEmpty(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Moves pacman to a position on the board.
     * @param row the board's row to place pacman.
     * @param col the board's column to place pacman.
     * @precondition row >= 0 && row < NUM_BLOCKS
     * @precondition col >=  0 && col < NUM_BLOCKS
     */
    public void movePacman(int row, int col) {
        assert row >= 0 && row < NUM_BLOCKS : "Board row position doesn't exist.";
        assert col >= 0 && col < NUM_BLOCKS : "Board column position doesn't exist.";
        if (!isWall(row, col)) {
            pacman.move(row, col);
            moveGhosts();
            updateBoard();
        }
    }

    /**
     * Gets pacman's row number on the board.
     * @return pacman's row position on board.
     */
    public int getPacX() {
        return pacman.getX();
    }

    /**
     * Gets pacman's column number on the board.
     * @return pacman's column position on the board.
     */
    public int getPacY() {
        return pacman.getY();
    }

    /**
     * Moves the two ghosts to random positions if 
     * those positions aren't walls and they exist on
     * the current map.
     */
    public void moveGhosts() {
        Random r = new Random();
        int move = r.nextInt(4);
        int x1 = ghost.getX();
        int x2 = ghost2.getX();
        int y1 = ghost.getY();
        int y2 = ghost2.getY();
        switch (move) {
            case 0:
                x1 = ghost.getRandX();
            case 1:
                x2 = ghost2.getRandX();
            case 2:
                y1 = ghost.getRandY();
            case 3:
                y2 = ghost2.getRandY();
        }
        if (!isWall(x1, y1) && isValidMove(x1, y1)) {
            ghost.move(x1, y1);
        }
        if (!isWall(x2, y2) && isValidMove(x2, y2)) {
            ghost2.move(x2, y2);
        }
    }

    /**
     * Checks if the board position is a wall.
     * @param row the board's row to check for wall.
     * @param col the board's column to check for wall.
     * @return whether the board position is a wall.
     * @precondition row >= 0 && row < NUM_BLOCKS
     * @precondition col >=  0 && col < NUM_BLOCKS
     */
    public boolean isWall(int row, int col) {
        assert row >= 0 && row < NUM_BLOCKS : "Board row position doesn't exist.";
        assert col >= 0 && col < NUM_BLOCKS : "Board column position doesn't exist.";
        int pos = row * NUM_BLOCKS + col;
        for (int i=0; i<walls.size(); i++) {
            if (walls.get(i) == pos) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks existence of board position.
     * @return if the position exists on the board.
     */
    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < NUM_BLOCKS &&
            col >= 0 && col < NUM_BLOCKS;
    }

    private Cell[][] Board;
    private MovableCell pacman;
    private MovableCell ghost;
    private MovableCell ghost2;
    private WallPos walls;
    private Candies candies;
}