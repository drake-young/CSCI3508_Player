public class Action
{
    private int move; //Column to "drop piece" in

    //Default constructor
    public Action() { this.move = -1; }

    //Constructor - if we already calculated a move
    public Action(int move) { this.move = move; }

    //Accessors and Mutators
    public int getMove(){ return this.move; }
    public void setMove(int move){ this.move = move; }

    //Displays move JSON string to stderr if stdErr is true, or stdout if false.
    public void displayJSON(boolean stdErr)
    {
        String json = String.format("{\"move\":%d}", this.move);
        if (stdErr)
        {
            System.err.println(json);
        }
        else
        {
            System.out.println(json);
            System.out.flush();
        }
    }
}
