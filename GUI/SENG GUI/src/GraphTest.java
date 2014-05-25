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
       f.setBackground (Color.cyan);
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
 
    private static final Point2D.Double[] basePoints = {
       new Point2D.Double (10, 17),
       new Point2D.Double (10, 360),
   //    new Point2D.Double (360, 180),
       new Point2D.Double (360, 360),
       
    };
 
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
      // g2.setPaint (Color.gray);
       //g2.fill (square(basePoints[0]));
       //g2.fill (square(basePoints[1]));
       //g2.fill (square(basePoints[2]));
  //     g2.fill (square(basePoints[3]));
       
       for(int i = 0; i <curvePoints.length; i++) {
	       g2.setPaint (Color.red);
	       Point2D.Double newpoint =new Point2D.Double (80+curvePoints[i].x, 445-curvePoints[i].y);
	       g2.fill (square(newpoint));
	    
       }
     
//       g2.setPaint (Color.blue);
//       g2.setStroke (s);
//       final CubicCurve2D c = new CubicCurve2D.Double ();
//       c.setCurve (cc,0);
//       g2.draw (c);
 
       // construct base lines
       int drawHigh[] = new int[curvePoints.length];
       int drawwidth[] = new int[curvePoints.length];

       //折点坐标
       for (int i = 0; i < curvePoints.length; i++) {
           drawHigh[i] = (int) (445 - curvePoints[i].y);
           drawwidth[i] = (int) (80 + curvePoints[i].x) ;
           g2.setPaint (Color.black);
           g.drawString(""+ i*80, 110 + i * 80, 465);
       }
       //g2d.setXORMode(Color.WHITE);
       //折线粗细
      // g2.setStroke(new BasicStroke(3.0f));
       //折线的颜色
       g2.setColor(Color.RED);
       //画折线
       g2.drawPolyline(drawwidth, drawHigh, curvePoints.length);
       int visitValue = 0;
  
       for (int i = 418; i > 0; i -= 38) {
           g.setColor(Color.BLACK);
           g.drawString("" + visitValue, 36, (i + 27));
           g.setColor(Color.LIGHT_GRAY);
           g.drawLine(80, (i + 27), 520, (i + 27));
           visitValue += 10;
       }
       g.setColor(Color.BLACK);
       g.drawLine(80, 40, 80, 445);
       g.drawLine(80, 445, 550, 445);

      // g2.setPaint (Color.gray);
       //g2.fill (square(curvePoints[0]));
       //g2.setStroke (new BasicStroke (1));
       //g2.draw (new Line2D.Double (basePoints[0], basePoints[1]));
       //g2.draw (new Line2D.Double (basePoints[1], basePoints[2]));
   //    g2.draw (new Line2D.Double (basePoints[1], basePoints[3]));
       g.setColor(Color.BLACK);
       g.setFont(new Font("Arial Unicode MS", Font.PLAIN, 22));
       g.drawString("Graph", 15, 25);
      // g2.setPaint (Color.blue);
       //g2.setStroke (s);
       //final CubicCurve2D q = new CubicCurve2D.Double ();
       //final CubicCurve2D x = new CubicCurve2D.Double ();
       //x.setCurve (curvePoints1,0);
       //x.setLine(l);
      // q.setCurve (curvePoints,0);
      // g2.draw(x);
      // g2.draw (q);
 
    }
 
 }