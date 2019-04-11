/**
 * Move Object
 *
 * <p>Representing cell of a potential move</p>
 *
 * @author Adrian Dagher
 * @author Julian Gardner
 * @author Tobby Lie
 * @author Drake Young
 */
public class Move
{
    // === ATTRIBUTES === //
    private int col;
    private int row;
    private int longestChain;



    // === CONSTRUCTORS === //

    /**
     * Default Constructor
     *
     * <p>Initialize all attributes to 0</p>
     */
    public Move()
    {
        this.col = 0;
        this.row = 0;
        this.longestChain = 0;
    }

    /**
     * Overloaded Constructor
     *
     * <p>Caled when column and row are known</p>
     *
     * <p>Assigns column and row appropriately, and initializes longestChain to 0</p>
     *
     * @param col column of the desired cell
     * @param row row of the desired cell
     */
    public Move(int col, int row)
    {
        this.col = col;
        this.row = row;
        this.longestChain = 0;
    }



    // === ACCESSORS AND MUTATORS === //

    /**
     * Get Col
     *
     * <p>Accessor method for the private col attribute</p>
     *
     * @return value stored in the private col attribute
     */
    public int getCol(){ return this.col; }

    /**
     * Set Col
     *
     * <p>Mutator method for the private col attribute</p>
     *
     * @param col new value for the private col attribute
     */
    public void setCol(int col){ this.col = col; }



    /**
     * Get Row
     *
     * <p>Accessor method for the private row attribute</p>
     *
     * @return value stored in the private row attribute
     */
    public int getRow(){ return this.row; }

    /**
     * Set Row
     *
     * <p>Mutator method for the private row attribute</p>
     *
     * @param row new value to be stored in the private row attribute
     */
    public void setRow(int row){ this.row = row; }


    /**
     * Get Longest Chain
     *
     * <p>Accessor method for the private longestChain attribute</p>
     *
     * @return value stored in the private longestChain attribute
     */
    public int getLongestChain(){ return this.longestChain; }

    /**
     * Set Longest Chain
     *
     * <p>Mutator method for the private longestChain attribute</p>
     * @param longestChain new value to be stored in the private longestChain attribute
     */
    public void setLongestChain(int longestChain){ this.longestChain = longestChain; }
}
