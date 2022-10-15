import javax.swing.JFrame;

    /**
    * Frame, screen container, gui root
    * @category Gui
    */

public class GuiRoot extends JFrame{
    
    private Screen1 screen1;

    protected GuiRoot(short width, short height, Screen1 screen1){

        this.screen1 = screen1;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Multi-Tasking program by MrMystery");
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // Set up the gui
    protected void setUp() {

        setContentPane(screen1);
        
        pack();

        setVisible(true);

        Thread tRun = new Thread(new Runnable(){
            public void run() {
    
                try {
                    screen1.textLimit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }    
        }
        });
        tRun.start();
        
    }

}