
package TestingGUICode;

import Triviasection.ConnectDatabase;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PointsShopMenu {
    public void pointsShopMenu() {
        
        MainMenu main = new MainMenu(); 
        
        // create a frame
        JFrame pointsShopMenuF = new JFrame();
        pointsShopMenuF.setSize(515, 470);
        pointsShopMenuF.setTitle("Ponts Shop");
        pointsShopMenuF.setResizable(false);
        pointsShopMenuF.setLayout(null);
        
        // -------------- panel ----------------
        JPanel upper = new JPanel();
        upper.setBounds(0, 0, 515, 100);
        upper.setBackground(new Color(88,153,111));
        upper.setLayout(null);
        
        JPanel lower = new JPanel();
        lower.setBounds(0, 100, 515, 370);
        lower.setBackground(new Color(255,255,255));
        lower.setLayout(null);
        
        // ------------- label -----------------
        JLabel title = new JLabel();
        title.setText("WELCOME TO POINTS SHOP");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 20)); // set font of text
        title.setForeground(Color.WHITE);
        title.setBounds(10, 10, 300, 80);

        
        // ------------ button ----------------
        // merch button
        JButton pointsShopBtn = new JButton("PURCHASE MERCHANDISE");
        pointsShopBtn.setBounds(300, 220, 200, 35);
        pointsShopBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 13)); // Set font
        
        pointsShopBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pointsShopMenuF.dispose();
                buyMerchandise();
            }
        });
        
        // plant a tree button
        JButton plantBtn = new JButton("PLANT TREE");
        plantBtn.setBounds(50, 270, 150, 35);
        plantBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 13)); // Set font
        
        plantBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pointsShopMenuF.dispose();
                plantTree();
            }
        });
        
        // back button
        JButton backBtn = new JButton("BACK TO MENU");
        backBtn.setBounds(300, 290, 150, 35);
        backBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 13)); // Set font
        
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pointsShopMenuF.dispose();
                main.mainMenu();
            }
        });
        
        // add image
        ImageIcon iconPic = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/shopingIcon.png");
        JLabel iconL = new JLabel();
        iconL.setBounds(310, 0, 100, 100);
        iconL.setIcon(iconPic);
        
        ImageIcon BGPic = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/pointsShopBG.jpg");
        JLabel backgroundL = new JLabel();
        backgroundL.setBounds(0, 0, 515, 370);
        backgroundL.setIcon(BGPic);
        
        // add to panel
        upper.add(title);
        upper.add(iconL);
        lower.add(pointsShopBtn);
        lower.add(plantBtn);
        lower.add(backBtn);
        lower.add(backgroundL);
        
        // add to frame
        pointsShopMenuF.add(upper);
        pointsShopMenuF.add(lower);
        
        pointsShopMenuF.setVisible(true);
        pointsShopMenuF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
    public void buyMerchandise() {
        // create a frame
        JFrame merchandiseF = new JFrame();
        merchandiseF.setSize(1080, 820);
        merchandiseF.setTitle("Buy Merchandise");
        merchandiseF.setResizable(false);
        merchandiseF.setLayout(null);
        
        // -------------- panel ----------------
        JPanel upper = new JPanel();
        upper.setBounds(0, 0, 1080, 100);
        upper.setBackground(new Color(88,153,111));
        upper.setLayout(null);
        
        JPanel middle = new JPanel();
        middle.setBounds(0, 100, 1080, 400);
        middle.setBackground(new Color(183, 244, 216));
        middle.setLayout(null);
        
        JPanel lower = new JPanel();
        lower.setBounds(0, 500, 1080, 300);
        lower.setBackground(new Color(183, 244, 216));
        lower.setLayout(null);
        
        // -------------- label ----------------
        JLabel title = new JLabel();
        title.setText("MERCHANDISE");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 35)); // set font of text
        int xCoordinate = (upper.getWidth() - title.getPreferredSize().width) / 2;
        title.setBounds(xCoordinate, 10, 1080, 80);
        
        JLabel merch1 = new JLabel();
        merch1.setText("MERCH-1 (5 POINTS)");
        merch1.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        merch1.setBounds(170, 300, 200, 50);
        
        JLabel merch2 = new JLabel();
        merch2.setText("MERCH-2 (10 POINTS)");
        merch2.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        merch2.setBounds(450, 300, 200, 50);
        
        JLabel merch3 = new JLabel();
        merch3.setText("MERCH-3 (15 POINTS)");
        merch3.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        merch3.setBounds(770, 300, 200, 50);
        
        JLabel merchIDL = new JLabel();
        merchIDL.setText("MERCHANDISE ID :");
        merchIDL.setFont(new Font("Helvetica Neue", Font.BOLD, 20)); // set font of text
        merchIDL.setBounds(250, 20, 300, 50);
        
        JLabel merchQuantityL = new JLabel();
        merchQuantityL.setText("NUMBER OF PURCHASE :");
        merchQuantityL.setFont(new Font("Helvetica Neue", Font.BOLD, 20)); // set font of text
        merchQuantityL.setBounds(250, 90, 300, 50);
        
        JLabel addressL = new JLabel();
        addressL.setText("DELIVERY ADDRESS :");
        addressL.setFont(new Font("Helvetica Neue", Font.BOLD, 20)); // set font of text
        addressL.setBounds(250, 160, 300, 50);
        
        // --------- text fields ------------
        JTextField addressTF = new JTextField();
        addressTF.setBounds(550, 170, 260, 30);
        addressTF.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        
        
        // -------- combo box ---------
        String[] merch = {"Merch-1", "Merch-2", "Merch-3"} ;
        JComboBox merchList = new JComboBox(merch);
        merchList.setBounds(550,20,260,50);
        
        String[] quantity = {"1", "2", "3","4","5","6","7"} ;
        JComboBox quantityCB = new JComboBox(quantity);
        quantityCB.setBounds(550,90,260,50);
        
        // --------- button -----------
        JButton orderB = new JButton("PLACE ORDER");
        orderB.setBounds(350, 230, 150, 35);
        orderB.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // Set font
        
        orderB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // connect to database
                ConnectDatabase database = new ConnectDatabase();
        
                // get user's username from database
                String username = database.getUsername();
        
                // get points from database
                int points = database.getCurrentPoint();
                
                try {
            PrintWriter merchandiseWriter = new PrintWriter(new FileWriter("MerchandiseOrder.txt", true));


            if (!addressTF.getText().isEmpty()) {

                String merchandiseID = (String) merchList.getSelectedItem();
                String quantityStr = (String) quantityCB.getSelectedItem();
                int quantity = Integer.parseInt(quantityStr);
                String address = addressTF.getText();
                
                if(merchandiseID.equalsIgnoreCase("Merch-1")) {
                    points -= 5 * quantity;
                } else if (merchandiseID.equalsIgnoreCase("Merch-2")) {
                    points -= 10 * quantity;
                } else {
                    points -= 15 * quantity;
                }
                
                // if the user dont have enough point
                if(points < 0) {
                    JOptionPane.showMessageDialog(null, "You don't have enough point!", "Result", JOptionPane.ERROR_MESSAGE);
                    merchandiseF.dispose();
                    pointsShopMenu();
                    return; // exit the method
                }

                // Writing merchandise order to the text file
                merchandiseWriter.println(username + " orders " + quantity + " " + merchandiseID + " to " + address);
                merchandiseWriter.close();
                
                JOptionPane.showMessageDialog(null, "Order placed successfully in MerchandiseOrder.txt.", "Result", JOptionPane.INFORMATION_MESSAGE);
                
                // update the point in database
                database.updateCurrentPoint(points);
            } else {
                JOptionPane.showMessageDialog(null, "Address is empty!", "Result", JOptionPane.ERROR_MESSAGE);
            }

            

        } catch (IOException ee) {
            ee.printStackTrace();
        }
            }
        });
        
        JButton backB = new JButton("BACK");
        backB.setBounds(520, 230, 150, 35);
        backB.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // Set font
        
        backB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                merchandiseF.dispose();
                pointsShopMenu();
            }
        });
        
        // add image
        ImageIcon merch1Pic = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/merch1.jpg");
        JLabel merch1L = new JLabel();
        merch1L.setBounds(180, 100, 160, 120);
        merch1L.setIcon(merch1Pic);
        
        ImageIcon merch2Pic = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/merch2.jpg");
        JLabel merch2L = new JLabel();
        merch2L.setBounds(480, 50, 102, 170);
        merch2L.setIcon(merch2Pic);
        
        ImageIcon merch3Pic = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/merch3.jpg");
        JLabel merch3L = new JLabel();
        merch3L.setBounds(780, 35, 160, 213);
        merch3L.setIcon(merch3Pic);
        
        // add to panel
        upper.add(title);
        middle.add(merch1);
        middle.add(merch2);
        middle.add(merch3);
        middle.add(merch1L);
        middle.add(merch2L);
        middle.add(merch3L);
        lower.add(merchIDL);
        lower.add(merchQuantityL);
        lower.add(addressL);
        lower.add(merchList);
        lower.add(quantityCB);
        lower.add(addressTF);
        lower.add(orderB);
        lower.add(backB);
        
        // add to frame
        merchandiseF.add(upper);
        merchandiseF.add(middle);
        merchandiseF.add(lower);
        
        merchandiseF.setVisible(true);
        merchandiseF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
    
    public void plantTree() {
        // create a frame
        JFrame planTreeF = new JFrame();
        planTreeF.setSize(720, 520);
        planTreeF.setTitle("Plant a Tree");
        planTreeF.setResizable(false);
        planTreeF.setLayout(null);
        
        // -------------- panel ----------------
        JPanel upper = new JPanel();
        upper.setBounds(0, 0, 720, 100);
        upper.setBackground(new Color(88,153,111));
        upper.setLayout(null);
        
        JPanel lower = new JPanel();
        lower.setBounds(0, 100, 720, 420);
        lower.setBackground(new Color(183, 244, 216));
        lower.setLayout(null);
        
        // -------------- label ----------------
        JLabel title = new JLabel();
        title.setText("PLANT A TREE");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 35)); // set font of text
        int xCoordinate = (upper.getWidth() - title.getPreferredSize().width) / 2;
        title.setBounds(xCoordinate, 10, 720, 80);
        
        JLabel treePiceL = new JLabel();
        treePiceL.setText("PLANT TREE (10 POINTS)");
        treePiceL.setFont(new Font("Helvetica Neue", Font.BOLD, 16)); // set font of text
        treePiceL.setBounds(265, 180, 300, 50);
        
        JLabel treeNameL = new JLabel();
        treeNameL.setText("NAME OF THE TREE");
        treeNameL.setFont(new Font("Helvetica Neue", Font.BOLD, 16)); // set font of text
        treeNameL.setBounds(120, 240, 300, 50);
        
        // --------- text fields ------------
        JTextField nameTF = new JTextField();
        nameTF.setBounds(290, 250, 260, 30);
        nameTF.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        
        // --------- button -----------
        JButton plantB = new JButton("PLANT TREE");
        plantB.setBounds(190, 310, 150, 35);
        plantB.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // Set font
        
        plantB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // connect to database
        ConnectDatabase database = new ConnectDatabase();
        
        // get user's username from database
        String username = database.getUsername();
        
        // get points from database
        int points = database.getCurrentPoint();
        
        try {
            PrintWriter treeWriter = new PrintWriter(new FileWriter("TreePlantOrder.txt",true));

            if (!nameTF.getText().isEmpty()) {

                String treeName = nameTF.getText();
                System.out.println(treeName);
                
                points -= 10;
                
                // if the user dont have enough point
                if(points < 0) {
                    JOptionPane.showMessageDialog(null, "You don't have enough point!", "Result", JOptionPane.ERROR_MESSAGE);
                    planTreeF.dispose();
                    pointsShopMenu();
                    return; // exit the method
                }
                
                // Writing tree planting order to the text file
                treeWriter.println(username + " plants a tree with the name \"" + treeName + "\"");
                treeWriter.close();

                JOptionPane.showMessageDialog(null, "Tree planting order placed successfully in TreePlantOrder.txt.", "Result", JOptionPane.INFORMATION_MESSAGE);
                
                // update the point in database
                database.updateCurrentPoint(points);
                
            } else {
                JOptionPane.showMessageDialog(null, "Plant's name is empty!", "Result", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (IOException ee) {
            ee.printStackTrace();
        }
            }
        });
        
        JButton backB = new JButton("BACK");
        backB.setBounds(370, 310, 150, 35);
        backB.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // Set font
        
        backB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                planTreeF.dispose();
                pointsShopMenu();
            }
        });
        
        // add image
        ImageIcon treePic = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/treePic.png");
        JLabel treeL = new JLabel();
        treeL.setBounds(290, 30, 150, 150);
        treeL.setIcon(treePic);

        // add to panel
        upper.add(title);
        lower.add(treeL);
        lower.add(treePiceL);
        lower.add(treeNameL);
        lower.add(nameTF);
        lower.add(plantB);
        lower.add(backB);
        
        // add to frame
        planTreeF.add(upper);
        planTreeF.add(lower);
                 
        planTreeF.setVisible(true);
        planTreeF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}
