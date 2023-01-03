
package quizgame;

import static quizgame.QuizGame.path;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.text.*;

public class Quiz {
    //fields
    private final String timestamp;
    private final int questions = 10;
    private int score = 0;
    private final String TBpath = (path + "TestBank.txt");
    private final String Anspath = (path + "Answers.txt");

    
    //constructor: find date and time
    public Quiz(){
        //get timestamp when Quiz is called
        Date date = new Date();
        //formatting timestamp to get rid of uneccesary info
        SimpleDateFormat fdate = new SimpleDateFormat("E_MM.dd.yyyy_hh:mm:ss_a");
        timestamp = fdate.format(date);
    }
    
    
    //get timestamp method 
    public String getTime(){
        return timestamp;
    }
    
    
    //questions method
    public void RunQuiz() throws IOException{
        //get number of lines in TestBank
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(TBpath))) {
            while(reader.readLine() != null) lines++;
        }
        //ask the questions
        for (int i = 0; i < questions; i ++){
            //get random line number
            Random rand = new Random();
            int randline = rand.nextInt(lines) + 1;
            //get question from randline in TestBank
            String question = Files.readAllLines(Paths.get(TBpath)).get(randline);
            //get answer from randline in Answers
            String answer = Files.readAllLines(Paths.get(Anspath)).get(randline);
            
            //display question with two answer choices
            String[] options = {"TRUE", "FALSE"};
            int useranswer = JOptionPane.showOptionDialog(null, question, ("Question #" + (i+1)), JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            //return 0: user choose TRUE, return 1: usr choose FALSE
            
            if (useranswer == 0) {
                //if user choose TRUE and the answer is TRUE, add 10 points to score
                if (answer.equals("TRUE")){
                    score += 10;
                }
            } else {
                //if user choose FALSE and the answer is FALSE, add 10 points to score
                if (answer.equals("FALSE")){
                    score += 10;
                }
            }
            
            //if score ends up negative, reset to 0 (for grading purposes) 
            if (score < 0) {
                score = 0;
            }
        }
    }
    
    
    //get score method
    public int getScore(){
        return score;
    }
    
}
