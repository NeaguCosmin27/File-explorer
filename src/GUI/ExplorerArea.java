package GUI;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import DataModels.ExplorerTableDataModel;
import DataModels.FileListModel;
import Listeners.ExplorerAreaListener;
import Metadata.FileMetadata;

public class ExplorerArea extends JPanel implements MouseListener{
	
	private JTable explorerTable;
	private ExplorerTableDataModel explorerTableDataModel;
	private FileListModel fileListModel;
	
	private ExplorerAreaListener listener;
	
	public ExplorerArea() {
		setLayout(new BorderLayout());
		
		explorerTable = new JTable();
		explorerTable.setShowGrid(false);
		explorerTable.addMouseListener(this);
		
		add(new JScrollPane(explorerTable), BorderLayout.CENTER);
		
		
		loadTableData();
	}
	
	
	public void loadTableData() {
		
		this.fileListModel = new FileListModel();
		this.explorerTableDataModel = new ExplorerTableDataModel();
		
		FileSystemView view = FileSystemView.getFileSystemView();
		
        File[] roots = File.listRoots();
        
        for (File root: roots)
        {
            FileMetadata metadata = new FileMetadata();
            metadata.setFilePath(root.getAbsolutePath());
            metadata.setFileName(root.getAbsolutePath());
            metadata.setFileTyle(view.getSystemTypeDescription(root));
            fileListModel.addToList(metadata); 
        }
        explorerTableDataModel.setFileList(fileListModel.getFileList());
        explorerTable.setModel(explorerTableDataModel);
        
        explorerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		explorerTable.getColumnModel().getColumn(0).setPreferredWidth(350);
		explorerTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		explorerTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        
	}
	
	private long getFolderSize(File folder) {
		long folderSize = 0;
		
		if(folder.isDirectory() && folder.listFiles()!=null) {
			for(File file:folder.listFiles()) {
				if(!file.isDirectory()) {
					folderSize += file.length();
				}else if(file.isDirectory() && file.listFiles()!=null) {
					folderSize += getFolderSize(file);
				}
			}
		}
		
		return folderSize;
	}
	
	//used to explore the selected folder or drive option
	public void exploreFolder(String folderPath) {
		
		this.fileListModel = new FileListModel();
		this.explorerTableDataModel = new ExplorerTableDataModel();
		
		File folderExplored = new File(folderPath);
		
		if(folderExplored.isDirectory()) {
			
			FileSystemView view = FileSystemView.getFileSystemView();
			
			if(folderExplored.listFiles() == null) {
				
			}else if(folderExplored.listFiles() != null) {
				for(File fileContained:folderExplored.listFiles()) {
					if(fileContained!= null && !fileContained.isHidden() && fileContained.isDirectory() && fileContained.listFiles()!=null) {
						
						DecimalFormat decimalFormat = new DecimalFormat("#0.0");
						
						FileMetadata metadata = new FileMetadata();
			            metadata.setFileName(fileContained.getName());
			            metadata.setFilePath(fileContained.getAbsolutePath());
			            metadata.setFileTyle(view.getSystemTypeDescription(fileContained));
			            
			            /*if(getFolderSize(fileContained) < 1024) {
			            	
			            	double folderSize = getFolderSize(fileContained);
			            	metadata.setFileSize(decimalFormat.format(folderSize) + " bytes");
			            	
			            }else if((getFolderSize(fileContained) > 1024) && (getFolderSize(fileContained) < (1024 * 1024))) {
			            	
			            	double folderSize = (double)getFolderSize(fileContained) / 1024;
			            	metadata.setFileSize(decimalFormat.format(folderSize) + " kb");
			            	
			            }else if((getFolderSize(fileContained) > (1024 * 1024)) && (getFolderSize(fileContained) < (1024 * 1024 * 1024))) {
			            	
			            	double folderSize = (double)getFolderSize(fileContained) / (1024 * 1024);
			            	metadata.setFileSize(decimalFormat.format(folderSize) + " MB");
			            	
			            }else if(fileContained.length() > (1024 * 1024 * 1024)) {
			            	
			            	double folderSize = (double)getFolderSize(fileContained) / (1024 * 1024 * 1024);
			            	metadata.setFileSize(decimalFormat.format(folderSize) + " GB");
			            }*/
			           
			            fileListModel.addToList(metadata);
			            
					} else if(!fileContained.isDirectory() && !fileContained.isHidden() && fileContained!=null) {
						
						DecimalFormat decimalFormat = new DecimalFormat("#0.0");
						FileMetadata metadata = new FileMetadata();
			            metadata.setFileName(fileContained.getName());
			            metadata.setFilePath(fileContained.getAbsolutePath());
			            
			            if(fileContained.length() < 1024) {
			            	
			            	double fileSize = fileContained.length();
			            	metadata.setFileSize(decimalFormat.format(fileSize) + " bytes");
			            	
			            }else if((fileContained.length() > 1024) && (fileContained.length() < (1024 * 1024))) {
			            	
			            	double fileSize = (double)fileContained.length() / 1024;
			            	metadata.setFileSize(decimalFormat.format(fileSize) + " kb");
			            	
			            }else if((fileContained.length() > (1024 * 1024)) && (fileContained.length() < (1024 * 1024 * 1024))) {
			            	
			            	double fileSize = (double)fileContained.length() / (1024 * 1024);
			            	metadata.setFileSize(decimalFormat.format(fileSize) + " MB");
			            	
			            }else if((fileContained.length() > (1024 * 1024 * 1024))) {
			            	
			            	double fileSize = (double)fileContained.length() / (1024 * 1024 * 1024);
			            	metadata.setFileSize(decimalFormat.format(fileSize) + " GB");
			            }
			            
			            
			            metadata.setFileTyle(view.getSystemTypeDescription(fileContained));
			            fileListModel.addToList(metadata);
					}
			}
			
			explorerTableDataModel.setFileList(fileListModel.getFileList());
	        explorerTable.setModel(explorerTableDataModel);
	        
	        explorerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			explorerTable.getColumnModel().getColumn(1).setPreferredWidth(100);
			explorerTable.getColumnModel().getColumn(2).setPreferredWidth(150);
			explorerTable.getColumnModel().getColumn(0).setPreferredWidth(350);
	        
	        listener.getSelectedPath(folderPath);
			
		}
		}
	}
	
	public void setExplorerAreaListener(ExplorerAreaListener listener) {
		this.listener = listener;
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		
		int selectedRow = explorerTable.rowAtPoint(event.getPoint());
		
		if(event.getClickCount() == 2) {
			//System.out.println("Selected: " + explorerTableDataModel.getFileList().get(selectedRow).getFileName());
			
			if(explorerTableDataModel.getFileList() == null) {
				System.out.println("Filelist null");
			}else {
				exploreFolder(explorerTableDataModel.getFileList().get(selectedRow).getFilePath());
				//System.out.println(explorerTableDataModel.getFileList().get(selectedRow).getFilePath());
			}	
		}else if(event.getClickCount() == 1) {
			
			if(explorerTableDataModel.getFileList()!=null) {
				String selectedFileName = explorerTableDataModel.getFileList().get(selectedRow).getFileName();
			    //System.out.println(selectedFileName);
			    listener.getSelectedFileName(selectedFileName);
			}
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
