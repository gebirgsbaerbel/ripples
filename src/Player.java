import java.util.ArrayList;
import java.util.List;


public abstract class Player {
	List<Card> cards = new ArrayList<Card>();
	
	boolean hasCards() {
		return cards.size() > 0;
	}
	
}
