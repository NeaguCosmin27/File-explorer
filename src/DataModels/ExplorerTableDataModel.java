package DataModels;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import Metadata.FileMetadata;

public class ExplorerTableDataModel extends AbstractTableModel{
	
	private String[] columnName = {"Name", "Size", "Type"};
	private LinkedList<FileMetadata> fileList;
	
	public ExplorerTableDataModel() {
		
	}
	
	public void setFileList(LinkedList<FileMetadata> fileList) {
		this.fileList = fileList;
	}
	
	public LinkedList<FileMetadata> getFileList(){
		return fileList;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnName[column];
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return fileList.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		
		FileMetadata fileMetadata = fileList.get(row);
		switch(column) {
		case 0: 
			return fileMetadata.getFileName();
			
		case 1:
			return fileMetadata.getFileSize();
			
		case 2:
			return fileMetadata.getFileTyle();
		}
		return null;
	}

}
