package sengGUI;

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
       f.setBackground (Color.cyan);
       f.setSize (400, 400);
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
 
    private static final Point2D.Double[] basePoints = {
       new Point2D.Double (10, 17),
       new Point2D.Double (10, 180),
       new Point2D.Double (360, 180),
       new Point2D.Double (10, 360),
       
    };
 
    private static final Point2D.Double[] curvePoints = {
    	new Point2D.Double (10, 180),
    	new Point2D.Double (30, 30),
    	new Point2D.Double (50, 50),
    	new Point2D.Double (200, 300),
//    	new Point2D.Double (250, 350),
    	new Point2D.Double (300, 30),
    	new Point2D.Double (360, 180)
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
 
       // Paint four control points
       g2.setPaint (Color.gray);
       g2.fill (square(basePoints[0]));
       g2.fill (square(basePoints[1]));
       g2.fill (square(basePoints[2]));
       g2.fill (square(basePoints[3]));
       
       for(int i = 1; i <curvePoints.length - 1; i++) {
	       g2.setPaint (Color.red);
	       g2.fill (square(curvePoints[i]));
       }
 
//       g2.setPaint (Color.blue);
//       g2.setStroke (s);
//       final CubicCurve2D c = new CubicCurve2D.Double ();
//       c.setCurve (cc,0);
//       g2.draw (c);
 
       // construct base lines
       g2.setPaint (Color.gray);
       g2.fill (square(curvePoints[0]));
       g2.setStroke (new BasicStroke (1));
       g2.draw (new Line2D.Double (basePoints[0], basePoints[1]));
       g2.draw (new Line2D.Double (basePoints[1], basePoints[2]));
       g2.draw (new Line2D.Double (basePoints[1], basePoints[3]));
       g2.setPaint (Color.blue);
       g2.setStroke (s);
       final CubicCurve2D q = new CubicCurve2D.Double ();
       q.setCurve (curvePoints,0);
       g2.draw (q);
 
    }
 
 }