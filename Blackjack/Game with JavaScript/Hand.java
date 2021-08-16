/**
 * @author modified by AJWuu
 */

package blackjack;

import java.io.Serializable;

public class Hand implements Serializable {
	private static final long serialVersionUID = 7526472295622776147L; //unique ID
	private Card[] theHand = new Card[12];
	private int numberOfCards = 0;

	//Calculate the total of a hand & Decide whether Ace is 1 or 11
	public int calculateTotal() {
		int total =0;
		boolean aceFlag = false;
		for (int i=0; i<numberOfCards; i++) {
			int value = theHand[i].getValue();
			if (value > 10) {
				value = 10;
			}
			else if ( value == 1) {
				aceFlag = true;
			}
			total += value;
		}
		if (aceFlag && total + 10 <= 21) {
			total += 10;
		}
		return total;
	}

	public String toString() {
		return this.toString(false, false);
	}

	public String toString(boolean isDealer, boolean hideHoleCard) {
		String str = "";
		int total =0;
		boolean aceFlag = false;
		String aceString = "";
		for (int i=0; i<numberOfCards; i++) {
			if (isDealer && hideHoleCard && i == 0) {
				str = " Showing";
			}
			else {
				int value = theHand[i].getValue();
				String valueName;
				if (value > 10) {
					valueName = theHand[i].getValueName().substring(0, 1);
				}
				else if ( value == 1 ){
					valueName = "A";
				}
				else {
					valueName = Integer.toString(value);
				}

				str += " " +valueName + theHand[i].getSuitDesignator();
				if (value > 10) {
					value = 10;
				}
				else if ( value == 1) {
					aceFlag = true;
				}
				total += value;
			}
		}
		if (aceFlag && total + 10 <= 21) {
			aceString = " or "+ (total + 10);
		}
		if (hideHoleCard) {
			return str;
		}
		else {
			return str+ " totals "+ total + aceString;
		}

	}

	public void addCard(Card card) {
		theHand[numberOfCards++] = card;
	}

	public void clearHand() {
		numberOfCards = 0;
	}

	public boolean dealerPeek() {
		int value = theHand[1].getValue();
		return (value == 1 || value >= 10);
	}
}
