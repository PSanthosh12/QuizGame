# QuizGame

## What is it?
This is a Quiz program written in Java. It perfroms two main functions:
  1. Adminitser a quiz to a Student
  2. Allow Instructor to edit quiz or find statistics of quiz grades

This program runs via JOptionPane instead of command line outputs. This allows the program to have custom icons, graphic, options, etc.

## How does it work?
### QuizGame.java (main class)
The program starts off with displaying a welcome message and will then ask for the users' username. If the username exists in the class roster (UserInfo.txt), it will store the necesssary information of the user profile (name, username, password, role) and then ask for password. 
  
Using JPasswordField, the charcters of the password typed is hidden and replaced with '*'. The user has three attempts to get the password correct. If the user uses up all 3 tries, an imposter message will display and the program will exit. If the user gets the password correct, the program will check the Role of the user, Instructor or Student.
  
If the user is an Instructor, the program will display two options: "Add Question" or "Display Stats". If the user chose the Add Question option, the program will what question you want to add and the answer for said question. It will then append the new question to TestBank.txt and the answer to Answers.txt. If the user chose Display Stats, it will display a message stating the name of the student with the highest score, the name of the student with the lowest score, and the average score of all current quiz scores.
  
If the user is a Student, the program will run the Quiz by calling the Quiz class and its methods. Then it will write and display a report card for the student by calling the Report class and its methods
  
All ImageIcons used in the main class has their own method. Each method uses the ImageIcon class to use an image as the icon for JOptionPane.showMessageDialog. The image is also scaled to fit the panel box.
  
### Quiz.java (Quiz Class)
The constructor of this class creates a timestamp for the quiz. This is later added to the reports.
  
The RunQuiz method chooses 10 questions at random using line numbers in the TestBank.txt file. It will also retrieve the answer to that question using the same line number in Answers.txt (the line numbers of questions and answers in their respective files corellate). The question will be displayed to the user with a TRUE or FALSE options (those are the only answer options). If the user pick the correct answer, they gain 10 points. This class returns the timestamp and score.

### Report.java (Report Class)
The costructor of this class takes in all the information necesary to make a student report: timestamp, name, username, and score.

The FileReport method takes the information and adds it to StudentReports.txt when called. Each line in StudentReports.txt is a student report.

The DisplayReport method will display a report to the student when called. It will check the student's score and display the appropriate icon and message for their letter grade.

All ImageIcons used in this class has their own methods as well. 

## How to run it?
  1. Make sure you have Java and a Java IDE downloaded
  2. Download ALL files in this repository (with the exception of the README.md) and make sure they are all in the same folder/location
  3. Replace the public static String path in QuizGame.java (line 17) with the path that is specific to your computer
  4. Open QuizGame.java, Quiz.java, and Report.java and run program according to your IDE

#Thank You!
