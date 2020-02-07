import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FileWordAnalyzerTest {
	FileWordAnalyzer analyzer = new FileWordAnalyzer();

	@Test
	public void getWordsOrderedAlphabetically() {
	}

	@Test
	public void wordsContainingSubstringTestByStarWars() {
		analyzer.setFilePath("Star_Wars_-_Attack_of_the_Clones.txt");
		analyzer.setReadRange(1, 1520);
		analyzer.caseInsensitiveSet();
		analyzer.setWordsUniqueness(true);

		List<String> result;
		String subString = "Ani";
		List<String> expected = Arrays.asList("Ani", "canisters", "animals", "mechanic", "DANIELS", "DANIEL", "HASSANI");

		result = analyzer.getWordsContainingSubstring(subString);
		assertEquals(expected, result);

		analyzer.setWordsUniqueness(false);
		expected = Arrays.asList("Ani", "Ani", "Ani", "Ani", "Ani", "Ani", "Ani", "Ani",
				"canisters", "Ani", "Ani", "Ani", "Ani", "Ani", "Ani", "Ani", "Ani",
				"animals", "animals", "Ani", "Ani", "mechanic", "DANIELS", "DANIELS", "DANIEL", "HASSANI");

		result = analyzer.getWordsContainingSubstring(subString);
		assertEquals(expected, result);
	}

	@Test
	public void getStringsWhichPalindromes() {
	}

}