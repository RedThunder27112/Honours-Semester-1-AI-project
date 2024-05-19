import java.awt.Color;
import java.io.IOException;

import javax.swing.JOptionPane;


public class Main 
{
	static boolean trainingMode = false;
	static boolean guiDisplay = true;
	static boolean outputs = false;
	static boolean onlyAI = false;
	static int loopNum = 1;
	
	static String playerTurn;
	static int numCompleteBoards;
	static int[] rowCol;
	static TreeNode<Square> turnNodeReturn;
	static TreeNode<BoardState> miniBoardRoot;
	static AI ai = new AI();
	
	public static void main(String[] args) 
	{
		
        int userInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter a number:\n1 for training mode\n2 for playing mode\n3 for seeing training success"));
        
        if(userInput == 1)//training mode
        {
        	loopNum = 150000;
        	trainingMode = true;
        	guiDisplay = false;
        	outputs = false;
        	onlyAI = true;
        	
        }else if(userInput == 2)//playing mode
        {
        	loopNum = 1;
        	trainingMode = false;
        	guiDisplay = true;
        	outputs = true;
        	onlyAI = false;
        	
        }else//displaying training success mode
        {
        	loopNum = 1000;
        	trainingMode = false;
        	guiDisplay = false;
        	outputs = false;
        	onlyAI = true;
        }

        		
		///GUI
		if(guiDisplay)
		{
			GUI.start();
		}
		
		
		
		
 		Square root = new Square(1,"R23",1,1,1,1,1);
 		Tree<Square> tree2 = new Tree<>(root);
 		

 		
 		TreeNode<Square> loadedRoot = null;
		try {
			loadedRoot = tree2.readTreeFromFile("tree.txt");
			if(outputs) {printTree(loadedRoot, 0);}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tree<Square> tree = new Tree<>(loadedRoot.getData());
		tree.setRoot(loadedRoot);
        //System.out.println("Tree data loaded from file:");
		
		//Now, tree for mini boards
		
 		BoardState startRootMiniBoard = new BoardState(1,1,1,1,"M2");
 		Tree<BoardState> treeStartMiniBoard = new Tree<>(startRootMiniBoard);
 		
 		
 		//code in order to create the initial file
 		if (false)
 		{
	 		
	 		///////remeber to check not exact match
			BoardState boardState1 = new BoardState(1,0, 1, 1,"a");
			BoardState boardState2 = new BoardState(2,0, 1, 1,"a");
			BoardState boardState3 = new BoardState(3,0, 1, 1,"a");
			BoardState boardState4 = new BoardState(4,0, 1, 1,"a");
			BoardState boardState5 = new BoardState(5,0, 1, 1,"a");
			BoardState boardState6 = new BoardState(6,0, 1, 1,"a");
			BoardState boardState7 = new BoardState(7,0, 1, 1,"a");
			BoardState boardState8 = new BoardState(8,0, 1, 1,"a");
			BoardState boardState9 = new BoardState(9,0, 1, 1,"a");
			
			TreeNode<BoardState> miniBoardNode1 = new TreeNode(boardState1);
			TreeNode<BoardState> miniBoardNode2 = new TreeNode(boardState2);
			TreeNode<BoardState> miniBoardNode3 = new TreeNode(boardState3);
			TreeNode<BoardState> miniBoardNode4 = new TreeNode(boardState4);
			TreeNode<BoardState> miniBoardNode5 = new TreeNode(boardState5);
			TreeNode<BoardState> miniBoardNode6 = new TreeNode(boardState6);
			TreeNode<BoardState> miniBoardNode7 = new TreeNode(boardState7);
			TreeNode<BoardState> miniBoardNode8 = new TreeNode(boardState8);
			TreeNode<BoardState> miniBoardNode9 = new TreeNode(boardState9);
			
			TreeNode<BoardState> rootTemp = treeStartMiniBoard.getRoot();
			
			rootTemp.addChild(miniBoardNode1);
			rootTemp.addChild(miniBoardNode2);
			rootTemp.addChild(miniBoardNode3);
			rootTemp.addChild(miniBoardNode4);
			rootTemp.addChild(miniBoardNode5);
			rootTemp.addChild(miniBoardNode6);
			rootTemp.addChild(miniBoardNode7);
			rootTemp.addChild(miniBoardNode8);
			rootTemp.addChild(miniBoardNode9);
	 		
	        try {
	        	treeStartMiniBoard.writeTreeToFile(treeStartMiniBoard.getRoot(), "miniTree.txt");
	 
	        } catch (IOException e) {
	            System.err.println("Error occurred: " + e.getMessage());
	        }
 		
 		}
 		////////
 		
		TreeNode<BoardState> loadedSmallRoot = null;
		try {
			loadedSmallRoot = treeStartMiniBoard.readBoardsFromFile("miniTree.txt");

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tree<BoardState> miniTree = new Tree<>(loadedSmallRoot.getData());
		miniTree.setRoot(loadedSmallRoot);
        
        //Tree<Square> tree = new Tree<>(loadedRoot);
 		int totalW = 0;
 		int totalL = 0;
 		int totalD = 0;

 		for(int i = 0; i < loopNum; i++)
 		{
 			
 		
 		//reset board
 		turnNodeReturn = tree.getRoot();
 		miniBoardRoot = miniTree.getRoot();

 		//System.out.println("Tree data loaded from file:" + turnNodeReturn.getChildren().get(0).getData().toString());
 		
		Board[][] board = new Board[3][3]; 
		rowCol = new int[2];
		rowCol[0] = 0;
		rowCol[1] = 0;
		numCompleteBoards = 0;
		playerTurn = "X";
		
 		for(int r = 0; r < 3; r++)
     	{
         	 for(int c = 0; c < 3; c++)
         	 {
         		board[r][c] = new Board();
         	 }
       
     	}
 		
 		
 		int winner = 0;
		while(0 == (winner = checkWin(bigToSmallBoard(board))) && numCompleteBoards < 9)
		{
	 		for(int r = 0; r < 3; r++)
	     	{
	         	 for(int c = 0; c < 3; c++)
	         	 {
	         		if(outputs) {System.out.print(bigToSmallBoard(board)[r][c]);}
	         	 }
	         	if(outputs) {System.out.println("");}
	     	}
	 		if(outputs) {System.out.println("");}
			
	
			int row = rowCol[0];
			int col = rowCol[1];
			
			while(board[row][col].isFull() || board[row][col].getBoardWinner() != 0)
			{
				row = (int)(Math.random()*3);
				col = (int)(Math.random()*3);
			}
			if(outputs) {System.out.println("Next round location R C:" + rowCol[0] + " - " + rowCol[1]);}

			
			getRandomMove(board,row,col,turnNodeReturn);
			if(outputs) {System.out.println();}
	 		for(int r = 0; r < 3; r++)
	     	{
	         	 for(int ir = 0; ir < 3; ir++)
	         	 {
	         		 for(int c = 0; c < 3; c++)
		         	 {
		         		 for(int ic = 0; ic < 3; ic++)
			         	 {
		         			if(outputs) {System.out.print(board[r][c].getBoard()[ir][ic]);}
			         	 }
		         		if(outputs) {System.out.print(" | ");}
		         	 }
	         		if(outputs) {System.out.println("");}
	         		
	         		
	         		
	         	
	         	 }
	         	for(int s = 0; s < 17; s++)
	         	{
	         		if(outputs) {System.out.print("-");}
	         	}
	         	if(outputs) {System.out.println();}
	         	
	     	}
	 		if(outputs) {System.out.println();}
	 		if(outputs) {System.out.println("move num: " + numCompleteBoards);}
	 		
	 		//check if at draw state
			numCompleteBoards = 0;
	 		for(int r = 0; r < 3; r++)
	     	{
	         	 for(int c = 0; c < 3; c++)
	         	 {
	     			if(board[r][c].isFull() || board[r][c].getBoardWinner() != 0)
	    			{
	     				numCompleteBoards++;
	    			}
	         	 }
	         
	     	}
					
			

		}
		
		if(numCompleteBoards == 9 && winner == 0)
		{
			if(outputs) {System.out.print("draw");}
			if(trainingMode) {turnNodeReturn.getData().setValueType(ValueTypes.draw.getValue());}
			totalD++;
			if(guiDisplay) {JOptionPane.showMessageDialog(null, "Draw Achieved");}
		}else
		{
			//game won at this point... now give values to graph
			if(winner == 1)
			{
				if(outputs) {System.out.print("X wins");}
				if(trainingMode) {turnNodeReturn.getData().setValueType(ValueTypes.winBig.getValue());}
				totalW++;
				if(guiDisplay) {JOptionPane.showMessageDialog(null, "You Lose!");}
			}else
			{
				if(outputs) {System.out.print("O win");}
				if(trainingMode) {turnNodeReturn.getData().setValueType(ValueTypes.loseBig.getValue());}
				totalL++;
				if(guiDisplay) {JOptionPane.showMessageDialog(null, "You Won!");}
			}
			
			if(trainingMode)
			{
				ai.valueTree(turnNodeReturn,tree.getRoot());
			}
			
			
		}
		
		
		
		//displaying winning board
		if(outputs) {System.out.println();}
 		for(int r = 0; r < 3; r++)
     	{
         	 for(int c = 0; c < 3; c++)
         	 {
         		if(outputs) {System.out.print(bigToSmallBoard(board)[r][c]);}
         	 }
         	if(outputs) {System.out.println("");}
     	}
 		if(outputs) {System.out.println("");}
 		if(outputs) {System.out.println();}
 		if(outputs) {System.out.println();}
		for(int r = 0; r < 3; r++)
     	{
         	 for(int ir = 0; ir < 3; ir++)
         	 {
         		 for(int c = 0; c < 3; c++)
	         	 {
	         		 for(int ic = 0; ic < 3; ic++)
		         	 {
	         			if(outputs) {System.out.print(board[r][c].getBoard()[ir][ic]);}
		         	 }
	         		if(outputs) {System.out.print(" | ");}
	         	 }
         		if(outputs) {System.out.println("");}
         		
         		
         		
         	
         	 }
         	for(int s = 0; s < 17; s++)
         	{
         		if(outputs) {System.out.print("-");}
         	}
         	if(outputs) {System.out.println();}
         	
     	}
		if(outputs) {System.out.println();}
		
		

 		
 
 		
 		/*
 		Square root = new Square(1,"X",1,1,1,1);
 		Tree<Square> tree = new Tree<>(root);

        TreeNode<Square> node1 = new TreeNode<>(new Square(2,"O",1,1,1,1));
        TreeNode<Square> node2 = new TreeNode<>(new Square(3,"O",1,1,1,1));
        TreeNode<Square> node3 = new TreeNode<>(new Square(4,"O",1,1,1,1));

        tree.getRoot().addChild(node1);
        tree.getRoot().addChild(node2);
        tree.getRoot().addChild(node3);

        TreeNode<Square> subNode1 = new TreeNode<>(new Square(5,"X",1,1,1,1));
        TreeNode<Square> subNode2 = new TreeNode<>(new Square(6,"X",1,1,1,1));
        TreeNode<Square> subNode3 = new TreeNode<>(new Square(6,"X",1,1,1,1));

        node1.addChild(subNode1);
        node1.addChild(subNode2);
        

       
        
        System.out.println("sadasdas");
        System.out.println(node1.getChildren().get(0).getData());
        System.out.println("sadasdas");
        // Printing tree structure
 		*/
		if(outputs) {printTree(tree.getRoot(), 0);}
        
        /*
 		TreeNode<Square> node31 = new TreeNode<>(new Square(4,"RRR1",1,1,1,1));
 		TreeNode<Square> node32 = new TreeNode<>(new Square(4,"RRR2",1,1,1,1));
 		TreeNode<Square> node33 = new TreeNode<>(new Square(4,"RRR3",1,1,1,1));
 		TreeNode<Square> node4 = tree.getRoot().getChildren().get(0);
        ai.addNewDecision(tree.getRoot(), node31);
        ai.addNewDecision(node31, node32);
        ai.addNewDecision(node4, node33);
         */
        
 		}
 		
		//debug output
		if(trainingMode)
		{
			System.out.println("Training:");
			
			System.out.println("Total Games:"+(totalW+totalL+totalD));
			System.out.println("Total Wins:"+totalW);
			System.out.println("Total loses:"+totalL);
			System.out.println("Total draws:"+totalD);
		}else if(!outputs)
		{
			System.out.println("Non Training - no output:");
			
			System.out.println("Total Games:"+(totalW+totalL+totalD));
			System.out.println("Total Wins:"+totalW);
			System.out.println("Total loses:"+totalL);
			System.out.println("Total draws:"+totalD);
			
			//printTree(tree.getRoot(), 0);
			//System.out.println(tree.getRoot().getChildren());
		}
 		//write tree file
        try {
            tree.writeTreeToFile(tree.getRoot(), "tree.txt");
            if(outputs) {System.out.println("Tree data saved to tree.txt");}

            TreeNode<Square> loadedRoot2 = tree.readTreeFromFile("tree.txt");
            if(outputs) {System.out.println("Tree data loaded from file:");}
            if(outputs) {printTree(loadedRoot2, 0);}
        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }

        //write board file
        try {
        	miniTree.writeTreeToFile(miniTree.getRoot(), "miniTree.txt");
 
        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
		
		
		
		
		
      

	}
	
    private static void printTree(TreeNode<Square> node, int depth) {
    	/*
    	if(false)//disable if want deug code
    	{
    		return;
    	}
    	
        StringBuilder indent = new StringBuilder();
        
        for (int i = 0; i < depth; i++) {
            indent.append("  ");
        }
        System.out.println(indent + node.getData().toString());
        for (TreeNode<Square> child : node.getChildren()) 
        {
            printTree(child, depth + 1);
        }
        */
    }
	
	//Save state
	
	//Check for Loss
	
	public static String[][] bigToSmallBoard(Board[][] board)
	{
		String[][] array = new String[3][3];
		
 		for(int r = 0; r < 3; r++)
     	{
         	 for(int c = 0; c < 3; c++)
         	 {
         		 int boardwinner = board[r][c].getBoardWinner();
         		 
         		 if(boardwinner == 0)
         		 {
         			array[r][c] = ".";
         		 }else
         		 if(boardwinner == 1)
         		 {
         			array[r][c] = "X";
         		 }else
         		 {
         			array[r][c] = "O";
         		 }
         
         	 }
       
     	}
 		
 		return array;
	}

	
	public static int checkWin(String[][] array)
	   {
		   String charX = "X";

	 		
	 		
		   if(array[0][0].equals(charX) && array[0][1].equals(charX) && array[0][2].equals(charX))
		   {

	
			   
			   return 1;
		   }
		   if(array[1][0].equals(charX) && array[1][1].equals(charX) && array[1][2].equals(charX))
		   {
			   
			   return 1;
		   }
		   if(array[2][0].equals(charX) && array[2][1].equals(charX) && array[2][2].equals(charX))
		   {
			
			   return 1;
		   }
		   
		   if(array[0][0].equals(charX) && array[1][0].equals(charX) && array[2][0].equals(charX))
		   {
			  
			   return 1;
		   }
		   if(array[0][1].equals(charX) && array[1][1].equals(charX) && array[2][1].equals(charX))
		   {
			  
			   return 1;
		   }
		   if(array[0][2].equals(charX) && array[1][2].equals(charX) && array[2][2].equals(charX))
		   {
			 
			   return 1;
		   }
		   
		   if(array[0][0].equals(charX) && array[1][1].equals(charX) && array[2][2].equals(charX))
		   {
			
			   return 1;
		   }
		   if(array[0][2].equals(charX) && array[2][0].equals(charX) && array[1][1].equals(charX))
		   {
			 
			   return 1;
		   }
		   
		   
		   
		   charX = "O";
		   if(array[0][0].equals(charX) && array[0][1].equals(charX) && array[0][2].equals(charX))
		   {

			  
			   
			   return 2;
		   }
		   if(array[1][0].equals(charX) && array[1][1].equals(charX) && array[1][2].equals(charX))
		   {
			   
			   return 2;
		   }
		   if(array[2][0].equals(charX) && array[2][1].equals(charX) && array[2][2].equals(charX))
		   {
			
			   return 2;
		   }
		   
		   if(array[0][0].equals(charX) && array[1][0].equals(charX) && array[2][0].equals(charX))
		   {
			 
			   return 2;
		   }
		   if(array[0][1].equals(charX) && array[1][1].equals(charX) && array[2][1].equals(charX))
		   {
			 
			   return 2;
		   }
		   if(array[0][2].equals(charX) && array[1][2].equals(charX) && array[2][2].equals(charX))
		   {
			  
			   return 2;
		   }
		   
		   if(array[0][0].equals(charX) && array[1][1].equals(charX) && array[2][2].equals(charX))
		   {
			   
	
			   return 2;
		   }
		   if(array[0][2].equals(charX) && array[2][0].equals(charX) && array[1][1].equals(charX))
		   {
	
			   return 2;
		   }

		   return 0;
	   }
	
	//Backtrack reward function
	
	//Save all winning or losing boards
	
	//random play function
	public static int[] getRandomMove(Board[][] boardArray,int row, int col, TreeNode<Square> parentNode)
	{
		
		String[][] array = boardArray[row][col].getBoard();
		
		//array before move happens
		String[][] tempArray = new String[3][3];
		
 		for(int r = 0; r < 3; r++)
     	{
         	 for(int c = 0; c < 3; c++)
         	 {
         		tempArray[r][c] = array[r][c];
         	 }
       
     	}
		
		//while they are called random, they are not actually always random.. only for training mode
		int randomRow;
		int randomCol;
		
		//changes training mode - ie random choices during training, but uses training data for decision with non-training mode/playing mode
		if(trainingMode || playerTurn.equals("O"))
		{
			//check if human playing, or only AI
			if(onlyAI)
			{
				randomRow = (int)(Math.random()*3);
				randomCol = (int)(Math.random()*3);
				
				while(!array[randomRow][randomCol].equals("."))
				{
					 randomRow = (int)(Math.random()*3);
					 randomCol = (int)(Math.random()*3);
				}
			}else
			{
				boolean waiting = true;
				
				while(waiting)
				{
					try 
					{
						
					    Thread.sleep(1000); // Pause for 1000 milliseconds (1 second)
					} catch (InterruptedException e) {
					    e.printStackTrace();
					}

					waiting = GUI.wait;

				}
				randomRow = GUI.playerY;
				randomCol = GUI.playerX;
				/////
	
			}

						
			
		}else
		{
			///Start of mini board decision
			
			
			String boardCurrentMoveT = miniBoardRoot.getData().generateBoardString(tempArray);
			int lengthT = miniBoardRoot.getData().calcLength(boardCurrentMoveT);
			BoardState boardStateT = new BoardState(lengthT,80, 1, 1,boardCurrentMoveT);
			
			TreeNode<BoardState> miniBoardNodeT = new TreeNode(boardStateT);
			
			TreeNode<BoardState> miniBoardChoiceT = ai.checkIfMiniExists(miniBoardRoot, miniBoardNodeT);
			
			///end of mini board decision
			if(parentNode.getChildren().isEmpty())
			{
				//if miniBoard was found
				if(miniBoardChoiceT.getData().getLength() > -1)
				{
					randomRow = miniBoardChoiceT.getData().getNextMoveY();
					randomCol = miniBoardChoiceT.getData().getNextMoveX();
				}else
				{
					//if no children choices, then do other logic
					randomRow = (int)(Math.random()*3);
					randomCol = (int)(Math.random()*3);
					
					while(!array[randomRow][randomCol].equals("."))
					{
						 randomRow = (int)(Math.random()*3);
						 randomCol = (int)(Math.random()*3);
					}
				}

				
				if(outputs) {System.out.println("1 AI made choice: "+randomRow + "  " +randomCol);}
			}else
			{
				//if there are children choices, choose one of them
				int maxValue = -999999;
				TreeNode<Square> maxChoice = null;
				for(TreeNode<Square> treeNode: parentNode.getChildren())
				{
					if(maxValue < treeNode.getData().getValue())
					{
						maxValue = treeNode.getData().getValue();
						maxChoice = treeNode;
					}
					
				}
				
				//if miniBoard was found
				if(miniBoardChoiceT.getData().getLength() > -1 && maxValue < 80)
				{
					randomRow = miniBoardChoiceT.getData().getNextMoveY();
					randomCol = miniBoardChoiceT.getData().getNextMoveX();
				}else
				{
					//if only bad decision, instead do random
					if(maxValue < -5)
					{
						randomRow = (int)(Math.random()*3);
						randomCol = (int)(Math.random()*3);
						
						while(!array[randomRow][randomCol].equals("."))
						{
							 randomRow = (int)(Math.random()*3);
							 randomCol = (int)(Math.random()*3);
						}
					}else
					{
						randomCol = maxChoice.getData().getSmallX();
						randomRow = maxChoice.getData().getSmallY();
					}

				}

				
				//debug code
				if(!array[randomRow][randomCol].equals("."))
				{
					System.err.println("LINE 479, PLAY MODE CHOICES ARE OVERWRITING EXISTING SQUARES");
				}
				
				if(outputs) {System.out.println("2 AI made choice: "+randomRow + "  " +randomCol);}
			}
		}
		
		 
	    
	    TreeNode<Square> turnNode;
		if(playerTurn.equals("X"))
		{
			boardArray[row][col].setSquare(randomRow, randomCol, "X");
			playerTurn = "O";
			turnNode = new TreeNode<>(new Square(2,"X",col,row,randomCol,randomRow,0));
		}else
		{
			boardArray[row][col].setSquare(randomRow, randomCol, "O");
			playerTurn = "X";
			turnNode = new TreeNode<>(new Square(2,"O",col,row,randomCol,randomRow,0));
			
			
		}
		


		rowCol[0] = randomRow;
		rowCol[1] = randomCol;
		
		boardArray[row][col].checkWin();
		if(outputs) {boardArray[row][col].displayBoard();}
		
		int smallBoardWinner = boardArray[row][col].getBoardWinner();
		if(outputs) {System.out.println("Small Board win state " + smallBoardWinner);}
		
		//set board value state, ie was small board won
		if(trainingMode)
		{
			
			if(smallBoardWinner == 0)//no winning
			{
				
			}else if(smallBoardWinner == 1)//X is winner
			{
				if((row == 0 && col == 0) || (row == 2 && col == 2) || (row == 2 && col == 0) || (row == 0 && col == 2))
				{
					turnNode.getData().setValueType(ValueTypes.winSmallCorner.getValue());
				}else if((row == 2 && col == 1) || (row == 0 && col == 1) || (row == 1 && col == 0) || (row == 1 && col == 2))
				{
					turnNode.getData().setValueType(ValueTypes.winSmallSide.getValue());
				}else if(row == 1 && col == 1)
				{
					turnNode.getData().setValueType(ValueTypes.winMiddle.getValue());
				}else//failsafe
				{
					turnNode.getData().setValueType(ValueTypes.winSmallCorner.getValue());
				}
				
				
				
				
				//add small board win
				String boardCurrentMove = miniBoardRoot.getData().generateBoardString(tempArray);
				int length = miniBoardRoot.getData().calcLength(boardCurrentMove);
				BoardState boardState = new BoardState(length,80, randomCol, randomRow,boardCurrentMove);
				
				TreeNode<BoardState> miniBoardNode = new TreeNode(boardState);
				
				ai.addNewMiniBoard(miniBoardRoot, miniBoardNode);
				
				
			}else if(smallBoardWinner == 2)//O is winner
			{
				if((row == 0 && col == 0) || (row == 2 && col == 2) || (row == 2 && col == 0) || (row == 0 && col == 2))
				{
					turnNode.getData().setValueType(ValueTypes.loseSmallCorner.getValue());
				}else if((row == 2 && col == 1) || (row == 0 && col == 1) || (row == 1 && col == 0) || (row == 1 && col == 2))
				{
					turnNode.getData().setValueType(ValueTypes.loseSmallSide.getValue());
				}else if(row == 1 && col == 1)
				{
					turnNode.getData().setValueType(ValueTypes.loseMiddle.getValue());
				}else//failsafe
				{
					turnNode.getData().setValueType(ValueTypes.loseSmallCorner.getValue());
				}
				
				//add small board block
				String boardCurrentMove = miniBoardRoot.getData().generateBoardString(tempArray);
				int length = miniBoardRoot.getData().calcLength(boardCurrentMove);
				BoardState boardState = new BoardState(length,80, randomCol, randomRow,boardCurrentMove);
				
				TreeNode<BoardState> miniBoardNode = new TreeNode(boardState);
				
				ai.addNewMiniBoard(miniBoardRoot, miniBoardNode);
			}
		

			turnNode = ai.addNewDecision(parentNode, turnNode);
		}

		turnNodeReturn = turnNode;
		
		if(guiDisplay)
		{
			GUI.AICLICK(col, row, randomCol, randomRow);
		}
		
		
		return rowCol;
	}
	
	

}
