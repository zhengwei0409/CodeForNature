
package TestingGUICode;

import Triviasection.ConnectDatabase;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class TriviaSection {
    
    // -------------------------------------------- method 1 ----------------------------------------------
    // ----------------------------------------------------------------------------------------------------
    
    public void playTrivia() {
        
        // -------- connecting to database --------
        ConnectDatabase database = new ConnectDatabase();
        
        // create an object of main menu class
        MainMenu main = new MainMenu();
        
        // create an object of trivia menu class
        TriviaMenu tm = new TriviaMenu();
        
        // -------- reading the text file --------
        int numberOfQuestions = 10;

        // create an array for storing question
        String[] questions = new String[numberOfQuestions];

        // create an 2D array for storing choices
        String[][] options = new String[numberOfQuestions][];

        // create an anrray for storing correct answer's index
        String[] answers = new String[numberOfQuestions];

        File triviaSample = new File("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/Triviasection/TriviaSample.txt");
        
        try {
            
            Scanner sc = new Scanner(triviaSample);

            while (sc.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {
                    // store the question into the array
                    questions[i] = sc.nextLine();

                    // skip 3 line
                    for(int j = 0; j < 3 && sc.hasNextLine(); j++) {
                        sc.nextLine();
                    }
                }
            }
            sc.close();

            Scanner sc2 = new Scanner(triviaSample);

            while (sc2.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {

                    // skip for the question line
                    sc2.nextLine();

                    // store the choices into a string
                    // eg. 4%,10%,25%,50%
                    String choice = sc2.nextLine();

                    // store the choices into the array
                    // eg. {"4%", "10%", "25%", "50%"}
                    String temporaryArr[] = choice.split(",");

                    // store the choices into 2D array
                    // eg. {
                    //      {"4%", "10%", "25%", "50%"},
                    //      {"500 million", "900 million", "1.6 billion", "5 billion"}
                    //      ...
                    //     }
                    options[i] = temporaryArr;

                    // skip 2 line
                    for(int z = 0; z < 2 && sc2.hasNextLine(); z++) {
                        sc2.nextLine();
                    }
                }
            }

            sc2.close();

            Scanner sc3 = new Scanner(triviaSample);

            while (sc3.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {

                    // skip 2 line
                    for (int j = 0; j < 2; j++) {
                        sc3.nextLine();
                    }

                    // store the answer
                    answers[i] = sc3.nextLine();
                
                    // skip 1 line
                    if(sc3.hasNextLine()) {
                        sc3.nextLine();
                    }
                }
            }

            sc3.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // -------- trivia section --------
        
        // getting the date of registration and current date
        LocalDate registrationDate = database.getResgistrationDate();
        LocalDate currentDate = LocalDate.now();
        
        // compute the different in days
        int currentQuestionIndex = (int) ChronoUnit.DAYS.between(registrationDate, currentDate);
        int i = currentQuestionIndex;
        
        // handle the case where the numbers of days is exceed 10 days
        if(i > 10) {
            JOptionPane.showMessageDialog(null, "There are no trivia for you anymore ~", "Result", JOptionPane.INFORMATION_MESSAGE); 
            // **** call the other frame 
            tm.triviaMenu();
            return;
        }
        
        // check for whether this question already being answered 
        if(alreadyAnswer(currentQuestionIndex)) {
            JOptionPane.showMessageDialog(null, "You have done today's question ~", "Result", JOptionPane.INFORMATION_MESSAGE); 
            // **** call the other frame 
            tm.triviaMenu();
            return;
        }
        
        // getting the question
        String question = questions[i];
        String [] answerOptions = options[i];
        String correctAnswer = answers[i];
        
        // storing the answer
        String A,B,C,D;
        A = answerOptions[0];
        B = answerOptions[1];
        C = answerOptions[2];
        D = answerOptions[3];
        
        int attempts = 1;
        String dateStr = currentDate.toString();
        
        // ----------- GUI --------------
        
        JFrame playTriviaF = new JFrame();
        playTriviaF.setSize(1080, 720);
        playTriviaF.setTitle("Daily Trivia");
        playTriviaF.setResizable(false);
        playTriviaF.setLayout(null);
        
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
        
        JLabel currentQuestionL = new JLabel();
        currentQuestionL.setText("Current Question No  :  " + (i+1));
        currentQuestionL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        currentQuestionL.setBounds(30, 220, 400, 30);
        
        JLabel pointsL = new JLabel();
        pointsL.setText("Points  :  " + database.getCurrentPoint());
        pointsL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        pointsL.setBounds(30, 280, 400, 30);
        
        JLabel answerL = new JLabel();
        answerL.setText("Choose Your Answer : ");
        answerL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        answerL.setBounds(30, 360, 600, 30);
        
        // ----- textArea -----
                        
        JTextArea questionsTextArea = new JTextArea();
        questionsTextArea.setText("Day " +  (i+1)  + " Trivia (Attempt #" + attempts + ")" + "\n" + question);
        questionsTextArea.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        questionsTextArea.setLineWrap(true);
        questionsTextArea.setWrapStyleWord(true);
        questionsTextArea.setBounds(30, 40, 600, 100);
        questionsTextArea.setBackground(new Color(232, 247, 238)); // Set background color
        questionsTextArea.setEditable(false); // Make it non-editable
        
        JTextArea choiceTextArea = new JTextArea();
        choiceTextArea.setText("[A] " + A + "\n\n" + "[B] " + B + "\n\n" + "[C] " + C +  "\n\n" + "[D] " + D);
        choiceTextArea.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        choiceTextArea.setLineWrap(true);
        choiceTextArea.setWrapStyleWord(true);
        choiceTextArea.setBounds(30, 150, 600, 200);
        choiceTextArea.setBackground(new Color(232, 247, 238)); // Set background color
        choiceTextArea.setEditable(false); // Make it non-editable
        
        // ---- combo box ------
        String[] choices = {"A","B","C","D"} ;
        JComboBox choicesList = new JComboBox(choices);
        choicesList.setBounds(30,400,100,30);
        
        // ---- button -----
        JButton submitBtn = new JButton("SUBMIT ANSWER");
        submitBtn.setBounds(30, 460, 160, 45);
        submitBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // Set font
        
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // define all the necessary variable
                int attempts = 1;
                int points = database.getCurrentPoint();
                int XP = database.getXP();
                String userAnswer = (String) choicesList.getSelectedItem();
                
                playTriviaF.dispose();

                if (userAnswer.equals("A") && A.equals(correctAnswer) ||
                    userAnswer.equals("B") && B.equals(correctAnswer) ||
                    userAnswer.equals("C") && C.equals(correctAnswer) ||
                    userAnswer.equals("D") && D.equals(correctAnswer)) {
                    
                    points += 2;
                    XP += 2;
                    
                    // update the point in database
                    database.updateCurrentPoint(points);
                    // update the XP in database
                    database.updateXP(XP);
                    
                    // update the answered question in database
                    if(database.getQuestionAnswered() != null) {
                        String newAnsweredQuestion = database.getQuestionAnswered() + "," + Integer.toString(currentQuestionIndex);
                        database.updateQuestionAnswered(newAnsweredQuestion);
                    } else {
                        String newAnsweredQuestion = Integer.toString(currentQuestionIndex);
                        database.updateQuestionAnswered(newAnsweredQuestion);
                    }
                    
                    JOptionPane.showMessageDialog(null, "Congratulations! You answered it correctly. \nYou have been awarded 2 point, you now have " + points + " points.", "Result", JOptionPane.INFORMATION_MESSAGE); 
                    
                    // call new frame
                    tm.triviaMenu();
                    
                } else {
                    
                    JOptionPane.showMessageDialog(null, "Whoops, that doesn’t look right, try again!", "Result", JOptionPane.INFORMATION_MESSAGE); 
                    
                    attempts++;
                    
        // -------- connecting to database --------
        ConnectDatabase database = new ConnectDatabase();
        
        // create an object of main menu class
        MainMenu main = new MainMenu();
        
        // -------- reading the text file --------
        int numberOfQuestions = 10;

        // create an array for storing question
        String[] questions = new String[numberOfQuestions];

        // create an 2D array for storing choices
        String[][] options = new String[numberOfQuestions][];

        // create an anrray for storing correct answer's index
        String[] answers = new String[numberOfQuestions];

        File triviaSample = new File("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/Triviasection/TriviaSample.txt");
        
        try {
            
            Scanner sc = new Scanner(triviaSample);

            while (sc.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {
                    // store the question into the array
                    questions[i] = sc.nextLine();

                    // skip 3 line
                    for(int j = 0; j < 3 && sc.hasNextLine(); j++) {
                        sc.nextLine();
                    }
                }
            }
            sc.close();

            Scanner sc2 = new Scanner(triviaSample);

            while (sc2.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {

                    // skip for the question line
                    sc2.nextLine();

                    // store the choices into a string
                    // eg. 4%,10%,25%,50%
                    String choice = sc2.nextLine();

                    // store the choices into the array
                    // eg. {"4%", "10%", "25%", "50%"}
                    String temporaryArr[] = choice.split(",");

                    // store the choices into 2D array
                    // eg. {
                    //      {"4%", "10%", "25%", "50%"},
                    //      {"500 million", "900 million", "1.6 billion", "5 billion"}
                    //      ...
                    //     }
                    options[i] = temporaryArr;

                    // skip 2 line
                    for(int z = 0; z < 2 && sc2.hasNextLine(); z++) {
                        sc2.nextLine();
                    }
                }
            }

            sc2.close();

            Scanner sc3 = new Scanner(triviaSample);

            while (sc3.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {

                    // skip 2 line
                    for (int j = 0; j < 2; j++) {
                        sc3.nextLine();
                    }

                    // store the answer
                    answers[i] = sc3.nextLine();
                
                    // skip 1 line
                    if(sc3.hasNextLine()) {
                        sc3.nextLine();
                    }
                }
            }

            sc3.close();

        } catch (FileNotFoundException ee) {
            // TODO Auto-generated catch block
            ee.printStackTrace();
        }
        
        // -------- trivia section --------
        
        // getting the date of registration and current date
        LocalDate registrationDate = database.getResgistrationDate();
        LocalDate currentDate = LocalDate.now();
        
        // compute the different in days
        int currentQuestionIndex = (int) ChronoUnit.DAYS.between(registrationDate, currentDate);
        int i = currentQuestionIndex;

        // getting the question
        String question = questions[i];
        String [] answerOptions = options[i];
        String correctAnswer = answers[i];
        
        // shuffle answer options
        List<String> optionsList = Arrays.asList(answerOptions);
        Collections.shuffle(optionsList);
        optionsList.toArray(answerOptions);
        
        // storing the answer
        String A,B,C,D;
        A = answerOptions[0];
        B = answerOptions[1];
        C = answerOptions[2];
        D = answerOptions[3];

        // ----------- GUI --------------
        
        String dateStr = currentDate.toString();
        
        JFrame playTriviaF = new JFrame();
        playTriviaF.setSize(1080, 720);
        playTriviaF.setTitle("Daily Trivia");
        playTriviaF.setResizable(false);
        playTriviaF.setLayout(null);
        
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
        
        JLabel currentQuestionL = new JLabel();
        currentQuestionL.setText("Current Question No  :  " + (i+1));
        currentQuestionL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        currentQuestionL.setBounds(30, 220, 400, 30);
        
        JLabel pointsL = new JLabel();
        pointsL.setText("Points  :  " + database.getCurrentPoint());
        pointsL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        pointsL.setBounds(30, 280, 400, 30);
        
        JLabel answerL = new JLabel();
        answerL.setText("Choose Your Answer : ");
        answerL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        answerL.setBounds(30, 360, 600, 30);
        
        // ----- textArea -----
                        
        JTextArea questionsTextArea = new JTextArea();
        questionsTextArea.setText("Day " +  (i+1)  + " Trivia (Attempt #" + attempts + ")" + "\n" + question);
        questionsTextArea.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        questionsTextArea.setLineWrap(true);
        questionsTextArea.setWrapStyleWord(true);
        questionsTextArea.setBounds(30, 40, 600, 100);
        questionsTextArea.setBackground(new Color(232, 247, 238)); // Set background color
        questionsTextArea.setEditable(false); // Make it non-editable
        
        JTextArea choiceTextArea = new JTextArea();
        choiceTextArea.setText("[A] " + A + "\n\n" + "[B] " + B + "\n\n" + "[C] " + C +  "\n\n" + "[D] " + D);
        choiceTextArea.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        choiceTextArea.setLineWrap(true);
        choiceTextArea.setWrapStyleWord(true);
        choiceTextArea.setBounds(30, 150, 600, 200);
        choiceTextArea.setBackground(new Color(232, 247, 238)); // Set background color
        choiceTextArea.setEditable(false); // Make it non-editable
        
        // ---- combo box ------
        String[] choices = {"A","B","C","D"} ;
        JComboBox choicesList = new JComboBox(choices);
        choicesList.setBounds(30,400,100,30);
        
        // ---- button -----
        JButton submitBtn = new JButton("SUBMIT ANSWER");
        submitBtn.setBounds(30, 460, 160, 45);
        submitBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // Set font
        
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // define all the necessary variable
                int points = database.getCurrentPoint();
                int XP = database.getXP();
                String userAnswer = (String) choicesList.getSelectedItem();
                

                if (userAnswer.equals("A") && A.equals(correctAnswer) ||
                    userAnswer.equals("B") && B.equals(correctAnswer) ||
                    userAnswer.equals("C") && C.equals(correctAnswer) ||
                    userAnswer.equals("D") && D.equals(correctAnswer)) {
                    
                    points += 1;
                    XP +=1;
                    
                    // update the point in database
                    database.updateCurrentPoint(points);
                    // update the XP in database
                    database.updateXP(XP);
                    // update the answered question in database
                    if(database.getQuestionAnswered() != null) {
                        String newAnsweredQuestion = database.getQuestionAnswered() + "," + Integer.toString(currentQuestionIndex);
                        database.updateQuestionAnswered(newAnsweredQuestion);
                    } else {
                        String newAnsweredQuestion = Integer.toString(currentQuestionIndex);
                        database.updateQuestionAnswered(newAnsweredQuestion);
                    }
                    
                    playTriviaF.dispose();
                    
                    
                    JOptionPane.showMessageDialog(null, "Congratulations! You answered it correctly. \nYou have been awarded 1 point, you now have " + points + " points.", "Result", JOptionPane.INFORMATION_MESSAGE); 
                    
                    // call new frame
                    tm.triviaMenu();
                    
                } else {
                    
                    // update the answered question in database
                    if(database.getQuestionAnswered() != null) {
                        String newAnsweredQuestion = database.getQuestionAnswered() + "," + Integer.toString(currentQuestionIndex);
                        database.updateQuestionAnswered(newAnsweredQuestion);
                    } else {
                        String newAnsweredQuestion = Integer.toString(currentQuestionIndex);
                        database.updateQuestionAnswered(newAnsweredQuestion);
                    }
                    
                    playTriviaF.dispose();
                    
                    JOptionPane.showMessageDialog(null, "Your answer is still incorrect, the correct answer is: \n" +  correctAnswer, "Result", JOptionPane.INFORMATION_MESSAGE); 
                    
                    // call new frame
                    tm.triviaMenu();
                }
                
            }
        });
        
        JButton backBtn = new JButton("BACK TO MAIN MENU");
        backBtn.setBounds(200, 460, 190, 45);
        backBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // Set font
        
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.mainMenu();
                playTriviaF.dispose();
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
        bottomLeft.add(currentQuestionL);
        bottomLeft.add(pointsL);
        bottomRight.add(questionsTextArea);
        bottomRight.add(choiceTextArea);
        bottomRight.add(choicesList);
        bottomRight.add(answerL);
        bottomRight.add(submitBtn);
        bottomRight.add(backBtn);
        
        // add to frame
        playTriviaF.add(top);
        playTriviaF.add(bottomLeft);
        playTriviaF.add(bottomRight);
        
        playTriviaF.setVisible(true);
        playTriviaF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

                    
                }
                
            
        });
        
        JButton backBtn = new JButton("BACK TO MAIN MENU");
        backBtn.setBounds(200, 460, 190, 45);
        backBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // Set font
        
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.mainMenu();
                playTriviaF.dispose();
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
        bottomLeft.add(currentQuestionL);
        bottomLeft.add(pointsL);
        bottomRight.add(questionsTextArea);
        bottomRight.add(choiceTextArea);
        bottomRight.add(choicesList);
        bottomRight.add(answerL);
        bottomRight.add(submitBtn);
        bottomRight.add(backBtn);
        
        // add to frame
        playTriviaF.add(top);
        playTriviaF.add(bottomLeft);
        playTriviaF.add(bottomRight);
        
        playTriviaF.setVisible(true);
        playTriviaF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
    // ---------------------------------- method 2 ----------------------------------------------
    // ------------------------------------------------------------------------------------------
    
    public void playTriviaWithoutAffectingPoints(int q) {
        
        // -------- connecting to database --------
        ConnectDatabase database = new ConnectDatabase();
        
        // create an object of main menu class
        MainMenu main = new MainMenu();
        
        // create an object of trivia menu class
        TriviaMenu tm = new TriviaMenu();
        
        // -------- reading the text file --------
        int numberOfQuestions = 10;

        // create an array for storing question
        String[] questions = new String[numberOfQuestions];

        // create an 2D array for storing choices
        String[][] options = new String[numberOfQuestions][];

        // create an anrray for storing correct answer's index
        String[] answers = new String[numberOfQuestions];

        File triviaSample = new File("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/Triviasection/TriviaSample.txt");
        
        try {
            
            Scanner sc = new Scanner(triviaSample);

            while (sc.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {
                    // store the question into the array
                    questions[i] = sc.nextLine();

                    // skip 3 line
                    for(int j = 0; j < 3 && sc.hasNextLine(); j++) {
                        sc.nextLine();
                    }
                }
            }
            sc.close();

            Scanner sc2 = new Scanner(triviaSample);

            while (sc2.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {

                    // skip for the question line
                    sc2.nextLine();

                    // store the choices into a string
                    // eg. 4%,10%,25%,50%
                    String choice = sc2.nextLine();

                    // store the choices into the array
                    // eg. {"4%", "10%", "25%", "50%"}
                    String temporaryArr[] = choice.split(",");

                    // store the choices into 2D array
                    // eg. {
                    //      {"4%", "10%", "25%", "50%"},
                    //      {"500 million", "900 million", "1.6 billion", "5 billion"}
                    //      ...
                    //     }
                    options[i] = temporaryArr;

                    // skip 2 line
                    for(int z = 0; z < 2 && sc2.hasNextLine(); z++) {
                        sc2.nextLine();
                    }
                }
            }

            sc2.close();

            Scanner sc3 = new Scanner(triviaSample);

            while (sc3.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {

                    // skip 2 line
                    for (int j = 0; j < 2; j++) {
                        sc3.nextLine();
                    }

                    // store the answer
                    answers[i] = sc3.nextLine();
                
                    // skip 1 line
                    if(sc3.hasNextLine()) {
                        sc3.nextLine();
                    }
                }
            }

            sc3.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // -------- trivia section --------
        
        // getting the current date
        LocalDate currentDate = LocalDate.now();

        // getting the question
        String question = questions[q];
        String [] answerOptions = options[q];
        String correctAnswer = answers[q];
        
        // storing the answer
        String A,B,C,D;
        A = answerOptions[0];
        B = answerOptions[1];
        C = answerOptions[2];
        D = answerOptions[3];
        
        int attempts = 1;
        String dateStr = currentDate.toString();
        
        // ----------- GUI --------------
        
        JFrame playTriviaF = new JFrame();
        playTriviaF.setSize(1080, 720);
        playTriviaF.setTitle("Daily Trivia");
        playTriviaF.setResizable(false);
        playTriviaF.setLayout(null);
        
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
        
        JLabel currentQuestionL = new JLabel();
        currentQuestionL.setText("Current Question No  :  " + (q+1));
        currentQuestionL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        currentQuestionL.setBounds(30, 220, 400, 30);
        
        JLabel pointsL = new JLabel();
        pointsL.setText("Points  :  " + database.getCurrentPoint());
        pointsL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        pointsL.setBounds(30, 280, 400, 30);
        
        JLabel answerL = new JLabel();
        answerL.setText("Choose Your Answer : ");
        answerL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        answerL.setBounds(30, 360, 600, 30);
        
        // ----- textArea -----
                        
        JTextArea questionsTextArea = new JTextArea();
        questionsTextArea.setText("Day " +  (q+1)  + " Trivia (Attempt #" + attempts + ")" + "\n" + question);
        questionsTextArea.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        questionsTextArea.setLineWrap(true);
        questionsTextArea.setWrapStyleWord(true);
        questionsTextArea.setBounds(30, 40, 600, 100);
        questionsTextArea.setBackground(new Color(232, 247, 238)); // Set background color
        questionsTextArea.setEditable(false); // Make it non-editable
        
        JTextArea choiceTextArea = new JTextArea();
        choiceTextArea.setText("[A] " + A + "\n\n" + "[B] " + B + "\n\n" + "[C] " + C +  "\n\n" + "[D] " + D);
        choiceTextArea.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        choiceTextArea.setLineWrap(true);
        choiceTextArea.setWrapStyleWord(true);
        choiceTextArea.setBounds(30, 150, 600, 200);
        choiceTextArea.setBackground(new Color(232, 247, 238)); // Set background color
        choiceTextArea.setEditable(false); // Make it non-editable
        
        // ---- combo box ------
        String[] choices = {"A","B","C","D"} ;
        JComboBox choicesList = new JComboBox(choices);
        choicesList.setBounds(30,400,100,30);
        
        // ---- button -----
        JButton submitBtn = new JButton("SUBMIT ANSWER");
        submitBtn.setBounds(30, 460, 160, 45);
        submitBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // Set font
        
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // define all the necessary variable
                int attempts = 1;
                String userAnswer = (String) choicesList.getSelectedItem();
                
                playTriviaF.dispose();

                if (userAnswer.equals("A") && A.equals(correctAnswer) ||
                    userAnswer.equals("B") && B.equals(correctAnswer) ||
                    userAnswer.equals("C") && C.equals(correctAnswer) ||
                    userAnswer.equals("D") && D.equals(correctAnswer)) {
                    
                    JOptionPane.showMessageDialog(null, "Congratulations! You answered it correctly.", "Result", JOptionPane.INFORMATION_MESSAGE); 
                    
                    // call new frame
                    tm.triviaMenu();
                    
                } else {
                    
                    JOptionPane.showMessageDialog(null, "Whoops, that doesn’t look right, try again!", "Result", JOptionPane.INFORMATION_MESSAGE); 
                    
                    attempts++;
                    
        // -------- connecting to database --------
        ConnectDatabase database = new ConnectDatabase();
        
        // create an object of trivia menu class
        TriviaMenu tm = new TriviaMenu();
        
        // create an object of main menu class
        MainMenu main = new MainMenu();
        
        // -------- reading the text file --------
        int numberOfQuestions = 10;

        // create an array for storing question
        String[] questions = new String[numberOfQuestions];

        // create an 2D array for storing choices
        String[][] options = new String[numberOfQuestions][];

        // create an anrray for storing correct answer's index
        String[] answers = new String[numberOfQuestions];

        File triviaSample = new File("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/Triviasection/TriviaSample.txt");
        
        try {
            
            Scanner sc = new Scanner(triviaSample);

            while (sc.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {
                    // store the question into the array
                    questions[i] = sc.nextLine();

                    // skip 3 line
                    for(int j = 0; j < 3 && sc.hasNextLine(); j++) {
                        sc.nextLine();
                    }
                }
            }
            sc.close();

            Scanner sc2 = new Scanner(triviaSample);

            while (sc2.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {

                    // skip for the question line
                    sc2.nextLine();

                    // store the choices into a string
                    // eg. 4%,10%,25%,50%
                    String choice = sc2.nextLine();

                    // store the choices into the array
                    // eg. {"4%", "10%", "25%", "50%"}
                    String temporaryArr[] = choice.split(",");

                    // store the choices into 2D array
                    // eg. {
                    //      {"4%", "10%", "25%", "50%"},
                    //      {"500 million", "900 million", "1.6 billion", "5 billion"}
                    //      ...
                    //     }
                    options[i] = temporaryArr;

                    // skip 2 line
                    for(int z = 0; z < 2 && sc2.hasNextLine(); z++) {
                        sc2.nextLine();
                    }
                }
            }

            sc2.close();

            Scanner sc3 = new Scanner(triviaSample);

            while (sc3.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {

                    // skip 2 line
                    for (int j = 0; j < 2; j++) {
                        sc3.nextLine();
                    }

                    // store the answer
                    answers[i] = sc3.nextLine();
                
                    // skip 1 line
                    if(sc3.hasNextLine()) {
                        sc3.nextLine();
                    }
                }
            }

            sc3.close();

        } catch (FileNotFoundException ee) {
            // TODO Auto-generated catch block
            ee.printStackTrace();
        }
        
        // -------- trivia section --------
        
        // getting the current date
        LocalDate currentDate = LocalDate.now();

        // getting the question
        String question = questions[q];
        String [] answerOptions = options[q];
        String correctAnswer = answers[q];
        
        // shuffle answer options
        List<String> optionsList = Arrays.asList(answerOptions);
        Collections.shuffle(optionsList);
        optionsList.toArray(answerOptions);
        
        // storing the answer
        String A,B,C,D;
        A = answerOptions[0];
        B = answerOptions[1];
        C = answerOptions[2];
        D = answerOptions[3];

        // ----------- GUI --------------
        
        String dateStr = currentDate.toString();
        
        JFrame playTriviaF = new JFrame();
        playTriviaF.setSize(1080, 720);
        playTriviaF.setTitle("Daily Trivia");
        playTriviaF.setResizable(false);
        playTriviaF.setLayout(null);
        
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
        
        JLabel currentQuestionL = new JLabel();
        currentQuestionL.setText("Current Question No  :  " + (q+1));
        currentQuestionL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        currentQuestionL.setBounds(30, 220, 400, 30);
        
        JLabel pointsL = new JLabel();
        pointsL.setText("Points  :  " + database.getCurrentPoint());
        pointsL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        pointsL.setBounds(30, 280, 400, 30);
        
        JLabel answerL = new JLabel();
        answerL.setText("Choose Your Answer : ");
        answerL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        answerL.setBounds(30, 360, 600, 30);
        
        // ----- textArea -----
                        
        JTextArea questionsTextArea = new JTextArea();
        questionsTextArea.setText("Day " +  (q+1)  + " Trivia (Attempt #" + attempts + ")" + "\n" + question);
        questionsTextArea.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        questionsTextArea.setLineWrap(true);
        questionsTextArea.setWrapStyleWord(true);
        questionsTextArea.setBounds(30, 40, 600, 100);
        questionsTextArea.setBackground(new Color(232, 247, 238)); // Set background color
        questionsTextArea.setEditable(false); // Make it non-editable
        
        JTextArea choiceTextArea = new JTextArea();
        choiceTextArea.setText("[A] " + A + "\n\n" + "[B] " + B + "\n\n" + "[C] " + C +  "\n\n" + "[D] " + D);
        choiceTextArea.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        choiceTextArea.setLineWrap(true);
        choiceTextArea.setWrapStyleWord(true);
        choiceTextArea.setBounds(30, 150, 600, 200);
        choiceTextArea.setBackground(new Color(232, 247, 238)); // Set background color
        choiceTextArea.setEditable(false); // Make it non-editable
        
        // ---- combo box ------
        String[] choices = {"A","B","C","D"} ;
        JComboBox choicesList = new JComboBox(choices);
        choicesList.setBounds(30,400,100,30);
        
        // ---- button -----
        JButton submitBtn = new JButton("SUBMIT ANSWER");
        submitBtn.setBounds(30, 460, 160, 45);
        submitBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // Set font
        
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String userAnswer = (String) choicesList.getSelectedItem();
                

                if (userAnswer.equals("A") && A.equals(correctAnswer) ||
                    userAnswer.equals("B") && B.equals(correctAnswer) ||
                    userAnswer.equals("C") && C.equals(correctAnswer) ||
                    userAnswer.equals("D") && D.equals(correctAnswer)) {
                    
                    playTriviaF.dispose();
                    
                    
                    JOptionPane.showMessageDialog(null, "Congratulations! You answered it correctly. ", "Result", JOptionPane.INFORMATION_MESSAGE); 
                    
                    // call new frame
                    tm.triviaMenu();
                    
                } else {
                    
                    playTriviaF.dispose();
                    
                    JOptionPane.showMessageDialog(null, "Your answer is still incorrect, the correct answer is: \n" +  correctAnswer, "Result", JOptionPane.INFORMATION_MESSAGE); 
                    
                    // call new frame
                    tm.triviaMenu();
                }
                
            }
        });
        
        JButton backBtn = new JButton("BACK TO MAIN MENU");
        backBtn.setBounds(200, 460, 190, 45);
        backBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // Set font
        
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.mainMenu();
                playTriviaF.dispose();
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
        bottomLeft.add(currentQuestionL);
        bottomLeft.add(pointsL);
        bottomRight.add(questionsTextArea);
        bottomRight.add(choiceTextArea);
        bottomRight.add(choicesList);
        bottomRight.add(answerL);
        bottomRight.add(submitBtn);
        bottomRight.add(backBtn);
        
        // add to frame
        playTriviaF.add(top);
        playTriviaF.add(bottomLeft);
        playTriviaF.add(bottomRight);
        
        playTriviaF.setVisible(true);
        playTriviaF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

                    
                }
                
            
        });
        
        JButton backBtn = new JButton("BACK TO MAIN MENU");
        backBtn.setBounds(200, 460, 190, 45);
        backBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // Set font
        
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.mainMenu();
                playTriviaF.dispose();
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
        bottomLeft.add(currentQuestionL);
        bottomLeft.add(pointsL);
        bottomRight.add(questionsTextArea);
        bottomRight.add(choiceTextArea);
        bottomRight.add(choicesList);
        bottomRight.add(answerL);
        bottomRight.add(submitBtn);
        bottomRight.add(backBtn);
        
        // add to frame
        playTriviaF.add(top);
        playTriviaF.add(bottomLeft);
        playTriviaF.add(bottomRight);
        
        playTriviaF.setVisible(true);
        playTriviaF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
    // ------------------------------------- Method 3 --------------------------------------------------
    // -------------------------------------------------------------------------------------------------
    
    public void playTriviaWithAffectingPoints(int q) {
        
        // -------- connecting to database --------
        ConnectDatabase database = new ConnectDatabase();
        
        // create an object of main menu class
        MainMenu main = new MainMenu();
        
        // create an object of trivia menu class
        TriviaMenu tm = new TriviaMenu();
        
        // -------- reading the text file --------
        int numberOfQuestions = 10;

        // create an array for storing question
        String[] questions = new String[numberOfQuestions];

        // create an 2D array for storing choices
        String[][] options = new String[numberOfQuestions][];

        // create an anrray for storing correct answer's index
        String[] answers = new String[numberOfQuestions];

        File triviaSample = new File("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/Triviasection/TriviaSample.txt");
        
        try {
            
            Scanner sc = new Scanner(triviaSample);

            while (sc.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {
                    // store the question into the array
                    questions[i] = sc.nextLine();

                    // skip 3 line
                    for(int j = 0; j < 3 && sc.hasNextLine(); j++) {
                        sc.nextLine();
                    }
                }
            }
            sc.close();

            Scanner sc2 = new Scanner(triviaSample);

            while (sc2.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {

                    // skip for the question line
                    sc2.nextLine();

                    // store the choices into a string
                    // eg. 4%,10%,25%,50%
                    String choice = sc2.nextLine();

                    // store the choices into the array
                    // eg. {"4%", "10%", "25%", "50%"}
                    String temporaryArr[] = choice.split(",");

                    // store the choices into 2D array
                    // eg. {
                    //      {"4%", "10%", "25%", "50%"},
                    //      {"500 million", "900 million", "1.6 billion", "5 billion"}
                    //      ...
                    //     }
                    options[i] = temporaryArr;

                    // skip 2 line
                    for(int z = 0; z < 2 && sc2.hasNextLine(); z++) {
                        sc2.nextLine();
                    }
                }
            }

            sc2.close();

            Scanner sc3 = new Scanner(triviaSample);

            while (sc3.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {

                    // skip 2 line
                    for (int j = 0; j < 2; j++) {
                        sc3.nextLine();
                    }

                    // store the answer
                    answers[i] = sc3.nextLine();
                
                    // skip 1 line
                    if(sc3.hasNextLine()) {
                        sc3.nextLine();
                    }
                }
            }

            sc3.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // -------- trivia section --------
        
        // getting the current date
        LocalDate currentDate = LocalDate.now();

        // getting the question
        String question = questions[q];
        String [] answerOptions = options[q];
        String correctAnswer = answers[q];
        
        // storing the answer
        String A,B,C,D;
        A = answerOptions[0];
        B = answerOptions[1];
        C = answerOptions[2];
        D = answerOptions[3];
        
        int attempts = 1;
        String dateStr = currentDate.toString();
        
        // ----------- GUI --------------
        
        JFrame playTriviaF = new JFrame();
        playTriviaF.setSize(1080, 720);
        playTriviaF.setTitle("Daily Trivia");
        playTriviaF.setResizable(false);
        playTriviaF.setLayout(null);
        
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
        
        JLabel currentQuestionL = new JLabel();
        currentQuestionL.setText("Current Question No  :  " + (q+1));
        currentQuestionL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        currentQuestionL.setBounds(30, 220, 400, 30);
        
        JLabel pointsL = new JLabel();
        pointsL.setText("Points  :  " + database.getCurrentPoint());
        pointsL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        pointsL.setBounds(30, 280, 400, 30);
        
        JLabel answerL = new JLabel();
        answerL.setText("Choose Your Answer : ");
        answerL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        answerL.setBounds(30, 360, 600, 30);
        
        // ----- textArea -----
                        
        JTextArea questionsTextArea = new JTextArea();
        questionsTextArea.setText("Day " +  (q+1)  + " Trivia (Attempt #" + attempts + ")" + "\n" + question);
        questionsTextArea.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        questionsTextArea.setLineWrap(true);
        questionsTextArea.setWrapStyleWord(true);
        questionsTextArea.setBounds(30, 40, 600, 100);
        questionsTextArea.setBackground(new Color(232, 247, 238)); // Set background color
        questionsTextArea.setEditable(false); // Make it non-editable
        
        JTextArea choiceTextArea = new JTextArea();
        choiceTextArea.setText("[A] " + A + "\n\n" + "[B] " + B + "\n\n" + "[C] " + C +  "\n\n" + "[D] " + D);
        choiceTextArea.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        choiceTextArea.setLineWrap(true);
        choiceTextArea.setWrapStyleWord(true);
        choiceTextArea.setBounds(30, 150, 600, 200);
        choiceTextArea.setBackground(new Color(232, 247, 238)); // Set background color
        choiceTextArea.setEditable(false); // Make it non-editable
        
        // ---- combo box ------
        String[] choices = {"A","B","C","D"} ;
        JComboBox choicesList = new JComboBox(choices);
        choicesList.setBounds(30,400,100,30);
        
        // ---- button -----
        JButton submitBtn = new JButton("SUBMIT ANSWER");
        submitBtn.setBounds(30, 460, 160, 45);
        submitBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // Set font
        
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // define all the necessary variable
                int attempts = 1;
                String userAnswer = (String) choicesList.getSelectedItem();
                int points = database.getCurrentPoint();
                int XP = database.getXP();
                
                playTriviaF.dispose();

                if (userAnswer.equals("A") && A.equals(correctAnswer) ||
                    userAnswer.equals("B") && B.equals(correctAnswer) ||
                    userAnswer.equals("C") && C.equals(correctAnswer) ||
                    userAnswer.equals("D") && D.equals(correctAnswer)) {
                    
                    points += 2;
                    XP += 2;
                    
                    // update the point in database
                    database.updateCurrentPoint(points);
                    // update the XP in database
                    database.updateXP(XP);
                    
                    // update the answered question in database
                    if(database.getQuestionAnswered() != null) {
                        String newAnsweredQuestion = database.getQuestionAnswered() + "," + Integer.toString(q);
                        database.updateQuestionAnswered(newAnsweredQuestion);
                    } else {
                        String newAnsweredQuestion = Integer.toString(q);
                        database.updateQuestionAnswered(newAnsweredQuestion);
                    }
                    
                    JOptionPane.showMessageDialog(null, "Congratulations! You answered it correctly.", "Result", JOptionPane.INFORMATION_MESSAGE); 
                    
                    // call new frame
                    tm.triviaMenu();
                    
                } else {
                    
                    JOptionPane.showMessageDialog(null, "Whoops, that doesn’t look right, try again!", "Result", JOptionPane.INFORMATION_MESSAGE); 
                    
                    attempts++;
                    
        // -------- connecting to database --------
        ConnectDatabase database = new ConnectDatabase();
        
        // create an object of trivia menu class
        TriviaMenu tm = new TriviaMenu();
        
        // create an object of main menu class
        MainMenu main = new MainMenu();
        
        // -------- reading the text file --------
        int numberOfQuestions = 10;

        // create an array for storing question
        String[] questions = new String[numberOfQuestions];

        // create an 2D array for storing choices
        String[][] options = new String[numberOfQuestions][];

        // create an anrray for storing correct answer's index
        String[] answers = new String[numberOfQuestions];

        File triviaSample = new File("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/Triviasection/TriviaSample.txt");
        
        try {
            
            Scanner sc = new Scanner(triviaSample);

            while (sc.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {
                    // store the question into the array
                    questions[i] = sc.nextLine();

                    // skip 3 line
                    for(int j = 0; j < 3 && sc.hasNextLine(); j++) {
                        sc.nextLine();
                    }
                }
            }
            sc.close();

            Scanner sc2 = new Scanner(triviaSample);

            while (sc2.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {

                    // skip for the question line
                    sc2.nextLine();

                    // store the choices into a string
                    // eg. 4%,10%,25%,50%
                    String choice = sc2.nextLine();

                    // store the choices into the array
                    // eg. {"4%", "10%", "25%", "50%"}
                    String temporaryArr[] = choice.split(",");

                    // store the choices into 2D array
                    // eg. {
                    //      {"4%", "10%", "25%", "50%"},
                    //      {"500 million", "900 million", "1.6 billion", "5 billion"}
                    //      ...
                    //     }
                    options[i] = temporaryArr;

                    // skip 2 line
                    for(int z = 0; z < 2 && sc2.hasNextLine(); z++) {
                        sc2.nextLine();
                    }
                }
            }

            sc2.close();

            Scanner sc3 = new Scanner(triviaSample);

            while (sc3.hasNextLine()) {
                for(int i = 0; i < numberOfQuestions; i++) {

                    // skip 2 line
                    for (int j = 0; j < 2; j++) {
                        sc3.nextLine();
                    }

                    // store the answer
                    answers[i] = sc3.nextLine();
                
                    // skip 1 line
                    if(sc3.hasNextLine()) {
                        sc3.nextLine();
                    }
                }
            }

            sc3.close();

        } catch (FileNotFoundException ee) {
            // TODO Auto-generated catch block
            ee.printStackTrace();
        }
        
        // -------- trivia section --------
        
        // getting the current date
        LocalDate currentDate = LocalDate.now();

        // getting the question
        String question = questions[q];
        String [] answerOptions = options[q];
        String correctAnswer = answers[q];
        
        // shuffle answer options
        List<String> optionsList = Arrays.asList(answerOptions);
        Collections.shuffle(optionsList);
        optionsList.toArray(answerOptions);
        
        // storing the answer
        String A,B,C,D;
        A = answerOptions[0];
        B = answerOptions[1];
        C = answerOptions[2];
        D = answerOptions[3];

        // ----------- GUI --------------
        
        String dateStr = currentDate.toString();
        
        JFrame playTriviaF = new JFrame();
        playTriviaF.setSize(1080, 720);
        playTriviaF.setTitle("Daily Trivia");
        playTriviaF.setResizable(false);
        playTriviaF.setLayout(null);
        
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
        
        JLabel currentQuestionL = new JLabel();
        currentQuestionL.setText("Current Question No  :  " + (q+1));
        currentQuestionL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        currentQuestionL.setBounds(30, 220, 400, 30);
        
        JLabel pointsL = new JLabel();
        pointsL.setText("Points  :  " + database.getCurrentPoint());
        pointsL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        pointsL.setBounds(30, 280, 400, 30);
        
        JLabel answerL = new JLabel();
        answerL.setText("Choose Your Answer : ");
        answerL.setFont(new Font("Helvetica Neue", Font.BOLD, 18)); // set font of text
        answerL.setBounds(30, 360, 600, 30);
        
        // ----- textArea -----
                        
        JTextArea questionsTextArea = new JTextArea();
        questionsTextArea.setText("Day " +  (q+1)  + " Trivia (Attempt #" + attempts + ")" + "\n" + question);
        questionsTextArea.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        questionsTextArea.setLineWrap(true);
        questionsTextArea.setWrapStyleWord(true);
        questionsTextArea.setBounds(30, 40, 600, 100);
        questionsTextArea.setBackground(new Color(232, 247, 238)); // Set background color
        questionsTextArea.setEditable(false); // Make it non-editable
        
        JTextArea choiceTextArea = new JTextArea();
        choiceTextArea.setText("[A] " + A + "\n\n" + "[B] " + B + "\n\n" + "[C] " + C +  "\n\n" + "[D] " + D);
        choiceTextArea.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        choiceTextArea.setLineWrap(true);
        choiceTextArea.setWrapStyleWord(true);
        choiceTextArea.setBounds(30, 150, 600, 200);
        choiceTextArea.setBackground(new Color(232, 247, 238)); // Set background color
        choiceTextArea.setEditable(false); // Make it non-editable
        
        // ---- combo box ------
        String[] choices = {"A","B","C","D"} ;
        JComboBox choicesList = new JComboBox(choices);
        choicesList.setBounds(30,400,100,30);
        
        // ---- button -----
        JButton submitBtn = new JButton("SUBMIT ANSWER");
        submitBtn.setBounds(30, 460, 160, 45);
        submitBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // Set font
        
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                int points = database.getCurrentPoint();
                int XP = database.getXP();
                String userAnswer = (String) choicesList.getSelectedItem();
                

                if (userAnswer.equals("A") && A.equals(correctAnswer) ||
                    userAnswer.equals("B") && B.equals(correctAnswer) ||
                    userAnswer.equals("C") && C.equals(correctAnswer) ||
                    userAnswer.equals("D") && D.equals(correctAnswer)) {
                    
                    playTriviaF.dispose();
                    
                    points += 1;
                    XP += 1;
                    
                    // update the point in database
                    database.updateCurrentPoint(points);
                    // update the XP in database
                    database.updateXP(XP);
        
                    // update the answered question in database
                    if(database.getQuestionAnswered() != null) {
                        String newAnsweredQuestion = database.getQuestionAnswered() + "," + Integer.toString(q);
                        database.updateQuestionAnswered(newAnsweredQuestion);
                    } else {
                        String newAnsweredQuestion = Integer.toString(q);
                        database.updateQuestionAnswered(newAnsweredQuestion);
                    }
                    
                    JOptionPane.showMessageDialog(null, "Congratulations! You answered it correctly. ", "Result", JOptionPane.INFORMATION_MESSAGE); 
                    
                    // call new frame
                    tm.triviaMenu();
                    
                } else {
                    
                    playTriviaF.dispose();
                    
                    JOptionPane.showMessageDialog(null, "Your answer is still incorrect, the correct answer is: \n" +  correctAnswer, "Result", JOptionPane.INFORMATION_MESSAGE); 
                    
                    // call new frame
                    tm.triviaMenu();
                }
                
            }
        });
        
        JButton backBtn = new JButton("BACK TO MAIN MENU");
        backBtn.setBounds(200, 460, 190, 45);
        backBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // Set font
        
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.mainMenu();
                playTriviaF.dispose();
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
        bottomLeft.add(currentQuestionL);
        bottomLeft.add(pointsL);
        bottomRight.add(questionsTextArea);
        bottomRight.add(choiceTextArea);
        bottomRight.add(choicesList);
        bottomRight.add(answerL);
        bottomRight.add(submitBtn);
        bottomRight.add(backBtn);
        
        // add to frame
        playTriviaF.add(top);
        playTriviaF.add(bottomLeft);
        playTriviaF.add(bottomRight);
        
        playTriviaF.setVisible(true);
        playTriviaF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

                    
                }
                
            
        });
        
        JButton backBtn = new JButton("BACK TO MAIN MENU");
        backBtn.setBounds(200, 460, 190, 45);
        backBtn.setFont(new Font("Helvetica Neue", Font.BOLD, 15)); // Set font
        
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.mainMenu();
                playTriviaF.dispose();
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
        bottomLeft.add(currentQuestionL);
        bottomLeft.add(pointsL);
        bottomRight.add(questionsTextArea);
        bottomRight.add(choiceTextArea);
        bottomRight.add(choicesList);
        bottomRight.add(answerL);
        bottomRight.add(submitBtn);
        bottomRight.add(backBtn);
        
        // add to frame
        playTriviaF.add(top);
        playTriviaF.add(bottomLeft);
        playTriviaF.add(bottomRight);
        
        playTriviaF.setVisible(true);
        playTriviaF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
    // -------------------------------------------- method 4 ----------------------------------------------
    // ----------------------------------------------------------------------------------------------------
    
    // method check for whether a question already being answered 
    public boolean alreadyAnswer(int questionNum) {
        // create an object of database class
        ConnectDatabase database = new ConnectDatabase();
        
        String questionAnsweredStr = database.getQuestionAnswered();
        
        if(questionAnsweredStr == null) {
            return false;
        }
        
        // get the question number 
        String[] questionAnsweredStrArr = questionAnsweredStr.split(",");
        
        String questionNumStr = Integer.toString(questionNum);
        
        for(int i = 0; i < questionAnsweredStrArr.length; i++) {
            if(questionNumStr.equals(questionAnsweredStrArr[i])) {
                return true;
            } 
        }
        
        return false;
    }
}
