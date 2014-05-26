import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

public class GUI {
	private static JPanel content;
	private static String modulePath;
	private static String filePath;
	private static DefTextField nField;
	private static DefTextField thField;
	private static DefTextField outField;
	private static String dir;
	private static JLabel mPath;
	private static JLabel fPath;
	private static JLabel loading;

	private static void paintInputs(JPanel content) {
		nField = new DefTextField("Window Size:");
		thField = new DefTextField("Threshold:");
		outField = new DefTextField("Name of output file:");
		
		outField.setBounds(60, 35, 30, 20);
		thField.setBounds(60, 70, 60, 20);
		outField.setBounds(60, 105, 100, 20);
		
		nField.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		content.add(nField, "flowx,cell 0 5,alignx left");
		nField.setColumns(10);
		
		thField.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		content.add(thField, "cell 0 5");
		thField.setColumns(10);
		
		outField.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		content.add(outField, "cell 0 5,growx");
		outField.setColumns(10);
		
		nField.addFocusListener(new FocusListener() {
			
			public void focusGained(FocusEvent e) {
				if (nField.getText().equals(nField.getDefText())) {
					nField.setForeground(Color.BLACK);
					nField.setText("");
				}
			}
			
			public void focusLost(FocusEvent e) {
				if (nField.getText().equals("")) {
					nField.setForeground(Color.LIGHT_GRAY);
					nField.setText(nField.getDefText());
				}
				
			}
		});
		thField.addFocusListener(new FocusListener() {
			
			public void focusGained(FocusEvent e) {
				if (thField.getText().equals(thField.getDefText())) {
					thField.setForeground(Color.BLACK);
					thField.setText("");
				}
				
			}
			
			public void focusLost(FocusEvent e) {
				if (thField.getText().equals("")) {
					thField.setForeground(Color.LIGHT_GRAY);
					thField.setText(thField.getDefText());
				}
			}
		});
		outField.addFocusListener(new FocusListener() {
			
			public void focusGained(FocusEvent e) {
				if (outField.getText().equals(outField.getDefText())) {
					outField.setForeground(Color.BLACK);
					outField.setText("");
				}
				
			}
			
			public void focusLost(FocusEvent e) {
				if (outField.getText().equals("")) {
					outField.setForeground(Color.LIGHT_GRAY);
					outField.setText(outField.getDefText());
				}
			}
		});

	}

	public static void main(String[] args) {

		dir = GUI.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		try {
			dir = URLDecoder.decode(dir, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		dir = dir.substring(1);
		dir += "..\\..";
		
		content = new JPanel();
		content.setBackground(new Color (255, 255, 255));

		JFrame window = new JFrame("MSM Module");
		window.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		window.setContentPane(content);
		content.setLayout(new MigLayout("", "[299px,grow][grow][5px][1px][4px][1px][4px][9px][][][]", "[66px][66px][66px][66px][66px][66px][66px][66px][66px][66px][66px]"));
		ImageIcon bg =new ImageIcon("bg1.jpg");
		
		JLabel lable= new JLabel(bg);
		JLabel txtrWelcome1 = new JLabel();
		lable.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		window.getLayeredPane().add(lable,new Integer(Integer.MIN_VALUE));
		JPanel jp=(JPanel)window.getContentPane();
		jp.setOpaque(false);
		txtrWelcome1.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		txtrWelcome1.setForeground(new Color(200, 150, 0));
		txtrWelcome1.setBackground(new Color(0, 191, 255));
		txtrWelcome1.setText("Welcome to the Momentum Strategy Module!");
		content.add(txtrWelcome1, "cell 0 0,alignx center,aligny center");
		
		JLabel txtrWelcome2 = new JLabel();
		txtrWelcome2.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtrWelcome2.setForeground(new Color(111, 255, 96));
		txtrWelcome2.setBackground(new Color(0, 191, 255));
		txtrWelcome2.setText("Firstly, select the module you would like to use by clicking the button below");
		content.add(txtrWelcome2, "cell 0 1,alignx center,aligny center");
		
		JButton ModuleButton = new JButton("Choose Your Module");
		ModuleButton.setForeground(new Color(0, 0, 0));
	//	ModuleButton.setBackground(new Color(0, 206, 209));
		ModuleButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		content.add(ModuleButton, "cell 0 2,alignx center,aligny top");
		
		mPath = new JLabel();
		mPath.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		mPath.setForeground(new Color(255,255,255));
		mPath.setBackground(new Color(0, 191, 255));
		content.add(mPath, "cell 0 3,alignx left,aligny top");
		
		JLabel txtrNowPleaseEnter = new JLabel();
		txtrNowPleaseEnter.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtrNowPleaseEnter.setForeground(new Color(111, 255, 96));
		txtrNowPleaseEnter.setBackground(new Color(0, 191, 255));
		txtrNowPleaseEnter.setText("Now please enter the 'window size' value, and "
				+ "'threshold' value:");
		content.add(txtrNowPleaseEnter, "cell 0 4,alignx left,aligny top");

		paintInputs(content);
		
		JLabel txtrNowPleaseSelect = new JLabel();
		txtrNowPleaseSelect.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtrNowPleaseSelect.setForeground(new Color(111, 255, 96));
		txtrNowPleaseSelect.setBackground(new Color(0, 191, 255));
		txtrNowPleaseSelect.setText("Now, please select trades file in CSV format.");
		content.add(txtrNowPleaseSelect, "cell 0 6,grow");
		
		JButton fileButton = new JButton("Choose Your File");
		fileButton.setForeground(new Color(0, 0, 0));
		fileButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		content.add(fileButton, "cell 0 7,alignx center");
		
		fPath = new JLabel();
		fPath.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		fPath.setForeground(new Color(255,255,255));
		fPath.setBackground(new Color(0, 191, 255));
		content.add(fPath, "cell 0 8,grow");
		
		JLabel txtrNowLetsRun = new JLabel();
		txtrNowLetsRun.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtrNowLetsRun.setForeground(new Color(111, 255, 96));
		txtrNowLetsRun.setBackground(new Color(0, 191, 255));
		txtrNowLetsRun.setText("Now, let's run The Module based on your inputs.");
		content.add(txtrNowLetsRun, "cell 0 9,grow");
		
		JButton okButton = new JButton("Run Application");
		okButton.setForeground(new Color(0, 0, 0));
		okButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		content.add(okButton, "cell 0 10,alignx center,aligny top");
		
		loading = new JLabel();
		loading.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		loading.setBackground(new Color(0, 191, 255));
		content.add(loading, "cell 0 11,grow");
		
		window.setSize(540, 500);
		window.setLocation(100, 100);
		window.setVisible(true);
		window.setResizable(false);
		
		ButtonHandler listener = new ButtonHandler();
		ModuleHandler Modulelistener = new ModuleHandler();
		FileHandler filelistener = new FileHandler();
		okButton.addActionListener(listener);
		ModuleButton.addActionListener(Modulelistener);
		fileButton.addActionListener(filelistener);

	}

	private static class FileHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser openFile = new JFileChooser(dir);
			openFile.showOpenDialog(null);
			filePath = openFile.getSelectedFile().getAbsolutePath();
			fPath.setText(filePath);
			System.out.println(filePath);
		}
	}

	private static class ModuleHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser openFile = new JFileChooser(dir);
			openFile.showOpenDialog(null);
			modulePath = openFile.getSelectedFile().getAbsolutePath();
			mPath.setText(modulePath);
			System.out.println(modulePath);
		}
	}

	private static class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			loading.setText("Loading...");
			EventQueue.invokeLater( new Runnable() {
                public void run() {
                    MSMExecute();    //Action performer event
                }
            });
			
		}
	}
	
	private static boolean isValidName(String text)
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
	
	private static void MSMExecute() {
		String nVal = nField.getText();
		String thVal = thField.getText();
		String outVal = outField.getText();

		if (modulePath == null || modulePath.equals("")) {
			modulePath = "msm_v_1_2.exe";	
		}

		if (filePath == null || filePath.matches("")) {
			JOptionPane.showMessageDialog(null,
					"Please provide input file.");
			endRun(null);
		}
		
		String comm;
		
		if (modulePath == "msm_v_1_2.exe") {
			comm = modulePath + " " + "-i '" + filePath + "'";

			if (nVal != null && !nVal.equals("") && !nVal.equals(nField.getDefText())) {
				try {
					Integer.parseInt(nVal);
					comm += " -w " + nVal;
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalid window size.");
					ex.printStackTrace();
					endRun(ex);
					return;
				}
			}

			if (thVal != null && !thVal.equals("") && !thVal.equals(thField.getDefText())) {
				try {
					Double.parseDouble(thVal);
					comm += " -t " + thVal;
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalid threshold size.");
					ex.printStackTrace();
					endRun(ex);
					return;
				}
			}

			String[] inputFileName = filePath.split("\\\\");
			if (outVal != null && !outVal.equals("") && !outVal.equals(outField.getDefText())) {
				if (isValidName(outVal)) {
					if (outVal.equals(inputFileName[inputFileName.length - 1])) {
						JOptionPane.showMessageDialog(null,
								"Output file name cannot be same as input file name.");
						return;
					} else {
						comm += " -o " + outVal;
					}
					
				} else {
					JOptionPane.showMessageDialog(null,
							"Invalid output file name");
					endRun(null);
					return;
				}
			} else {
				if (inputFileName[inputFileName.length - 1].equals("newEntries.csv")) {
					JOptionPane.showMessageDialog(null,
							"Output file name cannot be same as input file name.");
					endRun(null);
					return;
				}
			}

			System.out.println(comm);
		} else {
			comm = modulePath;
		}
		
		Process p;

		try {
			p = Runtime.getRuntime().exec(comm);
			p.waitFor();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(p.getInputStream()));

			//System.out.println(reader.readLine());
			String line = "";
			StringBuffer output = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			JOptionPane.showMessageDialog(null, "Done. Please see log.txt in directory of module.");
			try {
				String winDir = dir.replace("/", "\\");
				System.out.println("explorer \"" + winDir + "\"");
				p = Runtime.getRuntime().exec("explorer \"" + winDir + "\"");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Could not open directory.");
				e1.printStackTrace();	
			}
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Could not open " + modulePath);
			e1.printStackTrace();
		}
		endRun(null);
		
	}
	
	private static void endRun(Exception e) {
		if (e != null) {
			e.printStackTrace();
		}
		loading.setText("");
	}
}