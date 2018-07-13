package exceptions;

public class IllegalNameException extends Exception {
	public IllegalNameException(String illegalName) {
		super("Name darf nicht \"" + illegalName + "\" sein");
	}
}
