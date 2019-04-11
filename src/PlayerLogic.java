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
 * <p>Version 2.0: Determines move by placing to continue the longest chain</p>
 *
 * @author Adrian Dagher
 * @author Julian Gardner
 * @author Tobby Lie
 * @author Drake Young
 * @version 2.0
 */
public class PlayerLogic
{
    // === ATTRIBUTES === //
    private int[][] grid; //Visualization of board
    private ArrayList<Move> moves; //Valid move list



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
        this.moves = new ArrayList<Move>();
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
                moves.add(new Move(i, 0)); //add it to the valid move list
            }
            ++i;
        }
        return this.moves.get((int)(Math.random() * moves.size())).getCol();
    }

    /**
     * Get Move With Most Adjacency
     *
     * <p>
     *     Determine which option is next to the longest chain,
     *     and select randomly from any ties. Will block and
     *     try to win at the same time with no preference for either
     * </p>
     *
     * @return integer representing the column selection of the player
     */
    public int getMoveWithMostAdjacency()
    {
        // clear moves options to ensure "blank slate"
        this.moves.clear();

        // determine all valid columns (and their bottom cell)
        for(int col = 0; col < grid.length; ++col)
        {
            for(int row = grid[col].length-1; row >= 0; --row)
            {
                if(grid[col][row] == 0)
                {
                    moves.add(new Move(col,row));
                    break;
                }
            }
        }

        // calculate adjacency of all valid moves and determine max
        int maxAdjacency = 0;
        for (Move m : moves)
        {
            m.setLongestChain(getMaxAdjacency(m));
            if (m.getLongestChain() > maxAdjacency)
            {
                maxAdjacency = m.getLongestChain();
            }
        }

        for(Move m : new ArrayList<Move>(moves))
        {
            if (m.getLongestChain() < maxAdjacency)
            {
                moves.remove(m);
            }
        }

        return this.moves.get((int)(Math.random() * moves.size())).getCol();
    }


    /**
     * Get Max Adjacency
     *
     * <p>
     *     Check diagonals, vertical, and horizontal for the longest chain of consecutive pieces
     *     and return whatever value that is. Does not care which player the chain belongs to
     * </p>
     * @param   m  Move object that stores the column and row of the potential move
     * @return     integer representing the longest adjacent consecutive chain
     */
    private int getMaxAdjacency(Move m)
    {
        // === DETERMINE ADJACENCY IN ALL DIRECTIONS === //
        int adjacency = 0;
        int vertical = getVerticalAdjacency(m);
        int horizontal = getHorizontalAdjacency(m);
        int diagonalUR = getDiagonalUpRightAdjacency(m);
        int diagonalDR = getDiagonalDownRightAdjacency(m);

        // === DETERMINE MAXIMUM ADJACENCY === //
        adjacency = (vertical > adjacency) ? vertical : adjacency;
        adjacency = (horizontal > adjacency) ? horizontal : adjacency;
        adjacency = (diagonalUR > adjacency) ? diagonalUR : adjacency;
        adjacency = (diagonalDR > adjacency) ? diagonalDR : adjacency;

        // === RETURN RESULT === //
        return adjacency;
    }

    /**
     * Get Adjacency
     *
     * <p>
     *     Given a move and the directional offsets, determine the length of the chain in that direction
     * </p>
     *
     * @param m         move object containing column and row of the desired move
     * @param colOffset directional offset for column iteration. 0 = none, <0 = left, >0 = right
     * @param rowOffset directional offset for row iteration. 0 = none, <0 = up, >0 = down
     * @return          The length of the chain of consecutive pieces in the specified direction
     */
    private int getAdjacency(Move m, int colOffset, int rowOffset)
    {
        // === VARIABLES === //
        int chainLen = 0;
        int cellVal = 0;
        int maxCol = grid.length;
        int maxRow = grid[m.getCol()].length;

        // === ONLY CHECK IF NOT THE BOTTOM ROW === //
        // for loop:
        //      iterate c and r, where c is column and r is row
        // Initial conditions:
        //      c = column of the move + given column offset
        //      r = row of the move + given row offset
        // Iterate Again as long as:
        //      0 <= c < number of columns
        //      0 <= r < number of rows
        // At end of each iteration:
        //      c += given column offset
        //      r += given row offset
        // LOOP WAS DESIGNED TO BE GENERIC AND HANDLE ALL DIRECTIONS FROM ALL CELLS
        for (int c=m.getCol()+colOffset,r=m.getRow()+rowOffset; c>=0&&c<maxCol&&r>=0&&r<maxRow; c+=colOffset,r+=rowOffset)
        {
            // === IF CHAIN IS CONTINUING OR STARTING === //
            if(grid[c][r] == cellVal || cellVal == 0)
            {
                cellVal = grid[c][r]; // move along the chain

                // === CHAIN BROKEN BY EMPTY CELL === //
                if (cellVal == 0)
                {
                    break;
                }

                // === COUNT MOVE SINCE CHAIN NOT BROKEN === //
                ++chainLen;
            }
            // === CHAIN BROKEN BY CHANGE IN PLAYER === //
            else
            {
                break;
            }
        }

        // === RETURN CHAIN LENGTH === //
        return chainLen;
    }

    /**
     * Get Vertical Adjacency
     *
     * <p>Call getAdjacency in the vertical direction</p>
     *
     * @param m Move object containing the column and row of the desired move
     * @return  integer holding the length of the longest vertical chain below the given move
     */
    private int getVerticalAdjacency(Move m)
    {
        // === RETURN VERTICAL CHAIN LENGTH === //
        return getAdjacency(m, 0, 1);
    }

    /**
     * Get Horizontal Adjacency
     *
     * <p>Call getAdjacency in the horizontal direction</p>
     *
     * @param m Move object containing the column and row of the desired move
     * @return  integer holding the length of the longest horizontal chain left/right of the given move
     */
    private int getHorizontalAdjacency(Move m)
    {
        // === VARIABLES === //
        int horizontal = 0;
        int horizontalL = getAdjacency(m, -1, 0);
        int horizontalR = getAdjacency(m, 1, 0);

        // === SET HORIZONTAL CHAIN LENGTH TO THE MAX OF THE TWO === //
        horizontal = (horizontalL > horizontalR) ? horizontalL : horizontalR;

        // === IF CHAIN EXISTS ON BOTH SIDES === //
        if (horizontalL > 0 && horizontalR > 0)
        {
            // === IF CHAIN IS SAME PLAYER ON BOTH SIDES === //
            if (grid[m.getCol()+1][m.getRow()] == grid[m.getCol()-1][m.getRow()])
            {
                horizontal = horizontalL + horizontalR; // aggregate the two sides
            }
        }

        // === RETURN HORIZONTAL CHAIN LENGTH === //
        return horizontal;
    }

    /**
     * Get Diagonal Up-Right Adjacency
     *
     * <p>Call getAdjacency in the Up-Right and Down-Left directions</p>
     *
     * @param m Move object containing the column and row of the desired move
     * @return  integer holding the length of the longest diagonal chain up/right and down/left of the given move
     */
    private int getDiagonalUpRightAdjacency(Move m)
    {
        // === VARIABLES === //
        int diagonal = 0;
        int diagonalUR = getAdjacency(m, 1,-1);
        int diagonalDL = getAdjacency(m, -1, 1);

        // === SET VERTICAL CHAIN LENGTH TO THE MAX OF THE TWO === //
        diagonal = (diagonalUR > diagonalDL) ? diagonalUR : diagonalDL;

        // === IF CHAIN EXISTS ON BOTH SIDES === //
        if (diagonalDL > 0 && diagonalUR > 0)
        {
            // === IF CHAIN IS SAME PLAYER ON BOTH SIDES === //
            if (grid[m.getCol()+1][m.getRow()-1] == grid[m.getCol()-1][m.getRow()+1])
            {
                diagonal = diagonalDL + diagonalUR; // aggregate the two sides
            }
        }

        // === RETURN UP-RIGHT DIAGONAL CHAIN LENGTH === //
        return diagonal;
    }

    /**
     * Get Diagonal Down-Right Adjacency
     *
     * <p>Call getAdjacency in the Up-Left and Down-Right directions</p>
     *
     * @param m Move object containing the column and row of the desired move
     * @return  integer holding the length of the longest diagonal chain up/left and down/right of the given move
     */
    private int getDiagonalDownRightAdjacency(Move m)
    {
        // === VARIABLES === //
        int diagonal = 0;
        int diagonalUL = getAdjacency(m, -1, -1);
        int diagonalDR = getAdjacency(m, 1, 1);

        // === SET VERTICAL CHAIN LENGTH TO THE MAX OF THE TWO === //
        diagonal = (diagonalUL > diagonalDR) ? diagonalUL : diagonalDR;

        // === IF CHAIN EXISTS ON BOTH SIDES === //
        if (diagonalUL > 0 && diagonalDR > 0)
        {
            // === IF CHAIN IS SAME PLAYER ON BOTH SIDES === //
            if (grid[m.getCol()-1][m.getRow()-1] == grid[m.getCol()+1][m.getRow()+1])
            {
                diagonal = diagonalUL + diagonalDR; // aggregate the two
            }
        }

        // === RETURN DOWN-RIGHT DIAGONAL CHAIN LENGTH === //
        return diagonal;
    }
}
