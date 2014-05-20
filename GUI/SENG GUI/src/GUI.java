package sengrr;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextPane;

public class GUI {
	static JTextField thField;
	static JTextField nField;
	static JTextField outField;
	static String modulePath;
	static String filePath;
	private static JTextField txtEnterNHere;
	private static JTextField txtThreshold;
	private static JTextField txtOut;

	private static class HelloWorldDisplay extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// g.drawString( "Hello World!", 20, 30 );
			//g.drawString("N = ", 20, 50);
			//g.drawString("TH = ", 20, 85);
			//g.drawString("Out = ", 20, 120);
			nField = new JTextField();
			thField = new JTextField(0);
			outField = new JTextField();
			nField.setBounds(60, 35, 30, 20);
			thField.setBounds(60, 70, 60, 20);
			outField.setBounds(60, 105, 100, 20);
			;

			this.add(nField);
			this.add(thField);
			this.add(outField);
		}
	}

	public static void main(String[] args) {

		JPanel content = new JPanel();
		content.setBackground(new Color(0, 191, 255));

		JFrame window = new JFrame("MSM Module");
		window.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		window.setContentPane(content);
		content.setLayout(new MigLayout("", "[299px,grow][grow][5px][1px][4px][1px][4px][9px]", "[66px][29px][16px][][29.00][][grow][]"));
		
		JTextArea txtrWelcomeToThe = new JTextArea();
		txtrWelcomeToThe.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtrWelcomeToThe.setForeground(new Color(220, 20, 60));
		txtrWelcomeToThe.setBackground(new Color(0, 191, 255));
		txtrWelcomeToThe.setText("   Welcome to the MSM Module Gui \n  Firstly, select the module you would\nlike to use by clicking the button below");
		content.add(txtrWelcomeToThe, "cell 0 0,alignx center,aligny center");
		
		JLabel label_1 = new JLabel("");
		content.add(label_1, "cell 3 0,alignx left,aligny center");
		
		JLabel label_2 = new JLabel("");
		content.add(label_2, "cell 5 0,alignx left,aligny center");
		
		JLabel label_3 = new JLabel("");
		content.add(label_3, "cell 7 0,alignx left,aligny center");
		
		JButton ModuleButton = new JButton("Choose Your Module");
		ModuleButton.setForeground(new Color(218, 165, 32));
		ModuleButton.setBackground(new Color(0, 206, 209));
		ModuleButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		content.add(ModuleButton, "cell 0 1,alignx center,aligny top");
		
		JTextArea txtrNowPleaseEnter = new JTextArea();
		txtrNowPleaseEnter.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		txtrNowPleaseEnter.setForeground(new Color(220, 20, 60));
		txtrNowPleaseEnter.setBackground(new Color(0, 191, 255));
		txtrNowPleaseEnter.setText("Now please enter the N value, and Threshold Value:");
		content.add(txtrNowPleaseEnter, "cell 0 2 8 1,alignx left,aligny top");
		
		txtEnterNHere = new JTextField();
		txtEnterNHere.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtEnterNHere.setText("       N");
		content.add(txtEnterNHere, "flowx,cell 0 3,alignx left");
		txtEnterNHere.setColumns(10);
		
		txtThreshold = new JTextField();
		txtThreshold.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtThreshold.setText("   Threshold");
		content.add(txtThreshold, "cell 0 3");
		txtThreshold.setColumns(10);
		
		txtOut = new JTextField();
		txtOut.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		txtOut.setText("       Out");
		content.add(txtOut, "cell 0 3,growx");
		txtOut.setColumns(10);
		
		JTextArea txtrNowPleaseSelect = new JTextArea();
		txtrNowPleaseSelect.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		txtrNowPleaseSelect.setForeground(new Color(220, 20, 60));
		txtrNowPleaseSelect.setBackground(new Color(0, 191, 255));
		txtrNowPleaseSelect.setText("Now please select trades file in CSV format");
		content.add(txtrNowPleaseSelect, "cell 0 4,grow");
		
		JButton fileButton = new JButton("Choose Your File");
		fileButton.setForeground(new Color(218, 165, 32));
		fileButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		content.add(fileButton, "cell 0 5,alignx center");
		
		JTextArea txtrNowLetsRun = new JTextArea();
		txtrNowLetsRun.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtrNowLetsRun.setForeground(new Color(220, 20, 60));
		txtrNowLetsRun.setBackground(new Color(0, 191, 255));
		txtrNowLetsRun.setText("Now Lets Run The Module Based on your Inputs");
		content.add(txtrNowLetsRun, "cell 0 6,grow");
		
		JButton okButton = new JButton("Run Application");
		okButton.setForeground(new Color(218, 165, 32));
		okButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		content.add(okButton, "cell 0 7,alignx center");
		window.setSize(350, 300);
		window.setLocation(100, 100);
		window.setVisible(true);

	}

	public static boolean isValidName(String text)
	{
	    try
	    {
	        File file = new File(text);
	        file.createNewFile();
	        if(file.exists()) file.delete();
	        return true;
	    }
	    catch(Exception ex){}
	    return false;
	}
}