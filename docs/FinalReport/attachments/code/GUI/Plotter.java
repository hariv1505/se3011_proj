
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
import java.text.DecimalFormat;

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
     f.setBackground (Color.darkGray);
     f.setSize (1400, 900);
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
     
    
     
//place all point
  
     for(int i = 0; i <curvePoints.length; i++) {
	       g2.setPaint (Color.gray);
	       Point2D.Double newpoint =new Point2D.Double (80+(465/(curvePoints.length)*(i)), 445-(380/(maxprice-minprice)*(curvePoints[i].getPrice().doubleValue()-minprice)));
	      // g2.fill (square(newpoint));
	       
     }
    
     for(int i = 0; i <sellPoints.length; i++) {
	       g2.setPaint (Color.red);
	       int sx = 0;
	       for(int j = 0; j <curvePoints.length; j++) {
		       //g2.setPaint (Color.gray);
		       //Point2D.Double newpoint =new Point2D.Double (80+(465/(curvePoints.length)*(i)), 445-(380/(maxprice-minprice)*(curvePoints[i].getPrice().doubleValue()-minprice)));
		      // g2.fill (square(newpoint));
	    	   
		       if (sellPoints[i].getDate().equals(curvePoints[j].getDate())&&sellPoints[i].getPrice().equals(curvePoints[j].getPrice())){
		    	    sx =  (int) (80+(1225/(maxtime-mintime)*(j)));
		       }
	     }
	       Point2D.Double newpoint =new Point2D.Double (sx, 445-(380/(maxprice-minprice)*(sellPoints[i].getPrice().doubleValue()-minprice)));
	       g2.fill (square(newpoint));
	       int x=  (int)(80+(465/(curvePoints.length)*(i)));
	       int y=  (int)(445-(380/(curvePoints.length)*(sellPoints[i].getPrice().doubleValue()-minprice)));
	     //  g.drawString("Sell", x, y);
	       
     }
     for(int i = 0; i <buyPoints.length; i++) {
	       g2.setPaint (Color.green);
	       int bx = 0;
	       for(int j = 0; j <curvePoints.length; j++) {
		       //g2.setPaint (Color.gray);
		       //Point2D.Double newpoint =new Point2D.Double (80+(465/(curvePoints.length)*(i)), 445-(380/(maxprice-minprice)*(curvePoints[i].getPrice().doubleValue()-minprice)));
		      // g2.fill (square(newpoint));
	    	   
		       if (buyPoints[i].getDate().equals(curvePoints[j].getDate())&&buyPoints[i].getPrice().equals(curvePoints[j].getPrice())){
		    	    bx =  (int) (80+(1225/(maxtime-mintime)*(j)));
		       }
	     }
	       Point2D.Double newpoint =new Point2D.Double (bx, 445-(380/(maxprice-minprice)*(buyPoints[i].getPrice().doubleValue()-minprice)));
	       g2.fill (square(newpoint));
	       System.out.println(buyPoints[i].getDate());
	       int x=  (int)(80+(465/(curvePoints.length)*(i)));
	       int y=  (int)(445-(380/(maxprice-minprice)*(buyPoints[i].getPrice().doubleValue()-minprice)));
	      // g.drawString("Buy", x, y);
	       
	       
   }
     int drawHight[] = new int[curvePoints.length];
     int drawwidth[] = new int[curvePoints.length];
     int avgHigh[] = new int[curvePoints.length];
     int avgwidth[] = new int[curvePoints.length];

    
     for (int i = 0; i < curvePoints.length; i++) {
         drawHight[i] = (int) (445-(380/(maxprice-minprice)*(curvePoints[i].getPrice().doubleValue()-minprice)));
         drawwidth[i] = (int) (80+(1225/(maxtime-mintime)*(i))) ;
         avgwidth[i] = (int) (80+(1225/(maxtime-mintime)*(i))) ;
         avgHigh[i]= (int) getavg(drawHight,i+1);
      	 
    //     System.out.println(avgHigh[i]);
         g2.setPaint (Color.white);
        // g.drawString(""+ i*80, 110 + i * 80, 465);
     }
     g.setFont(new Font("Arial Unicode MS", Font.PLAIN, 10));
     String str1=curvePoints[0].getDate();
	 String[] arr1 = str1.split(",");
	 g.drawString(arr1[0], 20, 620);
     for (int i = 0; i < 10; i++) {    	   
    	 String str=curvePoints[(curvePoints.length / 10) * i].getDate();
    	 String[] arr2 = str.split(",");
    			 String[] arr = arr2[1].split("\\.");
                  
  	   g.drawString(arr[0], 80 + i * 130, 620);
     }
  // draw lines
     g2.setColor(new Color(114,98,255));
   
     g2.drawPolyline(drawwidth, drawHight, curvePoints.length);
     g2.setColor(Color.orange);
     
     g2.drawPolyline(avgwidth, avgHigh, curvePoints.length);
     int j=0;
     for (int i = 540; i > 0; i -= 38) {
    	 DecimalFormat df = new DecimalFormat("0.000");
         g.setColor(Color.white);
         g.drawString(""+df.format((maxprice-j*((maxprice-minprice)/10))), 36, (i+60));
         g.setColor(Color.LIGHT_GRAY);
         g.drawLine(80, (i + 60), 1300, (i + 60));
         j++;
     }
     g.setColor(Color.white);
     g.drawLine(80, 40, 80, 600);
     g.drawLine(80, 600, 1300, 600);

  
     g.setColor(Color.white);
     g.setFont(new Font("Arial Unicode MS", Font.PLAIN, 22));
     g.drawString(title, 10, 25);
     g.setFont(new Font("Arial Unicode MS", Font.PLAIN, 15));
     g.drawString(yCoordinate, 10, 45);
     g.drawString(xCoordinate, 1200, 640);
  }

}