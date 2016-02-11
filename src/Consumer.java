class Consumer extends Thread {
	private DirectoryTree directoryTree;

	public Consumer(DirectoryTree directoryTree) {
		this.directoryTree = directoryTree;
	}

	@Override
	public void run() {
		try {
			while (true) {
				this.directoryTree.printLines();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}