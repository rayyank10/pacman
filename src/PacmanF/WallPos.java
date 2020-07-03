package PacmanF;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class for specifying map wall positions.
 */
public class WallPos extends ArrayList<Integer> {

  /**
   * Constructs a list of wall positions and adds 
   * map corners by default.
   */
  public WallPos() {
    new ArrayList<>() ;
    addCorners();
  }
   
  /**
   * Adds the corners to the map to wall positions.
   */
  public void addCorners() {
    for (int i=0; i<(Board.NUM_BLOCKS*Board.NUM_BLOCKS); i++) {
    	if (i < 15 || i > 210 || i %15 == 0 || i %15 == 14 ) {
    		add(i);
    	}
    }
    remove(25);
    remove(25);
  }

  /**
   * Adds level 1 walls to list of wall positions.
   */
  public void addLevel1() {
    List<Integer> wallPos2 = Arrays.asList(76,77,87,88,106,107,117,118,32,33, 47,48, 41,42,56,57,167,168,182,183,176,177,191,192, 189,188,187,186,185,172,157,35,36,38,37,39,52,67,112,111,113,97,124,139,138,145,146,130);
    addAll(wallPos2);
  }
}