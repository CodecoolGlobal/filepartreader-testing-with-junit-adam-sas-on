public class FilePartReader {
	private String filePath;
	int fromLine, toLine;

	public FilePartReader(){
		filePath = "";
		fromLine = toLine = -1;
	}
	public FilePartReader(String filePath, int fromLine, int toLine){
		setup(filePath, fromLine, toLine);
	}

	public void setup(String filePath, int fromLine, int toLine){
		if(fromLine < 1 || fromLine > toLine)
			throw new IllegalArgumentException("FilePartReader.setup: wrong arguments fromLine and/or toLine!");

		this.filePath = filePath;
		this.fromLine = fromLine;
		this.toLine = toLine;
	}

	/**
	 * Opens the file on filePath, and gives back it's content as a String.
	 *
	 * @return
	 */
	public String read(){
		return "";
	}

	/**
	 * It gives back every line from it's content between fromLine and toLine (both of them are included),
	 * and returns these lines as a String.
	 * @return
	 */
	public String readLines(){
		return "";
	}
}
