import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JColorChooser;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Consumer;

/** Screen 1 which extends a JPanel and implements a ActionListener to detect button clicks */
public class Screen1 extends JPanel implements ActionListener{
    
    // Text stats for changing text on button click without overriding stats given by another button
    private String font = "Comic Sans MS";
    private int textSize = 50;
    private Color textColor = Color.BLACK; 

    // Create all JComonents for the program
    private JTextField text = text();
    private JLabel label = label();

    // Creates all 20 Buttons using the button blueprint
    private Map<JButton, Consumer<ActionEvent>> bMap = new HashMap<>();
    private JButton button1 = button((short)20, (short)200, (short)240, (short)50, new Font("Arial", Font.BOLD, 50), "Red");
    private JButton button2 = button((short)280, (short)200, (short)240, (short)50, new Font("Arial", Font.BOLD, 50), "Green");
    private JButton button3 = button((short)540, (short)200, (short)240, (short)50, new Font("Arial", Font.BOLD, 50), "Blue");
    private JButton button4 = button((short)20, (short)260, (short)240, (short)50, new Font("Arial", Font.BOLD, 50), "Yellow");
    private JButton button5 = button((short)280, (short)260, (short)240, (short)50, new Font("Arial", Font.BOLD, 30), "Default color");
    private JButton button6 = button((short)540, (short)260, (short)240, (short)50, new Font("Arial", Font.BOLD, 32), "Choose color");
    private JButton button7 = button((short)20, (short)380, (short)240, (short)50, new Font("Arial", Font.BOLD, 50), "Arial");
    private JButton button8 = button((short)280, (short)380, (short)240, (short)50, new Font("Comic Sans MS", Font.BOLD, 26), "Comic Sans MS");
    private JButton button9 = button((short)540, (short)380, (short)240, (short)50, new Font("Courier New", Font.BOLD, 30), "Courier New");
    private JButton button10 = button((short)20, (short)490, (short)360, (short)50, new Font("Arial", Font.BOLD, 30), "Write in label");
    private JButton button11 = button((short)420, (short)490, (short)360, (short)50, new Font("Arial", Font.BOLD, 30), "Remove text from label");
    private JButton button12 = button((short)20, (short)590, (short)240, (short)50, new Font("Arial", Font.BOLD, 50), "Red");
    private JButton button13 = button((short)280, (short)590, (short)240, (short)50, new Font("Arial", Font.BOLD, 50), "Blue");
    private JButton button14 = button((short)540, (short)590, (short)240, (short)50, new Font("Arial", Font.BOLD, 50), "Black");
    private JButton button15 = button((short)20, (short)690, (short)360, (short)50, new Font("Arial", Font.BOLD, 70), "+");
    private JButton button16 = button((short)420, (short)690, (short)360, (short)50, new Font("Arial", Font.BOLD, 70), "—");
    private JButton button17 = button((short)20, (short)790, (short)240, (short)50, new Font("Arial", Font.BOLD, 32), "text-left");
    private JButton button18 = button((short)280, (short)790, (short)240, (short)50, new Font("Arial", Font.BOLD, 40), "center");
    private JButton button19 = button((short)540, (short)790, (short)240, (short)50, new Font("Arial", Font.BOLD, 32), "text-right");
    private JButton button20 = button((short)20, (short)870, (short)760, (short)200, new Font("Arial", Font.BOLD, 120), "EXIT");

    /**
     * Sets the base stats of the JPanel, pull all JButtons inside of a HashMap with Consumer-Function and adds them to the JPanel
     * @param width
     * @param height
     */
    protected Screen1(short width, short height){

        // Base JPanel settings
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.decode("#e8e6d3"));
        setLayout(null);

        // Create a hashmap with all button click actionEvents in lambda 
        Map<JButton, Consumer<ActionEvent>> bMap = new HashMap<>();
        bMap.put(button1, event -> setBackground(Color.decode("#de7b57")));
        bMap.put(button2, event -> setBackground(Color.decode("#67a85d")));
        bMap.put(button3, event -> setBackground(Color.decode("#6a7cad")));
        bMap.put(button4, event -> setBackground(Color.decode("#d7db9c")));
        bMap.put(button5, event -> setBackground(Color.decode("#e8e6d3")));
        bMap.put(button6, event -> setBackground(JColorChooser.showDialog(null, "Farbe wählen", null)));
        bMap.put(button7, event -> {font = "Arial";          label.setFont(new Font(font, Font.BOLD, textSize));});
        bMap.put(button8, event -> {font = "Comic Sans MS";  label.setFont(new Font(font, Font.BOLD, textSize));});
        bMap.put(button9, event -> {font = "Courier New";    label.setFont(new Font(font, Font.BOLD, textSize));});
        bMap.put(button10, event -> label.setText(text.getText()));
        bMap.put(button11, event -> label.setText(""));
        bMap.put(button12, event -> label.setForeground(Color.RED));
        bMap.put(button13, event -> label.setForeground(Color.BLUE));
        bMap.put(button14, event -> label.setForeground(Color.BLACK));
        bMap.put(button15, event -> {if (textSize < 50) textSize += 10; label.setFont(new Font(font, Font.BOLD, textSize));});
        bMap.put(button16, event -> {if (textSize > 10) textSize -= 10; label.setFont(new Font(font, Font.BOLD, textSize));});
        bMap.put(button17, event -> label.setHorizontalAlignment(SwingConstants.LEFT));
        bMap.put(button18, event -> label.setHorizontalAlignment(SwingConstants.CENTER));
        bMap.put(button19, event -> label.setHorizontalAlignment(SwingConstants.RIGHT));
        bMap.put(button20, event -> System.exit(0));

        // Add all 20 JButtons to the panel
        for ( JButton key : bMap.keySet() ) {
            add(key);
        }

        // Add text and label to the panel
        add(text);
        add(label);

        this.bMap = bMap;
    } 

    // Button blueprint for all 20 buttons
    private JButton button(short x, short y, short width, short height, Font font, String text){

        JButton button = new JButton();

        button.setBounds(x, y, width, height);
        button.setFont(font);
        button.setText(text);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusable(false);
        if (height == 200){
            button.setBackground(Color.decode("#86b6b8"));
        }
        button.addActionListener(this);

        return button;
    }

    // JTextField to put text inside of the JLabel | text limit by textLimit() method (23 characters, whitespaces included)
    private JTextField text(){

        JTextField text = new JTextField();

        text.setBounds(20, 440, 760, 30);
        text.setBackground(Color.decode("#a3dce3"));
        text.setForeground(Color.BLACK);
        text.setCaretColor(Color.RED);
        text.setSelectionColor(Color.decode("#e3a8ba"));
        text.setFont(new Font(font, Font.BOLD, 20));
        text.setText("BEST MESSAGE LMAO");

        return text;
    }

    // JLabel for the Text
    private JLabel label(){

        JLabel label = new JLabel();

        label.setBounds(20, 20, 760, 110);
        label.setForeground(textColor);
        label.setFont(new Font(font, Font.BOLD, textSize));
        label.setText(text.getText());
        label.setHorizontalAlignment(SwingConstants.CENTER);

        return label;
    }
    

    // Main paint method 
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        drawBase(g2d);
        drawText(g2d);
    }

    // Draw the base of the program 
    private void drawBase(Graphics2D g2d){

        // Label background color
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(20, 20, 760, 110);

        // Button background
        g2d.fillRect(10, 190, 780, 130);
        g2d.fillRect(10, 370, 780, 180);
        g2d.fillRect(10, 580, 780, 70);
        g2d.fillRect(10, 680, 780, 70);
        g2d.fillRect(10, 780, 780, 70);

        g2d.fillRect(10, 370, 20, 480);
        g2d.fillRect(770, 370, 20, 480);

        // Button background border
        g2d.setColor(Color.BLACK);
        g2d.drawRect(9, 189, 781, 131);
        g2d.drawRect(9, 369, 781, 481);

        for(int x = 550; x < 850; x += 100)
        g2d.drawRect(30, x, 740, 30);

        // Label around background things idk hwo to call it lol
        g2d.setColor(Color.GRAY);
        for(int x = 20; x < 760; x += 40){
            g2d.fillRect(x - 5, 15, 20, 5);
        }
        for(int x = 760; x > 20; x -= 40){
            g2d.fillRect(x + 5, 130, 20, 5);
        }
        for(int y = 100; y > 10; y -= 40){
            g2d.fillRect(15, y, 5, 20);
        }
        for(int y = 20; y < 120; y += 40){
            g2d.fillRect(780, y, 5, 20);
        }
    }

    // Draw the textes for the program
    private void drawText(Graphics2D g2d){

        g2d.setColor(Color.decode("#917b21"));
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.drawString("Change background color:", 20, 185);
        g2d.drawString("Text Settings:", 20, 365);
    }

    // Check every 10 miliseconds if the textField is over the max letter limit (23 characters, whitespaces included)
    protected void textLimit() throws InterruptedException{

        String limit = "";

        while (true){

            Thread.sleep(10);
            
            if (text.getText().length() <= 23){
                limit = text.getText();
            } else {
                text.setText(limit);
            }
        }  
        
    }

    // Execute the button click from a HashMap consumer-function
    public void actionPerformed(ActionEvent e) {

        JButton b = (JButton) e.getSource();
            
        bMap.get(b).accept(e);
    }
    
}