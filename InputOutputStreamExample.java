import java.io.*;

public class InputOutputStreamExample {
	
	public static void writeFile(String fileName, String[] message) {
		// The name of the file to open.
         String resourcePath = System.getProperty("user.dir")+"\\src\\"+fileName;

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(resourcePath);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            for( int i = 0; i < message.length; i++) {            	
            	bufferedWriter.write(message[i]);
            	bufferedWriter.newLine();
            }
            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();


        }
	}
	
	
	public static void readFile(String fileName) {
		String resourceName = System.getProperty("user.dir")+"\\src\\"+fileName;

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(resourceName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

		
	}

	public static void main(String[] args) {
		String[] test = new String[] {"Name","Wins","Losses"};
		writeFile("Testfile.txt", test);
		readFile("Testfile.txt");
		
	}
}
