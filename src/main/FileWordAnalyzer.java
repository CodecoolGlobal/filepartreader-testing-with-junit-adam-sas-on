import java.util.ArrayList;
import java.util.List;

public class FileWordAnalyzer {
	FilePartReader reader;

	public FileWordAnalyzer(){
		reader = new FilePartReader();
	}
	public FileWordAnalyzer(String filePath, int fromLine, int toLine){
		reader = new FilePartReader(filePath, fromLine, toLine);
	}

	public List<String> getWordsOrderedAlphabetically(){
		List<String> words = new ArrayList<>();

		return words;
	}

	public List<String> getWordsContainingSubstring(String subString){
		List<String> words = new ArrayList<>();

		return words;
	}

	public List<String> getStringsWhichPalindromes(){
		List<String> words = new ArrayList<>();

		return words;
	}
}
