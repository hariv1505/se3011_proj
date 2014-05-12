import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import javax.swing.*;

public class GUI {
	static JTextField thField; 
	static JTextField nField;
	static JTextField outField;
	static String modulePath;
	static String filePath;
   private static class HelloWorldDisplay extends JPanel {
		
	   /**
	    * 
	    */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
         super.paintComponent(g);
         //g.drawString( "Hello World!", 20, 30 );
         g.drawString( "N = ", 20, 50 );
         g.drawString( "TH = ", 20, 50 );
         nField = new JTextField();
         thField = new JTextField(0);
         outField = new JTextField();
         nField.setBounds(60, 35, 30, 20);
         thField.setBounds(60, 60, 30, 60);
         outField.setBounds(60, 85, 30, 100);;
         
         this.add(nField);
         this.add(thField);
         this.add(outField);
      }
   }
   private static class FileHandler implements ActionListener{
	   public void actionPerformed(ActionEvent arg0) {
           JFileChooser openFile = new JFileChooser();
           openFile.showOpenDialog(null);
           filePath = openFile.getSelectedFile().getAbsolutePath();
           System.out.println(openFile.getSelectedFile().getAbsolutePath());
       }
   }
   
   private static class ModuleHandler implements ActionListener{
	   public void actionPerformed(ActionEvent arg0) {
           JFileChooser openFile = new JFileChooser();
           openFile.showOpenDialog(null);
           modulePath= openFile.getSelectedFile().getAbsolutePath();
           System.out.println(openFile.getSelectedFile().getAbsolutePath());
       }
   } 
   
   private static class ButtonHandler implements ActionListener {
      public void actionPerformed(ActionEvent e) {
    	 String nVal= nField.getText();
    	 String thVal= thField.getText();
    	 String outVal = outField.getText(); 
    	 
    	 if (modulePath.equals("")) {
    		 modulePath = "msm_v_1_2.exe";
    	 }
    	 
    	 if (filePath == null || filePath.matches("")) {
    		 JOptionPane.showMessageDialog(null, "Please provide input file.");
    		 return;
    	 }
    	 
    	 String s = modulePath+" " +"-i " + filePath;
    	 if (!nVal.equals("")) {
    		 try {
    			 Integer.parseInt(nVal);
    			 s += " -w " + nVal;
    		 } catch (NumberFormatException ex) {
    			 JOptionPane.showMessageDialog(null, "Invalid window size.");
    			 ex.printStackTrace();
    			 return;
    		 }
    	 }
    	
      	if (thVal.equals("")) {
    		 try {
    			 Integer.parseInt(thVal);
    			 s += " -th " + thVal;
    		 } catch (NumberFormatException ex) {
    			 JOptionPane.showMessageDialog(null, "Invalid window size.");
    			 ex.printStackTrace();
    			 return;
    		 }
    	 }
    	 
    	 if (outVal.equals("")) {
    		 if (isValidName(outVal)) {
    			 s += " -o " + outVal;
    		 } else {
    			 JOptionPane.showMessageDialog(null, "Invalid output file name");
    			 return;
    		 }
    	 }
    		 
    	// String outputPath=gui2.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    	// String s = modulePath+" " +"-i "+ filePath  + " -w "+ nVal +" -o " + outputPath + "-th " + thVal;
    	// String s="ping www.google.com.au";
    	System.out.println(s);
   
		Process p;
    	 
    	try {
 			p = Runtime.getRuntime().exec(s);
 			p.waitFor();
 			BufferedReader reader = 
                 new BufferedReader(new InputStreamReader(p.getInputStream()));
  	
 			System.out.println(reader.readLine());
            String line = "";
            StringBuffer output = new StringBuffer();
 			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
 			System.out.println(output.toString()); 
 		} catch (Exception e1) {
 			System.err.println("Could not open " + modulePath);
 			e1.printStackTrace();
 		}
    	
    	 
         System.exit(0);
      }
   }
   
   public static void main(String[] args) {
      
      HelloWorldDisplay displayPanel = new HelloWorldDisplay();
      JButton okButton = new JButton("Run Application");
      JButton ModuleButton = new JButton("Choose Your Module");
      JButton fileButton = new JButton("Choose Your file");
      ButtonHandler listener = new ButtonHandler();
      ModuleHandler Modulelistener = new ModuleHandler();
      FileHandler filelistener = new FileHandler();
      okButton.addActionListener(listener);
      ModuleButton.addActionListener(Modulelistener);
      fileButton.addActionListener(filelistener);   

      
      JPanel content = new JPanel();
      content.setLayout(new BorderLayout());
      content.add(displayPanel, BorderLayout.CENTER);
      content.add(ModuleButton,BorderLayout.NORTH);
      content.add(okButton, BorderLayout.SOUTH);
      content.add(fileButton,BorderLayout.AFTER_LINE_ENDS);
  
      JFrame window = new JFrame("GUI for MSM Module");
      window.setContentPane(content);      
      window.setSize(350,200);
      window.setLocation(100,100);
      window.setVisible(true);
    
   }
   
   public static boolean isValidName(String text)
   {
       Pattern pattern = Pattern.compile("[^/./\\:*?\"<>|]");
       return !pattern.matcher(text).find();
   }
   
}