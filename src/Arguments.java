public class Arguments
{
    private int player;
    private int width;
    private int height;
    private int[][] grid;

    public Arguments()
    {
        this.player = 0;
        this.width = 0;
        this.height = 0;
        this.grid = new int[0][0];
    }

    public Arguments(String[] args)
    {
        this.player = Integer.parseInt(args[1]);
        this.width = Integer.parseInt(args[3]);
        this.height = Integer.parseInt(args[5]);
        this.grid = new int[width][height];

        for (int i = 0; i < width; ++i)
        {
            for (int j = 0; j < height; ++j)
            {
                grid[i][j] = 0;
            }
        }
    }

    public int getPlayer(){ return this.player; }
    public void setPlayer(int player){ this.player = player; }

    public int getWidth(){ return this.width; }
    public void setWidth(int width){ this.width = width; }

    public int getHeight(){ return this.height; }
    public void setHeight(int height){ this.height = height; }

    public int[][] getGrid(){ return this.grid; }
    public void setGrid(int[][] grid){ this.grid = grid; }

    public void setGridFromJSON(String json)
    {
        String arr = json.replaceAll("[[\\{\\}\\s]]", "");
        arr = arr.replaceAll("^(.*):", "");
        arr = arr.replaceAll( "\\],", ";");
        arr = arr.replaceAll( "[\\[\\]]", "");
        String[] cols = arr.split(";");

        System.err.println("JSON: " + json);
        System.err.println("Arr: ");
        for (int i = 0; i < cols.length; ++i)
        {
            System.err.println("Column " + i + ": " + cols[i]);
            String[] row = cols[i].split(",");
            for (int j = 0; j < row.length; ++j)
            {
                this.grid[i][j] = Integer.parseInt(row[j]);
            }
        }
    }

    public void displayGrid(boolean stdErr)
    {
        if (stdErr)
        {
            for(int i = 0; i < height; ++i)
            {
                for(int j = 0; j < width; ++j)
                {
                    System.out.print(" " + this.grid[j][i] + " ");
                }
                System.err.printf("%n");
            }
        }
        else
        {
            for(int i = 0; i < height; ++i)
            {
                for(int j = 0; j < width; ++j)
                {
                    System.err.print(" " + this.grid[j][i] + " ");
                }
                System.err.printf("%n");
            }
        }
    }
}