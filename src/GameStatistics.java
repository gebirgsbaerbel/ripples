import java.util.ArrayList;
import java.util.List;

public class GameStatistics {
	List<GameScore> gameScores = new ArrayList<GameScore>();
	
	void showStatistics() {
		int totalScoreRed = 0;
		int totalScoreBlue = 0;
		int totalScoreWhite = 0;
		for (GameScore gameScore : gameScores) {
			totalScoreRed += gameScore.scoreRed;
			totalScoreBlue += gameScore.scoreBlue;
			totalScoreWhite += gameScore.scoreWhite;
		}
		double totalGamesPlayed = (double) gameScores.size();
		double averageScoreRed = totalScoreRed / totalGamesPlayed;
		double averageScoreBlue = totalScoreBlue / totalGamesPlayed ;
		double averageScoreWhite = totalScoreWhite / totalGamesPlayed;
		
		System.out.println("Statitics");
		System.out.println("Games Played: " + totalGamesPlayed);
		System.out.println("White Tiles Total: " + totalScoreWhite + "\tWhite Tiles Average: " + averageScoreWhite);
		System.out.println("Red Tiles Total: " + totalScoreRed + "\tRed Tiles Average: " + averageScoreRed);
		System.out.println("Blue Tiles Total: " + totalScoreBlue + "\tBlue Tiles Average: " + averageScoreBlue);
	}

}
