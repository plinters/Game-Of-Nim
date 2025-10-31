import java.util.Scanner;

public class GameRunner {
   public GameRunner() {
   }

   public static void main(String[] var0) {
      System.out.println("Welcome to the Game of Nim!");
      System.out.println("Created by: Daniel Z. and Pranav A.");
      Scanner var1 = new Scanner(System.in);
      System.out.print("Enter name for player 1: ");
      String var2 = var1.nextLine().trim();
      if (var2.isEmpty()) {
         var2 = "Player 1";
      }

      System.out.print("Enter name for player 2: ");
      String var3 = var1.nextLine().trim();
      if (var3.isEmpty()) {
         var3 = "Player 2";
      }

      Game var4 = new Game(var2, var3);
      boolean var5 = true;

      while(var5) {
         var4.playRound(var1);
         System.out.print("\nPlay again? (Y/N): ");
         String var6 = var1.nextLine().trim();
         if (!var6.equalsIgnoreCase("Y")) {
            var5 = false;
         }
      }

      System.out.println("\nThanks for playing! Final scores:");
      var4.printScores();
   }
}
