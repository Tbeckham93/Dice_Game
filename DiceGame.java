
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * DiceGame class
 * @author Timothy Beckham
 */
public class DiceGame { //start of Dice game class
// the fields needed for this class
    private Player[] players;
    private int numPlayers;
    private int pot;
    private boolean gameOver;
    private boolean hardMode = false;

    /**
     * Basic constructor, calls setUpGame method.
     */
    public DiceGame() {  //constructor for this class, calls setUpGame method
        setUpGame();
    }

    /**
     * This method gets all players
     * @return all the players
     */
    public Player[] getPlayers() { //gets all the players and returns them
        return players;
    }

    /**
     * this gets the number of players
     * @return gets the number of players
     */
    public int getNumPlayers() { //gets all the number of players and returns them.
        return numPlayers;
    }

    /**
     * checks if the game is over
     * @return true or false based of if the game is over.
     */
    public boolean isGameOver() { //gets the boolean for game state and returns it.
        return gameOver;
    }

    /**
     * This will set the game up.
     * Pick hard or normal mode, input the number of players, and displays the rules.
     */
    public void setUpGame() { //setUpGame method...
        Scanner sc = new Scanner(System.in); // makes a scanner object
        int mode;
        System.out.println("press 1 for hard mode, anything else will be normal."); 
        mode = sc.nextInt(); //scans in "mode" for normal/hard mode
        if (mode == 1) { // sets hardMode to true if a 1 was entered for "mode"
            hardMode = true;
        }
        System.out.println("How many players are playing?");
        numPlayers = sc.nextInt(); // scans in the number of players playing the game.
        sc.nextLine();
        this.players = new Player[numPlayers]; //this makes up the array to be the right size we need based on the number of players.
        for (int i = 0; i < players.length; i++) { // loop get set the player names.
            System.out.println("Whats the name of player " + (i + 1) + "?");
            String name = sc.nextLine();
            players[i] = new Player(name); //sets the name
        }
        displayRules(); //displays the rules.
    }

    /**
     * This will display the rules for both normal and hard mode.
     */
    public void displayRules() { //outputs the rules based on mode normal/hard
        System.out.println("RULES!");
        if (hardMode != true) { //rules if hard mode
            System.out.println("Each player places a bet and chooses a number between 2 and 12.");
            System.out.println("The total of the bets forms the pot.");
            System.out.println("Then 2 dice are rolled.");
            System.out.println("If one of the players bet on the added value of both dice correctly, she or he win the entire pot.");
            System.out.println("If more than one player bet on that number, the one who bet the most, wins the entire pot.");
            System.out.println("If there is a tie, they split the pot.");
            System.out.println("If nobody bet on that number, the money remains in the pot for the next round.");
            System.out.println("The game is over if one the players runs out of money.");
        } else { //rules if normal
            System.out.println("Each player places a bet and chooses a number between 2 and 12.");
            System.out.println("The total of the bets forms the pot.");
            System.out.println("Then 2 dice are rolled.");
            System.out.println("If one of the players bet on the result of each individual die correctly, she or he win the entire pot.");
            System.out.println("If more than one player bet on the right numbers, the one who bet the most, wins the entire pot.");
            System.out.println("If there is a tie, they split the pot.");
            System.out.println("If nobody bet on that number, the money remains in the pot for the next round.");
            System.out.println("The game is over if one the players runs out of money.");
        }
    }

    /**
     * This method is the logic for the game
     * It loops the players through the playTurn method sets the pot amount, 
     * rolls both dice, and checks the winner for both normal and hard mode.
     * then checks if the game is over.
     */
    public void playGame() { //play game method
        for (int i = 0; i < players.length; i++) { //loops through each player and plays their turn and sets the pot for each round.
            playTurn(players[i]);
            pot = pot + players[i].getBetAmount(); //sets pot
        }
        System.out.println("The pot has $ " + pot);
        Random die = new Random(); //gets the die numbers psudorandomly
        int die1 = die.nextInt(6) + 1;
        int die2 = die.nextInt(6) + 1;
        System.out.println("die 1 rolled a " + die1 + " and die 2 rolled a " + die2);
        int outcome = die1 + die2;
        System.out.println("Both dice add to " + outcome);
        if (hardMode == false) { // checks winner if normal mode
            checkWinner(outcome);
        } else { //checks winner if hard mode
            checkWinner(die1, die2);
        }
        for (int i = 0; i < players.length; i++) { //checks if any of the players have $0, if ture stes gameOver to true
            if (players[i].getBalance() == 0) {
                gameOver = true;
            }
        }
    }

    /**
     * Plays the turn for each player.
     * shows the balance for every player on their turn, takes in their bet and
     * takes in the guess for the die, for both normal and hard mode.
     * @param player
     */
    public void playTurn(Player player) { //playTurn method

        System.out.println("You have " + player.getBalance()); //shows player his/her balance
        Scanner sc = new Scanner(System.in);
        int betAmount;
        for(int i = 0; i < 1; i++)
        {
            System.out.println(player.getName() + ", how much would you like to bet?");
            betAmount = sc.nextInt(); // scans in bet of player
            if (betAmount > 0 && betAmount <= player.getBalance()) { // checks if bet is more than 0 and not more than the players balance
            player.setBetAmount(betAmount); // sets players bet
            player.setBalance(player.getBalance() - betAmount); // takes bet amount of players balance.
            if (hardMode == false) { //normal mode
                for (int j = 0; j < 1; j++) { // asks player for one guess for the added value of both dice.
                    int guess;
                    System.out.println("What do you think the number of the dice will be? ");
                    guess = sc.nextInt();
                    if (guess >= 2 && guess <= 12) { //makes sure teh guess is between 2 and 12
                        player.setGuess(guess);
                    } else {
                        System.out.println("Pick a number between 2 and 12 please.");
                        j--; // this will make the loops repeat if the guess i not between 2 and 12.
                    }
                }
            } else { //hard mode - same as normal mode other than the player places a guess for both dice (1-6)
                for (int j = 0; j < 1; j++) {
                    int guess1;
                    int guess2;
                    System.out.println("What do you think the number of the first die will be? ");
                    guess1 = sc.nextInt(); //scans in guess1
                    System.out.println("What do you think the number of the second die will be? ");
                    guess2 = sc.nextInt();//scans in guess2
                    if ((guess1 >= 1 && guess1 <= 6) && (guess2 >= 1 && guess2 <= 6)) { //makes sure both of the players guesses are berween 1 and 6
                        player.setGuess1(guess1);
                        player.setGuess2(guess2);
                    } else {
                        System.out.println("Pick two numbers between 1 and 6 please.");
                        j--; //repeats loop if not between i and 6
                    }
                }
            }
        } else {
            System.out.println("Your bet is either less than 0 or more than you have in your balance.");
            i--; //repeats loop if not in bounds.
        }
        }
    }

    /**
     * This will check for winner(s) if any. then split up the pot based on how many winners there are.
     * This is the normal mode checkWinner.
     * @param outcome
     */
    public void checkWinner(int outcome) {
        ArrayList<Player> winner = new ArrayList<>(); //makes a winner arraylist
        for (int i = 0; i < players.length; i++) {
            if (players[i].getGuess() == outcome) {
                winner.add(players[i]); //adds player to winners array
            }
        }
        if (winner.isEmpty()) { //checks for no winner
            System.out.println("There are no winners, all the money stays in the pot.");
        } else { //if there is a winner
            int max = 0;
            for (int i = 0; i < winner.size(); i++) { //loop to see which winner had the highest bet
                if (winner.get(i).getBetAmount() > max) {
                    max = winner.get(i).getBetAmount();
                }
            }
            for (int i = 0; i < winner.size(); i++) {//loops to how many players made the same, highest bet
                if (winner.get(i).getBetAmount() != max) {
                    winner.remove(i); //removed from winners array if not == to highest bet
                }
            }
            for (int i = 0; i < winner.size(); i++) {//loops to check players array aginst winners list
                for (int j = 0; j < players.length; j++) {
                    if (players[j] == winner.get(i)) {
                        players[j].setBalance(players[j].getBalance() + (pot / winner.size())); //if player == to winner that player gets a part of the pot based on number of winners
                        System.out.println(players[j].getName() + " has won $" + (pot / winner.size()));
                    }
                }
            }
        }
    }

    /**
     * This method checks the winner(s) of each game if there is any.
     * (Hard mode checkWinner)
     * @param outcome1
     * @param outcome2
     */
    public void checkWinner(int outcome1, int outcome2) {
        ArrayList<Player> winner = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            if ((players[i].getGuess1() == outcome1 || players[i].getGuess1() == outcome2) && (players[i].getGuess2() == outcome1 || players[i].getGuess2() == outcome2)) {
                winner.add(players[i]);
            }
        }
        if (winner.isEmpty()) {
            System.out.println("There are no winners, all the money stays in the pot.");
        } else {
            int max = 0;
            for (int i = 0; i < winner.size(); i++) {
                if (winner.get(i).getBetAmount() > max) {
                    max = winner.get(i).getBetAmount();
                }
            }
            for (int i = 0; i < winner.size(); i++) {
                if (winner.get(i).getBetAmount() != max) {
                    winner.remove(i);
                }
            }
            for (int i = 0; i < winner.size(); i++) {
                for (int j = 0; j < players.length; j++) {
                    if (players[j] == winner.get(i)) {
                        players[j].setBalance(players[j].getBalance() + (pot / winner.size()));
                        System.out.println(players[j].getName() + " has won $" + (pot / winner.size()));
                    }
                }
            }
        }
    }
}
