import org.junit.Test;

import static org.junit.Assert.*;

public class FilePartReaderTest {
	FilePartReader fpr = new FilePartReader();

	@Test
	public void readLinesTest(){
		fpr.setup("lorem_ipsum.txt", 3, 6);
		String[] rows = new String[4];
		rows[0] = "Nam porta condimentum lorem ac egestas. Sed lectus erat, maximus vitae ex in, " +
				"scelerisque convallis tortor. Ut sit amet magna ipsum. Etiam ut eleifend eros. " +
				"Pellentesque bibendum at magna id iaculis. Ut et pretium risus. Curabitur aliquet, " +
				"neque pharetra tristique tempus, erat justo pellentesque diam, nec rhoncus tellus massa ut nisl. " +
				"Duis libero odio, ultrices non enim id, porttitor volutpat leo. Maecenas eget aliquam diam. " +
				"Donec metus nibh, gravida ac euismod id, mattis vitae risus. Vivamus porta in lectus ac vehicula. " +
				"Proin laoreet egestas neque sagittis finibus. In dignissim nisl in justo porta, " +
				"nec maximus ex vulputate. Donec ornare, neque id pellentesque consectetur, " +
				"purus ante ultrices arcu, ut vehicula neque massa a justo. Nam at sagittis metus. " +
				"Ut semper, purus vel viverra vestibulum, ligula nibh maximus odio, quis gravida erat massa non magna.";
		rows[1] = "";
		rows[2] = "Fusce molestie orci magna, ac commodo massa egestas id. Proin venenatis ante eu justo " +
				"molestie bibendum. In posuere augue cursus mi varius, ac vehicula orci facilisis. " +
				"Donec euismod eleifend ex, sit amet sodales diam lacinia vehicula. Donec vel urna elementum, " +
				"commodo orci ac, convallis velit. Donec orci massa, tempor non pulvinar in, " +
				"pretium nec lectus. Duis odio libero, ultricies et aliquet quis, sollicitudin ullamcorper odio. " +
				"Morbi tristique vehicula quam, ac pharetra mauris vulputate non. Quisque convallis " +
				"posuere cursus. Sed metus nunc, ornare eget augue cursus, dictum venenatis risus. " +
				"Nullam egestas nibh vitae lectus porttitor, eu cursus dui varius. " +
				"Ut sit amet tincidunt nisl, vel pretium erat.";
		rows[3] = "";
		String text = String.join("\n", rows), strFromFile;

		strFromFile = fpr.readLines();
		assertEquals(text, strFromFile);

		fpr.setReadRange(1, 1);
		text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur mauris nisl, " +
				"mollis vitae neque vitae, congue eleifend ligula. Nam ultrices mauris nisl, " +
				"et malesuada nibh porttitor sit amet. Ut nec libero bibendum ligula lobortis rutrum. " +
				"Suspendisse ut est in ante aliquam aliquet. Integer aliquet magna in urna placerat, " +
				"id aliquet neque rhoncus. Pellentesque habitant morbi tristique senectus et netus " +
				"et malesuada fames ac turpis egestas. Curabitur ex libero, fringilla et rhoncus sed, " +
				"dapibus eget justo. Orci varius natoque penatibus et magnis dis parturient montes, " +
				"nascetur ridiculus mus. Maecenas et maximus arcu. Maecenas porta, nisl maximus gravida " +
				"placerat, sem ipsum interdum metus, nec imperdiet lorem ligula et erat. " +
				"In hac habitasse platea dictumst.";
		strFromFile = fpr.readLines();
		assertEquals(text, strFromFile);
	}

	@Test
	public void readLinesTestByStarWars() {
		fpr.setup("Star_Wars_-_Attack_of_the_Clones.txt", 313, 321);
		String[] rows = new String[]{"ELAN SLEAZEBAGGANO: You wanna buy some death-sticks?", "",
				"OBI-WAN: You don't want to sell me death-sticks.", "",
				"ELAN: I don't want to sell you death-sticks.", "",
				"OBI-WAN: You want to go home and rethink your life.", "",
				"ELAN: I want to go home and rethink my life."};
		String text = String.join("\n", rows), strFromFile;

		strFromFile = fpr.readLines();
		assertEquals(text, strFromFile);

		fpr.setReadRange(752, 775);
		rows = new String[]{"OBI-WAN: Ever make your way as far into the interior as Coruscant?", "",
				"JANGO FETT: Once or twice.", "",
				"OBI-WAN: Recently?", "",
				"JANGO FETT: Possibly...", "",
				"OBI-WAN: Then you must know Master Sifo-Dyas?", "",
				"JANGO FETT: Boba, rood eht so-heeck.", "",
				"JANGO FETT: Master who?", "",
				"OBI-WAN: Sifo-Dyas. Is he not the Jedi who hired you for this job?", "",
				"JANGO FETT: Never heard of him.", "",
				"OBI-WAN: Really?", "",
				"JANGO FETT: I was recruited by a man called Tyranus on one of the moons of Bogden.", "",
				"OBI-WAN: Curious.", ""};
		text = String.join("\n", rows);

		strFromFile = fpr.readLines();
		assertEquals(text, strFromFile);

		fpr.setReadRange(452, 452);
		text = "ANAKIN: May the Force be with you, Master.";
		strFromFile = fpr.readLines();
		assertEquals(text, strFromFile);
	}
}

