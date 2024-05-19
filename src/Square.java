
public class Square 
{
	//reward value
	int value;
	String player;
	
	//Specify which board
	int bigX;
	int bigY;
	
	//Specify which square in board
	int smallX;
	int smallY;
	
	int valueType;


	public Square(int value, String player, int bigX, int bigY, int smallX, int smallY,int valueType) {
		
		this.value = value;
		this.player = player;
		this.bigX = bigX;
		this.bigY = bigY;
		this.smallX = smallX;
		this.smallY = smallY;
		this.valueType = valueType;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public int getBigX() {
		return bigX;
	}

	public void setBigX(int bigX) {
		this.bigX = bigX;
	}

	public int getBigY() {
		return bigY;
	}

	public void setBigY(int bigY) {
		this.bigY = bigY;
	}

	public int getSmallX() {
		return smallX;
	}

	public void setSmallX(int smallX) {
		this.smallX = smallX;
	}

	public int getSmallY() {
		return smallY;
	}

	public void setSmallY(int smallY) {
		this.smallY = smallY;
	}
	
	public int getValueType() {
		return valueType;
	}

	public void setValueType(int valueType) {
		this.valueType = valueType;
	}
	
	
    // Override toString method to provide custom string representation
    @Override
    public String toString() {
        return value + "," + player + "," + bigX + "," + bigY + "," + smallX + "," + smallY + "," + valueType;
    }
	
    public String toString2() {
        return player + "," + bigX + "," + bigY + "," + smallX + "," + smallY;
    }
	

}
