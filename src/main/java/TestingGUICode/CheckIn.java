
package TestingGUICode;

import Triviasection.ConnectDatabase;
import java.time.LocalDate;
import javax.swing.JOptionPane;


public class CheckIn {
    public void checkIn() {
        // connect to database 
        ConnectDatabase database = new ConnectDatabase();
        
        // create an object of main menu class
        MainMenu main = new MainMenu();
        
        // get the last check in date
        LocalDate lastCheckInDate = database.getLastCheckInDate();
        LocalDate currentDate = LocalDate.now();
        
        int points = database.getCurrentPoint();
        
        // check the conditions
        // case 1 : if the user just register his/her account and did not checkin yet
        if(lastCheckInDate == null) {
            points++;
            // update the point in database
            database.updateCurrentPoint(points);
            // update the check in date in database
            database.updateCheckInDate(currentDate);
            // feedback
            JOptionPane.showMessageDialog(null, "You have successfully checked in today !", "Result", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "You receive 1 point !", "Result", JOptionPane.INFORMATION_MESSAGE);
            main.mainMenu();
        }
        // case 2 : if the user had already check in today
        else if (lastCheckInDate.equals(currentDate)) {
            JOptionPane.showMessageDialog(null, "You have already check in today.", "Result", JOptionPane.ERROR_MESSAGE);
            main.mainMenu();
        } 
        // case 3 : if the user had check in previously and did not checkin today yet
        else {
            points++;
            // update the point in database
            database.updateCurrentPoint(points);
            // update the check in date in database
            database.updateCheckInDate(currentDate);
            // feedback
            JOptionPane.showMessageDialog(null, "You have successfully checked in today !", "Result", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "You receive 1 point !", "Result", JOptionPane.INFORMATION_MESSAGE);
            main.mainMenu();
        }
    }
}
