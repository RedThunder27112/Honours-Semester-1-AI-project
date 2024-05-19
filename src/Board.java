
public class Board {
	
	String[][] board = new String[3][3]; 
	int boardWinner;
	int boardFull;
	
	public Board()
	{
		boardWinner = 0;
		boardFull = 0;
 		for(int r = 0; r < 3; r++)
     	{
         	 for(int c = 0; c < 3; c++)
         	 {
         		board[r][c] = ".";
         	 }
       
     	}
	}
	
	public String[][] getBoard()
	{
		return board;
	}
	
	public void displayBoard()
	{
 		for(int r = 0; r < 3; r++)
     	{
         	 for(int c = 0; c < 3; c++)
         	 {
         		
         		
         		System.out.print(board[r][c] + " ");
         	 }
         	System.out.println();
     	}
	}
	
	public void setSquare(int r, int c, String type)
	{
		boardFull++;
		board[r][c] = type;
	}
	
	public boolean isFull()
	{
		if (boardFull < 9)
		{
			return false;
		}
		
		return true;
	}
	

	
	
	public void checkWin()
	{
		   String charX = "X";

	 		
		   if(board[0][0].equals(charX) && board[0][1].equals(charX) && board[0][2].equals(charX))
		   {

			   boardWinner = 1;
		   }
		   if(board[1][0].equals(charX) && board[1][1].equals(charX) && board[1][2].equals(charX))
		   {
			   
			   boardWinner = 1;
		   }
		   if(board[2][0].equals(charX) && board[2][1].equals(charX) && board[2][2].equals(charX))
		   {
			
			   boardWinner = 1;
		   }
		   
		   if(board[0][0].equals(charX) && board[1][0].equals(charX) && board[2][0].equals(charX))
		   {
			  
			   boardWinner = 1;
		   }
		   if(board[0][1].equals(charX) && board[1][1].equals(charX) && board[2][1].equals(charX))
		   {
			  
			   boardWinner = 1;
		   }
		   if(board[0][2].equals(charX) && board[1][2].equals(charX) && board[2][2].equals(charX))
		   {
			 
			   boardWinner = 1;
		   }
		   
		   if(board[0][0].equals(charX) && board[1][1].equals(charX) && board[2][2].equals(charX))
		   {
			
			   boardWinner = 1;
		   }
		   if(board[0][2].equals(charX) && board[2][0].equals(charX) && board[1][1].equals(charX))
		   {
			 
			   boardWinner = 1;
		   }
		   
		   
		   
		   charX = "O";
		   if(board[0][0].equals(charX) && board[0][1].equals(charX) && board[0][2].equals(charX))
		   {

			  
			   
			   boardWinner = 2;
		   }
		   if(board[1][0].equals(charX) && board[1][1].equals(charX) && board[1][2].equals(charX))
		   {
			   
			   boardWinner = 2;
		   }
		   if(board[2][0].equals(charX) && board[2][1].equals(charX) && board[2][2].equals(charX))
		   {
			
			   boardWinner = 2;
		   }
		   
		   if(board[0][0].equals(charX) && board[1][0].equals(charX) && board[2][0].equals(charX))
		   {
			 
			   boardWinner = 2;
		   }
		   if(board[0][1].equals(charX) && board[1][1].equals(charX) && board[2][1].equals(charX))
		   {
			 
			   boardWinner = 2;
		   }
		   if(board[0][2].equals(charX) && board[1][2].equals(charX) && board[2][2].equals(charX))
		   {
			  
			   boardWinner = 2;
		   }
		   
		   if(board[0][0].equals(charX) && board[1][1].equals(charX) && board[2][2].equals(charX))
		   {
			   
	
			   boardWinner = 2;
		   }
		   if(board[0][2].equals(charX) && board[2][0].equals(charX) && board[1][1].equals(charX))
		   {
	
			   boardWinner = 2;
		   }
		   

	 }
	
	public int getBoardWinner()
	{
		return boardWinner;
	}
	


}
