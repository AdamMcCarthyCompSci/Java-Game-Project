public class VIPPlayer extends Player {
    private int powerups;

    // Default constructor
    public VIPPlayer() {
        powerups = 3;
    }

    // Custom constructor
    public VIPPlayer(String name, int points, int difficulty, int bettingLimit, int powerups) {
        super(name, points, difficulty, bettingLimit);
        // Error handling - should not show up on user end
        if (powerups < 0) {
            System.out.println("Powerups should not be negative. Please change with setPowerups().");
        }
        this.powerups = powerups;
    }

    // Getter
    public int getPowerups() {
        return powerups;
    }

    // Setter
    public void setPowerups(int powerups) {
        // Error handling
        if (powerups < 0) {
            System.out.println("Powerups cannot be negative.");
        }
        else {
            this.powerups = powerups;
        }
    }

    // Overrides the player toString() which in turn overrided the default toString() - adds VIP tag on leaderboard
    @Override
    public String toString() {
        return super.toString() + " (VIP)";
    }
}
