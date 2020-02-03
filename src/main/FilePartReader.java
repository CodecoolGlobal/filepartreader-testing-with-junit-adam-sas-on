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

	public String read(){
		return "";
	}

	public String readLines(){
		return "";
	}
}
