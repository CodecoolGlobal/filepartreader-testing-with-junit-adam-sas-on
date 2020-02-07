import java.util.List;

public class Main {
	public static void main(String[] args){
		String fileName = "Star_Wars_-_Attack_of_the_Clones.txt";

		FilePartReader fpr = new FilePartReader(fileName, 313, 321);
		String fileContent = fpr.readLines();
		System.out.println("\tRows from 313 to 321:");
		System.out.println(fileContent);

		int fromLine = 751, toLine = 775;
		fpr.setReadRange(fromLine, toLine);
		fileContent = fpr.readLines();
		System.out.println("\tRows from " + fromLine +  " to " + toLine + ":");
		System.out.println(fileContent);


		FileWordAnalyzer analyzer = new FileWordAnalyzer(fileName, 10, 1400);
		List<String> words = analyzer.getWordsOrderedAlphabetically();
		System.out.println("\nUnique and case sensitive words from  " + fileName + "\n\t" + words.size() + " words:");
		System.out.println(words);

		analyzer.caseInsensitiveSet();
		words = analyzer.getWordsOrderedAlphabetically();
		System.out.println("\nUnique and case insensitive words from  " + fileName + "\n\t" + words.size() + " words:");
		System.out.println(words);

		analyzer.caseSensitiveSet();
		analyzer.setFilePath("lorem_ipsum.txt");
		words = analyzer.getStringsWhichPalindromes();
		System.out.println("\nPalindromes (case sensitive) from lorem_ipsum.txt\n\t" + words.size() + " words:");
		System.out.println(words);

		analyzer.setFilePath(fileName);
		words = analyzer.getStringsWhichPalindromes();
		System.out.println("\nPalindromes (case sensitive) from " + fileName + "\n\t" + words.size() + " words:");
		System.out.println(words);

		analyzer.caseInsensitiveSet();
		words = analyzer.getStringsWhichPalindromes();
		System.out.println("\nPalindromes (case insensitive) from " + fileName + "\n\t" + words.size() + " words:");
		System.out.println(words);
	}
}

