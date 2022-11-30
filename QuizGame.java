//Fix FileReport method: the timestamp prints on a different line than rest of the report (there's something wrong with the format of the timestamp)

package quizgame;

//@author PSanthosh12

import java.awt.HeadlessException;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

public class QuizGame {
    
    //path for all files: used for all classes
    //this is specific to my computer. If you want to run this program, replace with the path of wherever you download all these files 
    public static String path = "C:\\Users\\parus\\OneDrive\\Desktop\\Academics\\Fall 2023\\Programming 2\\NetBeansProjects\\FinalProject\\src\\finalproject\\";
    
    public static void main(String[] args) throws IOException {
        
        //Welcome message w/hellbot icon
        ImageIcon helloicon = HelloIcon();
        JOptionPane.showMessageDialog(null,"Welcome!","Welcome Page",JOptionPane.PLAIN_MESSAGE,helloicon);
        
        //Prompt user for username
        String username = JOptionPane.showInputDialog("What is your username?");
        
        //checker variables for finding username and password attempts
        boolean found = false;
        int tries = 3;
        
        //login: check for username and password, get role
        try{
            //open UserInfo
            String UIpath = (path + "UsersInfo.txt");
            try (Scanner userinfo = new Scanner(new File(UIpath))) {
                //go line by line until you find username, store info from that line
                userinfo.useDelimiter("[,]");     //use comma to seperate variables
                while(userinfo.hasNext() && !found){
                    //storing info in each line (if username found, it will have the contents from that line)
                    String Fname = userinfo.next();
                    String Lname = userinfo.next();
                    String User = userinfo.next();
                    String Pass = userinfo.next();
                    String Role = userinfo.next();
                    //if username is found
                    if(User.equals(username)){
                        found = true;
                        //ask for password
                        //replace characters with *
                        JPasswordField pwd = new JPasswordField();
                        pwd.setEchoChar('*');
                        int pass = JOptionPane.showConfirmDialog(null, pwd, "What is your password?",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        String password = "";
                        if (pass == JOptionPane.OK_OPTION){
                            password = new String(pwd.getPassword());
                        }
                        //check if password is correct & give 3 tries
                        while (!password.equals(Pass) && tries != 0){
                            //print Invalid if password wrong & take away try
                            JOptionPane.showMessageDialog(null, "Invalid password");
                            tries -= 1;
                            //exit program if there are no more tries left
                            if (tries == 0){
                                //display exit message w/ iposter icon
                                ImageIcon impicon = ImposterIcon();
                                JOptionPane.showMessageDialog(null,"IMPOSTER! \n You are not allowed in.","Imposter",JOptionPane.PLAIN_MESSAGE,impicon);
                                System.exit(0);
                            }
                            //ask again if there are tries left (continue to hide characters)
                            pass = JOptionPane.showConfirmDialog(null, pwd, "What is your password?",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            if (pass == JOptionPane.OK_OPTION){
                                password = new String(pwd.getPassword());
                            }
                        }
                        
                        //if password correct, check role, run appropriate method
                        if (Role.equals("Instructor")){
                            //call instructor method
                            Instructor();
                        }else{
                            //call student method and pass(username, fname, lname)
                            Student(User,Fname,Lname);
                        }    
                    }
                }
            }
        } catch (HeadlessException e) {
            System.out.println("Error");
        }
    }
    
    
    //STUDENT ------------------------------------------------------------------------------------------------------------------
    
    //student method
    public static void Student(String username, String fname, String lname) throws IOException{
        //call quiz class & store returned timestamp and score
        Quiz quiz = new Quiz();
        quiz.RunQuiz();
        String timestamp = quiz.getTime();
        int score = quiz.getScore();
        //Call Report(pass firstname, lastname, username, timestamp, score)
        String name = fname + "_" + lname;
        Report report = new Report(timestamp, name, username, score);
        report.FileReport();
        report.DisplayReport();
    }
    
    
    //INSTRUCTOR -----------------------------------------------------------------------------------------------------------------
    
    //instructor method
    public static void Instructor() throws IOException{
        //display two options in joptionpane
        String[] options = {"Add Question", "Display Stats"};
        int jop = JOptionPane.showOptionDialog(null, "What would you like to do?", "Instructor Panel", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        //Add Question returns 0; Display Stats returns 1
        if (jop == 0) {
            //Add Questions: call Question method
            Question();
        } else {
            //Display Stats: call DisplayStats method
            DisplayStats();
        }
        
    }
    
    //add question method
    public static void Question()throws IOException {
        //what question do you want to add?
        String question = JOptionPane.showInputDialog("What question do you want to add?");
        
        //append question to TestBank.txt
        String TBpath = (path + "TestBank.txt");
        FileWriter TB = new FileWriter(TBpath,true);
        try (PrintWriter atb = new PrintWriter(TB)) {
            atb.println("\n" + question);   //added extra line so format works out better
        }
        
        //what is the answer to the question?
        String[] options = { "TRUE", "FALSE"};
        int answer = JOptionPane.showOptionDialog(null, "What is the answer to the question?", "Adding Test Question", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        
        //append TRUE or FALSE to Answers.txt
        String Apath = (path + "Answers.txt");
        FileWriter A = new FileWriter(Apath,true);
        try (PrintWriter aa = new PrintWriter(A)) {
            if (answer == 0){
                //append TRUE to Answers.txt
                aa.println("TRUE");
            } else {
                //append FALSE to Answers.txt
                aa.println("FALSE");
            }
        }
        
    }
    
    //display stats method
    public static void DisplayStats() throws IOException {
        //variables for person with highest or lowest score
        int max = 0;
        String highest = "";
        int min = 101;
        String lowest = "";
        
        //variables for calculating average
        int sum = 0;
        int lines = 0;
        
        String SRpath = (path + "StudentReports.txt");
        
        //find number of lines in SR (also number of students that have taken the exam, needed for average calculation)
        try (BufferedReader reader = new BufferedReader(new FileReader(SRpath))) {
            while(reader.readLine() != null) lines++;
        }
        
        //find highest score, lowest score, and sum of scores
        try (Scanner reports = new Scanner(new File(SRpath))) {
            while(reports.hasNext()){
                //take in each line (report) and split up info
                String line = reports.next();
                String[] reps = line.split(",");
                //single out the score (parse as int since it comes out of the array as String)
                int score = Integer.parseInt(reps[3]);
                //add score to sum
                sum = sum + score;
                //check if score is max score, set highest as the name of student who got that score
                if (score > max){
                    max = score;
                    highest = reps[1];
                //check if score is min score, set lowest as the name of student who got that score
                } else if (score < min) {
                    min = score;
                    lowest = reps[1];
                }
            }
         }
        
        //calculate average
        int average = sum / lines;
        
        //display stats w/ stats icon
        ImageIcon stats = StatsIcon();
        JOptionPane.showMessageDialog(null,("Highest Score: " + highest + "\n" + "Lowest Score: " + lowest + "\n" + "Average Score: " + average),"Display Stats",JOptionPane.PLAIN_MESSAGE,stats);

        
    }
    
    
    //IMAGE ICONS ------------------------------------------------------------------------------------------------------------------
    public static ImageIcon HelloIcon(){
        //get image
        String hellobot = (path + "hellobot.png");
        ImageIcon icon = new ImageIcon(hellobot);
        //resize it
        Image image = icon.getImage();
        Image newimage = image.getScaledInstance(120,100,java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimage);
        return icon;
    }
    
    public static ImageIcon ImposterIcon(){
        //get image
        String imposter = (path + "imposter.png");
        ImageIcon icon = new ImageIcon(imposter);
        //resize it
        Image image = icon.getImage();
        Image newimage = image.getScaledInstance(60,50,java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimage);
        return icon;
    }
    
    public static ImageIcon StatsIcon(){
        //get image
        String stats = (path + "analytics.png");
        ImageIcon icon = new ImageIcon(stats);
        //resize it
        Image image = icon.getImage();
        Image newimage = image.getScaledInstance(60,50,java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimage);
        return icon;
    }
}

