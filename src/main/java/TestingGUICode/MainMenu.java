
package TestingGUICode;


import java.awt.*;
import javax.swing.*;
import Triviasection.ConnectDatabase;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenu {
    
    public void mainMenu() {
        
        // ------ create object -------
        
        ConnectDatabase db = new ConnectDatabase();
        
        // create an object of log in class
        Login lg = new Login(); 
        
        // create an object of news class
        News news = new News();
        
        // create an object of checkin class
        CheckIn checkin = new CheckIn();
        
        // create an object of trivia class
        TriviaSection ts = new TriviaSection();
        
        // create an object of donations class
        Donations donate = new Donations();
        
        // create an object of points shop class
        PointsShopMenu shop = new PointsShopMenu();
        
        // ----- GUI ------
        
        // create a frame
        JFrame mainMenuF = new JFrame();
        mainMenuF.setSize(1080, 720);
        mainMenuF.setTitle("Main Menu");
        mainMenuF.setResizable(false);
        mainMenuF.setLayout(null);
        
        // -------------- panel ----------------
        JPanel upper = new JPanel();
        upper.setBounds(0, 0, 1080, 100);
        upper.setBackground(new Color(88,153,111));
        upper.setLayout(null);
        
        JPanel lower = new JPanel();
        lower.setBounds(0, 100, 1080, 620);
        lower.setLayout(null);
        
        // -------------- label --------------------
        JLabel title = new JLabel();
        title.setText("MAIN MENU");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 35)); // set font of text
        int xCoordinate = (upper.getWidth() - title.getPreferredSize().width) / 2;
        title.setBounds(xCoordinate, 0, 650, 80);
        
        JLabel title2 = new JLabel();
        title2.setText("CODE FOR NATURE");
        title2.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // set font of text
        int xCoordinate2 = (upper.getWidth() - title2.getPreferredSize().width) / 2;
        title2.setBounds(xCoordinate2, 60, 650, 20);
        
        JLabel usernameL = new JLabel();
        usernameL.setText(db.getUsername());
        usernameL.setFont(new Font("Helvetica Neue", Font.BOLD, 20)); // set font of text
        usernameL.setBounds(75, 0, 300, 100);
        
        JLabel pointsL = new JLabel();
        pointsL.setText("POINTS : " + db.getCurrentPoint()); 
        pointsL.setFont(new Font("Helvetica Neue", Font.BOLD, 20)); // set font of text
        pointsL.setBounds(920, 0, 200, 100);
        
        // -------------- button ---------------
        // trivia button
        JButton triviaBtn = new JButton("PLAY TRIVIA");
        triviaBtn.setBounds(200, 100, 300, 85);
        triviaBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 22)); // Set font
        
        triviaBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ts.playTrivia();
                mainMenuF.dispose();
            }
        });
        
        // donation button
        JButton donationsBtn = new JButton("DONATIONS");
        donationsBtn.setBounds(600, 100, 300, 85);
        donationsBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 22)); // Set font
        
        donationsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                donate.donations();
                mainMenuF.dispose();
            }
        });
        
        // check in button
        JButton checkInBtn = new JButton("CHECK IN");
        checkInBtn.setBounds(200, 250, 300, 85);
        checkInBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 22)); // Set font
        
        checkInBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkin.checkIn();
                mainMenuF.dispose();
            }
        });
        
        // points shop button
        JButton pointsShopBtn = new JButton("POINTS SHOP");
        pointsShopBtn.setBounds(600, 250, 300, 85);
        pointsShopBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 22)); // Set font
        
        pointsShopBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenuF.dispose();
                shop.pointsShopMenu();
            }
        });
        
        // news button
        JButton newsBtn = new JButton("NEWS");
        newsBtn.setBounds(400, 400, 300, 85);
        newsBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 22)); // Set font
        
        newsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                news.news();
            }
        });
        
        // log out button
        JButton logOutBtn = new JButton("LOG OUT");
        logOutBtn.setBounds(950, 550, 100, 30);
        logOutBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 13)); // Set font
        
        logOutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenuF.dispose();
                JOptionPane.showMessageDialog(null, "You have successfully log out !", "Result", JOptionPane.INFORMATION_MESSAGE);
                lg.logIn();
            }
        });
        
        
        
        // --------------- image -----------------
        
        // add image to upper panel
        ImageIcon profilePic = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/profilePic_1.png");
        JLabel proficP = new JLabel();
        proficP.setBounds(10, 0, 100, 100);
        proficP.setIcon(profilePic);
        
        // add image to lower panel
        ImageIcon image = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/bgMain.jpg");
        JLabel mainBG = new JLabel();
        mainBG.setBounds(0, 0, 1080, 620);
        mainBG.setIcon(image);
        
        // add image to button
        ImageIcon triviaBtnPic = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/triviaPic.png");
        triviaBtn.setIcon(triviaBtnPic);
        triviaBtn.setIconTextGap(10);
        

        ImageIcon donationsPic = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/donationsPic.png");
        donationsBtn.setIcon(donationsPic);
        donationsBtn.setIconTextGap(10);
        
        ImageIcon checkInPic = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/checkInPic.png");
        checkInBtn.setIcon(checkInPic);
        checkInBtn.setIconTextGap(10);
        
        ImageIcon pointsShopPic = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/pointsshop.png");
        pointsShopBtn.setIcon(pointsShopPic);
        pointsShopBtn.setIconTextGap(10);
        
        ImageIcon newsPic = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/news.png");
        newsBtn.setIcon(newsPic);
        newsBtn.setIconTextGap(10);
        
        // add to panel
        
        lower.add(triviaBtn);
        lower.add(donationsBtn);
        lower.add(checkInBtn);
        lower.add(pointsShopBtn);
        lower.add(newsBtn);
        lower.add(logOutBtn);
        lower.add(mainBG);
        upper.add(title);
        upper.add(title2);
        upper.add(proficP);
        upper.add(usernameL);
        upper.add(pointsL);
        
        
        
        // add to frame 
        mainMenuF.add(upper);
        mainMenuF.add(lower);
        
        mainMenuF.setVisible(true);
        mainMenuF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
    }
    
}
