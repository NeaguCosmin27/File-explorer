package GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Listeners.ControlsAreaListener;

public class ControlsArea extends JPanel implements ActionListener{
	
	private JLabel filePath;
	
	private JTextField filePathText;
	
	private JButton goBack;
	
	private ControlsAreaListener listener;
	
	public ControlsArea() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Search management"));
		
		filePath = new JLabel("File path: ");
		
		filePathText = new JTextField(20);
		filePathText.setEditable(false);
		
		goBack = new JButton("Back");
		goBack.addActionListener(this);
		
		setControls();
	}
	
	public void setFileHerarchicalpath(String fileHierarchicalPath) {
		filePathText.setText(fileHierarchicalPath);
	}
	
	public void setControlsAreaListener(ControlsAreaListener listener) {
		this.listener = listener;
	}
	
	public String getSelectedFilePath() {
		
		if(filePathText.getText().equals("")) {
			return null;
		}else {
			return filePathText.getText();
		}
		
	}
	
	private void setControls() {
		JPanel controlsPanel = new JPanel();
		add(controlsPanel, BorderLayout.CENTER);
		controlsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridx = 0;
		gc.gridy = 0;
		controlsPanel.add(filePath, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		controlsPanel.add(filePathText, gc);
		
		gc.gridx = 2;
		gc.gridy = 0;
		controlsPanel.add(goBack, gc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		
		if(source == goBack) {
			listener.getHierarchicalPath(filePathText.getText());
		}
	}

}
