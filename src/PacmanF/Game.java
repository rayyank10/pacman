package PacmanF;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

public class Game extends JFrame{

  /**
 * @param name
 */
public Game(String name) {
    super(name);
  }

  /**
 * Creates and shows the gui for the game
 */
private static void createAndShowGUI() {
    Game game = new Game("Pacman");
    game.setSize(SCREEN_SIZE, SCREEN_SIZE);
    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Board board = new Board();
    game.add(board);
    game.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        game.pack();
      }
      
      /**
     * The key listeners to move Pacman around the board
     */
    @Override
      public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP) {
        board.movePacman(board.getPacRow()-1, board.getPacCol());
        game.pack();
        }
      if(key == KeyEvent.VK_DOWN) {
        board.movePacman(board.getPacRow()+1, board.getPacCol());
        }
      if(key == KeyEvent.VK_LEFT) {
        board.movePacman(board.getPacRow(), board.getPacCol()-1);
        }
      if(key == KeyEvent.VK_RIGHT) {
        board.movePacman(board.getPacRow(), board.getPacCol()+1);
        }
      }
    
      @Override
      public void keyReleased(KeyEvent e) {
      }
    });
      game.pack();
      game.setVisible(true);
  }

  /**
 * displays the highscores
 */
public static void showHighScores() {
    String theRecords = "High scores\n";
    Collections.sort(records, GameRecord.GameRecordComparator);
    int counter = 0;
    for (GameRecord record : records) {
      if (counter != 3) {
        theRecords += record.getInfo() + "\n";
        counter++;
      }
    }
    JOptionPane.showMessageDialog(null, theRecords);
  }

  public static void main(String[] args) {
    createAndShowGUI();
  }

  private static final int SCREEN_SIZE = 700;
  public static final ArrayList<GameRecord> records = new ArrayList<>();
}