
/**
 * LyricConverter: Main method to catch unexpected error
 * 
 * @author  IntelleBitnify
 * @version 1.0 (10/6/2019)
 */
public class LyricConverter
{
    /********************************************************************
     * Main method to call user interface
     *********************************************************************/
    public static void main(String[] args)
    {
        try // Catch base exception
        {
            UserInterface.mainMenu();
        }
        catch (Exception e)
        {
            System.out.println(">> CRITICAL ERROR! " + e.getMessage());
        }
    }
}