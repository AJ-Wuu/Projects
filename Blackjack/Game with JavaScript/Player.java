/**
 * @author modified by AJWuu
 */

package blackjack;

import java.io.Serializable;

public class Player implements Serializable {
	private static final long serialVersionUID = 7526472295622776147L; //unique ID
	private int bank;
	private int bet;
	private String name;
	private Hand hand;
	
	//Create a player
	public Player() {
		bank = 100;
		hand = new Hand();
		
	}
	
	//Get a player's bank amount
	public int getBank() {
		return bank;
	}
	
	//Remove a player's bet from their bank if they bust
	//Set bet to zero afterwards
	public void bust() {
		bank -= bet;
		bet = 0;
	}
	
	//Add a player's bet from their bank if they win
	//Set bet to zero afterwards
	public void win() {
		bank += bet;
		bet = 0;
	}

	//Remove a player's bet from their bank if they lose
	//Sets bet to zero afterwards
	public void loss() {
		bank -= bet;
		bet = 0;
	}
	
	//Set the player bank to -1 -> -1 is unreachable, so they are removed from the game
	public void removeFromGame() {
		bank = -1;
	}
	
	//Reset the bank to 0
	//Currently used to reset a removed player's bank from -1 to 0
	public void resetBank() {
		bank = 0;
	}
	
	//Calculate the bet for a player who has a Blackjack
	public void blackjack() {
		bank += bet * 1.5;
		bet = 0;
	}
	
	//Set a player's bet to 0 if the "push"
	//Notice, no bet is added or removed
	public void push() {
		bet = 0;
	}
	
	//Set a player's bet
	public void setBet(int newBet) {
		bet = newBet;
	}
	
	//Set a player's name
	public void setName(String name1) {
		name = name1;
	}
	
	//Get the player's name
	public String getName() {
		return name;
	}
	
	//Get the player's total at hand
	public int getTotal() {
		return hand.calculateTotal();
	}
	
	//Get a player's bet
	public int getBet() {
		return this.bet;
	}
		
	//Add a card to the player's hand
	public void addCard(Card card) {
		hand.addCard(card);
	}
	
	//Get the player's cards to print as a string
	public String getHandString() {
		return ("Cards:" + hand.toString());
	}
		
	//Clear the player's hand
	public void clearHand() {
		hand.clearHand();
	}
		
}
