/**
 * @author AJWuu
 */

package blackjack;

import java.util.LinkedList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Welcome to the Blackjack Game!");
		System.out.println("What's the player's name?");
		Scanner scan = new Scanner(System.in);
		Player player = new Player(scan.next());
		Dealer dealer = new Dealer();
		Deck deck = new Deck();
		scan.close();
		
		for (int i=0; i<2; i++) { //First two draws
			player.playerDraw(deck.newSet);
			dealer.dealerDraw(deck.newSet);
		}
		LinkedList<Integer> currWinning = new LinkedList<Integer>();
		
		System.out.println("The player " + player.name + "'s max winning in this round could be: ");
		System.out.print(PlayBlackjack.blackjack(4, deck.newSet, player.getHold(), player.sumInit(), dealer.getHold(), dealer.sumInit(), currWinning, 0));
	}

}
