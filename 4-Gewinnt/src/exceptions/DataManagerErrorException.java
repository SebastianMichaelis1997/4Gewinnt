package exceptions;

public class DataManagerErrorException extends Exception {

	public DataManagerErrorException(String additionalMsg) {
		super("<html><body>Error in the Data Manager. Please try again!<br>"+additionalMsg+"</body></html>");
	}
}