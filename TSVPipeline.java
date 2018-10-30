import java.io.IOException;
import java.util.ArrayList;

/**
 * TSVPipeline is the class that runs the editor methods and the main process.
 * 
 * @author Samuel Fu ssf2130 COMS1007
 *
 */
public class TSVPipeline {
	private TSVFilter myTSVFilter;

	public TSVPipeline(TSVFilter myTSVFilter) {
		this.myTSVFilter = myTSVFilter;
	}

	/**
	 * The method that creates a TSVEditor and passes in TSVFilter values as
	 * parameters. Then it initiates the editor's line processor and terminal
	 * processor.
	 * 
	 * @throws IOException
	 */
	public void doit() throws IOException {
		TSVEditor editor = new TSVEditor(myTSVFilter.getFileName(), myTSVFilter.getKey(), myTSVFilter.getValue(),
				myTSVFilter.getComputeKey(), myTSVFilter.getOption());

		editor.processLineByLine();
		editor.processTerminal();
	}
}
