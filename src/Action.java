/**
 * Action Object
 *
 * <p>Representing the Action JSON sent to the driver program</p>
 *
 * @author Adrian Dagher
 * @author Julian Gardner
 * @author Tobby Lie
 * @author Drake Young
 */
public class Action
{
    // === ATTRIBUTES === //
    private int move; //Column to "drop piece" in



    // === CONSTRUCTORS === //
    /**
     * Default Constructor.
     *
     * <p>It assigns the private move attribute a value of -1, expecting it to be updated later</p>
     */
    public Action() { this.move = -1; }

    /**
     * Default Constructor.
     *
     * <p>It initializes the private move attribute with a given value</p>
     *
     * @param move an integer representing the move decided by the player
     */
    public Action(int move) { this.move = move; }



    // === ACCESSORS AND MUTATORS === //
    /**
     * Accessor for the Private Move Attribute
     *
     * @return the value stored in the private move attribute
     */
    public int getMove(){ return this.move; }

    /**
     * Mutator for the Private Move Attribute
     *
     * <P>Assign the parameter value to the private attribute "move"</p>
     *
     * @param move
     */
    public void setMove(int move){ this.move = move; }



    // === OTHER METHODS === //
    /**
     * Display JSON
     *
     * <p>
     *     Displays the object contents to one of two specified ports.
     *     Standard Error (StdErr) if the parameter stdErr is true,
     *     Standard Out (StdOut) otherwise.
     * </p>
     *
     * {@code {move: <move>} }
     *
     * @param stdErr boolean to determine whether or not to output to StdErr
     */
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
