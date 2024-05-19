
public enum ValueTypes 
{
	normalSquare(0),
	winSmallCorner(1),
	winSmallSide(2),
	winBig(3),
	loseSmallCorner(4),
	loseSmallSide(5),
	loseBig(6),
	draw(7),
	loseMiddle(8),
	winMiddle(9);
	
    private final int value;

    ValueTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
