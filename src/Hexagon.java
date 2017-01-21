enum Color {
	RED, BLUE, WHITE
}

public class Hexagon {
	Color color = Color.WHITE;
	boolean blocked = false;
	private Coordinate coordinate;

	
	public Hexagon(int x, int y, int z) {
		coordinate = new Coordinate(x, y, z);
	}
	
	public int getX() {
		return coordinate.getX();
	}
	
	public int getY() {
		return coordinate.getY();
	}
	
	public int getZ() {
		return coordinate.getZ();
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}

}