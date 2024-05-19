import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class GUI {
     
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
 
   public GUI(){
      prepareGUI();
   }
 
   public static int count = 0;
   public static int arrayCol = 0;
   public static int arrayRow = 0;
   public static int player = 0;
   public static boolean wait = true;
   public static int playerX;
   public static int playerY;
   
   public static JRadioButton[][] charArray11 = new JRadioButton[3][3];//r c
   public static JRadioButton[][] charArray12 = new JRadioButton[3][3]; 
   public static JRadioButton[][] charArray13 = new JRadioButton[3][3];
   
   public static JRadioButton[][] charArray21 = new JRadioButton[3][3]; 
   public static JRadioButton[][] charArray22 = new JRadioButton[3][3]; 
   public static JRadioButton[][] charArray23 = new JRadioButton[3][3]; 
   
   public static JRadioButton[][] charArray31 = new JRadioButton[3][3]; 
   public static JRadioButton[][] charArray32 = new JRadioButton[3][3]; 
   public static JRadioButton[][] charArray33 = new JRadioButton[3][3]; 
   
   public static int[][] winArray = new int[3][3]; 
   
   public static void start()
   {
	   
	  GUI swingControlDemo = new GUI(); 
      ButtonGroup group = new ButtonGroup();
      
      for(int i = 0; i < 9; i++)
      {
          for(int j = 0; j < 2; j++)
          {
        	  swingControlDemo.showRadioButtonDemo(group,false,false);
          }
          swingControlDemo.showRadioButtonDemo(group,false,true);
          for(int j = 0; j < 2; j++)
          {
        	  swingControlDemo.showRadioButtonDemo(group,false,false);
          }
          swingControlDemo.showRadioButtonDemo(group,false,true);
          for(int j = 0; j < 2; j++)
          {
        	  swingControlDemo.showRadioButtonDemo(group,false,false);
          }
          count++;
          swingControlDemo.showRadioButtonDemo(group,true,true);
          
    	  
      }
      
   }
   
   public static void AICLICK(int bigX, int bigY,int smallX, int smallY)
   {
	   if(bigX == 0 && bigY == 0)
	   {
		   charArray11[smallY][smallX].doClick();
		   
	   }else if (bigX == 0 && bigY == 1)
	   {
		   charArray21[smallY][smallX].doClick();
		   
	   }else if (bigX == 0 && bigY == 2)
	   {
		   charArray31[smallY][smallX].doClick();
		   
	   }else if (bigX == 1 && bigY == 0)
	   {
		   charArray12[smallY][smallX].doClick();
		   
	   }else if (bigX == 1 && bigY == 1)
	   {
		   charArray22[smallY][smallX].doClick();
		   
	   }else if (bigX == 1 && bigY == 2)
	   {
		   charArray32[smallY][smallX].doClick();
		   
	   }else if (bigX == 2 && bigY ==0)
	   {
		   charArray13[smallY][smallX].doClick();
		   
	   }else if (bigX == 2 && bigY == 1)
	   {
		   charArray23[smallY][smallX].doClick();
		   
	   }else if (bigX == 2 && bigY == 2)
	   {
		   charArray33[smallY][smallX].doClick();
	   }
	   
	   
   }
 
   private void prepareGUI(){
        
      mainFrame = new JFrame("Java Radio Button Swing Examples");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(1, 12));
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    
 
      statusLabel.setSize(350,100);
 
      controlPanel = new JPanel();
      //controlPanel.setLayout(new FlowLayout());
      controlPanel.setLayout(new GridLayout(12,1));
 
      //mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      //mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }
 
   private void showRadioButtonDemo(ButtonGroup group, boolean line, boolean space){
      headerLabel.setText("Control in action: RadioButton"); 
      
      JRadioButton radDog = new JRadioButton(" ");
    
      radDog.setMnemonic(KeyEvent.VK_C);

 
      radDog.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
        	 
        	 wait = true;
        	 if(player == 0)
        	 {
        		 
        		 radDog.setText("X");
        		 player++;
        	 }else
        	 {
        		 
        		 radDog.setText("O");
        		 player--;
        	 }
        	 
        	 if(charArray11[0][0].equals(radDog))
        	 {
        		 playerX = 0;
        		 playerY = 0;
        		 wait = false;
        		 System.out.println("It was here yeyeyeyye");
        	 }
        	
        	 //to send info to main
        	if(player == 0)
        	{
	         	for(int r = 0; r < 3; r++)
	         	{
	             	 for(int c = 0; c < 3; c++)
	             	 {
	                	 if(charArray11[r][c].equals(radDog))
	                	 {
	                		 playerX = c;
	                		 playerY = r; 
	                		 wait = false;
	                	 }
	                	 if(charArray12[r][c].equals(radDog))
	                	 {
	                		 playerX = c;
	                		 playerY = r; 
	                		 wait = false;
	                	 }
	                	 if(charArray13[r][c].equals(radDog))
	                	 {
	                		 playerX = c;
	                		 playerY = r; 
	                		 wait = false;
	                	 }
	                	 
	                	 if(charArray21[r][c].equals(radDog))
	                	 {
	                		 playerX = c;
	                		 playerY = r;  
	                		 wait = false;
	                	 }
	                	 if(charArray22[r][c].equals(radDog))
	                	 {
	                		 playerX = c;
	                		 playerY = r;  
	                		 wait = false;
	                	 }
	                	 if(charArray23[r][c].equals(radDog))
	                	 {
	                		 playerX = c;
	                		 playerY = r; 
	                		 wait = false;
	                	 }
	                	 
	                	 if(charArray31[r][c].equals(radDog))
	                	 {
	                		 playerX = c;
	                		 playerY = r;  
	                		 wait = false;
	                	 }
	                	 if(charArray32[r][c].equals(radDog))
	                	 {
	                		 playerX = c;
	                		 playerY = r; 
	                		 wait = false;
	                	 }
	                	 if(charArray33[r][c].equals(radDog))
	                	 {
	                		 playerX = c;
	                		 playerY = r;   
	                		 wait = false;
	                	 }
	             		 
	             	 }
	         	}
	         	
        	} 


        	 
        	 
           	 //checkWin(charArray11);
           	 //checkWin(charArray12);
           	 //checkWin(charArray13);
           	 
           	 //checkWin(charArray21);

        	 
        	 
   
           	 
           	 //checkWin(charArray23);
           	 
           	 //checkWin(charArray31);
           	 //checkWin(charArray32);
           	 //checkWin(charArray33);
     

        	 JRadioButton[][] tempArray = new JRadioButton[3][3]; 
        	 int row = 0;
        	 int col = 0;
        	 Object property = radDog.getClientProperty("row");
        	 if (property instanceof Integer) {
        	    row = ((Integer)property);
        	    // do stuff
        	 }
        	 Object property2 = radDog.getClientProperty("col");
        	 if (property2 instanceof Integer) {
        	    col = ((Integer)property2);
        	    // do stuff
        	 }
        	 
        	 
         	for(int r = 0; r < 3; r++)
         	{
             	 for(int c = 0; c < 3; c++)
             	 {
             		 charArray11[r][c].setEnabled(false);
             		 charArray12[r][c].setEnabled(false);
             		 charArray13[r][c].setEnabled(false);
             		 
             		 charArray21[r][c].setEnabled(false);
             		 charArray22[r][c].setEnabled(false);
             		 charArray23[r][c].setEnabled(false);
             		 
             		 charArray31[r][c].setEnabled(false);
             		 charArray32[r][c].setEnabled(false);
             		 charArray33[r][c].setEnabled(false);
             			 
             	 } 
         	 }
         	
         	
         	
         	boolean full = false;
         	int win = 0;
         	for(int r = 0; r < 3; r++)
         	{
             	 for(int c = 0; c < 3; c++)
             	 {
        	 
		        	 if(col == 0)
		             {
		                 if(row == 0)
		                 {
		               	  	charArray11[r][c].setEnabled(true);
		               	  	full = checkFull(charArray11);
		               	  	win = checkWin(charArray11);
		               	  	tempArray = charArray11;
		               	 if(win != 0)
		               	  	{
		               		 	//System.out.println("WINNNNNNNNNN");
		               		 	winArray[0][0] = win;
		               	  	}
		                 }else
		                 if(row == 1)
		                 {
		               	  	charArray21[r][c].setEnabled(true);
		               	  	full = checkFull(charArray21);
		               	 win = checkWin(charArray21);
		               	tempArray = charArray21;
		               	if(win != 0)
	               	  	{
		               	winArray[1][0] = win;
	               	  	}
		                 }else
		                 if(row == 2)
		                 {
		               	  	charArray31[r][c].setEnabled(true);
		               	  	full = checkFull(charArray31);
		               	 win = checkWin(charArray31);
		               	tempArray = charArray31;
		               	if(win != 0)
	               	  	{
		               	winArray[2][0] = win;
	               	  	}
		                 }
		           	  
		             }
		        	 
		        	 if(col == 1)
		             {
		                 if(row == 0)
		                 {
		               	  	charArray12[r][c].setEnabled(true);
		               	 full = checkFull(charArray12);
		               	win = checkWin(charArray12);
		               	tempArray = charArray12;
		               	if(win != 0)
	               	  	{
		               	winArray[0][1] = win;
	               	  	}
		                 }else
		                 if(row == 1)
		                 {
		               	  	charArray22[r][c].setEnabled(true);
		               	 full = checkFull(charArray22);
		               	win = checkWin(charArray22);
		               	tempArray = charArray22;
		               	//System.out.println("asdasdasdasd" + win);
		               	if(win != 0)
	               	  	{
		               	winArray[1][1] = win;
	               	  	}
		                 }else
		                 if(row == 2)
		                 {
		               	  	charArray32[r][c].setEnabled(true);
		               	 full = checkFull(charArray32);
		               	win = checkWin(charArray32);
		               	tempArray = charArray32;
		               	if(win != 0)
	               	  	{
		               	winArray[2][1] = win;
	               	  	}
		                 }
		           	  
		             }
		        	 
		        	 if(col == 2)
		             {
		                 if(row == 0)
		                 {
		               	  	charArray13[r][c].setEnabled(true);
		               	  	full = checkFull(charArray13);
		               	 	win = checkWin(charArray13);
		               	 tempArray = charArray13;
		               	if(win != 0)
	               	  	{
		               	winArray[0][2] = win;
	               	  	}
		               	
		                 }else
		                 if(row == 1)
		                 {
		               	  	charArray23[r][c].setEnabled(true);
		               	  	full = checkFull(charArray23);
		               	 	win = checkWin(charArray23);
		               		tempArray = charArray23;
		               		if(win != 0)
		               	  	{
		               		winArray[1][2] = win;
		               	  	}
		                 }else
		                 if(row == 2)
		                 {
		               	  	charArray33[r][c].setEnabled(true);
		               	  	full = checkFull(charArray33);
		               	  	win = checkWin(charArray33);
		               	  	tempArray = charArray33;
		               	  	if(win != 0)
		               	  	{
		               	  		
		               	  	
		               	  	winArray[2][2] = win;
		               	  	}
		                 }
		           	  
		             }
		             
        	 
             	 }
             }
         	
         	if(win !=0)
         	{
         		//System.out.println("yes win yet");
         	}else
         	{
         		//System.out.println("No win yet");
         	}
         	
     	   //System.out.println();
    		for(int r = 0; r < 3; r++)
        	{
            	 for(int c = 0; c < 3; c++)
            	 {
            		//System.out.print(winArray[0][0] + " ");
            	 }
            	//System.out.println();
        	}
         	
         	int x = checkfinalwin(winArray);
         	
         	//System.out.println(x);
         	
         	if(full || win != 0)
         	{
         		for(int r = 0; r < 3; r++)
             	{
                 	 for(int c = 0; c < 3; c++)
                 	 {
                 		 charArray11[r][c].setEnabled(true);
                 		 charArray12[r][c].setEnabled(true);
                 		 charArray13[r][c].setEnabled(true);
                 		 
                 		 charArray21[r][c].setEnabled(true);
                 		 charArray22[r][c].setEnabled(true);
                 		 charArray23[r][c].setEnabled(true);
                 		 
                 		 charArray31[r][c].setEnabled(true);
                 		 charArray32[r][c].setEnabled(true);
                 		 charArray33[r][c].setEnabled(true);
                 		 
                 		setdisEn(tempArray, false);
                 			 
                 	 }
             		 
             	 }
         	}
         	

         	
  
           	//unwait

         	
        	 
        	 


        		 
        	 
        	
            
         }           
      });
 
       
      //Group the radio buttons.
      //group.add(radDog);
      
      controlPanel.add(radDog);
      //System.out.println(arrayRow + " " + arrayCol);
      if(arrayCol == 0 || arrayCol == 1 || arrayCol == 2)
      {
          if(arrayRow == 0 || arrayRow == 1 || arrayRow == 2)
          {
        	  charArray11[arrayRow][arrayCol] = radDog;
          }
          if(arrayRow == 3 || arrayRow == 4 || arrayRow == 5)
          {
        	  charArray21[arrayRow%3][arrayCol] = radDog;
        	  
     
          }
          if(arrayRow == 6 || arrayRow == 7 || arrayRow == 8)
          {
        	  charArray31[arrayRow%3][arrayCol] = radDog;
        	 
      
          }
    	  
      }
      
      if(arrayCol == 3 || arrayCol == 4 || arrayCol == 5)
      {
          if(arrayRow == 0 || arrayRow == 1 || arrayRow == 2)
          {
        	  charArray12[arrayRow%3][arrayCol%3] = radDog;
        	
          }
          if(arrayRow == 3 || arrayRow == 4 || arrayRow == 5)
          {
        	  charArray22[arrayRow%3][arrayCol%3] = radDog;
        	  
          }
          if(arrayRow == 6 || arrayRow == 7 || arrayRow == 8)
          {
        	  charArray32[arrayRow%3][arrayCol%3] = radDog;
        	  
          }
      }
      
      if(arrayCol == 6 || arrayCol == 7 || arrayCol == 8)
      {
          if(arrayRow == 0 || arrayRow == 1 || arrayRow == 2)
          {
        	  charArray13[arrayRow%3][arrayCol%3] = radDog;
        	
          }
          if(arrayRow == 3 || arrayRow == 4 || arrayRow == 5)
          {
        	  charArray23[arrayRow%3][arrayCol%3] = radDog;
        	  
          }
          if(arrayRow == 6 || arrayRow == 7 || arrayRow == 8)
          {
        	  charArray33[arrayRow%3][arrayCol%3] = radDog;
        	
          }
    	  
      }
      
	  radDog.putClientProperty("row", arrayRow%3);
	  radDog.putClientProperty("col", arrayCol%3);
      
      arrayCol++;
      
      if(space && line)
      {
    	  JLabel spaceLabel = new JLabel("  "); 
    	  controlPanel.add(spaceLabel);
    	  arrayRow++;
    	  arrayCol = 0;
      }else
      if(space)
      {
    	  JLabel spaceLabel = new JLabel(" | "); 
    	  controlPanel.add(spaceLabel);
      }
     
      
      if(line && count == 3)
      {
    	  count = 0;
    	  
    	  //System.out.println("line");
    	  for(int i = 0; i < 12; i++)
    	  {
    		 JLabel lineLabel2 = new JLabel("\n-----------------------"); 
       	  	controlPanel.add(lineLabel2); 
    	  }
    	  
      }
      
      
 
      mainFrame.setVisible(true);  
   }
   
   public boolean checkFull(JRadioButton[][] array)
   {
	   int counter2 = 0;
	   	for(int r = 0; r < 3; r++)
	 	{
	     	 for(int c = 0; c < 3; c++)
	     	 {
	     		
	     		 if(!array[r][c].getText().equals(" "))
	     		 {
	     			 counter2++;
	     		 }
	     		 
	     				
	     	 }
	 	}
	   	
	   	if(counter2 == 9)
	   	{
	   		
	   		return true;
	   	}
	   	return false;
     	 
   }
   
   public int checkWin(JRadioButton[][] array)
   {
	   String charX = "X";
	   
 		
 		
	   if(array[0][0].getText().equals(charX) && array[0][1].getText().equals(charX) && array[0][2].getText().equals(charX))
	   {

		   array[0][0].setForeground(Color.RED);
		   array[0][1].setForeground(Color.RED);
		   array[0][2].setForeground(Color.RED);
		   
		   return 1;
	   }
	   if(array[1][0].getText().equals(charX) && array[1][1].getText().equals(charX) && array[1][2].getText().equals(charX))
	   {
		   array[1][0].setForeground(Color.RED);
		   array[1][1].setForeground(Color.RED);
		   array[1][2].setForeground(Color.RED);
		   return 1;
	   }
	   if(array[2][0].getText().equals(charX) && array[2][1].getText().equals(charX) && array[2][2].getText().equals(charX))
	   {
		   array[2][0].setForeground(Color.RED);
		   array[2][1].setForeground(Color.RED);
		   array[2][2].setForeground(Color.RED);
		   return 1;
	   }
	   
	   if(array[0][0].getText().equals(charX) && array[1][0].getText().equals(charX) && array[2][0].getText().equals(charX))
	   {
		   array[0][0].setForeground(Color.RED);
		   array[1][0].setForeground(Color.RED);
		   array[2][0].setForeground(Color.RED);
		   return 1;
	   }
	   if(array[0][1].getText().equals(charX) && array[1][1].getText().equals(charX) && array[2][1].getText().equals(charX))
	   {
		   array[0][1].setForeground(Color.RED);
		   array[1][1].setForeground(Color.RED);
		   array[2][1].setForeground(Color.RED);
		   return 1;
	   }
	   if(array[0][2].getText().equals(charX) && array[1][2].getText().equals(charX) && array[2][2].getText().equals(charX))
	   {
		   array[0][2].setForeground(Color.RED);
		   array[1][2].setForeground(Color.RED);
		   array[2][2].setForeground(Color.RED);
		   return 1;
	   }
	   
	   if(array[0][0].getText().equals(charX) && array[1][1].getText().equals(charX) && array[2][2].getText().equals(charX))
	   {
		   array[0][0].setForeground(Color.RED);
		   array[1][1].setForeground(Color.RED);
		   array[2][2].setForeground(Color.RED);
		   return 1;
	   }
	   if(array[0][2].getText().equals(charX) && array[2][0].getText().equals(charX) && array[1][1].getText().equals(charX))
	   {
		   array[0][2].setForeground(Color.RED);
		   array[1][1].setForeground(Color.RED);
		   array[2][0].setForeground(Color.RED);
		   return 1;
	   }
	   
	   
	   
	   charX = "O";
	   if(array[0][0].getText().equals(charX) && array[0][1].getText().equals(charX) && array[0][2].getText().equals(charX))
	   {

		   array[0][0].setForeground(Color.RED);
		   array[0][1].setForeground(Color.RED);
		   array[0][2].setForeground(Color.RED);
		   
		   return 2;
	   }
	   if(array[1][0].getText().equals(charX) && array[1][1].getText().equals(charX) && array[1][2].getText().equals(charX))
	   {
		   array[1][0].setForeground(Color.RED);
		   array[1][1].setForeground(Color.RED);
		   array[1][2].setForeground(Color.RED);
		   return 2;
	   }
	   if(array[2][0].getText().equals(charX) && array[2][1].getText().equals(charX) && array[2][2].getText().equals(charX))
	   {
		   array[2][0].setForeground(Color.RED);
		   array[2][1].setForeground(Color.RED);
		   array[2][2].setForeground(Color.RED);
		   return 2;
	   }
	   
	   if(array[0][0].getText().equals(charX) && array[1][0].getText().equals(charX) && array[2][0].getText().equals(charX))
	   {
		   array[0][0].setForeground(Color.RED);
		   array[1][0].setForeground(Color.RED);
		   array[2][0].setForeground(Color.RED);
		   return 2;
	   }
	   if(array[0][1].getText().equals(charX) && array[1][1].getText().equals(charX) && array[2][1].getText().equals(charX))
	   {
		   array[0][1].setForeground(Color.RED);
		   array[1][1].setForeground(Color.RED);
		   array[2][1].setForeground(Color.RED);
		   return 2;
	   }
	   if(array[0][2].getText().equals(charX) && array[1][2].getText().equals(charX) && array[2][2].getText().equals(charX))
	   {
		   array[0][2].setForeground(Color.RED);
		   array[1][2].setForeground(Color.RED);
		   array[2][2].setForeground(Color.RED);
		   return 2;
	   }
	   
	   if(array[0][0].getText().equals(charX) && array[1][1].getText().equals(charX) && array[2][2].getText().equals(charX))
	   {
		   
		   array[0][0].setForeground(Color.RED);
		   array[1][1].setForeground(Color.RED);
		   array[2][2].setForeground(Color.RED);
		   return 2;
	   }
	   if(array[0][2].getText().equals(charX) && array[2][0].getText().equals(charX) && array[1][1].getText().equals(charX))
	   {
		   array[0][2].setForeground(Color.RED);
		   array[1][1].setForeground(Color.RED);
		   array[2][0].setForeground(Color.RED);
		   return 2;
	   }

	   return 0;
   }
   
   public int checkfinalwin(int[][] array)
   {
	   //System.out.println();
 		for(int r = 0; r < 3; r++)
     	{
         	 for(int c = 0; c < 3; c++)
         	 {
         		//System.out.print(array[0][0] + " ");
         	 }
         	//System.out.println();
     	}
 		
	   int charX = 1;
	   
	   if(array[0][0] == charX && array[0][1]== charX && array[0][2]== charX)
	   {


		   return 1;
	   }
	   if(array[1][0]== charX && array[1][1]== charX && array[1][2]== charX)
	   {

		   return 1;
	   }
	   if(array[2][0]== charX && array[2][1]== charX && array[2][2]== charX)
	   {
		   return 1;
	   }
	   
	   if(array[0][0]== charX && array[1][0]== charX && array[2][0]== charX)
	   {

		   return 1;
	   }
	   if(array[0][1]== charX && array[1][1]== charX && array[2][1]== charX)
	   {

		   return 1;
	   }
	   if(array[0][2]== charX && array[1][2]== charX && array[2][2]== charX)
	   {

		   return 1;
	   }
	   
	   if(array[0][0]== charX && array[1][1]== charX && array[2][2]== charX)
	   {

		   return 1;
	   }
	   if(array[0][2]== charX && array[2][0]== charX && array[1][1]== charX)
	   {

		   return 1;
	   }
	   
	   
	   
	   charX = 2;
	   if(array[0][0] == charX && array[0][1]== charX && array[0][2]== charX)
	   {


		   return 2;
	   }
	   if(array[1][0]== charX && array[1][1]== charX && array[1][2]== charX)
	   {

		   return 2;
	   }
	   if(array[2][0]== charX && array[2][1]== charX && array[2][2]== charX)
	   {
		   return 2;
	   }
	   
	   if(array[0][0]== charX && array[1][0]== charX && array[2][0]== charX)
	   {

		   return 2;
	   }
	   if(array[0][1]== charX && array[1][1]== charX && array[2][1]== charX)
	   {

		   return 2;
	   }
	   if(array[0][2]== charX && array[1][2]== charX && array[2][2]== charX)
	   {

		   return 2;
	   }
	   
	   if(array[0][0]== charX && array[1][1]== charX && array[2][2]== charX)
	   {

		   return 2;
	   }
	   if(array[0][2]== charX && array[2][0]== charX && array[1][1]== charX)
	   {

		   return 2;
	   }
	   return 0;
   }
   
   public void setdisEn(JRadioButton[][] array, boolean flag)
   {
		for(int r = 0; r < 3; r++)
     	{
         	 for(int c = 0; c < 3; c++)
         	 {
         		array[r][c].setEnabled(flag);

         			 
         	 }
     		 
     	 }
   }
}