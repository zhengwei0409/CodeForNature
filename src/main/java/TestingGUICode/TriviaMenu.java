
package TestingGUICode;

import Triviasection.ConnectDatabase;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TriviaMenu {
    public void triviaMenu() {
        
        // -------- connecting to database --------
        ConnectDatabase database = new ConnectDatabase();
        
        
        // getting the date of registration and current date
        LocalDate registrationDate = database.getResgistrationDate();
        LocalDate currentDate = LocalDate.now();
        
        // compute the different in days
        int currentQuestionIndex = (int) ChronoUnit.DAYS.between(registrationDate, currentDate);
        int i = currentQuestionIndex;
        
        // make the array global
        String[] questionAnsweredStr;
        int[] questionAnsweredInt = new int[0];
        
        if(database.getQuestionAnswered() != null) {
            // get the question number 
            questionAnsweredStr = database.getQuestionAnswered().split(",");
        
            // convert the string array to int array
            questionAnsweredInt = new int[questionAnsweredStr.length];
        
            for(int j = 0; j < questionAnsweredStr.length; j++) {
                questionAnsweredInt[j] = Integer.parseInt(questionAnsweredStr[j]);
            }
            
            // sort the array
            Arrays.sort(questionAnsweredInt);
        }
        
        // find the answered question & unanswer question
        int[] answeredQuestion = new int[10];
 
        for(int j = 0; j < questionAnsweredInt.length; j++) {
            int questionNumber = questionAnsweredInt[j];
            answeredQuestion[questionNumber]++;
        }

        
        // convert date to string
        String dateStr = currentDate.toString();
        
        // create an object of main menu class
        MainMenu main = new MainMenu();
        
        // create an object of trivia section class
        TriviaSection trivia = new TriviaSection();
        
        // ----- GUI -----
        
        JFrame triviaMenuF = new JFrame();
        triviaMenuF.setSize(1080, 720);
        triviaMenuF.setTitle("Daily Trivia");
        triviaMenuF.setResizable(false);
        triviaMenuF.setLayout(null);
        
        // ----- panel -------
        JPanel top = new JPanel();
        top.setBounds(0, 0, 1080, 120);
        top.setBackground(new Color(241, 171, 185));
        top.setLayout(null);
        
        JPanel bottomLeft = new JPanel();
        bottomLeft.setBounds(0, 120, 420, 600);
        bottomLeft.setBackground(new Color(83, 145, 126));
        bottomLeft.setLayout(null);
        
        JPanel bottomRight = new JPanel();
        bottomRight.setBounds(420, 120, 660, 600);
        bottomRight.setBackground(new Color(232, 247, 238));
        bottomRight.setLayout(null);
        
        // ----- label -------
        JLabel title = new JLabel();
        title.setText("WELCOME");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 45)); // set font of text
        title.setBounds(30, 20, 270, 60);
        
        JLabel subTitle = new JLabel();
        subTitle.setText("CODE FOR NATURE");
        subTitle.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // set font of text
        subTitle.setBounds(30, 80, 270, 30);
        
        JLabel dateL = new JLabel();
        dateL.setText("DATE     " + dateStr);
        dateL.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // set font of text
        dateL.setBounds(880, 80, 270, 30);
        
        JLabel IdL = new JLabel();
        IdL.setText("ID  :  " + database.getID());
        IdL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        IdL.setBounds(30, 40, 400, 30);
        
        JLabel usernameL = new JLabel();
        usernameL.setText("USERNAME  :  " + database.getUsername());
        usernameL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        usernameL.setBounds(30, 100, 400, 30);
        
        JLabel totalQuestionL = new JLabel();
        totalQuestionL.setText("Total Questions  :  10 Qs");
        totalQuestionL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        totalQuestionL.setBounds(30, 160, 400, 30);
        
        JLabel pointsL = new JLabel();
        pointsL.setText("Points  :  " + database.getCurrentPoint());
        pointsL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        pointsL.setBounds(30, 220, 400, 30);
        
        // ------- button --------
        
        // question 1 button
        JButton question1Btn = new JButton("1");
        question1Btn.setBounds(50, 30, 100, 95);
        question1Btn.setFont(new Font("Helvetica Neue", Font.BOLD, 24)); // Set font
        if(i >= 0) {
            if(answeredQuestion[0] == 0) {

                question1Btn.setForeground(Color.red);
                
                question1Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithAffectingPoints(0);
                    }
                });
            } else {
                question1Btn.setForeground(Color.green);
                
                question1Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithoutAffectingPoints(0);
                    }
                });
            }
        } else {
            question1Btn.setEnabled(false);
        }
        
        
        
        // question 2 button
        JButton question2Btn = new JButton("2");
        question2Btn.setBounds(250, 30, 100, 95);
        question2Btn.setFont(new Font("Helvetica Neue", Font.BOLD, 24)); // Set font
        
        if(i >= 1) {
            if(answeredQuestion[1] == 0) {

                question2Btn.setForeground(Color.red);
                
                question2Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithAffectingPoints(1);
                    }
                });
            } else {
                question2Btn.setForeground(Color.green);
                
                question2Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithoutAffectingPoints(1);
                    }
                });
            }
        } else {
            question2Btn.setEnabled(false);
        }
        
        // question 3 button
        JButton question3Btn = new JButton("3");
        question3Btn.setBounds(450, 30, 100, 95);
        question3Btn.setFont(new Font("Helvetica Neue", Font.BOLD, 24)); // Set font

        if(i >= 2) {
            if(answeredQuestion[2] == 0) {

                question3Btn.setForeground(Color.red);
                
                question3Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithAffectingPoints(2);
                    }
                });
            } else {
                question3Btn.setForeground(Color.green);
                
                question3Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithoutAffectingPoints(2);
                    }
                });
            }
        } else {
            question3Btn.setEnabled(false);
        }
        
        // question 4 button
        JButton question4Btn = new JButton("4");
        question4Btn.setBounds(50, 160, 100, 95);
        question4Btn.setFont(new Font("Helvetica Neue", Font.BOLD, 24)); // Set font
        
        if(i >= 3) {
            if(answeredQuestion[3] == 0) {

                question4Btn.setForeground(Color.red);
                
                question4Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithAffectingPoints(3);
                    }
                });
            } else {
                question4Btn.setForeground(Color.green);
                
                question4Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithoutAffectingPoints(3);
                    }
                });
            }
        } else {
            question4Btn.setEnabled(false);
        }
        
        // question 5 button
        JButton question5Btn = new JButton("5");
        question5Btn.setBounds(250, 160, 100, 95);
        question5Btn.setFont(new Font("Helvetica Neue", Font.BOLD, 24)); // Set font
        
        if(i >= 4) {
            if(answeredQuestion[4] == 0) {

                question5Btn.setForeground(Color.red);
                
                question5Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithAffectingPoints(4);
                    }
                });
            } else {
                question5Btn.setForeground(Color.green);
                
                question5Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithoutAffectingPoints(4);
                    }
                });
            }
        } else {
            question5Btn.setEnabled(false);
        }
        
        // question 6 button
        JButton question6Btn = new JButton("6");
        question6Btn.setBounds(450, 160, 100, 95);
        question6Btn.setFont(new Font("Helvetica Neue", Font.BOLD, 24)); // Set font
        
        if(i >= 5) {
            if(answeredQuestion[5] == 0) {

                question6Btn.setForeground(Color.red);
                
                question6Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithAffectingPoints(5);
                    }
                });
            } else {
                question6Btn.setForeground(Color.green);
                
                question6Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithoutAffectingPoints(5);
                    }
                });
            }
        } else {
            question6Btn.setEnabled(false);
        }
        
        // question 7 button
        JButton question7Btn = new JButton("7");
        question7Btn.setBounds(50, 290, 100, 95);
        question7Btn.setFont(new Font("Helvetica Neue", Font.BOLD, 24)); // Set font
        
        if(i >= 6) {
            if(answeredQuestion[6] == 0) {

                question7Btn.setForeground(Color.red);
                
                question7Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithAffectingPoints(6);
                    }
                });
            } else {
                question7Btn.setForeground(Color.green);
                
                question7Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithoutAffectingPoints(6);
                    }
                });
            }
        } else {
            question7Btn.setEnabled(false);
        }
        
        // question 8 button
        JButton question8Btn = new JButton("8");
        question8Btn.setBounds(250, 290, 100, 95);
        question8Btn.setFont(new Font("Helvetica Neue", Font.BOLD, 24)); // Set font
        
        if(i >= 7) {
            if(answeredQuestion[7] == 0) {

                question8Btn.setForeground(Color.red);
                
                question8Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithAffectingPoints(7);
                    }
                });
            } else {
                question8Btn.setForeground(Color.green);
                
                question8Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithoutAffectingPoints(7);
                    }
                });
            }
        } else {
            question8Btn.setEnabled(false);
        }
        
        // question 9 button
        JButton question9Btn = new JButton("9");
        question9Btn.setBounds(450, 290, 100, 95);
        question9Btn.setFont(new Font("Helvetica Neue", Font.BOLD, 24)); // Set font
        
        if(i >= 8) {
            if(answeredQuestion[8] == 0) {

                question9Btn.setForeground(Color.red);
                
                question9Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithAffectingPoints(8);
                    }
                });
            } else {
                question9Btn.setForeground(Color.green);
                
                question9Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithoutAffectingPoints(8);
                    }
                });
            }
        } else {
            question9Btn.setEnabled(false);
        }
        
        // question 10 button
        JButton question10Btn = new JButton("10");
        question10Btn.setBounds(50, 420, 100, 95);
        question10Btn.setFont(new Font("Helvetica Neue", Font.BOLD, 24)); // Set font
        
        if(i >= 9) {
            if(answeredQuestion[9] == 0) {

                question10Btn.setForeground(Color.red);
                
                question10Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                       triviaMenuF.dispose();
                       trivia.playTriviaWithAffectingPoints(9);
                    }
                });
            } else {
                question10Btn.setForeground(Color.green);
                
                question10Btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triviaMenuF.dispose();
                        trivia.playTriviaWithoutAffectingPoints(9);
                    }
                });
            }
        } else {
            question10Btn.setEnabled(false);
        }
        
        
        JButton backBtn = new JButton("BACK TO MAIN MENU");
        backBtn.setBounds(430, 530, 200, 30);
        backBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 13)); // Set font
        
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                triviaMenuF.dispose();
                main.mainMenu();
            }
        });
        
        // insert image to top panel
        ImageIcon welcomeImage = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/welcomePic.png");
        JLabel welcomeL = new JLabel();
        welcomeL.setBounds(270, 10, 100, 100);
        welcomeL.setIcon(welcomeImage);
        
        // add to panel
        top.add(title);
        top.add(subTitle);
        top.add(welcomeL);
        top.add(dateL);
        bottomLeft.add(IdL);
        bottomLeft.add(usernameL);
        bottomLeft.add(totalQuestionL);
        bottomLeft.add(pointsL);
        bottomRight.add(question1Btn);
        bottomRight.add(question2Btn);
        bottomRight.add(question3Btn);
        bottomRight.add(question4Btn);
        bottomRight.add(question5Btn);
        bottomRight.add(question6Btn);
        bottomRight.add(question7Btn);
        bottomRight.add(question8Btn);
        bottomRight.add(question9Btn);
        bottomRight.add(question10Btn);
        bottomRight.add(backBtn);
        
        // add to frame
        triviaMenuF.add(top);
        triviaMenuF.add(bottomLeft);
        triviaMenuF.add(bottomRight);
        
        triviaMenuF.setVisible(true);
        triviaMenuF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}
