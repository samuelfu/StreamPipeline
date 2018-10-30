import java.io.IOException;
import java.util.ArrayList;

/**
 * Executes the main code. Builds a TSVFilter that has WhichFile(String file)
 * and options to select grows(String key) or compute(String key, Terminal
 * option) and an end done(). A TSVPipeline is build using the TSVFilter to
 * execute the main program.
 * 
 * Test file: test.tsv
 *  Name	Age	Cell 	Phone		Zip Code
	Frank	20		212117777	10027
	Molly	22		2121115432	10029
	Tony		18		2010001123	99876
	Ann		19		9171118421	43210
 * @author Samuel Fu ssf2130 COMS1007
 *
 */
public class Runner {
	/**
	 * Builds a TSVFilter that takes in file name, filters and options, and
	 * sends it to a TSVPipeline that executes the main code through the
	 * parameters sent.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		TSVFilter myTSVFilter = new TSVFilter
				.WhichFile("test.tsv")
				.grows("Name")
				.compute("Name", Terminal.MAX)
				.done();

		System.out.println(myTSVFilter);
		new TSVPipeline(myTSVFilter).doit();
	}
}
