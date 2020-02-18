package wordcounts;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class WordCounts4
{
	public static void main(String[] args)
	{	
		Path andress = Paths.get("url");
		try {
			byte[] words = Files.readAllBytes(andress);
			String readx = new String(words);

			String[] tokens = readx.split(" ");
			System.out.printf("Number of words: %d%n",tokens.length);
			
		} catch (Exception error) {
		}	
		}
}