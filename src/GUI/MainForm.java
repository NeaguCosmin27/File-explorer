package GUI;

import javax.swing.SwingUtilities;

public class MainForm {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(()-> new Mainframe());

	}

}
