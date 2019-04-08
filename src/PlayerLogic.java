import java.util.ArrayList;
import java.lang.Math;

public class PlayerLogic
{
    private int[][] grid;
    private ArrayList<Integer> moves;

    public PlayerLogic(int[][] grid)
    {
        this.grid = grid;
        this.moves = new ArrayList<Integer>();
    }

    public int getRandomMove()
    {
        this.moves.clear();
        int i = 0;
        for (int[] cols : grid)
        {
            if(cols[0] == 0)
            {
                moves.add(i);
            }
            ++i;
        }
        return this.moves.get((int)(Math.random() * moves.size()));
    }
}
