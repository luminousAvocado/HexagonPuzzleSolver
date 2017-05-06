package lab01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
	
	public List<Hexagon> readFromFile(File loadedFile) throws FileNotFoundException{
		
		List<Hexagon> hexList = new ArrayList<>();
		Scanner input = new Scanner(loadedFile);
		
		while (input.hasNext()){
			String newLine = input.nextLine();
			newLine = newLine.replaceAll("\\s+", ""); //closes all spaces in the string
			hexList.add(new Hexagon(newLine));
		}
		
		input.close();
		return hexList;
	}

}
