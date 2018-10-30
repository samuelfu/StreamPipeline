import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * FileOutput's sole goal is to write a file with the name and the content that
 * the user specified
 * 
 * @author Samuel Fu ssf2130 COMS1007
 *
 */
public class FileOutput {
	/**
	 * The method saves the specified lines by parsing the ArrayList streamList and
	 * saving it with the name fileName. In this case, the file is saved to output.txt
	 * 
	 * @param fileName
	 * @param streamList
	 *            is the ArrayList of streams that will be written into the file
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void saveFileToDirectory(String fileName, ArrayList<String[]> streamList)
			throws IOException, FileNotFoundException {
		String stream = "";
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"))) {
			for (String[] line : streamList)
				for(int i = 0; i<line.length; i++) {
					if(i < line.length - 1)
						stream = stream+line[i] + "\t";
					else
						stream = stream+ line[i] + "\n";
					
				}
			writer.write(stream);
		}
	}
}