/**
 * @author AJWuu
 */

package blackjack;

import java.util.LinkedList;
import java.util.List;

public class PlayBlackjack {

	private static int numTotal = 52;
	
	public static int blackjack(int numPlayed, List<Card> deckSet, List<Card> playerHold, int sumPlayer, List<Card> dealerHold, int sumDealer, LinkedList<Integer> currWinning, int maxWinning) {
		//numPlayed is initially 4
		if (numTotal - numPlayed < 0) { //no more cards
			return 0;
		}
		
		int numPlayerTaken = 2, numDealerTaken = 2;
		for (; numPlayerTaken<numTotal-numPlayed-1; numPlayerTaken++) {
			numPlayed++;
			Card newDraw = Deck.drawACard(deckSet);
			playerHold.add(newDraw);
			sumPlayer += newDraw.getValue();
			if (sumPlayer > 21) { //bust
				currWinning.add(-1);
				break;
			}
			else {
				blackjack(numPlayed, deckSet, playerHold, sumPlayer, dealerHold, sumDealer, currWinning, maxWinning);
			}
			
			for (; numDealerTaken<numTotal-numPlayed-1; numDealerTaken++) {
				numPlayed++;
				newDraw = Deck.drawACard(deckSet);
				dealerHold.add(newDraw);
				sumDealer += newDraw.getValue();
				if (sumDealer >= 17) {
					break;
				}
			}
			
			if (sumDealer > 21) { //bust
				sumDealer = 0;
				currWinning.add(1);
			}
			else {
				currWinning.add(Integer.compare(sumPlayer, sumDealer));
			}
		}
		
		while (!currWinning.isEmpty()) {
			maxWinning = Integer.max(maxWinning, maxWinning + currWinning.poll());
		}
		return maxWinning;
	}
	
}
