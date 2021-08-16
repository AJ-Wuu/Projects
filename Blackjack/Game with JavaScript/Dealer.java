/**
 * @author modified by AJWuu
 */

package blackjack;

import java.io.Serializable;

public class Dealer implements Serializable {
	private static final long serialVersionUID = 7526472295622776147L; //unique ID
	private Hand hand = new Hand();

	public boolean isBlackjack() {
		if (hand.calculateTotal() == 21) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Automate dealer's play
	public void dealerPlay(Deck deck) {
		System.out.println();
		while (hand.calculateTotal() <= 16) {
			System.out.println("Dealer has " + hand.calculateTotal()+ " and hits");
			hand.addCard(deck.nextCard());
			System.out.println("Dealer " + this.getHandString(true, false));
		}
		if (hand.calculateTotal() > 21) {
			System.out.println("Dealer busts.");
		}
		else {
			System.out.println("Dealer stands.");
		}
	}
	
	//Add a card to dealer's hand
	public void addCard(Card card) {
		hand.addCard(card);

	}
	
	//Show dealer's hand
	public String getHandString(boolean isDealer, boolean hideHoleCard) {
		return ("Cards:" + hand.toString(isDealer, hideHoleCard));
	}
	
	//Calculate dealer's total at hand
	public int calculateTotal() {
		return hand.calculateTotal();
	}
	
	//Clear dealer's hand
	public void clearHand() {
		hand.clearHand();
	}
	
	//Peek dealer's face-down card
	public boolean peek() {
		return hand.dealerPeek();
	}
}
