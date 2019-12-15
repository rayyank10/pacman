package PacmanF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Walls extends ArrayList<Integer> {

  /**
 * 
 */
public Walls() {
    new ArrayList<>() ;
    addCorners();
  }
   
  /**
 * adds walls all around the corner of the board
 * @precondition 
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
 * adds all of the walls inside the the borders
 */
public void addLevel1() {
    List<Integer> wallPo = Arrays.asList(76,77,87,88,106,107,117,118,32,33, 47,48, 41,42,56,57,167,168,182,183,176,177,191,192, 189,188,187,186,185,172,157,35,36,38,37,39,52,67,112,111,113,97,124,139,138,145,146,130);
    addAll(wallPo);
  }

  /**
 *checks to see if the location is a wall
 * @param row
 * @param col
 * @return
 */
public boolean isWall(int row, int col) {
    int pos = (row * Board.NUM_BLOCKS) + col;
    for (int i=0; i<size(); i++) {
      if (get(i) == pos) {
        return true;
      }
    }
    return false;
  }
}