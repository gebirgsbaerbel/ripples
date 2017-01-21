
public class Coordinate {
	private int x;
	private int y;
	private int z;
	
	public Coordinate(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
	
	public Coordinate addCoordinate(Coordinate c) {
		return new Coordinate(c.x + this.x, c.y + this.y, c.z + this.z);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		Coordinate c = (Coordinate) obj;
		return c.x == this.x && c.y == this.y && c.z == this.z;
	}
	
	@Override
	public int hashCode() {
		return x + y + z;
	}
	
}
