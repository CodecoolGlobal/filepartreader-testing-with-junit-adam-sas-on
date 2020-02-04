import java.io.*;

public class FilePartReader {
	private InputStream is = null;
	private String filePath;
	private int fromLine, toLine;
	private boolean fileExists = false;

	public FilePartReader(){
		is = null;
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
		is = this.getClass().getResourceAsStream(filePath);
		fileExists = is != null;
	}

	/**
	 * Opens the file on filePath, and gives back it's content as a String.
	 *
	 * @return
	 */
	public String read(){
		if( !fileExists )
			return "";

		String fileContent = "";
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(is) );

		String line;
		StringBuilder sb = new StringBuilder();
		try {
			while( (line = br.readLine()) != null){
				sb.append(line + "\n");
			}
			br.close();

			fileContent = sb.toString();
		} catch (IOException ignored){}

		return fileContent;
	}

	/**
	 * It gives back every line from it's content between fromLine and toLine (both of them are included),
	 * and returns these lines as a String.
	 * @return
	 */
	public String readLines(){
		if( !fileExists )
			return "";

		String fileContent = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(is) );
		int lineCount = 1;

		StringBuilder sb = new StringBuilder();

		try {
			if(fromLine > 1) {
				while(lineCount < fromLine && br.readLine() != null)
					lineCount++;
			}

			String line;
			while(lineCount <= toLine && (line = br.readLine()) != null){
				sb.append(line + "\n");
				lineCount++;
			}
			br.close();

			fileContent = sb.toString();
		} catch (IOException ignored){}

		return fileContent;
	}

	void setReadRange(int fromLine, int toLine){
		if(toLine < fromLine)
			return;

		this.fromLine = fromLine;
		this.toLine = toLine;
	}
	void setFilePath(String newFile){
		filePath = newFile;
		is = this.getClass().getResourceAsStream(filePath);
		fileExists = is != null;

		if( !fileExists )
			filePath = "";
	}
}
