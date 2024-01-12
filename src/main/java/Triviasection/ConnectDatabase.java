
package Triviasection;

import TestingGUICode.Login; // for GUI
// import LoginSection.*; // for CLI
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ConnectDatabase {
    
    // create an object of login class ( to get the user id )
    Login lg = new Login();

    private String userName;
    private String email;
    private LocalDate registrationDate;
    private LocalDate lastCheckInDate;
    private int current_point;
    private int XP;
    private String question_answered;
    private int userId = lg.getUserId();
    private ArrayList<Integer> userXP = new ArrayList<Integer>();
    private ArrayList<String> usernameList = new ArrayList<String>();
    
    // constructor that connect to database and execute query
    public ConnectDatabase() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/testing";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            String sqlQuery = "SELECT id, email, username, registration_date, current_point, question_answered, last_checkin_date, XP FROM UserAccount WHERE id = ?";
            
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
                        this.XP = resultSet.getInt("XP");
                        
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
        String password = "";

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
    
    // method use for update the XP
    public void updateXP(int newXP) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/testing";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            // Update SQL query to set new value for current_point
            String sqlUpdate = "UPDATE UserAccount SET XP = ? WHERE id = ?";

            // Create a PreparedStatement with the SQL update query
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
                // Set the parameters
                preparedStatement.setInt(1, newXP);
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
        String password = "";

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
        String password = "";

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
    
    // method use for getting user XP
    public void getUserXP() {
        
        String jdbcUrl = "jdbc:mysql://localhost:3306/testing";
        String username = "root";
        String password = "";

        
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "SELECT username, XP FROM UserAccount";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String username2 = resultSet.getString("username");
                        usernameList.add(username2);
                        int XP = resultSet.getInt("XP");
                        userXP.add(XP);
                    }
                }
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
    
    public ArrayList<Integer> getUserXPList() {
        return this.userXP;
    }
    
    public ArrayList<String> getUserName() {
        return this.usernameList;
    }
    
    public int getXP() {
        return this.XP;
    }

}
