/**
 * @author AJWuu
 */

package blackjack;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class Deck {
	List<Card> newSet;
	
	public Deck() {
		newSet = Card.setCards();
		shuffle(newSet);
	}

	private static void shuffle(List<Card> newSet ) {
		Collections.shuffle(newSet);
	}

	public static Card drawACard(List<Card> newSet ) {
		try {
			return newSet.remove(0);
		} catch (NoSuchElementException e) {
			newSet = Card.setCards();
			shuffle(newSet);
			return drawACard(newSet);
		}
	}
}
