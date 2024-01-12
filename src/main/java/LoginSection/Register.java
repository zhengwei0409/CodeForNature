
package LoginSection;

import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.Scanner;
import java.sql.*;

public class Register {
    
    public void registerAccount() {
        
        Scanner sc = new Scanner(System.in);
        
        String email; // make it global
        while(true) {
        System.out.print("Enter a valid email : ");
        email = sc.next();
        // use regular expressions (regex) to match whether the email enter by user is in correct format
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        boolean match = email.matches(regex);
        if(match) {
            break;
        } else {
            System.out.println("Invalid email ! Enter again.");
        }
        }
        System.out.println("Enter an username : ");
        String username = sc.next();
        System.out.println("Enter a password : ");
        String password = sc.next();
        
        String hashedPassword = PasswordHash(password);
        
        LocalDate currentDate = LocalDate.now();
        
        try {
            // Establish database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "");

            // Insert user into the database
            String query = "INSERT INTO UserAccount (email, username, password, registration_date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, username); 
                preparedStatement.setString(3, hashedPassword);
                preparedStatement.setDate(4, java.sql.Date.valueOf(currentDate));

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Registration successful!");
                } else {
                    System.out.println("Registration failed. Please try again.");
                }
            }

            // Close resources
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // method use for hashing password
    public static String PasswordHash (String password){
        try{
            MessageDigest md = MessageDigest.getInstance ("SHA-256"); 
            md.update(password.getBytes());
            byte [] rbt = md.digest();
            StringBuilder sb = new StringBuilder();
            
            for (byte b: rbt){
                sb.append (String.format("%02x", b));
            }
            return sb.toString();
        }catch (Exception e){
            
        }return null;
    }
    
}
