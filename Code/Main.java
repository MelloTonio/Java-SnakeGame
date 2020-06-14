import java.awt.Color;

import javax.swing.JFrame;

public class Main {

    public static void main(String args[]){
        JFrame obj = new JFrame();  // Created a Panel named obj
        Gameplay gameplay = new Gameplay();
        obj.setBounds(10,10,905,700); // Setting window size
        obj.setResizable(false); // You cannot resize the window
        obj.setBackground(Color.DARK_GRAY); // Background color
        obj.setVisible(true); //Show the program executing 
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when the window is closed
        obj.add(gameplay);  //add the method gameplay to object
    } 

}
