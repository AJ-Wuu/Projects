/**
 * @author modified by AJWuu
 */

package blackjack;

public class InvalidDeckPositionException extends Exception {
	private static final long serialVersionUID = 1L;
	private int positionIdentifier = 0;

	public InvalidDeckPositionException(int inValidPosition) {
		positionIdentifier = inValidPosition;
		System.out.println("Invalid Position" + inValidPosition);
	}

	@SuppressWarnings("unused")
	private InvalidDeckPositionException() {
		System.out.println("Invalid Position");
	}

	public String toString() {
		return ("Attempted to get a card from a position not in Deck" + " " + this.positionIdentifier);
	}

	public int getPositionValue() {
		return positionIdentifier;
	}
}
