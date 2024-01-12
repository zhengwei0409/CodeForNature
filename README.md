# FOP CODE FOR NATURE 3.0
*yea this must be the final version*

In the 3.0 version, we introduced a new feature - global leaderboard.
A special thanks to myself.

In the 2.0 version, we introduced a new feature - password hashing.  
A special thanks to the person who implemented this valuable addition.

This is the complete CLI & GUI code 

There are 6 packages inside the source pakages in this code 
- Default package ( use for storing the images that use in GUI )
- LoginSection
- NewsSection
- PointsSection
- TestingGUICode ( GUI code ) don't ask me why i name it like this cuz initially i really just want to test creating gui and at the end created whole GUI
- Triviasection  

I am using built with Maven , so there is no require jar file for database , if u r copying this code , make sure u use maven otherwise u 自己gaodim  
  
If using maven , there will be a folder call Project Files , inside there has a file named pom.xml  
  
copy the code inside pom.xml  
*but if u r directly cloning , i think it will be included liao*  
  
For database  
execute the sql below to create the same table that this program using    
  
create table UserAccount (  
	id int auto_increment primary key,  
    	email varchar(100),  
    	username varchar(50) unique,  
    	password varchar(255),  
    	current_point int default 0,  
    	registration_date date default (current_date),  
	question_answered varchar(50),  
	last_checkin_date date,  
 	XP int default 0   
);   
   
And remember to change the username to your database username as well as the password  
还有还有 remember change every file path in this code   
  
*run the code in MainTesting.java to execute the GUI program*  
*run the code in Main.java to execute the CLI program* ( also need to change something in ConnectDatabase.java , i included comment at there liao )  
  
Yeahh happy new year everyone ʕ•̫͡•ʔ

