import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import io.netty.util.internal.ThreadLocalRandom;

public class Nallas_demo {

	public static String status = "Yet to start";
	public static JLabel num_status;
	public static JLabel guess_status;
	public static JLabel remaining_count;
	public static String user_status ="Yet to start";
	public static  int count=5;
	public static JTextField userEnt;
	public static int random_numner=0;
	public static String user_guess;
	public static String guess_satus = "Failed";
	
	public static JButton number;
	public static JButton subm;
	public static void main(String[] args) 
	{
	     //---Javaframe and java Panel intialization----
		JFrame frame = new JFrame();
		frame.setTitle("-----Welcome to NALLAS-----");
		
		JPanel pane1 = new JPanel();
		
		//---to identify system resolution----
		int[] scrn_resln = system_resolution();
		 int width = scrn_resln[0];
		 int height = scrn_resln[1];
		 frame.setSize(width/2,height/2);
		//---Element initialization----
         number = new JButton("Generate Number");
         subm = new JButton("Submit");
        JLabel user = new JLabel("Guess your number(1 to 10): ");
        userEnt = new JTextField("", 5);
        num_status = new JLabel("<html><br>"+"Number Generation: **-- "+status+" --**</html>");
        guess_status = new JLabel("<html><br>"+"User Guess: **-- " +user_status+" --**</html>");
        remaining_count =  new JLabel("<html><br>"+"User guess remaining count:(" +count+")</html>");
      
        //---added Element to jpanel----
        pane1.add(number);
        pane1.add(user);
        pane1.add(userEnt);
        pane1.add(subm);
        pane1.add(num_status);
        pane1.add(guess_status);
        pane1.add(remaining_count);
        
        //---added jpanel  to jframe----
        frame.add(pane1);
        subm.setEnabled(false);
        frame.setVisible(true);
      
        //----random number generation---
        number.addActionListener(new ActionListener(){  
			  public void actionPerformed(ActionEvent e){
				 
				  random_numner = ThreadLocalRandom.current().nextInt(0,10);
				  
				  System.out.println(random_numner);
				  System.out.println("test");
				  status = "Completed";
				  num_status.setText("<html><br>"+"Number Generation: **-- "+status+"-- **</html>");
				  num_status.setBackground(Color.green);
				  num_status.setOpaque(true);
				  number.setEnabled(false);
				  subm.setEnabled(true);}  
		});  
        
        //----user input and system generated number comparison---
        subm.addActionListener(new ActionListener(){  
			  public void actionPerformed(ActionEvent e){
				  user_guess = userEnt.getText();
				  System.out.println("guess"+user_guess);
				  Boolean valid_data = check_input(user_guess);
				  System.out.println("guess"+valid_data);
				  
				  if(user_guess.equalsIgnoreCase("")||user_guess.equalsIgnoreCase(null)||valid_data==false)
				  { 
					  user_status ="Invalid data";
					  guess_status.setText("<html><br>"+"User Guess: **-- " +user_status+" --**</html>");
					  count = count-1;
					  System.out.println("if"+count);
					  remaining_count.setText("<html><br>"+"User guess remaining count:(" +count+")</html>");
					  guess_status.setBackground(Color.RED);
					  guess_status.setOpaque(true);
					  userEnt.setText("");
				  }
				  else
				  {
					  int userdata= Integer.parseInt(user_guess);
					 String comp_status = input_compare(userdata);
					 
					  count = count-1;
					  System.out.println("else"+count);
					  
					  guess_status.setText("<html><br>"+"User Guess: **--" +comp_status+"--**</html>");
					  remaining_count.setText("<html><br>"+"User guess remaining count :( " +count+" )</html>");
					  if(!comp_status.contains("Matched"))
					  {
						  guess_status.setBackground(Color.YELLOW);
						  guess_status.setOpaque(true);
			
						  userEnt.setText("");
					  }
					  else
					  {
						  guess_status.setBackground(Color.GREEN);
						  guess_status.setOpaque(true);
						  subm.setEnabled(false);
						  guess_satus = "Success";
						  JOptionPane.showMessageDialog(null, "User guess Status :---" +guess_satus +"----","Thank you for your participation", JOptionPane.PLAIN_MESSAGE);
						  System.exit(1);
					  }
					  
					 
				  }
				  if(count==0)
				  {
					  JOptionPane.showMessageDialog(null,"User guess Status:---"  +guess_satus+"----","Thank you for your participation", JOptionPane.PLAIN_MESSAGE);
					  subm.setEnabled(false);
					  System.exit(1);
				  }
				 
		}
		}); 
	}
	
	public static String input_compare(int value)
	{
		String status;
		if(value<random_numner)
		{
			status = "Your guess is Low";
		}
		else if(value>random_numner)
		{
			status = "Your guess is High";
		}
		else
		{
			status = "Matched";
		}
		
		return status;
		
	}
	
	 public static boolean check_input(String value)
     {
  	  
  	   String regex = ".*[0-9].*";
	  Pattern ptn = Pattern.compile(regex);
	  Matcher match_status = ptn.matcher(value);
	  boolean isMatched = match_status.matches();
		 return isMatched;
     }
	
	public static int[] system_resolution()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		
		int height = (int)screenSize.getHeight();
		int[] val = {width,height};
		return val;
		
	}
	
	
	

}
