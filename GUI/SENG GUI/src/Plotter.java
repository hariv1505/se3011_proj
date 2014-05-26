//package SENG GUI;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
 
 import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
 public class Plotter extends Canvas {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 6680022366152090715L;
	private static Frame f = new Frame ();
    private static  MSMPoint[] sellPoints = null;
    private static MSMPoint[] curvePoints= null;
    private static  MSMPoint[] buyPoints =null;
    private static String title = null;
    private static String xCoordinate = null;
    private static String yCoordinate = null;
    
    public void getinput(MSMPoint[] a,MSMPoint[] b,MSMPoint[] c){
    	 curvePoints =a;
         buyPoints = b;
         sellPoints = c;
    }
    
    public void setStrings(String header, String x, String y) {
    	title = header;
    	xCoordinate = x;
    	yCoordinate = y;
    		
    }
	public  void  GenarateGraph () {
    
       f.setTitle ("MSM by The Gs");
       f.setBackground (Color.white);
       f.setSize (900, 900);
       class WindowClosingListener extends WindowAdapter {
 	 public void windowClosing (WindowEvent evt) { close(); }
       }
       f.addWindowListener (new WindowClosingListener ());
       f.add (new Plotter());
       f.show();
    }
    
    private static void close () {
       f.setVisible (false);
       f.dispose();
       System.exit(0);
    }
 

     
  
    private static Rectangle2D square (Point2D p) {
       final double x=p.getX()-2,y=p.getY()-2;
       final int w=5,h=5;
       return new Rectangle2D.Double(x,y,w,h);
    }
    public double getavg (int[] a,int arraylength) {
     double total=0.0;
    
 	   for(int i = 0; i < arraylength; i++) {		
 		 total = total + a[i];
 	   }
 	  
 	   
 	   return total/arraylength;
    }
    
    public void paint (Graphics g) {
       final Graphics2D g2 = (Graphics2D) g;
       g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       final BasicStroke s = new BasicStroke (2);
       double mintime = 0.0;
       double maxtime = curvePoints.length;
       double minprice = curvePoints[0].getPrice().doubleValue();
       double maxprice = curvePoints[curvePoints.length - 1].getPrice().doubleValue();
       
      
       
// place all point
    
       for(int i = 0; i <curvePoints.length; i++) {
	       g2.setPaint (Color.gray);
	       Point2D.Double newpoint =new Point2D.Double (80+(465/(curvePoints.length)*(i)), 445-(380/(maxprice-minprice)*(curvePoints[i].getPrice().doubleValue()-minprice)));
	       g2.fill (square(newpoint));
	       
       }
       for(int i = 0; i <buyPoints.length; i++) {
	       g2.setPaint (Color.green);
	       Point2D.Double newpoint =new Point2D.Double (80+(465/(curvePoints.length)*(i)), 445-(380/(maxprice-minprice)*(buyPoints[i].getPrice().doubleValue()-minprice)));
	       g2.fill (square(newpoint));
	       int x=  (int)(80+(465/(curvePoints.length)*(i)));
	       int y=  (int)(445-(380/(maxprice-minprice)*(buyPoints[i].getPrice().doubleValue()-minprice)));
	       g.drawString("Buy", x, y);
	       
	       
       }
       for(int i = 0; i <sellPoints.length; i++) {
	       g2.setPaint (Color.red);
	       Point2D.Double newpoint =new Point2D.Double (80+(465/(curvePoints.length)*(i)), 445-(380/(maxprice-minprice)*(sellPoints[i].getPrice().doubleValue()-minprice)));
	       g2.fill (square(newpoint));
	       int x=  (int)(80+(465/(curvePoints.length)*(i)));
	       int y=  (int)(445-(380/(curvePoints.length)*(sellPoints[i].getPrice().doubleValue()-minprice)));
	       g.drawString("Sell", x, y);
	       
       }
 
       int drawHight[] = new int[curvePoints.length];
       int drawwidth[] = new int[curvePoints.length];
       int avgHigh[] = new int[curvePoints.length];
       int avgwidth[] = new int[curvePoints.length];

      
       for (int i = 0; i < curvePoints.length; i++) {
           drawHight[i] = (int) (445-(380/(maxprice-minprice)*(curvePoints[i].getPrice().doubleValue()-minprice)));
           drawwidth[i] = (int) (80+(465/(maxtime-mintime)*(i))) ;
           avgwidth[i] = (int) (80+(465/(maxtime-mintime)*(i))) ;
           avgHigh[i]= (int) getavg(drawHight,i+1);
        	 
      //     System.out.println(avgHigh[i]);
           g2.setPaint (Color.black);
          // g.drawString(""+ i*80, 110 + i * 80, 465);
       }
       g.setFont(new Font("Arial Unicode MS", Font.PLAIN, 10));
       for (int i = 0; i < 10; i++) {    	   
    	   g.drawString(curvePoints[(curvePoints.length / 10) * i].getDate(), 80 + i * 50, 465 + ((i%2)*20));
       }
    // draw lines
       g2.setColor(Color.blue);
     
       g2.drawPolyline(drawwidth, drawHight, curvePoints.length);
       g2.setColor(Color.orange);
       
       g2.drawPolyline(avgwidth, avgHigh, curvePoints.length);
       int j=0;
       for (int i = 418; i > 0; i -= 38) {
           g.setColor(Color.BLACK);
           g.drawString(""+(minprice+j*((maxprice-minprice)/10)), 36, (i + 27));
           g.setColor(Color.LIGHT_GRAY);
           g.drawLine(80, (i + 27), 550, (i + 27));
           j++;
       }
       g.setColor(Color.BLACK);
       g.drawLine(80, 40, 80, 445);
       g.drawLine(80, 445, 550, 445);

    
       g.setColor(Color.BLACK);
       g.setFont(new Font("Arial Unicode MS", Font.PLAIN, 22));
       g.drawString(title, 10, 25);
       g.setFont(new Font("Arial Unicode MS", Font.PLAIN, 15));
       g.drawString(yCoordinate, 10, 45);
       g.drawString(xCoordinate, 500, 500);
    }
 
 }