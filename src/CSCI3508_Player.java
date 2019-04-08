// TODO document code
// TODO add javadoc to each class method
// TODO test this program against a given driver

import java.util.Scanner;

public class CSCI3508_Player
{
    public static void main(String[] args)
    {
        // === ENABLE SCANNER === //
        Scanner scanner = new Scanner(System.in);

        // === GATHER CL ARGUMENTS === //
        Arguments arguments = new Arguments(args);
        String gridJSON = scanner.nextLine();
        arguments.setGridFromJSON(gridJSON);

        // === DISPLAY DATA TO STDERR === //
        arguments.displayData(true);

        // === PREPARE PLAYER LOGIC === //
        PlayerLogic pl = new PlayerLogic(arguments.getGrid());
        Action action = new Action();
        action.setMove(pl.getRandomMove());
        action.displayJSON(true); // prints it to standard error for debug/user view
        action.displayJSON(false); // sends through standard out to the driver

        // === CLOSE PORTS === //
        System.out.flush();
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
