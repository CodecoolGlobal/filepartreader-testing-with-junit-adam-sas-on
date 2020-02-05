
public class Main {
	public static void main(String[] args){
		FilePartReader fpr = new FilePartReader("Star_Wars_-_Attack_of_the_Clones.txt", 313, 321);
		String fileContent = fpr.readLines();
		System.out.println("\tRows from 313 to 321:");
		System.out.println(fileContent);

		int fromLine = 751, toLine = 775;
		fpr.setReadRange(fromLine, toLine);
		fileContent = fpr.readLines();
		System.out.println("\tRows from " + fromLine +  " to " + toLine + ":");
		System.out.println(fileContent);
	}
}

