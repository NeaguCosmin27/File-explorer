package DataModels;

import java.util.LinkedList;

import Metadata.FileMetadata;

public class FileListModel {
	
	private LinkedList<FileMetadata> fileList;
	
	public FileListModel() {
		fileList = new LinkedList<FileMetadata>();
	}
	
	public void addToList(FileMetadata fileMetadata) {
		fileList.add(fileMetadata);
	}
	
	public void removeFormList(FileMetadata fileMetadata) {
		fileList.remove(fileMetadata);
	}
	
	public LinkedList<FileMetadata> getFileList(){
		return fileList;
	}

}
