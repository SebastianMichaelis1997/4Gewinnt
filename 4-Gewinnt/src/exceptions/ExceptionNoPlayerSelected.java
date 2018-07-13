package exceptions;

public class ExceptionNoPlayerSelected extends Exception {
	public ExceptionNoPlayerSelected() {
		super("Bitte erstelle oder wähle erst einen Spieler.");
	} 
}
