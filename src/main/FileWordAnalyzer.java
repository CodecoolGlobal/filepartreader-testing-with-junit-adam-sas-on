import java.util.ArrayList;
import java.util.List;

public class FileWordAnalyzer {
	private FilePartReader reader;
	private final String splitRegex = "[^\\w\\-']+[^\\w]*";
	/* sets if e.g. 'A' is the same what 'a' or not. Default not: */
	private boolean caseSensitive = true;
	/* sets if analyzed words can be repeated or not. Default not: */
	private boolean readUnique = true;
	/* string to set once during removing palindromes from an array: */
	private String palindromeWord;

	public FileWordAnalyzer(){
		reader = new FilePartReader();
	}
	public FileWordAnalyzer(String filePath, int fromLine, int toLine){
		reader = new FilePartReader(filePath, fromLine, toLine);
	}

	private boolean caseInsensitiveContains(List<String> list, String str){
		return list.stream().anyMatch(str::equalsIgnoreCase);
	}

	/**
	 * Loop through words array and checks if word is in list; if not (meaning is unique)
	 * then inserts the word into list.
	 *
	 * @param list: list to fill with unique words;
	 * @param words: list from which we choose the words.
	 */
	private void insertCaseSensitiveAndUnique(List<String> list, String[] words){
		for(String word : words){
			if(word.length() > 0 && !list.contains(word))
				list.add(word);
		}
	}
	/**
	 * Same as above, but new method instead overloading to the name of method explaining itself.
	 */
	private void insertCaseSensitiveAndUniqueWithSubstring(List<String> list, String[] words, String subString){
		for(String word : words){
			if(word.length() > 0 && word.contains(subString) && !list.contains(word))
				list.add(word);
		}
	}

	/**
	 * Loop through words array and checks if word is in list; if not (meaning is unique)
	 * then inserts the word into list. It is also an ignore-case method.
	 *
	 * @param list: list to fill with unique words;
	 * @param words: list from which we choose the words.
	 */
	private void insertCaseInsensitiveAndUnique(List<String> list, String[] words){
		for(String word : words){
			if(word.length() > 0 && !caseInsensitiveContains(list, word) )
				list.add(word);
		}
	}
	/**
	 * Same as above, but new method instead overloading to the name of method explaining itself.
	 */
	private void insertCaseInsensitiveAndUniqueWithSubstring(List<String> list, String[] words, String subString){
		final String lowerSubString = subString.toLowerCase();
		for(String word : words){
			if(word.length() > 0 && word.toLowerCase().contains(lowerSubString) && !caseInsensitiveContains(list, word) )
				list.add(word);
		}
	}

	/**
	 * It removes values from words array according to palindrome string and case-sensitive setting.
	 * The way it removes is to overwrite the words to remove by the words from the next neighbour index
	 * to the end of the array defined by wordsLimit value.
	 *
	 * @param words: array of strings to analyze;
	 * @param palindrome: a word to analyze and remove from array according to case-sensitivity;
	 * @param wordsLimit: number of significant elements to check, it has to be <= words.length;
	 * @return: new number of significant elements in words array;
	 */
	private int removePalindrome(String[] words, String palindrome, final int wordsLimit){
		String revPalindrome = new StringBuilder(palindrome).reverse().toString(),
				palindromeLocal = palindrome;

		if(!caseSensitive) {
			palindromeLocal = palindrome.toLowerCase();
			revPalindrome = revPalindrome.toLowerCase();
		}

		palindromeWord = "";
		if(palindromeLocal.equals(revPalindrome) ){
			palindromeWord = palindrome;
		}

		String wordCase;// word to use for 'lowercase' when case-insensitive was set;
		boolean isSelf, isReverse, wasNotSet = true;
		int i = 0;

		for(int j = 0; j < wordsLimit; i++, j++) {
			wordCase = (caseSensitive)? words[j] : words[j].toLowerCase();
			isSelf = palindromeLocal.equals(wordCase);
			isReverse = revPalindrome.equals(wordCase);

			if(isSelf || isReverse){
				if(isReverse && wasNotSet && j > 0){
					wasNotSet = false;
					if(isSelf)
						palindromeWord = words[j];
				}
				i--;
			} else if(i != j)
				words[i] = words[j];// override word from another index;
		}

		return i;
	}

	/**
	 * Returns the words ordered by alphabetically as an ArrayList.
	 * @return: an alphabetically sorted list of words from file in reader which meet ignore-case and uniqueness requirements.
	 */
	public List<String> getWordsOrderedAlphabetically(){
		List<String> words = new ArrayList<>();
		String fileContent = reader.readLines();

		if(fileContent.length() < 1)
			return words;

		String[] wordsArray = fileContent.split(splitRegex);

		if(readUnique && caseSensitive){
			insertCaseSensitiveAndUnique(words, wordsArray);
		} else if(readUnique){
			insertCaseInsensitiveAndUnique(words, wordsArray);
		} else {
			int i, count = wordsArray.length;
			for(i = 0; i < count; i++){
				words.add(wordsArray[i]);
			}

		}

		words.sort(String::compareToIgnoreCase);
		return words;
	}

	/**
	 * Returns the words which contains the subString.
	 *
	 * @param subString: string to check in words.
	 * @return: a list of words from file in reader which have a subString and meet ignore-case and uniqueness requirements.
	 */
	public List<String> getWordsContainingSubstring(String subString){
		List<String> words = new ArrayList<>();
		String fileContent = reader.readLines();

		if(fileContent.length() < 1)
			return words;

		String[] wordsArray = fileContent.split(splitRegex);

		if(readUnique && caseSensitive){
			insertCaseSensitiveAndUniqueWithSubstring(words, wordsArray, subString);
		} else if(readUnique){
			insertCaseInsensitiveAndUniqueWithSubstring(words, wordsArray, subString);
		} else if(!caseSensitive){
			String lowerSubString = subString.toLowerCase();
			int i, count = wordsArray.length;

			for(i = 0; i < count; i++){
				if(wordsArray[i].toLowerCase().contains(lowerSubString) )
					words.add(wordsArray[i]);
			}
		} else {
			int i, count = wordsArray.length;
			for(i = 0; i < count; i++){
				if(wordsArray[i].contains(subString) )
					words.add(wordsArray[i]);
			}

		}

		return words;
	}

	public List<String> getStringsWhichPalindromes(){
		List<String> words = new ArrayList<>();
		String fileContent = reader.readLines();

		if(fileContent.length() < 1)
			return words;

		String[] wordsArray = fileContent.split(splitRegex);
		String wordCheck;
		int count = wordsArray.length, newCount;
		while(count > 0){
			wordCheck = wordsArray[0];
			newCount = removePalindrome(wordsArray, wordCheck, count);

			if(palindromeWord.length() > 0){
				words.add(wordCheck);
				if(count - newCount > 1)
					words.add(palindromeWord);
			}
			count = newCount;
		}

		return words;
	}

	public void setReadRange(int fromLine, int toLine){
		reader.setReadRange(fromLine, toLine);
	}
	public void setReadRange(int fromLine){
		reader.setReadRange(fromLine);
	}
	public void setFilePath(String newFile){
		reader.setFilePath(newFile);
	}
	public void resetReader(){
		reader.resetFile();
	}

	public void caseInsensitiveSet(){
		caseSensitive = false;
		reader.resetFile();
	}
	public void caseSensitiveSet(){
		caseSensitive = true;
		reader.resetFile();
	}

	public void setWordsUniqueness(boolean uniqueness){
		readUnique = uniqueness;
		reader.resetFile();
	}
}

