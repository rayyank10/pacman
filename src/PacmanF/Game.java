package PacmanF;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A class for running the pacman game by 
 * updating board when user presses keys.
 */
public class Game extends JFrame{

  public static final int SCREEN_SIZE = 700;
  public static final ArrayList<GameRecord> records = new ArrayList<>();

  /**
   * Constructs a game object that can run a
   * game and listen for user input.
   * @param name the name of the game.
   */
  public Game(String name) {
    super(name);
  }

  /**
   * Initializes and adds the UI components to the game.
   */
  private static void createAndShowGUI() {
    Game game = new Game("Pacman");
    game.setSize(SCREEN_SIZE, SCREEN_SIZE);
    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Board board = new Board();
    game.add(board);
    game.pack();
    game.setVisible(true);
    game.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
      }
      
      @Override
      public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int x = board.getPacX();
        int y = board.getPacY();
        if(key == KeyEvent.VK_UP) {
          board.movePacman(x-1, y);

        }
      if(key == KeyEvent.VK_DOWN) {
        board.movePacman(x+1, y);
      }
      if(key == KeyEvent.VK_LEFT) {
        board.movePacman(x, y-1);
        }
      if(key == KeyEvent.VK_RIGHT) {
        board.movePacman(x, y+1);
        }
        board.updateBoard();
        game.pack();
      }
    
      @Override
      public void keyReleased(KeyEvent e) {
      }
    });
  }

  /**
   * Shows the top three high scores of the game.
   */
  public static void showHighScores() {
    String theRecords = "High scores\n";
    Collections.sort(records, GameRecord.GameRecordComparator);
    int counter = 1;
    for (GameRecord record : records) {
      if (counter <=3) {
        theRecords += (counter + ". " + record.getInfo() + "\n");
        counter++;
      }
    }
    JOptionPane.showMessageDialog(null, theRecords);
  } 

  public static void main(String[] args) {
    createAndShowGUI();
  }
}