
public class AI 
{
	
	public AI()
	{
		
	}
	//
	
	
	//Train AI
	
	//check if its a new decision node or not
	public TreeNode<Square> addNewDecision(TreeNode<Square> parentNode, TreeNode<Square> newNode)
	{
		boolean flag = false;
		for(TreeNode<Square> treeNode: parentNode.getChildren())
		{
			if(false)//this is debug code
			{
				System.out.println("____");
				System.out.println("parent: " +parentNode.getData().toString());
				System.out.println("child: " +treeNode.getData().toString());
				System.out.println("new: " +newNode.getData().toString());
				System.out.println("____");
			}
			
			if(treeNode.getData().toString2().equals(newNode.getData().toString2()))
			{
				
				//System.out.println("Are NOT");	
				flag = true;
				
				return treeNode;
				

			}
			
		}
		
		parentNode.addChild(newNode);		
		return newNode;
	}
	
	//check if new mini board type, at specific length
	public void addNewMiniBoard(TreeNode<BoardState> root, TreeNode<BoardState> newNode)
	{

		for(TreeNode<BoardState> treeNode: root.getChildren())
		{
			if(false)//this is debug code
			{
				System.out.println("____");
				System.out.println("parent: " +root.getData().toString());
				System.out.println("child: " +treeNode.getData().toString());
				System.out.println("new: " +newNode.getData().toString());
				System.out.println("____");
			}
			
			
			//get the 1-9 board length
			if(treeNode.getData().getLength() == newNode.getData().getLength())
			{
				//now get the check the children nodes to see if need to add another
				for(TreeNode<BoardState> childrenNode: treeNode.getChildren())
				{
					if(childrenNode.getData().getBoardCurrentMove().equals(newNode.getData().getBoardCurrentMove()))
					{
						return;
					}		
				}
				
			}
			
		}
		
		//now, as theres no double, add the new node to tree
		
		for(TreeNode<BoardState> treeNode: root.getChildren())
		{
			if(treeNode.getData().getLength() == newNode.getData().getLength())
			{
				//transmute
				int length = newNode.getData().getLength();
				int value = newNode.getData().getValue();
				int nextMoveX = newNode.getData().getNextMoveX();
				int nextMoveY = newNode.getData().getNextMoveY();
				String boardCurrentMove = newNode.getData().transmuteBoardString(newNode.getData().getBoardCurrentMove());
				
				BoardState boardState = new BoardState(length,value, nextMoveX, nextMoveY,boardCurrentMove);
				
				TreeNode<BoardState> transmutedNode = new TreeNode(boardState);
				
				//change points ect
				//change length beforehand...
				treeNode.addChild(newNode);
				treeNode.addChild(transmutedNode);
				
			}
		}
		
		
		

	}
	
	
	public TreeNode<BoardState> checkIfMiniExists(TreeNode<BoardState> root, TreeNode<BoardState> newNode)
	{

		for(TreeNode<BoardState> treeNode: root.getChildren())
		{
			if(false)//this is debug code
			{
				System.out.println("____");
				System.out.println("parent: " +root.getData().toString());
				System.out.println("child: " +treeNode.getData().toString());
				System.out.println("new: " +newNode.getData().toString());
				System.out.println("____");
			}
			
			
			//get the 1-9 board length
			if(treeNode.getData().getLength() == newNode.getData().getLength())
			{
				//now get the check the children nodes to see if need to add another
				for(TreeNode<BoardState> childrenNode: treeNode.getChildren())
				{
					if(childrenNode.getData().getBoardCurrentMove().equals(newNode.getData().getBoardCurrentMove()))
					{
						return childrenNode;
					}		
				}
			}
		}
		
		//now, as theres no double, add the new node to tree
		
		BoardState FalseState = new BoardState(-1,0, 0, 0,"a");
		TreeNode<BoardState> falseNode = new TreeNode(FalseState);
		
		return falseNode;
		

		
		
		

	}
	
	//value tree - goes from last node all the way to root
	public void valueTree(TreeNode<Square> node, TreeNode<Square> root)
	{
		int pointSum = 0;
		int oldPointSum = 0;
		//System.out.println("sadasdasdasd");
		boolean flag = true;
		while(flag)
		{
			//Square s = node.getData();
			//s.setPlayer("PPPPP");
			//node.setData(s);
			//System.out.println(node.getData().toString());
			
			//get occurance, ie someone won small board, at that move
			int squareValue = 0;
			if(node.getData().getValueType() == ValueTypes.normalSquare.getValue())
			{
				squareValue = 1;
				
			}else if(node.getData().getValueType() == ValueTypes.winMiddle.getValue())
			{
				squareValue = 30;
				
			}else if(node.getData().getValueType() == ValueTypes.winSmallCorner.getValue())
			{
				squareValue = 20;
				
			}else if(node.getData().getValueType() == ValueTypes.winSmallSide.getValue())
			{
				squareValue = 15;
				
			}else if(node.getData().getValueType() == ValueTypes.winBig.getValue())
			{
				squareValue = 150;
				
			}else if(node.getData().getValueType() == ValueTypes.draw.getValue())
			{
				squareValue = 1;
				
			}else if(node.getData().getValueType() == ValueTypes.loseMiddle.getValue())
			{
				squareValue = -30;
				
			}else if(node.getData().getValueType() == ValueTypes.loseBig.getValue())
			{
				squareValue = -150;
			}else if(node.getData().getValueType() == ValueTypes.loseSmallCorner.getValue())
			{
				squareValue = -20;
				
			}else if(node.getData().getValueType() == ValueTypes.loseSmallSide.getValue())
			{
				squareValue = -15;
				
			}
			
			//calculate value for current square
			pointSum = squareValue + oldPointSum;
			node.getData().setValue(pointSum);
			
			oldPointSum = (int) ((double)pointSum*0.95);
			//move to next node
			node = node.getParent();
			
			//System.out.println("sadasdasdasd" + node.getData().toString());
			if(node.getData().toString2().equals(root.getData().toString2()))
			{
				return;
			}
		}

		//System.out.println("sadasdasdasd");
	}
	


}
