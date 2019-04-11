// TODO [OPTIONAL] add "smarter" move selection

// === IMPORTS === //
import java.util.Scanner; // imported for using scanner to read from System.in

/**
 * Main Class
 *
 * <p>
 *     Retrieves the arguments, initializes the appropriate supporting classes,
 *     uses the PlayerLogic class to determine a move, sends the Action object's JSON
 *     to the Driver, then closes all ports (StdIn, StdOut, and StdErr).
 * </p>
 *
 * @author Adrian Dagher
 * @author Julian Gardner
 * @author Tobby Lie
 * @author Drake Young
 */
public class CSCI3508_Player
{
    public static void main(String[] args)
    {
        // === ENABLE SCANNER === //
        Scanner scanner = new Scanner(System.in);

        // === GATHER CL ARGUMENTS === //
        Arguments arguments = new Arguments(args);
        
        // === DISPLAY ARGS TO STDERR === //
        arguments.displayArgs(true);
        
        // === MOVE LOOP === //
        while (scanner.hasNextLine())
        {
            // === GET CURRENT GRID === //
            String gridJSON = scanner.nextLine();
            arguments.setGridFromJSON(gridJSON);

            // === PREPARE PLAYER LOGIC === //
            PlayerLogic pl = new PlayerLogic(arguments.getGrid());
            Action action = new Action();
            action.setMove(pl.getMoveWithMostAdjacency()); //could replace this later for better AI
            action.displayJSON(true); // prints it to standard error for debug/user view
            action.displayJSON(false); // sends through standard out to the driver
        }

        // === CLOSE PORTS === //
        try
        {
            System.in.close();
            System.out.close();
            System.err.close();
        }
        catch (java.io.IOException ioe)
        {
            System.out.close();
            System.err.close();
        }
    }
}
