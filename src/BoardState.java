
public class BoardState {
	
	int length;
	int value;
	int nextMoveX;
	int nextMoveY;
	String boardCurrentMove;

	
	public BoardState(int length, int value, int nextMoveX, int nextMoveY, String boardCurrentMove) {
		
		this.length = length;
		this.value = value;
		this.nextMoveX = nextMoveX;
		this.nextMoveY = nextMoveY;
		this.boardCurrentMove = boardCurrentMove;
	}
	
	

	public int getLength() {
		return length;
	}



	public void setLength(int length) {
		this.length = length;
	}



	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getBoardCurrentMove() {
		return boardCurrentMove;
	}

	public void setBoardCurrentMove(String boardCurrentMove) {
		this.boardCurrentMove = boardCurrentMove;
	}

	public int getNextMoveX() {
		return nextMoveX;
	}

	public void setNextMoveX(int nextMoveX) {
		this.nextMoveX = nextMoveX;
	}

	public int getNextMoveY() {
		return nextMoveY;
	}

	public void setNextMoveY(int nextMoveY) {
		this.nextMoveY = nextMoveY;
	}
	
	
	
	public String generateBoardString(String[][] board)
	{
		String tempString = ""; 
 		for(int r = 0; r < 3; r++)
     	{
         	 for(int c = 0; c < 3; c++)
         	 {
   
         		tempString = tempString + "" + board[r][c];
         		
         	 }
     	}
 		
 		return tempString;
		
	}
	
	public String transmuteBoardString(String boardString)
	{
		String tempTotal = "";
		for(int i = 0; i < 9; i++)
		{
			if(boardString.charAt(i) == 'X')
			{
				tempTotal = tempTotal + "O";
			}else if(boardString.charAt(i) == 'O')
			{
				tempTotal = tempTotal + "X";
			}else
			{
				tempTotal = tempTotal + ".";
			}
		}
		
		return tempTotal;
		
	}
	
	public int calcLength(String boardString)
	{
		int tempTotal = 0;
		for(int i = 0; i < 9; i++)
		{
			if(boardString.charAt(i) == 'X')
			{
				tempTotal++;
			}else if(boardString.charAt(i) == 'O')
			{
				tempTotal++;
			}
		}
		
		return tempTotal;
		
	}




	
    @Override
    public String toString() {
        return length + "," + value + "," + nextMoveX + "," + nextMoveY + "," + boardCurrentMove;
    }
	
	
	
	

}
