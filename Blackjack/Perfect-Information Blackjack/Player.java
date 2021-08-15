/**
 * @author AJWuu
 */

package blackjack;

import java.util.LinkedList;
import java.util.List;

public class Player {
	String name;
	static List<Card> playerHold;

	public Player(String name) {
		this.name = name;
		playerHold = new LinkedList<Card>();
	}
	
	public List<Card> getHold() {
		return playerHold;
	}
	
	public int sumInit() {
		return (playerHold.get(0).getValue() + playerHold.get(1).getValue());
	}
	
	public void playerDraw(List<Card> deckSet) {
		playerHold.add(Deck.drawACard(deckSet));
	}
}
