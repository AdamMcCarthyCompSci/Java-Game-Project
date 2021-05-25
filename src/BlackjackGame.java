import java.util.ArrayList;
import java.util.Random;

public class BlackjackGame {

    // Called when giving card to player/dealer
    static String[] blackjackDeck = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "King", "Queen", "Jack", "Ace"};
    // Holds player's hand
    static ArrayList<String> playerHand = new ArrayList<String>();
    // Holds dealer's hand
    static ArrayList<String> dealerHand = new ArrayList<String>();

    // Blackjack opening sequence, sets up bets/powerups/cards
    public static void Blackjack(Player player) {
        playerHand.clear();
        dealerHand.clear();

        System.out.println("Welcome to Blackjack!");

        System.out.println("Enter points to bet. You have " + player.getPoints() + " points.");
        String betInput = App.input.nextLine();
        int bet = App.betting(betInput, player);

        boolean powerup = App.usePowerup(player, "Blackjack");

        System.out.println("You draw a card: " + hit(playerHand, blackjackDeck) + "\n" +
                            "The dealer draws a card: " + hit(dealerHand, blackjackDeck));
        
        blackjackGameLoop("1", player, bet, powerup);
    }

    // Main Blackjack game element - user can either hit or stay
    public static void blackjackGameLoop(String input, Player player, int bet, boolean powerup) {
        if (input.equals("1")) {
            System.out.println("You draw a card: " + hit(playerHand, blackjackDeck) + "\n" +
            "Your hand: " + viewHand(playerHand) + "\n" +
            "Dealer's hand: " + viewHand(dealerHand) + "\n" +
            "1: Hit \n" +
            "2: Stay \n");
            // Checks if user busts
            if (checkCount(playerHand) > 21) {
                System.out.println("Bust! Your total is over 21.");
                App.changePoints(player, (bet*-1), "Blackjack");
                return;
            }
            // Get next decision
            String input1= App.input.nextLine();
            blackjackGameLoop(input1, player, bet, powerup);
            
        }
        // User stays
        else if (input.equals("2")) {
            // Powerup functionality
            int dealerHitNumber = 18;
            if (powerup) {
                dealerHitNumber = 20;
            }
            int playerCount = checkCount(playerHand);
            System.out.println("You stayed at: " + playerCount);
            // Dealer hits until minimum number
            while (checkCount(dealerHand) < dealerHitNumber) {
                System.out.println("The dealer draws a card: " + hit(dealerHand, blackjackDeck));
                // If dealer is between minimum and 21, they stay
                if (checkCount(dealerHand) <= 21 && checkCount(dealerHand) >= dealerHitNumber) {
                    System.out.println("The dealer stays at " + checkCount(dealerHand));
                    // Player wins
                    if (checkCount(playerHand) > checkCount(dealerHand)) {
                        App.changePoints(player, bet, "Blackjack");
                        return;
                    }
                    // Player draws
                    else if (checkCount(playerHand) == checkCount(dealerHand)) {
                        App.changePoints(player, 0, "Blackjack");
                        return;
                    }
                    // Player loses
                    else {
                        App.changePoints(player, (bet*-1), "Blackjack");
                        return;
                    }

                }
                // Dealer busts - player wins
                else if (checkCount(dealerHand) > 21) {
                    System.out.println("The dealer busts!");
                    App.changePoints(player, bet, "Blackjack");
                    return;
                }
            }
        }
        else {
            String[] invalidArray = new String[]{"1", "2"};
            blackjackGameLoop(App.invalidArgument(invalidArray), player, bet, powerup);
        }
    }

    // Give dealer/player a random card from the deck
    public static String hit(ArrayList<String> hand, String[] deck) {
        Random randomNumber = new Random();
        int randomCard = randomNumber.nextInt(deck.length);
        hand.add(deck[randomCard]);
        return deck[randomCard];
    }

    // Show all cards in hand
    public static String viewHand(ArrayList<String> hand) {
        String viewHand = "";
        for(String card : hand) {
            viewHand += card + " ";
        }
        return viewHand;
    }

    // Get value of cards in hand
    public static int checkCount(ArrayList<String> hand) {
        int total = 0;
        int aceCount = 0;
        // Loop through cards in hand
        for(String card : hand) {
            // Ace check (1 or 11)
            if (card.equals("Ace")) {
                aceCount += 1;
                total += 11;
            }
            // Turn face cards to value
            else if (card.equals("Jack") || card.equals("Queen") || card.equals("King")) {
                total += 10;
            }
            else {
                total += Integer.parseInt(card);
            }
        }
        // If bust, check if aces and change 11's to 1's
        if (total > 21) {
            for (int i = 0; i < aceCount; i++) {
                total -= 10;
                if (total <= 21) {
                    break;
                }
            }
        }
        return total;
    }
}
