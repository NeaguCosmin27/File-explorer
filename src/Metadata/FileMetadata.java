package Metadata;

public class FileMetadata {
	
	private String fileName;
	private String fileSize;
	private String fileTyle;
	private String filePath;
	
	public FileMetadata() {
		
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileTyle() {
		return fileTyle;
	}

	public void setFileTyle(String fileTyle) {
		this.fileTyle = fileTyle;
	}
	
	

}
