
package quizgame;

import static quizgame.QuizGame.path;
import java.awt.Image;
import java.io.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Report {
    
    //fields
    private final String SRpath = (path + "StudentReports.txt");
    private final String timestamp;
    private final String name;
    private final String username;
    private final int score;
    
    
    //constructor
    public Report(String ts, String n, String user, int sc){
        //trim everything for formatting
        this.timestamp = ts.trim();
        this.name = n.trim();
        this.username = user;
        this.score = sc;
    }
    
    
    //file report method
    public void FileReport() throws IOException{
        //append student report to SR
        FileWriter SR = new FileWriter(SRpath,true);
        try (PrintWriter Reports = new PrintWriter(SR)) {
            //make sure entire report is on one line
            Reports.printf("%s,%s,%s,%s", timestamp,name,username,score, "\n");
        }
    }
    
    
    //display report method
    public void DisplayReport(){
        //display report to student and display what grade they got via the icon
        if (score >= 90){
            //Grade A
            ImageIcon gradeA = AIcon();
            JOptionPane.showMessageDialog(null,(timestamp + "\n" + name + "\nYou Scored: " + score + "/100"),"Grade Report",JOptionPane.PLAIN_MESSAGE,gradeA);
        } else if (score >= 80 && score <= 89){
            //Grade B
            ImageIcon gradeB = BIcon();
            JOptionPane.showMessageDialog(null,(timestamp + "\n" + name + "\nYou Scored: " + score + "/100"),"Grade Report",JOptionPane.PLAIN_MESSAGE,gradeB);
        } else if (score >= 70 && score <= 79){
            //Grade C
            ImageIcon gradeC= CIcon();
            JOptionPane.showMessageDialog(null,(timestamp + "\n" + name + "\nYou Scored: " + score + "/100"),"Grade Report",JOptionPane.PLAIN_MESSAGE,gradeC);
        } else if (score >= 60 && score <= 69) {
            //Grade D
            ImageIcon gradeD = DIcon();
            JOptionPane.showMessageDialog(null,(timestamp + "\n" + name + "\nYou Scored: " + score + "/100"),"Grade Report",JOptionPane.PLAIN_MESSAGE,gradeD);
        } else if (score <= 59){
            //Grade F
            ImageIcon gradeF = FIcon();
            JOptionPane.showMessageDialog(null,(timestamp + "\n" + name + "\nYou Scored: " + score + "/100"),"Grade Report",JOptionPane.PLAIN_MESSAGE,gradeF);
        }
    }
    
    
    //grade image icons
    public static ImageIcon AIcon(){
        //get image
        String lettera = (path + "letter-a.png");
        ImageIcon icon = new ImageIcon(lettera);
        //resize it
        Image image = icon.getImage();
        Image newimage = image.getScaledInstance(90,90,java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimage);
        return icon;
    }
    
    public static ImageIcon BIcon(){
        //get image
        String letterb = (path + "letter-b.png");
        ImageIcon icon = new ImageIcon(letterb);
        //resize it
        Image image = icon.getImage();
        Image newimage = image.getScaledInstance(90,90,java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimage);
        return icon;
    }
    
    public static ImageIcon CIcon(){
        //get image
        String letterc = (path + "letter-c.png");
        ImageIcon icon = new ImageIcon(letterc);
        //resize it
        Image image = icon.getImage();
        Image newimage = image.getScaledInstance(90,90,java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimage);
        return icon;
    }
    
    public static ImageIcon DIcon(){
        //get image
        String letterd = (path + "letter-d.png");
        ImageIcon icon = new ImageIcon(letterd);
        //resize it
        Image image = icon.getImage();
        Image newimage = image.getScaledInstance(90,90,java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimage);
        return icon;
    }
    
    public static ImageIcon FIcon(){
        //get image
        String letterf = (path + "letter-f.png");
        ImageIcon icon = new ImageIcon(letterf);
        //resize it
        Image image = icon.getImage();
        Image newimage = image.getScaledInstance(90,90,java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimage);
        return icon;
    }
}
