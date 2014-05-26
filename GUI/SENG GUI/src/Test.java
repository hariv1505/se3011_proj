import java.awt.geom.Point2D;


public class Test {
	 private static  Point2D.Double[] curvePoints = {
	    	//new Point2D.Double (10, 180),
	    	//new Point2D.Double (30, 30),
	    	//new Point2D.Double (50, 50),
		 new Point2D.Double (0, 2), 
	    	new Point2D.Double (2, 30),
	    	new Point2D.Double (3,50),
	    	new Point2D.Double (6, 29),
	    	new Point2D.Double (7, 20),
	    	new Point2D.Double (8, 23),
	    	new Point2D.Double (12, 26),
	    	new Point2D.Double (13, 24),
	    	new Point2D.Double (15, 27),
	    	new Point2D.Double (20, 30),
	    	new Point2D.Double (25, 35),
	    	new Point2D.Double (30, 80),
	    	new Point2D.Double (36, 18),
	    	new Point2D.Double (41, 31),
	    	new Point2D.Double (43,32),
	    	new Point2D.Double (46, 33),
	    	new Point2D.Double (57, 34),
	    	new Point2D.Double (61, 36),
	    	new Point2D.Double (63, 43),
	    	new Point2D.Double (67, 41),
	    	new Point2D.Double (70, 24),
	    	new Point2D.Double (71, 31),
	    	new Point2D.Double (74, 28),
	    	new Point2D.Double (75, 42),
	    	new Point2D.Double (76, 13),
	    	//new Point2D.Double (80, 445)
	    };
	    
	    private static Point2D.Double[] buyPoints = {
	    	//new Point2D.Double (10, 180),
	    	//new Point2D.Double (30, 30),
	    	//new Point2D.Double (50, 50),
	    	
	    	new Point2D.Double (7, 20),
	    	
	    	new Point2D.Double (36,18 ),
	    	
	    	//new Point2D.Double (80, 445)
	    };
	    
	    private static Point2D.Double[] sellPoints = {
	    	//new Point2D.Double (10, 180),
	    	//new Point2D.Double (30, 30),
	    	//new Point2D.Double (50, 50),
	    
	    	new Point2D.Double (30, 80),
	    	
	    	new Point2D.Double (3,50 ),
	   
	    	//new Point2D.Double (80, 445)
	    };
	    public static void main(String[] args) {
		GraphTest g=new GraphTest();
		//GraphTest g1=new GraphTest();
		g.setStrings("Graph123", "Time (seccc)", "Price ($$)");
		g.getinput(curvePoints, buyPoints, sellPoints);
		g.GenarateGraph();
	    
	}
}
