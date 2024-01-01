
package TestingGUICode;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Instructions {
    
    public void instructions() {
        
        // create an object of main menu class
        MainMenu mainMenu = new MainMenu();
        
        JFrame insFrame = new JFrame();
        insFrame.setSize(650, 720);
        insFrame.setTitle("Instructions");
        insFrame.setResizable(false);
        insFrame.setLayout(null);
        insFrame.getContentPane().setBackground(new Color(183, 244, 216)); 
        
        // create a label
        JLabel title = new JLabel();
        title.setText("INSTRUCTIONS");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 35)); // set font of text
        title.setBounds(40, 30, 650, 50);
        
                // Paragraph text area
        JTextArea instructionTextArea = new JTextArea();
        instructionTextArea.setText(
                "Welcome to the daily trivia challenge! \n" +
"Here are some guidelines to make the most of your experience:\n" +
"\n" +
"\n" +
"1. You have two attempts to answer each quiz question. \n" +
"   For the first correct attempt, you'll earn 2 marks, \n" +
"   while the second correct attempt grants 1 mark.\n" +
"\n" +
"2. It's essential to answer the quiz daily for ten consecutive days \n" +
"   to maximize your participation.\n" +
"\n" +
"3. Feel free to explore the news section to enrich your knowledge \n" +
"   before attempting the quiz.\n" +
"\n" +
"4. Accumulated marks can be redeemed for exciting rewards \n" +
"   available at our Point Shop!\n" +
"\n" +
"5. Remember to check in before diving into the quiz.\n" +
"\n" +
"\n" +
"Best of luck in your quiz endeavors! \n" +
"We wish you an enjoyable and rewarding journey! :)"
        );
        instructionTextArea.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        instructionTextArea.setLineWrap(true);
        instructionTextArea.setWrapStyleWord(true);
        instructionTextArea.setBounds(40, 100, 640, 510);
        instructionTextArea.setBackground(insFrame.getContentPane().getBackground()); // Set background color
        instructionTextArea.setEditable(false); // Make it non-editable
        
        // add button
        JButton insB = new JButton("I UNDERSTAND");
        int xCoordinate = (insFrame.getWidth() - 200) / 2;
        insB.setBounds(xCoordinate, 630, 200, 35);
        insB.setFont(new Font("Helvetica Neue", Font.BOLD, 22)); // Set font
        
        insB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenu.mainMenu();
                insFrame.dispose();
            }
        });
        
        // add to frame
        insFrame.add(title);
        insFrame.add(instructionTextArea);
        insFrame.add(insB);
        
        insFrame.setVisible(true);
        insFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}
