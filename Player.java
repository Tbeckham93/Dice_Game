

/**
 * Player class
 * @author Timothy Beckham
 */
public class Player { //start of Player class and sets all the needed fields.
    private String name;
    private int balance;
    private int betAmount;
    private int guess;
    private int guess1;
    private int guess2;

    /**
     * start balance for ever player.
     */
    public final int INITIAL_BALANCE = 500; //sets the balance of ever player

    /**
     * constructor for Player, sets the name and balance for every player.
     * @param name
     */
    public Player(String name) { // Constructor for the player - sets name and starting balance.

        this.name = name;
        balance = INITIAL_BALANCE;
    }

    /**
     * getter for the name
     * @return name of of the player.
     */
    public String getName() { // gets the name of player
        return name;
    }

    /**
     * getter for the balance
     * @return balance of the player
     */
    public int getBalance() { // gets the balance of the player.
        return balance;
    }

    /**
     * getter for the bet amount of the player
     * @return the bet amount of the player
     */
    public int getBetAmount() { // gets the bet amount of the player
        return betAmount;
    }

    /**
     * getter for the guess of the player
     * @return guess of the player.
     */
    public int getGuess() { // gets the guess of the player (normal mode)
        return guess;
    }

    /**
     * setter of the balance of the player
     * @param balance
     */
    public void setBalance(int balance) { // sets the balance of the player
        this.balance = balance;
    }

    /**
     * setter for the bet amount of the player.
     * @param betAmount
     */
    public void setBetAmount(int betAmount) { //sets the bet amount of the player.
        this.betAmount = betAmount;
    }

    /**
     * setter of the guess of the player
     * @param guess
     */
    public void setGuess(int guess) { // sets the guess of the player (normal mode)
        this.guess = guess;
    }

    /**
     * getter of guess1 of the player (hard mode)
     * @return guess1 of player
     */
    public int getGuess1() { // gets the guess1 of the player (hard mode)
        return guess1;
    }

    /**
     * setter for guess1 of player (hard mode)
     * @param guess1
     */
    public void setGuess1(int guess1) {// sets the guess1 of the player (hard mode)
        this.guess1 = guess1;
    }

    /**
     * getter for guess2 of player (hard mode)
     * @return guess2 of the player
     */
    public int getGuess2() { // gets the guess2 of the player (hard mode)
        return guess2;
    }

    /**
     * setter for guess2 of the player (hard mode)
     * @param guess2
     */
    public void setGuess2(int guess2) { // sets the guess2 for the player (hard mode)
        this.guess2 = guess2;
    }
    
}
