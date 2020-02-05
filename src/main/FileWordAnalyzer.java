import java.util.ArrayList;
import java.util.List;

public class FileWordAnalyzer {
	private FilePartReader reader;
	/* sets if e.g. 'A' is the same what 'a' or not. Default not: */
	private boolean caseSensitive = true;
	/* sets if analyzed words can be repeated or not. Default not: */
	private boolean readUnique = true;

	public FileWordAnalyzer(){
		reader = new FilePartReader();
	}
	public FileWordAnalyzer(String filePath, int fromLine, int toLine){
		reader = new FilePartReader(filePath, fromLine, toLine);
	}

	private boolean caseInsensitiveContains(List<String> list, String str){
		return list.stream().anyMatch(str::equalsIgnoreCase);
	}

	private void insertCaseSensitiveAndUnique(List<String> list, String[] words){
		for(String word : words){
			if(!list.contains(word))
				list.add(word);
		}
	}

	private void insertCaseInsensitiveAndUnique(List<String> list, String[] words){
		for(String word : words){
			if(!caseInsensitiveContains(list, word) )
				list.add(word);
		}
	}
	/**
	 * Returns the words ordered by alphabetically as an ArrayList.
	 * @return
	 */
	public List<String> getWordsOrderedAlphabetically(){
		List<String> words = new ArrayList<>();
		String fileContent = reader.readLines();

		if(fileContent.length() < 1)
			return words;

		return words;
	}

	/**
	 * Returns the words which contains the subString.
	 *
	 * @param subString: string to check in words.
	 * @return
	 */
	public List<String> getWordsContainingSubstring(String subString){
		List<String> words = new ArrayList<>();

		return words;
	}

	public List<String> getStringsWhichPalindromes(){
		List<String> words = new ArrayList<>();

		return words;
	}

	public void setReadRange(int fromLine, int toLine){
		reader.setReadRange(fromLine, toLine);
	}
	public void setFilePath(String newFile){
		reader.setFilePath(newFile);
	}
	public void resetReader(){
		reader.resetFile();
	}
}

