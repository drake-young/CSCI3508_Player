import java.util.Scanner;

public class CSCI3508_Player
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        // Gather the Command Line Arguments
        // Assume the form:
        //      -player <p> -width <w> -height <h>
        // TODO:
        //      modify to accept arguments in any order
        Arguments arguments = new Arguments(args);

        String gridJSON = scanner.nextLine();
        arguments.setGridFromJSON(gridJSON);

        System.err.println("Player: " + arguments.getPlayer());
        System.err.println("Width: " + arguments.getWidth());
        System.err.println("Height: " + arguments.getHeight());
        System.err.println("Grid: ");
        arguments.displayGrid(true);

        //TODO show game state through System.err

        //TODO pick appropriate move

        //TODO send move to driver through System.out
    }
}
