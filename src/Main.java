import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String args[]) {
    	
		Path startDir = Paths.get("/");
		String searchString = "file";
		List<Line> allLines = new ArrayList<Line>();
		
        DirectoryTree directoryTree = new DirectoryTree(startDir, searchString, allLines);
        
        Producer producer = new Producer(directoryTree);
        producer.start();

        Consumer consumer = new Consumer(directoryTree);
        consumer.start();            
    }
}