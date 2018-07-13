package exceptions;

public class ImageAlreadyExistsException extends Exception {

	public ImageAlreadyExistsException(String additionalMsg) {
		super("<html><body>Error while saving File: An image with this name already exists. Please rename it!"
				+ additionalMsg + "</body></html>");
	}
}
