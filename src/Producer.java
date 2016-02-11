class Producer extends Thread {
    private DirectoryTree directoryTree;
 
    public Producer(DirectoryTree directoryTree) {
        this.directoryTree = directoryTree;
    }
 
    @Override
    public void run() {
        try {
        	
    		this.directoryTree.searchTree();
    		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
