//package sengGUI;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
 
 import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
 public class GraphTest extends Canvas {
 
    private static Frame f = new Frame ();
 
	public static void main (String[] args) {
       f.setTitle ("Java 2D Curve Demo");
       f.setBackground (Color.white);
       f.setSize (600, 600);
       class WindowClosingListener extends WindowAdapter {
 	 public void windowClosing (WindowEvent evt) { close(); }
       }
       f.addWindowListener (new WindowClosingListener ());
       f.add (new GraphTest());
       f.show();
    }
    
    private static void close () {
       f.setVisible (false);
       f.dispose();
       System.exit(0);
    }
 

 
    private static final Point2D.Double[] curvePoints = {
    	//new Point2D.Double (10, 180),
    	//new Point2D.Double (30, 30),
    	//new Point2D.Double (50, 50),
    	new Point2D.Double (0, 300),
    	new Point2D.Double (30, 350),
    	new Point2D.Double (60, 290),
    	new Point2D.Double (70, 200),
    	new Point2D.Double (85, 230),
    	new Point2D.Double (120, 260),
    	new Point2D.Double (130, 240),
    	new Point2D.Double (150, 270),
    	new Point2D.Double (200, 300),
    	new Point2D.Double (250, 350),
    	new Point2D.Double (300, 80),
    	new Point2D.Double (360, 180),
    	//new Point2D.Double (80, 445)
    };
    
    private static final Point2D.Double[] BuyPoints = {
    	//new Point2D.Double (10, 180),
    	//new Point2D.Double (30, 30),
    	//new Point2D.Double (50, 50),
    	
    	new Point2D.Double (70, 200),
    	
    	new Point2D.Double (300, 80),
    	
    	//new Point2D.Double (80, 445)
    };
    
    private static final Point2D.Double[] sellPoints = {
    	//new Point2D.Double (10, 180),
    	//new Point2D.Double (30, 30),
    	//new Point2D.Double (50, 50),
    
    	new Point2D.Double (30, 350),
    	
    	new Point2D.Double (250, 350),
   
    	//new Point2D.Double (80, 445)
    };
    
    private static Rectangle2D square (Point2D p) {
       final double x=p.getX()-2,y=p.getY()-2;
       final int w=5,h=5;
       return new Rectangle2D.Double(x,y,w,h);
    }
    
    public void paint (Graphics g) {
       final Graphics2D g2 = (Graphics2D) g;
       g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       final BasicStroke s = new BasicStroke (2);
// place all point
    
       for(int i = 0; i <curvePoints.length; i++) {
	       g2.setPaint (Color.gray);
	       Point2D.Double newpoint =new Point2D.Double (80+curvePoints[i].x, 445-curvePoints[i].y);
	       g2.fill (square(newpoint));
	       
       }
       for(int i = 0; i <BuyPoints.length; i++) {
	       g2.setPaint (Color.blue);
	       Point2D.Double newpoint =new Point2D.Double (80+BuyPoints[i].x, 445-BuyPoints[i].y);
	       g2.fill (square(newpoint));
	       int x=  (int)(80+BuyPoints[i].x);
	       int y=  (int)(460-BuyPoints[i].y);
	       g.drawString("Buy", x, y);
	       
	       
       }
       for(int i = 0; i <sellPoints.length; i++) {
	       g2.setPaint (Color.red);
	       Point2D.Double newpoint =new Point2D.Double (80+sellPoints[i].x, 445-sellPoints[i].y);
	       g2.fill (square(newpoint));
	       int x=  (int)(80+sellPoints[i].x);
	       int y=  (int)(445-sellPoints[i].y);
	       g.drawString("Sell", x, y);
	       
       }
 
       int drawHigh[] = new int[curvePoints.length];
       int drawwidth[] = new int[curvePoints.length];

       String mongth[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul","Aug","Sep","Oct","Nov","Dec" };
       for (int i = 0; i < curvePoints.length; i++) {
           drawHigh[i] = (int) (445 - curvePoints[i].y);
           drawwidth[i] = (int) (80 + curvePoints[i].x) ;
           g2.setPaint (Color.black);
          // g.drawString(""+ i*80, 110 + i * 80, 465);
       }
       for (int i = 0; i < mongth.length; i++) {
    	   g.drawString(mongth[i], 80 + i * 40, 465);
       }
    // draw lines
       g2.setColor(Color.RED);
     
       g2.drawPolyline(drawwidth, drawHigh, curvePoints.length);
       int visitValue = 0;
  
       for (int i = 418; i > 0; i -= 38) {
           g.setColor(Color.BLACK);
           g.drawString("" + visitValue, 36, (i + 27));
           g.setColor(Color.LIGHT_GRAY);
           g.drawLine(80, (i + 27), 550, (i + 27));
           visitValue += 10;
       }
       g.setColor(Color.BLACK);
       g.drawLine(80, 40, 80, 445);
       g.drawLine(80, 445, 550, 445);

    
       g.setColor(Color.BLACK);
       g.setFont(new Font("Arial Unicode MS", Font.PLAIN, 22));
       g.drawString("Graph", 10, 25);
       g.setFont(new Font("Arial Unicode MS", Font.PLAIN, 15));
       g.drawString("Price ($)", 10, 45);
       g.drawString("Time (dates)", 500, 500);
    }
 
 }