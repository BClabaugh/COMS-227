package hw4;

import api.Point;
/**
 * creates and manages the specifics of switchLink
 * @author Blake Clabaugh
 *
 */
public class SwitchLink extends AbstractLink{

	/*
	 * Models a switchable link with three paths. A boolean turn determines which path trains take.
	 *  By default turn is set to false. The following figure shows the three paths labeled A, B, and C.
 A     B
 --- ---
     \
      \C
 
The paths A and B run in the same direction and C branches away.
When turn is true a train from A passes to C.
When turn is false a train from A passes to B.
A train from B always passes to A.
A train from C always passes to A.
The turn cannot be modified when the train is in the crossing.
	 */
	
	/**
	 * holds endpoint var
	 */
	private Point endPointA;
	/**
	 * holds endpoint var
	 */
	private Point endPointB;
	/**
	 * holds endpoint var
	 */
	private Point endPointC;
	
	/**
	 * holds turn boolean
	 */
	private boolean turn;
	
	/**
	 * constructor for switch Link
	 * @param highpoint
	 * @param lowpoint
	 * @param lowpoint2
	 */
	public SwitchLink(Point highpoint, Point lowpoint, Point lowpoint2) {
		// TODO Auto-generated constructor stub
		this.endPointA = highpoint;
		this.endPointB = lowpoint;
		this.endPointC = lowpoint2;
	}
	/**
	 * gets connected points
	 * @param point
	 */
	public Point getConnectedPoint(Point point) {
		// TODO Auto-generated method stub
	
		if(point.equals(endPointA)) {
			if(turn) {
			return endPointC;
			}else {
				return endPointB;
			}
		}if(point.equals(endPointB)) {
			return endPointA;
		}if(point.equals(endPointC)) {
			return endPointA;
		}
		return null;
	}
	/**
	 * sets if the link turns
	 * @param turn
	 */
	public void setTurn(boolean turn) {
		// TODO Auto-generated method stub
		if(!isCrossing()) {
			this.turn=turn;
		}
		
	}
	

}
