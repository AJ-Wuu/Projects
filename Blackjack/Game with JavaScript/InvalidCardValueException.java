/**
 * @author modified by AJWuu
 */

package blackjack;

public class InvalidCardValueException extends Exception {
	private static final long serialVersionUID = 1L;
	private int valueIdentifier = 0;

	public InvalidCardValueException(int invalidValue) {
		valueIdentifier = invalidValue;
		System.out.println("Invalid value " + invalidValue);
	}

	@SuppressWarnings("unused")
	private InvalidCardValueException() {
		System.out.println("Invalid value");
	}

	public String toString() {
		return ("Attempted to create card with invalid suit argument" + " " + this.valueIdentifier);
	}

	public int getValue() {
		return valueIdentifier;
	}
}
