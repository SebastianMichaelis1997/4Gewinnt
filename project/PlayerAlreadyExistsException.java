package project;

public class PlayerAlreadyExistsException extends Exception {

	public PlayerAlreadyExistsException(String name, String additionalMsg) {
		super("Player with name "+name+" already exists! Names are not case sensitive! Please choose another name! "+additionalMsg);
	}
}
