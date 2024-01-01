
package TestingGUICode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import Triviasection.ConnectDatabase;
import java.time.LocalDate;

public class Login {

    private String email;
    private String password;
    private String username;
    // use static to achieve a shared userId value across multiple instances of the Login class
    private static int userId;
    
    public void logIn() {
        
        // create an object of instruction class
        Instructions i = new Instructions();

        // -------------- GUI ----------------
        
        JFrame LoginFrame = new JFrame(); // create a frame
        LoginFrame.setTitle("Login page"); // sets title of frame
        LoginFrame.setSize(1040,520); // set the x-dimension and y-dimension of frame
        LoginFrame.setLayout(null);
        
        // create a left panel ( for image )
        JPanel left = new JPanel();
        left.setBounds(0, 0, 520, 520);
        left.setBackground(new Color(255,255,255));
        left.setLayout(new BorderLayout());
        
        // create a right panel ( for login / register )
        JPanel right = new JPanel();
        right.setBounds(520, 0, 520, 520);
        right.setBackground(new Color(183, 244, 216));
        right.setLayout(null);
        
        // -- designing the right panel -- 
        
        // title part
        JLabel title = new JLabel();
        title.setText("Code For Nature");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 25)); // set font of text
        // Get the preferred size of the title label
        Dimension size = title.getPreferredSize();
        // Calculate the X-coordinate to center the label horizontally
        int xCoordinate = (520 - size.width) / 2;
        title.setBounds(xCoordinate, 20, 520, 50);
        
        // email & username & password
        JLabel emailL = new JLabel();
        emailL.setText("EMAIL ");
        emailL.setFont(new Font("Helvetica Neue", Font.BOLD, 13)); // set font of text
        emailL.setBounds(100, 140, 260, 30);
        
        JLabel usernameL = new JLabel();
        usernameL.setText("USERNAME ");
        usernameL.setFont(new Font("Helvetica Neue", Font.BOLD, 13)); // set font of text
        usernameL.setBounds(100, 200, 260, 30);
        
        JLabel passwordL = new JLabel();
        passwordL.setText("PASSWORD ");
        passwordL.setFont(new Font("Helvetica Neue", Font.BOLD, 13)); // set font of text
        passwordL.setBounds(100, 260, 260, 30);
        
        // text fields
        JTextField emailTF = new JTextField();
        emailTF.setBounds(230, 140, 200, 30);
        emailTF.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        emailTF.setBackground(new Color(203, 255, 236)); // You can customize the color
        
        JTextField usernameTF = new JTextField();
        usernameTF.setBounds(230, 200, 200, 30);
        usernameTF.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        usernameTF.setBackground(new Color(203, 255, 236)); // You can customize the color
        
        JPasswordField passwordTF = new JPasswordField();
        passwordTF.setBounds(230, 260, 200, 30);
        passwordTF.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        passwordTF.setBackground(new Color(203, 255, 236)); // You can customize the color
        
        
        // buttons
        JButton loginB = new JButton("LOGIN");
        loginB.setBounds(130, 350, 100, 25);
        loginB.setFont(new Font("Helvetica Neue", Font.BOLD, 12)); // Set font
        
        JButton signUpB = new JButton("SIGN UP");
        signUpB.setBounds(280, 350, 100, 25);
        signUpB.setFont(new Font("Helvetica Neue", Font.BOLD, 12)); // Set font
        
        loginB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
        email = emailTF.getText();
        username = usernameTF.getText();
        char[] passwordChars = passwordTF.getPassword();
        password = new String(passwordChars); // Convert char array to string
                
                try {
            // Establish database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "Vip4547chew$");

            // Query the database
            String query = "SELECT id FROM UserAccount WHERE email = ? AND username= ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Login successful!", "Result", JOptionPane.INFORMATION_MESSAGE);
                    userId = resultSet.getInt("id");
                    LoginFrame.dispose();
                    ConnectDatabase db = new ConnectDatabase();
                    i.instructions();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or password", "Result", JOptionPane.ERROR_MESSAGE);
                }
            }
            // Close resources
            connection.close();
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
            }
        });
        
        signUpB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        
        String email = emailTF.getText(); 
        String username = usernameTF.getText();
        char[] passwordChars = passwordTF.getPassword();
        String password = new String(passwordChars); // Convert char array to string
        

        // use regular expressions (regex) to match whether the email enter by user is in correct format
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        boolean match = email.matches(regex);
        if(match && !username.isEmpty() && !password.isEmpty()) {
            LocalDate currentDate = LocalDate.now();
        try {
            // Establish database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "Vip4547chew$");

            // Insert user into the database
            String query = "INSERT INTO UserAccount (email, username, password, registration_date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, username); 
                preparedStatement.setString(3, password);
                preparedStatement.setDate(4, java.sql.Date.valueOf(currentDate));

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Registration successful! You can login to your account now !", "Result", JOptionPane.INFORMATION_MESSAGE); 
                } else {
                    JOptionPane.showMessageDialog(null, "Error , Database Problem", "Result", JOptionPane.ERROR_MESSAGE);
                }
            }

            // Close resources
            connection.close();
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid email or username or password ! Enter again.", "Result", JOptionPane.ERROR_MESSAGE);
        }
        
        
            }
        });
        
        // insert image to left panel
        ImageIcon image = new ImageIcon("/Users/zhengwei/NetBeansProjects/TriviaSection/src/main/java/LoginPageImage.png");
        JLabel loginImage = new JLabel();
        loginImage.setIcon(image);
        
        // add to panel (left)
        left.add(loginImage, BorderLayout.CENTER);
        
        // add to panel (right)
        right.add(title);
        right.add(emailL);
        right.add(usernameL);
        right.add(passwordL);
        right.add(emailTF);
        right.add(usernameTF);
        right.add(passwordTF);
        right.add(loginB);
        right.add(signUpB);
        
        // add to frame
        LoginFrame.add(left);
        LoginFrame.add(right);
        LoginFrame.setVisible(true); // make frame visible
        LoginFrame.setResizable(false); // prevent frame from being resized
        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

    }
    
    // accessor 
    public int getUserId() {
        return userId;
    }
}
