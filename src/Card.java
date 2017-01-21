
enum Action { RED, BLUE, WHITE, FLIP, NOTHING, LINE_RED, LINE_BLUE }

public class Card {
	private Action[] actions;
	private Color color;
	
	public Card(Action[] actions, Color color) {
		this.actions = actions;
		this.color = color;
	}
	
	public Action[] getActions() {
		return actions;
	}
	
	public Color getColor() {
		return color;
	}
	
}
