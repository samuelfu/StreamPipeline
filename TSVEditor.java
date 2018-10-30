import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TSVEditor is the main code for taking in streams, taking out the fields,
 * filtering with the key, processing the fields and the lines, then outputting
 * the lines to a file and to the console.
 * 
 * @author Samuel Fu ssf2130 COMS1007
 *
 */
public class TSVEditor {
	private Scanner fileInput;
	private FileOutput fileOutput = new FileOutput();
	private String stream;
	private String[] valueList;
	private String[] fieldList;
	private String isLong = "";
	private Boolean hasFieldProcessed = false;
	private Boolean hasSecondLine = false; // *******************************
	private String fileName;
	private String computeKey;
	private String key;
	private String value;
	private Terminal option;
	private ArrayList<String[]> streamList = new ArrayList<String[]>();

	/**
	 * Sets fileName, key, value, computeKey and option. Tries to open file and
	 * exits if it fails.
	 * 
	 * @param fileName
	 * @param key
	 * @param value
	 * @param computeKey
	 * @param option
	 */
	public TSVEditor(String fileName, String key, String value, String computeKey, Terminal option) {
		this.fileName = fileName;
		this.key = key;
		this.value = value;
		this.option = option;
		this.computeKey = computeKey;
		try {
			this.fileInput = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("File not found. System exit.");
			System.exit(0);
		}
	}

	/**
	 * Creates a while loop that iterates through the file. First, it processes the
	 * first line with the fields and prints them. If the first line is already
	 * processed, then it processes and prints the line.
	 * 
	 * @throws FileNotFoundException
	 */
	public void processLineByLine() throws FileNotFoundException {
		while (fileInput.hasNextLine()) {
			this.stream = fileInput.nextLine();
			if (!hasFieldProcessed) {
				processFields();
				printFields();
				hasFieldProcessed = true;
			} else {
				processLine();
				printFilteredLine(key);
				hasSecondLine = true;
			}
		}

		if (!hasSecondLine) {
			System.out.println("Second line not found. System exit");
			System.exit(0);
		}
		try {
			streamList.add(0, fieldList);
			fileOutput.saveFileToDirectory(fileName, streamList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a TerminalProcessor and sends arguments to the processor
	 */
	public void processTerminal() {
		TerminalProcessor processor = new TerminalProcessor();
		processor.printTerminal(option, streamList, fieldList, computeKey);

	}

	/**
	 * Splits the stream into a list of Strings
	 */
	public void processFields() {
		/**
		 * Separates the tabs in the first line, which is the header line.
		 */
		fieldList = stream.split("\t");
	}

	/**
	 * Tries to cast the values into longs and records whether it is successful or
	 * not through isLong. If it does not fit, the stream will be cleared. Splits
	 * stream into a list of Strings and stores it in valueList.
	 */
	public void processLine() {
		valueList = stream.split("\t");
		/**
		 * If the line does not have the proper amount of fields, then it is set to ""
		 * and filtered out.
		 */
		if (valueList.length != fieldList.length)
			stream = "";

		if (isLong == "")
			recordIsLong();
		else {
			checkIsLong();
		}
	}

	/**
	 * Each string in isLong will have zeros or ones, where one represents a long
	 * and zero a string.
	 */
	public void recordIsLong() {
		isLong = "";
		// Record 0 and 1s into isLong
		for (int i = 0; i < valueList.length; i++) {
			try {
				Long.parseLong(valueList[i]);
				isLong += "1";
			} catch (NumberFormatException e) {
				isLong += "0";
			}
		}
	}

	/**
	 * Tries to convert the current line into longs. If the values that can be
	 * converted are different then the line is automatically filtered out by
	 * setting the stream to "".
	 */
	public void checkIsLong() {
		String currentLineLongValue = "";
		for (int i = 0; i < valueList.length; i++) {
			try {
				Long.parseLong(valueList[i]);
				currentLineLongValue += "1";
			} catch (NumberFormatException e) {
				currentLineLongValue += "0";
			}
		}
		if (!currentLineLongValue.equals(isLong)) {
			stream = "";
			System.out.print("Current line format invalid");
		}
	}

	/**
	 * Prints the fields in a tabbed format
	 */
	public void printFields() {
		System.out.println("The system found the following fields: ");
		for (int i = 0; i < fieldList.length; i++)
			if (i < fieldList.length - 1)
				System.out.print(fieldList[i] + "\t");
			else
				System.out.println(fieldList[i]);
	}

	/**
	 * Prints the line filtered with the key through finding what index the key
	 * resides in. If the key cannot be found the stream is cleared.
	 * 
	 * @param key
	 */
	public void printFilteredLine(String key) {
		if (key.equals("")) {
			System.out.println(stream);
			streamList.add(valueList);
		} else {
			IndexCalculator calculator = new IndexCalculator();
			int keyIndex = calculator.indexOf(fieldList, key);

			if (!(streamList.size() == 0))
				if (valueList[keyIndex].compareTo(streamList.get(streamList.size() - 1)[keyIndex]) < 0)
					stream = "";

			if (!stream.equals("")) {
				System.out.println(stream);
				streamList.add(valueList);
			}
		}
	}

}
