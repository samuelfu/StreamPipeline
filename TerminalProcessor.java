import java.util.ArrayList;

/**
 * Processes the option from compute() and prints out appropriate option and its
 * data.
 * 
 * @author Samuel Fu ssf2130 COMS1007
 *
 */
public class TerminalProcessor {
	/**
	 * Takes in option, an ArrayList of String arrays containing all streams, an
	 * array of fields, and a String computeKey. Uses switch case to determine
	 * output because it is cleaner than if else statements.
	 * 
	 * @param option
	 * @param streamList
	 * @param fieldList
	 * @param computeKey
	 */
	public void printTerminal(Terminal option, ArrayList<String[]> streamList, String[] fieldList, String computeKey) {

		IndexCalculator calculator = new IndexCalculator();
		int index = calculator.indexOf(fieldList, computeKey);

		switch (option) {
		/**
		 * Calculates the max value (alphabetically first) and prints it
		 */
		case MAX:
			String max = (streamList.get(0))[index];
			for (String[] stream : streamList)
				if (stream[index].compareTo(max) < 0)
					max = stream[index];
			System.out.println("Maximum " + computeKey + " is " + max + ".");
			break;
		/**
		 * Calculates the min value (alphabetically last) and prints it
		 */
		case MIN:
			String min = streamList.get(0)[index];
			for (String[] stream : streamList)
				if (stream[index].compareTo(min) > 0)
					min = stream[index];
			System.out.println("Minimum " + computeKey + " is " + min + ".");
			break;
		/**
		 * Returns if all the elements of the key are the same
		 */
		case ALLSAME:
			Boolean allSame = true;
			String firstString = "";
			for (String[] stream : streamList)
				if (firstString.equals(""))
					firstString = stream[index];
				else if (!stream[index].equals(firstString))
					allSame = false;
			System.out.print("All same: ");
			System.out.println(allSame);
			break;
		/**
		 * Prints how many lines there are after the filter
		 */
		case COUNT:
			System.out.println("Count: " + Integer.toString(streamList.size()));
			break;
		/**
		 * Prints if the elements of the key are mostly same, which means no more than 2
		 * different elements that are different from each other.
		 */
		case MOSTLYSAME:
			Boolean mostlySame = true;
			int variance = 0;
			String nextString = "";
			for (String[] stream : streamList)
				if (nextString.equals(""))
					nextString = stream[index];
				else if (!stream[index].equals(nextString)) {
					allSame = false;
					variance++;
				}
			if (variance > 2)
				mostlySame = false;
			System.out.print("Mostly Same: ");
			System.out.println(mostlySame);
			break;
		/**
		 * Does nothing
		 */
		case NONE:
			break;
		}
	}
}
