/**
 * @author modified by AJWuu
 */

package blackjack;

public class InvalidCardSuitException extends Exception {
	private static final long serialVersionUID = 1L;
	private char suitIdentifier = '?';

	public InvalidCardSuitException (char invalidSuit) {
		suitIdentifier = invalidSuit;
		System.out.println("Invalid suit" + " " + invalidSuit);
	}

	@SuppressWarnings("unused")
	private InvalidCardSuitException() {
		System.out.println("Invalid suit");
	}
	
	public String toString() {
		return ("Attempted to create card with invalid suit argument" + " " + this.suitIdentifier);
	}
	
	public char getSuitDesignator() {
		return suitIdentifier;
	}
}
