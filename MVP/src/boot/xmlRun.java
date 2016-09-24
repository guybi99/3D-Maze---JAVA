package boot;

import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import presenter.Properties;

public class xmlRun {

	public static void main(String[] args) {
		try {
			XMLEncoder write = new XMLEncoder(new FileOutputStream("properties.xml"));
			Properties st = new Properties();
			st.setNumOfThreads(99);
			st.setGenerateMazeAlgorithm("Growing");
			st.setSolveMazeAlgorithm("BFS");
			st.setSolveMazeAlgorithm("1");
			
			write.writeObject(st);
			write.flush();
			write.close();
			
			System.out.println("XML was created!");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}
	}
}
