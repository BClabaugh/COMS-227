package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;
import api.Traversable;


/**
 * This class holds lots of the duplicated code from the eaiser links
 * @author Blake Clabaugh
 */
public abstract class AbstractLink implements Crossable{
	
	/**
	 * this variable tells if the train is crossing or not
	 */
	private boolean isCrossing;
	
	
	public void trainEnteredCrossing() {
		setCrossing(true);
	}
	
	
	public void trainExitedCrossing() {
		setCrossing(false);
	}
	
	/**
	 * the reason i didnt want to unduplicate this class as much is because its so specific to the vars that come in
	 * and also takes away a class in deadlink so works well enough
	 */
	public Point getConnectedPoint(Point highPoint) {
		return null;
	}

	/**
	 * This method was duplicated in the less intensive links that has one to two points in them and thats why I 
	 * was able to use it in the abstract class. Having it here makes it accesible to all of those links so I didnt have to 
	 * duplicate
	 */
	public void shiftPoints(PositionVector positionVector) {
			// TODO Auto-generated method stub
			Point endPoint1 = positionVector.getPointA();
			Point endPoint2 = getConnectedPoint(positionVector.getPointB());
			
			// TODO Auto-generated method stub THIS IS STRAIGHT FROM COUPL
			if(positionVector.getPointB().equals(endPoint1)) {
				positionVector.setPointA(endPoint2);
				if(endPoint2.getPointIndex()== 0) {
				positionVector.setPointB(endPoint2.getPath().getPointByIndex(1));
				}else {
					positionVector.setPointB(endPoint2.getPath().getPointByIndex(endPoint2.getPath().getNumPoints() - 2));
				}
			}else if(positionVector.getPointB().equals(endPoint2)) {
				positionVector.setPointA(endPoint1);
				if(endPoint1.getPointIndex()== 0) {
				positionVector.setPointB(endPoint1.getPath().getPointByIndex(1));
				}else {
					positionVector.setPointB(endPoint1.getPath().getPointByIndex(endPoint1.getPath().getNumPoints() - 2));
				}
			}
			
		}
		
	
	/**
	 * I used this because it was the most common amount of paths in the project
	 * It tells how many paths are connected to the intersection
	 */
	public int getNumPaths() {
		return 3;
	}

	/**
	 * this class is a getter on the var isCrossing
	 */
	protected boolean isCrossing() {
		return isCrossing;
	}

	/**
	 * a setter for the var  is Crossing
	 */
	protected void setCrossing(boolean isCrossing) {
		this.isCrossing = isCrossing;
	}
	
	
}
