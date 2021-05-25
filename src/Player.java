public class Player {
    private String name;
    private int points;
    private int difficulty;
    private int bettingLimit;
    
    // Default constructor
    public Player() {
        this("Player", 1000, 1, 1000);
    }

    // Custom constructor
    public Player(String name, int points, int difficulty, int bettingLimit) {
        this.name = name;
        this.points = points;
        // Error checking if difficulty is valid - should not appear on user end
        if (difficulty < 1 || difficulty > 3) {
            System.out.println("Difficulty must be between 1 and 3 (Easy/Medium/Hard). Please change with setDifficulty().");
        }
        this.difficulty = difficulty;
        this.bettingLimit = bettingLimit;
    }
    
    // Getters
    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getBettingLimit() {
        return bettingLimit;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        // Error checking
        if (points < 0) {
            System.out.println("Points cannot be negative.");
        }
        else {
            this.points = points;
        }
    }

    public void setDifficulty(int difficulty) {
        // Error checking
        if (difficulty < 1 || difficulty > 3) {
            System.out.println("Difficulty must be between 1 and 3 (Easy/Medium/Hard).");
        }
        else {
            this.difficulty = difficulty;
        }
    }

    public void setBettingLimit(int bettingLimit) {
        // Error checking
        if (bettingLimit <= 0) {
            System.out.println("Betting limit must be a positive value");
        }
        else {
            this.bettingLimit = bettingLimit;
        }
    }

    // Get difficulty (originally stored as a number) in a word format
    public String getStringDifficulty() {
        int checkDifficulty = this.getDifficulty();
        String stringDifficulty;
        if (checkDifficulty == 1) {
            stringDifficulty = "Easy";
        }
        else if (checkDifficulty == 2) {
            stringDifficulty = "Medium";
        }
        else if (checkDifficulty == 3) {
            stringDifficulty = "Hard";
        }
        else {
            stringDifficulty = "Unknown";
        }
        return stringDifficulty;
    }

    // Override default toString() for leaderboard display
    @Override
    public String toString() {
        return this.getName() + " " + this.getPoints() + " (" + this.getStringDifficulty() + ")";
    }
}
