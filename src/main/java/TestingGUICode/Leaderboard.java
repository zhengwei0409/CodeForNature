
package TestingGUICode;

import Triviasection.ConnectDatabase;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Leaderboard {
    
    public Leaderboard() {
        
        // -------- connecting to database --------
        ConnectDatabase database = new ConnectDatabase();
        
        // create an object of main menu class
        MainMenu main = new MainMenu();
        
        database.getUserXP();
        
        ArrayList<Integer> userXP = database.getUserXPList();
        ArrayList<String> usernameList = database.getUserName();
        
        // use bubble sort
        for (int i = 0;i < userXP.size()-1;i++) {
            for(int j = 0; j < userXP.size() - i - 1; j++) {
                if(userXP.get(j) < userXP.get(j+1)) {
                    // Swap userXP
                    int tempXP = userXP.get(j);
                    userXP.set(j, userXP.get(j + 1));
                    userXP.set(j + 1, tempXP);

                    // Swap usernames
                    String tempUsername = usernameList.get(j);
                    usernameList.set(j, usernameList.get(j + 1));
                    usernameList.set(j + 1, tempUsername);
                }
            }
        }
        
        // ----------- GUI --------------
        JFrame leaderboardF = new JFrame();
        leaderboardF.setSize(650, 720);
        leaderboardF.setTitle("Leaderboard");
        leaderboardF.setLayout(null);
        
        // ------ panel ------
        // create a top panel
        JPanel top = new JPanel();
        top.setBounds(0, 0, 650, 100);
        top.setBackground(new Color(88,153,111));
        top.setLayout(null);
        
        // create a bottom panel
        JPanel bottom = new JPanel();
        bottom.setBounds(0, 100, 650, 620);
        bottom.setBackground(new Color(183, 244, 216));
        bottom.setLayout(null);
        
        
        // ------------- label -----------------
        JLabel title = new JLabel();
        title.setText("LEADERBOARD");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 25)); // set font of text
        title.setForeground(Color.WHITE);
        title.setBounds(50, 10, 300, 80);
        
        JLabel rank = new JLabel();
        rank.setText("RANKING");
        rank.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // set font of text
        rank.setForeground(Color.BLACK);
        rank.setBounds(50, 10, 300, 80);
        
        JLabel nameL = new JLabel();
        nameL.setText("USERNAME");
        nameL.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // set font of text
        nameL.setForeground(Color.BLACK);
        nameL.setBounds(200, 10, 300, 80);
        
        JLabel XPL = new JLabel();
        XPL.setText("XP");
        XPL.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // set font of text
        XPL.setForeground(Color.BLACK);
        XPL.setBounds(450, 10, 300, 80);
        
        int gap = 30;
        
        for(int i =0; i < userXP.size();i++) {
            
            
            JLabel userRank = new JLabel();
            userRank.setText(Integer.toString(i+1));
            userRank.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // set font of text
            userRank.setForeground(Color.BLACK);
            userRank.setBounds(50, 10+gap, 300, 80);
            
            JLabel userName = new JLabel();
            userName.setText(usernameList.get(i));
            userName.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // set font of text
            userName.setForeground(Color.BLACK);
            userName.setBounds(200, 10+gap, 300, 80);
            
            JLabel usersXP = new JLabel();
            usersXP.setText(Integer.toString(userXP.get(i)));
            usersXP.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // set font of text
            usersXP.setForeground(Color.BLACK);
            usersXP.setBounds(450, 10+gap, 300, 80);
            
            bottom.add(userRank);
            bottom.add(userName);
            bottom.add(usersXP);
            
            gap += 30;
        }
        
        // ------- button -----------
        JButton mainMenuB = new JButton("BACK TO MAIN MENU");
        mainMenuB.setBounds(190, 530, 280, 35);
        mainMenuB.setFont(new Font("Helvetica Neue", Font.BOLD, 12)); // Set font
        
        mainMenuB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                leaderboardF.dispose();
                main.mainMenu();
            }
        });
        
        
        // insert image to top panel
        ImageIcon leaderboardImage = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/leaderboardIcons.png");
        JLabel iconL = new JLabel();
        iconL.setBounds(250, 10, 80, 80);
        iconL.setIcon(leaderboardImage);
        
        // add to panel (left)
        top.add(title);
        top.add(iconL);
        bottom.add(rank);
        bottom.add(nameL);
        bottom.add(XPL);
        bottom.add(mainMenuB);
        
        // add to frame
        leaderboardF.add(top);
        leaderboardF.add(bottom);
        
        leaderboardF.setVisible(true); // make frame visible
        leaderboardF.setResizable(false); // prevent frame from being resized
        leaderboardF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        
        
    }
    
}
