package exceptions;

import project.ErrorWindow;

public class PlayerAlreadyExistsException extends Exception {

	public PlayerAlreadyExistsException(String name, String additionalMsg) {
		super();
		ErrorWindow.start("<html><body>Player with name "+name+" already exists!<br> Names are not case sensitive!<br> Please choose another name!<br>"+additionalMsg+"</body></html>");
	}
}
