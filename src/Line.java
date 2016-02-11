public class Line {
	private String fileName;
	private String lineText;
	private int rowNumber;
	
	public Line(String fileName, String lineText, int rowNumber) {
		this.fileName = fileName;
		this.lineText = lineText;
		this.rowNumber = rowNumber;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLineText() {
		return lineText;
	}

	public void setLineText(String lineText) {
		this.lineText = lineText;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	
	@Override
	public String toString() {
		return getFileName() + " " + getLineText() + " " + getRowNumber();
	}
}
