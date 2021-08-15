/**
 * @author AJWuu
 */

package blackjack;

import java.util.LinkedList;
import java.util.List;

public class Dealer {
	static List<Card> dealerHold;
	
	public Dealer() {
		dealerHold = new LinkedList<Card>();
	}
	
	public List<Card> getHold() {
		return dealerHold;
	}
	
	public int sumInit() {
		return (dealerHold.get(0).getValue() + dealerHold.get(1).getValue());
	}
	
	public void dealerDraw(List<Card> deckSet) {
		dealerHold.add(Deck.drawACard(deckSet));
	}
}
