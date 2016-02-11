import java.io.*;
import java.nio.file.*;
import java.util.*;
 
class DirectoryTree {
    private static final int STORE_SIZE = 5;
    
    private Path startDir;
    private String searchString;
    private List<Line> allLines;
    
    public DirectoryTree (Path startDir, String searchString, List<Line> allLines) {
		this.startDir = startDir.toAbsolutePath();
		this.searchString = searchString;
		this.allLines = allLines;
	}
	
    public synchronized void searchTree() 
    		throws InterruptedException {
    	
        getDirectoryContent(startDir);
    }
 
    public synchronized void printLines()
            throws InterruptedException {
    	
        while (allLines.size() == 0) {
            wait();
        }
        
        while (!allLines.isEmpty()) {
        	Line line = allLines.remove(0);
        	if (line.getLineText().contains(this.searchString)) {
        		System.out.println(line);
        	}
		}

        notify();
    }
    
    private void getDirectoryContent(Path dir) 
    		throws InterruptedException {
    	
    	try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir)) {
            for (Path file : dirStream) {
            	if (Files.isDirectory(file)) {
            		getDirectoryContent(file);
            	} else if (Files.isRegularFile(file)  &&  file.toString().endsWith(".txt")) {
                    readFile(file);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    private void readFile(Path file) 
    		throws IOException, InterruptedException {
    	
    	InputStream is = new FileInputStream(file.toString());
		Reader reader = new InputStreamReader(is, "UTF-8");
		Line lineOutput;
		
		try (LineNumberReader lnr = new LineNumberReader(reader)) {
			String line = lnr.readLine();
	        while (line != null) {
	        	lineOutput = new Line(file.getFileName().toString(), line, lnr.getLineNumber());
	        	
	        	while (allLines.size() == STORE_SIZE) {
	                wait();
	        	}
	        	
	        	allLines.add(lineOutput);
	        	
	        	notify();
	        	line = lnr.readLine();
	        }
		} 
	}
}