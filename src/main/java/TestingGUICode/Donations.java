
package TestingGUICode;

import Triviasection.ConnectDatabase;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;

public class Donations {
    public void donations() {
        
        ConnectDatabase database = new ConnectDatabase();
        
        // create an object of main menu class
        MainMenu main = new MainMenu();
                
        JFrame donationsF = new JFrame(); // create a frame
        donationsF.setTitle("Donations"); // sets title of frame
        donationsF.setSize(1040,612); // set the x-dimension and y-dimension of frame
        donationsF.setLayout(null);
        
        
        // ------ panel ------
        // create a left panel ( for image )
        JPanel left = new JPanel();
        left.setBounds(0, 0, 612, 612);
        left.setBackground(new Color(255,255,255));
        left.setLayout(new BorderLayout());
        
        // create a right panel ( for donations )
        JPanel right = new JPanel();
        right.setBounds(612, 0, 428, 612);
        right.setBackground(new Color(183, 244, 216));
        right.setLayout(null);
        
        // ---- jlabel ------

        JLabel title = new JLabel();
        title.setText("Donations");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 25)); // set font of text
        // Get the preferred size of the title label
        Dimension size = title.getPreferredSize();
        // Calculate the X-coordinate to center the label horizontally
        int xCoordinate = (428 - size.width) / 2;
        title.setBounds(xCoordinate, 20, 520, 50);
        
        JLabel donationsA = new JLabel();
        donationsA.setText("Enter donations amount $ : ");
        donationsA.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // set font of text
        donationsA.setBounds(50, 140, 260, 30);
        
        JLabel NGOLabel = new JLabel();
        NGOLabel.setText("Choose NGO : ");
        NGOLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // set font of text
        NGOLabel.setBounds(50, 210, 260, 30);
        
        // --- text field ----
        JTextField donationsTF = new JTextField();
        donationsTF.setBounds(50, 170, 260, 30);
        donationsTF.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        donationsTF.setBackground(new Color(203, 255, 236)); // You can customize the color
        
        // --- combo box ----
        String[] NGO = {"World Wildlife Fund (WWF)", "UNHCR", "Save the Children", "Oxfam International", "International Rescue Committee", "Catholic Relief Services", "CARE International","Rainforest Alliance", "Friends of the Earth", "The Nature Conservancy", "Evergreen Action"} ;
        JComboBox ngoList = new JComboBox(NGO);
        ngoList.setBounds(50,240,260,30);
        
        
        
        // --- button ----
        JButton donateB = new JButton("DONATE");
        donateB.setBounds(50, 350, 100, 25);
        donateB.setFont(new Font("Helvetica Neue", Font.BOLD, 12)); // Set font
        
        donateB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                
                int points = database.getCurrentPoint();
                int XP = database.getXP();
                String donationAmountStr = donationsTF.getText();
                
                if(donationAmountStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You did not enter the donations amount.", "Result", JOptionPane.ERROR_MESSAGE);
                } else {
                    String NGOName = (String) ngoList.getSelectedItem();
                int donationAmount = Integer.parseInt(donationAmountStr);
                // convert the donation to points
                int pointsAwarded = donationAmount * 10;
                points += pointsAwarded;
                XP += pointsAwarded;
                database.updateCurrentPoint(points);
                database.updateXP(XP);
                // calculate the amount of donation money goes to the mentioned NGO
                double amount = donationAmount * 0.9;
                // write to the donation.txt file
                try{
            
                    PrintWriter wr = new PrintWriter(new FileOutputStream("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/TestingGUICode/Donations.txt",true));
        
                    wr.printf("%s has donated $%.2f to %s\n", database.getUsername(), amount, NGOName);
        
                    wr.close();
                } catch (IOException ee) {
                    System.out.println("Fix this error now!!!!");
                }
                JOptionPane.showMessageDialog(null, "You have donated successfully.", "Result", JOptionPane.INFORMATION_MESSAGE);
                }
                
                
            }
        });
        
        JButton mainMenuB = new JButton("BACK TO MAIN MENU");
        mainMenuB.setBounds(150, 350, 200, 25);
        mainMenuB.setFont(new Font("Helvetica Neue", Font.BOLD, 12)); // Set font
        
        mainMenuB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                donationsF.dispose();
                main.mainMenu();
            }
        });
        
        // add image
        // insert image to left panel
        ImageIcon Donationsimage = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/donationsPic3.png");
        JLabel DonationsL = new JLabel();
        DonationsL.setIcon(Donationsimage);
        
        // add to panel
        left.add(DonationsL);
        right.add(title);
        right.add(donationsA);
        right.add(donationsTF);
        right.add(NGOLabel);
        right.add(ngoList);
        right.add(donateB);
        right.add(mainMenuB);
        
        // add to frame
        donationsF.add(left);
        donationsF.add(right);
        
        donationsF.setVisible(true); // make frame visible
        donationsF.setResizable(false); // prevent frame from being resized
        donationsF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}
