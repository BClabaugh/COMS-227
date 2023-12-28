package hw4;

import api.Point;
import api.PositionVector;
/**
 * 
 * @author Blake Clabaugh
 *
 */
public class TurnLink extends AbstractLink{
	
	/**
	 * Models a fixed link with three paths. The following figure shows the three paths labeled A, B, and C.
 		A     B
 		--- ---
     		\
      		 \C
 
		The paths A and B run in the same direction and C branches away.
		A train from A always passes to C.
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
	 * Constructor for turnlink
	 * @param endpointA
	 * @param endpointB
	 * @param endpointC
	 */
	public TurnLink(Point endpointA, Point endpointB, Point endpointC) {
		this.endPointA = endpointA;
		this.endPointB = endpointB;
		this.endPointC = endpointC;
		
	}
	
	/**
	 * gets connected point to whatever point is the
	 * @param point
	 */
	@Override
	public Point getConnectedPoint(Point highPoint) {
		if(highPoint.equals(endPointA)) {
			return endPointC;
		}if(highPoint.equals(endPointB)) {
			return endPointA;
		}if(highPoint.equals(endPointC)) {
			return endPointA;
		}
		return null;
	}

		
	/**
	 * shifts from one point to another between paths
	 */
	@Override
	public void shiftPoints(PositionVector positionVector) {
		
		Point endPoint1 = positionVector.getPointA();
		Point endPoint2 = getConnectedPoint(positionVector.getPointA());
		
		// TODO Auto-generated method stub THIS IS STRAIGHT FROM COUPL
		if(positionVector.getPointB().equals(endPoint1)) {
			positionVector.setPointA(endPoint2);
			if(endPoint2.getPointIndex()== 0) {
			positionVector.setPointB(endPoint2.getPath().getPointByIndex(1));
			}else {
				positionVector.setPointB(endPoint2.getPath().getPointByIndex(endPoint2.getPath().getNumPoints() - 2));
			}
		
		}else {
			positionVector.setPointA(endPointA);
			if(endPointA.getPointIndex()== 0) {
				positionVector.setPointB(endPointA.getPath().getPointByIndex(1));
			}else {
				positionVector.setPointB(endPointA.getPath().getPointByIndex(endPointA.getPath().getNumPoints() - 2));
			}
		}
	}
}
		
		
		
		
	

