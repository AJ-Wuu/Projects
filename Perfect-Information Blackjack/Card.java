/**
 * @author AJWuu
 */

package blackjack;

import java.util.LinkedList;
import java.util.List;

public class Card {
	//This initialization is unnecessarily complex; it's just for practicing enums
	private final int value;
	private final Rank rank; //Queen, Ace, etc.
	private final Suit suit; //Diamond, Club, Heart and Spade. Not necessary for this program, but it's fun to add them LOL

	private Card(final int value, final Rank rank, final Suit suit) {
		this.value = value;
		this.rank = rank;
		this.suit = suit;
	}

	public int getValue() {
		return this.value;
	}

	public Rank getRank() {
		return this.rank;
	}

	public Suit getSuit() {
		return this.suit;
	}

	public static List<Card> setCards() {
		List<Card> cards = new LinkedList<Card>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				switch(rank) {
				case Two:
					cards.add(new Card(2, rank, suit));
					break;
				case Three:
					cards.add(new Card(3, rank, suit));
					break;
				case Four:
					cards.add(new Card(4, rank, suit));
					break;
				case Five:
					cards.add(new Card(5, rank, suit));
					break;
				case Six:
					cards.add(new Card(6, rank, suit));
					break;
				case Seven:
					cards.add(new Card(7, rank, suit));
					break;
				case Eight:
					cards.add(new Card(8, rank, suit));
					break;
				case Nine:
					cards.add(new Card(9, rank, suit));
					break;
				case Ten: case Jack: case Queen: case King:
					cards.add(new Card(10, rank, suit));
					break;
				case Ace:
					cards.add(new Card(11, rank, suit));
					break;
				}
			}
		}
		return cards;
	}
}
