package project;

public class ExceptionPvP extends Exception {

	public ExceptionPvP() {
		super();
		ErrorWindow.start("<html><body>Du kannst nicht gegen dich selbst spielen, such einen anderen Player 2 bitte aus.</body></html>");
	}

}