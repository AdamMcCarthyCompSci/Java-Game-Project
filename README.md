## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

## OVERVIEW

This project involved building a program that enables users to play a series of games on the console. It involves users being able to create new players by selecting their difficulty, their name, and their status. Once a player has been created, they are given the choice between three games: Blackjack, Horse Racing, and Slots. Within each game the player can bet their points on a particular outcome. At any stage they can cash in their points and solidify their place on the leaderboard. However, if they do, they lock themselves out of that player forever, and their score is saved as long as the program is running. It can be accessed via the main menu. If the user loses all of their points and bankrupts themselves, then their player is deleted and their name will not be on the leaderboard.
Upon creating a player, the user must decide between Easy/Medium/Hard difficulties. This simply refers to the amount of points that the player starts with. Additionally, they can choose between a Standard Player and a VIP Player. With VIP status, the player can utilise three powerups. These powerups provide increased odds in the player’s favour for a single round. Powerups cannot be replenished, so they must be used tactically. VIP status also means the player’s betting limit is increased from 1,000 points to 10,000 points. Lastly, players can only select a name that is not already on the leaderboard. This is done in an effort to differentiate between players.
It is also important to note that players can exit the game in any of the menus. If they are struggling to understand the rules, they can always refer to the instructions which can be accessed via the main menu.

### BLACKJACK

Blackjack involves a player being dealt two cards upon the start of the game. These cards range from 2 to 10, with an additional 3 suit cards representing the number 10. There is also an Ace, which can represent either 1 or 11 depending on the player’s total card value. The player can ‘hit’ to be dealt an extra card. The objective is to get as close to 21, or ‘Blackjack’, as possible without actually going over.
The thing that makes this game interesting is that you are playing against a dealer. The dealer will always ‘hit’ for another card until they reach at least 18. If the player hasn’t gone ‘bust’, meaning they went over 21, then it is decided by examining whoever is closer to 21. Note that the dealer can go bust too. It’s important to note that if both the dealer and the player get the same number, then the player’s bet is returned to them.
The VIP powerup for this game is that the dealer’s hit number changes from 18 to 19 for a single round. While this might result in more Blackjacks, it also means that the dealer will go bust much more than usual, which will benefit the player significantly.

### HORSE RACING

This is a game where the player must bet on a ‘horse’ of their choice, which is represented by a line. There are 5 horses to choose from. Each horse moves a random amount every 0.2 seconds in an effort to reach a finish line. If the player’s horse wins, they get five times their winnings back. However, if there is a tie, the player’s bet is returned to them. What makes this game interesting is that the VIP powerup gives your chosen horse a ‘boost’, where their random amount’s bounds are increased for a single round.

### SLOTS

In Slots, the player must get matching symbols. There are 6 symbols in total that are randomly selected and placed into three sections. If the player gets a matching pair then they get double their bet. If they get all three matching, then they get 10 times their bet. The powerup for this game is interesting in that it removes a symbol from the selection process, so the player only has to worry about matching with 5 symbols instead of 6. This improves their odds significantly for a single round.