public class Arguments
{
    private int player; //Player number
    private int width; //Board width
    private int height; //Board height
    private int[][] grid; //Visualization of board
    public Arguments() //Default Constructor
    {
        this.player = 0;
        this.width = 0;
        this.height = 0;
        this.grid = new int[0][0];
    }

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


    //Accessors and Mutators
    public int getPlayer(){ return this.player; }
    public void setPlayer(int player){ this.player = player; }

    public int getWidth(){ return this.width; }
    public void setWidth(int width){ this.width = width; }

    public int getHeight(){ return this.height; }
    public void setHeight(int height){ this.height = height; }

    public int[][] getGrid(){ return this.grid; }
    public void setGrid(int[][] grid){ this.grid = grid; }

    //Parse JSON format string to set grid.
    public void setGridFromJSON(String json)
    {
        String arr = json.replaceAll("[\\{\\}\\s]", ""); //Remove curly braces and spaces
        arr = arr.replaceAll("^(.*):", ""); //Remove "grid":
        arr = arr.replaceAll( "\\],", ";"); //Replace column terminators with semicolons
        arr = arr.replaceAll( "[\\[\\]]", ""); //Remove other brackets
        String[] cols = arr.split(";"); //Parse into columns

        System.err.println("JSON: " + json); //Print passed-in string to stderr
        System.err.println("Arr: ");
        for (int i = 0; i < cols.length; ++i)
        {
            System.err.println("Column " + i + ": " + cols[i]); //Print parsed grid to stderr
            String[] row = cols[i].split(","); //Parse into rows
            for (int j = 0; j < row.length; ++j)
            {
                this.grid[i][j] = Integer.parseInt(row[j]); //Convert string-based numbers to integers
            }
        }
    }

    //Displays args to stderr if stderr is true, otherwise display to stdout
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
            System.out.println("Width: " + this.width);
            System.out.println("Height: " + this.height);
        }
    }

    //Display grid to stderr if stderr is true, otherwise display to stdout
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
                }
                System.err.printf("%n");
            }
        }
    }
}
