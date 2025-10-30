import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
public class GameRunner
{
  public static void main(String[] args) {
        System.out.println("Welcome to the Game of Nim!");
        System.out.println("Created by: Daniel Z. and Pranav A.");

        Board.pileSize = ThreadLocalRandom.current().nextInt(Board.MIN_SIZE, Board.MAX_SIZE + 1);

        // Game nim = new Game();
        // nim.play();
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Start? (Y/N): ");
        String answer = keyboard.nextLine();

        if (answer.equalsIgnoreCase("Y")) {
          System.out.println("The game will start with a pile size of: " + Board.pileSize);
        }

        else {
          System.out.println("To start, please input Y.");
        }
        keyboard.close();
    }
}
