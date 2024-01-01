
package LoginSection;

import Triviasection.ConnectDatabase;
import java.time.LocalDate;

public class User {
    
    // connect to database
    ConnectDatabase database = new ConnectDatabase();

    // instance variable for storing all the details of a user
    private int id = database.getID();
    private String email = database.getEmail();
    private String username = database.getUsername();
    private int currentPoints = database.getCurrentPoint();
    private LocalDate registrationDate = database.getResgistrationDate();

    
    // accessor
    public int getID() {
        return this.id;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getUsername() {
        return this.username;
    }

    public LocalDate getResgistrationDate() {
        return this.registrationDate;
    }
    
    public int getCurrentPoint() {
        return this.currentPoints;
    }
    
}
