import java.util.Random;

public class SlotsGame {

    // Create possible categories for slots
    static String[] slots = {"BAR", "7", "APPLE", "BELL", "CROWN", "BANANA"};

    // Slots introductory method - gets bets, powerups, and starts gameplay loop 
    public static void Slots(Player player) {
        System.out.println("Welcome to Slots!");

        System.out.println("Enter points to bet. You have " + player.getPoints() + " points.");
        String betInput = App.input.nextLine();
        int bet = App.betting(betInput, player);

        boolean powerup = App.usePowerup(player, "Slots");
        
        slotsGameLoop(player, bet, powerup);
    }

    // Slots gameplay loop - prints animation of slots spinning every 0.2 seconds and displays result at end
    public static void slotsGameLoop(Player player,int bet, boolean powerup) {
        // Animation
        for (int i = 0; i < 5; i++) {
            System.out.println("| "+ randomSlot(slots, powerup) + " | " + randomSlot(slots, powerup) + " | "  + randomSlot(slots, powerup) + " |");
            System.out.println("Spinning...");
            // Wait 0.2 seconds
            try {
                Thread.sleep(200);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        // Result, store slots in variables
        System.out.println("Result:");
        String slot1 = randomSlot(slots, powerup);
        String slot2 = randomSlot(slots, powerup);
        String slot3 = randomSlot(slots, powerup);
        // Display result
        System.out.println("| "+ slot1 + " | " + slot2 + " | "  + slot3 + " |");
        // Check if all slots are the same - return 10 times bet
        if ((slot1.equals(slot2) && slot1.equals(slot3)) || (slot2.equals(slot1) && slot2.equals(slot3)) || (slot3.equals(slot1) && slot3.equals(slot2))) {
            System.out.println("All matches!");
            App.changePoints(player, (bet*10), "Slots");
        }
        // Check if a matching pair - return double bet
        else if (slot1.equals(slot2) || slot1.equals(slot3) || slot2.equals(slot3)) {
            System.out.println("You got a pair!");
            App.changePoints(player, bet, "Slots");
        }
        // No matches - player loses bet
        else {
            System.out.println("No matches");
            App.changePoints(player, (bet*-1), "Slots");
        }
    }

    // Gets a random slot category from array
    public static String randomSlot(String[] slots, boolean powerup) {
        Random r = new Random();
        int low = 0;
        int high = slots.length;
        // Powerup functionality - removes a category
        if (powerup) {
            high -= 1;
        }
        return slots[r.nextInt(high-low) + low];
    }
}
