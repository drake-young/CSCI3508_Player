// === IMPORTS === //
import java.util.ArrayList; // imported for the ArrayList contianer
import java.lang.Math;      // imported for Math.random()

/**
 * Player Logic Object
 *
 * <p>Representing the AI logic made by the player program for determining which moves to make</p>
 *
 * <p>Version 1.0: provides random move selection</p>
 *
 * @author Adrian Dagher
 * @author Julian Gardner
 * @author Tobby Lie
 * @author Drake Young
 */
public class PlayerLogic
{
    // === ATTRIBUTES === //
    private int[][] grid; //Visualization of board
    private ArrayList<Integer> moves; //Valid move list



    // === CONSTRUCTORS === //
    /**
     * Constructor
     * <p>
     *     Initializes the grid attribute with the given parameter
     *     and the moves attribute to an empty ArrayList
     * </p>
     * @param grid matrix containing the current state of the game (used to determine valid moves)
     */
    public PlayerLogic(int[][] grid)
    {
        this.grid = grid;
        this.moves = new ArrayList<Integer>();
    }



    // === OTHER METHODS === //
    //Calculate random column to make a move in that is valid.
    /**
     * Get Random Move
     *
     * <p>
     *     Determine all columns with an available move option (at least one empty slot).
     *     Then randomly select one of those options and return the column number of the
     *     selected move.
     * </p>
     * <p>NOTE: this method expects there to be at least one valid move available</p>
     *
     * @return integer representing a column number of a valid move
     */
    public int getRandomMove()
    {
        this.moves.clear();
        int i = 0;
        for (int[] cols : grid) //Determine valid moves
        {
            if(cols[0] == 0) //If there's no piece at the top of the column, the move is valid.
            {
                moves.add(i); //add it to the valid move list
            }
            ++i;
        }
        return this.moves.get((int)(Math.random() * moves.size()));
    }
}
