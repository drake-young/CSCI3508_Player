import java.util.ArrayList;
import java.lang.Math;

public class PlayerLogic
{
    private int[][] grid; //Visualization of board
    private ArrayList<Integer> moves; //Valid move list

    //Grid constructor
    public PlayerLogic(int[][] grid)
    {
        this.grid = grid;
        this.moves = new ArrayList<Integer>();
    }

    //Calculate random column to make a move in that is valid.
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
