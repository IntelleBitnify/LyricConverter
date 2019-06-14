
/**
 * TestLyricConverter: Test harness class to test the readout of the program
 * 
 * @author  IntelleBitnify
 * @version 1.0 (10/6/2019)
 */
public class TestLyricConverter
{
    public static void main ()
    {
        //Create test SongLyric object for testing
        SongLyric test = new SongLyric();
        FileManager.readLRC(test, "test.lrc");
        
        System.out.println(">> READ FILE TEST HARNESS");
        System.out.println("\n>>INFO: Outputting the inputted lyric... (test.lrc)");
        System.out.println("<seconds> <lyric>");
        
        //Print out the file readout
        System.out.println(test.toString());
        System.out.println(">>INFO: End of lyric reached!");
    }
}