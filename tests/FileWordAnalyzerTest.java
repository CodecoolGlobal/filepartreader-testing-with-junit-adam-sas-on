import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FileWordAnalyzerTest {
	FileWordAnalyzer analyzer = new FileWordAnalyzer();

	private int diffForSymmetricThousandthNumbers(final int number){
		int mod = number/1000;
		mod = number - 1000*mod;
		return (mod > 990)? 11 : 110;
	}

	@Test
	public void getWordsOrderedAlphabeticallyByStarWars() {
		analyzer.setFilePath("Star_Wars_-_Attack_of_the_Clones.txt");
		analyzer.setReadRange(892, 898);

		List<String> expected = Arrays.asList("ANAKIN", "baan", "bata", "Booda", "boska",
				"chasa", "Chut", "di", "Ding", "Du", "hopa", "hota", "Jedi", "Ke", "mi",
				"No", "pee", "Shmi", "Skywalker", "tu", "wanga", "WATTO", "Yo");
		List<String> result;

		analyzer.setReadRange(892, 898);
		analyzer.caseInsensitiveSet();
		analyzer.setWordsUniqueness(true);

		result = analyzer.getWordsOrderedAlphabetically();
		assertEquals(expected, result);
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


		subString = "Ken";
		analyzer.setWordsUniqueness(true);
		expected = Arrays.asList("Kenobi", "broken", "Tusken", "Tuskens", "KENNY");
		result = analyzer.getWordsContainingSubstring(subString);
		assertEquals(expected, result);
	}

	@Test
	public void getStringsWhichPalindromesByNumbers() {
		analyzer.setFilePath("numbers_test.txt");
		analyzer.setReadRange(1);

		List<String> expected = new ArrayList<>(100);
		int n = 1001, diff = 110, repeatedNumber = 1551;

		while(n <= repeatedNumber){
			expected.add( Integer.toString(n) );
			n += diff;
		}
		expected.add( Integer.toString(repeatedNumber) );

		repeatedNumber = 7007;
		while(n <= repeatedNumber){
			expected.add( Integer.toString(n) );
			diff = diffForSymmetricThousandthNumbers(n);
			n += diff;
		}
		expected.add( Integer.toString(repeatedNumber) );
		while(n < 10000){
			expected.add( Integer.toString(n) );
			diff = diffForSymmetricThousandthNumbers(n);
			n += diff;
		}

		List<String> result = analyzer.getStringsWhichPalindromes();
		assertEquals(expected, result);
	}

}