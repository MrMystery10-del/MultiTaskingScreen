/**
 * Logic/Base of the program, variable container
 * @author Kirils Turkins
 */

public class Logic{
    
    private final short width = 810;
    private final short height = 1000; 

    private final Screen1 screen1 = new Screen1(width, height);
    private final GuiRoot guiRoot = new GuiRoot(width, height, screen1);

    // run the program
    public void run(){

        guiRoot.setUp();
    }
}
