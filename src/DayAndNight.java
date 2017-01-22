import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DayAndNight {
	private List<Hexagon> hexagons = new ArrayList<Hexagon>();
	private Player playerRed = new PlayerRed();
	private Player playerBlue = new PlayerBlue();
	private Random random = new Random();
	private GameScore gameScore = new GameScore();
	
	Coordinate[] directions = {
		new Coordinate(+1, -1,  0), new Coordinate(+1,  0, -1), new Coordinate( 0, +1, -1),
		new Coordinate(-1, +1,  0), new Coordinate(-1,  0, +1), new Coordinate( 0, -1, +1)
	};
	
	public DayAndNight() {
		hexagons.add(new Hexagon(0, 2, -2));
		hexagons.add(new Hexagon(1, 1, -2));
		hexagons.add(new Hexagon(2, 0, -2));
		
		hexagons.add(new Hexagon(-1, 2, -1));
		hexagons.add(new Hexagon(0, 1, -1));
		hexagons.add(new Hexagon(1, 0, -1));
		hexagons.add(new Hexagon(2, -1, -1));
		
		hexagons.add(new Hexagon(-2, 2, 0));
		hexagons.add(new Hexagon(-1, 1, 0));
		hexagons.add(new Hexagon(0, 0, 0));
		hexagons.add(new Hexagon(1, -1, 0));
		hexagons.add(new Hexagon(2, -2, 0));
		
		hexagons.add(new Hexagon(-2, 1, 1));
		hexagons.add(new Hexagon(-1, 0, 1));
		hexagons.add(new Hexagon(0, -1, 1));
		hexagons.add(new Hexagon(1, -2, 1));
		
		hexagons.add(new Hexagon(-2, 0, 2));
		hexagons.add(new Hexagon(-1, -1, 2));
		hexagons.add(new Hexagon(0, -2, 2));
	}
	
	GameScore playGame() {
		Player currentPlayer = playerRed;
		do {
			playCardRandomly(currentPlayer);
			currentPlayer = selectNextPlayer(currentPlayer);
		} while (playerRed.hasCards() || playerBlue.hasCards());
		scorePlayfield();
		return gameScore;
	}
	
	private void scorePlayfield() {
		int numberOfFields = hexagons.size();
		for (int i = 0; i <= numberOfFields - 1; i++) {
				switch (hexagons.get(i).color) {
				case RED:
					gameScore.scoreRed++;
					break;
				case BLUE:
					gameScore.scoreBlue++;
					break;
				case WHITE:
					gameScore.scoreWhite++;
					break;
				default:
					break;
				}
		}
		System.out.print("Red: " + gameScore.scoreRed + " | ");
		System.out.print("Blue: " + gameScore.scoreBlue + " | ");
		System.out.print("White: " + gameScore.scoreWhite + "\n");
	}

	private Player selectNextPlayer(Player currentPlayer) {
		if (currentPlayer == playerRed) {
			return playerBlue;
		} else {
			return playerRed;
		}
	}
	
	private void playCardRandomly(Player currentPlayer) {		
		Hexagon selectedField;
		do {
			selectedField = hexagons.get(random.nextInt(hexagons.size()));
		} while (selectedField.blocked == true);
		Card card = currentPlayer.cards.get(random.nextInt(currentPlayer.cards.size()));
		currentPlayer.cards.remove(card);
		selectedField.color = card.getColor();
		selectedField.blocked = true;
		
		int rotationOffset = random.nextInt(6);
		for (Action action : card.getActions()) {
			Coordinate direction = directions[rotationOffset % 6];
			rotationOffset++;
			Coordinate neighbourCoordinate = selectedField.getCoordinate().addCoordinate(direction);
			Hexagon neighbour = getHexagonForCoordinate(neighbourCoordinate);
			if (neighbour != null) {
				switch (action) {
				case RED:
					neighbour.color = Color.RED;
					neighbour.blocked = false;
					break;
				case BLUE:
					neighbour.color = Color.BLUE;
					neighbour.blocked = false;
					break;
				case WHITE:
					neighbour.color = Color.WHITE;
					neighbour.blocked = false;
					break;
				default:
					break;
				}
			}
		}
	}
	
	private Hexagon getHexagonForCoordinate(Coordinate coordinate) {
		for (Hexagon hexagon : hexagons) {
			if (hexagon.getCoordinate().equals(coordinate)) {
				return hexagon;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		GameStatistics statistics = new GameStatistics();
		for (int i = 0; i < 10000; i++) {
			DayAndNight dan = new DayAndNight();
			statistics.gameScores.add(dan.playGame());
		}
		statistics.showStatistics();
	}
	
	@Override
	public String toString() {
		for (int z = -2; z <= 2; z++) {
			for (int x = -2; x <= 2; x++) {
				for (Hexagon hexagon : hexagons) {
					if (hexagon.blocked) {
						if (hexagon.getX() == x && hexagon.getZ() == z) {
							switch (hexagon.color) {
							case RED:
								System.out.print("(R)");
								break;
							case BLUE:
								System.out.print("(B)");
								break;
							case WHITE:
								System.out.print("(W)");
								break;
							default:
								break;
							}
						}
					} else {
						if (hexagon.getX() == x && hexagon.getZ() == z) {
							switch (hexagon.color) {
							case RED:
								System.out.print(" R ");
								break;
							case BLUE:
								System.out.print(" B ");
								break;
							case WHITE:
								System.out.print(" W ");
								break;
							default:
								break;
							}
						}
					}
				}
			}
			System.out.println();
		}
		return super.toString();
	}
	
}
