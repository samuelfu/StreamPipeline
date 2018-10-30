/**
 * TSVFilter uses the builder pattern to allow for grows() and compute() options
 * to set file, key and option.
 * 
 * @author Samuel Fu ssf2130 COMS1007
 *
 */
public class TSVFilter {
	private final String fileName;
	private final String key;
	private final String value;
	private final Terminal option;
	private final String computeKey;
	/**
	 * Constructor for WhichFile which sets all default values
	 * @author Samuel Fu ssf2130 COMS1007
	 *
	 */
	public static class WhichFile {
		private String fileName = "";
		private String key = "";
		private String value = "";
		private Terminal option = Terminal.NONE;
		private String computeKey = "";
		/**
		 * Sets file name. Constructor
		 * @param fileName
		 */
		public WhichFile(String fileName) {
			this.fileName = fileName;
		}
		/**
		 * Takes in and sets key.
		 * @param key
		 * @return
		 */
		public WhichFile grows(String key) {
			this.key = key;
			return this;
		}
		/**
		 * Takes in and sets key and option.
		 * @param key
		 * @param option
		 * @return
		 */
		public WhichFile compute(String key, Terminal option) {
			this.computeKey = key;
			this.option = option;
			return this;
		}
		/**
		 * Completes the builder pattern and returns a new TSVFilter(WhichFile)
		 * @return
		 */
		public TSVFilter done() {
			return new TSVFilter(this);
		}
	}
	
	private TSVFilter(WhichFile file) {
		this.fileName = file.fileName;
		this.key = file.key;
		this.value = file.value;
		this.option = file.option;
		this.computeKey = file.computeKey;
	}

	public String toString() {
		return "TSVFilter Samuel Fu ssf2130\nFile Name = " + fileName + ", Key = " + key + ", Value = " + value;
	}

	public String getFileName() {
		return fileName;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public Terminal getOption() {
		return option;
	}

	public String getComputeKey() {
		return computeKey;
	}

}
