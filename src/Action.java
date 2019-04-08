public class Action
{
    private int move;

    public Action() { this.move = -1; }

    public Action(int move) { this.move = move; }

    public int getMove(){ return this.move; }
    public void setMove(int move){ this.move = move; }

    public void displayJSON(boolean stdErr)
    {
        String json = String.format("{move:%d}", this.move);
        if (stdErr)
        {
            System.err.println(json);
        }
        else
        {
            System.out.println(json);
        }
    }
}
