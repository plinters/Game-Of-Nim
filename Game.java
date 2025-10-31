import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

  private final String player1;
  private final String player2;
  private int player1Wins = 0;
  private int player2Wins = 0;
  private final boolean player2IsBot;

  public Game(String p1, String p2) {
    this(p1, p2, false);
  }

  public Game(String p1, String p2, boolean player2IsBot) {
    this.player1 = p1;
    this.player2 = p2;
    this.player2IsBot = player2IsBot;
  }

  /**
   * Play a single round. Returns the index of the winner: 1 for player1, 2 for player2.
   */
  public int playRound(Scanner input) {
    
    Board.pileSize = ThreadLocalRandom.current().nextInt(Board.MIN_SIZE, Board.MAX_SIZE + 1);

    System.out.println("\nStarting a new round!");
    System.out.println("Initial pile size: " + Board.pileSize);

    // Randomly choose who start
    int current = ThreadLocalRandom.current().nextInt(0, 2);
    String currentName = (current == 0) ? player1 : player2;
    System.out.println(currentName + " will go first.");


    while (Board.pileSize > 0) {
      int maxTake = Math.max(1, Board.pileSize / 2); // at least 1
      System.out.println("\n" + currentName + "'s turn. Pile size: " + Board.pileSize);
      System.out.println("You must take between 1 and " + maxTake + " pieces (inclusive). The last player to take a piece loses.");

      int take;
      // if it's player2's turn and player2 is a bot, compute bot move
      if (current == 1 && player2IsBot) {
        take = botChooseMove(Board.pileSize, maxTake);
        System.out.println(currentName + " (bot) chooses to take " + take + " piece" + (take == 1 ? "" : "s") + ".");
      } else {
        take = readIntInRange(input, 1, maxTake, currentName);
      }

      Board.pileSize -= take;
      System.out.println(currentName + " took " + take + " piece" + (take == 1 ? "" : "s") + ". Remaining: " + Board.pileSize);

      if (Board.pileSize == 0) {
        // The last player to take a piece loses, so the other player winss
        int winner = (current == 0) ? 2 : 1;
        if (winner == 1) player1Wins++; else player2Wins++;
        String winnerName = (winner == 1) ? player1 : player2;
        String loserName = (winner == 1) ? player2 : player1;
        System.out.println("\n" + currentName + " took the last piece and therefore loses.");
        System.out.println(winnerName + " wins this round! (" + loserName + " took the last piece)");
        printScores();
        return winner;
      }

      // switch player
      current = 1 - current;
      currentName = (current == 0) ? player1 : player2;
    }

    
    return 0;
  }

  private int readIntInRange(Scanner input, int min, int max, String playerName) {
    while (true) {
      System.out.print(playerName + ", enter number to take: ");
      String line = input.nextLine();
      try {
        int v = Integer.parseInt(line.trim());
        if (v < min || v > max) {
          System.out.println("Invalid move. You must enter a number between " + min + " and " + max + ".");
          continue;
        }
        return v;
      } catch (NumberFormatException e) {
        System.out.println("Please enter a valid integer.");
      }
    }
  }

  private int botChooseMove(int pile, int maxTake) {
  
    return ThreadLocalRandom.current().nextInt(1, maxTake + 1);
  }

  public void printScores() {
    System.out.println("\nCurrent scores:");
    System.out.println(player1 + ": " + player1Wins);
    System.out.println(player2 + ": " + player2Wins);
  }

}
