import java.util.*;
import java.io.*;
/**
 * UserInterface: All method that display something to the screen
 * 
 * @author  IntelleBitnify
 * @version 1.0 (10/6/2019)
 */
public class UserInterface
{
    /*********************************************************
     * Main Menu: Choose module to run based on user input
     * 
     * @return  Execute submodule based on the user choice
     * 
     * @author  IntelleBitnify
     * @version 1.0 (10/6/2019)
     *********************************************************/
    public static void mainMenu()
    {
        //Define data structure
        int option;

        //Display splash screen
        displaySplashScreen();

        //Main menu
        do //Loop while not exit is selected
        {
            System.out.println("\n>> MAIN MENU");
            System.out.println("Choose an option:");
            System.out.println("1. Convert .lrc to .srt");
            System.out.println("2. Convert .lrc to .srt then increase the speed of lyric");
            System.out.println("3. Exit");
            option = integerInput(1,3);
            switch (option)
            {
                case 1:
                System.out.println(">> CONVERT LRC TO SRT");
                convertMenu();
                break;

                case 2:
                System.out.println(">> INCREASE THE SPEED OF LYRIC");
                convertMultiplierMenu();
                break;

                case 3:
                System.out.println(">> EXIT! Closing program..");
                break;
            }
        } while (option != 3);
    }

    /************************************************************
     * User input for integer number (prevent Input Mismatch Exception)
     * 
     * @return userInput (integer)
     * 
     * @author  IntelleBitnify
     * @version 1.0 (10/6/2019)
     ************************************************************/
    private static int integerInput(int min, int max)
    {
        //Define data structure
        int userInput;
        boolean validInput;
        Scanner sc;

        //Initialize object
        sc = new Scanner(System.in);

        //Define default value
        userInput = Integer.MAX_VALUE;
        validInput = false;
        do
        {
            try
            {
                System.out.print("Your input: ");
                userInput = sc.nextInt();
                if (userInput < min || userInput > max)
                {
                    System.out.println(">> ERROR! Only input number in range (" + min + " to " + max + ")");
                    System.out.print("Re-input: ");
                }
                validInput = true;
            }
            catch (InputMismatchException a)
            {
                System.out.println(">> ERROR! Inputting a character or decimal are prohibited");
                System.out.print("Re-input: ");
            }
        } while (!validInput);

        //Space to keep the UI look clean
        System.out.println();

        return userInput;
    }

    /************************************************************
     * User input for real number (prevent Input Mismatch Exception)
     * 
     * @return userInput (real)
     * 
     * @author  IntelleBitnify
     * @version 1.0 (10/6/2019)
     ************************************************************/
    private static double realInput(double min, double max)
    {
        //Define data structure
        double userInput;
        boolean validInput;
        Scanner sc;

        //Initialize object
        sc = new Scanner(System.in);

        //Define default value
        userInput = Double.MAX_VALUE;
        validInput = false;
        do
        {
            try
            {
                System.out.print("Your input: ");
                userInput = sc.nextDouble();
                if (userInput < min || userInput > max)
                {
                    System.out.println(">> ERROR! Only input number in range (" + min + " to " + max + ")");
                    System.out.print("Re-input: ");
                }
                validInput = true;
            }
            catch (InputMismatchException a)
            {
                System.out.println(">> ERROR! Inputting a character or decimal are prohibited");
                System.out.print("Re-input: ");
            }
        } while (!validInput);

        //Space to keep the UI look clean
        System.out.println();

        return userInput;
    }

    /************************************************************
     * Menu for "Convert .lrc to .srt"
     * 
     * @return  Output file to ../Output/ with the file og ..._converted.srt
     * 
     * @author  IntelleBitnify
     * @version 1.0 (10/6/2019)
     ************************************************************/
    private static void convertMenu()
    {
        //Define data structure
        SongLyric inSong;
        String inFilename;

        //Initialize object
        inSong = new SongLyric();

        //Prompt for user to input a filename
        inFilename = filenameInput();

        FileManager.readLRC(inSong, inFilename);
        FileManager.writeLRC(inSong, inFilename);
        FileManager.writeSRT(inSong, inFilename);
    }

    /************************************************************
     * Menu for "Convert .lrc to .srt then increase the speed of lyric"
     * 
     * @return  Output file to ../Output/ with the file og ..._converted.srt
     * 
     * @author  IntelleBitnify
     * @version 1.0 (10/6/2019)
     ************************************************************/
    private static void convertMultiplierMenu()
    {
        //Define data structure
        double inMultiplier;
        String inFilename;
        SongLyric inSong;

        //Initialize object
        inSong = new SongLyric();

        //Prompt for the multiplier
        System.out.println("Specify the speed of song (1.0x - 5.0x, input the number only):");
        inMultiplier = realInput(1.0,5.0);

        //Prompt for user to input a filename
        inFilename = filenameInput();

        FileManager.readLRC(inSong, inFilename);

        //Change the multiplier
        inSong.changeSpeed(inMultiplier);
        FileManager.writeLRC(inSong, inFilename);
        FileManager.writeSRT(inSong, inFilename);
    }

    /************************************************************
     * Splash Screen for the first start
     * 
     * @return  Information to the screen
     * 
     * @author  IntelleBitnify
     * @version 1.0 (10/6/2019)
     ************************************************************/
    private static void displaySplashScreen()
    {
        System.out.println("[]=======================[]");
        System.out.println("||    LYRIC CONVERTER    ||");
        System.out.println("[]=======================[]");
    }

    /************************************************************
     * User input for a filename for File IO and validate the file availability
     * 
     * @return  inFilename (String)
     * 
     * @author  IntelleBitnify
     * @version 1.0 (10/6/2019)
     ************************************************************/
    private static String filenameInput()
    {
        //Define data structure
        boolean validInput;
        char inChar;
        File inFile;
        String inFilename;
        Scanner sc;

        //Initialize object
        sc = new Scanner(System.in);

        //Define default value
        validInput = false;
        inFile = null;
        do //Loop while the filename is invalid
        {
            //List the available file to help user
            listAvailableFile();
            //Ask for user to input filename
            System.out.print("Specify the filename: ");
            inFilename = sc.next();

            inFile = new File("./Input/" + inFilename);

            //Check the file validity
            if (inFile.length() == 0) //If the file is empty
            {
                System.out.println(">> WARNING! File contain no data (blank file)");
            }
            else //File is valid
            {
                validInput = true; //Filename valid, out from the loop
            }
        } while(!validInput);
        return inFilename;
    }

    /************************************************************
     * Output the list of file in the directory ../Input to the screen
     * 
     * @return  Information to the screen
     * 
     * @author  IntelleBitnify
     * @version 1.0 (10/6/2019)
     ************************************************************/
    private static void listAvailableFile()
    {
        //Define data structure
        int i;
        File[] folder;

        //List all file in the directory of ../Input
        folder = new File("./Input/").listFiles();
        //Output Header
        System.out.println("[!]----------------------------------------------------------------------------");
        System.out.println(" |  >> HELPER: Put the ship file inside \"../Input\" directory) ");
        System.out.println(" |  >> INFO: List of the file available: ");
        for (i=0; i<folder.length; i++) //Output all filename in the directory
        {
            System.out.println(" |  • " + folder[i].getName());
        }
        System.out.println("[!]----------------------------------------------------------------------------\n");
    }
}