
package Triviasection;

import TestingGUICode.Login; // for GUI
// import LoginSection.*; // for CLI
import java.sql.*;
import java.time.LocalDate;

public class ConnectDatabase {
    
    // create an object of login class ( to get the user id )
    Login lg = new Login();

    private String userName;
    private String email;
    private LocalDate registrationDate;
    private LocalDate lastCheckInDate;
    private int current_point;
    private String question_answered;
    private int userId = lg.getUserId();;
    
    // constructor that connect to database and execute query
    public ConnectDatabase() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/testing";
        String username = "root";
        String password = "Vip4547chew$";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            String sqlQuery = "SELECT id, email, username, registration_date, current_point, question_answered, last_checkin_date FROM UserAccount WHERE id = ?";
            
            // Create a PreparedStatement with the SQL query
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                // 1 refer to the first placeholder "?" follow by the value you want to replace
                preparedStatement.setInt(1, userId);

                // Execute the query
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Check if the result set has a row
                    if (resultSet.next()) {
                        // Retrieve the registration date from the result set
                        java.sql.Date sqlRegistrationDate = resultSet.getDate("registration_date");
                        java.sql.Date sqlLastCheckInDate = resultSet.getDate("last_checkin_date");
                        this.current_point = resultSet.getInt("current_point");
                        this.question_answered = resultSet.getString("question_answered");
                        this.userName = resultSet.getString("username");
                        this.userId = resultSet.getInt("id");
                        this.email = resultSet.getString("email");
                        
                        // Convert java.sql.Date to LocalDate
                        this.registrationDate = sqlRegistrationDate.toLocalDate();
                        if(sqlLastCheckInDate != null) {
                            this.lastCheckInDate = sqlLastCheckInDate.toLocalDate();
                        } else {
                            this.lastCheckInDate = null;
                        }
                    } else {
                        System.out.println("User not found!");
                    }
                }
            }

            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // method use for update the point
    public void updateCurrentPoint(int newPoint) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/testing";
        String username = "root";
        String password = "Vip4547chew$";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            // Update SQL query to set new value for current_point
            String sqlUpdate = "UPDATE UserAccount SET current_point = ? WHERE id = ?";

            // Create a PreparedStatement with the SQL update query
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
                // Set the parameters
                preparedStatement.setInt(1, newPoint);
                preparedStatement.setInt(2, userId);
                
                // Execute the update
                int rowsAffected = preparedStatement.executeUpdate();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // method use for update the question answered
    public void updateQuestionAnswered(String questionAnswered) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/testing";
        String username = "root";
        String password = "Vip4547chew$";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            // Update SQL query to set new value for question_answered
            String sqlUpdate = "UPDATE UserAccount SET question_answered = ? WHERE id = ?";

            // Create a PreparedStatement with the SQL update query
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
                // Set the parameters
                preparedStatement.setString(1, questionAnswered);
                preparedStatement.setInt(2, userId);
                
                // Execute the update
                int rowsAffected = preparedStatement.executeUpdate();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // method use for update the check in date
    public void updateCheckInDate(LocalDate lastCheckInDate) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/testing";
        String username = "root";
        String password = "Vip4547chew$";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            // Update SQL query to set new value for current_point
            String sqlUpdate = "UPDATE UserAccount SET last_checkin_date = ? WHERE id = ?";

            // Create a PreparedStatement with the SQL update query
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
                
                // Convert LocalDate to java.sql.Date
                preparedStatement.setDate(1, java.sql.Date.valueOf(lastCheckInDate)); 
                preparedStatement.setInt(2, userId); // Set the user ID
                
                // Execute the update
                int rowsAffected = preparedStatement.executeUpdate();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // accessor
    public int getID() {
        return this.userId;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getUsername() {
        return this.userName;
    }
    
    public LocalDate getResgistrationDate() {
        return this.registrationDate;
    }
    
    public int getCurrentPoint() {
        return this.current_point;
    }
    
    public String getQuestionAnswered() {
        return this.question_answered;
    }
    
    public LocalDate getLastCheckInDate() {
        return this.lastCheckInDate;
    }

}
