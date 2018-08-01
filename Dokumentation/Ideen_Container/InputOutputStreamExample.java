import java.io.*;

public class InputOutputStreamExample {
	
	public static void writeFile(String fileName, String[] message, String directoryName) {
		// The name of the file to open.
         String resourcePath1 = System.getProperty("user.dir")+"\\src\\"+fileName+".player";
         String resourcePath2 = directoryName+"/"+fileName+".player";
         
         System.out.println("Vor mkdir()");
         //Check for existing directory
         File directory = new File(directoryName);
         if(!directory.exists()) {
        	 directory.mkdir();
        	 System.out.println("mkdir()");
         }

        
         File file = new File(resourcePath2);
         try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            

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
	
	
	public static void readFile(String fileName, String directoryName) {
		String resourceName1 = System.getProperty("user.dir")+"\\src\\"+fileName+".player";
		String resourceName2 = directoryName+"/"+fileName+".player";

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(resourceName2);

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
		writeFile("Testfile", test, "players");
		readFile("Testfile", "players");
		
	}
}
