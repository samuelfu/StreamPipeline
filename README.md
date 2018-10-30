# StreamPipeline

Reads a *.tsv file one record at a time, checks the record for proper form, and outputs the record to a *.tsv file if it is correctly formatted. A file has proper form if the output file is the same as the input file. 

Uses Builder Pattern that adds on arguments. Example:

public static void main(String[] args) { TSVFilter myTSVFilter = new TSVFilter
            .WhichFile("mydata.tsv")
            .select("Name", "Samuel")
            .done();
    System.out.println(myTSVFilter);
    new TSVPipeline(myTSVFilter).doit();
}

Using terminal stream operations:
public static void main(String[] args) { TSVFilter myTSVFilter = new TSVFilter
            .WhichFile("mydata.tsv")
            .select("Name", "Frank")
            .compute("Age", Terminal.MAX)
            .done();
    System.out.println(myTSVFilter);
    new TSVPipeline(myTSVFilter).doit();
}
