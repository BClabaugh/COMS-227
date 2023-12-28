package hw4;

import api.Point;
import api.PositionVector;
/**
 * 
 * @author Blake Clabaugh
 *
 */
public class StraightLink extends AbstractLink{
	
	/**
	 * Models a fixed link with three paths. The following figure shows
	 *  the three paths labeled A, B, and C.
 
 A     B
 --- ---
     \
      \C
 
The paths A and B run in the same direction and C branches away.
A train from A always passes to B.
A train from B always passes to A.
A train from C always passes to A.
	 */
	
	/**
	 * holds the endPoint A as a point var
	 */
	private Point endPointA;
	/**
	 * holds the endPoint B as a point var
	 */
	private Point endPointB;
	/**
	 * holds the endPoint C as a point var
	 */
	private Point endPointC;

	
	/**
	 * constructor for straightLink
	 * @param lowpoint
	 * @param highpoint
	 * @param highpoint2
	 */
	public StraightLink(Point lowpoint, Point highpoint, Point highpoint2) {
		this.endPointA = lowpoint;
		this.endPointB = highpoint;
		this.endPointC = highpoint2;

	}
		
	/**
	 * gets connected point to whatever point is the
	 * @param point
	 */
	@Override
	public Point getConnectedPoint(Point point) {
		// TODO Auto-generated method stub
		if(point.equals(endPointA)) {
			return endPointB;
		}if(point.equals(endPointB)) {
			return endPointA;
		}if(point.equals(endPointC)) {
			return endPointA;
		}
		return null;
	}


	

}
