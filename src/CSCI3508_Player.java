// TODO document code
// TODO add javadoc to each class method
// TODO test this program against a given driver
// TODO [OPTIONAL] add "smarter" move selection

import java.util.Scanner;

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
            action.setMove(pl.getRandomMove()); //could replace this later for better AI
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
