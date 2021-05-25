import java.util.Random;
import java.util.ArrayList;

public class HorseRacingGame {
    
    // Holds horses' progress in race
    static ArrayList<String> horses = new ArrayList<String>();
    // Holds the number of each horse
    static ArrayList<String> horseNumbers = new ArrayList<String>();
    
    // Opening sequence for Horse Racing - gets bets, powerups, and send info on for user to pick a horse
    public static void HorseRacing(Player player) {
        horses.clear();
        System.out.println("Welcome to Horse Racing!");

        System.out.println("Enter points to bet. You have " + player.getPoints() + " points.");
        String betInput = App.input.nextLine();
        int bet = App.betting(betInput, player);

        int horseCount = 5;
        for (int i = 0; i < horseCount; i++) {
            horses.add(i+1 + " ");
        }
        boolean powerup = App.usePowerup(player, "Horse Racing");
        horsePick(player, bet, powerup);
    }

    // Asks what horse the user wants to bet on and starts gameplay loop
    public static void horsePick(Player player, int bet, boolean powerup) {
        System.out.println("Pick a horse to bet on (between numbers 1 and " + horses.size() + ")");
        String horseNumberInput = App.input.nextLine();
        try {
            int horseNumberInputInt= Integer.parseInt(horseNumberInput);
            if (horseNumberInputInt > 0 && horseNumberInputInt <= horses.size()) {
                horseRacingGameLoop(player, bet, horseNumberInputInt, powerup);
                return;
            }
            else {
                System.out.println("Invalid input");
                horsePick(player, bet, powerup);
                return;
            }
        }
        catch(Exception e) {
            System.out.println("Invalid input");
            horsePick(player, bet, powerup);
            return;
        }
    }

    // Gameplay loop - repeatedly prints random number of "-" until at least one reaches desired number
    public static void horseRacingGameLoop(Player player, int bet, int horseNumber, boolean powerup) {
        // Sets up race course length and structure
        int raceLength = 50;
        String raceCourse = "";
        for (int i = 0; i < raceLength; i++) {
            raceCourse += "_";
        }

        // Initialises if there is a winner and if the player has won
        boolean winner = false;
        boolean playerWin = false;

        // Keep looping until function returns - only happens when user wins/ties/loses
        while (true) {
            int winnerCount = 0;
            // Wrapper of race course length
            System.out.println(raceCourse);
            // Loop through all horses
            for(int i = 0; i < horses.size(); i++) {
                // Powerup functionality
                int speed = 5;
                if (powerup && (i + 1) == horseNumber) {
                    speed = 6;
                }
                // Add a random amount of "-" each turn
                for (int j = 0; j < randomSpeed(speed); j++) {
                    // If horse reaches finish
                    if (horses.get(i).length() == 49) {
                        // There is a winner, add 1 winner, check if winner is player's horse
                        winner = true;
                        winnerCount++;
                        if ((i + 1) == horseNumber) {
                            playerWin = true;
                        }
                        // Only break from loop when at least one horse wins
                        break;
                    } 
                    horses.set(i, horses.get(i) + "-");
                }
                System.out.println(horses.get(i));
            }
            // Race course wrapper
            System.out.println(raceCourse);
            if (winner) {
                // If multiple winners and player won
                if (winnerCount > 1 && playerWin) {
                    System.out.println("Win tied! Returning bet...");
                    App.changePoints(player, 0, "Horse Racing");
                }
                // If just the player won
                else if (playerWin) {
                    System.out.println("Winner!");
                    App.changePoints(player, (bet*5), "Horse Racing");
                }
                // If the player didn't win
                else {
                    App.changePoints(player, (bet*-1), "Horse Racing");
                }
                return;
            }
            // Wait 0.2 seconds
            try {
                Thread.sleep(200);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Return a random number between 1 and the given number (speed)
    public static int randomSpeed(int maxSpeed) {
        Random r = new Random();
        int low = 1;
        int high = maxSpeed;
        return r.nextInt(high-low) + low;
    }
}
