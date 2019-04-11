/**
 * Arguments Object
 *
 * <p>Representing the arguments provided from the player program</p>
 *
 * @author Adrian Dagher
 * @author Julian Gardner
 * @author Tobby Lie
 * @author Drake Young
 */
public class Arguments
{
    // === ATTRIBUTES === //
    private int player; //Player number
    private int width; //Board width
    private int height; //Board height
    private int[][] grid; //Visualization of board



    // === CONSTRUCTORS === //
    /**
     * Default Constructor
     *
     * <p>
     *     initializes all private integers with a value of 0,
     *     the private grid to a 0x0 matrix
     * </p>
     */
    public Arguments() //Default Constructor
    {
        this.player = 0;
        this.width = 0;
        this.height = 0;
        this.grid = new int[0][0];
    }

    /**
     * Overloaded constructor
     *
     * <p>Initializes the Arguments from the string array</p>
     * <p>The grid will be empty at first, but will be initialized to the appropriate dimensions</p>
     *
     * <p>Expected:</p>
     * {@code ["--player", "<p>", "--width", "<w>", "--height", "<h>"]}
     *
     * <p>In its current state it isvery fragile, as arguments are expected in a specific order</p>
     * @param args
     */
    public Arguments(String[] args) //Command-line args constructor - very fragile and expects args in certain places
    {
        this.player = Integer.parseInt(args[1]);
        this.width = Integer.parseInt(args[3]);
        this.height = Integer.parseInt(args[5]);
        this.grid = new int[width][height];

        //Initialize grid
        for (int i = 0; i < width; ++i)
        {
            for (int j = 0; j < height; ++j)
            {
                grid[i][j] = 0;
            }
        }
    }


    // === ACCESSORS AND MUTATORS === //
    /**
     * Player Attribute Accessor
     *
     * <p>Retrieve the value stored in the private player attribute</p>
     *
     * @return  value stored in the private player attribute
     */
    public int getPlayer(){ return this.player; }

    /**
     * Player Attribute Mutator
     *
     * <p>Modify the value stored in the private player attribute to the given integer value</p>
     *
     * @param player new player number to be assigned to the player attribute
     */
    public void setPlayer(int player){ this.player = player; }

    /**
     * Width Attribute Accessor
     *
     * <p>Retrieve the value stored in the private width attribute</p>
     *
     * @return value stored in the private width attribute
     */
    public int getWidth(){ return this.width; }

    /**
     * Height Attribute Accessor
     *
     * <p>Retrieve the value stored in the private height attribute</p>
     *
     * @return value stored in the private height attribute
     */
    public int getHeight(){ return this.height; }

    /**
     * Grid Attribute Accessor
     *
     * <p>Retrieve the matrix stored in the private grid attribute</p>
     *
     * @return the matrix represented by the private grid attribute
     */
    public int[][] getGrid(){ return this.grid; }

    /**
     * Grid Attribute Mutator
     *
     * <p>Replace the matrix stored in the private grid attribute to the given matrix</p>
     *
     * @param grid a new integer matrix to replace the current one stored in the private grid attribute
     */
    public void setGrid(int[][] grid){ this.grid = grid; }



    // === OTHER METHODS === //
    /**
     * Set Grid from JSON
     *
     * <p>
     *     Parses the given json string to retrieve the "grid" matrix from it and store that in
     *     the private grid attribute
     * </p>
     * <p>Expected form: {@code {grid: [[col-1], [col-2], ..., [col-n]]}}</p>
     * <p>
     *     NOTE: it is expected that the given matrix is the same dimensions as the private grid attribute
     * </p>
     *
     * @param json json-format string containing the matrix to be assigned
     */
    public void setGridFromJSON(String json)
    {
        String arr = json.replaceAll("[\\{\\}\\s]", ""); //Remove curly braces and spaces
        arr = arr.replaceAll("^(.*):", ""); //Remove "grid":
        arr = arr.replaceAll( "\\],", ";"); //Replace column terminators with semicolons
        arr = arr.replaceAll( "[\\[\\]]", ""); //Remove other brackets
        String[] cols = arr.split(";"); //Parse into columns

        System.err.println("JSON: " + json); //Print passed-in string to stderr -- for debugging
        System.err.println("Arr: "); // for debugging
        for (int i = 0; i < cols.length; ++i)
        {
            System.err.println("Column " + i + ": " + cols[i]); //Print parsed grid to stderr -- for debugging
            String[] row = cols[i].split(","); //Parse into rows
            for (int j = 0; j < row.length; ++j)
            {
                this.grid[i][j] = Integer.parseInt(row[j]); //Convert string-based numbers to integers
            }
        }
    }

    /**
     * Display Args
     *
     * <p>Displays all of the private integer attributes (primarily used for debugging)</p>
     *
     * <p>
     *     Output is displayed to either Standard Error or Standard Out depending on the
     *     value of the boolean stdErr parameter (display to Standard Error if true)
     * </p>
     * @param stdErr boolean used to determine whether to output to StdErr or StdOut
     */
    public void displayArgs(boolean stdErr)
    {
        if(stdErr)
        {
            System.err.println("Player: " + this.player);
            System.err.println("Width: " + this.width);
            System.err.println("Height: " + this.height);
        }
        else
        {
            System.out.println("Player: " + this.player);
            System.out.flush();
            System.out.println("Width: " + this.width);
            System.out.flush();
            System.out.println("Height: " + this.height);
            System.out.flush();
        }
    }

    /**
     * Display Grid
     *
     * <p>
     *     Prints out the private grid attribute in matrix form to either Standard Out
     *     or Standard Error depending on the value of the stdErr parameter. Standard
     *     Error if true, otherwise, Standard Out
     * </p>
     *
     * @param stdErr boolean to determine whether to display to standard error or standard out
     */
    public void displayGrid(boolean stdErr)
    {
        if (stdErr)
        {
            System.err.println("Grid:");
            for(int i = 0; i < height; ++i)
            {
                for(int j = 0; j < width; ++j)
                {
                    System.err.print(" " + this.grid[j][i] + " ");
                }
                System.err.printf("%n");
            }
        }
        else
        {
            System.out.println("Grid:");
            for(int i = 0; i < height; ++i)
            {
                for(int j = 0; j < width; ++j)
                {
                    System.out.print(" " + this.grid[j][i] + " ");
                    System.out.flush();
                }
                System.out.printf("%n");
                System.out.flush();
            }
        }
    }
}
