package GUI;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

import Listeners.ControlsAreaListener;
import Listeners.ExplorerAreaListener;

public class Mainframe extends JFrame{
	
	private ExplorerArea explorerArea;
	private ControlsArea controlsArea;
	
	public Mainframe() {
		super("File Explorer");
		setVisible(true);
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		explorerArea = new ExplorerArea();
		add(explorerArea, BorderLayout.CENTER);
		
		controlsArea = new ControlsArea();
		add(controlsArea, BorderLayout.NORTH);
		
		explorerArea.setExplorerAreaListener(new ExplorerAreaListener() {

			@Override
			public void getSelectedPath(String fileHierarchicalPath) {
				controlsArea.setFileHerarchicalpath(fileHierarchicalPath);
			}

			@Override
			public void getSelectedFileName(String selectedFileName) {
				
				if(controlsArea.getSelectedFilePath()==null) {
					String filePath = selectedFileName; 
					System.out.println(filePath);
				}else {
					String filePath = controlsArea.getSelectedFilePath() + "\\" + selectedFileName;
					System.out.println(filePath);
				}
						
			}
			
		});
		
		controlsArea.setControlsAreaListener(new ControlsAreaListener() {

			@Override
			public void getHierarchicalPath(String hierarchicalPath) {
				
				FileSystemView view = FileSystemView.getFileSystemView();
				
                File parentFile = new File(hierarchicalPath);
       
                System.out.println(parentFile.getName());
                
                if(view.getSystemTypeDescription(parentFile).equals("Local Disk")) {
                	explorerArea.loadTableData();
                	controlsArea.setFileHerarchicalpath("");
                	
                }else if(parentFile.getParent()!=null){
                	explorerArea.exploreFolder(parentFile.getParent());
                }else {
                	
                }
			}
			
		});
		
	}

}
