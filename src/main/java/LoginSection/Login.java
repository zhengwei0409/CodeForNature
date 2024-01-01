
package LoginSection;

import java.sql.*;
import java.util.Scanner;

public class Login {
    
    private String email;
    private String password;
    // use static to achieve a shared userId value across multiple instances of the Login class
    private static int userId;
    
    public void Login() {
        
        // while loop use for : if the user type invalid email or password , it will keep asking user to retype
        while(true) {

        Scanner sc = new Scanner(System.in);
        
        // ask for the user email address and password
        System.out.print("Enter your email : ");
        email = sc.next();
        System.out.print("Enter your password : ");
        password = sc.next();
        
        try {
            // Establish database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "Vip4547chew$");

            // Query the database
            String query = "SELECT id FROM UserAccount WHERE email = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Login successful!");
                    userId = resultSet.getInt("id");
                    break;
                } else {
                    System.out.println("Invalid email or password");
                    System.out.println("Enter again");
                }
            }

            // Close resources
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
    }
    
    // accessor
    public int getUserId() {
        return userId;
    }
    
}
    
    


