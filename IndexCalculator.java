/***
 * IndexCalculator takes in an array of Strings and calculates where the key
 * occurs in the array.
 * 
 * @author Samuel Fu ssf2130 COMS1007
 *
 */
public class IndexCalculator {
	/**
	 * Calculates the index where key occurs at fields and returns the index. If the
	 * key does not occur, returns -1
	 * 
	 * @param fields
	 * @param key
	 * @return index or -1
	 */
	public int indexOf(String[] fields, String key) {
		for (int i = 0; i < fields.length; i++)
			if (fields[i].equals(key))
				return i;
		return -1;
	}
}
