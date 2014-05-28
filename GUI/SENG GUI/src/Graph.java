import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Graph {
	 private static  ArrayList<MSMPoint> curvePoints;	//new Point2D.Double (80, 445)
	    
	 private static ArrayList<MSMPoint> buyPoints;
 
	 private static ArrayList<MSMPoint> sellPoints;
	 
	 public Graph(String originalFile, String resultFile) {
		 
		 curvePoints = new ArrayList<MSMPoint>();
		 buyPoints = new ArrayList<MSMPoint>();
		 sellPoints = new ArrayList<MSMPoint>();
		 
		 InputStream fis;
		 BufferedReader br;
		 String line;

		 try {
			 fis = new FileInputStream(originalFile);
			 br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			 int i = 0;
			 while ((line = br.readLine()) != null) {
				 i++;
			     if (i == 1) {
			    	 continue;
			     }
				 String[] splitLine = line.split(",");
				 if (!splitLine[3].equals("TRADE")) {
					 continue;
				 } else {
					 curvePoints.add(new MSMPoint(splitLine[1] + ", " 
					 + splitLine[2], 
					 Double.parseDouble(splitLine[4])));
				 }
			 }
			 
			 br.close();
			 
			 System.out.println(resultFile);
			 fis = new FileInputStream(resultFile);
			 br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			 i = 0;
			 while ((line = br.readLine()) != null) {
			     if (i == 0) {
			    	 i++;
			    	 continue;
			     }
				 String[] splitLine = line.split(",");
				 if (splitLine[12].equals("B")) {
					 buyPoints.add(new MSMPoint(splitLine[1] + ", " + splitLine[2], Double.parseDouble(splitLine[4])));
				 } else if (splitLine[12].equals("A")) {
					 sellPoints.add(new MSMPoint(splitLine[1] + ", " + splitLine[2], Double.parseDouble(splitLine[4])));
				 }
				 i++;
			 }
			 
	
			 // Done with the file
			 br.close();
			 br = null;
			 fis = null;
			 
			 this.create();
		 } catch (FileNotFoundException e) {
			 	JOptionPane.showMessageDialog(null,
						"File was not found - cannot create graph. The module must have failed.");
				e.printStackTrace();
				return;
			} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
				"File was not found - cannot create graph. The module must have failed.");
			e.printStackTrace();
			return;
		}
	 }
	 
	 public void create() {
		 Plotter p = new Plotter();
		 p.setStrings("MSM by The Gs", "Time (seconds)", "Price ($)");
		 p.getinput(curvePoints.toArray(new MSMPoint[curvePoints.size()]), 
				 buyPoints.toArray(new MSMPoint[buyPoints.size()]), sellPoints.toArray(new MSMPoint[sellPoints.size()]));
		 p.GenarateGraph();
    
	 }
}
