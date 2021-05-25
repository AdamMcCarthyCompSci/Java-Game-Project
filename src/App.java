import java.util.Scanner;
import java.util.ArrayList;

public class App {
    // Scanner object used thoughout entire program
    static Scanner input = new Scanner(System.in);
    // Holds player objects and associated attributes
    static ArrayList<Player> players = new ArrayList<Player>();
    // Holds toString of players in order of points
    static ArrayList<String> leaderboard = new ArrayList<String>();
    // Main method only calls menu, menu handles program from there
    public static void main(String[] args) {
        menu();
    }

    // Menu functionality - create new player, view leaderboard, view instructions, quit game
    // Also handles difficulty, mode, player type
    public static void menu() {
        System.out.println("Please choose an option: \n" +
        "1: New Game \n" +
        "2: Leaderboard \n" +
        "3: Instructions \n" +
        "4: Quit \n");

        String input1 = input.nextLine();

        menuInput1Check(input1);
        if (input1.equals("2") || input1.equals("3")) {
            return;
        }

        System.out.println("Please enter a name:");
        String input2 = input.nextLine();

        String name = menuInput2Check(input2);

        System.out.println("Please choose a difficulty: \n" +
                "1: Easy \n" +
                "2: Medium \n" +
                "3: Hard \n" +
                "4: Quit \n");
        String input3 = input.nextLine();
        String difficulty = menuInput3Check(input3);

        System.out.println("Please choose your player type: \n" +
                "1: Default \n" +
                "2: VIP \n" +
                "3: Quit \n");
        String input4 = input.nextLine();

        gameSelect("0", true, menuInput4Check(input4, name, difficulty));
        
        input.close();
    }


    // Main leaderboard/instructions functionality
    public static void menuInput1Check(String input1) {
        if (input1.equals("1")) {
            System.out.println("Creating new player...");
        }
        else if (input1.equals("2")) {
            System.out.println("LEADERBOARD");
            if (players.size() == 0) {
                System.out.println("No players have cashed out yet \n");
                menu();
            }
            else {
                leaderboard.clear();
                while (leaderboard.size() < players.size()) {
                    int maxPoints = 0;
                    Player maxPlayer = players.get(0);
                    for (int i = 0; i < players.size(); i++) {
                        if (!leaderboard.contains(players.get(i).toString())) {
                            if (players.get(i).getPoints() > maxPoints) {
                                maxPlayer = players.get(i);
                                maxPoints = maxPlayer.getPoints();
                            }
                        }
                    }
                    leaderboard.add(maxPlayer.toString());
                }
                leaderboard.forEach(player -> System.out.println(player));
                System.out.println("\n");
                menu();
            }
        }
        else if(input1.equals("3")) {
            System.out.println("INSTRUCTIONS");
            System.out.println("There are three games within this program \n" +
                                "- Blackjack \n" +
                                "- Horse Racing \n" +
                                "- Slots \n" +
                                "The mode you select determines the amount of points you start with \n" +
                                "You must bet points when playing these games \n" +
                                "If you run out of points, your character is deleted and you return to the menu \n" +
                                "You can cash out at any time to get on the leaderboard, but you can't access the character afterwards \n" +
                                "VIP Players get 3 powerups which improve their odds for a round, they can be used in any game \n" +
                                "VIP players also get their betting limit increased from 1,000 points to 10,000 points \n" +
                                "The leaderboard will display your points, game mode, and VIP status \n");
            menu();
        }
        else if (input1.equals("4")) {
            System.out.println("Exiting game...");
            System.exit(0);
        }
        else {
            String[] invalidArray = new String[]{"1", "2", "3", "4"};
            menuInput1Check(invalidArgument(invalidArray));
        }
    }

    // Generic error handling - looks if input is in provided array, if not then asks again
    public static String invalidArgument(String [] arguments) {
        String newArgument = "";
        boolean valid = false;
        while(true) {
            System.out.print("Valid inputs are: ");
            for (int i = 0; i < arguments.length; i++) {
                System.out.print(arguments[i] + " ");
            }
            System.out.println("");
            System.out.println("Enter a valid input:");
            String menuInput1 = input.next();
            for (int j = 0; j < arguments.length; j++) {
                if (menuInput1.equals(arguments[j])) {
                    System.out.println("Valid input!");
                    newArgument = arguments[j];
                    valid = true;
                    break;
                }
            }
            if (valid) {
                break;
            }
        }
        return newArgument;
    }

    // Gets player's name
    public static String menuInput2Check(String input2) {
        String name = input2;
        for(Player player : players) {
            if (player.getName().equals(name)) {
                name = menuInput2Check(invalidName());
                return name;
            }
        }
        return name;
    }

    // Error handling for valid name - loops until name not taken (inside players ArrayList)
    public static String invalidName() {
        System.out.println("That name has already been taken.");
        boolean taken = false;
        String menuInput2 = "";
        while(true) {
            System.out.print("Taken names are: ");
            players.forEach(player -> System.out.print(player.getName() + " "));
            System.out.println("");
            System.out.println("Enter a name that has not yet been taken:");
            if (input.hasNextLine()) {
                menuInput2 = input.nextLine();
            }
            for(Player player : players) {
                if (player.getName().equals(menuInput2)) {
                    System.out.println("That name has already been taken.");
                    taken = true;
                }
            }
            if (taken == false) {;
                break;
                }
            taken = false; 
        }
        System.out.println("Valid name!");
        return menuInput2;
        };

    // Mode selection - defines how many points players start with
    public static String menuInput3Check(String input3) {
        String[] invalidArray = new String[]{"1", "2", "3", "4"};
        String difficulty = "";
        if (input3.equals("1")) {
            System.out.println("Selecting Easy Mode...");
            difficulty = "1";
        }
        else if (input3.equals("2")) {
            System.out.println("Selecting Medium Mode...");
            difficulty = "2";
        }
        else if (input3.equals("3")) {
            System.out.println("Selecting Hard Mode...");
            difficulty = "3";
        }
        else if (input3.equals("4")) {
            System.out.println("Exiting game...");
            System.exit(0);
        }
        else {
            difficulty = menuInput3Check(invalidArgument(invalidArray));
        }
        return difficulty;
    }

    // Instantiates player/VIP with attributes provided earlier
    public static Player menuInput4Check(String input4, String name, String difficulty) {
        String[] invalidArray = new String[]{"1", "2", "3"};
        if (input4.equals("1")) {
            System.out.println("Selecting Default Settings...");
            int bettingLimit = 1000;
            int points = 100;
            int difficultyInt = Integer.parseInt(difficulty);
            if (difficultyInt == 2) {
                points = 500;
            }
            else if (difficultyInt == 1) {
                points = 1000;
            }
            System.out.println("DIFFICULTY:"+difficultyInt);
            Player playerSettings = new Player(name, points, difficultyInt, bettingLimit);
            players.add(playerSettings);
            return playerSettings;
        }
        else if (input4.equals("2")) {
            System.out.println("Selecting VIP Settings...");
            int bettingLimit = 10000;
            int points = 100;
            int powerups = 3;
            int difficultyInt = Integer.parseInt(difficulty);
            if (difficultyInt == 2) {
                points = 500;
            }
            else if (difficultyInt == 1) {
                points = 1000;
            }
            Player playerSettings = new VIPPlayer(name, points, difficultyInt, bettingLimit, powerups);
            players.add(playerSettings);
            return playerSettings;
        }
        else if (input4.equals("3")) {
            System.out.println("Exiting game...");
            System.exit(0);
        }
        else {
            Player playerSettings = menuInput4Check(invalidArgument(invalidArray), name, difficulty);
            return playerSettings;
        }
        return new Player();
    }

    // Game selection mechanic - allows users to cash out and save result to leaderboard too
    public static void gameSelect(String input5, boolean firstInput, Player player) {
        if (firstInput) {
            System.out.println("Please choose a game to play: \n" +
            "1: Blackjack \n" +
            "2: Horse Racing \n" +
            "3: Slots \n" +
            "4: Cash out and exit to main menu \n" +
            "5: Quit \n");
            input5 = input.nextLine();
        }

        String[] invalidArray = new String[]{"1", "2", "3", "4", "5"};
        if (input5.equals("1")) {
            System.out.println("Selecting Blackjack...");
            BlackjackGame.Blackjack(player);
        }
        else if (input5.equals("2")) {
            System.out.println("Selecting Horse Racing...");
            HorseRacingGame.HorseRacing(player);
        }
        else if (input5.equals("3")) {
            System.out.println("Selecting Slots...");
            SlotsGame.Slots(player);
        }
        else if (input5.equals("4")) {
            System.out.println("Cashing out and exiting to main menu...");
            menu();
        }
        else if (input5.equals("5")) {
            System.out.println("Exiting game...");
            System.exit(0);
        }
        else {
            gameSelect(invalidArgument(invalidArray), false, player);
        }
    }

    // Generic function used to add/remove a player's points - can be used in any game
    public static void changePoints(Player player, int points, String game) {
        int playerPoints = player.getPoints();
        playerPoints += points;
        player.setPoints(playerPoints);

        if (playerPoints <= 0) {
            System.out.println("Game over! You ran out of points. \n" +
                                "Returning to main menu...");
            players.remove(player);
            player = null;
            App.menu();
            return;
        }
        else {
            if (points > 0) {
                System.out.println("Congratulations! You won " + points + " point(s)!");
            }
            else if (points < 0) {
                System.out.println("You lost " + (points*-1) + " point(s).");
            }
            else {
                System.out.println("You have neither won or lost points");
            }
            System.out.println("You currently have " + playerPoints + " point(s). \n" + 
            "1: Play again \n" +
            "2: Game selection \n");
            String gameSelectInput = input.nextLine();
            newGame(player, gameSelectInput, game);
        }
        
    }

    // First input restarts active game, second input sends player to game selection method
    public static void newGame(Player player, String gameSelectInput, String game) {
        if (gameSelectInput.equals("1")) {
            if (game.equals("Blackjack")) {
                BlackjackGame.Blackjack(player);
            }
            else if (game.equals("Horse Racing")) {
                HorseRacingGame.HorseRacing(player);
            }
            else if (game.equals("Slots")) {
                SlotsGame.Slots(player);
            }
            else {
                System.out.println("Game not found!");
            }
        }
        else if (gameSelectInput.equals("2")) {
            gameSelect("0", true, player);
        }
        else {
            String[] invalidArray = new String[]{"1", "2"};
            newGame(player, invalidArgument(invalidArray), game);
        }
    }

    // Generic function for player betting points
    public static int betting(String betInput, Player player) {
        int bet = 0;
        try {
            bet = Integer.parseInt(betInput);
            if 
            (bet < 1 || bet > player.getPoints()) {
                System.out.println("Invalid input. Bet must be between 1 and " + player.getPoints());
                betInput = input.nextLine();
                bet = betting(betInput, player);
            }
        }
        catch(Exception e) {
            System.out.println("Invalid input. Bet must be an integer.");
            betInput = input.nextLine();
            bet = betting(betInput, player);
        }
        return bet;
    }

    // VIP powerup functionality - tracks powerups and sends decision to powerupSelect() method
    public static boolean usePowerup(Player player, String game) {
        if (player instanceof VIPPlayer) {
            int powerups = ((VIPPlayer) player).getPowerups();
            System.out.println("You currently have " + powerups + " powerup(s)");
            if  (powerups > 0) {
                System.out.println("Use a powerup this round? \n" +
                                    "1: Yes \n" +
                                    "2: No \n");
                String powerupInput = input.nextLine();
                return powerupSelect(powerupInput, player, powerups, game);
            }
        }
        return false;
    }

    // First input activates powerup in active game, second input is when player doesn't use powerup
    public static boolean powerupSelect(String input, Player player, int powerups, String game) {
        boolean result;
        if (input.equals("1")) {
            System.out.println("Powerup activated!");
            if (game.equals("Blackjack")) {
                System.out.println("The dealer now hits on 19 instead of 18 for this round");
            }
            else if (game.equals("Horse Racing")) {
                System.out.println("Your horse now has a speed boost for this round");
            }
            else if (game.equals("Slots")) {
                System.out.println("One slot type removed to improve odds for this round");
            }
            else {
                System.out.println("Game not recognised");
            }
            powerups -= 1;
            ((VIPPlayer) player).setPowerups(powerups);
            result = true;
        }
        else if (input.equals("2")) {
            result =  false;
        }
        else {
            String[] invalidArray = new String[]{"1", "2"};
            result = powerupSelect(invalidArgument(invalidArray), player, powerups, game);
        }
        return result;

    }
}