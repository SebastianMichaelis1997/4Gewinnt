package exceptions;

public class ExceptionNoPlayerSelected extends Exception {
	public ExceptionNoPlayerSelected() {
		super("Bitte erstelle erst einen Spieler.");
	} 
}
